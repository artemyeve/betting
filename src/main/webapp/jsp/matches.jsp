<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="content"/>
<html>
<head>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
</head>
<body>
<%--begin="${num_page * 10}" end="${num_page * 10 + 9}"--%>
<div class="table-responsive">
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>
    <c:choose>
        <c:when test="${fn:length(matches_list)eq 0}">
            <h2><fmt:message key="match.empty"/></h2>
        </c:when>
        <c:otherwise>
            <c:set var="number_of_pages" value="${fn:length(matches_list)/5 +1}" scope="request"/>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><fmt:message key="add.match.tournament"/></th>
                    <th><fmt:message key="add.match.homeTeam"/></th>
                    <th><fmt:message key="add.match.awayTeam"/></th>
                    <th><fmt:message key="add.match.homeTeamGoals"/></th>
                    <th><fmt:message key="add.match.awayTeamGoals"/></th>
                    <th><fmt:message key="add.match.matchDate"/></th>
                    <th><fmt:message key="add.match.description"/></th>
                </tr>
                </thead>
                <tbody id="myTable">
                <c:forEach var="match" items="${matches_list}" begin="${num_page * 5}" end="${num_page * 5 + 4}">
                    <tr>
                        <td>${match.tournament}</td>
                        <td>${match.homeTeam}</td>
                        <td>${match.awayTeam}</td>
                        <td>${match.homeTeamGoals}</td>
                        <td>${match.awayTeamGoals}</td>
                        <td>${match.matchDate}</td>
                        <td>${match.description}</td>
                        <ctg:notDeleted>
                            <td>
                                <button class="btn btn-link"
                                        onClick='location.href="${pageContext.request.contextPath}/controller?command=matches_info&match_id=${match.id}"'>
                                    <fmt:message key="match.more"/>
                                </button>
                            </td>
                            <td>
                                <ctg:isAdmin>
                                    <button class="btn btn-info"
                                            onclick='location.href="${pageContext.request.contextPath}/controller?command=delete&match_id=${match.id}"'>
                                        <i class="glyphicon glyphicon-trash"></i>
                                        <fmt:message key="match.delete"/>
                                    </button>
                                </ctg:isAdmin>
                            </td>
                            <td>
                                <ctg:isLoggedIn>
                                    <c:choose>
                                        <c:when test="${not empty is_my_bets}">
                                            <button class="btn btn-primary"
                                                    onclick='location.href="${pageContext.request.contextPath}/controller?command=bet&match_id=${match.id}"'>
                                                <i class="glyphicon glyphicon-credit-card"></i>
                                                <fmt:message key="match.bet"/>
                                            </button>
                                        </c:when>
                                    </c:choose>
                                </ctg:isLoggedIn>
                            </td>
                        </ctg:notDeleted>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <c:forEach begin="1" end="${number_of_pages}" var="i">
                <td>
                    <button class="btn btn-default"
                            onclick='location.href="${pageContext.request.contextPath}/controller?command=change_page&page=${i-1}"'>${i}</button>
                </td>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
