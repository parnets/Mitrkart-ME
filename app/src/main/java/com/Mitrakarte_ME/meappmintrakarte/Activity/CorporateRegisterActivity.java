package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.CorporateRegister;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dao;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dbhelper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.regex.Matcher;

public class CorporateRegisterActivity extends AppCompatActivity {

    EditText etOutletName1CR, etOwnerName2CR, etWhatsAppNumber4CR, etMailID5CR;
    EditText etEmployeeStrength6CR, etAddress7CR, etPincode8CR, etVillageName9CR, etVillageCode10CR;
    EditText etAadharNo11CR, etGSTNo12CR, etPanNo13CR, etBankName14CR, etBankAccountNumber15CR;
    EditText etBranch16CR, etIFSCCode17CR, etPassword18CR;

    ImageView imgPhotoOfCompany18CR;

    Button btnCorporateRegister;
    private String encodedBase64String = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corporate_register);

        etOutletName1CR = findViewById(R.id.etOutletName1CR);
        etOwnerName2CR = findViewById(R.id.etOwnerName2CR);
        etWhatsAppNumber4CR = findViewById(R.id.etWhatsAppNumber4CR);
        etMailID5CR = findViewById(R.id.etMailID5CR);
        etEmployeeStrength6CR = findViewById(R.id.etEmployeeStrength6CR);
        etAddress7CR = findViewById(R.id.etAddress7CR);
        etPincode8CR = findViewById(R.id.etPincode8CR);
        etVillageName9CR = findViewById(R.id.etVillageName9CR);
        etVillageCode10CR = findViewById(R.id.etVillageCode10CR);
        etAadharNo11CR = findViewById(R.id.etAadharNo11CR);
        etGSTNo12CR = findViewById(R.id.etGSTNo12CR);
        etPanNo13CR = findViewById(R.id.etPanNo13CR);
        etBankName14CR = findViewById(R.id.etBankName14CR);
        etBankAccountNumber15CR = findViewById(R.id.etBankAccountNumber15CR);
        etBranch16CR = findViewById(R.id.etBranch16CR);
        etIFSCCode17CR = findViewById(R.id.etIFSCCode17CR);
        etPassword18CR = findViewById(R.id.etPassword18CR);
        imgPhotoOfCompany18CR = findViewById(R.id.imgPhotoOfCompany18CR);

        btnCorporateRegister = findViewById(R.id.btnCorporateRegister);

        btnCorporateRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AG dg_inst = AG.getInstance();
                System.out.println("jyo name" + etWhatsAppNumber4CR);

                if (etOutletName1CR.getText().toString().length() == 0) {
                    etOutletName1CR.setError("Enter Outlet Name");
                    return;
                }
                dg_inst.getUser().setCrOutletName(etOutletName1CR.getText().toString());

                if (etOwnerName2CR.getText().toString().length() == 0) {
                    etOwnerName2CR.setError("Enter Owner Name");
                    return;
                }
                dg_inst.getUser().setCrOwnerName(etOwnerName2CR.getText().toString());

                /*if (etPhoneNumber3CR.getText().toString().length() == 0) {
                    etPhoneNumber3CR.setError("Enter Mobile Number");
                    return;
                }
                if (etPhoneNumber3CR.getText().toString().length() < 10) {
                    etPhoneNumber3CR.setError("Enter Valid Mobile Number");
                    return;
                }
                dg_inst.getUser().setCrMobile(etPhoneNumber3CR.getText().toString());*/

                if (etWhatsAppNumber4CR.getText().toString().length() == 0) {
                    etWhatsAppNumber4CR.setError("Enter What's App Number");
                    return;
                }
                if (etWhatsAppNumber4CR.getText().toString().length() < 10) {
                    etWhatsAppNumber4CR.setError("Enter 10 Digit Number");
                    return;
                }
                dg_inst.getUser().setCrWhatsappNo(etWhatsAppNumber4CR.getText().toString());

                Matcher matcher = Patterns.EMAIL_ADDRESS.matcher(etMailID5CR.getText().toString());
                if (etMailID5CR.getText().toString().length() == 0) {
                    etMailID5CR.setError("Enter Mail ID");
                    return;
                }
                if (!matcher.matches()) {
                    etMailID5CR.setError("Invalid Email Address");
                    return;
                }else
                     dg_inst.getUser().setCrMailId(etMailID5CR.getText().toString());

                if (etEmployeeStrength6CR.getText().toString().length() == 0) {
                    etEmployeeStrength6CR.setError("Enter Employee Strength");
                    return;
                }
                dg_inst.getUser().setCrEmployeeStrength(etEmployeeStrength6CR.getText().toString());

                if (etAddress7CR.getText().toString().length() == 0) {
                    etAddress7CR.setError("Enter Address");
                    return;
                }
                dg_inst.getUser().setCrAddress(etAddress7CR.getText().toString());

                if (etPincode8CR.getText().toString().length() == 0) {
                    etPincode8CR.setError("Enter Pincode");
                    return;
                }
                if (etPincode8CR.getText().toString().length() < 6) {
                    etPincode8CR.setError("Enter Correct Pincode");
                    return;
                }
                dg_inst.getUser().setCrPincode(etPincode8CR.getText().toString());


                if (etVillageName9CR.getText().toString().length() == 0) {
                    etVillageName9CR.setError("Enter Village name");
                    return;
                }
                dg_inst.getUser().setCrVillageName(etVillageName9CR.getText().toString());

