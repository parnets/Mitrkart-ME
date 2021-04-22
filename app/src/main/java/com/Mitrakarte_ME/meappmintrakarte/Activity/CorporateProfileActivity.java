package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;

public class CorporateProfileActivity extends AppCompatActivity {

    TextView profileMobile_cor, profilePassword_cor, profileMail_cor, profileOutletName_cor, profileOwnerName_cor;
    TextView profileWhatsapp_cor, profileEmpStrength_cor, profileAddress_cor, profilePincode_cor ;
    TextView profile_village_name_cor, profile_village_code_cor, profile_aadhaar_no_cor, profile_gst_no_cor;
    TextView    profile_pan_no_cor, profile_bank_name_cor;
    TextView profile_acccount_number_cor, profile_branch_cor, profile_ifsc_code_cor;

    Button btnUpdateProfile_cor;

    AG ht_inst = AG.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corporate_profile);

        btnUpdateProfile_cor = (Button) findViewById(R.id.btnUpdateProfile_cor);

        profileMobile_cor = findViewById(R.id.profileMobile_cor);
        profilePassword_cor = findViewById(R.id.profilePassword_cor);
        profileMail_cor = findViewById(R.id.profileMail_cor);
        profileOutletName_cor = findViewById(R.id.profileOutletName_cor);
        profileOwnerName_cor = findViewById(R.id.profileOwnerName_cor);
        profileWhatsapp_cor = findViewById(R.id.profileWhatsapp_cor);
        profileEmpStrength_cor = findViewById(R.id.profileEmpStrength_cor);
        profileAddress_cor = findViewById(R.id.profileAddress_cor);
        profilePincode_cor = findViewById(R.id.profilePincode_cor);
        profile_village_name_cor =  findViewById(R.id.profile_village_name_cor);
        profile_village_code_cor = findViewById(R.id.profile_village_code_cor);
        profile_aadhaar_no_cor = findViewById(R.id.profile_aadhaar_no_cor);
        profile_gst_no_cor = findViewById(R.id.profile_gst_no_cor);
        profile_pan_no_cor = findViewById(R.id.profile_pan_no_cor);
        profile_bank_name_cor = findViewById(R.id.profile_bank_name_cor);
        profile_acccount_number_cor = findViewById(R.id.profile_acccount_number_cor);
        profile_branch_cor = findViewById(R.id.profile_branch_cor);
        profile_ifsc_code_cor = findViewById(R.id.profile_ifsc_code_cor);

        //Todo; Fetching the Profile Data value into the TextView
        profileMobile_cor.setText(""+ ht_inst.getInstance().getUser().getCrmobile());
        profilePassword_cor.setText(""+ ht_inst.getInstance().getUser().getCrPassword());

        profileMail_cor.setText(""+ ht_inst.getInstance().getUser().getCrMailId());
        profileOutletName_cor.setText(""+ ht_inst.getInstance().getUser().getCrOutletName());
        profileOwnerName_cor.setText(""+ ht_inst.getInstance().getUser().getCrOwnerName());
        profileWhatsapp_cor.setText(""+ ht_inst.getInstance().getUser().getCrWhatsappNo());
        profileEmpStrength_cor.setText(""+ ht_inst.getInstance().getUser().getCrEmployeeStrength());
        profileAddress_cor.setText(""+ ht_inst.getInstance().getUser().getCrAddress());
        profilePincode_cor.setText(""+ ht_inst.getInstance().getUser().getCrPincode());
        profile_village_name_cor.setText(""+ ht_inst.getInstance().getUser().getCrVillageName());

        profile_village_code_cor.setText(""+ ht_inst.getInstance().getUser().getCrVillageCode());
        profile_aadhaar_no_cor.setText(""+ ht_inst.getInstance().getUser().getCrAadharNumber());
        profile_gst_no_cor.setText(""+ ht_inst.getInstance().getUser().getCrGSTNumber());
        profile_pan_no_cor.setText(""+ ht_inst.getInstance().getUser().getCrPanNumber());
        profile_bank_name_cor.setText(""+ ht_inst.getInstance().getUser().getCrBankName());
        profile_acccount_number_cor.setText(""+ ht_inst.getInstance().getUser().getCrBankAccountNumber());
        profile_branch_cor.setText(""+ ht_inst.getInstance().getUser().getCrBranch());
        profile_ifsc_code_cor.setText(""+ ht_inst.getInstance().getUser().getCrIFSCCode());


        btnUpdateProfile_cor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CorporateProfileActivity.this, CorporateProfileUpdateActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onResume() {
        AG ht_inst = AG.getInstance();
        if (!ht_inst.getInitDone()) {
            Intent intent = new Intent(CorporateProfileActivity.this, CorporateHomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        super.onResume();
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(CorporateProfileActivity.this, CorporateHomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }
}