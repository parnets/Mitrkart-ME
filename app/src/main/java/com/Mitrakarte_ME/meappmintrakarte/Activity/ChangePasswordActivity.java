package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.ChangePassword;

public class ChangePasswordActivity extends AppCompatActivity {

    TextView old_password_k, new_password_k;
    Button btn_Change_Password_k;

    AG dg_inst = AG.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        old_password_k = findViewById(R.id.old_password_k);
        new_password_k = findViewById(R.id.new_password_k);

        btn_Change_Password_k = (Button) findViewById(R.id.btn_Change_Password_k);

        btn_Change_Password_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (old_password_k.getText().toString().length() == 0) {
                    old_password_k.setError("Enter Old Password");
                    return;
                }
                //todo:changes
                dg_inst.getUser().setkPassword(old_password_k.getText().toString());

                if (new_password_k.getText().toString().length() == 0) {
                    new_password_k.setError("Enter New password");
                    return;
                }
                //todo:changes
                dg_inst.getUser().setkPassword(new_password_k.getText().toString());


                //Todo: Password Changed
                AsyncTask<Context, Void, Integer> result2 = new ChangePassword().execute(ChangePasswordActivity.this);
                try {

                    Intent intent = new Intent(ChangePasswordActivity.this, KeyAccountHomeActivity.class);
                    startActivity(intent);

                    Toast.makeText(ChangePasswordActivity.this, "Password Changed", Toast.LENGTH_SHORT).show();

                    int code = result2.get();
                    System.out.println("jyo code" + code);
                } catch (Exception e) {

                    Toast.makeText(ChangePasswordActivity.this, "Old Password Not Matching", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}