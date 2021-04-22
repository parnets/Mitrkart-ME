package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.RetailerSurveyRegister;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dao;
import com.Mitrakarte_ME.meappmintrakarte.Util.Dbhelper;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class RetailerSurveyRegistrationActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

//    Boolean boolean1 = Boolean.valueOf(textSpnAvaiHul);
//    boolean bolleanHulAvail= Boolean.parseBoolean(textSpnAvaiHul);

    TextView text1, text2, text3, text4, text5;

    FusedLocationProviderClient fusedLocationProvideClient;

    private int mDay;
    private int mMonth;
    private int mYear;

    private Boolean isetDateofBirth = false;

    EditText etName1RS, etpermanentNumber2RS, etVillagePapulation4RS, etOwnerName5RS;
    EditText etEmail6RS, etDob7RS, etAnniversaryDate8RS, etGstNo9RS, etPanNo10RS;
    EditText etAddress11RS, etLandMark12RS, etAlternateNo13RS, etWhatsappNumber14RS, etExperience15RS;
    EditText etTypeOfRetailerOther16RS, etPlaceName19RS, etKmTravel19RS, etHowOften19RS;
    EditText etAverageCustomerPerDay21RS, etAverageSalesPerWeek22RS, etAverageSalesMoth23RS, etFinanceAmount24RS;
    Button btnRetailerSurveyRegister;

    CheckBox chkWhatsapp17RS, chkFacebook17RS, chkTwitter17RS;

    TextView tvBrandNameHUL18RS;

    TextView tvTypeOfRetailer16RS, tvBuyStock19RS, tvfinance24Rs;
    RadioGroup radioGroupBuyStockOuside19RS, radioGroupServiceToDoor20RS, radioGroupFinanceAmount24RS;
    RadioButton radioButtonBuyYes19RS, radioButtonBuyNo19RS;
    RadioButton radioButtonServiceToDoorYes20RS, radioButtonServiceToDoorNo20RS;
    RadioButton radioButtonetFinanceAmountYes24RS, radioButtonetFinanceAmountNo24RS;
    RadioButton radioButtonShopInsuranceAvailableYes24RS, radioButtonShopInsuranceAvailableNo24RS;
    RadioButton radioButtonShopInsuranceRequiredYes24RS, radioButtonShopInsuranceRequiredNo24RS;
    RadioButton radioButtonMedicalInsuranceAvailableYes25RS, radioButtonMedicalInsuranceAvailableNo25RS;
    RadioButton radioButtonMedicalInsuranceRequiredYes25RS, radioButtonMedicalInsuranceRequiredNo25RS;
    RadioButton radioButtonLifeInsuranceAvailableYes26RS, radioButtonLifeInsuranceAvailableNo26RS;
    RadioButton radioButtonLifeInsuranceRequiredYes26RS, radioButtonLifeInsuranceRequiredNo26RS;
    RadioButton radioButtonTermInsuranceAvailableYes27RS, radioButtonTermInsuranceAvailableNo27RS;
    RadioButton radioButtonTermInsuranceRequiredYes27RS, radioButtonTermInsuranceRequiredNo27RS;

    Spinner spinnerTypeOfFirm3RS, spinnerTypeOfRetailer16RS, spinnerAvailabiltyHUL18RS, spinnerFrequencyServiceHUL18RS;
    Spinner spinnerAvailabiltyITC18RS, spinnerFrequencyServiceITC18RS;
    Spinner spinnerAvailabiltyNestle183RS, spinnerFrequencyServiceNestle183RS, spinnerAvailabiltyCadburys184RS;
    Spinner spinnerFrequencyServiceCadburys184RS, spinnerAvailabiltyKaleeshwari185RS, spinnerFrequencyServiceKaleeshwari185RS;
    Spinner spinnerAvailabilty_Dabur186RS, spinnerFrequencyService_Dabur186RS, spinnerAvailabilty_britannia187RS, spinnerFrequencyService_britannia187RS;
    Spinner spinnerAvailabilty_Kwalitywalls188RS, spinnerFrequencyService_Kwalitywalls188RS, spinnerAvailabilty_Hatsunagro189RS;
    Spinner spinnerFrequencyService_Hatsunagro189RS, spinnerAvailabilty_Heritage1810RS, spinnerFrequencyService_Heritage1810RS;
    Spinner spinnerAvailabilty_MTR1811RS, spinnerFrequencyService_MTR1811RS, spinnerAvailabilty_Sakthi1812RS, spinnerFrequencyService_Sakthi1812RS;
    Spinner spinnerAvailabilty_Amul1813RS, spinnerFrequencyService_Amul1813RS, spinnerAvailabilty_Everest1814RS, spinnerFrequencyService_Everest1814RS;
    Spinner spinnerAvailabilty_Haldirams1815RS, spinnerFrequencyService_Haldirams1815RS, spinnerAvailabilty_Colgate1816RS;
    Spinner spinnerFrequencyService_Colgate1816RS, spinnerAvailabilty_Motherdairy1817RS, spinnerFrequencyService_Motherdairy1817RS;
    Spinner spinnerAvailabilty_Parle1818RS, spinnerFrequencyService_Parle1818RS, spinnerAvailabilty_Biskfarm1819RS;
    Spinner spinnerFrequencyService_Biskfarm1819RS, spinnerAvailabilty_ReiAgro1820RS, spinnerFrequencyService_ReiAgro1820RS;
    Spinner spinnerAvailabilty_Rucisoya1821RS, spinnerFrequencyService_Rucisoya1821RS, spinnerAvailabilty_Idhayam1822RS;
    Spinner spinnerFrequencyService_Idhayam1822RS, spinnerAvailabilty_Jubilantfood1823RS, spinnerFrequencyService_Jubilantfood1823RS;
    Spinner spinnerAvailabilty_Coke1824RS, spinnerFrequencyService_Coke1824RS, spinnerAvailabilty_Pepsi1825RS;
    Spinner spinnerFrequencyService_Pepsi1825RS, spinnerAvailabilty_Bisleri1826RS, spinnerFrequencyService_Bisleri1826RS;
    Spinner spinnerAvailabilty_GSK1827RS, spinnerFrequencyService_GSK1827RS, spinnerAvailabilty_Patanjali1828RS;
    Spinner spinnerFrequencyService_Patanjali1828RS, spinnerAvailabilty_Marico1829RS, spinnerFrequencyService_Marico1829RS;
    Spinner spinnerAvailabilty_PG1830RS, spinnerFrequencyService_PG1830RS;

    Spinner spinnerServiceToDoor20RS, spinnerAvailabiltyTATA18RS, spinnerFrequencyServiceTATA18RS;

    TextView tvSocialMedia17RS, tvShopInsuranceRequired24RS, LiveLocation28;

    String[] typeOfFirm = {"Select Type of Firm", "Ownership", "Partnership"};
    String[] typeOfRetailer = {"Select Type of Retailer", "Provision/Groceries", "Depeartmental Store", "Wholesale Retailer",
            "Bakery", "Condiments", "Mobile", "Stationery",
            "Fruits/ Vegetables", "Medicals", "Shoes", "Petty Shop",
            "Branded Outlet", "Fancy Store", "Photocopy", "Hotel/Restaurants",
            "Textiles", "Home appliances", "Electronics", "Others"};

    String[] serviceToDoorstepFrequency = {"Select Buying Frequency", "Daily", "Alternative Day", "Weekly", "Alternative Week", "Monthly"};
    String[] brandAvailaibility = {"Select  Availability", "Yes", "No"};
    String[] brandFrequncyOfService = {"Select Frequency of Service", "Daily", "Alternative Day", "Weekly", "Alternative Week", "Monthly"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_survey_registration);

        etName1RS = findViewById(R.id.etName1RS);
        etpermanentNumber2RS = findViewById(R.id.etpermanentNumber2RS);
        spinnerTypeOfFirm3RS = findViewById(R.id.spinnerTypeOfFirm3RS);
        etVillagePapulation4RS = findViewById(R.id.etVillagePapulation4RS);
        etOwnerName5RS = findViewById(R.id.etOwnerName5RS);

        etEmail6RS = findViewById(R.id.etEmail6RS);
        etDob7RS = findViewById(R.id.etDob7RS);
        etAnniversaryDate8RS = findViewById(R.id.etAnniversaryDate8RS);
        etGstNo9RS = findViewById(R.id.etGstNo9RS);
        etPanNo10RS = findViewById(R.id.etPanNo10RS);

        etAddress11RS = findViewById(R.id.etAddress11RS);
        etLandMark12RS = findViewById(R.id.etLandMark12RS);
        etAlternateNo13RS = findViewById(R.id.etAlternateNo13RS);
        etWhatsappNumber14RS = findViewById(R.id.etWhatsappNumber14RS);
        etExperience15RS = findViewById(R.id.etExperience15RS);

        etTypeOfRetailerOther16RS = findViewById(R.id.etTypeOfRetailerOther16RS);
        tvTypeOfRetailer16RS = findViewById(R.id.tvTypeOfRetailer16RS);
        spinnerTypeOfRetailer16RS = findViewById(R.id.spinnerTypeOfRetailer16RS);
        tvSocialMedia17RS = findViewById(R.id.tvSocialMedia17RS);
        chkWhatsapp17RS = findViewById(R.id.chkWhatsapp17RS);
        chkFacebook17RS = findViewById(R.id.chkFacebook17RS);
        chkTwitter17RS = findViewById(R.id.chkTwitter17RS);

        tvBrandNameHUL18RS = findViewById(R.id.tvBrandNameHUL18RS);
        spinnerAvailabiltyHUL18RS = findViewById(R.id.spinnerAvailabiltyHUL18RS);
        spinnerFrequencyServiceHUL18RS = findViewById(R.id.spinnerFrequencyServiceHUL18RS);

        spinnerAvailabiltyTATA18RS = findViewById(R.id.spinnerAvailabiltyTATA18RS);
        spinnerFrequencyServiceTATA18RS = findViewById(R.id.spinnerFrequencyServiceTATA18RS);

        spinnerAvailabiltyITC18RS = findViewById(R.id.spinnerAvailabiltyITC18RS);
        spinnerFrequencyServiceITC18RS = findViewById(R.id.spinnerFrequencyServiceITC18RS);

        spinnerAvailabiltyNestle183RS = findViewById(R.id.spinnerAvailabiltyNestle183RS);
        spinnerFrequencyServiceNestle183RS = findViewById(R.id.spinnerFrequencyServiceNestle183RS);

        spinnerAvailabiltyCadburys184RS = findViewById(R.id.spinnerAvailabiltyCadburys184RS);
        spinnerFrequencyServiceCadburys184RS = findViewById(R.id.spinnerFrequencyServiceCadburys184RS);

        spinnerAvailabiltyKaleeshwari185RS = findViewById(R.id.spinnerAvailabiltyKaleeshwari185RS);
        spinnerFrequencyServiceKaleeshwari185RS = findViewById(R.id.spinnerFrequencyServiceKaleeshwari185RS);

        spinnerAvailabilty_Dabur186RS = findViewById(R.id.spinnerAvailabilty_Dabur186RS);
        spinnerFrequencyService_Dabur186RS = findViewById(R.id.spinnerFrequencyService_Dabur186RS);

        spinnerAvailabilty_britannia187RS = findViewById(R.id.spinnerAvailabilty_britannia187RS);
        spinnerFrequencyService_britannia187RS = findViewById(R.id.spinnerFrequencyService_britannia187RS);

        spinnerAvailabilty_Kwalitywalls188RS = findViewById(R.id.spinnerAvailabilty_Kwalitywalls188RS);
        spinnerFrequencyService_Kwalitywalls188RS = findViewById(R.id.spinnerFrequencyService_Kwalitywalls188RS);

        spinnerAvailabilty_Hatsunagro189RS = findViewById(R.id.spinnerAvailabilty_Hatsunagro189RS);
        spinnerFrequencyService_Hatsunagro189RS = findViewById(R.id.spinnerFrequencyService_Hatsunagro189RS);

        spinnerAvailabilty_Heritage1810RS = findViewById(R.id.spinnerAvailabilty_Heritage1810RS);
        spinnerFrequencyService_Heritage1810RS = findViewById(R.id.spinnerFrequencyService_Heritage1810RS);
