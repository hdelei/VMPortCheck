package com.example.vanderlei.vmportcheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.Locale;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        String defaulthtml, ptbrHtml;
        defaulthtml = "file:///android_asset/help_about_apk.html";
        ptbrHtml = "file:///android_asset/help_about_apk_pt_BR.html";

        WebView helpView = (WebView) findViewById(R.id.helpV);
        //WebSettings settings = helpView.getSettings();
        //settings.setJavaScriptEnabled(true);
        String language = Locale.getDefault().getLanguage();
        if (language.equals("pt"))
            helpView.loadUrl(ptbrHtml);
        else
            helpView.loadUrl(defaulthtml);



    }
}
