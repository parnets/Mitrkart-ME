package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.CustomerRegister;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dao;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dbhelper;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;

public class CustomerRegisterActivity extends AppCompatActivity {

    private int mDay;
    private int mMonth;
    private int mYear;

    private Boolean isetDateofBirth = false;

    EditText etName1C, etAddress2C, etEmail3C, etDOB4C, etGender5C, etPincode6C, etNewPassword7C;

    Button btnCustomerRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_register);

        etName1C = findViewById(R.id.etName1C);
        etAddress2C = findViewById(R.id.etAddress2C);
        etEmail3C = findViewById(R.id.etEmail3C);
        etDOB4C = findViewById(R.id.etDOB4C);
        etGender5C = findViewById(R.id.etGender5C);
        etPincode6C = findViewById(R.id.etPincode6C);
        etNewPassword7C = findViewById(R.id.etNewPassword7C);

        btnCustomerRegister = findViewById(R.id.btnCustomerRegister);

        //Todo: getting number from MobileotpVerification
        Intent intent = getIntent();
        String str = intent.getStringExtra("mobile number");
        //etPermanentContactNo16.setText(str);


        // Todo: Date of Birth
        etDOB4C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                calendar.setTimeZone(TimeZone.getTimeZone("UTC"));

                int selectedDay = calendar.get(Calendar.DAY_OF_MONTH);
                int selectedMonth = calendar.get(Calendar.MONTH);
                int selectedYear = calendar.get(Calendar.YEAR);


                DatePickerDialog datePickerDialog = new DatePickerDialog(CustomerRegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int selectedYear,
                                                  int selectedMonth, int selectedDay) {
                                mDay = selectedDay;
                                mMonth = selectedMonth;
                                mYear = selectedYear;
                                StringBuilder Date = new StringBuilder();
                                String conver = Integer.toString(selectedYear);
                                Date.append(conver);
                                Date.append("-");
                                selectedMonth++;
                                conver = Integer.toString(selectedMonth);
                                Date.append(conver);
                                Date.append("-");
                                conver = Integer.toString(selectedDay);
                                Date.append(conver);

                                etDOB4C.setText(Date.toString());
                                isetDateofBirth = true;
                            }
                        }, mDay, mMonth, mYear);

                datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                final Calendar calendar2 = Calendar.getInstance();
                calendar2.set(1940,1,1);
                datePickerDialog.getDatePicker().setMinDate(calendar2.getTimeInMillis());
                datePickerDialog.setTitle("Select Date");
                datePickerDialog.show();
            }
        });

        btnCustomerRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AG dg_inst= AG.getInstance();
                System.out.println("jyo name"+etName1C);

                if (etName1C.getText().toString().length() == 0){
                    etName1C.setError("Enter Firm Name");
                    return;
                }
                dg_inst.getUser().setcName(etName1C.getText().toString());

                if (etAddress2C.getText().toString().length() == 0){
                    etAddress2C.setError("Enter Address");
                    return;
                }
                dg_inst.getUser().setcAddress(etAddress2C.getText().toString());

                if (etEmail3C.getText().toString().length() == 0){
                    etEmail3C.setError("Enter Email");
                    return;
                }
                Matcher matcher = Patterns.EMAIL_ADDRESS.matcher(etEmail3C.getText().toString());
                if (!matcher.matches()) {
                    etEmail3C.setError("Invalid Email Address");
                    return;
                }else
                    dg_inst.getUser().setcMailID(etEmail3C.getText().toString());

                if (etDOB4C.getText().toString().length() == 0){
                    etDOB4C.setError("Enter DOB");
                    return;
                }
                dg_inst.getUser().setcDob(etDOB4C.getText().toString());

                if (etGender5C.getText().toString().length() == 0){
                    etGender5C.setError("Enter Gender");
                    return;
                }
                dg_inst.getUser().setcGender(etGender5C.getText().toString());

                if (etPincode6C.getText().toString().length() == 0){
                    etPincode6C.setError("Enter Pincode");
                    return;
                }
                if (etPincode6C.getText().toString().length() < 6){
                    etPincode6C.setError("Enter Correct Pincode");
                    return;
                }
                dg_inst.getUser().setcPincode(etPincode6C.getText().toString());

                if (etNewPassword7C.getText().toString().length() == 0){
                    etNewPassword7C.setError("Enter New Password");
                    return;
                }
                dg_inst.getUser().setcPassword(etNewPassword7C.getText().toString());


                try {
                    AsyncTask<String, Void, Integer> result = new CustomerRegister().execute();
                    int code = result.get();
                    System.out.println("jyo reg code" + code);
                    if (code == 200) {

                        Dbhelper.createVendorDbHelper(CustomerRegisterActivity.this);
                        Dao vendorDetailsDao = new Dao();
                        vendorDetailsDao.getUserDetails();

                        Toast.makeText(CustomerRegisterActivity.this,"Register Successfully",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(CustomerRegisterActivity.this, HomeActivity.class);
                        startActivity(i);
                        //  finish();
                    } else {
                        Toast.makeText(CustomerRegisterActivity.this, "Failed to Register", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {

                    Toast.makeText(CustomerRegisterActivity.this, "Fail to Register", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        AG ht_inst = AG.getInstance();
        if(!ht_inst.getInitDone()) {
            Intent intent = new Intent(CustomerRegisterActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(CustomerRegisterActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }
}