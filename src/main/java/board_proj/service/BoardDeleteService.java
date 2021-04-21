package board_proj.service;

import java.sql.Connection;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDS;
import board_proj.dto.BoardDto;

public class BoardDeleteService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public BoardDeleteService() {
		dao.setCon(con);
	}
	
	public boolean isArticleWrite(int boardNum,String pass) {
		return dao.isArticlBoardWriter(boardNum, pass);
	}
	
	public boolean removeArticle(int boardNum) {
		return dao.deleteArticle(boardNum)==1 ? true:false;
	}
}
