package board_proj.dao;

import java.util.ArrayList;

import board_proj.dto.BoardDto;

public interface BoardDao {
	int selectListCount();
	ArrayList<BoardDto> selectArticleList(int page, int limit);
	BoardDto selectArticle(int boardNum);
	int insertArticle(BoardDto article);
	
	// 리플라이 다는것.
	int insertReplyArticle(BoardDto article);
	
	
	int updateArticle(BoardDto article);
	int deleteArticle(int article);
	int updateReadCount(int boardNum);
	boolean isArticlBoardWriter(int boardNum, String pass);
	int nextBoardNum();
	
	
}
