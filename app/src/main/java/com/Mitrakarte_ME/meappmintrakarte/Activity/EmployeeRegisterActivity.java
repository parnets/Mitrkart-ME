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
import com.Mitrakarte_ME.meappmintrakarte.Service.EmployeeRegister;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dao;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dbhelper;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;

public class EmployeeRegisterActivity extends AppCompatActivity {

    EditText etEmployeeName1E, etEmployeeCode2E, etResidentialAddress3E, etOfficeAddress4E, etAadharNo5E;
    EditText etPanNo6E, etEmpCompanyEmail7E,  etEmpPersonalEmail8E, etDOB9E, etPassword10E;

    Button btn_employee_Register;

    private Boolean isDob = false;

    private int mDay;

    private int mMonth;
    private int mYear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_register);

        etEmployeeName1E = findViewById(R.id.etEmployeeName1E);
        etEmployeeCode2E = findViewById(R.id.etEmployeeCode2E);
        etResidentialAddress3E = findViewById(R.id.etResidentialAddress3E);
        etOfficeAddress4E = findViewById(R.id.etOfficeAddress4E);
        etAadharNo5E = findViewById(R.id.etAadharNo5E);
        etPanNo6E = findViewById(R.id.etPanNo6E);
        etEmpCompanyEmail7E = findViewById(R.id.etEmpCompanyEmail7E);
        etEmpPersonalEmail8E = findViewById(R.id.etEmpPersonalEmail8E);
        etDOB9E = findViewById(R.id.etDOB9E);
        etDOB9E.setKeyListener(null);
        etPassword10E = findViewById(R.id.etPassword10E);

        btn_employee_Register = findViewById(R.id.btn_employee_Register);


        etDOB9E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                calendar.setTimeZone(TimeZone.getTimeZone("UTC"));

                int selectedYear = calendar.get(Calendar.YEAR);
                int selectedMonth = calendar.get(Calendar.MONTH);
                int selectedDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(EmployeeRegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int selectedYear,
                                                  int selectedMonth, int selectedDay) {
                                mDay = selectedDay;
                                mMonth = selectedMonth;
                                mYear = selectedYear;
                                StringBuilder Date = new StringBuilder("");
                                String conver = Integer.toString(selectedYear);
                                Date.append(conver);
                                Date.append("-");
                                selectedMonth++;
                                conver = Integer.toString(selectedMonth);
                                Date.append(conver);
                                Date.append("-");
                                conver = Integer.toString(selectedDay);
                                Date.append(conver);

                                etDOB9E.setText(Date.toString());
                                isDob = true;
                            }
                        }, mDay, mMonth, mYear);

                datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                final Calendar calendar2 = Calendar.getInstance();
                calendar2.set(1940, 1, 1);
                datePickerDialog.getDatePicker().setMinDate(calendar2.getTimeInMillis());
                datePickerDialog.setTitle("Select Date");
                datePickerDialog.show();
            }
        });


        btn_employee_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AG dg_inst= AG.getInstance();
                System.out.println("jyo name"+etEmployeeName1E);

                if (etEmployeeName1E.getText().toString().length() == 0){
                    etEmployeeName1E.setError("Enter Employee Name");
                    return;
                }
                dg_inst.getUser().setEmpNAme(etEmployeeName1E.getText().toString());

                if (etEmployeeCode2E.getText().toString().length() == 0){
                    etEmployeeCode2E.setError("Enter Employee Code");
                    return;
                }
                dg_inst.getUser().setEmpCode(etEmployeeCode2E.getText().toString());

                if (etResidentialAddress3E.getText().toString().length() == 0){
                    etResidentialAddress3E.setError("Enter Residential Address");
                    return;
                }
                dg_inst.getUser().setEmpResidential_address(etResidentialAddress3E.getText().toString());

                if (etOfficeAddress4E.getText().toString().length() == 0){
                    etOfficeAddress4E.setError("Enter Office Address");
                    return;
                }
                dg_inst.getUser().setEmpOfficialAddress(etOfficeAddress4E.getText().toString());

                if (etAadharNo5E.getText().toString().length() == 0){
                    etAadharNo5E.setError("Enter Aadhar Number");
                    return;
                }
                if (etAadharNo5E.getText().toString().length() < 12){
                    etAadharNo5E.setError("Enter 12 Digit Aadhar Number");
                    return;
                }
                dg_inst.getUser().setEmpAadhaarNumber(etAadharNo5E.getText().toString());

                if (etPanNo6E.getText().toString().length() == 0){
                    etPanNo6E.setError("Enter Pan Number");
                    return;
                }
                dg_inst.getUser().setEmppPanNumber(etPanNo6E.getText().toString());


                if (etEmpCompanyEmail7E.getText().toString().length() == 0){
                    etEmpCompanyEmail7E.setError("Enter Company Email Id");
                    return;
                }
                Matcher matcher = Patterns.EMAIL_ADDRESS.matcher(etEmpCompanyEmail7E.getText().toString());
                if (!matcher.matches()) {
                    etEmpCompanyEmail7E.setError("Invalid Email Id");
                    return;
                }else
                     dg_inst.getUser().setEmppEmployee_email(etEmpCompanyEmail7E.getText().toString());

                if (etEmpPersonalEmail8E.getText().toString().length() == 0){
                    etEmpPersonalEmail8E.setError("Enter Personal Email Id");
                    return;
                }
                Matcher matcher2 = Patterns.EMAIL_ADDRESS.matcher(etEmpPersonalEmail8E.getText().toString());
                if (!matcher2.matches()) {
                    etEmpPersonalEmail8E.setError("Invalid  Email Id");
                    return;
                }else
                dg_inst.getUser().setEmpePersonalMail(etEmpPersonalEmail8E.getText().toString());

                if (etDOB9E.getText().toString().length() == 0){
                    etDOB9E.setError("Select DOB");
                    return;
                }
                dg_inst.getUser().setEmpDob(etDOB9E.getText().toString());

                if (etPassword10E.getText().toString().length() == 0){
                    etPassword10E.setError("Enter Password");
                    return;
                }
                dg_inst.getUser().setEmpPassword(etPassword10E.getText().toString());

                try {
                    AsyncTask<String, Void, Integer> result = new EmployeeRegister().execute();
                    int code = result.get();
                    System.out.println("jyo reg code" + code);
                    if (code == 200) {

                        Dbhelper.createVendorDbHelper(EmployeeRegisterActivity.this);
                        Dao vendorDetailsDao = new Dao();
                        vendorDetailsDao.getUserDetails();

                        Toast.makeText(EmployeeRegisterActivity.this,"Register Successfully",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(EmployeeRegisterActivity.this, CorporateHomeActivity.class);
                        startActivity(i);
                        //  finish();
                    }  else  if (code == 202){
                        Toast.makeText(EmployeeRegisterActivity.this, "Provide complete information to Register", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(EmployeeRegisterActivity.this, "Failed to Register", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {

                    Toast.makeText(EmployeeRegisterActivity.this, "Fail to Register", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}