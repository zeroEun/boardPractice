package com.example.boardPractice.htxScraping.service;

import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.USER_AGENT;

@Service
public class HtxScrapingService {

    public Map htxScraping(String inputNo) throws IOException, ParserConfigurationException, SAXException {

        String u = "https://teht.hometax.go.kr/wqAction.do?actionId=ATTABZAA001R08&screenId=UTEABAAA13&popupYn=false&realScreenId=";
        String data = "<map id='ATTABZAA001R08'><pubcUserNo/><mobYn>N</mobYn><inqrTrgtClCd>1</inqrTrgtClCd><txprDscmNo>" + inputNo + "</txprDscmNo><dongCode>45</dongCode><psbSearch>Y</psbSearch><map id='userReqInfoVO'/></map><nts<nts>nts>652DGsSpYtF9Qw8ROVR5H2ckndNxRezd0lBVzh7P53T5A54";
        // payload : 전송되는 데이터 body에 담기는 data

        //url 객체 생성, url 클래스 : 자원을 요청할 주소를 나타냄
        URL url = new URL(u);

        //url에 연결하는 HTTP 연결 객체 생성
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        //요청메소드 설정
        connection.setRequestMethod("POST");

        //요청 헤더 설정
        //HTTP 요청을 하는 사용자의 애플리케이션 타입, 운영체제, 소프트웨어 버전 등을 식별할 수 있는 User-Agent 헤더 설정 시
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setRequestProperty("Content-Type","application/xml; charset=UTF-8");

        //true : OutputStream으로 데이터 전송, 디폴트 false
        connection.setDoOutput(true);
        connection.setDoInput(true);

        //POST 요청 시 데이터 넘겨주기
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream()); //연결에 사용할 outputStream 객체 얻어오기
        outputStream.writeBytes(data); //전송할 데이터가 문자열일 경우
        outputStream.flush();
        outputStream.close();

        //응답코드
        int responseCode = connection.getResponseCode();

        //응답데이터
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer stringBuffer = new StringBuffer();
        String inputLine = "";

        while((inputLine = bufferedReader.readLine()) != null){
            stringBuffer.append(inputLine);
        }
        bufferedReader.close();
        String response = stringBuffer.toString();
        System.out.println(response);

        //response 데이터에서 결과 메시지만 자르기
        String[] resArr = response.split(">");
        String result = "";

        for (String res: resArr) {
            if(res.contains("/trtCntn")){
                result = res;
            }
        }
        String[] rArr =  result.split("<");
        result = rArr[0];

        Map resultMap = new HashMap();

        if(responseCode != 200){
            resultMap.put("errYn", "Y");
            resultMap.put("errMsg", responseCode);
        }else{
            resultMap.put("errYn", "N");
            resultMap.put("errMsg", "");
            resultMap.put("result", result);
        }

        return resultMap;
    }
}
