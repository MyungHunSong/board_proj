<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>페이지 알티클</title>
	<style type="text/css">
		#registForm{
			width: 450px;
			height: 650px;
			border: 1px solid red;
			margin: auto;
		}
		
		h2{
			text-align: center;
		}
		table {
			margin: auto;
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
		#commandCell{
			text-align: center;
		}
	</style>
</head>
<body>
	board_re_ref :${article.boardReRef}<br>
	board_re_lev:${article.boardReLev}<br>
	board_re_seq:${article.boardReSeq}
	<section id="writeForm">
		<h2>게시판글등록</h2>
		<form action="boardReplyPro.do" method="post" name="boardform">
			<input type="hidden" name="page" value="${page}"/>
			<input type="hidden" name="boardNum" value="${article.boardNum}"/>
			<input type="hidden" name="BOARD_RE_REF" value="${article.boardReRef}"/>
			<input type="hidden" name="BOARD_RE_LEV" value="${article.boardReLev}"/>
			<input type="hidden" name="BOARD_RE_SEQ" value="${article.boardReSeq}"/>
			<table>
				<tr>
					<td class ="td_left"><label for="BOARD_NAME">글쓴이</label></td>
					<td class="td_right"><input type="text" name="BOARD_NAME" id="BOARD_NAME"></td>
				</tr>
				<tr>
					<td class="td_left">
						<label for="BOARD_NAME">글쓴이</label>
					</td>
				</tr>
				<tr>
					<td class="td_left">
						<label for="BOARD_PASS">비밀번호</label>
					</td>
					<td class="td_right">
						<input name="BOARD_PASS" type="password" id="BOARD_PASS">
					</td>
				</tr>
				<tr>
					<td class="td_left">
						<label for="BOARD_SUBJECT">제 목</label>
					</td>
					<td class="td_right">
						<input name="BOARD_SUBJECT" type="text" id="BOARD_SUBJECT">
					</td>
				</tr>
				<tr>
					<td class="td_left">
						<label for="BOARD_CONTENT">내 용</label>
					</td>
					<td>
						<textarea name="BOARD_CONTENT"  id="BOARD_CONTENT" col="40" rows="15"></textarea>
					</td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="답변글등록"/>&nbsp;&nbsp;
				<input type="reset" value="다시작성"/>
			</section>
		</form>
	</section>
</body>
</html>