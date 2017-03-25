package com.example.vanderlei.vmportcheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        TextView text = (TextView) findViewById(R.id.lblStatus);

        WebView helpView = (WebView) findViewById(R.id.helpV);
        WebSettings settings = helpView.getSettings();
        settings.setJavaScriptEnabled(true);
        helpView.loadUrl("file:///android_asset/help_about_apk.html");


    }
}
