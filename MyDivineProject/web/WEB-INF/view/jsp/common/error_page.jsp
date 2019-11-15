
<%@include file="/WEB-INF/view/jspf/common/page_info.jspf" %>
<%@include file="/WEB-INF/view/jspf/common/taglib.jspf" %>
<html>
<c:set var="title" value="Error" scope="page"/>
<%@include file="/WEB-INF/view/jspf/common/head.jspf" %>
<body>
<h3 class="page-element">Error page</h3><hr>
<div class="page-element"><h5>${sessionScope.error}</h5></div><br/>

<%-- Footer --%>
<%@include file="/WEB-INF/view/jspf/common/footer.jspf" %>
</body>
</html>