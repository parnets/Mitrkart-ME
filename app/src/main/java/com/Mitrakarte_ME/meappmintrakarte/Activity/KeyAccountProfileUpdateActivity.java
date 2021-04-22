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
import com.Mitrakarte_ME.meappmintrakarte.Service.KeyAccountRegister;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dao;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dbhelper;

public class KeyAccountProfileUpdateActivity extends AppCompatActivity {

    EditText etOutletName1K, etOwnerName2K, etWhatsAppNumber4K;
    TextView tvMailID5K;
    EditText etEmployeeStrength6K, etAddress7K, etPincode8K, etVillageName9K, etVillageCode10K;
    EditText etAadharNo11K, etGSTNo12K, etPanNo13K, etBankName14K, etBankAccountNumber15K;
    EditText etBranch16K,  etIFSCCode17K, etPassword18k;

    ImageView imgPhotoOfCompany18K;

    Button btnUpdateAccount;

    AG ht_inst = AG.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_account_profile_update);


        etOutletName1K = findViewById(R.id.etOutletName1K);
        etOwnerName2K = findViewById(R.id.etOwnerName2K);
        etWhatsAppNumber4K = findViewById(R.id.etWhatsAppNumber4K);

        tvMailID5K = findViewById(R.id.tvMailID5K);
        etEmployeeStrength6K = findViewById(R.id.etEmployeeStrength6K);
        etAddress7K = findViewById(R.id.etAddress7K);
        etPincode8K = findViewById(R.id.etPincode8K);
        etVillageName9K = findViewById(R.id.etVillageName9K);
        etVillageCode10K = findViewById(R.id.etVillageCode10K);
        etAadharNo11K = findViewById(R.id.etAadharNo11K);
        etGSTNo12K = findViewById(R.id.etGSTNo12K);
        etPanNo13K = findViewById(R.id.etPanNo13K);
        etBankName14K = findViewById(R.id.etBankName14K);
        etBankAccountNumber15K = findViewById(R.id.etBankAccountNumber15K);
        etBranch16K = findViewById(R.id.etBranch16K);
        etIFSCCode17K = findViewById(R.id.etIFSCCode17K);
        etPassword18k  = findViewById(R.id.etPassword18k);

        btnUpdateAccount  = findViewById(R.id.btnUpdateAccount);

        //Todo: Fetching all the user detail to edit (Update profile)
        etOutletName1K.setText(""+ ht_inst.getInstance().getUser().getkOutletName());
        etOwnerName2K.setText(""+ ht_inst.getInstance().getUser().getkOwnerName());
        etWhatsAppNumber4K.setText(""+ ht_inst.getInstance().getUser().getkWhatsappNo());

        tvMailID5K.setText(""+ ht_inst.getInstance().getUser().getkMAil());
        etEmployeeStrength6K.setText(""+ ht_inst.getInstance().getUser().getkEmployeeStrength());
        etAddress7K.setText(""+ ht_inst.getInstance().getUser().getkAddress());
        etPincode8K.setText(""+ ht_inst.getInstance().getUser().getkPincode());
        etVillageName9K.setText(""+ ht_inst.getInstance().getUser().getkVillageName());
        etVillageCode10K.setText(""+ ht_inst.getInstance().getUser().getkVillageCode());
        etAadharNo11K.setText(""+ ht_inst.getInstance().getUser().getkAadharNumber());
        etGSTNo12K.setText(""+ ht_inst.getInstance().getUser().getkGSTNumber());
        etPanNo13K.setText(""+ ht_inst.getInstance().getUser().getkPanNumber());
        etBankName14K.setText(""+ ht_inst.getInstance().getUser().getkBankName());
        etBankAccountNumber15K.setText(""+ ht_inst.getInstance().getUser().getkBankAccountNumber());
        etBranch16K.setText(""+ ht_inst.getInstance().getUser().getkBranch());
        etIFSCCode17K.setText(""+ ht_inst.getInstance().getUser().getkIFSCCode());
        etPassword18k.setText(""+ ht_inst.getInstance().getUser().getkPassword());



        btnUpdateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AG dg_inst = AG.getInstance();
                System.out.println("jyo name" + etOutletName1K);


                if (etOutletName1K.getText().toString().length() == 0) {
                    etOutletName1K.setError("Enter Outlet Name");
                    return;
                }
                dg_inst.getUser().setkOutletName(etOutletName1K.getText().toString());

                if (etOwnerName2K.getText().toString().length() == 0) {
                    etOwnerName2K.setError("Enter Owner Name");
                    return;
                }
                dg_inst.getUser().setkOwnerName(etOwnerName2K.getText().toString());

