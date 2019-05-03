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
<!DOCTYPE HTML>
<html>
  <head>
    <title>${requestScope.Title}</title>
  </head>
  <body>
  <c:out value = "${requestScope.Title}" default="目录浏览" />
  </body>
</html>
