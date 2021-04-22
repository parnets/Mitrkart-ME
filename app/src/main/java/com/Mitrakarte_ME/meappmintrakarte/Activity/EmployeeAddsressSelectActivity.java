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
import com.Mitrakarte_ME.meappmintrakarte.Service.EmployeeAddressChange;

import java.util.regex.Matcher;

public class EmployeeAddsressSelectActivity extends AppCompatActivity {

    TextView et_addr_name_emp, et_addr_email_emp, et_addr_phno_emp, et_addr_addr_emp, et_addr_locality_emp;
    TextView et_addr_Pin_emp, et_addr_landmark_emp, et_addr_city_emp, et_addr_state_emp, et_addr_country_emp;

    Button add_submit_emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_addsress_select);



        et_addr_name_emp =findViewById(R.id.et_addr_name_emp);
        et_addr_addr_emp =findViewById(R.id.et_addr_addr_emp);
        et_addr_locality_emp =findViewById(R.id.et_addr_locality_emp);
        et_addr_landmark_emp =findViewById(R.id.et_addr_landmark_emp);
        et_addr_country_emp =findViewById(R.id.et_addr_country_emp);
        et_addr_state_emp =findViewById(R.id.et_addr_state_emp);
        et_addr_city_emp =findViewById(R.id.et_addr_city_emp);
        et_addr_Pin_emp =findViewById(R.id.et_addr_Pin_emp);
        et_addr_phno_emp =findViewById(R.id.et_addr_phno_emp);
        et_addr_email_emp =findViewById(R.id.et_addr_email_emp);

        add_submit_emp =(Button) findViewById(R.id.add_submit_emp);

        add_submit_emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AG dg_inst= AG.getInstance();
                System.out.println("jyo name"+et_addr_name_emp);

                if (et_addr_name_emp.getText().toString().length() == 0){
                    et_addr_name_emp.setError("Enter  Name");
                    return;
                }
                dg_inst.getUser().setkName_Del_emp(et_addr_name_emp.getText().toString());

                if (et_addr_email_emp.getText().toString().length() == 0){
                    et_addr_email_emp.setError("Enter Email");
                    return;
                }
                Matcher matcher = Patterns.EMAIL_ADDRESS.matcher(et_addr_email_emp.getText().toString());
                if (!matcher.matches()) {
                    et_addr_email_emp.setError("Invalid Email Address");
                    return;
                }else
                dg_inst.getUser().setkEmail_del_emp(et_addr_email_emp.getText().toString());

                if (et_addr_phno_emp.getText().toString().length() == 0){
                    et_addr_phno_emp.setError("Enter Phone Number");
                    return;
                }
                if (et_addr_phno_emp.getText().toString().length() < 10){
                    et_addr_phno_emp.setError("Enter 10 Digit Number");
                    return;
                }
                dg_inst.getUser().setkContact_del_emp(et_addr_phno_emp.getText().toString());

                if (et_addr_addr_emp.getText().toString().length() == 0){
                    et_addr_addr_emp.setError("Enter Address ");
                    return;
                }
                dg_inst.getUser().setKaddress_Del_emp(et_addr_addr_emp.getText().toString());

                if (et_addr_locality_emp.getText().toString().length() == 0){
                    et_addr_locality_emp.setError("Enter Locality");
                    return;
                }
                dg_inst.getUser().setkLocality_Del_emp(et_addr_locality_emp.getText().toString());

                if (et_addr_Pin_emp.getText().toString().length() == 0){
                    et_addr_Pin_emp.setError("Enter Pincode ");
                    return;
                }
                 if (et_addr_Pin_emp.getText().toString().length() < 6){
                    et_addr_Pin_emp.setError("Enter Correct Pincode ");
                    return;
                }
                dg_inst.getUser().setkPin_del_emp(et_addr_Pin_emp.getText().toString());


                if (et_addr_landmark_emp.getText().toString().length() == 0){
                    et_addr_landmark_emp.setError("Enter Landmark ");
                    return;
                }
                dg_inst.getUser().setkLand_mark_Del_emp(et_addr_landmark_emp.getText().toString());

                if (et_addr_city_emp.getText().toString().length() == 0){
                    et_addr_city_emp.setError("Enter City ");
                    return;
                }
                dg_inst.getUser().setkCity_del_emp(et_addr_city_emp.getText().toString());

                if (et_addr_state_emp.getText().toString().length() == 0){
                    et_addr_state_emp.setError("Enter State ");
                    return;
                }
                dg_inst.getUser().setkState_del_emp(et_addr_state_emp.getText().toString());

                if (et_addr_country_emp.getText().toString().length() == 0){
                    et_addr_country_emp.setError("Enter Country ");
                    return;
                }
                dg_inst.getUser().setkCountry_del_emp(et_addr_country_emp.getText().toString());





                try {
                    AsyncTask<String, Void, Integer> result = new EmployeeAddressChange().execute();
                    int code = result.get();
                    System.out.println("jyo reg code" + code);
                    if (code == 200) {

//                        Dbhelper.createVendorDbHelper(EmployeeAddsressSelectActivity.this);
//                        Dao vendorDetailsDao = new Dao();
//                        vendorDetailsDao.getUserDetails();

                        Toast.makeText(EmployeeAddsressSelectActivity.this,"Address Added Successfully",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(EmployeeAddsressSelectActivity.this, EmployeeAddAddressActivity.class);
                        startActivity(i);
                        //  finish();
                    } else {
                        Toast.makeText(EmployeeAddsressSelectActivity.this, "Failed to Add Address", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {

                    Toast.makeText(EmployeeAddsressSelectActivity.this, "Fail to Add Address", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(EmployeeAddsressSelectActivity.this, EmployeeAddAddressActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }
}