//todo
        spinnerAvailabilty_MTR1811RS = findViewById(R.id.spinnerAvailabilty_MTR1811RS);
        spinnerFrequencyService_MTR1811RS = findViewById(R.id.spinnerFrequencyService_MTR1811RS);

        spinnerAvailabilty_Sakthi1812RS = findViewById(R.id.spinnerAvailabilty_Sakthi1812RS);
        spinnerFrequencyService_Sakthi1812RS = findViewById(R.id.spinnerFrequencyService_Sakthi1812RS);

        spinnerAvailabilty_Amul1813RS = findViewById(R.id.spinnerAvailabilty_Amul1813RS);
        spinnerFrequencyService_Amul1813RS = findViewById(R.id.spinnerFrequencyService_Amul1813RS);

        spinnerAvailabilty_Everest1814RS = findViewById(R.id.spinnerAvailabilty_Everest1814RS);
        spinnerFrequencyService_Everest1814RS = findViewById(R.id.spinnerFrequencyService_Everest1814RS);

        spinnerAvailabilty_Haldirams1815RS = findViewById(R.id.spinnerAvailabilty_Haldirams1815RS);
        spinnerFrequencyService_Haldirams1815RS = findViewById(R.id.spinnerFrequencyService_Haldirams1815RS);

        spinnerAvailabilty_Colgate1816RS = findViewById(R.id.spinnerAvailabilty_Colgate1816RS);
        spinnerFrequencyService_Colgate1816RS = findViewById(R.id.spinnerFrequencyService_Colgate1816RS);

        spinnerAvailabilty_Motherdairy1817RS = findViewById(R.id.spinnerAvailabilty_Motherdairy1817RS);
        spinnerFrequencyService_Motherdairy1817RS = findViewById(R.id.spinnerFrequencyService_Motherdairy1817RS);

        spinnerAvailabilty_Parle1818RS = findViewById(R.id.spinnerAvailabilty_Parle1818RS);
        spinnerFrequencyService_Parle1818RS = findViewById(R.id.spinnerFrequencyService_Parle1818RS);

        //todo
        spinnerAvailabilty_Biskfarm1819RS = findViewById(R.id.spinnerAvailabilty_Biskfarm1819RS);
        spinnerFrequencyService_Biskfarm1819RS = findViewById(R.id.spinnerFrequencyService_Biskfarm1819RS);

        spinnerAvailabilty_ReiAgro1820RS = findViewById(R.id.spinnerAvailabilty_ReiAgro1820RS);
        spinnerFrequencyService_ReiAgro1820RS = findViewById(R.id.spinnerFrequencyService_ReiAgro1820RS);

        spinnerAvailabilty_Rucisoya1821RS = findViewById(R.id.spinnerAvailabilty_Rucisoya1821RS);
        spinnerFrequencyService_Rucisoya1821RS = findViewById(R.id.spinnerFrequencyService_Rucisoya1821RS);

        spinnerAvailabilty_Idhayam1822RS = findViewById(R.id.spinnerAvailabilty_Idhayam1822RS);
        spinnerFrequencyService_Idhayam1822RS = findViewById(R.id.spinnerFrequencyService_Idhayam1822RS);

        spinnerAvailabilty_Jubilantfood1823RS = findViewById(R.id.spinnerAvailabilty_Jubilantfood1823RS);
        spinnerFrequencyService_Jubilantfood1823RS = findViewById(R.id.spinnerFrequencyService_Jubilantfood1823RS);

        spinnerAvailabilty_Coke1824RS = findViewById(R.id.spinnerAvailabilty_Coke1824RS);
        spinnerFrequencyService_Coke1824RS = findViewById(R.id.spinnerFrequencyService_Coke1824RS);

        spinnerAvailabilty_Pepsi1825RS = findViewById(R.id.spinnerAvailabilty_Pepsi1825RS);
        spinnerFrequencyService_Pepsi1825RS = findViewById(R.id.spinnerFrequencyService_Pepsi1825RS);

        spinnerAvailabilty_Bisleri1826RS = findViewById(R.id.spinnerAvailabilty_Bisleri1826RS);
        spinnerFrequencyService_Bisleri1826RS = findViewById(R.id.spinnerFrequencyService_Bisleri1826RS);

        spinnerAvailabilty_GSK1827RS = findViewById(R.id.spinnerAvailabilty_GSK1827RS);
        spinnerFrequencyService_GSK1827RS = findViewById(R.id.spinnerFrequencyService_GSK1827RS);

        spinnerAvailabilty_Patanjali1828RS = findViewById(R.id.spinnerAvailabilty_Patanjali1828RS);
        spinnerFrequencyService_Patanjali1828RS = findViewById(R.id.spinnerFrequencyService_Patanjali1828RS);

        spinnerAvailabilty_Marico1829RS = findViewById(R.id.spinnerAvailabilty_Marico1829RS);
        spinnerFrequencyService_Marico1829RS = findViewById(R.id.spinnerFrequencyService_Marico1829RS);

        spinnerAvailabilty_PG1830RS = findViewById(R.id.spinnerAvailabilty_PG1830RS);
        spinnerFrequencyService_PG1830RS = findViewById(R.id.spinnerFrequencyService_PG1830RS);


        etPlaceName19RS = findViewById(R.id.etPlaceName19RS);
        etKmTravel19RS = findViewById(R.id.etKmTravel19RS);
        etHowOften19RS = findViewById(R.id.etHowOften19RS);

        tvBuyStock19RS = findViewById(R.id.tvBuyStock19RS);

        radioButtonBuyYes19RS = findViewById(R.id.radioButtonBuyYes19RS);
        radioButtonBuyNo19RS = findViewById(R.id.radioButtonBuyNo19RS);
        radioGroupBuyStockOuside19RS = findViewById(R.id.radioGroupBuyStockOuside19RS);


        spinnerServiceToDoor20RS = findViewById(R.id.spinnerServiceToDoor20RS);
        radioGroupServiceToDoor20RS = findViewById(R.id.radioGroupBuyStockOuside19RS);
        radioButtonServiceToDoorYes20RS = findViewById(R.id.radioButtonServiceToDoorYes20RS);
        radioButtonServiceToDoorNo20RS = findViewById(R.id.radioButtonServiceToDoorNo20RS);

        etAverageCustomerPerDay21RS = findViewById(R.id.etAverageCustomerPerDay21RS);
        etAverageSalesPerWeek22RS = findViewById(R.id.etAverageSalesPerWeek22RS);
        etAverageSalesMoth23RS = findViewById(R.id.etAverageSalesMoth23RS);

        tvfinance24Rs = findViewById(R.id.tvfinance24Rs);
        etFinanceAmount24RS = findViewById(R.id.etFinanceAmount24RS);
        tvShopInsuranceRequired24RS = findViewById(R.id.tvShopInsuranceRequired24RS);

        radioGroupFinanceAmount24RS = findViewById(R.id.radioGroupFinanceAmount24RS);
        radioButtonetFinanceAmountYes24RS = findViewById(R.id.radioButtonetFinanceAmountYes24RS);
        radioButtonetFinanceAmountNo24RS = findViewById(R.id.radioButtonetFinanceAmountNo24RS);

        radioButtonShopInsuranceAvailableYes24RS = findViewById(R.id.radioButtonShopInsuranceAvailableYes24RS);
        radioButtonShopInsuranceAvailableNo24RS = findViewById(R.id.radioButtonShopInsuranceAvailableNo24RS);
        radioButtonShopInsuranceRequiredYes24RS = findViewById(R.id.radioButtonShopInsuranceRequiredYes24RS);
        radioButtonShopInsuranceRequiredNo24RS = findViewById(R.id.radioButtonShopInsuranceRequiredNo24RS);

        radioButtonMedicalInsuranceAvailableYes25RS = findViewById(R.id.radioButtonMedicalInsuranceAvailableYes25RS);
        radioButtonMedicalInsuranceAvailableNo25RS = findViewById(R.id.radioButtonMedicalInsuranceAvailableNo25RS);
        radioButtonMedicalInsuranceRequiredYes25RS = findViewById(R.id.radioButtonMedicalInsuranceRequiredYes25RS);
        radioButtonMedicalInsuranceRequiredNo25RS = findViewById(R.id.radioButtonMedicalInsuranceRequiredNo25RS);

        radioButtonLifeInsuranceAvailableYes26RS = findViewById(R.id.radioButtonLifeInsuranceAvailableYes26RS);
        radioButtonLifeInsuranceAvailableNo26RS = findViewById(R.id.radioButtonLifeInsuranceAvailableNo26RS);
        radioButtonLifeInsuranceRequiredYes26RS = findViewById(R.id.radioButtonLifeInsuranceRequiredYes26RS);
        radioButtonLifeInsuranceRequiredNo26RS = findViewById(R.id.radioButtonLifeInsuranceRequiredNo26RS);

        radioButtonTermInsuranceAvailableYes27RS = findViewById(R.id.radioButtonTermInsuranceAvailableYes27RS);
        radioButtonTermInsuranceAvailableNo27RS = findViewById(R.id.radioButtonTermInsuranceAvailableNo27RS);
        radioButtonTermInsuranceRequiredYes27RS = findViewById(R.id.radioButtonTermInsuranceRequiredYes27RS);
        radioButtonTermInsuranceRequiredNo27RS = findViewById(R.id.radioButtonTermInsuranceRequiredNo27RS);

        LiveLocation28 = findViewById(R.id.LiveLocation28);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);

        btnRetailerSurveyRegister = findViewById(R.id.btnRetailerSurveyRegister);

