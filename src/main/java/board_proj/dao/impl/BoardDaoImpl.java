package board_proj.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board_proj.dao.BoardDao;
import board_proj.dto.BoardDto;

public class BoardDaoImpl implements BoardDao {
	private static final BoardDaoImpl instance = new BoardDaoImpl();
	private Connection con;

	public BoardDaoImpl() {

	}

	public static BoardDaoImpl getInstance() {
		return instance;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	// 레코드의 갯수를 구하는것 (들어가 있는 정보 수)
	@Override
	public int selectListCount() {
		String sql = "select count(*) from board";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			ResultSet rs = pstmt.executeQuery();
			{
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 4/13
	@Override
	public ArrayList<BoardDto> selectArticleList(int page, int limit) {
		String sql = "select BOARD_NUM,BOARD_NAME,BOARD_PASS,BOARD_SUBJECT,BOARD_CONTENT,BOARD_FILE"
				+ "	,BOARD_RE_REF,BOARD_RE_LEV,BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE" + " from board"
				+ " order by BOARD_RE_REF desc, BOARD_RE_SEQ asc" + " limit ?,?";

		int startRow = (page - 1) * limit; // 해당 페이지 시작 위치
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					ArrayList<BoardDto> list = new ArrayList<>();

					do {
						list.add(getBoard(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	// BOARD_NUM,BOARD_NAME,BOARD_PASS,BOARD_SUBJECT,BOARD_CONTENT,BOARD_FILE
	// BOARD_RE_REF,BOARD_RE_LEV,BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE
	private BoardDto getBoard(ResultSet rs) throws SQLException {
		int boardNum = rs.getInt("BOARD_NUM");
		String boardName = rs.getString("BOARD_NAME");
		String boardPass = rs.getString("BOARD_PASS");
		String boardSubject = rs.getString("BOARD_SUBJECT");
		String boardContent = rs.getString("BOARD_CONTENT");
		String boardFile = rs.getString("BOARD_FILE");
		int boardReRef = rs.getInt("BOARD_RE_REF");
		int boardReLev = rs.getInt("BOARD_RE_LEV");
		int boardReSeq = rs.getInt("BOARD_RE_SEQ");
		int boardReadCount = rs.getInt("BOARD_READCOUNT");
		Date boardDate = rs.getDate("BOARD_DATE");
		return new BoardDto(boardNum, boardName, boardPass, boardSubject, boardContent, boardFile, boardReadCount,
				boardReRef, boardReLev, boardReSeq, boardDate);
	}

	@Override
	public BoardDto selectArticle(int boardNum) {
		String sql = "select BOARD_NUM" + ",BOARD_NAME" + ",BOARD_PASS" + ",BOARD_SUBJECT" + ",BOARD_CONTENT"
				+ ",BOARD_FILE" + ",BOARD_RE_REF" + ",BOARD_RE_LEV,BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE"
				+ " from board where BOARD_NUM = ?";

		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, boardNum);
			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					return getBoard(rs);
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertArticle(BoardDto article) {
		String sql = "INSERT INTO web_gradle_erp.board "
				+ "(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE" + ", BOARD_RE_REF) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?);";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			int nextNum = nextBoardNum();
			pstmt.setInt(1, nextBoardNum());
			pstmt.setString(2, article.getBoardName());
			pstmt.setString(3, article.getBoardPass());
			pstmt.setString(4, article.getBoardSubject());
			pstmt.setString(5, article.getBoardContent());
			pstmt.setString(6, article.getBoardFile());
			pstmt.setInt(7, nextNum);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public int updateArticle(BoardDto article) {
		String sql = "update board" + " set BOARD_SUBJECT =?, BOARD_CONTENT =?" + "where BOARD_NUM =?";

		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, article.getBoardSubject());
			pstmt.setString(2, article.getBoardContent());
			pstmt.setInt(3, article.getBoardNum());
			System.out.println("updateArticle pstmt >> " + pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public int deleteArticle(int boardNum) {
		String sql = "delete " + "from board " + "where BOARD_NUM =?";

		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, boardNum);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean isArticlBoardWriter(int boardNum, String pass) {
		String sql = "select 1 from board where BOARD_NUM =? and BOARD_PASS =?";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, boardNum);
			pstmt.setString(2, pass);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return true;
				}
			}
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public int nextBoardNum() {
		String sql = "select max(BOARD_NUM) from board";
		try (PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery();) {
			if (rs.next()) {
				return rs.getInt(1) + 1; // 그다음 값을 리턴 해줘야 하기 때문에 +1 을 해줘야한다.
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public int updateReadCount(int boardNum) {
		String sql = "update board set BOARD_READCOUNT = BOARD_READCOUNT +1 where BOARD_NUM = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, boardNum);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int insertReplyArticle(BoardDto article) {
		int nextBoardNum = nextBoardNum();
		int reRef = article.getBoardReRef();
		int reLev = article.getBoardReLev();
		int reSeq = article.getBoardReSeq();

		String sql1 = "update board set BOARD_RE_LEV = BOARD_RE_SEQ +1 " + " where BOARD_RE_REF =? and BOARD_RE_SEQ >? ";

		String sql2 = "insert into board(BOARD_NUM " 
				+ " , BOARD_NAME "
				+ ", BOARD_PASS "
				+ ", BOARD_SUBJECT "
				+ ",BOARD_CONTENT "
				+ ",BOARD_FILE "
				+ ",BOARD_RE_REF "
				+ ",BOARD_RE_LEV "
				+ ",BOARD_RE_SEQ) values "
				+ "	(?,	?, ?, ?, ?, '',? ,? ,?)";
		try {
			con.setAutoCommit(false);
			int res;
			try (PreparedStatement pstmt = con.prepareStatement(sql1)) {
				pstmt.setInt(1, reRef);
				pstmt.setInt(2, reSeq);
				System.out.println(pstmt);
				res = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			reSeq += 1;
			reLev += 1;

			try (PreparedStatement pstmt = con.prepareStatement(sql2)) {
				pstmt.setInt(1, nextBoardNum);
				pstmt.setString(2, article.getBoardName());
				pstmt.setString(3, article.getBoardPass());
				pstmt.setString(4, article.getBoardSubject());
				pstmt.setString(5, article.getBoardContent());
				pstmt.setInt(6, reRef);
				pstmt.setInt(7, reLev);
				pstmt.setInt(8, reSeq);
				System.out.println(pstmt);
				pstmt.executeUpdate();
			}

			con.commit();
			return 1;
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

}
