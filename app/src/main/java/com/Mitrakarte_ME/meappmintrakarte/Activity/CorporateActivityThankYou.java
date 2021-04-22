package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;

public class CorporateActivityThankYou extends AppCompatActivity {

    TextView backToHome_cor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corporate_thank_you);

        backToHome_cor = findViewById(R.id.backToHome_cor);

        backToHome_cor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CorporateActivityThankYou.this, CorporateHomeActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        AG ht_inst = AG.getInstance();
        if(!ht_inst.getInitDone()) {
            Intent intent = new Intent(CorporateActivityThankYou.this, CorporateHomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(CorporateActivityThankYou.this, CorporateHomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }
}