//        checkBoxBuyYes19RS=(CheckBox)findViewById(R.id.checkBoxBuyYes19RS);
//        checkBoxBuyNo19RS=(CheckBox)findViewById(R.id.checkBoxBuyNo19RS);


        //Todo: spinner- type of firm
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        spinnerTypeOfFirm3RS.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, typeOfFirm);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinnerTypeOfFirm3RS.setAdapter(aa);


        //Todo: spinner - type of retailer
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        spinnerTypeOfRetailer16RS.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter bb = new ArrayAdapter(this, android.R.layout.simple_spinner_item, typeOfRetailer);
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinnerTypeOfRetailer16RS.setAdapter(bb);

        //Todo: spinner - Hul brand Avilaibility
        spinnerAvailabiltyHUL18RS.setOnItemSelectedListener(this);
        spinnerAvailabiltyTATA18RS.setOnItemSelectedListener(this);
        spinnerAvailabiltyITC18RS.setOnItemSelectedListener(this);
        spinnerAvailabiltyNestle183RS.setOnItemSelectedListener(this);
        spinnerAvailabiltyCadburys184RS.setOnItemSelectedListener(this);
        spinnerAvailabiltyKaleeshwari185RS.setOnItemSelectedListener(this);


        spinnerAvailabilty_Dabur186RS.setOnItemSelectedListener(this);
        spinnerAvailabilty_britannia187RS.setOnItemSelectedListener(this);
        spinnerAvailabilty_Kwalitywalls188RS.setOnItemSelectedListener(this);
        spinnerAvailabilty_Hatsunagro189RS.setOnItemSelectedListener(this);
        spinnerAvailabilty_Heritage1810RS.setOnItemSelectedListener(this);

        spinnerAvailabilty_MTR1811RS.setOnItemSelectedListener(this);
        spinnerAvailabilty_Sakthi1812RS.setOnItemSelectedListener(this);
        spinnerAvailabilty_Amul1813RS.setOnItemSelectedListener(this);
        spinnerAvailabilty_Everest1814RS.setOnItemSelectedListener(this);
        spinnerAvailabilty_Haldirams1815RS.setOnItemSelectedListener(this);

        spinnerAvailabilty_Colgate1816RS.setOnItemSelectedListener(this);
        spinnerAvailabilty_Motherdairy1817RS.setOnItemSelectedListener(this);
        spinnerAvailabilty_Parle1818RS.setOnItemSelectedListener(this);
        spinnerAvailabilty_Biskfarm1819RS.setOnItemSelectedListener(this);
        spinnerAvailabilty_ReiAgro1820RS.setOnItemSelectedListener(this);

        spinnerAvailabilty_Rucisoya1821RS.setOnItemSelectedListener(this);
        spinnerAvailabilty_Idhayam1822RS.setOnItemSelectedListener(this);
        spinnerAvailabilty_Jubilantfood1823RS.setOnItemSelectedListener(this);
        spinnerAvailabilty_Coke1824RS.setOnItemSelectedListener(this);
        spinnerAvailabilty_Pepsi1825RS.setOnItemSelectedListener(this);

        spinnerAvailabilty_Bisleri1826RS.setOnItemSelectedListener(this);
        spinnerAvailabilty_GSK1827RS.setOnItemSelectedListener(this);
        spinnerAvailabilty_Patanjali1828RS.setOnItemSelectedListener(this);
        spinnerAvailabilty_Marico1829RS.setOnItemSelectedListener(this);
        spinnerAvailabilty_PG1830RS.setOnItemSelectedListener(this);


        ArrayAdapter dd = new ArrayAdapter(this, android.R.layout.simple_spinner_item, brandAvailaibility);
        dd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAvailabiltyHUL18RS.setAdapter(dd);
        spinnerAvailabiltyTATA18RS.setAdapter(dd);
        spinnerAvailabiltyITC18RS.setAdapter(dd);
        spinnerAvailabiltyNestle183RS.setAdapter(dd);
        spinnerAvailabiltyCadburys184RS.setAdapter(dd);
        spinnerAvailabiltyKaleeshwari185RS.setAdapter(dd);

        spinnerAvailabilty_Dabur186RS.setAdapter(dd);
        spinnerAvailabilty_britannia187RS.setAdapter(dd);
        spinnerAvailabilty_Kwalitywalls188RS.setAdapter(dd);
        spinnerAvailabilty_Hatsunagro189RS.setAdapter(dd);
        spinnerAvailabilty_Heritage1810RS.setAdapter(dd);


        spinnerAvailabilty_MTR1811RS.setAdapter(dd);
        spinnerAvailabilty_Sakthi1812RS.setAdapter(dd);
        spinnerAvailabilty_Amul1813RS.setAdapter(dd);
        spinnerAvailabilty_Everest1814RS.setAdapter(dd);
        spinnerAvailabilty_Haldirams1815RS.setAdapter(dd);

        spinnerAvailabilty_Colgate1816RS.setAdapter(dd);
        spinnerAvailabilty_Motherdairy1817RS.setAdapter(dd);
        spinnerAvailabilty_Parle1818RS.setAdapter(dd);
        spinnerAvailabilty_Biskfarm1819RS.setAdapter(dd);
        spinnerAvailabilty_ReiAgro1820RS.setAdapter(dd);

        spinnerAvailabilty_Rucisoya1821RS.setAdapter(dd);
        spinnerAvailabilty_Idhayam1822RS.setAdapter(dd);
        spinnerAvailabilty_Jubilantfood1823RS.setAdapter(dd);
        spinnerAvailabilty_Coke1824RS.setAdapter(dd);
        spinnerAvailabilty_Pepsi1825RS.setAdapter(dd);

        spinnerAvailabilty_Bisleri1826RS.setAdapter(dd);
        spinnerAvailabilty_GSK1827RS.setAdapter(dd);
        spinnerAvailabilty_Patanjali1828RS.setAdapter(dd);
        spinnerAvailabilty_Marico1829RS.setAdapter(dd);
        spinnerAvailabilty_PG1830RS.setAdapter(dd);


        //Todo: spinner - brand frequence of service

//        if (spinnerAvailabiltyHUL18RS.getSelectedItemPosition() == 2) {
//
//
//        }
        spinnerFrequencyServiceHUL18RS.setOnItemSelectedListener(this);
        spinnerFrequencyServiceTATA18RS.setOnItemSelectedListener(this);
        spinnerFrequencyServiceITC18RS.setOnItemSelectedListener(this);
        spinnerFrequencyServiceNestle183RS.setOnItemSelectedListener(this);
        spinnerFrequencyServiceCadburys184RS.setOnItemSelectedListener(this);
        spinnerFrequencyServiceKaleeshwari185RS.setOnItemSelectedListener(this);

        spinnerFrequencyService_Dabur186RS.setOnItemSelectedListener(this);
        spinnerFrequencyService_britannia187RS.setOnItemSelectedListener(this);
        spinnerFrequencyService_Kwalitywalls188RS.setOnItemSelectedListener(this);
        spinnerFrequencyService_Hatsunagro189RS.setOnItemSelectedListener(this);
        spinnerFrequencyService_Heritage1810RS.setOnItemSelectedListener(this);

        spinnerFrequencyService_MTR1811RS.setOnItemSelectedListener(this);
        spinnerFrequencyService_Sakthi1812RS.setOnItemSelectedListener(this);
        spinnerFrequencyService_Amul1813RS.setOnItemSelectedListener(this);
        spinnerFrequencyService_Everest1814RS.setOnItemSelectedListener(this);
        spinnerFrequencyService_Haldirams1815RS.setOnItemSelectedListener(this);

        spinnerFrequencyService_Colgate1816RS.setOnItemSelectedListener(this);
        spinnerFrequencyService_Motherdairy1817RS.setOnItemSelectedListener(this);
        spinnerFrequencyService_Parle1818RS.setOnItemSelectedListener(this);
        spinnerFrequencyService_Biskfarm1819RS.setOnItemSelectedListener(this);
        spinnerFrequencyService_ReiAgro1820RS.setOnItemSelectedListener(this);

        spinnerFrequencyService_Rucisoya1821RS.setOnItemSelectedListener(this);
        spinnerFrequencyService_Idhayam1822RS.setOnItemSelectedListener(this);
        spinnerFrequencyService_Jubilantfood1823RS.setOnItemSelectedListener(this);
        spinnerFrequencyService_Coke1824RS.setOnItemSelectedListener(this);
        spinnerFrequencyService_Pepsi1825RS.setOnItemSelectedListener(this);

        spinnerFrequencyService_Bisleri1826RS.setOnItemSelectedListener(this);
        spinnerFrequencyService_GSK1827RS.setOnItemSelectedListener(this);
        spinnerFrequencyService_Patanjali1828RS.setOnItemSelectedListener(this);
        spinnerFrequencyService_Marico1829RS.setOnItemSelectedListener(this);
        spinnerFrequencyService_PG1830RS.setOnItemSelectedListener(this);

        ArrayAdapter ee = new ArrayAdapter(this, android.R.layout.simple_spinner_item, brandFrequncyOfService);
        dd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrequencyServiceHUL18RS.setAdapter(ee);
        spinnerFrequencyServiceTATA18RS.setAdapter(ee);
        spinnerFrequencyServiceITC18RS.setAdapter(ee);
        spinnerFrequencyServiceNestle183RS.setAdapter(ee);
        spinnerFrequencyServiceCadburys184RS.setAdapter(ee);
        spinnerFrequencyServiceKaleeshwari185RS.setAdapter(ee);

        spinnerFrequencyService_Dabur186RS.setAdapter(ee);
        spinnerFrequencyService_britannia187RS.setAdapter(ee);
        spinnerFrequencyService_Kwalitywalls188RS.setAdapter(ee);
        spinnerFrequencyService_Hatsunagro189RS.setAdapter(ee);
        spinnerFrequencyService_Heritage1810RS.setAdapter(ee);

        spinnerFrequencyService_MTR1811RS.setAdapter(ee);
        spinnerFrequencyService_Sakthi1812RS.setAdapter(ee);
        spinnerFrequencyService_Amul1813RS.setAdapter(ee);
        spinnerFrequencyService_Everest1814RS.setAdapter(ee);
        spinnerFrequencyService_Haldirams1815RS.setAdapter(ee);

        spinnerFrequencyService_Colgate1816RS.setAdapter(ee);
        spinnerFrequencyService_Motherdairy1817RS.setAdapter(ee);
        spinnerFrequencyService_Parle1818RS.setAdapter(ee);
        spinnerFrequencyService_Biskfarm1819RS.setAdapter(ee);
        spinnerFrequencyService_ReiAgro1820RS.setAdapter(ee);

        spinnerFrequencyService_Rucisoya1821RS.setAdapter(ee);
        spinnerFrequencyService_Idhayam1822RS.setAdapter(ee);
        spinnerFrequencyService_Jubilantfood1823RS.setAdapter(ee);
        spinnerFrequencyService_Coke1824RS.setAdapter(ee);
        spinnerFrequencyService_Pepsi1825RS.setAdapter(ee);

        spinnerFrequencyService_Bisleri1826RS.setAdapter(ee);
        spinnerFrequencyService_GSK1827RS.setAdapter(ee);
        spinnerFrequencyService_Patanjali1828RS.setAdapter(ee);
        spinnerFrequencyService_Marico1829RS.setAdapter(ee);
        spinnerFrequencyService_PG1830RS.setAdapter(ee);


        //Todo: spinner - type of service
        spinnerServiceToDoor20RS.setOnItemSelectedListener(this);
        ArrayAdapter cc = new ArrayAdapter(this, android.R.layout.simple_spinner_item, serviceToDoorstepFrequency);
        cc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerServiceToDoor20RS.setAdapter(cc);


        // Todo: Date of Birth
        etDob7RS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                calendar.setTimeZone(TimeZone.getTimeZone("UTC"));

                int selectedDay = calendar.get(Calendar.DAY_OF_MONTH);
                int selectedMonth = calendar.get(Calendar.MONTH);
                int selectedYear = calendar.get(Calendar.YEAR);


                DatePickerDialog datePickerDialog = new DatePickerDialog(RetailerSurveyRegistrationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int selectedYear,
                                                  int selectedMonth, int selectedDay) {
                                mDay = selectedDay;
                                mMonth = selectedMonth;
                                mYear = selectedYear;
                                StringBuilder Date = new StringBuilder();
                                String conver = Integer.toString(selectedYear);
                                Date.append(conver);
                                Date.append("-");
                                selectedMonth++;
                                conver = Integer.toString(selectedMonth);
                                Date.append(conver);
                                Date.append("-");
                                conver = Integer.toString(selectedDay);
                                Date.append(conver);

                                etDob7RS.setText(Date.toString());
                                isetDateofBirth = true;
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

        // Todo: Anniversary Date
        etAnniversaryDate8RS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                calendar.setTimeZone(TimeZone.getTimeZone("UTC"));

                int selectedDay = calendar.get(Calendar.DAY_OF_MONTH);
                int selectedMonth = calendar.get(Calendar.MONTH);
                int selectedYear = calendar.get(Calendar.YEAR);


                DatePickerDialog datePickerDialog = new DatePickerDialog(RetailerSurveyRegistrationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int selectedYear,
                                                  int selectedMonth, int selectedDay) {
                                mDay = selectedDay;
                                mMonth = selectedMonth;
                                mYear = selectedYear;
                                StringBuilder Date = new StringBuilder();
                                String conver = Integer.toString(selectedYear);
                                Date.append(conver);
                                Date.append("-");
                                selectedMonth++;
                                conver = Integer.toString(selectedMonth);
                                Date.append(conver);
                                Date.append("-");
                                conver = Integer.toString(selectedDay);
                                Date.append(conver);

                                etAnniversaryDate8RS.setText(Date.toString());
                                isetDateofBirth = true;
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


        radioButtonBuyYes19RS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etPlaceName19RS.setVisibility(View.VISIBLE);
                    etKmTravel19RS.setVisibility(View.VISIBLE);
                    etHowOften19RS.setVisibility(View.VISIBLE);
                    radioButtonBuyNo19RS.setChecked(false);

                }
            }
        });
        radioButtonBuyNo19RS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etPlaceName19RS.setVisibility(View.INVISIBLE);
                    etKmTravel19RS.setVisibility(View.INVISIBLE);
                    etHowOften19RS.setVisibility(View.INVISIBLE);
                    radioButtonBuyYes19RS.setChecked(false);

                }
            }
        });


        radioButtonServiceToDoorYes20RS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    spinnerServiceToDoor20RS.setVisibility(View.VISIBLE);
                    radioButtonServiceToDoorNo20RS.setChecked(false);

                }
            }
        });
        radioButtonServiceToDoorNo20RS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    spinnerServiceToDoor20RS.setVisibility(View.INVISIBLE);
                    radioButtonServiceToDoorYes20RS.setChecked(false);
                }
            }
        });


        radioButtonetFinanceAmountYes24RS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etFinanceAmount24RS.setVisibility(View.VISIBLE);
                    radioButtonBuyNo19RS.setChecked(false);

                }
            }
        });
        radioButtonetFinanceAmountNo24RS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etFinanceAmount24RS.setVisibility(View.INVISIBLE);
                    radioButtonBuyYes19RS.setChecked(false);
                }
            }
        });


        //Todo: Live Location
        fusedLocationProvideClient = LocationServices.getFusedLocationProviderClient(this);

        LiveLocation28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(RetailerSurveyRegistrationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {

                    ActivityCompat.requestPermissions(RetailerSurveyRegistrationActivity.this
                            , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);

                }
            }
        });


        btnRetailerSurveyRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AG dg_inst = AG.getInstance();
                System.out.println("jyo name" + etName1RS);


                //todo: if none of the spinner seleted
                String textSpnAvaiHul = spinnerAvailabiltyHUL18RS.getSelectedItem().toString();
                String textSpnFreqHul = spinnerFrequencyServiceHUL18RS.getSelectedItem().toString();
                //  boolean booleanSpnAvailHul = Boolean.parseBoolean(textSpnAvaiHul);


