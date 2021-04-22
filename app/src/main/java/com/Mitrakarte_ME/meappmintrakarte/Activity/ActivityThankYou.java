package com.Mitrakarte_ME.meappmintrakarte.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.Mitrakarte_ME.meappmintrakarte.R;

public class ActivityThankYou extends AppCompatActivity {

    TextView backToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thank_you_page);


        backToHome = findViewById(R.id.backToHome);

        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityThankYou.this, KeyAccountHomeActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(ActivityThankYou.this, KeyAccountHomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }
}