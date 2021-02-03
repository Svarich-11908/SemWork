<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Log In</title>
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
            <a class="nav-link text-white" href="/login">Login</a>
        </li>
    </ul>

</nav>

<div class="vw-auto vh-100 bg-light py-2">
    <div class="pg-inline mx-3 mt-3 my-lg-0 p-3 bg-white border" style="border-radius:20px">
        <div class="container">
            <p class="text-danger">${fail}</p>
            <form name="form" action="/login" method="post">
                <div class="form-group">
                    <input type="hidden" name="action" id="action" value="" />
                    <div class="row justify-content-center pb-2">
                        <div class="col-md-auto">
                            <input type="email" class="form-control" name="email" placeholder="Email">
                        </div>
                    </div>
                    <div class="row justify-content-center pb-2">
                        <div class="col-md-auto">
                            <input type="password" class="form-control" name="password" placeholder="Password">
                        </div>
                    </div>
                    <div class="row justify-content-center pb-2">
                        <div class="col-md-auto">
                            <input type="submit" class="btn btn-outline-success" value="Log In" onClick="setType('login')">
                            <input type="submit" class="btn btn-outline-success" value="Register" onClick="setType('register')">
                            <script>
                                function setType(type)
                                {
                                    document.forms["form"].elements["action"].value = type;
                                }
                            </script>
                        </div>
                    </div>
                </div>
            </form>
            <div class="col-md-auto">
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${error.getMessage()}</li>
                    </c:forEach>
                </ul>
            </div>
    </div>
</div>
</div>
</body>
</html>
