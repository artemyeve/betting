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
    <title><fmt:message key="betline.more"/></title>
</head>
<body>
<c:set var="page" value="path.page.betline_edit" scope="session"/>
<%@ include file="../menu.jsp" %>
<div class="container" style="margin-top: 90px">
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <form method="post" class="form-horizontal" id="edit" name="edit"
          action="${pageContext.request.contextPath}/controller">
        <div class="form-group">
            <label class="col-md-4 control-label" for="outcome"><fmt:message key="add.betline.outcome"/> *</label>
            <div class="col-md-6">
                <input id="outcome" name="outcome" type="text" class="form-control input-md"
                       data-parsley-required data-parsley-length="[1,100]" value="${betLine.outcome}">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label" for="odd"><fmt:message key="add.betline.odd"/> *</label>
            <div class="col-md-6">
                <input id="odd" name="odd" type="number" class="form-control input-md"
                       data-parsley-required data-parsley-type="number" value="${betLine.odd}">
            </div>
        </div>
              <div class="form-group">
            <div class="col-md-4 col-md-offset-4">
                <button type="submit" name="command" value="edit" class="btn btn-primary">
                    <fmt:message key="betline.edit"/>
                </button>
                <button type="button" class="btn btn-primary "
                        onClick='location.href="../menu.jsp"'>
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
