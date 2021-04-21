<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Mvc 게시판</title>
	<script>
		function modfiyboard(){
			modifyform.submit();
		}
	</script>
	<style type="text/css">
		#regisForm{
			width: 500px;
			height: 600px;
			border: 1px solid red;
			margin: auto;
		}
		
		h2{
			text-align: center;
		}
		
		table{
			margin: auto;
			width: 450px;
		}
		.td_left{
			width: 150px;
			background: orange;
		}
		
		.td_right{
			width: 300px;
			
		}
		#commandCell{
			text-align: center;
		}
	</style>
	</head>
<body>
	<section id="writeForm">
		<h2>게시판글수정</h2>
		<form action="boardModifyPro.do" method="post" name="modifyform">
		<input type = "hidden" name ="boardNum" id="BOARD_NUM" value ="${article.boardNum}">
		<input type="hidden" name="page" value="${page}">
			<table>
				<tr>
					<td class="td_left">
						<label for="BOARD_NAME">글쓴이</label>
					</td>
					<td class="td_right">
						<input type="text" name="BOARD_NAME" id="BOARD_NAME" value ="${article.boardName}"/>
					</td>
				</tr>
				<tr>
					<td class = "td_left">
						<label for="BOARD_PASS">비밀번호</label>
					</td>
					<td class="td_right">
						<input name="BOARD_PASS" type="password" id="BOARD_PASS">
					</td>
				</tr>
				<tr>
					<td class="td_left">
						<label for="BOARD_SUBJECT">제목</label>
					</td>
					<td class="td_right">
						<input name="BOARD_SUBJECT" type="text" id ="BOARD_SUBJECT" value="${article.boardSubject}"/>
					</td>
				</tr>
				<tr>
					<td class="td_left">
						<label for="BOARD_CONTENT">내용</label>
					</td>
					<td>
						<textarea id="BOARD_CONTENT" name = "BOARD_CONTENT" rows="15" cols="40" >
							${article.boardContent}
						</textarea>
					</td>
				</tr>
			</table>
			 
			<section id="commandCell">
				<a href="javascript:modfiyboard()">[수정]</a>
				<a href="javascript:history.go(-1)">[뒤로]</a>				
			</section>
		</form>
	</section>
</body>
</html>