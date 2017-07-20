package com.inti.brandon.travelme.RSSFeed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.inti.brandon.travelme.R;

public class NewsDetails extends AppCompatActivity {
WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        webView= (WebView) findViewById(R.id.webview);
        Bundle bundle=getIntent().getExtras();
        webView.loadUrl(bundle.getString("Link"));
    }
}
