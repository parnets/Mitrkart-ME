package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.CorporateRegister;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dao;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dbhelper;

public class CorporateProfileUpdateActivity extends AppCompatActivity {

    EditText etOutletName1_cor, etOwnerName2_cor, etWhatsAppNumber4_cor;
    TextView tvMailID5_cor;
    EditText etEmployeeStrength6_cor, etAddress7_cor, etPincode8_cor, etVillageName9_cor, etVillageCode10_cor;
    EditText etAadharNo11_cor, etGSTNo12_cor, etPanNo13_cor, etBankName14_cor, etBankAccountNumber15_cor;
    EditText etBranch16_cor,  etIFSCCode17_cor, etPassword18_cor;

    ImageView imgPhotoOfCompany18K;

    Button btnUpdateAccount_cor;

    AG ht_inst = AG.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corporate_profile_update);



        etOutletName1_cor = findViewById(R.id.etOutletName1_cor);
        etOwnerName2_cor = findViewById(R.id.etOwnerName2_cor);
        etWhatsAppNumber4_cor = findViewById(R.id.etWhatsAppNumber4_cor);

        tvMailID5_cor = findViewById(R.id.tvMailID5_cor);
        etEmployeeStrength6_cor = findViewById(R.id.etEmployeeStrength6_cor);
        etAddress7_cor = findViewById(R.id.etAddress7_cor);
        etPincode8_cor = findViewById(R.id.etPincode8_cor);
        etVillageName9_cor = findViewById(R.id.etVillageName9_cor);
        etVillageCode10_cor = findViewById(R.id.etVillageCode10_cor);
        etAadharNo11_cor = findViewById(R.id.etAadharNo11_cor);
        etAadharNo11_cor = findViewById(R.id.etAadharNo11_cor);
        etGSTNo12_cor = findViewById(R.id.etGSTNo12_cor);
        etPanNo13_cor = findViewById(R.id.etPanNo13_cor);
        etBankName14_cor = findViewById(R.id.etBankName14_cor);
        etBankAccountNumber15_cor = findViewById(R.id.etBankAccountNumber15_cor);
        etBranch16_cor = findViewById(R.id.etBranch16_cor);
        etIFSCCode17_cor  = findViewById(R.id.etIFSCCode17_cor);
        etPassword18_cor = findViewById(R.id.etPassword18_cor);

        btnUpdateAccount_cor  = findViewById(R.id.btnUpdateAccount_cor);

        //Todo: Fetching all the user detail to edit (Update profile)
        etOutletName1_cor.setText(""+ ht_inst.getInstance().getUser().getCrOutletName());
        etOwnerName2_cor.setText(""+ ht_inst.getInstance().getUser().getCrOwnerName());
        etWhatsAppNumber4_cor.setText(""+ ht_inst.getInstance().getUser().getCrWhatsappNo());
        tvMailID5_cor.setText(""+ ht_inst.getInstance().getUser().getCrMailId());
        etEmployeeStrength6_cor.setText(""+ ht_inst.getInstance().getUser().getCrEmployeeStrength());
        etAddress7_cor.setText(""+ ht_inst.getInstance().getUser().getCrAddress());
        etPincode8_cor.setText(""+ ht_inst.getInstance().getUser().getCrPincode());

        etVillageName9_cor.setText(""+ ht_inst.getInstance().getUser().getCrVillageName());
        etVillageCode10_cor.setText(""+ ht_inst.getInstance().getUser().getCrVillageCode());
        etAadharNo11_cor.setText(""+ ht_inst.getInstance().getUser().getCrAadharNumber());


        etGSTNo12_cor.setText(""+ ht_inst.getInstance().getUser().getCrGSTNumber());
        etPanNo13_cor.setText(""+ ht_inst.getInstance().getUser().getCrPanNumber());
        etBankName14_cor.setText(""+ ht_inst.getInstance().getUser().getCrBankName());
        etBankAccountNumber15_cor.setText(""+ ht_inst.getInstance().getUser().getCrBankAccountNumber());
        etBranch16_cor.setText(""+ ht_inst.getInstance().getUser().getCrBranch());
        etIFSCCode17_cor.setText(""+ ht_inst.getInstance().getUser().getCrIFSCCode());
        etPassword18_cor.setText(""+ ht_inst.getInstance().getUser().getCrPassword());


        btnUpdateAccount_cor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AG dg_inst = AG.getInstance();
                System.out.println("jyo name" + etOutletName1_cor);

                if (etOutletName1_cor.getText().toString().length() == 0) {
                    etOutletName1_cor.setError("Enter Outlet Name");
                    return;
                }
                dg_inst.getUser().setCrOutletName(etOutletName1_cor.getText().toString());

                if (etOwnerName2_cor.getText().toString().length() == 0) {
                    etOwnerName2_cor.setError("Enter Owner Name");
                    return;
                }
                dg_inst.getUser().setCrOwnerName(etOwnerName2_cor.getText().toString());

