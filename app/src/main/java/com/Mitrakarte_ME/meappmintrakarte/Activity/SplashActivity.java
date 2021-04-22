package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dao;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dbhelper;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageView;
    private static final int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Dbhelper.createVendorDbHelper(this);
        Dao vendorDetailsDao = new Dao();
        vendorDetailsDao.getUserDetails();


        System.out.println("jyo get user token "+ AG.getInstance().getUser().getUserToken());
        AG.getInstance().setInitDone(true);
        System.out.println("jyo get user id "+ AG.getInstance().getUser().getUserId());
        imageView = findViewById(R.id.splashscreen_logo);

        netWorkAlert(this);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                AG dg_inst = AG.getInstance();
                if (dg_inst.getUser().getUserId() == -1) {
                    Intent intent = new Intent(SplashActivity.this,
                            FirstActivityAccountSelection.class);
                    startActivity(intent);
                    finish();

                } else {
                    Intent intent = new Intent(SplashActivity.this,
                            FirstActivityAccountSelection.class);
                    startActivity(intent);
                    finish();
                }

            }
        },3000);

        // imageView.startAnimation(animation);
    }

    public  static void netWorkAlert(final SplashActivity context){

        if (isOnline(context)) {

        } else {
            try {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();

                alertDialog.setTitle("Info");
                alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        context.finish();

                    }
                });

                alertDialog.show();
            } catch (Exception e) {
                Log.e("Constants.TAG", "Show Dialog: " + e.getMessage());
            }
        }
    }

    private static boolean isOnline(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager)context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        // Toast.makeText(context, "No Internet connection!", Toast.LENGTH_LONG).show();
        return netInfo != null && netInfo.isConnected() && netInfo.isAvailable();
    }
}