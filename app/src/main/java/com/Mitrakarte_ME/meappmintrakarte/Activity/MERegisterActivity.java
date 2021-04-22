package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.MERegister;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dao;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dbhelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

public class MERegisterActivity extends AppCompatActivity {

    ImageView photoME;
    EditText etName1, etWhatsAppNo2, etfather_name3, etMailID4, etAddress5;
    EditText etVillageName6, etVillage_code7, etPincode8, etTaluka9, etDistrictName10;
    EditText etAadhaarNumber11, etEducationQualification12, etTwoWheelerDetail13, etTwoWheelerLicenseNo14, etThreeWheelerDetail15;
    EditText etFourWheelerDetail16, etandroidPhoneName17, etSalesExperience18, etBankName19, etBranch20;
    EditText etIFSCCode21, etAccountNumber22, etPassword24, et_photo_empty;

    Button btnRegister;
    //TextView text1,text2,text3,text4,text5;

    FusedLocationProviderClient fusedLocationProvideClient;

    private String encodedBase64String = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_register);

        photoME = findViewById(R.id.img_profile);

        etName1 = findViewById(R.id.etName1);
        etWhatsAppNo2 = findViewById(R.id.etWhatsAppNo2);
        etfather_name3 = findViewById(R.id.etfather_name3);
        etMailID4 = findViewById(R.id.etMailID4);
        etAddress5 = findViewById(R.id.etAddress5);
        etVillageName6 = findViewById(R.id.etVillageName6);
        etVillage_code7 = findViewById(R.id.etVillage_code7);
        etPincode8 = findViewById(R.id.etPincode8);
        etTaluka9 = findViewById(R.id.etTaluka9);
        etDistrictName10 = findViewById(R.id.etDistrictName10);
        etAadhaarNumber11 = findViewById(R.id.etAadhaarNumber11);
        etEducationQualification12 = findViewById(R.id.etEducationQualification12);
        etTwoWheelerDetail13 = findViewById(R.id.etTwoWheelerDetail13);
        etTwoWheelerLicenseNo14 = findViewById(R.id.etTwoWheelerLicenseNo14);
        etThreeWheelerDetail15 = findViewById(R.id.etThreeWheelerDetail15);
        etFourWheelerDetail16 = findViewById(R.id.etFourWheelerDetail16);
        etandroidPhoneName17 = findViewById(R.id.etandroidPhoneName17);
        etSalesExperience18 = findViewById(R.id.etSalesExperience18);
        etBankName19 = findViewById(R.id.etBankName19);
        etBranch20 = findViewById(R.id.etBranch20);
        etIFSCCode21 = findViewById(R.id.etIFSCCode21);
        etAccountNumber22 = findViewById(R.id.etAccountNumber22);

        etPassword24 = findViewById(R.id.etPassword24);


        photoME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 12);

                */

                final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

                AlertDialog.Builder builder = new AlertDialog.Builder(MERegisterActivity.this);
                builder.setTitle("Choose your profile picture");

                builder.setItems(options, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int item) {

                        if (options[item].equals("Take Photo")) {
                            Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(takePicture, 0);

                        } else if (options[item].equals("Choose from Gallery")) {
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, 1);

                        } else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }


        });


