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
<c:if test="${ empty sessionScope.students}">
    <h6 class="page-element">
        <fmt:message key="page.lang.no.reg"/>
    </h6>
</c:if>

<c:if test="${not empty sessionScope.students}">
    <c:set var="finished" value="finished" scope="page"/>

    <h6 class="page-element">
        <fmt:message key="page.lang.course"/>: ${sessionScope.name} <fmt:message
            key="page.lang.course.state"/>: ${sessionScope.state}
    </h6>

    <table id="info-table">
        <tr>
            <th><fmt:message key="page.lang.student.name"/></th>
            <c:if test="${sessionScope.state.name == finished}">
                <th><fmt:message key="page.lang.course.assessment"/></th>
            </c:if>


        </tr>
        <c:forEach items="${sessionScope.students}" var="student">

            <tr>
                <td>${student.key.fullName}</td>
                <c:if test="${student.value == 0}">
                    <td>
                        <%@include file="/WEB-INF/view/jspf/registrated/teacher/check_state.jspf" %>
                    </td>
                </c:if>
                <c:if test="${student.value > 0}">
                    <td>${student.value}</td>
                </c:if>

                <c:if test="${sessionScope.state.name != finished}">
                    <td>
                        <form action="/controller" method="post">
                            <input type="hidden" name="command" value="toExclude">
                            <input type="hidden" name="login" value="${student.key.login}">
                            <input type="hidden" name="id" value="${id}">
                            <input type="submit" class="btn btn-sm btn-primary"
                                   value="<fmt:message key="page.lang.exclude"/>">
                        </form>
                    </td>
                </c:if>
            </tr>

        </c:forEach>
    </table>

    <table id="info-table">
        <tr>


            <c:if test="${sessionScope.state.name == finished}">
                <th>
                    Successful
                </th>
                <th>
                    Not Successful
                </th>
            </c:if>

        </tr>
        <td>
                ${sessionScope.pass}
        </td>
        <td>
                ${sessionScope.failed}
        </td>

        </tr>


    </table>


</c:if>
<u:permit role="teacher"/>

<%-- Footer --%>
<%@include file="/WEB-INF/view/jspf/common/footer.jspf" %>
</body>
</html>