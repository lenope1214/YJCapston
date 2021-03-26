package com.jumanji.capston.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

@Service
public class TxprService {

    private Gson gson;
    final String APIURL = "https://teht.hometax.go.kr/wqAction.do?actionId=ATTABZAA001R08&screenId=UTEABAAA13&popupYn=false&realScreenId=";
    final String IDTEXTFIELD = "idTextField";
    final String DONGTEXTFIELD = "dongTextField";
    final String XMLRAW = "<map id=\"ATTABZAA001R08\"><pubcUserNo/><mobYn>N</mobYn><inqrTrgtClCd>1</inqrTrgtClCd><txprDscmNo>idTextField</txprDscmNo><dongCode>dongTextField</dongCode><psbSearch>Y</psbSearch><map id=\"userReqInfoVO\"/></map><nts<nts>nts>62XGMPthYpaGY4sgE5yYu3QqEgXr2LO65PIkac7KmDg51";

    @PostConstruct
    private void setup() {
        gson = new GsonBuilder().create();
    }

    public String getTaxTypeFromNts(String txprDscmNo) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/xml; charset=UTF-8");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.put("Connection", "keep-alive");
        headers.put("Content-Length", "257");
        headers.put("Content-Type", "application/xml; charset=UTF-8");
        headers.put("Host", "teht.hometax.go.kr");
        headers.put("Origin", "https://teht.hometax.go.kr");
        headers.put("Referer", "https://teht.hometax.go.kr/websquare/websquare.html?w2xPath=/ui/ab/a/a/UTEABAAA13.xml");
        headers.put("Sec-Fetch-Mode", "cors");
        headers.put("Sec-Fetch-Site", "same-origin");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
        final String CRLF = "\n";
        StringBuffer sb = new StringBuffer();
        sb.append("<map id=\"ATTABZAA001R08\">" + CRLF);
        sb.append(" <pubcUserNo/>" + CRLF);
        sb.append(" <mobYn>N</mobYn>" + CRLF);
        sb.append(" <inqrTrgtClCd>1</inqrTrgtClCd>" + CRLF);
        sb.append(" <txprDscmNo>" + txprDscmNo + "</txprDscmNo>" + CRLF);
        sb.append(" <dongCode>" + txprDscmNo.substring(3,5) + "</dongCode>" + CRLF);
        sb.append(" <psbSearch>Y</psbSearch>" + CRLF);
        sb.append(" <map id=\"userReqInfoVO\"/>" + CRLF);
        sb.append("</map>" + CRLF);
        String body = sb.toString();
        try {
            Connection.Response res = Jsoup.connect(APIURL).headers(headers).requestBody(body).timeout(3000).method(Connection.Method.POST).execute();
//            if ()//logger.isDebugEnabled()) {
                //logger.debug(res.body());
//            }
            org.dom4j.Document document = DocumentHelper.parseText(res.body());
//            String trtCntn = StringUtils.nvl(document.valueOf("//map/trtCntn"), "");
//            if (//logger.isDebugEnabled()) {
                //logger.debug("trtCntn[" + trtCntn + "]");
//            }
            return document.toString();
        } catch (IOException e) {
            //logger.error("Jsoup 오류", e);
        } catch (DocumentException e) {
//            //logger.error("Document parse 오류", e);
        }
        return null;

    }

//    // National Tax Service check Business license number api.
//    public String getResult(String licenseId) throws IOException {
//        OutputStream os = null;
//        try {
//            licenseId = URLEncoder.encode(licenseId, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException("encoding fail!",e);
//        }
//        System.out.println("LICENSEID : " + licenseId);
//        String post = XMLRAW.replace(IDTEXTFIELD, licenseId); // 얘를 받은 사업자 번호로 재배치 시킴.
//        post = post.replace(DONGTEXTFIELD, licenseId.substring(3,5));
//        HttpURLConnection conn = connect(APIURL);
//        String resStr = "";
//        try{
//            conn.setDoOutput(true);
//            conn.setRequestMethod("POST");
//
//            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            conn.setRequestProperty("Content-Length", Integer.toString(post.length()));
//            os = conn.getOutputStream();
//            os.write(post.getBytes(StandardCharsets.UTF_8));
//            System.out.println("os 는 잘 됐나?" + os);
//            os.flush();
//            os.close();
//
//            int resultCode = conn.getResponseCode();
//            if(resultCode == 200){
//                System.out.println("요청 결과는???" + resultCode);
//                InputStreamReader in = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);
//                BufferedReader br = new BufferedReader(in);
//                String strLine;
//                while((strLine = br.readLine())!=null){
////                    if(strLine.contains("trtCntn"))return strLine;
//                    System.out.println("입력옴..!");
//                    resStr = strLine.concat(strLine);
//                }
//                System.out.println("리턴 resStr : " + resStr);
//
//                return resStr;
////                return "원하는 결과가 없었습니다.";
//            }else{
//                return "실패! 오류코드 : " + resultCode;
//            }
//        }catch (ProtocolException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            if(os!=null)os.close();
//            conn.disconnect();
//        }
//
//        return "원하는 정보가 없습니다.";
//    }


    private HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }
}