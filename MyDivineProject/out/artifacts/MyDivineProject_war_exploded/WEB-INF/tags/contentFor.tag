<%@ tag language="java" pageEncoding="UTF-8" %>


<%@ attribute name="user" type="my.divine.project.model.entity.User" %>

<%@include file="/WEB-INF/view/jspf/common/taglib.jspf" %>

<%-- Language settings --%>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>


<c:set var="student" value="student" scope="page"/>
<c:set var="teacher" value="teacher" scope="page"/>
<c:set var="admin" value="admin" scope="page"/>

<c:if test="${not empty user && user.role.name == student}">
    <h5>
    <fmt:message key="page.lang.student"/> : ${sessionScope.user.fullName}</br>
    </h5>
    <fmt:message key="page.lang.edit.name"/>

    <form action="/controller">
        <input type="hidden" name="command" value="toEditUser">
        <input name="fullName" required minlength="2" maxlength="16">
        <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.accept"/>">
    </form>
    <br/>

    <form action="/controller">
        <input type="hidden" name="command" value="toUserCourses">
        <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.courses"/>">
    </form>


</c:if>

<c:if test="${not empty user && user.role.name == teacher}">
    <h5>
    <fmt:message key="page.lang.teacher"/> : ${sessionScope.user.fullName}
    </br>
    </h5>
    <fmt:message key="page.lang.edit.name"/>

    <form action="/controller">
        <input type="hidden" name="command" value="toEditUser">
        <input name="fullName" required minlength="2" maxlength="16">
        <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.accept"/>">
    </form>
    <br/>
    <form action="/controller">
        <input type="hidden" name="command" value="toTeacherCourses">
        <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.grade.book"/>">
    </form>


</c:if>

<c:if test="${not empty user && user.role.name == admin}">
    <fmt:message key="page.lang.admin"/> : ${sessionScope.user.fullName}<br/><br/>

    <fmt:message key="page.lang.course.menu"/> :
    <table>
        <tr>
            <td>
                <form action="/controller">
                        <%--                    <input type="hidden" name="command" value="toAddCourses">--%>
                    <a href="create" class="btn btn-sm btn-primary"><fmt:message key="page.lang.add"/></a>
                </form>
            </td>

            <td>

                <form action="/controller">
                    <input type="hidden" name="command" value="toEditCourses">
                    <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.edit"/>">
                </form>
            </td>


        </tr>

    </table>
    <br/>
    <fmt:message key="page.lang.teacher.menu"/> :

    <form action="/controller" method="post">
        <input type="hidden" name="command" value="toAddTeacher">
        <input name="login" required minlength="4" maxlength="32">
        <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.accept"/>">
    </form>

    <br/>

    <fmt:message key="page.lang.block"/> :

    <form action="/controller" method="post">
        <input type="hidden" name="command" value="toBlock">
        <input name="login" required minlength="4" maxlength="32">
        <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.accept"/>">
    </form>

    <fmt:message key="page.lang.unblock"/> :

    <form action="/controller" method="post">
        <input type="hidden" name="command" value="toUnblock">
        <input name="login" required minlength="4" maxlength="32">
        <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.accept"/>">
    </form>
    <br/>
<%--    <form action="/controller" method="get">--%>
<%--        <input type="hidden" name="command" value="popular">--%>
<%--        <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.accept"/>">--%>
<%--    </form>--%>



</c:if>

