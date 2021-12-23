<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>HtxScraping</title>

</head>
<body>

<jsp:include page="../common/menubar.jsp" />

<div class="content">

    <h2>Login Form</h2>

    <form method="post" action="/authenticate">

        <div class="container">
            <label for="userId"><b>Username</b></label>
            <input type="text" class="form-control" placeholder="Enter Username" name="userId" id="userId" required>

            <label for="password"><b>Password</b></label>
            <input type="password" class="form-control" placeholder="Enter Password" name="password" id="password" required>

            <button type="submit" class="btn btn-primary">Login</button>
        </div>

    </form>

</div>

</body>
</html>