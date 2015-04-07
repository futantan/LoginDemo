<%@ page import="com.demo.domain.User" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 15/4/7
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>


<table border="1">
    <tr>
        <td>用户名</td>
        <td>密码</td>
        <td>邮箱</td>
        <td>邮箱是否验证</td>
        <td>修改</td>
        <td>删除</td>
    </tr>
    <%--todo 权限--%>
    <%
        ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
        for (User user : users) {
            String editURL = "edit?userName=" + user.getUserName() + "&action=edit";
            String deleteURL = "edit?userName=" + user.getUserName() + "&action=delete";
    %>
    <tr>
        <td><%= user.getUserName() %>
        </td>
        <td><%= user.getPassword() %>
        </td>
        <td><%= user.getEmail() %>
        </td>
        <td><%= user.isConfirmed() ? "已验证" : "未验证" %>
        </td>
        <td><a href=<%= editURL %>>修改</a></td>
        <td><a href=<%= deleteURL %>>删除</a></td>
    </tr>
    <% } %>

</table>
</body>
</html>
