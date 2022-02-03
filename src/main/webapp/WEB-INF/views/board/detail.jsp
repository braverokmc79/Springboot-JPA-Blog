<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
		
		<button type="button"  class="btn btn-secondary"  onclick="history.back()">돌아가기</button>

		<c:if test="${board.user.username eq principal.username }">
			<button id="btn-update"	class="btn btn-warning">수정</button>
			<button id="btn-delete"	class="btn btn-danger">삭제</button>		
		</c:if>
	
	
		<br><br>
	
		
		<div class="mb-5">
			글번호 : <span id="id" class="mr-5"><i>${board.id}</i></span>
			작성자 : <span><i>${board.user.username}</i></span>
		</div>
	
		<div class="form-group mb-5">
			<h3>${board.title }</h3>
		</div>

		<hr>
		<div class="form-group"> 		
			<div>${board.content }</div>
		</div>
			
		<hr>
	

</div>



<script src="${pageContext.request.contextPath}/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>


