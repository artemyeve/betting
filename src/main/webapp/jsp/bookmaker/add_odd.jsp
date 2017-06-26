<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="content"/>
<html>
<head>
    <link rel="icon" href="${pageContext.request.contextPath}/images/football.png">
    <title><fmt:message key="add.odd"/></title>
</head>
<body>
<c:set var="page" value="path.page.add.odd" scope="session"/>
<%@ include file="../menu.jsp" %>
<div class="container" style="margin-top: 90px">
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>
    <form class="form-horizontal" method="post" id="addOdd" name="addOdd" enctype="multipart/form-data"
          action="${pageContext.request.contextPath}/controller">
        <fieldset>
            <legend><fmt:message key="add.odd.title"/></legend>

            <div class="form-group">
                <label class="col-md-4 control-label" for="tournament"><fmt:message key="add.betline.outcome"/> *</label>
                <div class="col-md-6">
                    <input id="tournament" name="tournament" type="text" class="form-control input-md"
                           data-parsley-required data-parsley-length="[1,100]">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="homeTeamGoals"><fmt:message key="add.betline.odd"/> *</label>
                <div class="col-md-2">
                    <input id="homeTeamGoals" name="homeTeamGoals" type="number" class="form-control input-md"
                           data-parsley-required data-parsley-type="number">
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-4 col-md-offset-4">
                    <button type="submit" name="command" value="add_odd" class="btn btn-primary">
                        <fmt:message key="add.odd"/>
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
<%@include file="../footer.jsp" %>

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
