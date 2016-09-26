/**
 * Author: Muhammad Mustajab Ali
 * mustajab90@gmail.com
 *
 */

package com.example.must.my.derdiedas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.authentication.*;
import com.google.gson.reflect.TypeToken;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.net.InetAddress;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static android.R.id.message;
import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class MainActivity extends Activity {



    public MainActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Firebase.setAndroidContext(this);
        //CacheUtils.configureCache(this);

        //new InternetCheck().execute("Check for Internet");

        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void levelZero(View view) {
        Constants.level = Constants.LEVEL.ZERO;
        Intent intent = new Intent(MainActivity.this, SelectActivity.class);
        startActivity(intent);
    }

    public void levelOne(View view) {
        Constants.level = Constants.LEVEL.ONE;
        Intent intent = new Intent(MainActivity.this, SelectActivity.class);
        startActivity(intent);
    }





}




