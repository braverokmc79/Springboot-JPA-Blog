<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form action="${Home}/auth/joinProc" method="post">
		<div class="form-group">
			<label for="username">Username</label> 
			<input type="text" class="form-control" placeholder="Enter username" id="username" name="username" required="required">
		</div>

		<div class="form-group">
			<label for="password">Password</label> 
			<input type="password" class="form-control" placeholder="Enter password" id="password" name="password" required="required">
		</div>

		<div class="form-group">
			<label for="email">Email</label> 
			<input type="email" class="form-control" placeholder="Enter email" id="email" name="email" required="required">
		</div>


		<button type="button" id="btn-save" class="btn btn-primary">회원가입완료</button>
	</form>


</div>



<script src="${Home}/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>

