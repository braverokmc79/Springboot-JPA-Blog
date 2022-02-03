<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form  method="post">
		<div class="form-group">
			<label for="username">Username</label> 
			<input type="text" class="form-control" value="${userInfo.username}" id="username" name="username" readonly="readonly">
		</div>

		<div class="form-group">
			<label for="password">Password</label> 
			<input type="password" class="form-control"  placeholder="Enter password" id="password" name="password" required="required">
		</div>

		<div class="form-group">
			<label for="email">Email</label> 
			<input type="email" class="form-control"  value="${userInfo.email}"  id="email" name="email" required="required">
		</div>

		<input type="hidden" name="id" id="id"  value="${userInfo.id}" >
		<button type="button" id="btn-update" class="btn btn-primary">회원수정 완료</button>
	</form>


</div>



<script src="${Home}/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>