//                if (etVillageCode10CR.getText().toString().length() == 0) {
//                    etVillageCode10CR.setError("Enter Village code");
//                    return;
//                }
                dg_inst.getUser().setCrVillageCode(etVillageCode10CR.getText().toString());

                if (etAadharNo11CR.getText().toString().length() == 0) {
                    etAadharNo11CR.setError("Enter Aadhar Number");
                    return;
                }
                if (etAadharNo11CR.getText().toString().length() < 12) {
                    etAadharNo11CR.setError("Enter 12 Digit Aadhar Number");
                    return;
                }
                dg_inst.getUser().setCrAadharNumber(etAadharNo11CR.getText().toString());


                if (etGSTNo12CR.getText().toString().length() == 0) {
                    etGSTNo12CR.setError("Enter GST Number");
                    return;
                }
                dg_inst.getUser().setCrGSTNumber(etGSTNo12CR.getText().toString());

                if (etPanNo13CR.getText().toString().length() == 0) {
                    etPanNo13CR.setError("Enter Pan Number");
                    return;
                }
                dg_inst.getUser().setCrPanNumber(etPanNo13CR.getText().toString());

                if (etBankName14CR.getText().toString().length() == 0) {
                    etBankName14CR.setError("Enter Bank Name");
                    return;
                }
                dg_inst.getUser().setCrBankName(etBankName14CR.getText().toString());


                if (etBankAccountNumber15CR.getText().toString().length() == 0) {
                    etBankAccountNumber15CR.setError("Enter Bank Account Number ");
                    return;
                }
                dg_inst.getUser().setCrBankAccountNumber(etBankAccountNumber15CR.getText().toString());

                if (etBranch16CR.getText().toString().length() == 0) {
                    etBranch16CR.setError("Enter Branch");
                    return;
                }
                dg_inst.getUser().setCrBranch(etBranch16CR.getText().toString());

                if (etIFSCCode17CR.getText().toString().length() == 0) {
                    etIFSCCode17CR.setError("Enter IFSC Code");
                    return;
                }
                dg_inst.getUser().setCrIFSCCode(etIFSCCode17CR.getText().toString());

                if (imgPhotoOfCompany18CR.getDrawable() != null && !encodedBase64String.isEmpty() ) {

                    dg_inst.getUser().setCrPhotoCompany(encodedBase64String);
                    // dg_inst.getUser().setPhotoNumPlate(String.valueOf(R.id.imgtakephotoNumber));
                    //     Toast.makeText(RegisterActivity.this, "Add Image", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(CorporateRegisterActivity.this, "Add Image", Toast.LENGTH_SHORT).show();
                }


                if (etPassword18CR.getText().toString().length() == 0) {
                    etPassword18CR.setError("Enter Password ");
                    return;
                }
                dg_inst.getUser().setCrPassword(etPassword18CR.getText().toString());


                try {
                    AsyncTask<String, Void, Integer> result = new CorporateRegister().execute();
                    int code = result.get();
                    System.out.println("jyo reg code" + code);
                    if (code == 200) {

                        Dbhelper.createVendorDbHelper(CorporateRegisterActivity.this);
                        Dao vendorDetailsDao = new Dao();
                        vendorDetailsDao.getUserDetails();

                        Toast.makeText(CorporateRegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(CorporateRegisterActivity.this, CorporateLoginActivity.class);
                        startActivity(i);
                        //  finish();
                    }
                    else  if (code == 202){
                        Toast.makeText(CorporateRegisterActivity.this, "Provide complete information to Register", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(CorporateRegisterActivity.this, "Failed to Register", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {

                    Toast.makeText(CorporateRegisterActivity.this, "Fail to Register", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgPhotoOfCompany18CR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};



                AlertDialog.Builder builder = new AlertDialog.Builder(CorporateRegisterActivity.this);
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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            try{
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap imageBitmap = BitmapFactory.decodeStream(imageStream);
                imgPhotoOfCompany18CR.setImageBitmap(imageBitmap);


                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream .toByteArray();
                encodedBase64String = Base64.encodeToString(byteArray, Base64.DEFAULT);
            }catch (Exception e){

            }
        }else if (requestCode == 0 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgPhotoOfCompany18CR.setImageBitmap(imageBitmap);
            //Todo: Converting image to base 64 to store to database
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();
            encodedBase64String = Base64.encodeToString(byteArray, Base64.DEFAULT);
        }

    }

    @Override
    protected void onResume() {

        AG ht_inst = AG.getInstance();
        if (!ht_inst.getInitDone()) {
            Intent intent = new Intent(CorporateRegisterActivity.this, FirstActivityAccountSelection.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        super.onResume();
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(CorporateRegisterActivity.this, FirstActivityAccountSelection.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }

}