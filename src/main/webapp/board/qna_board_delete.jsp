<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MVC 게시판</title>
	<!-- <link rel="stylesheet" href="../board_proj/css/qna_board_delete.css"> -->
	<style>
	#passForm{
		width:400px;
		margin: auto;
		border: 1px solid orange;
		}
	</style>
	
</head>
<body>
	${page}<br>${boardNum }
	<section id = "passForm">
		<form name="deleteForm" method="post" action="boardDeletePro.do?boardNum=${boardNum}" >
		<input type="hidden" name="page" value="${page}">
		<table>
			<tr>
				<td>
					<label for="pass">글 비밀번호:</label>
				</td>
				<td>
					<input name="BOARD_PASS" type="password">
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="삭제"/>&nbsp;&nbsp;
					   <input type="button" value="돌아가기" onclick="javascript:history.go(-1)"/>
				</td>
			</tr>
		</table>
		</form>
	</section>
</body>
</html>