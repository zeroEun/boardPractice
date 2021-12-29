<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>login</title>

    <style>
        .loginForm{
            border: 1px solid lightgrey;
            width: 450px;
            padding: 30px;
            margin: auto;
            position: relative;
            top: 50px;
        }

        #loginBtn{
            margin-top: 10px;
        }

    </style>

</head>
<body>

<jsp:include page="../common/menubar.jsp" />

<div class="content">

    <div class="loginForm">
        <h2>Login</h2>

        <form method="post" action="/">

            <div class="container">
                <label for="userId"><b>UserID</b></label>
                <input type="text" class="form-control" placeholder="Enter UserID" name="userId" id="userId" required>

                <label for="password"><b>Password</b></label>
                <input type="password" class="form-control" placeholder="Enter Password" name="password" id="password" required>

                <button type="submit" class="btn btn-primary" id="loginBtn">Login</button>
            </div>

        </form>
    </div>

</div>

</body>
</html>