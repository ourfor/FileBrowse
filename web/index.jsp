<%--
  Created by IntelliJ IDEA.
  User: sagit
  Date: 2019-04-21
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@
        page import="RequestPath.RequestDirectory"
             import="ListFile.Files"
%>
<%@ page import="RequestPath.FileList" %>
<%!
    private RequestDirectory reqDir = new RequestDirectory();
    private String dir = "";
    private FileList fl = new FileList();
%>
<%
    dir = reqDir.getDirectory(request);
    request.setAttribute("Title",dir);
    fl.getFileLists(dir);

%>
<html>
  <head>
    <title>${requestScope.Title}</title>
  </head>
  <body>
  </body>
</html>
