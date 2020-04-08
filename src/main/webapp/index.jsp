<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored = "false" %>
<html lang="ru">
<head>
    <title>Вход</title>
    <style>
        <jsp:directive.include file="WEB-INF/styles/dashboard.css"/>
        <jsp:directive.include file="WEB-INF/bootstrap-4.3.1-dist/css/bootstrap.min.css"/>
    </style>
</head>
<body>
<c:redirect url="/fs/login">redirect</c:redirect>
<h1>redirect</h1>
<h1>hello</h1>
</body>
</html>
