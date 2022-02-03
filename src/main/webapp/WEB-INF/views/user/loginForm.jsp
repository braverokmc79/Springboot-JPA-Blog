<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form action="${Home}/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">Username</label> <input type="text" class="form-control" placeholder="Enter username" id="username" name="username">
		</div>

		<div class="form-group">
			<label for="password">Password</label> <input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
		</div>
		<div class="form-group form-check">
			<label class="form-check-label"> <input class="form-check-input" type="checkbox" name="remember"> Remember me
			</label>
		</div>
		  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		
		 <p style="color: red;">${loginErrorMsg}</p>
		
		<button type="submit"  id="btn-login" class="btn btn-primary">로그인</button>
	</form>
	
	
</div>




<%-- 
<script src="${Home}/js/user.js"></script>
 --%>
<%@ include file="../layout/footer.jsp"%>


