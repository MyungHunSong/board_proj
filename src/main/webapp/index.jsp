<%@page import="board_proj.ds.JndiDS"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<%=JndiDS.getConnection() %><br>
	<a href="boardWriteForm.do">게시판글쓰기</a>
	<br>
	<a href = "boardList.do">목록 보기</a>	
</body>
</html> 