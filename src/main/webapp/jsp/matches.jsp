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
        <c:when test="${fn:length(match_list)eq 0}">
            <h2><fmt:message key="match.empty"/></h2>
        </c:when>
        <c:otherwise>
            <c:set var="number_of_pages" value="${fn:length(match_list)/10 +1}" scope="request"/>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th colspan="6"></th>
                    <th><fmt:message key="match.home.win"/></th>
                    <th><fmt:message key="match.draw"/></th>
                    <th><fmt:message key="match.away.win"/></th>
                </tr>
                </thead>
                <tbody id="myTable">
                <c:forEach var="match" items="${match_list}" begin="${num_page * 10}" end="${num_page * 10 + 9}">
                    <tr>
                        <td width="5%">${match.tournament}</td>
                        <td width="10%">${match.homeTeam}</td>
                        <td width="10%">${match.awayTeam}</td>
                        <td width="5%">${match.homeTeamGoals}</td>
                        <td width="5%">${match.awayTeamGoals}</td>
                        <td width="20%">${match.matchDate}</td>
                        <td width="5%">1.3</td>
                        <td width="5%">2.7</td>
                        <td width="5%">3.5</td>
                        <ctg:notDeleted>

                            <td>
                                <ctg:isAdmin>
                                    <button class="btn btn-info"
                                            onclick='location.href="${pageContext.request.contextPath}/controller?command=delete&match_id=${match.matchId}"'>
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
                                                    onclick='location.href="${pageContext.request.contextPath}/controller?command=bet&match_id=${match.matchId}"'>
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
