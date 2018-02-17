package com.EgyPoint.Electronic.Tests.Activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.EgyPoint.Electronic.Tests.R;

public class WebVedio extends AppCompatActivity {
    private WebView webView;

    String postUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_vedio);
        webView = findViewById(R.id.webview);

        Intent i=getIntent();
        if (i!=null){

            if (i.hasExtra("url")){
                postUrl=i.getStringExtra("url");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl(postUrl);
                webView.setHorizontalScrollBarEnabled(false);
                WebViewClientImpl webViewClient = new WebViewClientImpl(WebVedio.this);
                webView.setWebViewClient(webViewClient);
            }
        }


    }
    private  class  WebViewClientImpl extends WebViewClient{
        private Activity activity = null;

        public WebViewClientImpl(Activity activity) {
            this.activity = activity;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            activity.startActivity(intent);
            return true;
        }
    }
}
