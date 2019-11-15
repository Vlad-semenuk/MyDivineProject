<%@include file="/WEB-INF/view/jspf/common/page_info.jspf" %>
<%@include file="/WEB-INF/view/jspf/common/taglib.jspf" %>
<html>
<c:set var="title" value="Student Courses" scope="page"/>
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


<c:if test="${empty sessionScope.courses}">
    <h3 class="page-element">
        <fmt:message key="page.lang.course.empty"/>
    </h3>
</c:if>


<c:if test="${not empty sessionScope.courses}">


    <table id="info-table">
        <h3 class="page-element">
            <fmt:message key="page.lang.course.your"/>
        </h3>
        <tr>
            <th><fmt:message key="page.lang.course.name"/></th>
            <th><fmt:message key="page.lang.course.topic"/></th>
            <th><fmt:message key="page.lang.course.state"/></th>
            <th><fmt:message key="page.lang.course.count"/></th>
            <th><fmt:message key="page.lang.course.start"/></th>
            <th><fmt:message key="page.lang.course.end"/></th>
        </tr>
        <c:forEach items="${sessionScope.courses}" var="course">
            <tr>
                <td>${course.name}</td>
                <td>${course.topic}</td>
                <td>${course.state}</td>
                <td>${course.count}</td>
                <td>${course.startDate}</td>
                <td>${course.endDate}</td>
                <c:if test="${not empty user && !user.blocked}">
                    <td>
                        <form action="/controller">
                            <input type="hidden" name="command" value="toViewCourse">
                            <input type="hidden" name="id" value="${course.id}">
                            <input type="hidden" name="name" value="${course.name}">
                            <input type="hidden" name="state" value="${course.state}">
                            <input type="submit" class="btn btn-sm btn-primary"
                                   value="<fmt:message key="page.lang.view"/>">
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>

    <u:permit role="teacher"/>

</c:if>

<%-- Footer --%>
<%@include file="/WEB-INF/view/jspf/common/footer.jspf" %>
</body>
</html>

