<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="content"/>
<html>
<head>
</head>
<body>
<div class="col-sm-3 col-md-2 sidebar" style="position: fixed">
    <ul class="nav nav-sidebar">
        <li><a href="${pageContext.request.contextPath}/controller?command=main"><fmt:message key="sidebar.home"/></a>
        </li>
        <li>
            <ctg:isLoggedIn>
                <a href="${pageContext.request.contextPath}/controller?command=my_bets"><fmt:message key="sidebar.bets"/></a>
            </ctg:isLoggedIn>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/controller?command=all"><fmt:message key="sidebar.all"/></a>
        </li>
        <li><a href="${pageContext.request.contextPath}/jsp/about.jsp"><fmt:message key="sidebar.about"/> </a></li>
        <li>
            <hr>
        </li>
        <li>
            <ctg:isAdmin>
                <a href="${pageContext.request.contextPath}/jsp/admin/add_match.jsp">
                    <fmt:message key="sidebar.add"/>
                </a>
            </ctg:isAdmin>
        </li>
        <li>
            <ctg:isAdmin>
                <a href="${pageContext.request.contextPath}/controller?command=show_users">
                    <fmt:message key="sidebar.users"/>
                </a>
            </ctg:isAdmin>
        </li>
    </ul>
</div>
</body>
</html>
