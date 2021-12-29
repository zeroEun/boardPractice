<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>login</title>

    <style>

        .jsonText{
            height: 70vh;
        }
        .jsonTextArea{
            margin: 10px 10px 0px 0px;
        }

    </style>


</head>
<body>

<jsp:include page="../common/menubar.jsp"/>

<div class="content">

    <div class="title">
        <h3>NX Local Service Practice</h3>
    </div>

    <button type="button" class="btn btn-primary" id="setupBtn">설치확인</button>
    <button type="button" class="btn btn-primary" id="executeBtn">수집</button>
    <button type="button" class="btn btn-primary" id="certSelectBtn">인증서목록뷰</button>
    <button type="button" class="btn btn-primary" id="certSelectBtn">인증서목록뷰</button>

    <div class="d-flex jsonText">
        <textarea class="form-control jsonTextArea" id="inJson" rows="20"></textarea>
        <textarea class="form-control jsonTextArea" id="outJson" rows="20"></textarea>
    </div>


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

        $('#certSelectBtn').on('click', function (){

            loadDoc('certSelect', setCertSelect());

        });

    })

    function setCertSelect(){

        //'{"certImageUrl": "", "nxKeypad": ""[, "enc":"1"]}'
        return '{"certImageUrl": "", "nxKeypad": ""}';
    }

    function certEncoding(data){
        let inJson = new Object();

        inJson.orgCd = 'common';
        inJson.svcCd = 'getCertInfo';
        inJson.appCd = '';
        inJson.signCert = data.file1;
        inJson.signPri = data.file2;
        inJson.signPw = data.cert_pw;

        loadDoc('execute', JSON.stringify(inJson));
    }

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

                <%--
                if(s_op == 'certSelect'){
                    certEncoding(data);
                }--%>
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