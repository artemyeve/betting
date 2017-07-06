<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="content"/>
<html>
<head>
    <link rel="icon" href="${pageContext.request.contextPath}/images/football.png">
    <title><fmt:message key="match.bet"/></title>
</head>
<body>
<c:set var="page" value="path.page.bet" scope="session"/>
<%@ include file="../menu.jsp"%>
<form method="post" class="form-horizontal" style="margin-top: 100px" id="bet" name="bet" action="${pageContext.request.contextPath}/controller">
    <c:if test="${not empty error}">
        <div class="alert alert-danger"style="margin-top: 90px">${error}</div>
    </c:if>
    <div class="form-group">
        <label for="bet_amount" class="col-sm-3 control-label"><fmt:message key="bet.amount"/> *</label>
        <div class="col-sm-6">
            <input type="number" class="form-control" name="bet_amount" id="bet_amount" data-parsley-required data-parsley-type="number"
                   step="1.00" data-parsley-min="1.00" data-parsley-max="1000.00"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-9 m-t-15">
            <button type="submit" name="command" value="bet" class="btn btn-primary"><fmt:message key="match.bet"/>
            </button>
            <button type="button" class="btn btn-primary " onClick='location.href="matches.jsp"'><fmt:message key="form.back"/>
            </button>
        </div>
    </div>
</form>
<div class="form-group">
    <%@include file="../footer.jsp"%>
    <script src="${pageContext.request.contextPath}/js/parsley.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/i18n/ruu.js"></script>
    <script src="${pageContext.request.contextPath}/js/i18n/enn.js"></script>
    <script>
        $(document).ready(function(){
            $('#add_funds').parsley();
            window.Parsley.setLocale($("#locale").val());
        });
    </script>
</body>
</html>

