<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Gallery</title>
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
        <li class="nav-item active">
            <a class="nav-link text-white" href="/gallery">Gallery</a>
        </li>
    </ul>

    <ul class="navbar-nav my-2 my-lg-0">
        <li class="nav-item">
            <a class="nav-link text-light" href="/user">User Profile</a>
        </li>
    </ul>

</nav>

<div class="vw-auto min-vh-100 bg-light py-2">
    <div class="pg-inline mx-3 mt-3 my-lg-0 p-3 bg-white border" style="border-radius:20px">

        <form action="/gallery" method="post" enctype="multipart/form-data">
            <div class="row">
                <div class="col-md-3 mb-3">
                    <div class="custom-file">
                        <input type="file" class="custom-file-input" name="file" id="upload">
                        <label class="custom-file-label" for="upload">Choose picture...</label>
                    </div>
                </div>
                <div class="col-md-auto">
                    <input type="submit" class="btn btn-outline-secondary" value="Upload File">
                </div>
            </div>
        </form>

        <c:forEach items="${photos}" var="photo">
            <img src="${photo}" alt="Picture" style="border: 1px solid #ddd; border-radius: 4px; padding: 5px; max-height: 500px; max-width: 500px;">
        </c:forEach>
    </div>
</div>
</body>
</html>