//                if (etPhoneNumber3CR.getText().toString().length() == 0) {
//                    etPhoneNumber3CR.setError("Enter Mobile Number");
//                    return;
//                }
//                if (etPhoneNumber3CR.getText().toString().length() < 10) {
//                    etPhoneNumber3CR.setError("Enter Valid Mobile Number");
//                    return;
//                }
//                dg_inst.getUser().setCrMobile(etPhoneNumber3CR.getText().toString());

                if (etWhatsAppNumber4_cor.getText().toString().length() == 0) {
                    etWhatsAppNumber4_cor.setError("Enter What's App Number");
                    return;
                }
                dg_inst.getUser().setCrWhatsappNo(etWhatsAppNumber4_cor.getText().toString());

                if (tvMailID5_cor.getText().toString().length() == 0) {
                    tvMailID5_cor.setError("Enter Mail ID");
                    return;
                }
                dg_inst.getUser().setCrMailId(tvMailID5_cor.getText().toString());

                if (etEmployeeStrength6_cor.getText().toString().length() == 0) {
                    etEmployeeStrength6_cor.setError("Enter Employee Strength");
                    return;
                }
                dg_inst.getUser().setCrEmployeeStrength(etEmployeeStrength6_cor.getText().toString());

                if (etAddress7_cor.getText().toString().length() == 0) {
                    etAddress7_cor.setError("Enter Address");
                    return;
                }
                dg_inst.getUser().setCrAddress(etAddress7_cor.getText().toString());

                if (etPincode8_cor.getText().toString().length() == 0) {
                    etPincode8_cor.setError("Enter Pincode");
                    return;
                }
                dg_inst.getUser().setCrPincode(etPincode8_cor.getText().toString());


                if (etVillageName9_cor.getText().toString().length() == 0) {
                    etVillageName9_cor.setError("Enter Village name");
                    return;
                }
                dg_inst.getUser().setCrVillageName(etVillageName9_cor.getText().toString());

//                if (etVillageCode10_cor.getText().toString().length() == 0) {
//                    etVillageCode10_cor.setError("Enter Village code");
//                    return;
//                }
                dg_inst.getUser().setCrVillageCode(etVillageCode10_cor.getText().toString());

                if (etAadharNo11_cor.getText().toString().length() == 0) {
                    etAadharNo11_cor.setError("Enter Aadhar Number");
                    return;
                }
                dg_inst.getUser().setCrAadharNumber(etAadharNo11_cor.getText().toString());


                if (etGSTNo12_cor.getText().toString().length() == 0) {
                    etGSTNo12_cor.setError("Enter GST Number");
                    return;
                }
                dg_inst.getUser().setCrGSTNumber(etGSTNo12_cor.getText().toString());

                if (etPanNo13_cor.getText().toString().length() == 0) {
                    etPanNo13_cor.setError("Enter Pan Number");
                    return;
                }
                dg_inst.getUser().setCrPanNumber(etPanNo13_cor.getText().toString());

                if (etBankName14_cor.getText().toString().length() == 0) {
                    etBankName14_cor.setError("Enter Bank Name");
                    return;
                }
                dg_inst.getUser().setCrBankName(etBankName14_cor.getText().toString());


                if (etBankAccountNumber15_cor.getText().toString().length() == 0) {
                    etBankAccountNumber15_cor.setError("Enter Bank Account Number ");
                    return;
                }
                dg_inst.getUser().setCrBankAccountNumber(etBankAccountNumber15_cor.getText().toString());

                if (etBranch16_cor.getText().toString().length() == 0) {
                    etBranch16_cor.setError("Enter Branch");
                    return;
                }
                dg_inst.getUser().setCrBranch(etBranch16_cor.getText().toString());

                if (etIFSCCode17_cor.getText().toString().length() == 0) {
                    etIFSCCode17_cor.setError("Enter IFSC Code");
                    return;
                }
                dg_inst.getUser().setCrIFSCCode(etIFSCCode17_cor.getText().toString());


                if (etPassword18_cor.getText().toString().length() == 0) {
                    etPassword18_cor.setError("Enter IFSC Code");
                    return;
                }
                dg_inst.getUser().setCrPassword(etPassword18_cor.getText().toString());

                try {
                    AsyncTask<String, Void, Integer> result = new CorporateRegister().execute();
                    int code = result.get();
                    System.out.println("jyo reg code" + code);
                    if (code == 200) {

                        Dbhelper.createVendorDbHelper(CorporateProfileUpdateActivity.this);
                        Dao vendorDetailsDao = new Dao();
                        vendorDetailsDao.getUserDetails();

                        Toast.makeText(CorporateProfileUpdateActivity.this, "Update Successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(CorporateProfileUpdateActivity.this, CorporateProfileActivity.class);
                        startActivity(i);
                        //  finish();
                    } else {
                        Toast.makeText(CorporateProfileUpdateActivity.this, "Failed to Update", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {

                    Toast.makeText(CorporateProfileUpdateActivity.this, "Fail to Update", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        AG ht_inst = AG.getInstance();
        if (!ht_inst.getInitDone()) {
            Intent intent = new Intent(CorporateProfileUpdateActivity.this, CorporateProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        super.onResume();
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(CorporateProfileUpdateActivity.this, CorporateProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }
}