package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;

public class EmployeeProfileActivity extends AppCompatActivity {

    TextView profile_EmployeeName_emp, profileMobile_emp, profilePassword_emp, profile_EmployeeCode_emp, profile_CompanyEmail_emp;
    TextView profile_PersonalEmail_emp, profile_ResidentialAddress_emp, profile_OfficeAddress_emp, profile_AadharNo_emp;
    TextView    profile_PanNo_emp, profile_DOB_emp;

    Button btnUpdateProfile_emp;

    AG ht_inst = AG.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);

        btnUpdateProfile_emp = (Button) findViewById(R.id.btnUpdateProfile_emp);

        profile_EmployeeName_emp = findViewById(R.id.profile_EmployeeName_emp);
        profileMobile_emp = findViewById(R.id.profileMobile_emp);
        profilePassword_emp = findViewById(R.id.profilePassword_emp);
        profile_EmployeeCode_emp = findViewById(R.id.profile_EmployeeCode_emp);
        profile_CompanyEmail_emp = findViewById(R.id.profile_CompanyEmail_emp);
        profile_PersonalEmail_emp = findViewById(R.id.profile_PersonalEmail_emp);
        profile_ResidentialAddress_emp = findViewById(R.id.profile_ResidentialAddress_emp);
        profile_OfficeAddress_emp = findViewById(R.id.profile_OfficeAddress_emp);
        profile_AadharNo_emp = findViewById(R.id.profile_AadharNo_emp);
        profile_PanNo_emp = findViewById(R.id.profile_PanNo_emp);
        profile_DOB_emp =  findViewById(R.id.profile_DOB_emp);

        //Todo; Fetching the Profile Data value into the TextView
        profile_EmployeeName_emp.setText(""+ ht_inst.getInstance().getUser().getEmpNAme());
        profileMobile_emp.setText(""+ ht_inst.getInstance().getUser().getEmpMobile());
        profilePassword_emp.setText(""+ ht_inst.getInstance().getUser().getEmpPassword());

        profile_EmployeeCode_emp.setText(""+ ht_inst.getInstance().getUser().getEmpCode());
        profile_CompanyEmail_emp.setText(""+ ht_inst.getInstance().getUser().getEmppEmployee_email());
        profile_PersonalEmail_emp.setText(""+ ht_inst.getInstance().getUser().getEmpePersonalMail());
        profile_ResidentialAddress_emp.setText(""+ ht_inst.getInstance().getUser().getEmpResidential_address());
        profile_OfficeAddress_emp.setText(""+ ht_inst.getInstance().getUser().getEmpOfficialAddress());
        profile_AadharNo_emp.setText(""+ ht_inst.getInstance().getUser().getEmpAadhaarNumber());
        profile_PanNo_emp.setText(""+ ht_inst.getInstance().getUser().getEmppPanNumber());
        profile_DOB_emp.setText(""+ ht_inst.getInstance().getUser().getEmpDob());

        btnUpdateProfile_emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EmployeeProfileActivity.this, EmployeeProfileUpdateActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        AG ht_inst = AG.getInstance();
        if (!ht_inst.getInitDone()) {
            Intent intent = new Intent(EmployeeProfileActivity.this, EmployeeHomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(EmployeeProfileActivity.this, EmployeeHomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }
}