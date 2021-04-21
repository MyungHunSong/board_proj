<%@page import="board_proj.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri ="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<style type="text/css">
#name {
	text-align: center;
}

.bt {
	margin: auto;
	width: 1000px;
	height: 600px;
	border: 1px solid #ccc;
	border-top: 2px solid red;
	border-collapse: collapse;
	text-align: center;
}

#titlecolor{
	border-bottom: 2px solid red; 
	background-color: skyblue;
}

.bt td {
	border: 1px solid #ccc;
	padding: 10px;
	text-align: center;
}


a{
	text-decoration: none;
}

#httpS{
	text-align: left;
}
#iam {
	background-color: #cacece;
	font-size: 18px;
	font: bolder;
	color: black;
	text-align: center;
}

#iamhere {
	text-align: center;
}
</style>
</head>
<body>

	<!-- ${articleList} -->
	<h2 id="iam">게시판 리스트</h2>
	<table border="1" class="bt">
		<tr id = "titlecolor" >
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>날짜</td>
			<td>조회수</td>
		</tr>
		<c:forEach var="board" items="${articleList}">
		
			<tr>
				<td>${board.boardNum}</td>
				<td id="httpS">
					<c:if test="${board.boardReLev ne 0}">
						<c:forEach var="i" begin="1" end="${board.boardReLev * 2}">
							&nbsp;&nbsp;
						</c:forEach>
						→답변:
					</c:if>
					<a href = "boardDetail.do?boardNum=${board.boardNum}&page=${pageInfo.page}">${board.boardSubject}</a>
				</td>
				<td>${board.boardName}</td>
				<td>${board.boardDate}</td>
				<td>${board.boardReadCount}</td>
			</tr>
		</c:forEach>
	</table>
	${pageInfo}
	<c:choose>
		<c:when
			test="${fn:length(articleList) != 0 && pageInfo.listCount > 0 }">
			<section id="pageList">
				<c:choose>
					<c:when test="${pageInfo.page <= 1 }">
						[이전]&nbsp;
					</c:when>
					<c:otherwise>
						<a href="boardList.do?page=${pageInfo.page -1}">[이전]</a>&nbsp;
					</c:otherwise>
				</c:choose>

				<c:forEach var="a" begin="1" end="${pageInfo.endPage}">
					<c:choose>
						<c:when test="${a == pageInfo.page}">
							<span>[${a}]</span>&nbsp;
						</c:when>
						<c:otherwise>
							<a href="boardList.do?page=${a}">[${a}]</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:choose>
					<c:when test="${pageInfo.page >= pageInfo.maxPage }">
						[다음]&nbsp;
					</c:when>
					<c:otherwise>
						<a href="boardList.do?page=${pageInfo.page +1}">[다음]</a>&nbsp;
					</c:otherwise>
				</c:choose>
			</section>
		</c:when>
		<c:otherwise>
			<section>등록된 글이 없습니다.</section>
		</c:otherwise>
	</c:choose>

	<%-- <section id = "iamhere">
		<c:if test="${pageInfo.page <= 1}">
			[이전]&nbsp;
		</c:if>
		<c:if test="${pageInfo.page > 1}">
			<a href="boardList.do?page=${pageInfo.page -1}">[이전]</a>&nbsp;
		</c:if>	
		<c:forEach var = "a" begin = "1" end= "${pageInfo.endPage }">
			<c:if test="${a == pageInfo.page}">
				[${a}]
			</c:if>
			<c:if test="${a ne pageInfo.page}">
				<a href = "boardList.do?page=${a}">[${a}]</a>
			</c:if>
		</c:forEach>
		
		<c:if test="${pageInfo.page > pageInfo.maxPage }">
			[다음]&nbsp;
		</c:if>
			<c:if test="${pageInfo.page < pageInfo.maxPage}">
				<a href = "boardList.do?page=${pageInfo.page +1}">[다음]</a>&nbsp;
			</c:if>
	</section> --%>
</body>
</html>