//        text1 = findViewById(R.id.text1);
//        text2= findViewById(R.id.text2);
//        text3 = findViewById(R.id.text3);
//        text4 = findViewById(R.id.text4);
//        text5 = findViewById(R.id.text5);

        btnRegister = findViewById(R.id.btnRegister);


        //Todo: getting number from MobileotpVerification
        Intent intent = getIntent();
        String str = intent.getStringExtra("mobile number");
        //etPermanentContactNo16.setText(str);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AG dg_inst = AG.getInstance();
                System.out.println("jyo name" + etName1);
                Matcher matcher = Patterns.EMAIL_ADDRESS.matcher(etMailID4.getText().toString());

                if (etName1.getText().toString().length() == 0) {

                    etName1.setError("Enter Firm Name");
                    return;
                } else if (etWhatsAppNo2.getText().toString().length() == 0) {

                    dg_inst.getUser().setMeName(etName1.getText().toString());
                    etWhatsAppNo2.setError("Enter WhatsApp Number");
                    return;
                } else if (etWhatsAppNo2.getText().toString().length() < 10) {
                    etWhatsAppNo2.setError("Enter 10 Digit Number");
                    return;
                } else if (etfather_name3.getText().toString().length() == 0) {

                    dg_inst.getUser().setWhatsappNo(etWhatsAppNo2.getText().toString());
                    etfather_name3.setError("Enter Father name");
                    return;
                } else if (etMailID4.getText().toString().length() == 0) {

                    dg_inst.getUser().setFatherName(etfather_name3.getText().toString());
                    etMailID4.setError("Enter Mail ID");
                    return;
                } else if (!matcher.matches()) {
                    etMailID4.setError("Invalid Email Address");
                    return;
                } else if (etAddress5.getText().toString().length() == 0) {

                    dg_inst.getUser().setMailID(etMailID4.getText().toString());
                    etAddress5.setError("Enter Address");
                    return;
                } else if (etVillageName6.getText().toString().length() == 0) {

                    dg_inst.getUser().setAddress(etAddress5.getText().toString());
                    etVillageName6.setError("Enter Village Name");
                    return;
                } else if (etPincode8.getText().toString().length() == 0) {

                    dg_inst.getUser().setVillageName(etVillageName6.getText().toString());
                    dg_inst.getUser().setVillage_code(etVillage_code7.getText().toString());
                    etPincode8.setError("Enter Pincode");
                    return;
                } else if (etPincode8.getText().toString().length() < 6) {
                    etPincode8.setError("Enter Correct Pincode");
                    return;
                } else if (etTaluka9.getText().toString().length() == 0) {

                    dg_inst.getUser().setPincode(etPincode8.getText().toString());
                    etTaluka9.setError("Enter Taluk Name");
                    return;
                } else if (etDistrictName10.getText().toString().length() == 0) {

                    dg_inst.getUser().setTaluka(etTaluka9.getText().toString());
                    etDistrictName10.setError("Enter  District Nam");
                    return;
                } else if (etAadhaarNumber11.getText().toString().length() == 0) {

                    dg_inst.getUser().setDistrictName(etDistrictName10.getText().toString());
                    etAadhaarNumber11.setError("Enter Aadhaar Number");
                    return;
                } else if (etAadhaarNumber11.getText().toString().length() < 12) {
                    etAadhaarNumber11.setError("Enter 12 Digit Aadhaar Number");
                    return;
                } else if (etEducationQualification12.getText().toString().length() == 0) {

                    dg_inst.getUser().setAadhaarNo(etAadhaarNumber11.getText().toString());
                    etEducationQualification12.setError("Enter Education Qualification");
                    return;
                } else if (etTwoWheelerDetail13.getText().toString().length() == 0) {

                    dg_inst.getUser().setEducationQualification(etEducationQualification12.getText().toString());
                    etTwoWheelerDetail13.setError("Enter Two Wheeler Detail");
                    return;
                } else if (etTwoWheelerLicenseNo14.getText().toString().length() == 0) {

                    dg_inst.getUser().setTwoWheelerDetail(etTwoWheelerDetail13.getText().toString());
                    etTwoWheelerLicenseNo14.setError("Enter Two Wheeler License Number");
                    return;
                } else if (etThreeWheelerDetail15.getText().toString().length() == 0) {

                    dg_inst.getUser().setTwoWheelerLicenseNo(etTwoWheelerLicenseNo14.getText().toString());
                    etThreeWheelerDetail15.setError("Enter Three Wheeler Detail");
                    return;
                } else if (etFourWheelerDetail16.getText().toString().length() == 0) {

                    dg_inst.getUser().setThreeWheelerDetail(etThreeWheelerDetail15.getText().toString());
                    etFourWheelerDetail16.setError("Enter Four Wheeler Detail");
                    return;
                } else if (etandroidPhoneName17.getText().toString().length() == 0) {

                    dg_inst.getUser().setEtFourWheelerDetail16(etFourWheelerDetail16.getText().toString());
                    etandroidPhoneName17.setError("Enter Android Phone Name");
                    return;
                } else if (etSalesExperience18.getText().toString().length() == 0) {

                    dg_inst.getUser().setAndroidPhoneName(etandroidPhoneName17.getText().toString());
                    etSalesExperience18.setError("Enter Sales Experience");
                    return;
                } else if (etBankName19.getText().toString().length() == 0) {

                    dg_inst.getUser().setSalesExperience(etSalesExperience18.getText().toString());
                    etBankName19.setError("Enter  Bank Name");
                    return;
                } else if (etBranch20.getText().toString().length() == 0) {

                    dg_inst.getUser().setBankName(etBankName19.getText().toString());
                    etBranch20.setError("Enter Branch");
                    return;
                } else if (etIFSCCode21.getText().toString().length() == 0) {

                    dg_inst.getUser().setBranch(etBranch20.getText().toString());
                    etIFSCCode21.setError("Enter IFSC Code ");
                    return;
                } else if (etAccountNumber22.getText().toString().length() == 0) {
                    dg_inst.getUser().setIfscCode(etIFSCCode21.getText().toString());
                    etAccountNumber22.setError("Enter Account Number");
                    return;
                } else if (photoME.getDrawable() != null && !encodedBase64String.isEmpty()) {

                    dg_inst.getUser().setAccountNumber(etAccountNumber22.getText().toString());
                    dg_inst.getUser().setMePhoto(encodedBase64String);

                } else if (photoME.getDrawable() == null && encodedBase64String.isEmpty()) {
                    Toast.makeText(MERegisterActivity.this, "Add Photo", Toast.LENGTH_LONG).show();
                } else if (etPassword24.getText().toString().length() == 0) {
                    etPassword24.setError("Enter Password ");
                    return;
                } else {


                    dg_inst.getUser().setMeName(etName1.getText().toString());
                    dg_inst.getUser().setWhatsappNo(etWhatsAppNo2.getText().toString());
                    dg_inst.getUser().setFatherName(etfather_name3.getText().toString());
                    dg_inst.getUser().setMailID(etMailID4.getText().toString());
                    dg_inst.getUser().setAddress(etAddress5.getText().toString());
                    dg_inst.getUser().setVillageName(etVillageName6.getText().toString());
                    dg_inst.getUser().setVillage_code(etVillage_code7.getText().toString());
                    dg_inst.getUser().setPincode(etPincode8.getText().toString());
                    dg_inst.getUser().setTaluka(etTaluka9.getText().toString());
                    dg_inst.getUser().setDistrictName(etDistrictName10.getText().toString());
                    dg_inst.getUser().setAadhaarNo(etAadhaarNumber11.getText().toString());
                    dg_inst.getUser().setEducationQualification(etEducationQualification12.getText().toString());
                    dg_inst.getUser().setTwoWheelerDetail(etTwoWheelerDetail13.getText().toString());
                    dg_inst.getUser().setTwoWheelerLicenseNo(etTwoWheelerLicenseNo14.getText().toString());
                    dg_inst.getUser().setThreeWheelerDetail(etThreeWheelerDetail15.getText().toString());
                    dg_inst.getUser().setEtFourWheelerDetail16(etFourWheelerDetail16.getText().toString());
                    dg_inst.getUser().setAndroidPhoneName(etandroidPhoneName17.getText().toString());
                    dg_inst.getUser().setSalesExperience(etSalesExperience18.getText().toString());
                    dg_inst.getUser().setBankName(etBankName19.getText().toString());
                    dg_inst.getUser().setBranch(etBranch20.getText().toString());
                    dg_inst.getUser().setIfscCode(etIFSCCode21.getText().toString());
                    dg_inst.getUser().setAccountNumber(etAccountNumber22.getText().toString());
                    dg_inst.getUser().setMePhoto(encodedBase64String);
                    dg_inst.getUser().setPassword(etPassword24.getText().toString());

                    try {
                        AsyncTask<String, Void, Integer> result = new MERegister().execute();
                        int code = result.get();
                        System.out.println("jyo reg code" + code);
                        if (code == 200) {

                            Dbhelper.createVendorDbHelper(MERegisterActivity.this);
                            Dao vendorDetailsDao = new Dao();
                            vendorDetailsDao.getUserDetails();

                            Toast.makeText(MERegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MERegisterActivity.this, MELoginActivity.class);
                            startActivity(i);
                            //  finish();
                        } else if (code == 202) {
                            Toast.makeText(MERegisterActivity.this, "Provide complete information to Register", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(MERegisterActivity.this, "Failed to Register", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {

                        Toast.makeText(MERegisterActivity.this, "Fail to Register", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        fusedLocationProvideClient = LocationServices.getFusedLocationProviderClient(this);


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
                        Geocoder geocoder = new Geocoder(MERegisterActivity.this, Locale.getDefault());

                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {

            try {


                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap imageBitmap = BitmapFactory.decodeStream(imageStream);
                photoME.setImageBitmap(imageBitmap);

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                encodedBase64String = Base64.encodeToString(byteArray, Base64.DEFAULT);
            } catch (Exception e) {

            }
        } else if (requestCode == 0 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            photoME.setImageBitmap(imageBitmap);
            //Todo: Converting image to base 64 to store to database
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            encodedBase64String = Base64.encodeToString(byteArray, Base64.DEFAULT);
        }

    }


    @Override
    protected void onResume() {
        AG ht_inst = AG.getInstance();
        if (!ht_inst.getInitDone()) {
            Intent intent = new Intent(MERegisterActivity.this, MELoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        super.onResume();
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(MERegisterActivity.this, MELoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        super.onBackPressed();
    }
}



