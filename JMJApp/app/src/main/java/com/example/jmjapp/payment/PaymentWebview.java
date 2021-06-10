package com.example.jmjapp.payment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.jmjapp.R;

public class PaymentWebview extends Activity {
    private WebView paymentWebView;
    private static final String APP_SCHEME = "iamporttest://web1";
    private String shopNumber, jwt, resId, resDate, resTime, resShop, resAddr, resName, resPhone;
    static public String orderRequest;
    private Long orderId;
    private int resPrice, resPeople;

    @SuppressLint({"NewApi", "JavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_payment);

        SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
        jwt = pref.getString("token", "tokenIsNull");

        paymentWebView = (WebView) findViewById(R.id.paymentWebView);
        paymentWebView.setWebViewClient(new InicisWebViewClient(this));
        WebSettings settings = paymentWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.setAcceptThirdPartyCookies(paymentWebView, true);
        }

        Intent intent = getIntent();
        
        shopNumber = intent.getStringExtra("shopNumber");
        resDate = intent.getStringExtra("resDate");
        resTime = intent.getStringExtra("resTime");
        resPrice = intent.getIntExtra("price", 0);
        resPeople = intent.getIntExtra("people", 0);
        resPhone = intent.getStringExtra("resPhone");
        resAddr = intent.getStringExtra("resAddr");
        resName = intent.getStringExtra("resName");
        resId = intent.getStringExtra("resId");
        resShop = intent.getStringExtra("resShop");
        orderRequest = intent.getStringExtra("orderRequest");
        orderId = intent.getLongExtra("orderId",123);

        Log.d("leeyoungmin", orderRequest);

        Uri intentData = intent.getData();

        if (intentData == null) {
            Log.d("실행1", "실행1");
            Log.d("shopNumber", shopNumber);
            Log.d("jwt", jwt);
            Log.d("resDate", resDate);
            Log.d("resTime", resTime);
            Log.d("price", String.valueOf(resPrice));
            Log.d("people", String.valueOf(resPeople));

            paymentWebView.addJavascriptInterface(new MyJavaScriptInterface(), "android");
            //paymentWebView.loadUrl("http://192.168.1.62:8088/androidPayment");
            paymentWebView.loadUrl("http://3.34.55.186:8088/androidPayment");
        } else {
            //isp 인증 후 복귀했을 때 결제 후속조치
            Log.d("실행2", "실행2");
            String url = intentData.toString();
            if (url.startsWith(APP_SCHEME)) {
                String redirectURL = url.substring(APP_SCHEME.length() + 3);
                paymentWebView.loadUrl(redirectURL);
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d("실행3", "실행3");
        String url = intent.toString();
        if (url.startsWith(APP_SCHEME)) {
            String redirectURL = url.substring(APP_SCHEME.length() + 3);
            paymentWebView.loadUrl(redirectURL);
        }
    }

    class MyJavaScriptInterface {
        @JavascriptInterface
        public String getResDate() { // 예약날짜
            return resDate;
        }

        @JavascriptInterface
        public String getTimeDate() { // 예약시간
            return resTime;
        }

        @JavascriptInterface
        public int getPrice() { // 금액
            return resPrice;
        }

        @JavascriptInterface
        public int getPeople() { // 인원수
            return resPeople;
        }

        @JavascriptInterface
        public String getResShop() { // 매장이름
            return resShop;
        }

        @JavascriptInterface
        public String getResAddr() { // 매장주소
            return resAddr;
        }

        @JavascriptInterface
        public String getResName() { // 이름
            return resName;
        }

        @JavascriptInterface
        public String getResPhone() { // 전화번호
            return resPhone;
        }

        @JavascriptInterface
        public String getOrderId() { // orderId
            return String.valueOf(orderId);
        }

        @JavascriptInterface
        public void processDATA() {

            finish();
        }
    }
}
