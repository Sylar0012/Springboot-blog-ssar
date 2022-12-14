<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br />
	<div class="d-flex justify-content-end">
		<div style="width: 300px;">
			<form class="d-flex" method="get" action="/">
				<input class="form-control me-2" type="text" placeholder="Search" name="keyword">
				<button class="btn btn-primary" type="submit">Search</button>
			</form>
		</div>
	</div>
	<br />
	<table class="table table-striped">
		<thead>
			<tr>
				<th>번호</th>
				<th>게시글제목</th>
				<th>작성자이름</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="boards" items="#{boardsList }">
				<tr>
					<td>${boards.id }</td>
					<td><a href="/boards/${boards.id }">${boards.title }</a></td>
					<td>${boards.username }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


	<!-- 
	<ul class="pagination">
		<c:choose>
			<c:when test="${paging.first}">
				<li class="page-item disabled"><a class="page-link" href="?page=${paging.currentPage-1 }">이전페이지</a></li>
				<c:forEach var="num" begin="1" end="${paging.totalPage}" step="1">
					<a class="page-link" href="?page=${num-1 }">${num}</a>
				</c:forEach>
				<li class="page-item"><a class="page-link" href="?page=${paging.currentPage+1 }">다음페이지</a></li>
			</c:when>

			<c:when test="${paging.last }">
				<li class="page-item"><a class="page-link" href="?page=${paging.currentPage-1 }">이전페이지</a></li>
				<c:forEach var="num" begin="1" end="${paging.totalPage}" step="1">
					<a class="page-link" href="?page=${num-1 }">${num}</a>
				</c:forEach>
				<li class="page-item disabled"><a class="page-link" href="?page=${paging.currentPage+1 }">다음페이지</a></li>
			</c:when>

			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${paging.currentPage-1 }">이전페이지</a></li>
				<c:forEach var="num" begin="1" end="${paging.totalPage}" step="1">
					<a class="page-link" href="?page=${num-1 }">${num}</a>
				</c:forEach>
				<li class="page-item"><a class="page-link" href="?page=${paging.currentPage+1 }">다음페이지</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
	
 -->

	<div class="d-flex justify-content-center">
		<ul class="pagination">
			<li class='page-item ${paging.first ? "disabled" : "" }'><a class="page-link"
				href="?page=${paging.currentPage-1 }">Prev</a></li>
			<c:forEach var="num" begin="${paging.startPageNum }" end="${paging.lastPageNum}" step="1">
				<a class="page-link" href='?page=${num-1}&keyword=${paging.keyword}'>${num}</a>
			</c:forEach>
			<li class='page-item ${paging.last ? "disabled" : "" }'><a class="page-link"
				href="?page=${paging.currentPage+1 }">Next</a></li>
		</ul>
	</div>

</div>

<%@ include file="../layout/footer.jsp"%>

