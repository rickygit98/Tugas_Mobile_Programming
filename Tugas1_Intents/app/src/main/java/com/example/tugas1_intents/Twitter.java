package com.example.tugas1_intents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Twitter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);

        WebView twtpage = findViewById(R.id.webviewTwitter);

        twtpage.getSettings().setJavaScriptEnabled(true);
        twtpage.loadUrl("https://twitter.com/Digillusions");
        twtpage.reload();
    }
}