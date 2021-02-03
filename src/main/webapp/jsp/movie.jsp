<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Movie</title>
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
        <li class="nav-item">
            <a class="nav-link text-light" href="/user">User Profile</a>
        </li>
    </ul>

</nav>

<div class="vw-auto vh-100 bg-light py-2">
    <div class="pg-inline mx-3 mt-3 my-lg-0 p-3 bg-white border" style="border-radius:20px">
        <div class="container-md-auto px-1">
            <div class="row mb-2 justify-content-between">
                <div class="col-md-10">
                    <h2>${movie.getTitle()}</h2>
                </div>
                <div class="col-md-1 align-self-center ml-5 pr-1">
                    <h4 class="text-secondary text-right">${favNumber}</h4>
                </div>
                <div class="col-md-auto px-1">
                    <form action="/movie" method="post" class="my-1">
                        <input type="hidden" name="movieId" value="${movie.getId()}" />
                        <c:choose>
                            <c:when test="${!isFavourite}">
                                <input type="hidden" name="action" value="fav" />
                                <input id="favourite" type="image" src="/icons/fav_empty.png" alt="submit" width="48" height="48">
                            </c:when>
                            <c:otherwise>
                                <input type="hidden" name="action" value="unfav" />
                                <input id="favourite" type="image" src="/icons/fav_full.png" alt="submit" width="48" height="48">
                            </c:otherwise>
                        </c:choose>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col-md-auto">
                    <img src="${movie.getPicturePath()}" alt="${movie.getTitle()}" style="height: 268px; width: 182px;">
                </div>
                <div class="col">
                    <div class="row">
                        <p><span class="text-secondary">Year: </span>${movie.getYear()}</p>
                    </div>
                    <div class="row">
                        <p><span class="text-secondary">Length: </span>${hours}h ${minutes}m</p>
                    </div>
                    <div class="row">
                        <p><span class="text-secondary">Director: </span><a href="${director.getLink()}">${director.getName()}</a></p>
                    </div>
                    <div class="row">
                        <p class="text-secondary">Description:</p>
                    </div>
                    <div class="row">
                        <p>${movie.getDescription()}</p>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
