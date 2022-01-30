<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form method="post">
		<div class="form-group">
			<label for="username">Title</label> 
			<input type="text" class="form-control" placeholder="Enter Title" id="title" name="title">
		</div>

		<div class="form-group">
			<label for="content">content</label> 		
			<textarea rows="5" cols=""   id="content" name="content" class="form-control summernote"></textarea>
		</div>
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<p style="color: red;">${loginErrorMsg}</p>

		<button type="button" id="btn-save" class="btn btn-primary">글쓰기 완료</button>
	</form>

</div>


    <script>
      $('.summernote').summernote({
        placeholder: '내용을 입력해 주세요.',
        tabsize: 3,
        height: 300,
      });
    </script>


<script src="${pageContext.request.contextPath}/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>


