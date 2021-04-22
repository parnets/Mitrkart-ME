package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.KeyForgotpassword;

import java.util.regex.Matcher;

public class KeyAccountForgetPasswordActivity extends AppCompatActivity {

    Button resetButton;
    EditText emailId, mobile;
    TextView mtext;

    AG dg_inst = AG.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_account_forget_password);

        resetButton = findViewById(R.id.btn_confirm_key);
        emailId = (EditText) findViewById(R.id.et_fg_email_k);
        mtext = findViewById(R.id.back_text_keylogin);
        mobile = (EditText) findViewById(R.id.et_fg_mobile_k);



         mtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), KeyAccountLoginActivity.class);
                startActivity(intent);

            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Matcher matcher = Patterns.EMAIL_ADDRESS.matcher(emailId.getText().toString());
                if (!matcher.matches()) {

                    if (emailId.getText().length() != 10) {
                        emailId.setError("Invalid Email Id");
                        return;
                    }
                    dg_inst.getUser().setkMAil(emailId.getText().toString());

                    if (mobile.getText().length() == 0) {
                        mobile.setError("Enter  Mobile number");
                        return;
                    }
                    if (mobile.getText().length() != 10) {
                        mobile.setError("Invalid 10 Digit Mobile number");
                        return;
                    }
                    dg_inst.getUser().setkMobile(mobile.getText().toString());
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            dg_inst.getUser().setkMAil(""+emailId.getText().toString());
                            dg_inst.getUser().setkMobile(""+mobile.getText().toString());
                            AsyncTask<String, Void, Integer> result;
                            result = new KeyForgotpassword().execute(emailId.getText().toString());
                            Integer code = result.get();
                            //  System.out.println("jyo reg code" + code);

                            System.out.println("jyo code received " + code);
                            if (code == 201) {
                                Toast.makeText(getApplicationContext(), "Error Mail not sent", Toast.LENGTH_SHORT).show();

                            } else if (code == 202) {
                                Toast.makeText(getApplicationContext(), "Email not register,Enter registered Email ID", Toast.LENGTH_SHORT).show();
                            } else if (code == 203) {
                                Toast.makeText(getApplicationContext(), "Email and mobile Not Be Empty ", Toast.LENGTH_SHORT).show();
                            }else if (code == 205){
                                Toast.makeText(getApplicationContext(), "Email And Mobile Number not Match", Toast.LENGTH_SHORT).show();

                            }
                            else if (code == 200){
                                Toast.makeText(getApplicationContext(), "Credentials shared with email id registered", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(KeyAccountForgetPasswordActivity.this, KeyAccountLoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Error Mail not sent", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(KeyAccountForgetPasswordActivity.this, "Application Fail to request email recovery", Toast.LENGTH_SHORT).show();

                        }
                    }
                }, 1000);


            }
        });

    }
}