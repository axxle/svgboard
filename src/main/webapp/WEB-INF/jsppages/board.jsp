<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Гостевая книга</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>

<header>
    <div class="container">
        <h1 id="pagetitle">Гостевая книга</h1>
    </div>
</header>

<div class="main">
    <div class="container">
        <div class="allFeedback">
            <c:if test="${not empty commentsList}">
                    <c:forEach var="comment" items="${commentsList}">
                        <div class="feedback" id = "feedback_${comment.id}">
                                <h2>${comment.name}</h2>
                                <p>${comment.text}</p>
                        </div>
                    </c:forEach>
            </c:if>
        </div>

        <div>
            <form name="addComment" method="post" action="/guestbook/add">
                <p>Ваше имя:<br>
                    <input class="name-input" type="text" size="40" required name="name" id="name">
                </p>
                <p>Отзыв:<br>
                    <textarea class="comment-area" name="comment" cols="40" rows="3" required id="comment"></textarea></p>
                <p id="submitarea">
                    <input class="bttn" type="submit" value="Оставить запись">
                </p>
            </form>
        </div>

    </div>
</div>

<footer><div class="container"></div></footer>

</body>
</html>