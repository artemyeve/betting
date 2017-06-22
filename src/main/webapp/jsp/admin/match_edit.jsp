<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="content"/>
<html>
<head>
    <link rel="icon" href="${pageContext.request.contextPath}/images/football.png">
    <title><fmt:message key="match.more"/></title>
</head>
<body>
<c:set var="page" value="path.page.match_edit" scope="session"/>
<%@ include file="../menu.jsp" %>
<div class="container" style="margin-top: 90px">
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <form method="post" class="form-horizontal" id="edit" name="edit"
          action="${pageContext.request.contextPath}/controller">
        <div class="form-group">
            <label class="col-md-4 control-label" for="tournament"><fmt:message key="add.match.tournament"/> *</label>
            <div class="col-md-6">
                <input id="tournament" name="tournament" type="text" class="form-control input-md"
                       data-parsley-required data-parsley-length="[1,100]" value="${match.tournament}">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label" for="homeTeam"><fmt:message key="add.match.homeTeam"/> *</label>
            <div class="col-md-6">
                <input id="homeTeam" name="homeTeam" type="text" class="form-control input-md"
                       data-parsley-required data-parsley-length="[1,255]" value="${match.homeTeam}">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label" for="awayTeam"><fmt:message key="add.match.awayTeam"/> *</label>
            <div class="col-md-6">
                <input id="awayTeam" name="awayTeam" type="text" class="form-control input-md"
                       data-parsley-required data-parsley-length="[1,255]" value="${match.awayTeam}">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label" for="homeTeamGoals"><fmt:message key="add.match.homeTeamGoals"/> *</label>
            <div class="col-md-6">
                <input id="homeTeamGoals" name="homeTeamGoals" type="number" class="form-control input-md"
                       data-parsley-required data-parsley-type="number" step="1" data-parsley-min="0"
                       data-parsley-max="100" value="${match.homeTeamGoals}">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label" for="awayTeamGoals"><fmt:message key="add.match.awayTeamGoals"/> *</label>
            <div class="col-md-6">
                <input id="awayTeamGoals" name="awayTeamGoals" type="number" class="form-control input-md"
                       data-parsley-required data-parsley-type="number" step="1" data-parsley-min="0"
                       data-parsley-max="100" value="${match.awayTeamGoals}">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label" for="matchDate"><fmt:message key="add.match.matchDate"/> *</label>
            <div class="col-md-6">
                <input id="matchDate" name="matchDate" type="date" class="form-control input-md"
                       data-parsley-required data-parsley-type="date" value="${match.matchDate}">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label" for="description"><fmt:message key="add.match.description"/> *</label>
            <div class="col-md-6">
                <input id="description" name="description" type="text" class="form-control input-md"
                       data-parsley-required data-parsley-length="[1,255]" value="${match.description}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-4 col-md-offset-4">
                <button type="submit" name="command" value="edit" class="btn btn-primary">
                    <fmt:message key="match.edit"/>
                </button>
                <button type="button" class="btn btn-primary "
                        onClick='location.href="../matches_info.jsp"'>
                    <fmt:message key="form.back"/>
                </button>
            </div>
        </div>
    </form>
</div>
<%@include file="../footer.jsp" %>

<script src="${pageContext.request.contextPath}/js/parsley.min.js"></script>
<script src="${pageContext.request.contextPath}/js/i18n/ruu.js"></script>
<script src="${pageContext.request.contextPath}/js/i18n/enn.js"></script>
<script>
    $(document).ready(function () {
        $('#edit').parsley();
        window.Parsley.setLocale($("#locale").val());
    });
</script>

</body>
</html>
