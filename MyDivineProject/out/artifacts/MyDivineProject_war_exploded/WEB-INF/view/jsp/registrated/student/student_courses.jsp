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

<c:set var="open" value="open" scope="page"/>
<c:set var="during" value="during" scope="page"/>
<c:set var="finished" value="finished" scope="page"/>


<%--checking for courses in the session--%>
<c:if test="${empty sessionScope.coursesOpen }">
    <c:if test="${empty  sessionScope.coursesDuring }">

        <c:if test="${empty  sessionScope.coursesFinished}">

            <h3 class="page-element">
                <fmt:message key="page.lang.course.empty"/>
            </h3>
        </c:if>
    </c:if>

</c:if>


<c:if test="${not empty sessionScope.coursesOpen}">

    <h5 class="page-element">
        <fmt:message key="page.lang.course.lint.open"/><br/>
    </h5>
    <%@include file="/WEB-INF/view/jspf/registrated/student/open_courses.jspf" %>
    <br/>
</c:if>

<c:if test="${not empty sessionScope.coursesDuring}">
    <h5 class="page-element">
        <fmt:message key="page.lang.course.lint.during"/><br/>
    </h5>
    <%@include file="/WEB-INF/view/jspf/registrated/student/during_courses.jspf" %>
    <br/>
</c:if>

<c:if test="${not empty sessionScope.coursesFinished}">
    <h5 class="page-element">
        <fmt:message key="page.lang.course.lint.finished"/><br/>
    </h5>
    <%@include file="/WEB-INF/view/jspf/registrated/student/finished_courses.jspf" %>
</c:if>

<%-- Check permission --%>

<u:permit role="student"/>


<%-- Footer --%>
<%@include file="/WEB-INF/view/jspf/common/footer.jspf" %>
</body>
</html>