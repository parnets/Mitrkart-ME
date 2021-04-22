package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.RetailerRegister;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dao;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dbhelper;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

public class RetailerRegisterActivity extends AppCompatActivity {

    ImageView photo_Retailer1R;
    EditText etFirmName1R, etProprietorPartnership2R, etProprietorPartnersName3R, etTypeOfOutlet4R, etGSTNo5R;
    EditText etAadharNo6R, etPanNo7R, etVillageName8R, etVillageCode9R, etTaluk10R;
    EditText etDistrictName11R, et1State12R, etPincode13R, etAddressWithLandmark14R, etResidentialAddress15R;
    EditText etPermanentContactNo16R, etAlternateContactNo17R, etWhatsAppNo18R, etMailID19R, etBankName20R;
    EditText etAccountNumber21R, etIFSCCode22R, etBranch23R;
    // EditText etFingerPrint24;
    CheckBox chkAccpetTerms24R;
    TextView LiveLocation26R, tv_TermsAndConditions25R;
    EditText etPassword27R;


    Button btnRegister;

    TextView text1, text2, text3, text4, text5;

    FusedLocationProviderClient fusedLocationProvideClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_register2);

        //      photo_Retailer1R = findViewById(R.id.photo_Retailer1R);

        etFirmName1R = findViewById(R.id.etFirmName1R);
        etProprietorPartnership2R = findViewById(R.id.etProprietorPartnership2R);
        etProprietorPartnersName3R = findViewById(R.id.etProprietorPartnersName3R);
        etTypeOfOutlet4R = findViewById(R.id.etTypeOfOutlet4R);
        etGSTNo5R = findViewById(R.id.etGSTNo5R);
        etAadharNo6R = findViewById(R.id.etAadharNo6R);
        etPanNo7R = findViewById(R.id.etPanNo7R);
        etVillageName8R = findViewById(R.id.etVillageName8R);
        etVillageCode9R = findViewById(R.id.etVillageCode9R);
        etTaluk10R = findViewById(R.id.etTaluk10R);
        etDistrictName11R = findViewById(R.id.etDistrictName11R);
        et1State12R = findViewById(R.id.et1State12R);
        etPincode13R = findViewById(R.id.etPincode13R);
        etAddressWithLandmark14R = findViewById(R.id.etAddressWithLandmark14R);
        etResidentialAddress15R = findViewById(R.id.etResidentialAddress15R);
        //    etPermanentContactNo16R = findViewById(R.id.etPermanentContactNo16R);
        etAlternateContactNo17R = findViewById(R.id.etAlternateContactNo17R);
        etWhatsAppNo18R = findViewById(R.id.etWhatsAppNo18R);
        etMailID19R = findViewById(R.id.etMailID19R);
        etBankName20R = findViewById(R.id.etBankName20R);
        etAccountNumber21R = findViewById(R.id.etAccountNumber21R);
        etIFSCCode22R = findViewById(R.id.etIFSCCode22R);
        etBranch23R = findViewById(R.id.etBranch23R);
        // etFingerPrint24 = findViewById(R.id.etFingerPrint24);

        chkAccpetTerms24R = findViewById(R.id.chkAccpetTerms24R);
        tv_TermsAndConditions25R = findViewById(R.id.tv_TermsAndConditions25R);
        LiveLocation26R = findViewById(R.id.LiveLocation26R);
        etPassword27R = findViewById(R.id.etPassword27R);


        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);

        btnRegister = findViewById(R.id.btnRetailerRegister);


        //Todo: getting number from MobileotpVerification
