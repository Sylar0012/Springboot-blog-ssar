<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br /> <br />

	<c:if test="${principal.id == boards.usersId }">
		<div class="d-flex">

				<button class="btn btn-warning"><a href="/boards/${boards.id}/updateForm">수정하기</a></button>

			<form method="post" action="/boards/${boards.id}/delete">
				<button class="btn btn-danger">삭제</button>
			</form>
		</div>
	</c:if>
	<div>
		<h3>${boards.title}</h3>
	</div>
	<hr />

	<div>${boards.content}</div>


</div>

<%@ include file="../layout/footer.jsp"%>

