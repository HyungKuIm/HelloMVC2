<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate" %>
<%
    LocalDate now = LocalDate.now();
    LocalDate tomorrow =  now.plusDays(1);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1>MVC 홈페이지에 오신 것을 환영합니다.</h1>
<h2>
    오늘은 <%= now %> 입니다.<br/>
    내일은 <%= tomorrow %>입니다.
</h2>
<ul>
    <li><a href="<c:url value="/hello.do"/>">주인장 소개</a></li>
    <li><a href="<c:url value="/music.do"/>">음악듣기</a></li>
</ul>
</body>
</html>