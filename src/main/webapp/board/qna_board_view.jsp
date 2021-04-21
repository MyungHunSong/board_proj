<%@page import="board_proj.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판 글 보기</title>
	<!-- <link rel = "stylesheet" href = "css/qna_board_view.css"> -->
	<style type="text/css">
		#articleForm{
   border-radius: 20px;
   width: 500px;
   height: 500px;
   border: 1px solid #ccc;
   margin: 50px auto;
   text-align: center;
}

h2{
   text-align: center;
}

#basicInfoArea{
   height: 40px;
   text-align: center;
}

#articleContentArea{
   background: #80ff80;
   margin-top: 20px;
   height: 350px;
   text-align: center;
   overflow: auto;
   
}

#commandList{
   margin: auto;
   width: 500px;
   text-align: center;
}
a{
   text-decoration: none;
   color: green;
}

a:hover{
   color: brown;
}
	</style>
	
</head>
<body>
${page}${article}
	<!-- 게시판 수정-->
	<section id = "articleForm">
	<h2>글 내용 상세보기</h2>
	
		제목 :
		${article.boardSubject}
	<%-- 	<form action="boardFilePro.do?boardFile=${boardFile}"> --%>
		첨부파일:
		<c:if test="${article.boardFile ne null}">
		<a  href = "fileDown.do?boardFile=${article.boardFile}"  >
		${article.boardFile }</a>
		</c:if>
	<!-- 	</form> -->
		<article id="articleContentArea">${article.boardContent}</article>
	</section>
	
	<section id = "commandList">
		<a href = "boardReplyForm.do?boardNum=${article.boardNum}&page=${page}">[답변]</a>
		<a href = "boardModifyForm.do?boardNum=${article.boardNum}&page=${page}">[수정]</a>
		<a href = "boardDeleteForm.do?boardNum=${article.boardNum}&page=${page}">[삭제]</a>
		<a href = "boardList.do?page=${pageInfo.page}">[목록]</a>&nbsp;&nbsp;
	</section> 
	
</html>