package com.example.vanderlei.vmportcheck;

import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Created by Vanderlei on 18/03/2017.
 */

public class MyIPAsync extends AsyncTask<TextView, Void, TextView> {

    private String myIP;

    @Override
    protected TextView doInBackground(TextView... txt){
        try (java.util.Scanner s = new java.util.Scanner(
                new java.net.URL("https://api.ipify.org").openStream(),
                "UTF-8").useDelimiter("\\A")) {
            myIP = s.next();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return txt[0];
    }

    @Override
    protected void onPostExecute(TextView txt){
        //txt.setText("___.___.___.___");
        txt.setText(myIP);
    }
}
