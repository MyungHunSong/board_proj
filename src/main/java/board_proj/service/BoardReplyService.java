package board_proj.service;

import java.sql.Connection;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDS;
import board_proj.dto.BoardDto;

public class BoardReplyService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public BoardReplyService() {
		dao.setCon(con);
	}
	
		
	public BoardDto getArticle(int boardNum) {
		return dao.selectArticle(boardNum);
	}
}
