package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.Mitrakarte_ME.meappmintrakarte.R;

public class EmployeeActivityThankYou extends AppCompatActivity {

    TextView backToHome_emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_thank_you);

        backToHome_emp = findViewById(R.id.backToHome_emp);

        backToHome_emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmployeeActivityThankYou.this, EmployeeHomeActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(EmployeeActivityThankYou.this, EmployeeHomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }
}