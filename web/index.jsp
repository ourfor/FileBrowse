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
    <link rel="stylesheet" type="text/css" href="lib/css/font-awesome.min.css" />
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
  </head>
  <body>
  <c:out value = "${requestScope.Title}" default="目录浏览" />
  <br>
  <table>
  <c:forEach var="item" items="${requestScope.FileList}" varStatus="status">
    <tr>
      <td>
        <i class="fa fa-${item.type} fa-lg"></i>
        <a href="${requestScope.server}${requestScope.project}${item.path}">${item.name}</a>
      </td>
      <td>${item.change}</td>
      <td>${item.size}</td>
    </tr>
  </c:forEach>
  </table>
  </body>
</html>
