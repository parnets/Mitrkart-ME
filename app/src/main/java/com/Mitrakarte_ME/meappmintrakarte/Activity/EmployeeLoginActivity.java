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
import com.Mitrakarte_ME.meappmintrakarte.Service.EmployeeGetlogin;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dao;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dbhelper;

public class EmployeeLoginActivity extends AppCompatActivity {


    private EditText mPassword, mmobile;
    TextView signup;
    TextView forgotpass;
    Button login;
    AG dg_inst = AG.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_login);

        mmobile =findViewById(R.id.login_id_employee);
         //mmobile.setText("6666666666");
        mPassword = findViewById(R.id.login_password_employee);
        //mPassword.setText("1234e");
        forgotpass = findViewById(R.id.forget_password_employee);
        login = findViewById(R.id.login_employee);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mmobile.getText().toString().length() == 0) {
                    mmobile.setError("Enter mobile number");
                    return;
                }

                //todo:changes
                dg_inst.getUser().setEmpMobile(mmobile.getText().toString());

                if (mPassword.getText().toString().length() == 0) {
                    mPassword.setError("Enter password");
                    return;
                }
                //todo:changes
                dg_inst.getUser().setEmpPassword(mPassword.getText().toString());

                try {

                    AsyncTask<String, Void, Integer> result;
                    result = new EmployeeGetlogin().execute(mmobile.getText().toString(),mPassword.getText().toString());
                    Integer code = result.get();
                    // System.out.println("jyo logincode received " + code);

                    if(code==201){
                        Toast.makeText(getApplicationContext(), "wrong user name and password", Toast.LENGTH_SHORT).show();
                    }
                    else if(code==202){
                        Toast.makeText(getApplicationContext(), "mobile and password not be empty", Toast.LENGTH_SHORT).show();

                    }else {

                        Dbhelper.createVendorDbHelper(EmployeeLoginActivity.this);
                        Dao vendorDetailsDao = new Dao();
                        vendorDetailsDao.addUserDetails();

                        Toast.makeText(getApplicationContext(), "Login Sucessfull", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(EmployeeLoginActivity.this, EmployeeHomeActivity.class);
                        startActivity(intent);
                        finish();
                    }

                } catch (Exception e) {

                    Toast.makeText(EmployeeLoginActivity.this, "Application Fail to Login", Toast.LENGTH_SHORT).show();
                }

            }
        });


//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(CorporateLoginActivity.this, CorporateMobileOtpVerification.class);
//                startActivity(i);
//            }
//        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(EmployeeLoginActivity.this, EmployeeForgetPasswordActivity.class);
                  startActivity(j);
            }
        });
    }

    public  static void netWorkAlert(final EmployeeLoginActivity context){

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
            Intent intent = new Intent(EmployeeLoginActivity.this, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        super.onResume();
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(EmployeeLoginActivity.this, FirstActivityAccountSelection.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }

}