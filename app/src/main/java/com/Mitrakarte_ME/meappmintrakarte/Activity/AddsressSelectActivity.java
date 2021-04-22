package com.Mitrakarte_ME.meappmintrakarte.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.KeyAccountAddressChange;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dao;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dbhelper;

import java.util.regex.Matcher;

public class AddsressSelectActivity  extends AppCompatActivity {

    TextView et_addr_name, et_addr_email, et_addr_phno, et_addr_addr, et_addr_locality;
    TextView et_addr_Pin, et_addr_landmark, et_addr_city, et_addr_state;

    Button add_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_address);

        et_addr_name =findViewById(R.id.et_addr_name);
        et_addr_addr =findViewById(R.id.et_addr_addr);
        et_addr_locality =findViewById(R.id.et_addr_locality);
        et_addr_landmark =findViewById(R.id.et_addr_landmark);
      //  et_addr_country =findViewById(R.id.et_addr_country);
        et_addr_state =findViewById(R.id.et_addr_state);
        et_addr_city =findViewById(R.id.et_addr_city);
        et_addr_Pin =findViewById(R.id.et_addr_Pin);
        et_addr_phno =findViewById(R.id.et_addr_phno);
        et_addr_email =findViewById(R.id.et_addr_email);



        add_submit =(Button) findViewById(R.id.add_submit);

        add_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AG dg_inst= AG.getInstance();
                System.out.println("jyo name"+et_addr_name);

                if (et_addr_name.getText().toString().length() == 0){
                    et_addr_name.setError("Enter  Name");
                    return;
                }
                dg_inst.getUser().setkName_Del(et_addr_name.getText().toString());

                if (et_addr_email.getText().toString().length() == 0){
                    et_addr_email.setError("Enter Email");
                    return;
                }
                Matcher matcher = Patterns.EMAIL_ADDRESS.matcher(et_addr_email.getText().toString());
                if (!matcher.matches()) {
                    et_addr_email.setError("Invalid Email Address");
                    return;
                }else
                    dg_inst.getUser().setkEmail_del(et_addr_email.getText().toString());

                if (et_addr_phno.getText().toString().length() == 0){
                    et_addr_phno.setError("Enter Phone Number");
                    return;
                }
                if (et_addr_phno.getText().toString().length() < 10){
                    et_addr_phno.setError("Enter 10 Digit Number");
                    return;
                }
                dg_inst.getUser().setkContact_del(et_addr_phno.getText().toString());

                if (et_addr_addr.getText().toString().length() == 0){
                    et_addr_addr.setError("Enter Address ");
                    return;
                }
                dg_inst.getUser().setKaddress_Del(et_addr_addr.getText().toString());

                if (et_addr_locality.getText().toString().length() == 0){
                    et_addr_locality.setError("Enter Locality");
                    return;
                }
                dg_inst.getUser().setkLocality_Del(et_addr_locality.getText().toString());

                if (et_addr_Pin.getText().toString().length() == 0){
                    et_addr_Pin.setError("Enter Pincode ");
                    return;
                }
                if (et_addr_Pin.getText().toString().length() < 6){
                    et_addr_Pin.setError("Enter Correct Pincode ");
                    return;
                }
                dg_inst.getUser().setkPin_del(et_addr_Pin.getText().toString());


                if (et_addr_landmark.getText().toString().length() == 0){
                    et_addr_landmark.setError("Enter Landmark ");
                    return;
                }
                dg_inst.getUser().setkLand_mark_Del(et_addr_landmark.getText().toString());

                if (et_addr_city.getText().toString().length() == 0){
                    et_addr_city.setError("Enter City ");
                    return;
                }
                dg_inst.getUser().setkCity_del(et_addr_city.getText().toString());

                if (et_addr_state.getText().toString().length() == 0){
                    et_addr_state.setError("Enter State ");
                    return;
                }
                dg_inst.getUser().setkState_del(et_addr_state.getText().toString());

//                if (et_addr_country.getText().toString().length() == 0){
//                    et_addr_country.setError("Enter Country ");
//                    return;
//                }
//                dg_inst.getUser().setkCountry_del(et_addr_country.getText().toString());












                try {
                    AsyncTask<String, Void, Integer> result = new KeyAccountAddressChange().execute();
                    int code = result.get();
                    System.out.println("jyo reg code" + code);
                    if (code == 200) {

                        Dbhelper.createVendorDbHelper(AddsressSelectActivity.this);
                        Dao vendorDetailsDao = new Dao();
                        vendorDetailsDao.getUserDetails();

                        Toast.makeText(AddsressSelectActivity.this,"Address Added Successfully",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(AddsressSelectActivity.this, AddAddressActivity.class);
                        startActivity(i);
                        //  finish();
                    } else {
                        Toast.makeText(AddsressSelectActivity.this, "Failed to Add Address", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {

                    Toast.makeText(AddsressSelectActivity.this, "Fail to Add Address", Toast.LENGTH_SHORT).show();
                }

//                Intent intent = new Intent(AddsressSelectActivity.this, DeliverAddressActivity.class);
//                startActivity(intent);

            }
        });
    }

    @Override
    protected void onResume() {
        AG ht_inst = AG.getInstance();
        if(!ht_inst.getInitDone()) {
            Intent intent = new Intent(AddsressSelectActivity.this, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(AddsressSelectActivity.this, AddAddressActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }
}
