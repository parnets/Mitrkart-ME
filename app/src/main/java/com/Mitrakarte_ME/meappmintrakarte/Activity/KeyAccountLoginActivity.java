package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.KeyAccountGetlogin;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dao;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dbhelper;

public class KeyAccountLoginActivity extends AppCompatActivity {

    private EditText mPassword, mmobile;
    TextView signup;
    TextView forgotpass;
    Button login;
    AG dg_inst = AG.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_account_login);

        mmobile =findViewById(R.id.login_id_KeyAccount);
       // mmobile.setText("6666666666");
        mPassword = findViewById(R.id.login_password_KeyAccount);
      //  mPassword.setText("1234k");
        signup = findViewById(R.id.signUp_text_KeyAccount);
        forgotpass = findViewById(R.id.forget_password);
        login = findViewById(R.id.login_KeyAccount);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mmobile.getText().toString().length() == 0) {
                    mmobile.setError("Enter mobile number");
                    return;
                }

                //todo:changes
                dg_inst.getUser().setkMobile(mmobile.getText().toString());

                if (mPassword.getText().toString().length() == 0) {
                    mPassword.setError("Enter password");
                    return;
                }
                //todo:changes
                dg_inst.getUser().setkPassword(mPassword.getText().toString());

                try {

                    AsyncTask<String, Void, Integer> result;
                    result = new KeyAccountGetlogin().execute(mmobile.getText().toString(),mPassword.getText().toString());
                    Integer code = result.get();
                    // System.out.println("jyo logincode received " + code);

                    if(code==201){
                        Toast.makeText(getApplicationContext(), "wrong user name and password", Toast.LENGTH_SHORT).show();
                    }
                    else if(code==202){
                        Toast.makeText(getApplicationContext(), "mobile and password not be empty", Toast.LENGTH_SHORT).show();

                    }else {

                        Dbhelper.createVendorDbHelper(KeyAccountLoginActivity.this);
                        Dao vendorDetailsDao = new Dao();
                        vendorDetailsDao.addUserDetails();

                        Toast.makeText(getApplicationContext(), "Login Sucessfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(KeyAccountLoginActivity.this, KeyAccountHomeActivity.class);
                        startActivity(intent);
                        finish();



                    }

                } catch (Exception e) {

                    Toast.makeText(KeyAccountLoginActivity.this, "Application Fail to Login", Toast.LENGTH_SHORT).show();
                }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(KeyAccountLoginActivity.this, KeyAccountMobileOtpVerification.class);
                startActivity(i);
            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(KeyAccountLoginActivity.this, KeyAccountForgetPasswordActivity.class);
                  startActivity(j);
            }
        });
    }

    public  static void netWorkAlert(final MELoginActivity context){

        if (isOnline(context)) {
            //do whatever you want to do
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

    @Override
    protected void onResume() {
        AG ht_inst = AG.getInstance();
        if(!ht_inst.getInitDone()) {
            Intent intent = new Intent(KeyAccountLoginActivity.this, FirstActivityAccountSelection.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(KeyAccountLoginActivity.this, FirstActivityAccountSelection.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }
}