//                if (spinnerAvailabiltyHUL18RS.getSelectedItem().toString().equals("Yes"))  {
//                    if (spinnerFrequencyServiceHUL18RS.getSelectedItem().toString().equals("No") ||
//                            spinnerFrequencyServiceHUL18RS.getSelectedItem().toString().equals("Yes")) {
//
//                        //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
//                    } else {
//
//
//                        TextView errorTextview = (TextView)spinnerFrequencyServiceHUL18RS.getSelectedView();
//                        //  errorTextview.setError("");
//                        errorTextview.setTextColor(Color.RED);//
//                        errorTextview.setError("Select Frequency Of Service");
//
//                        ((TextView)spinnerFrequencyServiceHUL18RS.getSelectedView()).setError("Error message");
//
//                    }
//                }
//                else if (spinnerAvailabiltyHUL18RS.getSelectedItem().toString().equals("No")) {
//
//                }
//               else {
//                    TextView errorTextview = (TextView)spinnerAvailabiltyHUL18RS.getSelectedView();
//                    //  errorTextview.setError("");
//                    errorTextview.setTextColor(Color.RED);
//                    errorTextview.setError("Select Availabilty");
//                }


                if (etName1RS.getText().toString().length() == 0) {
                    etName1RS.setError("Enter Name");
                    return;
                }
                dg_inst.getUser().setRsName(etName1RS.getText().toString());

                if (etpermanentNumber2RS.getText().toString().length() == 0) {
                    etpermanentNumber2RS.setError("Enter Mobile Number");
                    return;
                }
                if (etpermanentNumber2RS.getText().toString().length() < 10) {
                    etpermanentNumber2RS.setError("Enter 10 digit Number");
                    return;
                }
                dg_inst.getUser().setRsPermanentNumber(etpermanentNumber2RS.getText().toString());


                if (spinnerTypeOfFirm3RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerTypeOfFirm3RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Type of Firm");

                }


                if (etVillagePapulation4RS.getText().toString().length() == 0) {
                    etVillagePapulation4RS.setError("Enter Village Papulation");
                    return;
                }
                dg_inst.getUser().setRsVillagePApulation(etVillagePapulation4RS.getText().toString());

                if (etOwnerName5RS.getText().toString().length() == 0) {
                    etOwnerName5RS.setError("Enter Owner or Owner's Name");
                    return;
                }
                dg_inst.getUser().setRsOwnerName(etOwnerName5RS.getText().toString());

                if (etEmail6RS.getText().toString().length() == 0) {
                    etEmail6RS.setError("Enter Email ID");
                    return;
                }
                dg_inst.getUser().setRsEmail(etEmail6RS.getText().toString());

                if (etDob7RS.getText().toString().equals("")) {
                    etDob7RS.setError("Select Date of Birth");
                    return;
                }
                if (etDob7RS.getText().toString().length() == 0) {
                    etDob7RS.setError("Enter Date of Birth");
                    return;
                }

                dg_inst.getUser().setRsDob(etDob7RS.getText().toString());

                if (etAnniversaryDate8RS.getText().toString().length() == 0) {
                    etAnniversaryDate8RS.setError("Enter Anniversary Date");
                    return;
                }
                dg_inst.getUser().setRsAnniversaryDate(etAnniversaryDate8RS.getText().toString());

                if (etGstNo9RS.getText().toString().length() == 0) {
                    etGstNo9RS.setError("Enter GST Number");
                    return;
                }
                dg_inst.getUser().setRsGstNo(etGstNo9RS.getText().toString());

                if (etPanNo10RS.getText().toString().length() == 0) {
                    etPanNo10RS.setError("Enter PAN Number");
                    return;
                }
                dg_inst.getUser().setRsPanNo(etPanNo10RS.getText().toString());

                if (etAddress11RS.getText().toString().length() == 0) {
                    etAddress11RS.setError("Enter PAN Number");
                    return;
                }
                dg_inst.getUser().setRsAddress(etAddress11RS.getText().toString());


                if (etLandMark12RS.getText().toString().length() == 0) {
                    etLandMark12RS.setError("Enter LandMark");
                    return;
                }
                dg_inst.getUser().setRsLandMark(etLandMark12RS.getText().toString());

                if (etAlternateNo13RS.getText().toString().length() == 0) {
                    etAlternateNo13RS.setError("Enter Alternate  Number");
                    return;
                }
                if (etAlternateNo13RS.getText().toString().length() < 10) {
                    etAlternateNo13RS.setError("Enter 10 Digit  Number");
                    return;
                }
                dg_inst.getUser().setRsAlternateNo(etAlternateNo13RS.getText().toString());

                if (etWhatsappNumber14RS.getText().toString().length() == 0) {
                    etWhatsappNumber14RS.setError("Enter  Whatsapp Number");
                    return;
                }
                if (etWhatsappNumber14RS.getText().toString().length() < 10) {
                    etWhatsappNumber14RS.setError("Enter 10 Digit Number");
                    return;
                }
                dg_inst.getUser().setRsWhatsappNumber(etWhatsappNumber14RS.getText().toString());

                if (etExperience15RS.getText().toString().length() == 0) {
                    etExperience15RS.setError("Enter Experience");
                    return;
                }
                dg_inst.getUser().setRsExperience(etExperience15RS.getText().toString());


                if (spinnerTypeOfRetailer16RS.getSelectedItemPosition() > 0) {

                }
//                else if (spinnerTypeOfRetailer16RS.getSelectedItemPosition() == 19) {
////                    dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
//                }
                else {

                    TextView errorTextview = (TextView) spinnerTypeOfRetailer16RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Type of Retailer");

                }

                // Todo: to store Type Of Retailer Other
                if (spinnerTypeOfRetailer16RS.getSelectedItemPosition() == 19) {
                    dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                }

                if (chkWhatsapp17RS.isChecked()) {
                    dg_inst.getUser().setRsSocialMedia(chkWhatsapp17RS.getText().toString());
                } else if (chkFacebook17RS.isChecked()) {
                    dg_inst.getUser().setRsSocialMedia(chkFacebook17RS.getText().toString());
                } else {
                    tvSocialMedia17RS.setError("Enter Social Media");
                }
//                if (chkFacebook17RS.isChecked()){
//                    dg_inst.getUser().setRsSocialMedia(chkFacebook17RS.getText().toString());
//
//                }
                //  dg_inst.getUser().setRsSocialMedia(chkTwitter17RS.getText().toString());


