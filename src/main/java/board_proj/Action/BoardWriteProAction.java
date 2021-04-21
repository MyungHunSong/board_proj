package board_proj.Action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.service.BoardWriteService;

public class BoardWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		BoardDto boardDto = null;
		
		String realFolder ="";
		String saveFolder = "/boardUpload";
		
		int fileSize =5*1024*1024;
		
		ServletContext context = request.getServletContext();
		
		System.out.println("realFolder >>" + realFolder);
		realFolder=context.getRealPath(saveFolder);
		System.out.println(realFolder);
		MultipartRequest multi =new MultipartRequest(
				request
				,realFolder
				,fileSize
				,"UTF-8"
				,new DefaultFileRenamePolicy());
		
		boardDto = new BoardDto();
		boardDto.setBoardName(multi.getParameter("boardName"));
		boardDto.setBoardPass(multi.getParameter("boardPass"));
		boardDto.setBoardSubject(multi.getParameter("boardSubject"));
		boardDto.setBoardContent(multi.getParameter("boardContent"));
		boardDto.setBoardFile(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		System.out.println("multi" + multi);
		//service
		BoardWriteService service = new BoardWriteService();
		boolean result = service.registerArticle(boardDto);
		
		ActionForward forward = null;
		
		if(result) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("boardList.do");
		}else {
			PrintWriter out =response.getWriter();
			out.println("<script>");
			out.println("alter('등록실패')");
			out.println("history.back();");
			out.println("</script>");
		}
	
		System.out.println("BoardWriteProAction.execute()");
		return forward;
	}

}
