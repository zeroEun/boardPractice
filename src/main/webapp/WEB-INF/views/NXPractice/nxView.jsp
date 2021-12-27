<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>login</title>

</head>
<body>

<jsp:include page="../common/menubar.jsp"/>

<div class="content">

    <div class="title">
        <h3>NX Local Service Practice</h3>
    </div>

    <button type="button" class="btn btn-primary" id="setupBtn">설치확인</button>
    <button type="button" class="btn btn-primary" id="executeBtn">수집</button>

    <textarea class="form-control" id="inJson" rows="20">

    </textarea>

    <textarea class="form-control" id="outJson" rows="20">

    </textarea>

</div>

<script>

    $(function () {

        $('#executeBtn').on('click', function () {

            var inJson = $('#inJson').val();
            console.log(inJson);
            loadDoc('execute', inJson);

        });

        $('#setupBtn').on('click', function () {

            loadDoc('setup', null);

        });


    })

    function loadDoc(s_op, s_inJson) {
        //crossDomain : true, crossOrigin : true 다른 도메인에서 데이터 가져올 때, htts에서 https로 데이터 가져올 때 CORS에러 방지

        $.ajax({
            url: "https://127.0.0.1:16566/?op=" + s_op,
            type: "POST",
            data: s_inJson,
            dataType: "json",
            contentType: "application/json; charset=UTF-8",
            crossDomain: true,
            crossOrigin: true,
            success: function (data) {
                console.log(data)
                $('#outJson').val(JSON.stringify(data, null, 5));
            },
            error: function (xhr, status, error) {
                console.log(status + error);

                if ('setup' == s_op) {
                    alert('프로그램 설치 필요');
                    let nxExeDownloadUrl = './ExAdapter_Web_Setup_20210225.exe';

                    if (!$('#ifrFile').length) {
                        $('<iframe />');  // Create an iframe element
                        $('<iframe />', {
                            name: 'ifrFile',
                            id: 'ifrFile',
                            src: nxExeDownloadUrl
                        }).appendTo('body');
                    }

                } else {
                    console.log(status + error);
                }

            }
        });

    }

</script>

</body>
</html>