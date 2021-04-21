package board_proj.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.Action.Action;
import board_proj.Action.BoardDeleteProAction;
import board_proj.Action.BoardDetailAction;
import board_proj.Action.BoardFileProAction;
import board_proj.Action.BoardListAction;
import board_proj.Action.BoardModifyFormAction;
import board_proj.Action.BoardModifyProAction;
import board_proj.Action.BoardReplyFormAction;
import board_proj.Action.BoardReplyProAction;
import board_proj.Action.BoardWriteProAction;
import board_proj.dto.ActionForward;


@WebServlet("*.do") // 여기서 do를 설정해줫기에 들룰수있다.
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doProcess(request, response);
	}


	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		String requestURI = request.getRequestURI();
//		String contextPath = request.getContextPath();
//		String command = requestURI.substring(contextPath.length());
//		System.out.println(requestURI + ">>" + contextPath +">>"+command);
		
		String command = request.getServletPath();
		System.out.println(command);
		
		ActionForward forward = null;
		Action action = null;
		
		
	
		if(command.equals("/boardWriteForm.do")) {
			
			forward = new ActionForward();
			forward.setPath("/board/qna_board_write.jsp");
		}else if (command.equals("/boardWritePro.do")) {
			action = new BoardWriteProAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}else if(command.contentEquals("/boardList.do")) {
			action = new BoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}else if(command.contentEquals("/boardDetail.do")) {
			action = new BoardDetailAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/boardDetailForm.do")) {
			action = new BoardDetailAction();
			
			try {
				forward = action.execute(request, response); 
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// 삭제 폼
		}else if(command.equals("/boardDeleteForm.do")) {
			
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);
			
			int boardNum = Integer.parseInt(request.getParameter("boardNum"));
			request.setAttribute("boardNum", boardNum);
			
			forward = new ActionForward();
			forward.setPath("/board/qna_board_delete.jsp");
		}else if(command.equals("/boardDeletePro.do")) {
			
			action = new BoardDeleteProAction();
			try {
				forward = action.execute(request, response); 
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/fileDown.do")) {
			
			action = new BoardFileProAction();
			try {
				forward= action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/boardModifyForm.do")) {
			action = new BoardModifyFormAction();
			try {
				forward= action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/boardModifyPro.do")) {
			action = new BoardModifyProAction();
			try {
				forward= action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/boardReplyForm.do")) {
			action = new BoardReplyFormAction();
			try {
				forward= action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/boardReplyPro.do")) {
			action = new BoardReplyProAction();
		}try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
	}
	
	

}
