<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>HtxScraping</title>

    <style>

        #inputNo{
            width: 200px;

        }

        .search{
            display: flex;
            background-color: rgb(250, 248, 248);
            margin: 30px 0px;
            padding: 30px;
        }

        .resultContent{
            background-color: rgb(250, 248, 248);
            padding: 30px;
        }

        .htxText{
            font-weight: bolder;
            margin-right: 50px;
            margin-top: auto;
        }

    </style>

</head>
<body>

<jsp:include page="../common/menubar.jsp" />

<div class="content">

    <div class="title">
        <h3>사업자상태조회</h3>
    </div>

    <div class="search">
        <label for="inputNo" class="htxText">사업자등록번호</label>
        <input type="text" class="form-control" id="inputNo">
        <button type="button" class="btn btn-primary" id="inputBtn">조회하기</button>
    </div>

    <div class="resultContent">
        <p class="htxText">사업자 등록 상태</p>
        <p id="result"></p>
    </div>

</div>

<script>
    $(function (){

        $('#inputBtn').on('click', function (){

            var input = $('#inputNo').val();
            var inputNo = input.replace(/[^0-9]/g,'');
            console.log(inputNo);

            $.ajax({
                url: "scraping.htx",
                data:{inputNo : inputNo},
                type: "GET",
                success: function (obj){
                    if(obj.errYn = 'Y'){
                        $('#result').text(obj.result);
                    }else{
                        alert(obj.errMsg);
                    }
                },
                error: function (){
                    console.log("ajax 통신 실패");
                }
            });
        });
    });

</script>

</body>
</html>