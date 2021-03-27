package com.jumanji.capston.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TxprService {

    private Gson gson;
    final String APIURL = "https://teht.hometax.go.kr/wqAction.do?actionId=ATTABZAA001R08&screenId=UTEABAAA13&popupYn=false&realScreenId=";

    @PostConstruct
    private void setup() {
        gson = new GsonBuilder().create();
    }

    public String getTaxTypeFromNts(String txprDscmNo) {
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Accept", "application/xml; charset=UTF-8");
        requestHeaders.put("Accept-Encoding", "gzip, deflate, br");
        requestHeaders.put("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
        requestHeaders.put("Connection", "keep-alive");
        requestHeaders.put("Content-Length", "257");
        requestHeaders.put("Content-Type", "application/xml; charset=UTF-8");
        requestHeaders.put("Host", "teht.hometax.go.kr");
        requestHeaders.put("Origin", "https://teht.hometax.go.kr");
        requestHeaders.put("Referer", "https://teht.hometax.go.kr/websquare/websquare.html?w2xPath=/ui/ab/a/a/UTEABAAA13.xml");
        requestHeaders.put("Sec-Fetch-Mode", "cors");
        requestHeaders.put("Sec-Fetch-Site", "same-origin");
        requestHeaders.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
        final String CRLF = "\n";
        StringBuffer sb = new StringBuffer();
        sb.append("<map id=\"ATTABZAA001R08\">" + CRLF);
        sb.append(" <pubcUserNo/>" + CRLF);
        sb.append(" <mobYn>N</mobYn>" + CRLF);
        sb.append(" <inqrTrgtClCd>1</inqrTrgtClCd>" + CRLF);
        sb.append(" <txprDscmNo>" + txprDscmNo + "</txprDscmNo>" + CRLF);
        sb.append(" <dongCode>" + txprDscmNo.substring(3, 5) + "</dongCode>" + CRLF);
        sb.append(" <psbSearch>Y</psbSearch>" + CRLF);
        sb.append(" <map id=\"userReqInfoVO\"/>" + CRLF);
        sb.append("</map>" + CRLF);
        String body = sb.toString();
        try {
            Connection.Response res = Jsoup.connect(APIURL).headers(requestHeaders).requestBody(body).timeout(3000).method(Connection.Method.POST).execute();
            //res.body() :
            /*
            <map id='' ><map id='resultMsg' ><detailMsg></detailMsg><msg></msg><code></code><result>S</result></map>
<trtEndCd>Y</trtEndCd>
<smpcBmanEnglTrtCntn>The business registration number is registered</smpcBmanEnglTrtCntn>
<nrgtTxprYn>N</nrgtTxprYn>
<smpcBmanTrtCntn>등록되어 있는 사업자등록번호 입니다. </smpcBmanTrtCntn>
<trtCntn>부가가치세 일반과세자 입니다.</trtCntn>
</map>
             */
            Pattern pattern =
                    Pattern.compile("(<smpcBmanTrtCntn>)(.*?)(</smpcBmanTrtCntn>)"); // 등록여부만 물어볼때
//            Pattern.compile("(<smpcBmanTrtCntn>|<trtCntn>)(.*?)(</smpcBmanTrtCntn>|</trtCntn>)");
            // 위 주석은 등록여부, 일반부가세 내는건지 판별.

            Matcher matcher = pattern.matcher(res.body());
            String trtCntn = "";
            while(matcher.find()){ // 일치하는게 있을때 까지 돎.
                trtCntn = matcher.group(2);

            }
            return trtCntn;

        } catch (IOException e) {
            //logger.error("Jsoup 오류", e);
        }
        return null;

    }

}