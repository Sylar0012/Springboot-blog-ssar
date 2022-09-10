<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/users/${users.id }/update" method="post">
		<div class="mb-3">
			<input type="password" class="form-control"
				placeholder="Enter new password" name="password" required="required">
		</div>
		<div class="mb-3">
			<input type="email" class="form-control"
				placeholder="Enter new email" name="email" required="required"
				value="${users.email }">
		</div>
		<button type="submit" class="btn btn-primary">회원정보 수정</button>
	</form>
	<br/>
	<form method="post" action="/users/${users.id}/delete">
		<button class="btn btn-danger">회원탈퇴</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>

