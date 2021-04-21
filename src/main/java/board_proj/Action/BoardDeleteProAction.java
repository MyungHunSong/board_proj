package board_proj.Action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.service.BoardDeleteService;

public class BoardDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// boardDeletePro.do?boardNum=26
		//hiddenpage=1
		//BOARD_PASS
		
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String page = request.getParameter("page");
		String pass = request.getParameter("BOARD_PASS");
		
		BoardDeleteService service = new BoardDeleteService();
		boolean isArticleWriter = service.isArticleWrite(boardNum, pass);
		
		ActionForward forward = null;
		
		if(isArticleWriter) {
			getMessage(response, "삭제할 권한이 없습니다.");
			return forward;
		}
			boolean isDeleteSuccess = service.removeArticle(boardNum);
			
			if(!isDeleteSuccess) {
				getMessage(response, "삭제실패");
				return forward;
			}
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("boardList.do?page="+page);
				
				return forward;
			}
			
	private void getMessage(HttpServletResponse response, String msg) throws IOException {
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.println("alert('"+ msg +"');");
		out.println("history.back()");
		out.println("</script>");
		out.close();
	}

}
