<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="content" />
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Football betting site">
    <meta name="author" content="Artemyev">
    <link rel="icon" href="${pageContext.request.contextPath}/images/football.png">

    <title><fmt:message key="menu.brand"/></title>

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/stdashboard.css" rel="stylesheet">
</head>
<body>
    <%@ include file="menu.jsp"%>
    <div class="container-fluid">
        <%@include file="sidebar.jsp"%>
        <div class="row col-md-offset-2  col-sm-offset-3">
          <c:choose>
              <c:when test="${not empty search}">
                  <c:set var="page" value="path.page.main" scope="session"/>
                  <h2 class="sub-header"><fmt:message key="match.search"/></h2>
              </c:when>
              <c:when test="${not empty all}">
                  <c:set var="page" value="path.page.all" scope="session"/>
                  <h2 class="sub-header"><fmt:message key="match.all"/></h2>
              </c:when>
              <c:otherwise>
                  <c:set var="page" value="path.page.home" scope="session"/>
                  <h2 class="sub-header"><fmt:message key="match.back"/></h2>
              </c:otherwise>
          </c:choose>s
          <%@include file="matches.jsp"%>
      </div>
    </div>
    <%@include file="footer.jsp"%>
</body>
</html>