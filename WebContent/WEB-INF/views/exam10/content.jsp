<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		content.jsp
	</div>
	<div class="card-body">		
		<a href="/servletjsp/exam10/ExceptionHandlingController" class="btn btn-info btn-sm">try-catch 사용</a>
		<a href="/servletjsp/exam10/NoExceptionHandlingController" class="btn btn-info btn-sm">web.xml 사용</a>
	
		
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>