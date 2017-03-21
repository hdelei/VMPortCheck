package com.example.vanderlei.vmportcheck;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView txtStatus;
    private EditText txtHost;
    private EditText txtPort;
    private TextView txtMyIP;
    private TextView txtPublicIP;

    //For homescreen icon
    Context mContext=MainActivity.this;
    public SharedPreferences appPreferences;
    boolean isAppInstalled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button btCheck = (Button) findViewById(R.id.btCheck);
        btCheck.setFocusable(true);

        txtHost = (EditText) findViewById(R.id.txtURL);
        txtHost.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        txtPort = (EditText) findViewById(R.id.txtPort);

        txtStatus = (TextView) findViewById(R.id.lblStatus);

        txtMyIP = (TextView) findViewById(R.id.txtMyIP);

        txtPublicIP = (TextView) findViewById(R.id.txtPublicIP);

        btCheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                txtStatus.setText("Obtaining status...");

                int port;
                port = Util.intTryParse(txtPort.getText().toString());
                txtPort.setText("" + port);

                String url = txtHost.getText().toString();
                if (url.equals("")){
                    Toast.makeText(MainActivity.this, "Invalid host", Toast.LENGTH_LONG).show();
                    txtHost.setText("Invalid");
                    txtStatus.setText("Change host and try again");
                }
                else{
                    checkPorts(url, port);
                }
            }
        });


        setHomeScreenIcon();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void checkPorts(String url, int port){
        final CheckerAsync check = new CheckerAsync(url, port);
        check.execute(txtStatus);
    }

    public void clearText(View v){
        txtPort.setText("");
        txtHost.setText("");
    }
    /*
        Set home screen icon after apk installation
     */
    public void setHomeScreenIcon(){
        appPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        isAppInstalled = appPreferences.getBoolean("isAppInstalled",false);
        if(isAppInstalled==false){

            //  create short code

            Intent shortcutIntent = new Intent(getApplicationContext(),MainActivity.class);
            shortcutIntent.setAction(Intent.ACTION_MAIN);
            Intent intent = new Intent();
            intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
            intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "Check Ports");
            intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource
                    .fromContext(getApplicationContext(), R.mipmap.ic_launcher));
            intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            getApplicationContext().sendBroadcast(intent);

            //Make preference true

            SharedPreferences.Editor editor = appPreferences.edit();
            editor.putBoolean("isAppInstalled", true);
            editor.commit();
        }
    }
    /*
        Get IP from MyIPAsync and update Host TextView with current result
     */
    public void getMyIP(View v){
        txtPublicIP.setText("___.___.___.___");
        final MyIPAsync myIP = new MyIPAsync();
        myIP.execute(txtPublicIP);
    }

    public void callHelpActivity(View v){
        Intent helpIntent = new Intent(this, HelpActivity.class);
        startActivity(helpIntent);
    }
}
