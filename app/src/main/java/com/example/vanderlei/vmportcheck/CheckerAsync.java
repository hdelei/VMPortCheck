package com.example.vanderlei.vmportcheck;

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

    public CheckerAsync(String _host, int _port){
        host = _host;
        port = _port;
        if (_port < 1)
            port = 0;
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
            text.setText("Port "+ port +" is open on \n" + host);
        else
            text.setText("Port "+ port +" is closed or no response from host");
    }
}
