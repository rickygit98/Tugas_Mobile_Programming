package com.example.tugas1_intents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Facebook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);

        WebView fbpage = findViewById(R.id.webviewFacebook);

        fbpage.getSettings().setJavaScriptEnabled(true);
        fbpage.loadUrl("https://www.facebook.com/Digillusions");
        fbpage.reload();
    }
}