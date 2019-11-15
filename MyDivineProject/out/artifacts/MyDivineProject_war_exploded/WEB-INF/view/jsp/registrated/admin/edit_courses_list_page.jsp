<%@include file="/WEB-INF/view/jspf/common/page_info.jspf" %>
<%@include file="/WEB-INF/view/jspf/common/taglib.jspf" %>
<html>
<c:set var="title" value="Edit course list" scope="page"/>
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

<c:if test="${not empty courses}">

    <table id="info-table">
        <tr>
            <th><fmt:message key="page.lang.course.name"/></th>
            <th><fmt:message key="page.lang.course.topic"/></th>
            <th><fmt:message key="page.lang.teacher"/></th>
            <th><fmt:message key="page.lang.course.start"/></th>
            <th><fmt:message key="page.lang.course.end"/></th>
        </tr>
        <c:forEach items="${courses}" var="course">
            <tr>
                <td>${course.name}</td>
                <td>${course.topic}</td>
                <td>${course.teacher}</td>
                <td>${course.startDate}</td>
                <td>${course.endDate}</td>
                <c:if test="${not empty user && !user.blocked}">
                    <td>
                        <form action="/controller" method="post">
                            <input type="hidden" name="id" value="${course.id}">
                            <input type="hidden" name="command" value="thisCourseEdit">

                            <input type="submit" name="submit" class="btn btn-sm btn-primary"
                                   value="<fmt:message key="page.lang.edit"/>">

                        </form>
                    </td>
                    <td>
                        <form action="/controller" >
                            <input type="hidden" name="id" value="${course.id}">
                            <input type="hidden" name="command" value="toDeleteCourse">
                            <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.delete"/>">
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</c:if>



<u:permit role="admin"/>
<%-- Footer --%>
<%@include file="/WEB-INF/view/jspf/common/footer.jspf" %>
</body>
</html>