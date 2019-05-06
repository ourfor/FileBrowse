<%--
  Created by IntelliJ IDEA.
  User: sagit
  Date: 2019-04-21
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="FileDetails.Details,FileDetails.FileList" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>${requestScope.Title}</title>
<%--    获取部署的应用名，如果没有使用代理的话  ${pageContext.request.contextPath}--%>
    <c:set var="proxy" value="true" />
    <c:set var="RootPath" value="${pageContext.request.contextPath}" />
    <c:if test="${proxy}"><c:set var="RootPath" value="" /></c:if>

    <link rel="shortcut icon" href="${RootPath}/lib/image/favicon.png" />
    <link href="${RootPath}/lib/css/ionicons.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${RootPath}/lib/css/index.css" />
    <meta name='viewport' content='width=device-width,initial-scale=1' />
  </head>
  <body>
  <h4><c:out value = "${requestScope.Title}" default="目录浏览" /></h4>
  <section class="container">
  <table>
    <tr>
      <td>
        <i class="icon ion-ios-arrow-dropup"></i>
        <a href="${requestScope.server}${requestScope.dir}" >..</a>
      </td>
      <td></td>
      <td></td>
    </tr>
  <c:forEach var="item" items="${requestScope.FileList}" varStatus="status">
    <tr>
      <td>
        <i class="icon ion-ios-${item.type}"></i>
        <a href="${requestScope.server}${item.path}">${item.name}</a>
      </td>
      <td class="change">${item.change}</td>
      <td class="size">${item.size}</td>
    </tr>
  </c:forEach>
  </table>
  </section>
  </body>
</html>
