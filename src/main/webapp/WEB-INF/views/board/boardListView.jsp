<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>BoardList</title>

    <!-- bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>

    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Dongle&display=swap" rel="stylesheet">

    <!-- CSS
    <link rel="stylesheet" type="text/css" href="/static/board.css">-->

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

        #writeBtn{
            margin-bottom: 10px;
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

    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary float-right" id="writeBtn" data-toggle="modal" data-target="#exampleModalCenter">글쓰기</button>

    <!-- Modal -->
    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">게시글 작성</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <form action="insert.bo" method="post">

                    <div class="modal-body">

                        <label for="boardTitle">제목</label>
                        <input type="text" class="form-control" name="boardTitle" id="boardTitle" required>
                        <label for="boardWriter">작성자</label>
                        <input type="text" class="form-control" name="boardWriter" id="boardWriter" required>
                        <label for="boardContent">내용</label>
                        <textarea class="form-control" name="boardContent" id="boardContent" rows="5" required></textarea>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                        <button type="submit" class="btn btn-primary">작성</button>
                    </div>

                </form>

            </div>
        </div>
    </div>


    <table id="boardList" class="table table-hover">
        <thead class="thead-light">
        <tr>
            <th>글번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>최종 수정일</th>
        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
    <br>

</div>

<script>

    $(function (){
        selectList();
    })

    function selectList() {
        $.ajax({
            url: "selectList.bo",
            success: function (list){

                var value = "";
                $.each(list, function (i, obj) {
                    value += '<tr>' +
                                    '<td>' + obj.boardNo + '</td>' +
                                    '<td><a href="boardDetail.bo?boardNo=' + obj.boardNo + '">' + obj.boardTitle + '</a></td>' +
                                    '<td>' + obj.boardWriter + '</td>' +
                                    '<td>' + obj.createDate + '</td>';
                })


                $('#boardList tbody').html(value);

            },
            error: function (){
                console.log("ajax 통신 실패");
            }
        });
    }

</script>


</body>
</html>