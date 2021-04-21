package board_proj.service;

import java.sql.Connection;
import java.util.ArrayList;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDS;
import board_proj.dto.BoardDto;

public class BoardDetailService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public BoardDetailService() {
		dao.setCon(con);
	}
	
	public int getListCount() {
		return dao.selectListCount();
	}
	
	public BoardDto getArticle(int boardNum){
		// 조회수 증가
		// boardNum 에 해당하는 BoardDto return 할 것이다.
		dao.updateReadCount(boardNum);
		return dao.selectArticle(boardNum);
	}
}
