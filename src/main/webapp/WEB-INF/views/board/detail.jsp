<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
		
		<button type="button"  class="btn btn-secondary"  onclick="history.back()">돌아가기</button>

		<c:if test="${board.user.username eq principal.username }">
			<a href="${Home}/board/${board.id}/updateForm"  class="btn btn-warning">수정</a>			
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
	

		<div class="card">
				<div class="card-body">
					<textarea class="form-control" rows="1" cols=""></textarea>
				</div>		
				<div class="card-footer text-center">
					<button class="btn btn-primary">등록</button>
				</div>	
		</div>		
		
			
		
		<div class="card mt-5">
			<div class="card-header">댓글 리스트  ${board.replys.size() }</div>
			<ul id="reply--box" class="list-group">
			
		<c:forEach items="${board.replys}" var="reply" >
			  <li id="reply--1"   class="list-group-item d-flex justify-content-between">
			  	<div>${reply.content}</div>
			  	<div class="d-flex ">
			  		<div>작성자 : ${reply.user.username}</div>
			  		<div class="ml-3"><button class="btn btn-danger btn-sm">삭제</button></div>				  		
			  	</div>			  
			  </li>
		</c:forEach>
		
			</ul>
		</div>




</div>



<script src="${Home}/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>


