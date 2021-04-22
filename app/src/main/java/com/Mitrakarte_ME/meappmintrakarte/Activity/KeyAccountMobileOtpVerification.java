package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.KeyAccountMobileVerified;
import com.Mitrakarte_ME.meappmintrakarte.Service.KeyAccountOtpVlidation;

public class KeyAccountMobileOtpVerification extends AppCompatActivity {

    EditText mmobile, motp;
    TextView tvMobile;
    Button btn_signup, resend_otp;
    String mobile, otp;
    int flag = 1;
    private String otp_gen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_account_mobile_otp_verification);


        intializeView();

        resend_otp.setVisibility(View.INVISIBLE);
        tvMobile.setVisibility(View.GONE);
    }

    private void intializeView() {

        mmobile = findViewById(R.id.reg_mobile_keyaccount);
        motp = findViewById(R.id.reg_otp_keyaccount);
        motp.setVisibility(View.GONE);
        btn_signup = findViewById(R.id.otp_submit_keyaccount);
        tvMobile = findViewById(R.id.tv_reg_mobile_key);
        resend_otp = findViewById(R.id.resend_otp_key);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mobile = mmobile.getText().toString();
                if (motp.getText().toString().length() == 0) {
                    motp.setError("Enter otp");
                }
                AG.getInstance().getUser().setOtp(motp.getText().toString());

                if (flag == 1) {
                    if (mobile.isEmpty()) {
                        // Toast.makeText(KeyAccountMobileOtpVerification.this, "Please enter the phone number", Toast.LENGTH_SHORT).show();
                        mmobile.setError("Enter number");
                        return;
                    } else if (mobile.length() < 10) {
                        //  Toast.makeText(KeyAccountMobileOtpVerification.this, "Please enter 10 digits number", Toast.LENGTH_SHORT).show();
                        mmobile.setError("Enter 10 digits number");
                        return;
                    } else {

                        AG.getInstance().getUser().setkMobile(mobile);

                        tvMobile.setText(mobile);
                        mmobile.setVisibility(View.GONE);
                        tvMobile.setVisibility(View.VISIBLE);
                        resend_otp.setVisibility(View.VISIBLE);

                        AsyncTask<String, Void, Integer> result = new KeyAccountMobileVerified().execute(otp_gen);
                        try {
                            int code = result.get();
                            if (code == 203) {

                                resend_otp.setVisibility(View.INVISIBLE);
                                Toast.makeText(KeyAccountMobileOtpVerification.this, "Already Registered ", Toast.LENGTH_SHORT).show();
                                  /*  Intent intent = new Intent(KeyAccountMobileOtpVerification.this, LoginActivity.class);
                                    startActivity(intent);*/
                                return;
                            } else

                                Toast.makeText(KeyAccountMobileOtpVerification.this, "Otp sent to mobile ", Toast.LENGTH_SHORT).show();
                            motp.setVisibility(View.VISIBLE);

                        } catch (Exception e) {

                        }
                        flag = 2;
                    }
                } else {

                    if (motp.getText().length() != 4) {
                        Toast.makeText(KeyAccountMobileOtpVerification.this, "Enter Valid OTP", Toast.LENGTH_LONG).show();
                        return;
                    }


                    System.out.println("jyo otp" + otp);

                    try {
                        AsyncTask<String, Void, Integer> result;
                        result = new KeyAccountOtpVlidation().execute();//mmobile.getText().toString(), motp.getText().toString());

                        int code = result.get();
                        System.out.println("jyo get otp code " + code);

                        if (code == 201) {
                            Toast.makeText(KeyAccountMobileOtpVerification.this, "Invalid OTP", Toast.LENGTH_LONG).show();
                        } else if (code == 203) {
                            Toast.makeText(KeyAccountMobileOtpVerification.this, " Already Registered", Toast.LENGTH_LONG).show();

                        } else if (code == 200) {

                            Intent i = new Intent(KeyAccountMobileOtpVerification.this, KeyAccountsRegisterActivity.class);
                            startActivity(i);

                        }
                    } catch (Exception e) {
                        Toast.makeText(KeyAccountMobileOtpVerification.this, "OTP sending failed", Toast.LENGTH_LONG).show();

                    }
                }


            }
        });


        //Todo:Resend otp
        if (flag == 1) {
            resend_otp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mobile.isEmpty()) {
                        Toast.makeText(KeyAccountMobileOtpVerification.this, "Please enter the phone number", Toast.LENGTH_SHORT).show();
                    } else if (mobile.length() < 10) {
                        Toast.makeText(KeyAccountMobileOtpVerification.this, "Please enter 10 digits number", Toast.LENGTH_SHORT).show();
                    } else {
                        AG.getInstance().getUser().setMobile(mobile);
                        AsyncTask<String, Void, Integer> result = new KeyAccountMobileVerified().execute(otp_gen);
                        try {
                            int code = result.get();
                            if (code == 203) {

                                resend_otp.setVisibility(View.INVISIBLE);
                                Toast.makeText(KeyAccountMobileOtpVerification.this, " Already Registered ", Toast.LENGTH_SHORT).show();
                                return;
                            } else if (code == 204) {
                                Toast.makeText(KeyAccountMobileOtpVerification.this, " Enter Mobile Number ", Toast.LENGTH_SHORT).show();
                                return;
                            } else

                                btn_signup.setText("SUBMIT");

                            Toast.makeText(KeyAccountMobileOtpVerification.this, " OTP Resent to the phone number", Toast.LENGTH_SHORT).show();
                            motp.setVisibility(View.VISIBLE);

                        } catch (Exception e) {

                        }
                        flag = 2;
                    }
                }
            });
        }


    }

    @Override
    protected void onResume() {
        AG ht_inst = AG.getInstance();
        if (!ht_inst.getInitDone()) {
            Intent intent = new Intent(KeyAccountMobileOtpVerification.this, FirstActivityAccountSelection.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        super.onResume();
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(KeyAccountMobileOtpVerification.this, FirstActivityAccountSelection.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        super.onBackPressed();
    }

}