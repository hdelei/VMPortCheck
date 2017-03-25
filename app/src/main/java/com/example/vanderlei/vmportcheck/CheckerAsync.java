package com.example.vanderlei.vmportcheck;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.widget.TextView;
import java.net.InetSocketAddress;
import java.net.Socket;


/**
 * Created by Vanderlei on 12/03/2017.
 */

public class CheckerAsync extends AsyncTask<TextView, Void, Boolean> {

    TextView text;
    String host;
    int port;
    final int TIMEOUT = 500;

    String portStr, isOpenStr, isClosedStr;

    public CheckerAsync(String[] str, int _port){
        host = str[0];
        port = _port;
        if (_port < 1)
            port = 0;

        portStr = str[1];
        isOpenStr = str[2];
        isClosedStr = str[3];
    }

    @Override
    protected Boolean doInBackground(TextView... myLabel) {
        text = myLabel[0];
        boolean isOpen = false;
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), TIMEOUT);
            socket.close();
            isOpen = true;
        }
        catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return isOpen;
    }

    @Override
    protected void onPostExecute(Boolean isOpen){


        if (isOpen)
            text.setText(portStr + " "+ port +" " + isOpenStr +  " \n" + host);
            //text.setText("Port "+ port +" is open on \n" + host);
        else
            text.setText(portStr + " " + port + " " + isClosedStr);
    }
}
