package board_proj.Action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.service.BoardReplyProService;

public class BoardReplyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page = Integer.parseInt(request.getParameter("page"));
		BoardDto article = getArticle(request);
		
		BoardReplyProService service = new BoardReplyProService();
		boolean res= service.getArticle(article);
		
		ActionForward forward = null;
		if(res) {
			forward = new ActionForward("boardList.do?page="+page, true);
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alter('답변완료')");
			out.println("history.back();");
			out.println("</script>");
		}
		return forward;
	}

	private BoardDto getArticle(HttpServletRequest request) {

		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		int boardReRef = Integer.parseInt(request.getParameter("BOARD_RE_REF"));
		int boardReLev= Integer.parseInt(request.getParameter("BOARD_RE_LEV"));
		int boardReSeq= Integer.parseInt(request.getParameter("BOARD_RE_SEQ"));
		
		String boardName = request.getParameter("BOARD_NAME");
		String boardPass= request.getParameter("BOARD_PASS");
		String boardSubject= request.getParameter("BOARD_SUBJECT");
		String boardContent= request.getParameter("BOARD_CONTENT");
		
		return new BoardDto(boardNum, boardName, boardPass, boardSubject, boardContent, " ", 0, boardReRef, boardReLev, boardReSeq, null);
	}

}
