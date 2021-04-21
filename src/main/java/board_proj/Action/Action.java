package board_proj.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;

public interface Action {
	ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
