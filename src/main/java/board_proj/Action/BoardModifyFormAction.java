package board_proj.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.service.BoardModifyService;

public class BoardModifyFormAction implements Action {
	private BoardModifyService service = new BoardModifyService();
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		int page = Integer.parseInt(request.getParameter("page"));
		System.out.println("BoardModifyFormAction boardNum >> " + boardNum);
//		System.out.println("boardNum>>" +boardNum); // 한번 찍어 봐야한다
		
		
		BoardDto article = service.getArticle(boardNum);
		System.out.println("boardNum>>" +article);
		
		request.setAttribute("article", article);
		request.setAttribute("page",page);
		ActionForward forward = new ActionForward();
		forward.setPath("/board/qna_board_modify.jsp");
		return forward;
	}

}
