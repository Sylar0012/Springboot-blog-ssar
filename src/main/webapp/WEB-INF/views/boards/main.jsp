<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>번호</th>
				<th>게시글제목</th>
				<th>작성자이름</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach var ="boards" items="#{boardsList }">
			<tr>
				<td>${boards.id }</td>
				<td><a href="/boards/${boards.id }">${boards.title }</a></td>
				<td>${boards.username }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>


</div>

<%@ include file="../layout/footer.jsp"%>

