package board_proj.Action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.dto.PageInfoDto;
import board_proj.service.BoardListService;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page") !=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		BoardListService service =new BoardListService();
		//총 리스트 개수.
		int listCount = service.getListCount();
		ArrayList<BoardDto> list = service.getArticleList(page, limit);
		
		//list.stream().forEach(System.out::println);
		
		// 21.0/10 = 5
		int maxPage = (int)(Math.ceil((double)listCount/limit));
		
		// 1~5, 6~10, 11~15,16~20
		//11 page 페이지 부터 나와야함 (51~56)
		//int startPage =  (( (int) ((double)page/limit+0.9)-1) *10+1);  
		int startPage = (int)Math.floor(page/10)*10+1;
		int endPage = startPage + 9;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfoDto pageInfo = new PageInfoDto(page, maxPage, startPage, endPage, listCount);
				request.setAttribute("articleList", list);
				request.setAttribute("pageInfo", pageInfo);
				
				System.out.println("pageInfo"+ pageInfo);
				
		//System.out.println("listCount = "+listCount + "maxpage>>" + maxPage);
		//System.out.println("startPage >>" +startPage);
		
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("/board/qna_board_list.jsp");
		return forward;
	}

}