//                if (etPhoneNumber3K.getText().toString().length() == 0) {
//                    etPhoneNumber3K.setError("Enter Mobile Number");
//                    return;
//                }
//                if (etPhoneNumber3K.getText().toString().length() < 10) {
//                    etPhoneNumber3K.setError("Enter Valid Mobile Number");
//                    return;
//                }
//                dg_inst.getUser().setkMobile(etPhoneNumber3K.getText().toString());

                if (etWhatsAppNumber4K.getText().toString().length() == 0) {
                    etWhatsAppNumber4K.setError("Enter What's App Number");
                    return;
                }
                dg_inst.getUser().setkWhatsappNo(etWhatsAppNumber4K.getText().toString());

                if (tvMailID5K.getText().toString().length() == 0) {
                    tvMailID5K.setError("Enter Mail ID");
                    return;
                }
                dg_inst.getUser().setkMAil(tvMailID5K.getText().toString());

                if (etEmployeeStrength6K.getText().toString().length() == 0) {
                    etEmployeeStrength6K.setError("Enter Employee Strength");
                    return;
                }
                dg_inst.getUser().setkEmployeeStrength(etEmployeeStrength6K.getText().toString());

                if (etAddress7K.getText().toString().length() == 0) {
                    etAddress7K.setError("Enter Address");
                    return;
                }
                dg_inst.getUser().setkAddress(etAddress7K.getText().toString());

                if (etPincode8K.getText().toString().length() == 0) {
                    etPincode8K.setError("Enter Pincode");
                    return;
                }
                dg_inst.getUser().setkPincode(etPincode8K.getText().toString());


                if (etVillageName9K.getText().toString().length() == 0) {
                    etVillageName9K.setError("Enter Village name");
                    return;
                }
                dg_inst.getUser().setkVillageName(etVillageName9K.getText().toString());

                if (etVillageCode10K.getText().toString().length() == 0) {
                    etVillageCode10K.setError("Enter Village code");
                    return;
                }
                dg_inst.getUser().setkVillageCode(etVillageCode10K.getText().toString());

                if (etAadharNo11K.getText().toString().length() == 0) {
                    etAadharNo11K.setError("Enter Aadhar Number");
                    return;
                }
                dg_inst.getUser().setkAadharNumber(etAadharNo11K.getText().toString());


                if (etGSTNo12K.getText().toString().length() == 0) {
                    etGSTNo12K.setError("Enter GST Number");
                    return;
                }
                dg_inst.getUser().setkGSTNumber(etGSTNo12K.getText().toString());

                if (etPanNo13K.getText().toString().length() == 0) {
                    etPanNo13K.setError("Enter Pan Number");
                    return;
                }
                dg_inst.getUser().setkPanNumber(etPanNo13K.getText().toString());

                if (etBankName14K.getText().toString().length() == 0) {
                    etBankName14K.setError("Enter Bank Name");
                    return;
                }
                dg_inst.getUser().setkBankName(etBankName14K.getText().toString());


                if (etBankAccountNumber15K.getText().toString().length() == 0) {
                    etBankAccountNumber15K.setError("Enter Bank Account Number ");
                    return;
                }
                dg_inst.getUser().setkBankAccountNumber(etBankAccountNumber15K.getText().toString());

                if (etBranch16K.getText().toString().length() == 0) {
                    etBranch16K.setError("Enter Branch");
                    return;
                }
                dg_inst.getUser().setkBranch(etBranch16K.getText().toString());

                if (etIFSCCode17K.getText().toString().length() == 0) {
                    etIFSCCode17K.setError("Enter IFSC Code");
                    return;
                }
                dg_inst.getUser().setkIFSCCode(etIFSCCode17K.getText().toString());

                if (etPassword18k.getText().toString().length() == 0) {
                    etPassword18k.setError("Enter New Password ");
                    return;
                }
                dg_inst.getUser().setkPassword(etPassword18k.getText().toString());


                try {
                    AsyncTask<String, Void, Integer> result = new KeyAccountRegister().execute();
                    int code = result.get();
                    System.out.println("jyo reg code" + code);
                    if (code == 200) {

                        Dbhelper.createVendorDbHelper(KeyAccountProfileUpdateActivity.this);
                        Dao vendorDetailsDao = new Dao();
                        vendorDetailsDao.getUserDetails();

                        Toast.makeText(KeyAccountProfileUpdateActivity.this, "Profile Update Successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(KeyAccountProfileUpdateActivity.this, KeyAccountProfile.class);
                        startActivity(i);
                        //  finish();
                    } else {
                        Toast.makeText(KeyAccountProfileUpdateActivity.this, "Failed to Update", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {

                    Toast.makeText(KeyAccountProfileUpdateActivity.this, "Fail to Update", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        AG ht_inst = AG.getInstance();
        if (!ht_inst.getInitDone()) {
            Intent intent = new Intent(KeyAccountProfileUpdateActivity.this, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        super.onResume();
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(KeyAccountProfileUpdateActivity.this, KeyAccountProfile.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }
}