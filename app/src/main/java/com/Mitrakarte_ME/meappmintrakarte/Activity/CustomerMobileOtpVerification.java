package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.CustomerOtpValidation;
import com.Mitrakarte_ME.meappmintrakarte.Service.MobileVerified;

public class CustomerMobileOtpVerification extends AppCompatActivity {

    EditText mmobile, motp;
    Button btn_signup;
    String mobile, otp;
    int flag = 1;
    private String otp_gen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_mobile_otp_verification);


        intializeView();
    }

    private void intializeView() {

        mmobile = findViewById(R.id.reg_mobile_customer);
        motp = findViewById(R.id.reg_otp_customer);
        motp.setVisibility(View.GONE);
        btn_signup = findViewById(R.id.otp_submit_customer);

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
                        Toast.makeText(CustomerMobileOtpVerification.this, "Please enter the phone number", Toast.LENGTH_SHORT).show();
                    } else if (mobile.length() < 10) {
                        Toast.makeText(CustomerMobileOtpVerification.this, "Please enter 10 digits number", Toast.LENGTH_SHORT).show();
                    } else {

                        //Todo: Changes
                        AG.getInstance().getUser().setMobile(mobile);
                        AsyncTask<String, Void, Integer> result = new MobileVerified().execute(otp_gen);
                        try {
                            int code = result.get();
                            if (code == 203) {
                                Toast.makeText(CustomerMobileOtpVerification.this, "Already Registered ", Toast.LENGTH_SHORT).show();
                                  /*  Intent intent = new Intent(Mobile_verify.this, LoginActivity.class);
                                    startActivity(intent);*/
                                return;
                            } else

                                Toast.makeText(CustomerMobileOtpVerification.this, "Otp sent to mobile ", Toast.LENGTH_SHORT).show();
                            motp.setVisibility(View.VISIBLE);

                        } catch (Exception e) {

                        }
                        flag = 2;
                    }
                } else {

                    if (motp.getText().length() != 4) {
                        Toast.makeText(CustomerMobileOtpVerification.this, "Enter Valid OTP", Toast.LENGTH_LONG).show();
                        return;
                    }


                    System.out.println("jyo otp"+otp);

                    try {
                        AsyncTask<String, Void, Integer> result;
                        result = new CustomerOtpValidation().execute();//mmobile.getText().toString(), motp.getText().toString());

                        int code = result.get();
                        System.out.println("jyo get otp code "+code);

                        if (code == 201) {
                            Toast.makeText(CustomerMobileOtpVerification.this, "Invalid OTP", Toast.LENGTH_LONG).show();
                        }else if (code == 203) {
                            Toast.makeText(CustomerMobileOtpVerification.this, " Already Registered", Toast.LENGTH_LONG).show();

                        } else if (code == 200) {

                            Intent i = new Intent(CustomerMobileOtpVerification.this, CustomerRegisterActivity.class);
                            startActivity(i);

                        }
                    } catch (Exception e) {
                        Toast.makeText(CustomerMobileOtpVerification.this, "OTP sending failed", Toast.LENGTH_LONG).show();

                    }
                }


            }
        });

    }

    @Override
    protected void onResume() {
        AG ht_inst = AG.getInstance();
        if(!ht_inst.getInitDone()) {
            Intent intent = new Intent(CustomerMobileOtpVerification.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(CustomerMobileOtpVerification.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }
}