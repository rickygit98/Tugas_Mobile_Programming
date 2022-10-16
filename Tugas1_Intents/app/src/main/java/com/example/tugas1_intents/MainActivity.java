package com.example.tugas1_intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callButton(View v){
        Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"08988887288"));
        startActivity(call);
    }

    public void visitFacebook(View v){
        Intent facebook = new Intent(this,Facebook.class);
        startActivity(facebook);
    }

    public void visitTwitter(View v){
        Intent twitter = new Intent(this,Twitter.class);
        startActivity(twitter);
    }

    public void showProfile(View v){
        Intent profile = new Intent(this,Profile.class);
        startActivity(profile);
    }
}