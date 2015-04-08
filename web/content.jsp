<%@ page import="com.demo.dao.UserDao" %>
<%@ page import="com.demo.dao.impl.UserDaoImpl" %>
<%@ page import="com.demo.domain.User" %>
<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 15/4/8
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
    String userType = (String) session.getAttribute("userType");
    User userInDB = (User) session.getAttribute("user");
    if (userType == null || !userType.equals("normal") || userInDB == null) {
        response.sendRedirect("/index.html");
        return;
    } else {
%>
<p>you can see this because you have logged in.</p>

<p><a href="${pageContext.request.contextPath}/logout">退出登录</a></p>

<%
    if (!userInDB.isConfirmed()) {
        String confirmURL = "mail?userName=" + userInDB.getUserName() + "&email=" + userInDB.getEmail();
%>
<p><a href=<%=confirmURL%>>点击确认邮箱</a></p>

<% } %>
<% } %>
</body>
</html>
