<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="content"/>
<html>
<head>
    <link rel="icon" href="${pageContext.request.contextPath}/images/football.png">
    <title><fmt:message key="add.match"/></title>
</head>
<body>
<c:set var="page" value="path.page.add.match" scope="session"/>
<%@ include file="../menu.jsp" %>
<div class="container" style="margin-top: 90px">
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>
    <form class="form-horizontal" method="post" id="addMatch" name="addMatch" enctype="multipart/form-data"
          action="${pageContext.request.contextPath}/controller">
        <fieldset>
            <legend><fmt:message key="add.match.title"/></legend>

            <div class="form-group">
                <label class="col-md-4 control-label" for="tournament"><fmt:message key="add.match.tournament"/> *</label>
                <div class="col-md-6">
                    <input id="tournament" name="tournament" type="text" class="form-control input-md"
                           data-parsley-required data-parsley-length="[1,100]">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="homeTeam"><fmt:message key="add.match.homeTeam"/> *</label>
                <div class="col-md-6">
                    <input id="homeTeam" name="homeTeam" type="text" class="form-control input-md"
                           data-parsley-required data-parsley-length="[1,255]">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="awayTeam"><fmt:message key="add.match.awayTeam"/> *</label>
                <div class="col-md-6">
                    <input id="awayTeam" name="awayTeam" type="text" class="form-control input-md"
                           data-parsley-required data-parsley-length="[1,255]">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="homeTeamGoals"><fmt:message key="add.match.homeTeamGoals"/> *</label>
                <div class="col-md-2">
                    <input id="homeTeamGoals" name="homeTeamGoals" type="number" class="form-control input-md"
                           data-parsley-required data-parsley-type="number" step="1" data-parsley-min="0"
                           data-parsley-max="100">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="awayTeamGoals"><fmt:message key="add.match.awayTeamGoals"/> *</label>
                <div class="col-md-2">
                    <input id="awayTeamGoals" name="awayTeamGoals" type="number" class="form-control input-md"
                           data-parsley-required data-parsley-type="number" step="1" data-parsley-min="0"
                           data-parsley-max="100">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="matchDate"><fmt:message key="add.match.matchDate"/> *</label>
                <div class="col-md-2">
                    <input id="matchDate" name="matchDate" type="date" class="form-control input-md"
                           data-parsley-required data-parsley-type="date">
                </div>
            </div>


            <div class="form-group">
                <div class="col-md-4 col-md-offset-4">
                    <button type="submit" name="command" value="add_match" class="btn btn-primary">
                        <fmt:message key="add.match"/>
                    </button>
                    <button type="button" class="btn btn-primary "
                            onClick='location.href="${pageContext.request.contextPath}/controller?command=main"'>
                        <fmt:message key="match.back"/>
                    </button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<%@include file="footer.jsp" %>

<script src="${pageContext.request.contextPath}/js/parsley.min.js"></script>
<script src="${pageContext.request.contextPath}/js/i18n/ruu.js"></script>
<script src="${pageContext.request.contextPath}/js/i18n/enn.js"></script>
<script>
    $(document).ready(function () {
        $('#addTrack').parsley();
        window.Parsley.setLocale($("#locale").val());
    });
</script>
</body>
</html>

