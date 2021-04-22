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

public class EmployeeProfileUpdateActivity extends AppCompatActivity {

    EditText etEmployeeName1_prou_Emp, etEmployeeCode2_prou_Emp, etResidentialAddress3_prou_Emp, etOfficeAddress4_prou_Emp;
    EditText etAadharNo5_prou_Emp, etPanNo6_prou_Emp, etEmpCompanyEmail7_prou_Emp, etEmpPersonalEmail8_prou_Emp;
    EditText  etDOB9_prou_Emp, etPassword10_prou_Emp;

    Button btn_employee_Update;

    private Boolean isDob = false;

    private int mDay;

    private int mMonth;
    private int mYear;

    AG ht_inst = AG.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile_update);

        etEmployeeName1_prou_Emp = findViewById(R.id.etEmployeeName1_prou_Emp);
        etEmployeeCode2_prou_Emp = findViewById(R.id.etEmployeeCode2_prou_Emp);
        etResidentialAddress3_prou_Emp = findViewById(R.id.etResidentialAddress3_prou_Emp);
        etOfficeAddress4_prou_Emp = findViewById(R.id.etOfficeAddress4_prou_Emp);
        etAadharNo5_prou_Emp = findViewById(R.id.etAadharNo5_prou_Emp);
        etPanNo6_prou_Emp = findViewById(R.id.etPanNo6_prou_Emp);
        etEmpCompanyEmail7_prou_Emp = findViewById(R.id.etEmpCompanyEmail7_prou_Emp);
        etEmpPersonalEmail8_prou_Emp = findViewById(R.id.etEmpPersonalEmail8_prou_Emp);
        etDOB9_prou_Emp = findViewById(R.id.etDOB9_prou_Emp);
        etPassword10_prou_Emp = findViewById(R.id.etPassword10_prou_Emp);

        btn_employee_Update  = findViewById(R.id.btn_employee_Update);


        //Todo; Fetching the Profile Data value into the EditText
        etEmployeeName1_prou_Emp.setText(""+ ht_inst.getInstance().getUser().getEmpNAme());
     //   profileMobile_emp.setText(""+ ht_inst.getInstance().getUser().getEmpMobile());
        etEmployeeCode2_prou_Emp.setText(""+ ht_inst.getInstance().getUser().getEmpCode());
        etResidentialAddress3_prou_Emp.setText(""+ ht_inst.getInstance().getUser().getEmpResidential_address());
        etOfficeAddress4_prou_Emp.setText(""+ ht_inst.getInstance().getUser().getEmpOfficialAddress());
        etAadharNo5_prou_Emp.setText(""+ ht_inst.getInstance().getUser().getEmpAadhaarNumber());
        etPanNo6_prou_Emp.setText(""+ ht_inst.getInstance().getUser().getEmppPanNumber());
        etEmpCompanyEmail7_prou_Emp.setText(""+ ht_inst.getInstance().getUser().getEmppEmployee_email());
        etEmpPersonalEmail8_prou_Emp.setText(""+ ht_inst.getInstance().getUser().getEmpePersonalMail());
        etDOB9_prou_Emp.setText(""+ ht_inst.getInstance().getUser().getEmpDob());
        etPassword10_prou_Emp.setText(""+ ht_inst.getInstance().getUser().getEmpPassword());

        etDOB9_prou_Emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                calendar.setTimeZone(TimeZone.getTimeZone("UTC"));

                int selectedYear = calendar.get(Calendar.YEAR);
                int selectedMonth = calendar.get(Calendar.MONTH);
                int selectedDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(EmployeeProfileUpdateActivity.this,
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

                                etDOB9_prou_Emp.setText(Date.toString());
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

        btn_employee_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AG dg_inst= AG.getInstance();
                System.out.println("jyo name"+etEmployeeName1_prou_Emp);

                if (etEmployeeName1_prou_Emp.getText().toString().length() == 0){
                    etEmployeeName1_prou_Emp.setError("Enter Employee Name");
                    return;
                }
                dg_inst.getUser().setEmpNAme(etEmployeeName1_prou_Emp.getText().toString());

                if (etEmployeeCode2_prou_Emp.getText().toString().length() == 0){
                    etEmployeeCode2_prou_Emp.setError("Enter Employee Code");
                    return;
                }
                dg_inst.getUser().setEmpCode(etEmployeeCode2_prou_Emp.getText().toString());

                if (etResidentialAddress3_prou_Emp.getText().toString().length() == 0){
                    etResidentialAddress3_prou_Emp.setError("Enter Residential Address");
                    return;
                }
                dg_inst.getUser().setEmpResidential_address(etResidentialAddress3_prou_Emp.getText().toString());

                if (etOfficeAddress4_prou_Emp.getText().toString().length() == 0){
                    etOfficeAddress4_prou_Emp.setError("Enter Office Address");
                    return;
                }
                dg_inst.getUser().setEmpOfficialAddress(etOfficeAddress4_prou_Emp.getText().toString());

                if (etAadharNo5_prou_Emp.getText().toString().length() == 0){
                    etAadharNo5_prou_Emp.setError("Enter Aadhar Number");
                    return;
                }
                dg_inst.getUser().setEmpAadhaarNumber(etAadharNo5_prou_Emp.getText().toString());

                if (etPanNo6_prou_Emp.getText().toString().length() == 0){
                    etPanNo6_prou_Emp.setError("Enter Pan Number");
                    return;
                }
                dg_inst.getUser().setEmppPanNumber(etPanNo6_prou_Emp.getText().toString());


                if (etEmpCompanyEmail7_prou_Emp.getText().toString().length() == 0){
                    etEmpCompanyEmail7_prou_Emp.setError("Enter Company Email Id");
                    return;
                }
                Matcher matcher = Patterns.EMAIL_ADDRESS.matcher(etEmpCompanyEmail7_prou_Emp.getText().toString());
                if (!matcher.matches()) {
                    etEmpCompanyEmail7_prou_Emp.setError("Invalid Email Id");
                    return;
                }else
                    dg_inst.getUser().setEmppEmployee_email(etEmpCompanyEmail7_prou_Emp.getText().toString());

                if (etEmpPersonalEmail8_prou_Emp.getText().toString().length() == 0){
                    etEmpPersonalEmail8_prou_Emp.setError("Enter Personal Email Id");
                    return;
                }
                Matcher matcher2 = Patterns.EMAIL_ADDRESS.matcher(etEmpPersonalEmail8_prou_Emp.getText().toString());
                if (!matcher2.matches()) {
                    etEmpPersonalEmail8_prou_Emp.setError("Invalid  Email Id");
                    return;
                }else
                    dg_inst.getUser().setEmpePersonalMail(etEmpPersonalEmail8_prou_Emp.getText().toString());

                if (etDOB9_prou_Emp.getText().toString().length() == 0){
                    etDOB9_prou_Emp.setError("Select DOB");
                    return;
                }
                dg_inst.getUser().setEmpDob(etDOB9_prou_Emp.getText().toString());

                if (etPassword10_prou_Emp.getText().toString().length() == 0){
                    etPassword10_prou_Emp.setError("Enter Password");
                    return;
                }
                dg_inst.getUser().setEmpPassword(etPassword10_prou_Emp.getText().toString());

                try {
                    AsyncTask<String, Void, Integer> result = new EmployeeRegister().execute();
                    int code = result.get();
                    System.out.println("jyo reg code" + code);
                    if (code == 200) {

                        Dbhelper.createVendorDbHelper(EmployeeProfileUpdateActivity.this);
                        Dao vendorDetailsDao = new Dao();
                        vendorDetailsDao.getUserDetails();

                        Toast.makeText(EmployeeProfileUpdateActivity.this,"Profie Update Successfully",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(EmployeeProfileUpdateActivity.this, EmployeeProfileActivity.class);
                        startActivity(i);
                        //  finish();
                    } else {
                        Toast.makeText(EmployeeProfileUpdateActivity.this, "Failed to Update", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {

                    Toast.makeText(EmployeeProfileUpdateActivity.this, "Fail to Update", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        AG ht_inst = AG.getInstance();
        if (!ht_inst.getInitDone()) {
            Intent intent = new Intent(EmployeeProfileUpdateActivity.this, EmployeeProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(EmployeeProfileUpdateActivity.this, EmployeeProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }
}