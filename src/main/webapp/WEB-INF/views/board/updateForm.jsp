<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form method="post">
		<div class="form-group">
			<input type="text" class="form-control" placeholder="제목" id="title" name="title" value="${board.title}">
		</div>

		<div class="form-group">	
			<textarea rows="5" cols=""   id="content" name="content" class="form-control summernote">${board.content}</textarea>
		</div>
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<p style="color: red;">${loginErrorMsg}</p>

		<input type="hidden" name="id" id="id" value="${board.id}">
		<button type="button" id="btn-update" class="btn btn-primary">글수정 완료</button>
	</form>

</div>


    <script>
      $('.summernote').summernote({
        placeholder: '내용을 입력해 주세요.',
        tabsize: 3,
        height: 300,
      });
    </script>


<script src="${Home}/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>


