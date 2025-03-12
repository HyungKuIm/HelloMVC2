<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 25. 3. 11.
  Time: 오전 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- FontAwesome CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="<c:url value="/style.css"/>">
    <title>Music Show</title>
</head>
<body>
    <div class="contents">
        <div class="container">
            <article>
                <h1>${music.title}</h1>

                <time datetime="2025-03-12">
                    <i class="far fa-clock"></i>
                    <javatime:format value="${music.insert_dt}" pattern="yyyy-MM-dd" />

                </time>
                <c:if test="${not empty music.url}">
                    <iframe width="1269" height="714" src="https://www.youtube.com/embed/${music.url}" title="${music.title}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
                </c:if>
                <p>artist: ${music.artist}</p>
                <p>album: ${music.album}</p>
                <p>genre: ${music.genre}</p>
            </article>

        </div>
    </div>

</body>
</html>
