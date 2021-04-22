package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.Mitrakarte_ME.meappmintrakarte.R;

public class FirstActivityAccountSelection extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    String[] TypeOfAccount  = { "Select Type of Account","ME", "Corporates", "Key Accounts", "Employee"};

    Spinner spinnerTypeOfAccount;
    Button btnSubmitAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        spinnerTypeOfAccount  = findViewById(R.id.spinnerTypeOfAccount);
        btnSubmitAccount = findViewById(R.id.btnSubmitAccount);



        //Todo: spinner-  Type Of Account
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        spinnerTypeOfAccount.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, TypeOfAccount);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinnerTypeOfAccount.setAdapter(aa);


        btnSubmitAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (spinnerTypeOfAccount.getSelectedItemPosition() > 0) {
//                    Intent i = new Intent(FirstActivity.this, LoginActivity.class);
//                    startActivity(i);

                }
//                else if (spinnerTypeOfAccount.getSelectedItemPosition() == 1){
//
//                }
//                else if (spinnerTypeOfAccount.getSelectedItemPosition() == 2){
//
//                }

                else {


                    TextView errorTextview = (TextView) spinnerTypeOfAccount.getSelectedView();

                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Type of Firm");

                }
            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        Spinner spinnerTypeOfAccount = (Spinner) adapterView;


//        if (spinnerTypeOfAccount.getId() == R.id.spinnerTypeOfRetailer16RS) {
//
//            String selectedItem = adapterView.getItemAtPosition(position).toString();
//            if (selectedItem.equals("ME")) {
//                Intent i = new Intent(FirstActivity.this, LoginActivity.class);
//                startActivity(i);
//
//            } else {
//
//            }
//        }

        String selectedClass = adapterView.getItemAtPosition(position).toString();
        switch (selectedClass) {
            case "ME":
                // assigning div item list defined in XML to the div Spinner
                Intent i = new Intent(FirstActivityAccountSelection.this, MELoginActivity.class);
                startActivity(i);
                break;

            case "Corporates":
                Intent in = new Intent(FirstActivityAccountSelection.this, CorporateLoginActivity.class);
                startActivity(in);
                break;

            case "Key Accounts":
                Intent intent = new Intent(FirstActivityAccountSelection.this, KeyAccountLoginActivity.class);
                startActivity(intent);
                break;

            case "Employee":
                Intent intent2 = new Intent(FirstActivityAccountSelection.this, EmployeeLoginActivity.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

//    @Override
//    protected void onResume () {
//        AG ht_inst = AG.getInstance();
//        if (!ht_inst.getInitDone()) {
//            Intent intent = new Intent(FirstActivity.this, SplashActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
//
//        super.onResume();
//    }
}