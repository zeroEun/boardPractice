<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>BoardDetail</title>

    <!-- bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>

    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Dongle&display=swap" rel="stylesheet">

    <!-- CSS
    <link rel="stylesheet" href="/static/board.css">-->

    <style>

        .content{
            width: 70%;
            margin: auto;
        }

        .title{
            text-align: center;
            padding: 30px;
        }

        .title h3{
            font-family: 'Dongle', sans-serif;
            font-size: 70px;
        }

        .btnArea{
            margin: 10px;
            text-align: center;
        }

    </style>


</head>
<body>

<c:if test="${not empty msg}">
    <script>
        alert("${msg}");
    </script>
    <c:remove var="msg" scope="session"/>
</c:if>

<div class="content">

    <div class="title">
        <h3>게시판</h3>
    </div>

    <hr>

    <form action="update.bo" method="post">

        <div>글 번호 : ${b.boardNo}</div>
        <input type="hidden" name="boardNo" id="boardNo" value="${b.boardNo}">
        <div>최종 수정일 : ${b.createDate}</div>
        <br>

        <label for="boardTitle">제목</label>
        <input type="text" class="form-control" name="boardTitle" id="boardTitle" value="${b.boardTitle}" required>
        <label for="boardWriter">작성자</label>
        <input type="text" class="form-control" name="boardWriter" id="boardWriter"  value="${b.boardWriter}" readonly>
        <label for="boardContent">내용</label>
        <textarea class="form-control" name="boardContent" id="boardContent" rows="5" required>${b.boardContent}</textarea>

        <div class="btnArea">
            <button type="button" class="btn btn-secondary" id="cancelBtn">취소</button>
            <button type="submit" class="btn btn-primary">수정</button>
            <button type="button" class="btn btn-danger" id="deleteBtn">삭제</button>
        </div>

    </form>

</div>

<script>

    $(function(){
        $('#cancelBtn').on('click', function(){
            location.href="/";
        });

        $('#deleteBtn').on('click', function(){

            var result = confirm("정말 삭제하시겠습니까?");
            var bNo = $('#boardNo').val();

            if(result){
                $.ajax({
                   url: "delete.bo",
                   data : {boardNo:bNo},
                   type : "GET",
                    success : function (result) {
                        if(result){
                            location.href="/";
                            alert("게시글이 삭제되었습니다.");
                        }
                    },
                    error : function (){
                       console.log("ajax 통신 실패");
                    }
                });
            }

        });
    });

</script>

</body>
</html>