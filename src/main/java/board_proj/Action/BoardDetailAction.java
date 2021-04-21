package board_proj.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.service.BoardDetailService;

public class BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//boardDetail.do?boardNum=15&page=2{pageInfo.page}
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		int page = Integer.parseInt(request.getParameter("page"));
		request.setAttribute("page", page);
		
		BoardDetailService service = new BoardDetailService();
		 BoardDto article = service.getArticle(boardNum);
		 
		 request.setAttribute("page", page);
		 request.setAttribute("article", article);
		 
		 ActionForward forward = new ActionForward();
		 forward.setPath("/board/qna_board_view.jsp");
		return forward;
	}

}
