<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>

    <!-- bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>

    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

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
            <%--font-family: 'Jua', sans-serif;--%>

        }

        #writeBtn{
            margin-bottom: 10px;
        }

        .btnArea{
            margin: 10px;
            text-align: center;
        }

    </style>

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">practice</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link" href="listView.bo">게시판</a>
            <a class="nav-item nav-link" href="scrapingView.htx">사업자상태조회</a>
            <a class="nav-item nav-link" href="/NXView">사업자상태조회</a>
        </div>
    </div>

    <div class="userInfo" >
        <div class="navbar-nav">
            <a class="nav-item nav-link" id="userId"> </a>
            <a class="nav-item nav-link" id="login" href="/loginForm">login</a>
            <a class="nav-item nav-link" id="logout" href="/logout">logout</a>
        </div>
    </div>
</nav>

<script>

    $(function (){

        getUserId();

    });

    function getUserId(){

        $.ajax({
            url:"/selectUserId",
            type:"GET",
            success: function (userId){

                if(userId != 'anonymousUser'){
                    $('#userId').text(userId);
                    $('#boardWriter').val(userId);
                    $('#login').hide();
                    $('#logout').show();
                }else{
                    $('#logout').hide();
                    $('#login').show();
                }

                var writer = $('#boardDWriter').val();
                if(userId != writer){
                    console.log("id" + userId + "writer" + writer);
                    $('#updateBtn').attr('disabled', true);
                    $('#deleteBtn').attr('disabled', true);
                }
            },
            error: function (){
                console.log("ajax 통신 실패");
            }
        });

    }

</script>
</body>
</html>