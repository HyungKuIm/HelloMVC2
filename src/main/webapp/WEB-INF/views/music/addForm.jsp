<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 25. 3. 10.
  Time: 오전 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>음악 추가 폼</title>
</head>
<body>
    <div class="container">
        <h1>음악 추가 폼입니다.</h1>

        <form action="<c:url value="/music.do?operate=addMusic"/>" method="post">
            <div class="mb-3">
                <label for="title" class="form-label">Title</label>
                <input type="text" class="form-control" id="title" name="title" aria-describedby="titleHelp">
                <div id="titleHelp" class="form-text">음악 제목을 입력하세요.</div>
            </div>
            <div class="mb-3">
                <label for="artist" class="form-label">Artist</label>
                <input type="text" class="form-control" id="artist" name="artist" aria-describedby="artistHelp">
                <div id="artistHelp" class="form-text">아티스트(가수/연주자)를 입력하세요.</div>
            </div>
            <div class="mb-3">
                <label for="album" class="form-label">Album</label>
                <input type="text" class="form-control" id="album" name="album" aria-describedby="albumHelp">
                <div id="albumHelp" class="form-text">앨범 제목을 입력하세요.</div>
            </div>
            <div class="mb-3">
                <label for="genre" class="form-label">Genre</label>
                <input type="text" class="form-control" id="genre" name="genre" aria-describedby="genreHelp">
                <div id="genreHelp" class="form-text">음악 장르를 입력하세요.</div>
            </div>

            <button type="submit" class="btn btn-primary">추가</button>
        </form>
    </div>
</body>
</html>
