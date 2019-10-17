package com.vjs.complaints;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class webpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webpage);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("http://www.kiit.ac.in");

    }
}
