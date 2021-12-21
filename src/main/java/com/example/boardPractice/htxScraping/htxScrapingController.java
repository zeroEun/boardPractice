package com.example.boardPractice.htxScraping;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static org.springframework.http.HttpHeaders.USER_AGENT;

@Controller
public class htxScrapingController {

    @ResponseBody
    @RequestMapping("")
    public void htxScrapingResult() throws IOException {

        //스크래핑할 url
        String url ="https://teht.hometax.go.kr/websquare/websquare.html?w2xPath=/ui/ab/a/a/UTEABAAA13.xml";
        //String url = "https://www.w3schools.com";

        //입력한 url 주소로 연결
        Connection conn = Jsoup.connect(url)
                .userAgent(" Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36")
                .header("Content-Type", "application/json;charset=UTF-8")
                .userAgent(USER_AGENT)
                .method(Connection.Method.GET)
                .ignoreContentType(true);

        //페이지 전체 소스 저장
        Document doc = conn.get();

        System.out.println(doc);


        //select로 접근한 요소
        /*
        Elements ele = doc.select("div.w2group h2");

        for(Element element: ele) {
            System.out.println("element" + element.text());
        }*/

        /*
        String url = "https://www.w3schools.com";
        Elements ele = doc.select("div.w3-content h1");
        for(Element element: ele) { // -- 3. Elemntes 길이만큼 반복한다.
            System.out.println(element.text()); // -- 4. 원하는 요소가 출력된다.
        }*/

    }

    @ResponseBody
    @RequestMapping("scraping.htx")
    public void htx() throws IOException {

        //https://limdevbasic.tistory.com/14

        //https://teht.hometax.go.kr/wqAction.do?actionId=ATTABZAA001R08&screenId=UTEABAAA13&popupYn=false&realScreenId=
        String u = "https://teht.hometax.go.kr/wqAction.do?actionId=ATTABZAA001R08&screenId=UTEABAAA13&popupYn=false&realScreenId=";

        //url 객체 생성, url 클래스 : 자원을 요청할 주소를 나타냄
        URL url = new URL(u);

        //url에 연결하는 HTTP 연결 객체 생성
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        //요청메소드 설정
        connection.setRequestMethod("POST");

        //요청 헤더 설정
        //HTTP 요청을 하는 사용자의 애플리케이션 타입, 운영체제, 소프트웨어 버전 등을 식별할 수 있는 User-Agent 헤더 설정 시
        connection.setRequestProperty("User-Agent", USER_AGENT);

        //true : OutputStream으로 데이터 전송, 디폴트 false
        connection.setDoOutput(true);

        //POST 요청 시 데이터 넘겨주기
        //3908800371
        String data = "";

        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream()); //연결에 사용할 outputStream 객체 얻어오기
        outputStream.writeBytes(data); //전송할 데이터가 문자열일 경우
        outputStream.flush();
        outputStream.close();

        //응답코드
        int responseCode = connection.getResponseCode();
        System.out.println(responseCode);

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

        //http
        // payload : 전송되는 데이터 body에 담기는 data
        //query String : actionId=ATTABZAA001R08&screenId=UTEABAAA13&popupYn=false&realScreenId=
        //<map id="ATTABZAA001R08"><pubcUserNo/><mobYn>N</mobYn><inqrTrgtClCd>1</inqrTrgtClCd><txprDscmNo>1234567894</txprDscmNo><dongCode>45</dongCode><psbSearch>Y</psbSearch><map id="userReqInfoVO"/></map><nts<nts>nts>652DGsSpYtF9Qw8ROVR5H2ckndNxRezd0lBVzh7P53T5A54


        //header 설정
        //Content-Type: application/xml; charset=UTF-8

    }

}
