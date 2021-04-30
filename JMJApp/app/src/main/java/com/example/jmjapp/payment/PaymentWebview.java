package com.example.jmjapp.payment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.jmjapp.R;

public class PaymentWebview extends Activity {
    private WebView paymentWebView;
    private static final String APP_SCHEME = "iamporttest://";

    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_payment);

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
        Uri intentData = intent.getData();

        if ( intentData == null ) {
            paymentWebView.loadUrl("http://192.168.1.77:8088/androidPayment");
        } else {
            //isp 인증 후 복귀했을 때 결제 후속조치
            String url = intentData.toString();
            if ( url.startsWith(APP_SCHEME) ) {
                String redirectURL = url.substring(APP_SCHEME.length()+3);
                paymentWebView.loadUrl(redirectURL);
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        String url = intent.toString();
        if ( url.startsWith(APP_SCHEME) ) {
            String redirectURL = url.substring(APP_SCHEME.length()+3);
            paymentWebView.loadUrl(redirectURL);
        }
    }
}
