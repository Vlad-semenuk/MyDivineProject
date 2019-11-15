<%@include file="/WEB-INF/view/jspf/common/page_info.jspf" %>
<%@include file="/WEB-INF/view/jspf/common/taglib.jspf" %>
<html>
<c:set var="title" value="Main" scope="page"/>
<%@include file="/WEB-INF/view/jspf/common/head.jspf" %>
<body>

<%-- Menu --%>
<ul>
   <li><a href="/controller?command=toMainPage"><fmt:message key="page.lang.main"/></a></li>
    <%@include file="/WEB-INF/view/jspf/common/personal_menu.jspf" %>
    <%@include file="/WEB-INF/view/jspf/common/course_list.jspf" %>
    <%@include file="/WEB-INF/view/jspf/common/language_menu.jspf" %>
    <%@include file="/WEB-INF/view/jspf/common/logout_menu.jspf" %>
</ul>
<hr/>





<%-- Footer --%>
<%@include file="/WEB-INF/view/jspf/common/footer.jspf" %>

<u:permit role="student"/>
</body>
</html>