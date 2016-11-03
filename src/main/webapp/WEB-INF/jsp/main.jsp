<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Sorravit
  Date: 3/11/2559
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>webboard</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="list-group">
    <a href="#" class="list-group-item">Display posting number, writer's name, title, date, no. of views</a>
    <a href="#" class="list-group-item active"><c:out value="${user.id}"/></a>
    <a href="#" class="list-group-item"><c:out value="${user.email}"/></a>
    <a href="#" class="list-group-item">Die With Your Boots On</a>
    <a href="#" class="list-group-item">Fairies Wear Boots</a>
</div>
<div class="container">
    <h2>Pagination</h2>
    <p>The .pagination class provides pagination links:</p>
    <ul class="pagination">
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">5</a></li>
    </ul>
</div>
</body>
</html>
