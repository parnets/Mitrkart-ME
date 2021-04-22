package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.CorporateAddressChange;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dao;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dbhelper;

import java.util.regex.Matcher;

public class CorporateAddsressSelectActivity extends AppCompatActivity {

    TextView et_addr_name_cor, et_addr_email_cor, et_addr_phno_cor, et_addr_addr_cor, et_addr_locality_cor;
    TextView et_addr_Pin_cor, et_addr_landmark_cor, et_addr_city_cor, et_addr_state_cor, et_addr_country_cor;

    Button add_submit_cor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corporate_addsress_select);


        et_addr_name_cor =findViewById(R.id.et_addr_name_cor);
        et_addr_addr_cor =findViewById(R.id.et_addr_addr_cor);
        et_addr_locality_cor =findViewById(R.id.et_addr_locality_cor);
        et_addr_landmark_cor =findViewById(R.id.et_addr_landmark_cor);
        et_addr_country_cor =findViewById(R.id.et_addr_country_cor);
        et_addr_state_cor =findViewById(R.id.et_addr_state_cor);
        et_addr_city_cor =findViewById(R.id.et_addr_city_cor);
        et_addr_Pin_cor =findViewById(R.id.et_addr_Pin_cor);
        et_addr_phno_cor =findViewById(R.id.et_addr_phno_cor);
        et_addr_email_cor =findViewById(R.id.et_addr_email_cor);


        add_submit_cor =(Button) findViewById(R.id.add_submit);

        add_submit_cor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AG dg_inst= AG.getInstance();
                System.out.println("jyo name"+et_addr_name_cor);

                if (et_addr_name_cor.getText().toString().length() == 0){
                    et_addr_name_cor.setError("Enter  Name");
                    return;
                }
                dg_inst.getUser().setkName_Del_cor(et_addr_name_cor.getText().toString());

                if (et_addr_email_cor.getText().toString().length() == 0){
                    et_addr_email_cor.setError("Enter Email");
                    return;
                }
               // dg_inst.getUser().setkEmail_del_cor(et_addr_email_cor.getText().toString());aaa
                Matcher matcher = Patterns.EMAIL_ADDRESS.matcher(et_addr_email_cor.getText().toString());
                if (!matcher.matches()) {
                    et_addr_email_cor.setError("Invalid Email Address");
                    return;
                }else
                    dg_inst.getUser().setkEmail_del_cor(et_addr_email_cor.getText().toString());

                if (et_addr_phno_cor.getText().toString().length() == 0){
                    et_addr_phno_cor.setError("Enter Phone Number");
                    return;
                }
                if (et_addr_phno_cor.getText().toString().length() < 10){
                    et_addr_phno_cor.setError("Enter 10 Digit Number");
                    return;
                }
                dg_inst.getUser().setkContact_del_cor(et_addr_phno_cor.getText().toString());

                if (et_addr_addr_cor.getText().toString().length() == 0){
                    et_addr_addr_cor.setError("Enter Address ");
                    return;
                }
                dg_inst.getUser().setKaddress_Del_cor(et_addr_addr_cor.getText().toString());

                if (et_addr_locality_cor.getText().toString().length() == 0){
                    et_addr_locality_cor.setError("Enter Locality");
                    return;
                }
                dg_inst.getUser().setkLocality_Del_cor(et_addr_locality_cor.getText().toString());

                if (et_addr_Pin_cor.getText().toString().length() == 0){
                    et_addr_Pin_cor.setError("Enter Pincode ");
                    return;
                }
                if (et_addr_Pin_cor.getText().toString().length() < 6){
                    et_addr_Pin_cor.setError("Enter Correct Pincode ");
                    return;
                }
                dg_inst.getUser().setkPin_del_cor(et_addr_Pin_cor.getText().toString());


                if (et_addr_landmark_cor.getText().toString().length() == 0){
                    et_addr_landmark_cor.setError("Enter Landmark ");
                    return;
                }
                dg_inst.getUser().setkLand_mark_Del_cor(et_addr_landmark_cor.getText().toString());

                if (et_addr_city_cor.getText().toString().length() == 0){
                    et_addr_city_cor.setError("Enter City ");
                    return;
                }
                dg_inst.getUser().setkCity_del_cor(et_addr_city_cor.getText().toString());

                if (et_addr_state_cor.getText().toString().length() == 0){
                    et_addr_state_cor.setError("Enter State ");
                    return;
                }
                dg_inst.getUser().setkState_del_cor(et_addr_state_cor.getText().toString());

                if (et_addr_country_cor.getText().toString().length() == 0){
                    et_addr_country_cor.setError("Enter Country ");
                   return;
              }
               dg_inst.getUser().setkCountry_del_cor(et_addr_country_cor.getText().toString());



                try {
                    AsyncTask<String, Void, Integer> result = new CorporateAddressChange().execute();
                    int code = result.get();
                    System.out.println("jyo reg code" + code);
                    if (code == 200) {

                        Dbhelper.createVendorDbHelper(CorporateAddsressSelectActivity.this);
                        Dao vendorDetailsDao = new Dao();
                        vendorDetailsDao.getUserDetails();

                        Toast.makeText(CorporateAddsressSelectActivity.this,"Address Added Successfully",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(CorporateAddsressSelectActivity.this, CorporateAddAddressActivity.class);
                        startActivity(i);
                        //  finish();
                    } else {
                        Toast.makeText(CorporateAddsressSelectActivity.this, "Failed to Add Address", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {

                    Toast.makeText(CorporateAddsressSelectActivity.this, "Fail to AddAddress", Toast.LENGTH_SHORT).show();
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
            Intent intent = new Intent(CorporateAddsressSelectActivity.this, CorporateAddAddressActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        super.onResume();
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(CorporateAddsressSelectActivity.this, CorporateAddAddressActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }
}