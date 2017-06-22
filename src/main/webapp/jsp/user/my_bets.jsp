<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="content" />
<html>
<head>
    <link rel="icon" href="${pageContext.request.contextPath}/images/football.png">
    <title><fmt:message key="sidebar.bets"/></title>

    <link href="${pageContext.request.contextPath}/css/stdashboard.css" rel="stylesheet">
</head>
<body>
<%@ include file="../menu.jsp"%>
<div class="container-fluid">
    <%@include file="../sidebar.jsp"%>
    <div class="row col-md-offset-2  col-sm-offset-3">
        <c:set var="page" value="path.page.bets" scope="session"/>
        <h2 class="sub-header"><fmt:message key="sidebar.bets"/></h2>
        <%@include file="../matches.jsp"%>
    </div>
</div>
<%@include file="../footer.jsp"%>
</body>
</html>