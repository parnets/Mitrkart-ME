package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.Mitrakarte_ME.meappmintrakarte.R;


public class dummy extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

Spinner spinnerEg;
Button btnEg;
TextView tvEg;
    String[] brandAvailaibility  = { "Select  Availability","Yes", "No"};
    String[] brandFrequncyOfService  = { "Select Frequency of Service","Daily", "Alternative Day", "Weekly", "Alternative Week", "Monthly"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dummy);


        spinnerEg = (Spinner) findViewById(R.id.spinnerEg);
        btnEg = (Button) findViewById(R.id.btnEg);
        tvEg = (TextView) findViewById(R.id.tvEg);

        spinnerEg.setOnItemSelectedListener(this);
        ArrayAdapter dd = new ArrayAdapter(this,android.R.layout.simple_spinner_item,   brandAvailaibility);
        dd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEg.setAdapter(dd);



        btnEg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textSpinner = spinnerEg.getSelectedItem().toString();

                if (textSpinner.equals("Yes")){
//
//                    if (textSpnFreqHul.equals("Daily") || textSpnFreqHul.equals("Alternative Day") ||
//                        textSpnFreqHul.equals("Weekly") || textSpnFreqHul.equals("Alternative Week") ||
//                            textSpnFreqHul.equals("Monthly Day")){
//
//
//                    }else {
//                        tvBrandNameHUL18RS.setError("Select");
//                        return;
//                    }

                    Intent intent = new Intent(dummy.this, HomeActivity.class);
                     startActivity(intent);
                     finish();

                        //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else if (textSpinner.equals("No")){
                    Intent intent = new Intent(dummy.this, HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
                else {
                    tvEg.setError("Select");
                    return;

//                        TextView errorTextview2 = (TextView)spinnerAvailabiltyHUL18RS.getSelectedView();
//                        //  errorTextview.setError("");
//                        errorTextview2.setTextColor(Color.RED);//
//                        errorTextview2.setError("Select Availabilty Of Service");
//                        errorTextview2.setText("Error Select Availabilty");
                }
            }


        });


    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}