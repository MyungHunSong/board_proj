package board_proj.service;

import java.sql.Connection;
import java.util.ArrayList;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDS;
import board_proj.dto.BoardDto;

public class BoardModifyProService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public BoardModifyProService() {
		dao.setCon(con);
	}
	
	public int getListCount() {
		return dao.selectListCount();
	}
		
	public boolean isArticleWriter(int boardNum, String pass) {
		return dao.isArticlBoardWriter(boardNum, pass);
	}
	
	public boolean modifyArticle(BoardDto article) {
		return dao.updateArticle(article) ==1 ? true:false;
	}
}
