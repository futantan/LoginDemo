<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>帐号确认</title>
</head>
<body>
<h1>帐号确认</h1>
<%
    String userName = request.getParameter("userName");
    String email = request.getParameter("email");
    String sub = email.substring(email.indexOf('@') + 1);
    if (sub.equals("gmail")) {
        sub = "google";
    } // 特殊处理
    String url = "http://mail." + sub;
%>
${userName}您的邮箱<a target="_blank" href="<%=url %>">${email}</a>还没确认，您必须尽快进行帐户激活（登录该邮箱，并按照帐号激活通知的指示操作）。否则您的帐号将很快被删除！<br/>
您还没有收到激活信？点击<a href="RegisterServlet.do?userName=<%=userName%>&email=<%=email%>">这里</a>重新发送激活邮件。
</body>
</html>