//        Intent intent = getIntent();
//        String str = intent.getStringExtra("mobile number");
//        etPermanentContactNo16R.setText(str);

        tv_TermsAndConditions25R.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RetailerRegisterActivity.this, TermsConditionActivityRetailer.class);
                startActivity(i);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AG dg_inst = AG.getInstance();
                System.out.println("jyo name" + etFirmName1R);
                Matcher matcher = Patterns.EMAIL_ADDRESS.matcher(etMailID19R.getText().toString());


                if (etFirmName1R.getText().toString().length() == 0) {
                    etFirmName1R.setError("Enter Firm Name");
                    return;
                }
                else if (etProprietorPartnership2R.getText().toString().length() == 0) {

                    dg_inst.getUser().setrFirmName(etFirmName1R.getText().toString());
                    etProprietorPartnership2R.setError("Enter Proprietor/Partnership ");
                    return;
                }


               else if (etProprietorPartnersName3R.getText().toString().length() == 0) {

                    dg_inst.getUser().setrPartnership(etProprietorPartnership2R.getText().toString());
                    etProprietorPartnersName3R.setError("Enter Partners  Name");
                    return;
                }


                else if (etTypeOfOutlet4R.getText().toString().length() == 0) {

                    dg_inst.getUser().setrPartnername(etProprietorPartnersName3R.getText().toString());
                    etTypeOfOutlet4R.setError("Enter  Type Of Outlet");
                    return;
                }


                else if (etGSTNo5R.getText().toString().length() == 0) {

                    dg_inst.getUser().setrTypeofoutlet(etTypeOfOutlet4R.getText().toString());
                    etGSTNo5R.setError("Enter GST No");
                    return;
                }


                else if (etAadharNo6R.getText().toString().length() == 0) {

                    dg_inst.getUser().setrGstNo(etGSTNo5R.getText().toString());
                    etAadharNo6R.setError("Enter Aadhar Number");
                    return;
                }
                else if (etAadharNo6R.getText().toString().length() < 12) {
                    etAadharNo6R.setError("Enter 12 Digit Aadhar Number");
                    return;
                }

                else if (etPanNo7R.getText().toString().length() == 0) {

                    dg_inst.getUser().setrAadhaarNo(etAadharNo6R.getText().toString());
                    etPanNo7R.setError("Enter Pan Number");
                    return;
                }



                else if (etVillageName8R.getText().toString().length() == 0) {

                    dg_inst.getUser().setrPanNo(etPanNo7R.getText().toString());
                    etVillageName8R.setError("Enter Village name");
                    return;
                }


                else if (etTaluk10R.getText().toString().length() == 0) {

                    dg_inst.getUser().setrVillageName(etVillageName8R.getText().toString());
                    dg_inst.getUser().setrVillage_code(etVillageCode9R.getText().toString());
                    etTaluk10R.setError("Enter Taluka");
                    return;
                }


                else if (etDistrictName11R.getText().toString().length() == 0) {

                    dg_inst.getUser().setrTaluka(etTaluk10R.getText().toString());
                    etDistrictName11R.setError("Enter District Name");
                    return;
                }


                else if (et1State12R.getText().toString().length() == 0) {

                    dg_inst.getUser().setrDistrictName(etDistrictName11R.getText().toString());
                    et1State12R.setError("Enter State");
                    return;
                }


                else if (etPincode13R.getText().toString().length() == 0) {

                    dg_inst.getUser().setrState(et1State12R.getText().toString());
                    etPincode13R.setError("Enter Pincode");
                    return;
                }
                else if (etPincode13R.getText().toString().length() < 6) {
                    etPincode13R.setError("Enter Correct Pincode");
                    return;
                }


                else if (etAddressWithLandmark14R.getText().toString().length() == 0) {

                    dg_inst.getUser().setrPincode(etPincode13R.getText().toString());
                    etAddressWithLandmark14R.setError("Enter Address with Landmark");
                    return;
                }


                else if (etResidentialAddress15R.getText().toString().length() == 0) {

                    dg_inst.getUser().setrAddressWithLandmark(etAddressWithLandmark14R.getText().toString());
                    etResidentialAddress15R.setError("Enter Residential Address");
                    return;
                }



                else if (etAlternateContactNo17R.getText().toString().length() == 0) {

                    dg_inst.getUser().setrResidentialAddress(etResidentialAddress15R.getText().toString());
                    etAlternateContactNo17R.setError("Enter Alternate Contact Number ");
                    return;
                }
                else if (etAlternateContactNo17R.getText().toString().length() < 10) {
                    etAlternateContactNo17R.setError("Enter 10 Digit Number ");
                    return;
                }


                else if (etWhatsAppNo18R.getText().toString().length() == 0) {

                    dg_inst.getUser().setrAlternateContactNo(etAlternateContactNo17R.getText().toString());
                    etWhatsAppNo18R.setError("Enter What's app   Number");
                    return;
                }

                else if (etWhatsAppNo18R.getText().toString().length() < 10) {
                    etWhatsAppNo18R.setError("Enter 10 Digit  Number");
                    return;
                }




                else if (etMailID19R.getText().toString().length() == 0) {

                    dg_inst.getUser().setrWhatsappNo(etWhatsAppNo18R.getText().toString());


                    etMailID19R.setError("Enter Mail ID");
                    return;
                }
                else if (!matcher.matches()) {
                    etMailID19R.setError("Invalid Email Address");
                    return;
                }

                else if (etPassword27R.getText().toString().length() == 0) {

                    dg_inst.getUser().setrEmail(etMailID19R.getText().toString());


                    dg_inst.getUser().setrBankName(etBankName20R.getText().toString());
                    dg_inst.getUser().setrAccountNumber(etAccountNumber21R.getText().toString());
                    dg_inst.getUser().setrIfscCode(etIFSCCode22R.getText().toString());
                    dg_inst.getUser().setrBranch(etBranch23R.getText().toString());
                    etPassword27R.setError("Enter New Password");
                    return;
                }


                else if (!chkAccpetTerms24R.isChecked()) {

                    dg_inst.getUser().setrPassword(etPassword27R.getText().toString());
                    Toast.makeText(RetailerRegisterActivity.this, "Please Accept Terms And Condition", Toast.LENGTH_SHORT).show();
                }

                else if (text1.getText().toString().length() == 0) {

                    Toast.makeText(RetailerRegisterActivity.this, "Add  Location", Toast.LENGTH_SHORT).show();

                }
                else {


                    dg_inst.getUser().setrFirmName(etFirmName1R.getText().toString());
                    dg_inst.getUser().setrPartnership(etProprietorPartnership2R.getText().toString());
                    dg_inst.getUser().setrPartnername(etProprietorPartnersName3R.getText().toString());
                    dg_inst.getUser().setrTypeofoutlet(etTypeOfOutlet4R.getText().toString());
                    dg_inst.getUser().setrGstNo(etGSTNo5R.getText().toString());
                    dg_inst.getUser().setrAadhaarNo(etAadharNo6R.getText().toString());
                    dg_inst.getUser().setrPanNo(etPanNo7R.getText().toString());
                    dg_inst.getUser().setrVillageName(etVillageName8R.getText().toString());
                    dg_inst.getUser().setrVillage_code(etVillageCode9R.getText().toString());
                    dg_inst.getUser().setrTaluka(etTaluk10R.getText().toString());
                    dg_inst.getUser().setrDistrictName(etDistrictName11R.getText().toString());
                    dg_inst.getUser().setrState(et1State12R.getText().toString());
                    dg_inst.getUser().setrPincode(etPincode13R.getText().toString());
                    dg_inst.getUser().setrAddressWithLandmark(etAddressWithLandmark14R.getText().toString());
                    dg_inst.getUser().setrResidentialAddress(etResidentialAddress15R.getText().toString());
                    dg_inst.getUser().setrAlternateContactNo(etAlternateContactNo17R.getText().toString());
                    dg_inst.getUser().setrWhatsappNo(etWhatsAppNo18R.getText().toString());
                    dg_inst.getUser().setrEmail(etMailID19R.getText().toString());
                    dg_inst.getUser().setrBankName(etBankName20R.getText().toString());
                    dg_inst.getUser().setrAccountNumber(etAccountNumber21R.getText().toString());
                    dg_inst.getUser().setrIfscCode(etIFSCCode22R.getText().toString());
                    dg_inst.getUser().setrBranch(etBranch23R.getText().toString());
                    dg_inst.getUser().setrPassword(etPassword27R.getText().toString());
                    dg_inst.getUser().setrLatitude(text1.getText().toString());
                    dg_inst.getUser().setrLongitude(text2.getText().toString());


                    try {
                        AsyncTask<String, Void, Integer> result = new RetailerRegister().execute();
                        int code = result.get();
                        System.out.println("jyo reg code" + code);
                        if (code == 200) {

                            Dbhelper.createVendorDbHelper(RetailerRegisterActivity.this);
                            Dao vendorDetailsDao = new Dao();
                            vendorDetailsDao.getUserDetails();

                            Toast.makeText(RetailerRegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(RetailerRegisterActivity.this, HomeActivity.class);
                            startActivity(i);
                            //  finish();
                        } else if (code == 202) {
                            Toast.makeText(RetailerRegisterActivity.this, "Provide complete information to Register", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(RetailerRegisterActivity.this, "Failed to Register", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {

                        Toast.makeText(RetailerRegisterActivity.this, "Fail to Register", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        fusedLocationProvideClient = LocationServices.getFusedLocationProviderClient(this);


        // Todo : getting Live location

        LiveLocation26R.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(RetailerRegisterActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {

                    ActivityCompat.requestPermissions(RetailerRegisterActivity.this
                            , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);

                }
            }
        });


    }


    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        fusedLocationProvideClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {

                Location location = task.getResult();

                if (location != null) {


                    try {
                        Geocoder geocoder = new Geocoder(RetailerRegisterActivity.this, Locale.getDefault());

                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );


                        text1.setText(Html.fromHtml(
                                "<font color = '#162252'><b><Latitude :</b></font>"
                                        + addresses.get(0).getLatitude()
                        ));

                        text2.setText(Html.fromHtml(
                                "<font color = '#162252'><b><Longitude :</b></font>"
                                        + addresses.get(0).getLongitude()
                        ));

                        text3.setText(Html.fromHtml(
                                "<font color = '#162252'><b><Country Name :</b></font>"
                                        + addresses.get(0).getCountryName()
                        ));
                        text4.setText(Html.fromHtml(
                                "<font color = '#162252'><b><Locality :</b></font>"
                                        + addresses.get(0).getLocality()
                        ));


                        text5.setText(Html.fromHtml(
                                "<font color = '#ba2255'><b><Address :</b></font>"
                                        + addresses.get(0).getAddressLine(0)
                        ));


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        });
    }

    @Override
    protected void onResume() {
        AG ht_inst = AG.getInstance();
        if (!ht_inst.getInitDone()) {
            Intent intent = new Intent(RetailerRegisterActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        super.onResume();
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(RetailerRegisterActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        super.onBackPressed();
    }


}