package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.ProfileKeyAccount;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dao;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dbhelper;

public class KeyAccountProfile extends AppCompatActivity {

    TextView profileMobile, profilePassword, profileMail, profileOutletName, profileOwnerName;
    TextView profileWhatsapp, profileEmpStrength, profileAddress, profilePincode ;
    TextView profile_village_name, profile_village_code, profile_aadhaar_no, profile_gst_no, profile_pan_no, profile_bank_name;
    TextView profile_acccount_number, profile_branch, profile_ifsc_code;

    Button btnUpdateProfile;

    AG ht_inst = AG.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_account_profile);

        btnUpdateProfile = (Button) findViewById(R.id.btnUpdateProfile);

        profileMobile = findViewById(R.id.profileMobile);
        profilePassword = findViewById(R.id.profilePassword);
        profileMail = findViewById(R.id.profileMail);
        profileOutletName = findViewById(R.id.profileOutletName);
        profileOwnerName = findViewById(R.id.profileOwnerName);
        profileWhatsapp = findViewById(R.id.profileWhatsapp);
        profileEmpStrength = findViewById(R.id.profileEmpStrength);
        profileAddress = findViewById(R.id.profileAddress);
        profilePincode = findViewById(R.id.profilePincode);
        profile_village_name =  findViewById(R.id.profile_village_name);
        profile_village_code = findViewById(R.id.profile_village_code);
        profile_aadhaar_no = findViewById(R.id.profile_aadhaar_no);
        profile_gst_no = findViewById(R.id.profile_gst_no);
        profile_pan_no = findViewById(R.id.profile_pan_no);
        profile_bank_name = findViewById(R.id.profile_bank_name);
        profile_acccount_number = findViewById(R.id.profile_acccount_number);
        profile_branch = findViewById(R.id.profile_branch);
        profile_ifsc_code = findViewById(R.id.profile_ifsc_code);

        //Todo; Fetching the Profile Data value into the TextView
        profileMobile.setText(""+ ht_inst.getInstance().getUser().getkMobile());
        profilePassword.setText(""+ ht_inst.getInstance().getUser().getkPassword());

        profileMail.setText(""+ ht_inst.getInstance().getUser().getkMAil());
        profileOutletName.setText(""+ ht_inst.getInstance().getUser().getkOutletName());
        profileOwnerName.setText(""+ ht_inst.getInstance().getUser().getkOwnerName());
        profileWhatsapp.setText(""+ ht_inst.getInstance().getUser().getkWhatsappNo());
        profileEmpStrength.setText(""+ ht_inst.getInstance().getUser().getkEmployeeStrength());
        profileAddress.setText(""+ ht_inst.getInstance().getUser().getkAddress());
        profilePincode.setText(""+ ht_inst.getInstance().getUser().getkPincode());
        profile_village_code.setText(""+ ht_inst.getInstance().getUser().getkVillageCode());
        profile_village_name.setText(""+ ht_inst.getInstance().getUser().getkVillageName());
        profile_aadhaar_no.setText(""+ ht_inst.getInstance().getUser().getkAadharNumber());
        profile_gst_no.setText(""+ ht_inst.getInstance().getUser().getkGSTNumber());
        profile_pan_no.setText(""+ ht_inst.getInstance().getUser().getkPanNumber());
        profile_bank_name.setText(""+ ht_inst.getInstance().getUser().getkBankName());
        profile_acccount_number.setText(""+ ht_inst.getInstance().getUser().getkBankAccountNumber());
        profile_branch.setText(""+ ht_inst.getInstance().getUser().getkBranch());
        profile_ifsc_code.setText(""+ ht_inst.getInstance().getUser().getkIFSCCode());

        try {
            AsyncTask<String, Void, Integer> result = new ProfileKeyAccount().execute();
            int code = result.get();
            System.out.println("jyo reg code" + code);
            if (code == 200) {

                Dbhelper.createVendorDbHelper(KeyAccountProfile.this);
                Dao vendorDetailsDao = new Dao();
                vendorDetailsDao.getUserDetails();

               // Toast.makeText(KeyAccountProfile.this, "Restister Successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(KeyAccountProfile.this, HomeActivity.class);

                //  finish();
            } else {
                Toast.makeText(KeyAccountProfile.this, "Failed to Register", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {

            Toast.makeText(KeyAccountProfile.this, "Fail to Register", Toast.LENGTH_SHORT).show();
        }

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(KeyAccountProfile.this, KeyAccountProfileUpdateActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(KeyAccountProfile.this, KeyAccountHomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }
}