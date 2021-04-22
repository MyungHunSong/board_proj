package board_proj.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import board_proj.Action.Action;
import board_proj.Action.NullAction;
//import board_proj.Action.BoardDeleteProAction;
//import board_proj.Action.BoardDetailAction;
//import board_proj.Action.BoardFileProAction;
//import board_proj.Action.BoardListAction;
//import board_proj.Action.BoardModifyFormAction;
//import board_proj.Action.BoardModifyProAction;
//import board_proj.Action.BoardReplyFormAction;
//import board_proj.Action.BoardReplyProAction;
//import board_proj.Action.BoardWriteProAction;
import board_proj.dto.ActionForward;


@WebServlet( urlPatterns = {"*.do"}, // 여기서 do를 설정해줫기에 들룰수있다.
					loadOnStartup = 1, // loadOnStartUp = 1로 되잇으면, 제일 먼저 실행하란 뜻이다
					initParams = { 
							@WebInitParam
							(name="configFile", value="/WEB-INF/commandAction.properties")} // 콘피그란 곳에는 요  value값에 잇는 주소가 담겨잇다.
		) 
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Action> actionMap = new HashMap<>(); // 2게 String& Action
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() - config : " + config.getInitParameter("configFile"));
		
		String configFile = config.getInitParameter("configFile");
		
		try(InputStream is = config.getServletContext().getResourceAsStream(configFile)){
			Properties props = new Properties();
			props.load(is);
			
			System.out.println("props >> " + props);
			
			for(Entry<Object, Object> entry : props.entrySet()) {
				Class<?> cls; 
				Action action = null;
				
				try {
					cls=Class.forName((String)entry.getValue());
					action = (Action) cls.newInstance();
				}catch (ClassNotFoundException  e) {
					action = new NullAction();
					e.printStackTrace();
				}
				actionMap.put((String)entry.getKey(), action); // 키값은 getKey, 밸류값은  action
			}
			
		} catch (IOException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() throws ServletException {
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			doProcess(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			doProcess(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String command = request.getServletPath();
		System.out.println(command);

		Action  action = actionMap.get(command);
		ActionForward forward = action.execute(request, response);
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
	}
		
/*		
		try {
			if(command.equals("/boardWriteForm.do")) {
			
			forward = new ActionForward();
			forward.setPath("/board/qna_board_write.jsp");
		}else if (command.equals("/boardWritePro.do")) {
			
			action = new BoardWriteProAction();
			forward = action.execute(request, response);
				
		}else if(command.contentEquals("/boardList.do")) {
			
			action = new BoardListAction();
			forward = action.execute(request, response);
	
		}else if(command.contentEquals("/boardDetail.do")) {
			
			action = new BoardDetailAction();
			forward = action.execute(request, response);
			
		}else if(command.equals("/boardDetailForm.do")) {
			
			action = new BoardDetailAction();
			forward = action.execute(request, response); 
			
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
			forward = action.execute(request, response); 
			
		}else if(command.equals("/fileDown.do")) {
			
			action = new BoardFileProAction();
			forward= action.execute(request, response);
			
		}else if(command.equals("/boardModifyForm.do")) {
			
			action = new BoardModifyFormAction();
			forward= action.execute(request, response);
			
		}else if(command.equals("/boardModifyPro.do")) {
			
			action = new BoardModifyProAction();
			forward= action.execute(request, response);
			
		}else if(command.equals("/boardReplyForm.do")) {
			
			action = new BoardReplyFormAction();
			forward= action.execute(request, response);
			
		}else if(command.equals("/boardReplyPro.do")) {
			
			action = new BoardReplyProAction();
			forward = action.execute(request, response);
		}	
		}catch (Exception e) {
			e.printStackTrace();
		}
*/		

}
