<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <!-- bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>

    <style>

        .content{
            width: 80%;
            margin: auto;
        }

        .title{
            text-align: center;
        }

    </style>

</head>
<body>

<div class="content">

    <div class="title">
        <h3>게시판</h3>
    </div>

    <button type="button" class="btn btn-primary float-right">글쓰기</button>

    <table id="boardList" class="table table-hover">
        <thead>
        <tr>
            <th>글번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ list }" var="b">
            <tr>
                <td>${ b.boardNo }</td>
                <td>${ b.boardTitle }</td>
                <td>${ b.boardWriter }</td>
                <td>${ b.createDate }</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>

</div>

</body>
</html>