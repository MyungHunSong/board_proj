<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판 글 등록</title>
	<link rel ="stylesheet" href="/board_proj/css/qna_board_write.css">
	<style >
	#registForm{
	width: 500px;
	height: 610px;
	border:1px solid red;
	margin: auto;
}

h2 {
	text-align: center;
}

table{
	margin:auto;
	width: 450px;
}

.td_left{
	width: 150px;
	background: orange;
}
.td_right{
	width: 300px;
	background: skyblue;
}
#commandcell{
	text-align: center;
}
			
</style> 	
</head>
<body>
	<!-- 게시판 등록 -->
	<section id = "wrtieForm">
		<h2>게시판글등록</h2>
		<form action="boardWritePro.do" method ="post"
			enctype="multipart/form-data" name ="boardform">
			<table>
				<tr>
					<td class = "td_left"><label for = "boardName">글쓴이</label></td>
					<td class ="td_right"><input type ="text" name="boardName" id = "boardName" required></td>
				</tr>
				<tr>
					<td class="td_left"><label for = "boardPass">비밀번호</label></td>
					<td class ="td_right"><input type ="password" name="boardPass" id = "boardPass" required></td>
				</tr>
				<tr>
					<td class="td_left"><label for = "boardSubject">제목</label></td>
					<td class ="td_right"><input type ="text" name="boardSubject" id = "boardSubject" required></td>
				</tr>
				<tr>
					<td class="td_left"><label for = "boardContent">내용</label></td>
					<td class ="td_right"><textarea id ="boardContent" name = "boardContent" cols="40" rows="15" required></textarea></td>
				</tr>
				<tr>
					<td class="td_left"><label for = "boardFile">파일첨부</label></td>
					<td class ="td_right"><input type ="file" name="boardFile" id = "boardFile" required></td>
				</tr>
			</table>
			<section id ="commandcell">
				<input type="submit" value="등록">&nbsp;&nbsp;
				<input type ="reset" value ="다시쓰기"/>
			</section>
		</form>
	</section>
	<!-- 게시판 등록 -->
</body>
</html>