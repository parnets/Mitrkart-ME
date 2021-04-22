package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;

public class HomeActivity extends AppCompatActivity {

    Button btnRetailerSurvey, btnRetailer, btnCorporate, btnKeyAccounts, btnCustomer, logout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnRetailerSurvey = findViewById(R.id.btnRetailerSurvey);
        btnRetailer = findViewById(R.id.btnRetailer);
        btnCorporate = findViewById(R.id.btnCorporate);
        btnKeyAccounts = findViewById(R.id.btnKeyAccounts);
        btnCustomer = findViewById(R.id.btnCustomer);
        logout = findViewById(R.id.logout);



        btnRetailerSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this, RetailerSurveyRegistrationActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnRetailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this, RetailerMobileOtpVerification.class);
                startActivity(i);
                finish();
            }
        });

        btnCorporate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this, CorporateMobileOtpVerification.class);
                startActivity(i);
                finish();
            }
        });

        btnKeyAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this, KeyAccountMobileOtpVerification.class);
                startActivity(i);
                finish();
            }
        });

        btnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this, CustomerMobileOtpVerification.class);
                startActivity(i);
                finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomeActivity.this);
                alertDialogBuilder.setMessage("Are you sure,You want to Logout");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                   /* DbHelper.createVendorDbHelper(SlidingBarActivity.this);
                    Dao vendorDetailsDao = new Dao();
                    vendorDetailsDao.deleteUserDetails();*/

                                Intent intent1= new Intent(HomeActivity.this, FirstActivityAccountSelection.class);
                                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent1);
                                finish();
                            }
                        });


                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }


    @Override
    protected void onResume() {
        AG ht_inst = AG.getInstance();
        if(!ht_inst.getInitDone()) {
            Intent intent = new Intent(HomeActivity.this, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        super.onResume();
    }
}