package board_proj.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.service.BoardReplyService;

public class BoardReplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String page = request.getParameter("page");
		
		System.out.println("boardNum>>"+ boardNum + "page>>"+page);
		
		BoardReplyService service = new BoardReplyService();
		BoardDto article = service.getArticle(boardNum);
		
		request.setAttribute("article", article);
		request.setAttribute("page", page);
		
		ActionForward forward =  new ActionForward();
		forward.setPath("/board/qna_board_reply.jsp");
		return forward;
	}

}
