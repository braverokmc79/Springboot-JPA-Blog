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
					<textarea id="reply-content"    class="form-control" rows="1" cols=""></textarea>
				</div>		
				<div class="card-footer text-center">
					 <input type="hidden" name="boardId" id="boardId" value="${board.id}" >
					
					
					<button id="btn-reply-save" class="btn btn-primary">등록</button>
				</div>	
		</div>
	
	

				
		<%
			//EAGER 전략일 경우 replys 의 데이터를 같이 가져오나, LAZY 전략일경우  replys 할때 데이터를 가져온다.
			//LAZY 사용시    @JsonIgnore 사용

		   // @OneToMany(mappedBy = "board", fetch = FetchType.LAZY) //하나의 게시판에 여러개의 댓글이 존재 , 따라서 oneToMany 의 기본전략은 LAZY 이다.
		  //@JsonIgnore  //@JsonIgnore 애노테이션을 사용함으로써   List<Reply> 객체는 포함하지 않는다.  그러나 replys 호출하는 순간 데이터를 가져오게 된다. 
		   //@JsonIgnoreProperties({"board"})  
		  //@OrderBy("id desc")
		  //private List<Reply> replys;
			System.out.println(" **** replys   호출  :");
		%>
			
		
		<div class="card mt-5">
			<div class="card-header">댓글 리스트  ${board.replys.size() }</div>
			<ul id="reply--box" class="list-group">
			
		<c:forEach items="${board.replys}" var="reply" >
			  <li id="reply-${reply.id}"   class="list-group-item d-flex justify-content-between">
			  	<div>${reply.content}</div>
			  	<div class="d-flex ">
			  		<div>작성자 : ${reply.user.username}</div>
			  		
			  		
			  	  	<c:if test="${reply.user.id ==auth.id}">
			  	  		<div class="ml-3">	
			  			<button 			  		
			  			data-boardId='${board.id}'
				  		data-replyId='${reply.id}'
				  		class="btn btn-danger btn-sm replyDelete">삭제</button>
				  		</div>
			  		</c:if>
			  						  		
			  		
			  	</div>			  
			  </li>
		</c:forEach>
		
			</ul>
		</div>



</div>



<script src="${Home}/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>


