<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		content.jsp
	</div>
	<div class="card-body">		
		<a href="${pageContext.request.contextPath}/exam04/HtmlResponseController" class="btn btn-info btn-sm">Html 응답 생성</a>
		<a href="${pageContext.request.contextPath}/exam04/JsonResponseController" class="btn btn-info btn-sm">JSON 응답 생성</a>
		<a href="${pageContext.request.contextPath}/exam04/FileResponseController" class="btn btn-info btn-sm">파일 다운로드 응답 생성</a>
	</div>
</div>

<div class="card m-2">
	<div class="card-header">
		redirect
	</div>
	<div class="card-body">		
		<form method="post" action="${pageContext.request.contextPath}/exam04/ContentController">
		    <label for="email2" class="mb-2 mr-sm-2">Email:</label>
		    <input type="text" class="form-control mb-2 mr-sm-2" id="email2" placeholder="Enter email" name="email">
		    <label for="pwd2" class="mb-2 mr-sm-2">Password:</label>
		    <input type="text" class="form-control mb-2 mr-sm-2" id="pwd2" placeholder="Enter password" name="pswd">
		    <div class="form-check mb-2 mr-sm-2">
		      <label class="form-check-label">
		        <input type="checkbox" class="form-check-input" name="remember"> Remember me
		      </label>
		    </div>    
	   		<button type="submit" class="btn btn-primary mb-2">Submit</button>
	 	 </form>
	</div>
</div>
		

<%@ include file="/WEB-INF/views/common/footer.jsp" %>