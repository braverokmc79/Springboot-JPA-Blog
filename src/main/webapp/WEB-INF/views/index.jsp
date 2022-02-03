<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="layout/header.jsp" %>

<div class="container">

	<form>
		<div class="form-inline justify-content-center" th:object="${searchCond}">
			<select class="form-control mr-3"  name="searchType"  >
				<option value="title"    ${searchCond.searchType eq 'title'  ? 'selected':''} >제목</option>
				<option value="username" ${searchCond.searchType eq 'username'  ? 'selected':''} >작성자</option>
				<option value="content"  ${searchCond.searchType eq 'content'  ? 'selected':''} >내용</option>
			</select> 
			<input type="text" class="form-control mr-3"  name="keyword" value="${searchCond.keyword}"    placeholder="검색어를 입력해주세요." size="50"> 
			
			<select class="form-control mr-3"  name="searchDateType"  style="width: auto;">
				<option value="all" >전체기간</option>
				<option value="1d" ${searchCond.searchDateType eq '1d' ? 'selected' :'' }  >1일</option>
				<option value="1w" ${searchCond.searchDateType eq '1w' ? 'selected' :'' }>1주</option>
				<option value="1m" ${searchCond.searchDateType eq '1m' ? 'selected' :'' }>1개월</option>
				<option value="6m" ${searchCond.searchDateType eq '6m' ? 'selected' :'' }>6개월</option>
			</select>
			
			<button  type="submit" class="btn btn-primary">검색</button>
		 </div>
	</form>
	


	<c:forEach items="${boards.content}" var="board">
	    <div class="card m-2">
	        <div class="card-body">
	            <h4 class="card-title">${board.title}</h4>
	            <a href="${Home}/board/${board.id}" class="btn btn-primary">상세보기</a>
	        </div>
	    </div>	
	</c:forEach>




[[${searchCond.getBoardsLink()}]]<br>
첫번째 페이지 : [[${boards.first}]] <br> [[${pagination.listCnt}]] 개 <br> [[${pagination}]] <br> 현재 페이지 :[[${pagination.curPage}]]
<ul class="pagination justify-content-center">
	   <c:if test="${not boards.first}">
	  	  <li class="page-item">
		  	<a class="page-link" href="${searchCond.getBoardsLink()}&page=1">≪</a>
		  </li>
		  <li class="page-item">
		  	<a class="page-link" href="${searchCond.getBoardsLink()}&page=${pagination.prevPage}">&lt;</a>
		  </li>
	  </c:if>
	  
	  <c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" 	var="page" step="1" varStatus="status">
	  	<li class="page-item ${page eq pagination.curPage  ? 'active' : ''}">
	  	<a class="page-link" href="${searchCond.getBoardsLink()}&page=${page}">${page}</a>
	  	</li>
	  </c:forEach>
	  
	  
	  <c:if test="${not boards.last}">
	  	<li class="page-item"><a class="page-link" href="${searchCond.getBoardsLink()}&page=${pagination.nextPage}">&gt;</a></li>  	
	  	<li class="page-item"><a class="page-link" href="${searchCond.getBoardsLink()}&page=${boards.totalPages}">≫</a></li>  	
	  </c:if>  
	  
</ul>


</div>
<%@ include file="layout/footer.jsp" %>


