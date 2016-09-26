package com.example.must.my.derdiedas;

import android.os.AsyncTask;
import java.net.InetAddress;

import static com.example.must.my.derdiedas.Constants.istate;
import static com.example.must.my.derdiedas.SelectActivity.fileRead;
import static com.example.must.my.derdiedas.SelectActivity.firebaseinit;


/**
 * Created by mustajab on 9/26/16.
 */

public class InternetCheck extends AsyncTask<String, Void, Boolean> {

    @Override
    protected Boolean doInBackground(String... params) {
        if (isInternetAvailable()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            istate = Constants.INTERNETSTATE.CONNECTED;
            firebaseinit();
        } else {
            istate = Constants.INTERNETSTATE.NOTCONNECTED;
            fileRead();
        }

    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected void onProgressUpdate(Void... values) {}


    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com"); //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }

    }

}