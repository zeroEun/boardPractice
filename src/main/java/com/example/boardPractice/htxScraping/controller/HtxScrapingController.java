package com.example.boardPractice.htxScraping.controller;

import com.example.boardPractice.htxScraping.service.HtxScrapingService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.USER_AGENT;

@Controller
public class HtxScrapingController {

    @Autowired
    private HtxScrapingService htxScrapingService;


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
        //Document doc = conn.get();

        //System.out.println(doc);


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

    @GetMapping("/scrapingView.htx")
    public String htxScrapingView(){

        return "htxScraping/htxScrapingView";
    }

    @ResponseBody
    @GetMapping("/scraping.htx")
    public Map htxScraping(String inputNo) throws IOException, ParserConfigurationException, SAXException {

         return htxScrapingService.htxScraping(inputNo);

    }

}
