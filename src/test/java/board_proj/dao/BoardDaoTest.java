package board_proj.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.dto.BoardDto;
import board_proj_erp.JdbcUtil;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardDaoTest {
	private static Connection con = JdbcUtil.getConnection();
	private static BoardDaoImpl dao = BoardDaoImpl.getInstance();
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao.setCon(con);
	}



	@After
	public void tearDown() throws Exception {
		System.out.println();
	}
	// 넥스트
	@Test
	public void text01NextBoardNum() {
		System.out.println("testNextBoardNum()");
		int res = dao.nextBoardNum();
		Assert.assertNotEquals(0, res);
		System.out.println("next Number >> " + res);
	}
	// 셀렉트
	@Test
	public void test02SelectListCount(){
		System.out.println("testSelectListCount()");
		int res = dao.selectListCount();
		
		Assert.assertNotEquals(-1, res);
		System.out.println("ListCount >> " + res);
	}
	
	@Test
	public void test03SelectArticle() {
		System.out.println("testSelectArticle()");
		int boardNum = 22;
		BoardDto rs = dao.selectArticle(boardNum);
		Assert.assertNotEquals(1, rs);
		System.out.println(rs);
	}
	
	@Test
	public void test04UpdateReadCount() {
		System.out.println("testUpdateReadCount()");
		int boardNum = 15;
		int rs = dao.updateReadCount(boardNum);
		Assert.assertNotEquals(1, rs);
		System.out.println(rs);
	}
	
	@Test
	public void test05SelectArticleList() {
		System.out.println("testSelectArticleList()");
			int page = 1;
			int limit = 10;  
			ArrayList<BoardDto> list = dao.selectArticleList(page, limit);
			System.out.println(list);
			Assert.assertNotNull(list);

	      list.stream().forEach(System.out::println);
	      System.out.println("=======================");
//	      dao.selectArticleList(2, 10).stream().forEach(System.out::println);
	}
		
	@Test
	public void test06IsArticlBoardWriter() {
		System.out.println("test07IsArticlBoardWriter() ");
		int boardNum= 22;
		boolean res = dao.isArticlBoardWriter(boardNum, "1234");
		Assert.assertEquals(true, res);
		System.out.println("res >>" + res);
	}
	
	// 인서트
	@Test
	public void test07InsertArticle() {
		System.out.println("testINsertArticle()");
		BoardDto newBoard = new BoardDto(
				"김상건"
				, "1234"
				, "곤뇽"
				, "5시"
				, "test.txt");
		int res = dao.insertArticle(newBoard);

		Assert.assertEquals(1, res);
		System.out.println(newBoard.toString());
	}

	
	@Test
	public void test08UpdateArticle() {
		System.out.println("test08UpdateArticle() ");
		int boardNum =22;
		BoardDto article = dao.selectArticle(boardNum);
		int res = dao.updateArticle(article);
		Assert.assertEquals(1, res);
		
		System.out.println("res>>"+res);
	}


	@Test
	public void test09DeleteArticle() {
		System.out.println("testDeleteArticle()");
		int boardNum = dao.nextBoardNum();
		int res =dao.deleteArticle(boardNum);
		Assert.assertEquals(1,res);
		System.out.println("res >> " + res);
	}
	
	@Test
	public void test10InsertReplyArticle() {
		System.out.println("testInsertReplyArticle()");
		BoardDto replyArticle = new BoardDto("정민강3", "1111", "앙대3", "절대로", " ");
		replyArticle.setBoardReRef(22);
		
		int res = dao.insertReplyArticle(replyArticle);
		Assert.assertEquals(1, res);
		System.out.println("res>>"+res);
	}

}