//                if (spinnerFrequencyServiceHUL18RS.getSelectedItemPosition() > 0) {
//
//                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
//                } else {
//
//
//                    TextView errorTextview = (TextView) spinnerFrequencyServiceHUL18RS.getSelectedView();
//                    //  errorTextview.setError("");
//                    errorTextview.setTextColor(Color.RED);//
//                    errorTextview.setError("Select Frequency Of Service");
//
//                }


                if (textSpnAvaiHul.equals("Yes")) {

//                    if (textSpnFreqHul.equals("Daily") || textSpnFreqHul.equals("Alternative Day") ||
//                        textSpnFreqHul.equals("Weekly") || textSpnFreqHul.equals("Alternative Week") ||
//                            textSpnFreqHul.equals("Monthly Day")){
//
//
//                    }else {
//                        tvBrandNameHUL18RS.setError("Select");
//                        return;
//                    }

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else if (textSpnAvaiHul.equals("No")) {


                } else {
                    tvBrandNameHUL18RS.setError("Select");
                    return;

//                        TextView errorTextview2 = (TextView)spinnerAvailabiltyHUL18RS.getSelectedView();
//                        //  errorTextview.setError("");
//                        errorTextview2.setTextColor(Color.RED);//
//                        errorTextview2.setError("Select Availabilty Of Service");
//                        errorTextview2.setText("Error Select Availabilty");
                }


                if (spinnerAvailabiltyTATA18RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerAvailabiltyTATA18RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Availabilty");

                }


                if (spinnerFrequencyServiceTATA18RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerFrequencyServiceTATA18RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Frequency Of Service");

                }


                if (spinnerAvailabiltyITC18RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerAvailabiltyITC18RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Availabilty");

                }


                if (spinnerFrequencyServiceITC18RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerFrequencyServiceITC18RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Frequency Of Service");

                }

                if (spinnerAvailabiltyNestle183RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerAvailabiltyNestle183RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Availabilty");

                }

                if (spinnerFrequencyServiceNestle183RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerFrequencyServiceNestle183RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Frequency Of Service");

                }

                if (spinnerAvailabiltyCadburys184RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerAvailabiltyCadburys184RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Availabilty");

                }

                if (spinnerFrequencyServiceCadburys184RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerFrequencyServiceCadburys184RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Frequency Of Service");

                }


                if (spinnerAvailabiltyKaleeshwari185RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerAvailabiltyKaleeshwari185RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Availabilty");

                }

                if (spinnerFrequencyServiceKaleeshwari185RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerFrequencyServiceKaleeshwari185RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Frequency Of Service");

                }

                if (spinnerAvailabilty_Dabur186RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerAvailabilty_Dabur186RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Availabilty");

                }

                if (spinnerFrequencyService_Dabur186RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerFrequencyService_Dabur186RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Frequency Of Service");

                }

                if (spinnerAvailabilty_britannia187RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerAvailabilty_britannia187RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Availabilty");

                }

                if (spinnerFrequencyService_britannia187RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerFrequencyService_britannia187RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Frequency Of Service");

                }

                if (spinnerAvailabilty_Kwalitywalls188RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerAvailabilty_Kwalitywalls188RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Availabilty");

                }

                if (spinnerFrequencyService_Kwalitywalls188RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerFrequencyService_Kwalitywalls188RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Frequency Of Service");

                }


                if (spinnerAvailabilty_Hatsunagro189RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerAvailabilty_Hatsunagro189RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Availabilty");

                }

                if (spinnerFrequencyService_Hatsunagro189RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerFrequencyService_Hatsunagro189RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Frequency Of Service");

                }


                if (spinnerAvailabilty_Heritage1810RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerAvailabilty_Heritage1810RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Availabilty");

                }

                if (spinnerFrequencyService_Heritage1810RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerFrequencyService_Heritage1810RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Frequency Of Service");

                }


                if (spinnerAvailabilty_MTR1811RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerAvailabilty_MTR1811RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Availabilty");

                }

                if (spinnerFrequencyService_MTR1811RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerFrequencyService_MTR1811RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Frequency Of Service");

                }

                if (spinnerAvailabilty_Sakthi1812RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerAvailabilty_Sakthi1812RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Availabilty");

                }

                if (spinnerFrequencyService_Sakthi1812RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerFrequencyService_Sakthi1812RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Frequency Of Service");

                }

                if (spinnerAvailabilty_Amul1813RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerAvailabilty_Amul1813RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Availabilty");

                }

                if (spinnerFrequencyService_Amul1813RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerFrequencyService_Amul1813RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Frequency Of Service");

                }


                if (spinnerAvailabilty_Everest1814RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerAvailabilty_Everest1814RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Availabilty");

                }

                if (spinnerFrequencyService_Everest1814RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerFrequencyService_Everest1814RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Frequency Of Service");

                }


                if (spinnerAvailabilty_Haldirams1815RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerAvailabilty_Haldirams1815RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Availabilty");

                }

                if (spinnerFrequencyService_Haldirams1815RS.getSelectedItemPosition() > 0) {

                    //dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());
                } else {


                    TextView errorTextview = (TextView) spinnerFrequencyService_Haldirams1815RS.getSelectedView();
                    //  errorTextview.setError("");
                    errorTextview.setTextColor(Color.RED);//
                    errorTextview.setError("Select Frequency Of Service");

                }


                if (!radioButtonBuyYes19RS.isChecked()) {

                    Log.d("RetailerSurveyActivity", "Check anyone radio button");
                    // radioGroupBuyStockOuside19RS.setErro
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RetailerSurveyRegistrationActivity.this);
                    alertDialogBuilder.setTitle("Please Select Buy Stock from Wholeseller Yes or No");
                    //   alertDialogBuilder.setMessage("Please Select Buy Stock from Wholeseller Yes or No");

                    alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }

                //Todo: radio button yes clicked get the value
//                if (radioGroupBuyStockOuside19RS.getCheckedRadioButtonId() == 0)
//                {
//
//                    if (!radioButtonBuyYes19RS.isChecked() && !radioButtonBuyNo19RS.isChecked()){
//                            Log.d("RetailerSurveyActivity","Check anyone radio button");
//                       // radioGroupBuyStockOuside19RS.setErro
//                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RetailerSurveyRegistrationActivity.this);
//                        alertDialogBuilder.setMessage("Please Select Buy Stock from Wholeseller Yes or No");
////                        alertDialogBuilder.setPositiveButton("yes",
////                                new DialogInterface.OnClickListener() {
////                                    @Override
////                                    public void onClick(DialogInterface arg0, int arg1) {
////
////                                        Intent intent1= new Intent(RetailerSurveyRegistrationActivity.this, FirstActivity.class);
////                                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
////                                        startActivity(intent1);
////                                        finish();
////                                    }
////                                });
//
//
//                        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.cancel();
//                            }
//                        });
//
//                        AlertDialog alertDialog = alertDialogBuilder.create();
//                        alertDialog.show();
//                    }
//                }
//                else {
//                    tvBuyStock19RS.setTextColor(Color.BLACK);
////                    tvBuyStock19RS.setError(" Buying Frequency");
//                }


