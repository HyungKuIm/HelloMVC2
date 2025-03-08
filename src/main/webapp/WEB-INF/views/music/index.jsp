<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 25. 3. 7.
  Time: 오후 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Music List</title>
</head>
<body>
    <table>
        <tr>
            <th>id</th><th>title</th><th>artist</th><th>album</th><th>genre</th>
        </tr>
        <c:forEach var="m" items="${musicList}">
        <tr>
            <td>${m.id}</td>
            <td>${m.title}</td>
            <td>${m.artist}</td>
            <td>${m.album}</td>
            <td>${m.genre}</td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>
