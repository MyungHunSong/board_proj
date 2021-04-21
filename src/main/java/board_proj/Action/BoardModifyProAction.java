package board_proj.Action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.service.BoardModifyProService;
import board_proj.service.BoardModifyService;

public class BoardModifyProAction implements Action {
	private BoardModifyProService service = new BoardModifyProService();
	
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String page = request.getParameter("page");
		String pass = request.getParameter("BOARD_PASS");
		
				
		ActionForward forward =null;
		boolean isArticleWriter = service.isArticleWriter(boardNum, pass);
			if(!isArticleWriter) {
				sendMessage(response, "수정할 권한이 없습니다 ");
				return forward;
			}
			
//			boolean modfiyArticle = service.modifyArticle(article);
			
			
			BoardDto article = new BoardDto();

			article.setBoardNum(boardNum);
			article.setBoardSubject(request.getParameter("BOARD_SUBJECT"));
			article.setBoardContent(request.getParameter("BOARD_CONTENT"));
			
			boolean isModfiySucess = service.modifyArticle(article);
			if(!isModfiySucess){
				sendMessage(response, "삭제할 권한이 없습니다.");
				return forward;
			}
			
			
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("boardDetail.do?boardNum="+boardNum + "&page=" +page);
			
			return forward;

	}

	private void sendMessage(HttpServletResponse response, String msg) throws IOException {
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.println("alert('"+msg+"');");
		out.println("history.back()");
		out.println("</script>");
		out.close();
		
		
	}

}
