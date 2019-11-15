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


<div class="page-element">
    <h5 class="table">
        <fmt:message key="page.lang.course.add"/>
    </h5>
</div>
<form action="/controller" method="post">
    <input type="hidden" name="id" value="1">
    <input type="hidden" name="command" value="createCourse">
    <table id="info-table">
        <tr>
            <th><fmt:message key="page.lang.course.name"/></th>
            <th><fmt:message key="page.lang.course.topic"/></th>
            <th><fmt:message key="page.lang.teacher"/></th>
            <th><fmt:message key="page.lang.course.start"/></th>
            <th><fmt:message key="page.lang.course.end"/></th>
        </tr>
        <tr>
            <td>
                <input name="courseName" minlength="2" maxlength="32">
            </td>
            <td>
                <select name="topics">
                    <c:forEach items="${sessionScope.topics}" var="topic">
                        <option value="${topic}"><c:out value="${topic}"/></option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select name="teacher">
                    <c:forEach items="${sessionScope.teachers}" var="teacher">
                        <option value="${teacher.login}"><c:out value="${teacher}"/></option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <input type="date" name="startDate">
            </td>
            <td>
                <input type="date" name="endDate">
            </td>
            <td>
                <input type="submit" name="submit" class="btn btn-sm btn-primary"
                       value="<fmt:message key="page.lang.accept"/>">
            </td>
        </tr>
    </table>


</form>


<u:permit role="admin"/>
<%-- Footer --%>
<%@include file="/WEB-INF/view/jspf/common/footer.jspf" %>
</body>
</html>