//package com.ssafy.kpc.apt.api;
//
//import com.ssafy.kpc.apt.api.xml.XmlReader;
//import com.ssafy.kpc.apt.dto.Apt;
//import com.ssafy.kpc.apt.dto.RegionCodeItem;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.w3c.dom.Document;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import java.net.URLEncoder;
//import java.util.List;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class GetApi {
//
//    private final XmlReader xmlReader;
//
//    @Value("${external.api.key}")
//    String key;
//
//    public List<Apt> getApiData(RegionCodeItem regionCodeItem, String date) {
//        try {
//            StringBuilder urlBuilder = new StringBuilder("http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev"); /*URL*/
//            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + key); /*Service Key*/
////        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
//            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
//            int code = Integer.parseInt(regionCodeItem.getRegionNum().substring(0, 5));
//            urlBuilder.append("&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(code), "UTF-8")); /*지역코드*/
//            urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8")); /*계약월*/
//
//            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
//            Document doc = dBuilder.parse(urlBuilder.toString());
//
//            return xmlReader.parser(doc);
//        } catch (Exception e) {
//            log.error("error", e);
//            throw new RuntimeException();
//        }
//    }
//}
