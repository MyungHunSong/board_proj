package board_proj.service;

import java.sql.Connection;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDS;
import board_proj.dto.BoardDto;

public class BoardModifyService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public BoardModifyService() {
		dao.setCon(con);
	}
	
	public int getListCount() {
		return dao.selectListCount();
	}
		
	public BoardDto getArticle(int boardNum) {
		return dao.selectArticle(boardNum);
	}
}
