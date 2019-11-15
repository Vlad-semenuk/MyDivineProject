<%@include file="/WEB-INF/view/jspf/common/page_info.jspf" %>
<%@include file="/WEB-INF/view/jspf/common/taglib.jspf" %>
<html>
<c:set var="title" value="Create course" scope="page"/>
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
<c:if test="${not empty sessionScope.popular}">

    <table id="info-table">
        <tr>
            <th><fmt:message key="page.lang.course.name"/></th>

            <th><fmt:message key="page.lang.course.count"/></th>

        </tr>
        <c:forEach items="${sessionScope.popular}" var="popula">
            <tr>
                <td>${popula.name}</td>
                <td>${popula.count}</td>

            </tr>
        </c:forEach>
    </table>
</c:if>


<u:permit role="admin"/>
<%-- Footer --%>
<%@include file="/WEB-INF/view/jspf/common/footer.jspf" %>
</body>
</html>