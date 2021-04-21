package board_proj.dto;

import java.sql.Date;

public class BoardDto {
	private int boardNum;
	private String boardName;
	private String boardPass;
	private String boardSubject;
	private String boardContent;
	private String boardFile;
	private int boardReadCount;
	private int boardReRef;
	private int boardReLev;
	private int boardReSeq;
	private Date boardDate;
	
	
	
	public BoardDto() {
		super();
	}
	
	


	public BoardDto(int boardNum, String boardName, String boardPass, String boardSubject, String boardContent,
			int boardReRef, int boardReLev, int boardReSeq) {
		this.boardNum = boardNum;
		this.boardName = boardName;
		this.boardPass = boardPass;
		this.boardSubject = boardSubject;
		this.boardContent = boardContent;
		this.boardReRef = boardReRef;
		this.boardReLev = boardReLev;
		this.boardReSeq = boardReSeq;
	}




	public BoardDto(int boardNum, String boardName, String boardPass, String boardSubject, String boardContent,
			String boardFile, int boardReadCount, int boardReRef, int boardReLev, int boardReSeq, Date boardDate) {
		this.boardNum = boardNum;
		this.boardName = boardName;
		this.boardPass = boardPass;
		this.boardSubject = boardSubject;
		this.boardContent = boardContent;
		this.boardFile = boardFile;
		this.boardReadCount = boardReadCount;
		this.boardReRef = boardReRef;
		this.boardReLev = boardReLev;
		this.boardReSeq = boardReSeq;
		this.boardDate = boardDate;
	}



	public BoardDto(String boardName, String boardPass, String boardSubject, String boardContent, String boardFile) {
		this.boardName = boardName;
		this.boardPass = boardPass;
		this.boardSubject = boardSubject;
		this.boardContent = boardContent;
		this.boardFile = boardFile;
	}



	public int getBoardNum() {
		return boardNum;
	}



	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}



	public String getBoardName() {
		return boardName;
	}



	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}



	public String getBoardPass() {
		return boardPass;
	}



	public void setBoardPass(String boardPass) {
		this.boardPass = boardPass;
	}



	public String getBoardSubject() {
		return boardSubject;
	}



	public void setBoardSubject(String boardSubject) {
		this.boardSubject = boardSubject;
	}



	public String getBoardContent() {
		return boardContent;
	}



	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}



	public String getBoardFile() {
		return boardFile;
	}



	public void setBoardFile(String boardFile) {
		this.boardFile = boardFile;
	}



	public int getBoardReadCount() {
		return boardReadCount;
	}



	public void setBoardReadCount(int boardReadCount) {
		this.boardReadCount = boardReadCount;
	}



	public int getBoardReRef() {
		return boardReRef;
	}



	public void setBoardReRef(int boardReRef) {
		this.boardReRef = boardReRef;
	}



	public int getBoardReLev() {
		return boardReLev;
	}



	public void setBoardReLev(int boardReLev) {
		this.boardReLev = boardReLev;
	}



	public int getBoardReSeq() {
		return boardReSeq;
	}



	public void setBoardReSeq(int boardReSeq) {
		this.boardReSeq = boardReSeq;
	}



	public Date getBoardDate() {
		return boardDate;
	}



	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}



	@Override
	public String toString() {
		return String.format(
				"BoardDto [boardNum=%s, boardName=%s, boardPass=%s, boardSubject=%s, boardContent=%s, boardFile=%s, boardReadCount=%s, boardReRef=%s, boardReLev=%s, boardReSeq=%s, boardDate=%s]",
				boardNum, boardName, boardPass, boardSubject, boardContent, boardFile, boardReadCount, boardReRef,
				boardReLev, boardReSeq, boardDate);
	}
	
	
	

	
}