//                if (radioButtonBuyYes19RS.isChecked() || radioButtonBuyNo19RS.isChecked()){
//
//                }else {
//                    tvBuyStock19RS.setError("");
//                }

                if (radioButtonBuyYes19RS.isChecked()) {
//                    tvBuyStock19RS.setTextColor(Color.blue);

                    dg_inst.getUser().setRsBuyWholesellerStock(radioButtonBuyYes19RS.getText().toString());

                    if (etPlaceName19RS.getText().toString().length() == 0) {
                        etPlaceName19RS.setError("Enter Place Name");
                        return;
                    }
                    dg_inst.getUser().setRsPlaceName(etPlaceName19RS.getText().toString());

                    if (etKmTravel19RS.getText().toString().length() == 0) {
                        etKmTravel19RS.setError("Enter Km's to travel");
                        return;
                    }
                    dg_inst.getUser().setRsKmToTravel(etKmTravel19RS.getText().toString());

                    if (etHowOften19RS.getText().toString().length() == 0) {
                        etHowOften19RS.setError("Enter How Often");
                        return;
                    }
                    dg_inst.getUser().setRsHowOften(etHowOften19RS.getText().toString());
                } else if (radioButtonBuyNo19RS.isChecked()) {

                    dg_inst.getUser().setRsBuyWholesellerStock(radioButtonBuyNo19RS.getText().toString());
                    dg_inst.getUser().setRsPlaceName(radioButtonBuyNo19RS.getText().toString());
                    dg_inst.getUser().setRsKmToTravel(radioButtonBuyNo19RS.getText().toString());
                    dg_inst.getUser().setRsHowOften(radioButtonBuyNo19RS.getText().toString());
                } else {
//                    Log.d("QAOD", "Gender is Null");
//                    tvBuyStock19RS.setTextColor(Color.RED);//
//                    tvBuyStock19RS.setError(" Buying Frequency");
                }

                //Todo: radio button Service To Doorstep checked or not
                if (!radioButtonServiceToDoorYes20RS.isChecked() && !radioButtonServiceToDoorNo20RS.isChecked()) {
                    Log.d("RetailerSurveyActivity", "Check anyone radio button");
                    // radioGroupBuyStockOuside19RS.setError
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RetailerSurveyRegistrationActivity.this);
                    alertDialogBuilder.setMessage("Please Select Are You Ready to Order Yes or No");

                    alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }


                //Todo: radio button Service To Doorstep checked or not
                if (radioButtonServiceToDoorYes20RS.isChecked()) {
                    dg_inst.getUser().setRsservice(radioButtonServiceToDoorYes20RS.getText().toString());

                    if (spinnerServiceToDoor20RS.getSelectedItemPosition() > 0) {

                    } else {


                        TextView errorTextview = (TextView) spinnerServiceToDoor20RS.getSelectedView();
                        errorTextview.setTextColor(Color.RED);//
                        errorTextview.setError("Select Buying Frequency");

                    }
                }
                if (radioButtonServiceToDoorNo20RS.isChecked()) {
                    dg_inst.getUser().setRsservice(radioButtonServiceToDoorNo20RS.getText().toString());
                    dg_inst.getUser().setRsServiceBuyingFrequency(radioButtonServiceToDoorNo20RS.getText().toString());
                }


                //Todo: radio button checked or not


                if (etAverageCustomerPerDay21RS.getText().toString().length() == 0) {
                    etAverageCustomerPerDay21RS.setError("Enter Average Customer Walkin's Per Day");
                    return;
                }
                dg_inst.getUser().setRsAverageCustomerDay(etAverageCustomerPerDay21RS.getText().toString());

                if (etAverageSalesPerWeek22RS.getText().toString().length() == 0) {
                    etAverageSalesPerWeek22RS.setError("Enter Average Sales Per Week");
                    return;
                }
                dg_inst.getUser().setRsAverageSalesWeek(etAverageSalesPerWeek22RS.getText().toString());

                if (etAverageSalesMoth23RS.getText().toString().length() == 0) {
                    etAverageSalesMoth23RS.setError("Enter Average Sales Per Month");
                    return;
                }
                dg_inst.getUser().setRsAverageSalesMonth(etAverageSalesMoth23RS.getText().toString());

                //Todo: radio Button Finance Amount checked or not
                if (radioGroupFinanceAmount24RS.getCheckedRadioButtonId() == -1) {
                    if (!radioButtonetFinanceAmountYes24RS.isChecked() && !radioButtonetFinanceAmountNo24RS.isChecked()) {
                        Log.d("RetailerSurveyActivity", "Check anyone radio button");

                        // radioGroupBuyStockOuside19RS.setErro
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RetailerSurveyRegistrationActivity.this);
                        alertDialogBuilder.setMessage("Please Select Credit facility Required Yes or No");

                        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                }


                if (radioButtonetFinanceAmountYes24RS.isChecked()) {
                    dg_inst.getUser().setRsFinance(radioButtonetFinanceAmountYes24RS.getText().toString());
                    if (etFinanceAmount24RS.getText().toString().length() == 0) {
                        etFinanceAmount24RS.setError("Enter Finance Amount");
                        return;
                    }
                    dg_inst.getUser().setRsFinanceAmount(etFinanceAmount24RS.getText().toString());

                }
                if (radioButtonetFinanceAmountNo24RS.isChecked()) {
                    dg_inst.getUser().setRsFinance(radioButtonetFinanceAmountNo24RS.getText().toString());
                    dg_inst.getUser().setRsFinanceAmount(radioButtonetFinanceAmountNo24RS.getText().toString());
                }

                //Todo: radio Button  checked or not
                if (!radioButtonShopInsuranceAvailableYes24RS.isChecked() && !radioButtonShopInsuranceAvailableNo24RS.isChecked()) {
                    Log.d("RetailerSurveyActivity", "Check anyone radio button");
                    // radioGroupBuyStockOuside19RS.setErro
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RetailerSurveyRegistrationActivity.this);
                    alertDialogBuilder.setMessage("Please Select Shop Insurance Available Yes or No");

                    alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }


                if (radioButtonShopInsuranceAvailableYes24RS.isChecked()) {
                    dg_inst.getUser().setRsShopInsuranceAvailable(radioButtonShopInsuranceAvailableYes24RS.getText().toString());
                }
                if (radioButtonShopInsuranceAvailableNo24RS.isChecked()) {
                    dg_inst.getUser().setRsShopInsuranceAvailable(radioButtonShopInsuranceAvailableNo24RS.getText().toString());
                }


                //Todo: radio Button  checked or not
                if (!radioButtonShopInsuranceRequiredYes24RS.isChecked() && !radioButtonShopInsuranceRequiredNo24RS.isChecked()) {
                    Log.d("RetailerSurveyActivity", "Check anyone radio button");
                    // radioGroupBuyStockOuside19RS.setErro
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RetailerSurveyRegistrationActivity.this);
                    alertDialogBuilder.setMessage("Please Select Shop Insurance Required Yes or No");

                    alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }

                if (radioButtonShopInsuranceRequiredYes24RS.isChecked()) {
                    dg_inst.getUser().setRsShopInsuranceRequired(radioButtonShopInsuranceRequiredYes24RS.getText().toString());
                }
                if (radioButtonShopInsuranceRequiredNo24RS.isChecked()) {
                    dg_inst.getUser().setRsShopInsuranceRequired(radioButtonShopInsuranceRequiredNo24RS.getText().toString());
                }

                //Todo: radio Button  checked or not
                if (!radioButtonMedicalInsuranceAvailableYes25RS.isChecked() && !radioButtonMedicalInsuranceAvailableNo25RS.isChecked()) {
                    Log.d("RetailerSurveyActivity", "Check anyone radio button");
                    // radioGroupBuyStockOuside19RS.setErro
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RetailerSurveyRegistrationActivity.this);
                    alertDialogBuilder.setMessage("Please Select Medical Insurance Available Yes or No");

                    alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }

                if (radioButtonMedicalInsuranceAvailableYes25RS.isChecked()) {
                    dg_inst.getUser().setRsMedicalInsuranceAvailable(radioButtonMedicalInsuranceAvailableYes25RS.getText().toString());
                }
                if (radioButtonMedicalInsuranceAvailableNo25RS.isChecked()) {
                    dg_inst.getUser().setRsMedicalInsuranceAvailable(radioButtonMedicalInsuranceAvailableNo25RS.getText().toString());
                }

                //Todo: radio Button  checked or not
                if (!radioButtonMedicalInsuranceRequiredYes25RS.isChecked() && !radioButtonMedicalInsuranceRequiredNo25RS.isChecked()) {
                    Log.d("RetailerSurveyActivity", "Check anyone radio button");
                    // radioGroupBuyStockOuside19RS.setErro
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RetailerSurveyRegistrationActivity.this);
                    alertDialogBuilder.setMessage("Please Select Medical Insurance Required Yes or No");

                    alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }

                if (radioButtonMedicalInsuranceRequiredYes25RS.isChecked()) {
                    dg_inst.getUser().setRsMedicalInsuranceRequired(radioButtonMedicalInsuranceRequiredYes25RS.getText().toString());
                }
                if (radioButtonMedicalInsuranceRequiredNo25RS.isChecked()) {
                    dg_inst.getUser().setRsMedicalInsuranceRequired(radioButtonMedicalInsuranceRequiredNo25RS.getText().toString());
                }

                //Todo: radio Button  checked or not
                if (!radioButtonLifeInsuranceAvailableYes26RS.isChecked() && !radioButtonLifeInsuranceAvailableNo26RS.isChecked()) {
                    Log.d("RetailerSurveyActivity", "Check anyone radio button");
                    // radioGroupBuyStockOuside19RS.setErro
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RetailerSurveyRegistrationActivity.this);
                    alertDialogBuilder.setMessage("Please Select Life Insurance Available Yes or No");

                    alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }

                if (radioButtonLifeInsuranceAvailableYes26RS.isChecked()) {
                    dg_inst.getUser().setRsLifeInsuranceAvailable(radioButtonLifeInsuranceAvailableYes26RS.getText().toString());
                }
                if (radioButtonLifeInsuranceAvailableNo26RS.isChecked()) {
                    dg_inst.getUser().setRsLifeInsuranceAvailable(radioButtonLifeInsuranceAvailableNo26RS.getText().toString());
                }

                //Todo: radio Button  checked or not
                if (!radioButtonLifeInsuranceRequiredYes26RS.isChecked() && !radioButtonLifeInsuranceRequiredNo26RS.isChecked()) {
                    Log.d("RetailerSurveyActivity", "Check anyone radio button");
                    // radioGroupBuyStockOuside19RS.setErro
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RetailerSurveyRegistrationActivity.this);
                    alertDialogBuilder.setMessage("Please Select Life Insurance Required Yes or No");

                    alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }

                if (radioButtonLifeInsuranceRequiredYes26RS.isChecked()) {
                    dg_inst.getUser().setRsLifeInsuranceRequired(radioButtonLifeInsuranceRequiredYes26RS.getText().toString());
                }
                if (radioButtonLifeInsuranceRequiredNo26RS.isChecked()) {
                    dg_inst.getUser().setRsLifeInsuranceRequired(radioButtonLifeInsuranceRequiredNo26RS.getText().toString());
                }

                //Todo: radio Button  checked or not
                if (!radioButtonTermInsuranceAvailableYes27RS.isChecked() && !radioButtonTermInsuranceAvailableNo27RS.isChecked()) {
                    Log.d("RetailerSurveyActivity", "Check anyone radio button");
                    // radioGroupBuyStockOuside19RS.setErro
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RetailerSurveyRegistrationActivity.this);
                    alertDialogBuilder.setMessage("Please Select Term Insurance Available Yes or No");

                    alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }

                if (radioButtonTermInsuranceAvailableYes27RS.isChecked()) {
                    dg_inst.getUser().setRsTermInsuranceAvailable(radioButtonTermInsuranceAvailableYes27RS.getText().toString());
                }
                if (radioButtonTermInsuranceAvailableNo27RS.isChecked()) {
                    dg_inst.getUser().setRsTermInsuranceAvailable(radioButtonTermInsuranceAvailableNo27RS.getText().toString());
                }

                //Todo: radio Button  checked or not
                if (!radioButtonTermInsuranceRequiredYes27RS.isChecked() && !radioButtonTermInsuranceRequiredNo27RS.isChecked()) {
                    Log.d("RetailerSurveyActivity", "Check anyone radio button");
                    // radioGroupBuyStockOuside19RS.setErro
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RetailerSurveyRegistrationActivity.this);
                    alertDialogBuilder.setMessage("Please Select Term Insurance Required Yes or No");

                    alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }

                if (radioButtonTermInsuranceRequiredYes27RS.isChecked()) {
                    dg_inst.getUser().setRsTermInsuranceRequired(radioButtonTermInsuranceRequiredYes27RS.getText().toString());
                }
                if (radioButtonTermInsuranceRequiredNo27RS.isChecked()) {
                    dg_inst.getUser().setRsTermInsuranceRequired(radioButtonTermInsuranceRequiredNo27RS.getText().toString());
                }


                try {
                    AsyncTask<String, Void, Integer> result = new RetailerSurveyRegister().execute();
                    int code = result.get();
                    System.out.println("jyo reg code" + code);
                    if (code == 200) {

                        Dbhelper.createVendorDbHelper(RetailerSurveyRegistrationActivity.this);
                        Dao vendorDetailsDao = new Dao();
                        vendorDetailsDao.getUserDetails();

                        Toast.makeText(RetailerSurveyRegistrationActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(RetailerSurveyRegistrationActivity.this, HomeActivity.class);
                        startActivity(i);
                        //  finish();
                    } else {
                        Toast.makeText(RetailerSurveyRegistrationActivity.this, "Failed to Register", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {

                    Toast.makeText(RetailerSurveyRegistrationActivity.this, "Fail to Register", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        //   Toast.makeText(this, "len = " + position, Toast.LENGTH_SHORT).show();

        Spinner spinnerFrequencyServiceHUL18RS = (Spinner) adapterView;
        //Todo: If HUL Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyServiceHUL18RS.getId() == R.id.spinnerFrequencyServiceHUL18RS) {

            if (spinnerAvailabiltyHUL18RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsHulFrequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyServiceTATA18RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyServiceTATA18RS.getId() == R.id.spinnerFrequencyServiceTATA18RS) {

            if (spinnerAvailabiltyTATA18RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsTata_Frequency(brandFrequncyOfService[position].trim());
            }

        }


        Spinner spinnerFrequencyServiceITC18RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyServiceITC18RS.getId() == R.id.spinnerFrequencyServiceITC18RS) {

            if (spinnerAvailabiltyITC18RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsItc_Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyServiceNestle183RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyServiceNestle183RS.getId() == R.id.spinnerFrequencyServiceNestle183RS) {

            if (spinnerAvailabiltyNestle183RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsNestle_Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyServiceCadburys184RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyServiceCadburys184RS.getId() == R.id.spinnerFrequencyServiceCadburys184RS) {

            if (spinnerAvailabiltyCadburys184RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsCadburys_Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyServiceKaleeshwari185RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyServiceKaleeshwari185RS.getId() == R.id.spinnerFrequencyServiceKaleeshwari185RS) {

            if (spinnerAvailabiltyKaleeshwari185RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsKaleeshwari_Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyService_Dabur186RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_Dabur186RS.getId() == R.id.spinnerFrequencyService_Dabur186RS) {

            if (spinnerAvailabilty_Dabur186RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsDaburFrequency(brandFrequncyOfService[position].trim());
            }

        }


        Spinner spinnerFrequencyService_britannia187RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_britannia187RS.getId() == R.id.spinnerFrequencyService_britannia187RS) {

            if (spinnerAvailabilty_britannia187RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsBritannia_Frequency(brandFrequncyOfService[position].trim());
            }

        }


        Spinner spinnerFrequencyService_Kwalitywalls188RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_Kwalitywalls188RS.getId() == R.id.spinnerFrequencyService_Kwalitywalls188RS) {

            if (spinnerAvailabilty_Kwalitywalls188RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsKwality_Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyService_Hatsunagro189RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_Hatsunagro189RS.getId() == R.id.spinnerFrequencyService_Hatsunagro189RS) {

            if (spinnerAvailabilty_Hatsunagro189RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsHatsun_Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyService_Heritage1810RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_Heritage1810RS.getId() == R.id.spinnerFrequencyService_Heritage1810RS) {

            if (spinnerAvailabilty_Heritage1810RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsHeritage_Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyService_MTR1811RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_MTR1811RS.getId() == R.id.spinnerFrequencyService_MTR1811RS) {

            if (spinnerAvailabilty_MTR1811RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsmtr_Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyService_Sakthi1812RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_Sakthi1812RS.getId() == R.id.spinnerFrequencyService_Sakthi1812RS) {

            if (spinnerAvailabilty_Sakthi1812RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRssakthiFrequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyService_Amul1813RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_Amul1813RS.getId() == R.id.spinnerFrequencyService_Amul1813RS) {

            if (spinnerAvailabilty_Amul1813RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsamul__Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyService_Everest1814RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_Everest1814RS.getId() == R.id.spinnerFrequencyService_Everest1814RS) {

            if (spinnerAvailabilty_Everest1814RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRseverest_Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyService_Haldirams1815RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_Haldirams1815RS.getId() == R.id.spinnerFrequencyService_Haldirams1815RS) {

            if (spinnerAvailabilty_Haldirams1815RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRshaldirams_Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyService_Colgate1816RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_Colgate1816RS.getId() == R.id.spinnerFrequencyService_Colgate1816RS) {

            if (spinnerAvailabilty_Colgate1816RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRscolgate_Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyService_Motherdairy1817RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_Motherdairy1817RS.getId() == R.id.spinnerFrequencyService_Motherdairy1817RS) {

            if (spinnerAvailabilty_Motherdairy1817RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsmotherdi_Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyService_Parle1818RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_Parle1818RS.getId() == R.id.spinnerFrequencyService_Parle1818RS) {

            if (spinnerAvailabilty_Parle1818RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsparle_Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyService_Biskfarm1819RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_Biskfarm1819RS.getId() == R.id.spinnerFrequencyService_Biskfarm1819RS) {

            if (spinnerAvailabilty_Biskfarm1819RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsbiskfarm_Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyService_ReiAgro1820RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_ReiAgro1820RS.getId() == R.id.spinnerFrequencyService_ReiAgro1820RS) {

            if (spinnerAvailabilty_ReiAgro1820RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsreiagro_Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyService_Rucisoya1821RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_Rucisoya1821RS.getId() == R.id.spinnerFrequencyService_Rucisoya1821RS) {

            if (spinnerAvailabilty_Rucisoya1821RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsruchi_Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyService_Idhayam1822RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_Idhayam1822RS.getId() == R.id.spinnerFrequencyService_Idhayam1822RS) {

            if (spinnerAvailabilty_Idhayam1822RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsidhayam_Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyService_Jubilantfood1823RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_Jubilantfood1823RS.getId() == R.id.spinnerFrequencyService_Jubilantfood1823RS) {

            if (spinnerAvailabilty_Jubilantfood1823RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsjubliant_Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyService_Coke1824RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_Coke1824RS.getId() == R.id.spinnerFrequencyService_Coke1824RS) {

            if (spinnerAvailabilty_Coke1824RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRscoke_Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyService_Pepsi1825RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_Pepsi1825RS.getId() == R.id.spinnerFrequencyService_Pepsi1825RS) {

            if (spinnerAvailabilty_Pepsi1825RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRspepsi_Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyService_Bisleri1826RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_Bisleri1826RS.getId() == R.id.spinnerFrequencyService_Bisleri1826RS) {

            if (spinnerAvailabilty_Bisleri1826RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsbisleri_Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyService_GSK1827RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_GSK1827RS.getId() == R.id.spinnerFrequencyService_GSK1827RS) {

            if (spinnerAvailabilty_GSK1827RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsgsk_Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyService_Patanjali1828RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_Patanjali1828RS.getId() == R.id.spinnerFrequencyService_Patanjali1828RS) {

            if (spinnerAvailabilty_Patanjali1828RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRspathanjalli_Frequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyService_Marico1829RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_Marico1829RS.getId() == R.id.spinnerFrequencyService_Marico1829RS) {

            if (spinnerAvailabilty_Marico1829RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsmaricoFrequency(brandFrequncyOfService[position].trim());
            }

        }

        Spinner spinnerFrequencyService_PG1830RS = (Spinner) adapterView;
        //Todo: If  Availabilty Yes selected getting the Seleceted Spinner
        if (spinnerFrequencyService_PG1830RS.getId() == R.id.spinnerFrequencyService_PG1830RS) {

            if (spinnerAvailabilty_PG1830RS.getSelectedItem().toString().equals("Yes")) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRspg_Frequency(brandFrequncyOfService[position].trim());
            }

        }


        Spinner spinnerTypeOfFirm3RS = (Spinner) adapterView;
        Spinner spinnerTypeOfRetailer16RS = (Spinner) adapterView;
        Spinner spinnerAvailabiltyHUL18RS = (Spinner) adapterView;
        Spinner spinnerAvailabiltyTATA18RS = (Spinner) adapterView;
        Spinner spinnerAvailabiltyITC18RS = (Spinner) adapterView;

        Spinner spinnerAvailabiltyNestle183RS = (Spinner) adapterView;
        Spinner spinnerAvailabiltyCadburys184RS = (Spinner) adapterView;
        Spinner spinnerAvailabiltyKaleeshwari185RS = (Spinner) adapterView;

        Spinner spinnerAvailabilty_Dabur186RS = (Spinner) adapterView;
        Spinner spinnerAvailabilty_britannia187RS = (Spinner) adapterView;
        Spinner spinnerAvailabilty_Kwalitywalls188RS = (Spinner) adapterView;
        Spinner spinnerAvailabilty_Hatsunagro189RS = (Spinner) adapterView;
        Spinner spinnerAvailabilty_Heritage1810RS = (Spinner) adapterView;

        Spinner spinnerAvailabilty_MTR1811RS = (Spinner) adapterView;
        Spinner spinnerAvailabilty_Sakthi1812RS = (Spinner) adapterView;
        Spinner spinnerAvailabilty_Amul1813RS = (Spinner) adapterView;
        Spinner spinnerAvailabilty_Everest1814RS = (Spinner) adapterView;
        Spinner spinnerAvailabilty_Haldirams1815RS = (Spinner) adapterView;

        Spinner spinnerAvailabilty_Colgate1816RS = (Spinner) adapterView;
        Spinner spinnerAvailabilty_Motherdairy1817RS = (Spinner) adapterView;
        Spinner spinnerAvailabilty_Parle1818RS = (Spinner) adapterView;
        Spinner spinnerAvailabilty_Biskfarm1819RS = (Spinner) adapterView;
        Spinner spinnerAvailabilty_ReiAgro1820RS = (Spinner) adapterView;

        Spinner spinnerAvailabilty_Rucisoya1821RS = (Spinner) adapterView;
        Spinner spinnerAvailabilty_Idhayam1822RS = (Spinner) adapterView;
        Spinner spinnerAvailabilty_Jubilantfood1823RS = (Spinner) adapterView;
        Spinner spinnerAvailabilty_Coke1824RS = (Spinner) adapterView;
        Spinner spinnerAvailabilty_Pepsi1825RS = (Spinner) adapterView;

        Spinner spinnerAvailabilty_Bisleri1826RS = (Spinner) adapterView;
        Spinner spinnerAvailabilty_GSK1827RS = (Spinner) adapterView;
        Spinner spinnerAvailabilty_Patanjali1828RS = (Spinner) adapterView;
        Spinner spinnerAvailabilty_Marico1829RS = (Spinner) adapterView;
        Spinner spinnerAvailabilty_PG1830RS = (Spinner) adapterView;

        Spinner spinnerServiceToDoor20RS = (Spinner) adapterView;
        spinnerFrequencyServiceHUL18RS = findViewById(R.id.spinnerFrequencyServiceHUL18RS);
        spinnerFrequencyServiceTATA18RS = findViewById(R.id.spinnerFrequencyServiceTATA18RS);
        spinnerFrequencyServiceITC18RS = findViewById(R.id.spinnerFrequencyServiceITC18RS);

        spinnerFrequencyServiceNestle183RS = findViewById(R.id.spinnerFrequencyServiceNestle183RS);
        spinnerFrequencyServiceCadburys184RS = findViewById(R.id.spinnerFrequencyServiceCadburys184RS);
        spinnerFrequencyServiceKaleeshwari185RS = findViewById(R.id.spinnerFrequencyServiceKaleeshwari185RS);

        spinnerFrequencyService_Dabur186RS = findViewById(R.id.spinnerFrequencyService_Dabur186RS);
        spinnerFrequencyService_britannia187RS = findViewById(R.id.spinnerFrequencyService_britannia187RS);
        spinnerFrequencyService_Kwalitywalls188RS = findViewById(R.id.spinnerFrequencyService_Kwalitywalls188RS);
        spinnerFrequencyService_Hatsunagro189RS = findViewById(R.id.spinnerFrequencyService_Hatsunagro189RS);
        spinnerFrequencyService_Heritage1810RS = findViewById(R.id.spinnerFrequencyService_Heritage1810RS);


        spinnerFrequencyService_MTR1811RS = findViewById(R.id.spinnerFrequencyService_MTR1811RS);
        spinnerFrequencyService_Sakthi1812RS = findViewById(R.id.spinnerFrequencyService_Sakthi1812RS);
        spinnerFrequencyService_Amul1813RS = findViewById(R.id.spinnerFrequencyService_Amul1813RS);
        spinnerFrequencyService_Everest1814RS = findViewById(R.id.spinnerFrequencyService_Everest1814RS);
        spinnerFrequencyService_Haldirams1815RS = findViewById(R.id.spinnerFrequencyService_Haldirams1815RS);

        spinnerFrequencyService_Colgate1816RS = findViewById(R.id.spinnerFrequencyService_Colgate1816RS);
        spinnerFrequencyService_Motherdairy1817RS = findViewById(R.id.spinnerFrequencyService_Motherdairy1817RS);
        spinnerFrequencyService_Parle1818RS = findViewById(R.id.spinnerFrequencyService_Parle1818RS);
        spinnerFrequencyService_Biskfarm1819RS = findViewById(R.id.spinnerFrequencyService_Biskfarm1819RS);
        spinnerFrequencyService_ReiAgro1820RS = findViewById(R.id.spinnerFrequencyService_ReiAgro1820RS);

        spinnerFrequencyService_Rucisoya1821RS = findViewById(R.id.spinnerFrequencyService_Rucisoya1821RS);
        spinnerFrequencyService_Idhayam1822RS = findViewById(R.id.spinnerFrequencyService_Idhayam1822RS);
        spinnerFrequencyService_Jubilantfood1823RS = findViewById(R.id.spinnerFrequencyService_Jubilantfood1823RS);
        spinnerFrequencyService_Coke1824RS = findViewById(R.id.spinnerFrequencyService_Coke1824RS);
        spinnerFrequencyService_Pepsi1825RS = findViewById(R.id.spinnerFrequencyService_Pepsi1825RS);

        spinnerFrequencyService_Bisleri1826RS = findViewById(R.id.spinnerFrequencyService_Bisleri1826RS);
        spinnerFrequencyService_GSK1827RS = findViewById(R.id.spinnerFrequencyService_GSK1827RS);
        spinnerFrequencyService_Patanjali1828RS = findViewById(R.id.spinnerFrequencyService_Patanjali1828RS);
        spinnerFrequencyService_Marico1829RS = findViewById(R.id.spinnerFrequencyService_Marico1829RS);
        spinnerFrequencyService_PG1830RS = findViewById(R.id.spinnerFrequencyService_PG1830RS);


        if (spinnerTypeOfFirm3RS.getId() == R.id.spinnerTypeOfFirm3RS) {

            AG dg_inst = AG.getInstance();
            dg_inst.getUser().setRsTypeofFirm(typeOfFirm[position].trim());
        }

        if (spinnerTypeOfRetailer16RS.getId() == R.id.spinnerTypeOfRetailer16RS) {

            String selectedItem = adapterView.getItemAtPosition(position).toString();
            if (selectedItem.equals("Others")) {
                etTypeOfRetailerOther16RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsTypeOfRetailer(etTypeOfRetailerOther16RS.getText().toString());

            } else {
                etTypeOfRetailerOther16RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsTypeOfRetailer(typeOfRetailer[position].trim());
            }
        }


        //Todo: If HUL Availabilty  selected
        if (spinnerAvailabiltyHUL18RS.getId() == R.id.spinnerAvailabiltyHUL18RS) {

            //Todo: If HUL Availabilty No selected
            if (position == 2) {
                spinnerFrequencyServiceHUL18RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsHulFrequency("");
                dg_inst.getUser().setRsHulAvailabilty(brandAvailaibility[position].toString().trim());


            } else {

                spinnerFrequencyServiceHUL18RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsHulAvailabilty(brandAvailaibility[position].toString().trim());

            }
        }


        if (spinnerAvailabiltyTATA18RS.getId() == R.id.spinnerAvailabiltyTATA18RS) {

            if (position == 2) {


                spinnerFrequencyServiceTATA18RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsTata_Frequency("");
                dg_inst.getUser().setRsTata_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyServiceTATA18RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsTata_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }


        if (spinnerAvailabiltyITC18RS.getId() == R.id.spinnerAvailabiltyITC18RS) {

            if (position == 2) {


                spinnerFrequencyServiceITC18RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsItc_Frequency("");
                dg_inst.getUser().setRsItc_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyServiceITC18RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsItc_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }


        if (spinnerAvailabiltyNestle183RS.getId() == R.id.spinnerAvailabiltyNestle183RS) {

            if (position == 2) {


                spinnerFrequencyServiceNestle183RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsNestle_Frequency("");
                dg_inst.getUser().setRsNestle_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyServiceNestle183RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsNestle_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }

        if (spinnerAvailabiltyCadburys184RS.getId() == R.id.spinnerAvailabiltyCadburys184RS) {

            if (position == 2) {


                spinnerFrequencyServiceCadburys184RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsCadburys_Frequency("");
                dg_inst.getUser().setRsCadburys_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyServiceCadburys184RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsCadburys_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }

        if (spinnerAvailabiltyKaleeshwari185RS.getId() == R.id.spinnerAvailabiltyKaleeshwari185RS) {

            if (position == 2) {


                spinnerFrequencyServiceKaleeshwari185RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsKaleeshwari_Frequency("");
                dg_inst.getUser().setRsKaleeshwari_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyServiceKaleeshwari185RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsKaleeshwari_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }


        if (spinnerAvailabilty_Dabur186RS.getId() == R.id.spinnerAvailabilty_Dabur186RS) {

            if (position == 2) {


                spinnerFrequencyService_Dabur186RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsDaburFrequency("");
                dg_inst.getUser().setRsDaburAvailabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_Dabur186RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsDaburAvailabilty(brandAvailaibility[position].toString().trim());
            }

        }

        if (spinnerAvailabilty_britannia187RS.getId() == R.id.spinnerAvailabilty_britannia187RS) {

            if (position == 2) {


                spinnerFrequencyService_britannia187RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsBritannia_Frequency("");
                dg_inst.getUser().setRsBritannia_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_britannia187RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsBritannia_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }

        if (spinnerAvailabilty_Kwalitywalls188RS.getId() == R.id.spinnerAvailabilty_Kwalitywalls188RS) {

            if (position == 2) {


                spinnerFrequencyService_Kwalitywalls188RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsKwality_Frequency("");
                dg_inst.getUser().setRsKwality_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_Kwalitywalls188RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsKwality_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }


        if (spinnerAvailabilty_Hatsunagro189RS.getId() == R.id.spinnerAvailabilty_Hatsunagro189RS) {

            if (position == 2) {


                spinnerFrequencyService_Hatsunagro189RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsHatsun_Frequency("");
                dg_inst.getUser().setRsHatsunAvailabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_Hatsunagro189RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsHatsunAvailabilty(brandAvailaibility[position].toString().trim());
            }

        }


        if (spinnerAvailabilty_Heritage1810RS.getId() == R.id.spinnerAvailabilty_Heritage1810RS) {

            if (position == 2) {


                spinnerFrequencyService_Heritage1810RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsHeritage_Frequency("");
                dg_inst.getUser().setRsHeritage_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_Heritage1810RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsHeritage_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }

        if (spinnerAvailabilty_MTR1811RS.getId() == R.id.spinnerAvailabilty_MTR1811RS) {

            if (position == 2) {


                spinnerFrequencyService_MTR1811RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsmtr_Frequency("");
                dg_inst.getUser().setRsmtr_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_MTR1811RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsmtr_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }

        if (spinnerAvailabilty_Sakthi1812RS.getId() == R.id.spinnerAvailabilty_Sakthi1812RS) {

            if (position == 2) {


                spinnerFrequencyService_Sakthi1812RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRssakthiFrequency("");
                dg_inst.getUser().setRssakthi_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_Sakthi1812RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRssakthi_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }

        if (spinnerAvailabilty_Amul1813RS.getId() == R.id.spinnerAvailabilty_Amul1813RS) {

            if (position == 2) {


                spinnerFrequencyService_Amul1813RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsamul__Frequency("");
                dg_inst.getUser().setRsamul__Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_Amul1813RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsamul__Availabilty(brandAvailaibility[position].toString().trim());
            }

        }

        if (spinnerAvailabilty_Everest1814RS.getId() == R.id.spinnerAvailabilty_Everest1814RS) {

            if (position == 2) {


                spinnerFrequencyService_Everest1814RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRseverest_Frequency("");
                dg_inst.getUser().setRseverest_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_Everest1814RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRseverest_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }

        if (spinnerAvailabilty_Haldirams1815RS.getId() == R.id.spinnerAvailabilty_Haldirams1815RS) {

            if (position == 2) {


                spinnerFrequencyService_Haldirams1815RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRshaldirams_Frequency("");
                dg_inst.getUser().setRshaldirams_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_Haldirams1815RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRshaldirams_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }


        if (spinnerAvailabilty_Colgate1816RS.getId() == R.id.spinnerAvailabilty_Colgate1816RS) {

            if (position == 2) {


                spinnerFrequencyService_Colgate1816RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRscolgate_Frequency("");
                dg_inst.getUser().setRscoke_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_Colgate1816RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRscoke_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }

        if (spinnerAvailabilty_Motherdairy1817RS.getId() == R.id.spinnerAvailabilty_Motherdairy1817RS) {

            if (position == 2) {

                spinnerFrequencyService_Motherdairy1817RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsmotherdi_Frequency("");
                dg_inst.getUser().setRsmotherdi_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_Motherdairy1817RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsmotherdi_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }

        if (spinnerAvailabilty_Parle1818RS.getId() == R.id.spinnerAvailabilty_Parle1818RS) {

            if (position == 2) {


                spinnerFrequencyService_Parle1818RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsparle_Frequency("");
                dg_inst.getUser().setRsparle_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_Parle1818RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsparle_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }


        if (spinnerAvailabilty_Biskfarm1819RS.getId() == R.id.spinnerAvailabilty_Biskfarm1819RS) {

            if (position == 2) {


                spinnerFrequencyService_Biskfarm1819RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsbiskfarm_Frequency("");
                dg_inst.getUser().setRsbiskfarm_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_Biskfarm1819RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsbiskfarm_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }


        if (spinnerAvailabilty_ReiAgro1820RS.getId() == R.id.spinnerAvailabilty_ReiAgro1820RS) {

            if (position == 2) {


                spinnerFrequencyService_ReiAgro1820RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsreiagro_Frequency("");
                dg_inst.getUser().setRsreiagro_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_ReiAgro1820RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsreiagro_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }


        if (spinnerAvailabilty_Rucisoya1821RS.getId() == R.id.spinnerAvailabilty_Rucisoya1821RS) {

            if (position == 2) {


                spinnerFrequencyService_Rucisoya1821RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsruchi_Frequency("");
                dg_inst.getUser().setRsruchi_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_Rucisoya1821RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsruchi_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }

        if (spinnerAvailabilty_Idhayam1822RS.getId() == R.id.spinnerAvailabilty_Idhayam1822RS) {

            if (position == 2) {


                spinnerFrequencyService_Idhayam1822RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsidhayam_Frequency("");
                dg_inst.getUser().setRsidhayam__Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_Idhayam1822RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsidhayam__Availabilty(brandAvailaibility[position].toString().trim());
            }

        }

        if (spinnerAvailabilty_Jubilantfood1823RS.getId() == R.id.spinnerAvailabilty_Jubilantfood1823RS) {

            if (position == 2) {


                spinnerFrequencyService_Jubilantfood1823RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsjubliant_Frequency("");
                dg_inst.getUser().setRsjubliant_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_Jubilantfood1823RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsjubliant_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }


        if (spinnerAvailabilty_Coke1824RS.getId() == R.id.spinnerAvailabilty_Coke1824RS) {

            if (position == 2) {


                spinnerFrequencyService_Coke1824RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRscoke_Frequency("");
                dg_inst.getUser().setRscoke_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_Coke1824RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRscoke_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }


        if (spinnerAvailabilty_Pepsi1825RS.getId() == R.id.spinnerAvailabilty_Pepsi1825RS) {

            if (position == 2) {


                spinnerFrequencyService_Pepsi1825RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRspepsi_Frequency("");
                dg_inst.getUser().setRspepsi_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_Pepsi1825RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRspepsi_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }

        if (spinnerAvailabilty_Bisleri1826RS.getId() == R.id.spinnerAvailabilty_Bisleri1826RS) {

            if (position == 2) {


                spinnerFrequencyService_Bisleri1826RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsbisleri_Frequency("");
                dg_inst.getUser().setRsbisleri_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_Bisleri1826RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsbisleri_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }


        if (spinnerAvailabilty_GSK1827RS.getId() == R.id.spinnerAvailabilty_GSK1827RS) {

            if (position == 2) {


                spinnerFrequencyService_GSK1827RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsgsk_Frequency("");
                dg_inst.getUser().setRsgsk_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_GSK1827RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsgsk_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }

        if (spinnerAvailabilty_Patanjali1828RS.getId() == R.id.spinnerAvailabilty_Patanjali1828RS) {

            if (position == 2) {


                spinnerFrequencyService_Patanjali1828RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRspathanjalli_Frequency("");
                dg_inst.getUser().setRspathanjalli_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_Patanjali1828RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRspathanjalli_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }

        if (spinnerAvailabilty_Marico1829RS.getId() == R.id.spinnerAvailabilty_Marico1829RS) {

            if (position == 2) {


                spinnerFrequencyService_Marico1829RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsmaricoFrequency("");
                dg_inst.getUser().setRsmarico_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_Marico1829RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsmarico_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }


        if (spinnerAvailabilty_PG1830RS.getId() == R.id.spinnerAvailabilty_PG1830RS) {

            if (position == 2) {


                spinnerFrequencyService_PG1830RS.setVisibility(View.INVISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRspg_Frequency("");
                dg_inst.getUser().setRspg_Availabilty(brandAvailaibility[position].toString().trim());
            } else {
                spinnerFrequencyService_PG1830RS.setVisibility(View.VISIBLE);
                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRspg_Availabilty(brandAvailaibility[position].toString().trim());
            }

        }


        if (spinnerServiceToDoor20RS.getId() == R.id.spinnerServiceToDoor20RS) {

            if (spinnerServiceToDoor20RS.getSelectedItemPosition() > 0) {

                AG dg_inst = AG.getInstance();
                dg_inst.getUser().setRsServiceBuyingFrequency(serviceToDoorstepFrequency[position].trim());
            }
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    // Todo: Live Location
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
                        Geocoder geocoder = new Geocoder(RetailerSurveyRegistrationActivity.this, Locale.getDefault());

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
            Intent intent = new Intent(RetailerSurveyRegistrationActivity.this, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        super.onResume();
    }
}