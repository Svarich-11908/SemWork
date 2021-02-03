<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>User Profile</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-dark">

    <ul class="navbar-nav mr-auto">
        <li class="nav-item">
            <a class="nav-link text-light font-weight-bold" href="/home">MovieDB</a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-light" href="/directors">Directors</a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-light" href="/movies">Movies</a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-light" href="/gallery">Gallery</a>
        </li>
    </ul>

    <ul class="navbar-nav my-2 my-lg-0">
        <li class="nav-item active">
            <a class="nav-link text-white" href="/user">User Profile</a>
        </li>
    </ul>

</nav>

<div class="vw-auto vh-100 bg-light py-2">
    <div class="pg-inline mx-3 mt-3 my-lg-0 p-3 bg-white border" style="border-radius:20px">
        <div class="container d-inline">
            <div class="row">
                <div class="col-md-5">
                    <h2>Favourite movies of ${email}:</h2>
                </div>
            </div>
            <ul>
                <c:forEach items="${movies}" var="movie">
                    <li><a href="${movie.getLink()}">${movie.getTitle()}</a></li>
                </c:forEach>
            </ul>
            <c:if test="${isProfile}">
                <div class="row">
                    <div class="col-md-auto">
                        <form action="/user" method="post">
                            <input type="submit" class="btn btn-outline-danger mt-5" value="Logout">
                        </form>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <a href="${link}">Direct link to your account</a>
                    </div>
                </div>
            </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
