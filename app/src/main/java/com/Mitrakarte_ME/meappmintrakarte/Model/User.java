package com.Mitrakarte_ME.meappmintrakarte.Model;

public class User {

    private int userId;
    private String userToken;
    private String name;

    private String email;
    private String mobile;
    private String address;
    private String dob;
    private String gender;
    private Boolean isMobileVerified;
    public String otp;
    private String newpwd;
    private String oldpwd;
    private String userlocation;
    private String product_Search_key;
    private String product_Search_cor;
    private String product_Search_emp;



    //Todo: Me added
    private String meName;
    private String mePhoto;
    private String fatherName;
    private String educationQualification;
    private String twoWheelerDetail;
    private String twoWheelerLicenseNo;
    private String threeWheelerDetail;
    private String etFourWheelerDetail16;
    private String androidPhoneName;
    private String salesExperience;
    private String photo;
    private String digitalSignature;



    //Todo: Retailer added
    private String retailerId;
    private String retailerToken;

    private String firmName;
    private String partnership;
    private String partnername;
    private String typeofoutlet;
    private String gstNo;
    private String aadhaarNo;
    private String panNo;
    private String villageName;
    private String village_code;
    private String taluka;
    private String districtName;
    private String state;
    private String country;
    private String pincode;
    private String addressWithLandmark;
    private String residentialAddress;
    private String permanentContactNo;
    private String alternateContactNo;
    private String whatsappNo;
    private String mailID;
    private String bankName;
    private String accountNumber;
    private String ifscCode;
    private String branch;
    private String password;
    private String fingerprint;
    private String term_condition;
    private String live_location;
    private String status;

    //Todo: Retailer Survey Details added

    private String rsName;
    private String rsPermanentNumber;
    private String rsTypeofFirm;
    private String rsVillagePApulation;
    private String rsOwnerName;
    private String rsEmail;
    private String rsDob;
    private String rsAnniversaryDate;
    private String rsGstNo;
    private String rsPanNo;
    private String rsAddress;
    private String rsLandMark;
    private String rsAlternateNo;
    private String rsWhatsappNumber;
    private String rsExperience;

    private String rsTypeOfRetailer;
    private String rsSocialMedia;

    private String rsBuyWholesellerStock;
    private String rsPlaceName;
    private String rsKmToTravel;
    private String rsHowOften;

    private String rsservice;
    private String rsServiceBuyingFrequency;

    private String rsAverageCustomerDay;
    private String rsAverageSalesWeek;
    private String rsAverageSalesMonth;

    private String rsFinance;
    private String rsFinanceAmount;

    private String rsShopInsuranceAvailable;
    private String rsShopInsuranceRequired;

    private String rsMedicalInsuranceAvailable;
    private String rsMedicalInsuranceRequired;
    private String rsLifeInsuranceAvailable;
    private String rsLifeInsuranceRequired;
    private String rsTermInsuranceAvailable;
    private String rsTermInsuranceRequired;

    private String rsHulAvailabilty;
    private String rsHulFrequency;
    private String rsTata_Availabilty;
    private String rsTata_Frequency;

    private String rsItc_Availabilty;
    private String rsItc_Frequency;
    private String rsNestle_Availabilty;
    private String rsNestle_Frequency;
    private String rsCadburys_Availabilty;
    private String rsCadburys_Frequency;
    private String rsKaleeshwari_Availabilty;
    private String rsKaleeshwari_Frequency;

    private String rsDaburAvailabilty;
    private String rsDaburFrequency;
    private String rsBritannia_Availabilty;
    private String rsBritannia_Frequency;
    private String rsKwality_Availabilty;
    private String rsKwality_Frequency;
    private String rsHatsunAvailabilty;
    private String rsHatsun_Frequency;

    private String rsHeritage_Availabilty;
    private String rsHeritage_Frequency;
    private String rsmtr_Availabilty;
    private String rsmtr_Frequency;
    private String rssakthi_Availabilty;
    private String rssakthiFrequency;
    private String rsamul__Availabilty;
    private String rsamul__Frequency;

    private String rseverest_Availabilty;
    private String rseverest_Frequency;
    private String rshaldirams_Availabilty;
    private String rshaldirams_Frequency;
    private String rscolgate_Availabilty;
    private String rscolgate_Frequency;
    private String rsmotherdi_Availabilty;
    private String rsmotherdi_Frequency;

    private String rsparle_Availabilty;
    private String rsparle_Frequency;
    private String rsbiskfarm_Availabilty;
    private String rsbiskfarm_Frequency;
    private String rsreiagro_Availabilty;
    private String rsreiagro_Frequency;
    private String rsruchi_Availabilty;
    private String rsruchi_Frequency;

    private String rsidhayam__Availabilty;
    private String rsidhayam_Frequency;
    private String rsjubliant_Availabilty;
    private String rsjubliant_Frequency;
    private String rscoke_Availabilty;
    private String rscoke_Frequency;
    private String rspepsi_Availabilty;
    private String rspepsi_Frequency;

    private String rsbisleri_Availabilty;
    private String rsbisleri_Frequency;
    private String rsgsk_Availabilty;
    private String rsgsk_Frequency;
    private String rspathanjalli_Availabilty;
    private String rspathanjalli_Frequency;
    private String rsmarico_Availabilty;
    private String rsmaricoFrequency;

    private String rspg_Availabilty;
    private String rspg_Frequency;

    //Todo: Retailer added New
    private String rFirmName;
    private String rPartnership;
    private String rPartnername;
    private String rTypeofoutlet;
    private String rGstNo;
    private String rAadhaarNo;
    private String rPanNo;
    private String rEmail;

    private String rVillageName;
    private String rVillage_code;
    private String rTaluka;
    private String rDistrictName;
    private String rState;
    private String rPincode;
    private String rAddressWithLandmark;
    private String rResidentialAddress;
    private String rAlternateContactNo;
    private String rWhatsappNo;
    private String rBankName;
    private String rAccountNumber;
    private String rIfscCode;
    private String rBranch;
    private String rPassword;
    private String rLongitude;
    private String rLatitude;

    //Todo: Corporate Added
    private int corporateId;
    private int cor_cartID;
    private String corporateToken;
    private String corToken;
    private String crStatus;
    private String crmobile;
    private String crPassword;
    public String crotp;
    private String crMobile;
    private String crOutletName;
    private String crOwnerName;
    private String crWhatsappNo;
    private String crMailId;
    private String crEmployeeStrength;
    private String crAddress;
    private String crPincode;

    private String crVillageName;
    private String crVillageCode;
    private String crAadharNumber;
    private String crGSTNumber;
    private String crPanNumber;
    private String crBankName;
    private String crBankAccountNumber;
    private String crBranch;
    private String crIFSCCode;
    private String crPhotoCompany;
    private String crDigitalSignature;

    //Todo: Employee Added
    private int employeeId;
    private String employeeToken;
    private String employeeStatus;
    private String empMobile;
    private String empPassword;
    private String empNAme;
    private String empCode;
    private String empResidential_address;
    private String empOfficialAddress;
    private String empAadhaarNumber;
    private String emppPanNumber;
    private String emppEmployee_email;
    private String empePersonalMail;
    private String empDob;

    //Todo: Key Account Added
    private int keyAccountId;
    private String keyAccountToken;
    private String kStatus;
    public String kOtp;
    private String kPassword;
    private String kChangeNewkPassword;
    private String kMobile;
    private String kOutletName;
    private String kOwnerName;
    private String kWhatsappNo;
    private String kEmployeeStrength;
    private String kAddress;
    private String kPincode;
    private String kMAil;

    private String kVillageName;
    private String kVillageCode;
    private String kAadharNumber;
    private String kGSTNumber;
    private String kPanNumber;
    private String kBankName;
    private String kBankAccountNumber;
    private String kBranch;
    private String kIFSCCode;
    private String kPhotoCompany;
    private String kDigitalSignature;
    private String AddressFull;

    //todo: deliery Address
    private String kName_Del;
    private String kaddress_Del;
    private String kLocality_Del;
    private String kLand_mark_Del;
    private String kCountry_del;
    private String kState_del;
    private String kCity_del;
    private String kPin_del;
    private String kContact_del;
    private String kEmail_del;



    //Todo: Customer Added
    private int customerId;
    private String customerToken;
    private String cStatus;
    public String cOtp;
    private String cPassword;
    private String cMobile;
    private String cWhatsappNo;
    private String cAddress;
    private String cPincode;

    private String cName;
    private String cMailID;
    private String cDob;
    private String cGender;


    //todo: deliery Address - Corporate
    private String kName_Del_cor;
    private String kaddress_Del_cor;
    private String kLocality_Del_cor;
    private String kLand_mark_Del_cor;
    private String kCountry_del_cor;
    private String kState_del_cor;
    private String kCity_del_cor;
    private String kPin_del_cor;
    private String kContact_del_cor;
    private String kEmail_del_cor;

    //todo: deliery Address - Employee
    private String kName_Del_emp;
    private String kaddress_Del_emp;
    private String kLocality_Del_emp;
    private String kLand_mark_Del_emp;
    private String kCountry_del_emp;
    private String kState_del_emp;
    private String kCity_del_emp;
    private String kPin_del_emp;
    private String kContact_del_emp;
    private String kEmail_del_emp;


    public String getMePhoto() {
        return mePhoto;
    }

    public void setMePhoto(String mePhoto) {
        this.mePhoto = mePhoto;
    }

    public String getCorToken() {
        return corToken;
    }

    public void setCorToken(String corToken) {
        this.corToken = corToken;
    }

    public String getrLongitude() {
        return rLongitude;
    }

    public void setrLongitude(String rLongitude) {
        this.rLongitude = rLongitude;
    }

    public String getrLatitude() {
        return rLatitude;
    }

    public void setrLatitude(String rLatitude) {
        this.rLatitude = rLatitude;
    }

    public String getRsDaburAvailabilty() {
        return rsDaburAvailabilty;
    }

    public void setRsDaburAvailabilty(String rsDaburAvailabilty) {
        this.rsDaburAvailabilty = rsDaburAvailabilty;
    }

    public String getRsDaburFrequency() {
        return rsDaburFrequency;
    }

    public void setRsDaburFrequency(String rsDaburFrequency) {
        this.rsDaburFrequency = rsDaburFrequency;
    }

    public String getRsBritannia_Availabilty() {
        return rsBritannia_Availabilty;
    }

    public void setRsBritannia_Availabilty(String rsBritannia_Availabilty) {
        this.rsBritannia_Availabilty = rsBritannia_Availabilty;
    }

    public String getRsBritannia_Frequency() {
        return rsBritannia_Frequency;
    }

    public void setRsBritannia_Frequency(String rsBritannia_Frequency) {
        this.rsBritannia_Frequency = rsBritannia_Frequency;
    }

    public String getRsKwality_Availabilty() {
        return rsKwality_Availabilty;
    }

    public void setRsKwality_Availabilty(String rsKwality_Availabilty) {
        this.rsKwality_Availabilty = rsKwality_Availabilty;
    }

    public String getRsKwality_Frequency() {
        return rsKwality_Frequency;
    }

    public void setRsKwality_Frequency(String rsKwality_Frequency) {
        this.rsKwality_Frequency = rsKwality_Frequency;
    }

    public String getRsHatsunAvailabilty() {
        return rsHatsunAvailabilty;
    }

    public void setRsHatsunAvailabilty(String rsHatsunAvailabilty) {
        this.rsHatsunAvailabilty = rsHatsunAvailabilty;
    }

    public String getRsHatsun_Frequency() {
        return rsHatsun_Frequency;
    }

    public void setRsHatsun_Frequency(String rsHatsun_Frequency) {
        this.rsHatsun_Frequency = rsHatsun_Frequency;
    }

    public String getRsHeritage_Availabilty() {
        return rsHeritage_Availabilty;
    }

    public void setRsHeritage_Availabilty(String rsHeritage_Availabilty) {
        this.rsHeritage_Availabilty = rsHeritage_Availabilty;
    }

    public String getRsHeritage_Frequency() {
        return rsHeritage_Frequency;
    }

    public void setRsHeritage_Frequency(String rsHeritage_Frequency) {
        this.rsHeritage_Frequency = rsHeritage_Frequency;
    }

    public String getRsmtr_Availabilty() {
        return rsmtr_Availabilty;
    }

    public void setRsmtr_Availabilty(String rsmtr_Availabilty) {
        this.rsmtr_Availabilty = rsmtr_Availabilty;
    }

    public String getRsmtr_Frequency() {
        return rsmtr_Frequency;
    }

    public void setRsmtr_Frequency(String rsmtr_Frequency) {
        this.rsmtr_Frequency = rsmtr_Frequency;
    }

    public String getRssakthi_Availabilty() {
        return rssakthi_Availabilty;
    }

    public void setRssakthi_Availabilty(String rssakthi_Availabilty) {
        this.rssakthi_Availabilty = rssakthi_Availabilty;
    }

    public String getRssakthiFrequency() {
        return rssakthiFrequency;
    }

    public void setRssakthiFrequency(String rssakthiFrequency) {
        this.rssakthiFrequency = rssakthiFrequency;
    }

    public String getRsamul__Availabilty() {
        return rsamul__Availabilty;
    }

    public void setRsamul__Availabilty(String rsamul__Availabilty) {
        this.rsamul__Availabilty = rsamul__Availabilty;
    }

    public String getRsamul__Frequency() {
        return rsamul__Frequency;
    }

    public void setRsamul__Frequency(String rsamul__Frequency) {
        this.rsamul__Frequency = rsamul__Frequency;
    }

    public String getRseverest_Availabilty() {
        return rseverest_Availabilty;
    }

    public void setRseverest_Availabilty(String rseverest_Availabilty) {
        this.rseverest_Availabilty = rseverest_Availabilty;
    }

    public String getRseverest_Frequency() {
        return rseverest_Frequency;
    }

    public void setRseverest_Frequency(String rseverest_Frequency) {
        this.rseverest_Frequency = rseverest_Frequency;
    }

    public String getRshaldirams_Availabilty() {
        return rshaldirams_Availabilty;
    }

    public void setRshaldirams_Availabilty(String rshaldirams_Availabilty) {
        this.rshaldirams_Availabilty = rshaldirams_Availabilty;
    }

    public String getRshaldirams_Frequency() {
        return rshaldirams_Frequency;
    }

    public void setRshaldirams_Frequency(String rshaldirams_Frequency) {
        this.rshaldirams_Frequency = rshaldirams_Frequency;
    }

    public String getRscolgate_Availabilty() {
        return rscolgate_Availabilty;
    }

    public void setRscolgate_Availabilty(String rscolgate_Availabilty) {
        this.rscolgate_Availabilty = rscolgate_Availabilty;
    }

    public String getRscolgate_Frequency() {
        return rscolgate_Frequency;
    }

    public void setRscolgate_Frequency(String rscolgate_Frequency) {
        this.rscolgate_Frequency = rscolgate_Frequency;
    }

    public String getRsmotherdi_Availabilty() {
        return rsmotherdi_Availabilty;
    }

    public void setRsmotherdi_Availabilty(String rsmotherdi_Availabilty) {
        this.rsmotherdi_Availabilty = rsmotherdi_Availabilty;
    }

    public String getRsmotherdi_Frequency() {
        return rsmotherdi_Frequency;
    }

    public void setRsmotherdi_Frequency(String rsmotherdi_Frequency) {
        this.rsmotherdi_Frequency = rsmotherdi_Frequency;
    }

    public String getRsparle_Availabilty() {
        return rsparle_Availabilty;
    }

    public void setRsparle_Availabilty(String rsparle_Availabilty) {
        this.rsparle_Availabilty = rsparle_Availabilty;
    }

    public String getRsparle_Frequency() {
        return rsparle_Frequency;
    }

    public void setRsparle_Frequency(String rsparle_Frequency) {
        this.rsparle_Frequency = rsparle_Frequency;
    }

    public String getRsbiskfarm_Availabilty() {
        return rsbiskfarm_Availabilty;
    }

    public void setRsbiskfarm_Availabilty(String rsbiskfarm_Availabilty) {
        this.rsbiskfarm_Availabilty = rsbiskfarm_Availabilty;
    }

    public String getRsbiskfarm_Frequency() {
        return rsbiskfarm_Frequency;
    }

    public void setRsbiskfarm_Frequency(String rsbiskfarm_Frequency) {
        this.rsbiskfarm_Frequency = rsbiskfarm_Frequency;
    }

    public String getRsreiagro_Availabilty() {
        return rsreiagro_Availabilty;
    }

    public void setRsreiagro_Availabilty(String rsreiagro_Availabilty) {
        this.rsreiagro_Availabilty = rsreiagro_Availabilty;
    }

    public String getRsreiagro_Frequency() {
        return rsreiagro_Frequency;
    }

    public void setRsreiagro_Frequency(String rsreiagro_Frequency) {
        this.rsreiagro_Frequency = rsreiagro_Frequency;
    }

    public String getRsruchi_Availabilty() {
        return rsruchi_Availabilty;
    }

    public void setRsruchi_Availabilty(String rsruchi_Availabilty) {
        this.rsruchi_Availabilty = rsruchi_Availabilty;
    }

    public String getRsruchi_Frequency() {
        return rsruchi_Frequency;
    }

    public void setRsruchi_Frequency(String rsruchi_Frequency) {
        this.rsruchi_Frequency = rsruchi_Frequency;
    }

    public String getRsidhayam__Availabilty() {
        return rsidhayam__Availabilty;
    }

    public void setRsidhayam__Availabilty(String rsidhayam__Availabilty) {
        this.rsidhayam__Availabilty = rsidhayam__Availabilty;
    }

    public String getRsidhayam_Frequency() {
        return rsidhayam_Frequency;
    }

    public void setRsidhayam_Frequency(String rsidhayam_Frequency) {
        this.rsidhayam_Frequency = rsidhayam_Frequency;
    }

    public String getRsjubliant_Availabilty() {
        return rsjubliant_Availabilty;
    }

    public void setRsjubliant_Availabilty(String rsjubliant_Availabilty) {
        this.rsjubliant_Availabilty = rsjubliant_Availabilty;
    }

    public String getRsjubliant_Frequency() {
        return rsjubliant_Frequency;
    }

    public void setRsjubliant_Frequency(String rsjubliant_Frequency) {
        this.rsjubliant_Frequency = rsjubliant_Frequency;
    }

    public String getRscoke_Availabilty() {
        return rscoke_Availabilty;
    }

    public void setRscoke_Availabilty(String rscoke_Availabilty) {
        this.rscoke_Availabilty = rscoke_Availabilty;
    }

    public String getRscoke_Frequency() {
        return rscoke_Frequency;
    }

    public void setRscoke_Frequency(String rscoke_Frequency) {
        this.rscoke_Frequency = rscoke_Frequency;
    }

    public String getRspepsi_Availabilty() {
        return rspepsi_Availabilty;
    }

    public void setRspepsi_Availabilty(String rspepsi_Availabilty) {
        this.rspepsi_Availabilty = rspepsi_Availabilty;
    }

    public String getRspepsi_Frequency() {
        return rspepsi_Frequency;
    }

    public void setRspepsi_Frequency(String rspepsi_Frequency) {
        this.rspepsi_Frequency = rspepsi_Frequency;
    }

    public String getRsbisleri_Availabilty() {
        return rsbisleri_Availabilty;
    }

    public void setRsbisleri_Availabilty(String rsbisleri_Availabilty) {
        this.rsbisleri_Availabilty = rsbisleri_Availabilty;
    }

    public String getRsbisleri_Frequency() {
        return rsbisleri_Frequency;
    }

    public void setRsbisleri_Frequency(String rsbisleri_Frequency) {
        this.rsbisleri_Frequency = rsbisleri_Frequency;
    }

    public String getRsgsk_Availabilty() {
        return rsgsk_Availabilty;
    }

    public void setRsgsk_Availabilty(String rsgsk_Availabilty) {
        this.rsgsk_Availabilty = rsgsk_Availabilty;
    }

    public String getRsgsk_Frequency() {
        return rsgsk_Frequency;
    }

    public void setRsgsk_Frequency(String rsgsk_Frequency) {
        this.rsgsk_Frequency = rsgsk_Frequency;
    }

    public String getRspathanjalli_Availabilty() {
        return rspathanjalli_Availabilty;
    }

    public void setRspathanjalli_Availabilty(String rspathanjalli_Availabilty) {
        this.rspathanjalli_Availabilty = rspathanjalli_Availabilty;
    }

    public String getRspathanjalli_Frequency() {
        return rspathanjalli_Frequency;
    }

    public void setRspathanjalli_Frequency(String rspathanjalli_Frequency) {
        this.rspathanjalli_Frequency = rspathanjalli_Frequency;
    }

    public String getRsmarico_Availabilty() {
        return rsmarico_Availabilty;
    }

    public void setRsmarico_Availabilty(String rsmarico_Availabilty) {
        this.rsmarico_Availabilty = rsmarico_Availabilty;
    }

    public String getRsmaricoFrequency() {
        return rsmaricoFrequency;
    }

    public void setRsmaricoFrequency(String rsmaricoFrequency) {
        this.rsmaricoFrequency = rsmaricoFrequency;
    }

    public String getRspg_Availabilty() {
        return rspg_Availabilty;
    }

    public void setRspg_Availabilty(String rspg_Availabilty) {
        this.rspg_Availabilty = rspg_Availabilty;
    }

    public String getRspg_Frequency() {
        return rspg_Frequency;
    }

    public void setRspg_Frequency(String rspg_Frequency) {
        this.rspg_Frequency = rspg_Frequency;
    }

    public String getRsKaleeshwari_Availabilty() {
        return rsKaleeshwari_Availabilty;
    }

    public void setRsKaleeshwari_Availabilty(String rsKaleeshwari_Availabilty) {
        this.rsKaleeshwari_Availabilty = rsKaleeshwari_Availabilty;
    }

    public String getRsKaleeshwari_Frequency() {
        return rsKaleeshwari_Frequency;
    }

    public void setRsKaleeshwari_Frequency(String rsKaleeshwari_Frequency) {
        this.rsKaleeshwari_Frequency = rsKaleeshwari_Frequency;
    }

    public String getRsItc_Availabilty() {
        return rsItc_Availabilty;
    }

    public void setRsItc_Availabilty(String rsItc_Availabilty) {
        this.rsItc_Availabilty = rsItc_Availabilty;
    }

    public String getRsItc_Frequency() {
        return rsItc_Frequency;
    }

    public void setRsItc_Frequency(String rsItc_Frequency) {
        this.rsItc_Frequency = rsItc_Frequency;
    }

    public String getRsNestle_Availabilty() {
        return rsNestle_Availabilty;
    }

    public void setRsNestle_Availabilty(String rsNestle_Availabilty) {
        this.rsNestle_Availabilty = rsNestle_Availabilty;
    }

    public String getRsNestle_Frequency() {
        return rsNestle_Frequency;
    }

    public void setRsNestle_Frequency(String rsNestle_Frequency) {
        this.rsNestle_Frequency = rsNestle_Frequency;
    }

    public String getRsCadburys_Availabilty() {
        return rsCadburys_Availabilty;
    }

    public void setRsCadburys_Availabilty(String rsCadburys_Availabilty) {
        this.rsCadburys_Availabilty = rsCadburys_Availabilty;
    }

    public String getRsCadburys_Frequency() {
        return rsCadburys_Frequency;
    }

    public void setRsCadburys_Frequency(String rsCadburys_Frequency) {
        this.rsCadburys_Frequency = rsCadburys_Frequency;
    }

    public String getRsTata_Availabilty() {
        return rsTata_Availabilty;
    }

    public void setRsTata_Availabilty(String rsTata_Availabilty) {
        this.rsTata_Availabilty = rsTata_Availabilty;
    }

    public String getRsTata_Frequency() {
        return rsTata_Frequency;
    }

    public void setRsTata_Frequency(String rsTata_Frequency) {
        this.rsTata_Frequency = rsTata_Frequency;
    }

    public String getRsHulAvailabilty() {
        return rsHulAvailabilty;
    }

    public void setRsHulAvailabilty(String rsHulAvailabilty) {
        this.rsHulAvailabilty = rsHulAvailabilty;
    }

    public String getRsHulFrequency() {
        return rsHulFrequency;
    }

    public void setRsHulFrequency(String rsHulFrequency) {
        this.rsHulFrequency = rsHulFrequency;
    }

    public String getkName_Del_emp() {
        return kName_Del_emp;
    }

    public void setkName_Del_emp(String kName_Del_emp) {
        this.kName_Del_emp = kName_Del_emp;
    }

    public String getKaddress_Del_emp() {
        return kaddress_Del_emp;
    }

    public void setKaddress_Del_emp(String kaddress_Del_emp) {
        this.kaddress_Del_emp = kaddress_Del_emp;
    }

    public String getkLocality_Del_emp() {
        return kLocality_Del_emp;
    }

    public void setkLocality_Del_emp(String kLocality_Del_emp) {
        this.kLocality_Del_emp = kLocality_Del_emp;
    }

    public String getkLand_mark_Del_emp() {
        return kLand_mark_Del_emp;
    }

    public void setkLand_mark_Del_emp(String kLand_mark_Del_emp) {
        this.kLand_mark_Del_emp = kLand_mark_Del_emp;
    }

    public String getkCountry_del_emp() {
        return kCountry_del_emp;
    }

    public void setkCountry_del_emp(String kCountry_del_emp) {
        this.kCountry_del_emp = kCountry_del_emp;
    }

    public String getkState_del_emp() {
        return kState_del_emp;
    }

    public void setkState_del_emp(String kState_del_emp) {
        this.kState_del_emp = kState_del_emp;
    }

    public String getkCity_del_emp() {
        return kCity_del_emp;
    }

    public void setkCity_del_emp(String kCity_del_emp) {
        this.kCity_del_emp = kCity_del_emp;
    }

    public String getkPin_del_emp() {
        return kPin_del_emp;
    }

    public void setkPin_del_emp(String kPin_del_emp) {
        this.kPin_del_emp = kPin_del_emp;
    }

    public String getkContact_del_emp() {
        return kContact_del_emp;
    }

    public void setkContact_del_emp(String kContact_del_emp) {
        this.kContact_del_emp = kContact_del_emp;
    }

    public String getkEmail_del_emp() {
        return kEmail_del_emp;
    }

    public void setkEmail_del_emp(String kEmail_del_emp) {
        this.kEmail_del_emp = kEmail_del_emp;
    }

    public String getkName_Del_cor() {
        return kName_Del_cor;
    }

    public void setkName_Del_cor(String kName_Del_cor) {
        this.kName_Del_cor = kName_Del_cor;
    }

    public String getKaddress_Del_cor() {
        return kaddress_Del_cor;
    }

    public void setKaddress_Del_cor(String kaddress_Del_cor) {
        this.kaddress_Del_cor = kaddress_Del_cor;
    }

    public String getkLocality_Del_cor() {
        return kLocality_Del_cor;
    }

    public void setkLocality_Del_cor(String kLocality_Del_cor) {
        this.kLocality_Del_cor = kLocality_Del_cor;
    }

    public String getkLand_mark_Del_cor() {
        return kLand_mark_Del_cor;
    }

    public void setkLand_mark_Del_cor(String kLand_mark_Del_cor) {
        this.kLand_mark_Del_cor = kLand_mark_Del_cor;
    }

    public String getkCountry_del_cor() {
        return kCountry_del_cor;
    }

    public void setkCountry_del_cor(String kCountry_del_cor) {
        this.kCountry_del_cor = kCountry_del_cor;
    }

    public String getkState_del_cor() {
        return kState_del_cor;
    }

    public void setkState_del_cor(String kState_del_cor) {
        this.kState_del_cor = kState_del_cor;
    }

    public String getkCity_del_cor() {
        return kCity_del_cor;
    }

    public void setkCity_del_cor(String kCity_del_cor) {
        this.kCity_del_cor = kCity_del_cor;
    }

    public String getkPin_del_cor() {
        return kPin_del_cor;
    }

    public void setkPin_del_cor(String kPin_del_cor) {
        this.kPin_del_cor = kPin_del_cor;
    }

    public String getkContact_del_cor() {
        return kContact_del_cor;
    }

    public void setkContact_del_cor(String kContact_del_cor) {
        this.kContact_del_cor = kContact_del_cor;
    }

    public String getkEmail_del_cor() {
        return kEmail_del_cor;
    }

    public void setkEmail_del_cor(String kEmail_del_cor) {
        this.kEmail_del_cor = kEmail_del_cor;
    }

    public String getkChangeNewkPassword() {
        return kChangeNewkPassword;
    }

    public void setkChangeNewkPassword(String kChangeNewkPassword) {
        this.kChangeNewkPassword = kChangeNewkPassword;
    }

    public String getkMAil() {
        return kMAil;
    }

    public void setkMAil(String kMAil) {
        this.kMAil = kMAil;
    }

    public String getkName_Del() {
        return kName_Del;
    }

    public void setkName_Del(String kName_Del) {
        this.kName_Del = kName_Del;
    }

    public String getKaddress_Del() {
        return kaddress_Del;
    }

    public void setKaddress_Del(String kaddress_Del) {
        this.kaddress_Del = kaddress_Del;
    }

    public String getkLocality_Del() {
        return kLocality_Del;
    }

    public void setkLocality_Del(String kLocality_Del) {
        this.kLocality_Del = kLocality_Del;
    }

    public String getkLand_mark_Del() {
        return kLand_mark_Del;
    }

    public void setkLand_mark_Del(String kLand_mark_Del) {
        this.kLand_mark_Del = kLand_mark_Del;
    }

    public String getkCountry_del() {
        return kCountry_del;
    }

    public void setkCountry_del(String kCountry_del) {
        this.kCountry_del = kCountry_del;
    }

    public String getkState_del() {
        return kState_del;
    }

    public void setkState_del(String kState_del) {
        this.kState_del = kState_del;
    }

    public String getkCity_del() {
        return kCity_del;
    }

    public void setkCity_del(String kCity_del) {
        this.kCity_del = kCity_del;
    }

    public String getkPin_del() {
        return kPin_del;
    }

    public void setkPin_del(String kPin_del) {
        this.kPin_del = kPin_del;
    }

    public String getkContact_del() {
        return kContact_del;
    }

    public void setkContact_del(String kContact_del) {
        this.kContact_del = kContact_del;
    }

    public String getkEmail_del() {
        return kEmail_del;
    }

    public void setkEmail_del(String kEmail_del) {
        this.kEmail_del = kEmail_del;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpResidential_address() {
        return empResidential_address;
    }

    public void setEmpResidential_address(String empResidential_address) {
        this.empResidential_address = empResidential_address;
    }

    public String getEmpOfficialAddress() {
        return empOfficialAddress;
    }

    public void setEmpOfficialAddress(String empOfficialAddress) {
        this.empOfficialAddress = empOfficialAddress;
    }

    public String getEmpAadhaarNumber() {
        return empAadhaarNumber;
    }

    public void setEmpAadhaarNumber(String empAadhaarNumber) {
        this.empAadhaarNumber = empAadhaarNumber;
    }

    public String getEmppPanNumber() {
        return emppPanNumber;
    }

    public void setEmppPanNumber(String emppPanNumber) {
        this.emppPanNumber = emppPanNumber;
    }

    public String getEmppEmployee_email() {
        return emppEmployee_email;
    }

    public void setEmppEmployee_email(String emppEmployee_email) {
        this.emppEmployee_email = emppEmployee_email;
    }

    public String getEmpePersonalMail() {
        return empePersonalMail;
    }

    public void setEmpePersonalMail(String empePersonalMail) {
        this.empePersonalMail = empePersonalMail;
    }

    public String getEmpDob() {
        return empDob;
    }

    public void setEmpDob(String empDob) {
        this.empDob = empDob;
    }

    public String getEmpNAme() {
        return empNAme;
    }

    public void setEmpNAme(String empNAme) {
        this.empNAme = empNAme;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeToken() {
        return employeeToken;
    }

    public void setEmployeeToken(String employeeToken) {
        this.employeeToken = employeeToken;
    }

    public String getEmpMobile() {
        return empMobile;
    }

    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }

    public int getCor_cartID() {
        return cor_cartID;
    }

    public void setCor_cartID(int cor_cartID) {
        this.cor_cartID = cor_cartID;
    }

    public String getAddressFull() {
        return AddressFull;
    }

    public void setAddressFull(String addressFull) {
        AddressFull = addressFull;
    }

    public String getrVillageName() {
        return rVillageName;
    }

    public void setrVillageName(String rVillageName) {
        this.rVillageName = rVillageName;
    }

    public String getrVillage_code() {
        return rVillage_code;
    }

    public void setrVillage_code(String rVillage_code) {
        this.rVillage_code = rVillage_code;
    }

    public String getrTaluka() {
        return rTaluka;
    }

    public void setrTaluka(String rTaluka) {
        this.rTaluka = rTaluka;
    }

    public String getrDistrictName() {
        return rDistrictName;
    }

    public void setrDistrictName(String rDistrictName) {
        this.rDistrictName = rDistrictName;
    }

    public String getrState() {
        return rState;
    }

    public void setrState(String rState) {
        this.rState = rState;
    }

    public String getrPincode() {
        return rPincode;
    }

    public void setrPincode(String rPincode) {
        this.rPincode = rPincode;
    }

    public String getrAddressWithLandmark() {
        return rAddressWithLandmark;
    }

    public void setrAddressWithLandmark(String rAddressWithLandmark) {
        this.rAddressWithLandmark = rAddressWithLandmark;
    }

    public String getrResidentialAddress() {
        return rResidentialAddress;
    }

    public void setrResidentialAddress(String rResidentialAddress) {
        this.rResidentialAddress = rResidentialAddress;
    }

    public String getrAlternateContactNo() {
        return rAlternateContactNo;
    }

    public void setrAlternateContactNo(String rAlternateContactNo) {
        this.rAlternateContactNo = rAlternateContactNo;
    }

    public String getrWhatsappNo() {
        return rWhatsappNo;
    }

    public void setrWhatsappNo(String rWhatsappNo) {
        this.rWhatsappNo = rWhatsappNo;
    }

    public String getrBankName() {
        return rBankName;
    }

    public void setrBankName(String rBankName) {
        this.rBankName = rBankName;
    }

    public String getrAccountNumber() {
        return rAccountNumber;
    }

    public void setrAccountNumber(String rAccountNumber) {
        this.rAccountNumber = rAccountNumber;
    }

    public String getrIfscCode() {
        return rIfscCode;
    }

    public void setrIfscCode(String rIfscCode) {
        this.rIfscCode = rIfscCode;
    }

    public String getrBranch() {
        return rBranch;
    }

    public void setrBranch(String rBranch) {
        this.rBranch = rBranch;
    }

    public String getrPassword() {
        return rPassword;
    }

    public void setrPassword(String rPassword) {
        this.rPassword = rPassword;
    }

    public String getrPartnership() {
        return rPartnership;
    }

    public void setrPartnership(String rPartnership) {
        this.rPartnership = rPartnership;
    }

    public String getrPartnername() {
        return rPartnername;
    }

    public void setrPartnername(String rPartnername) {
        this.rPartnername = rPartnername;
    }

    public String getrTypeofoutlet() {
        return rTypeofoutlet;
    }

    public void setrTypeofoutlet(String rTypeofoutlet) {
        this.rTypeofoutlet = rTypeofoutlet;
    }

    public String getrGstNo() {
        return rGstNo;
    }

    public void setrGstNo(String rGstNo) {
        this.rGstNo = rGstNo;
    }

    public String getrAadhaarNo() {
        return rAadhaarNo;
    }

    public void setrAadhaarNo(String rAadhaarNo) {
        this.rAadhaarNo = rAadhaarNo;
    }

    public String getrPanNo() {
        return rPanNo;
    }

    public void setrPanNo(String rPanNo) {
        this.rPanNo = rPanNo;
    }

    public String getrEmail() {
        return rEmail;
    }

    public void setrEmail(String rEmail) {
        this.rEmail = rEmail;
    }

    public String getrFirmName() {
        return rFirmName;
    }

    public void setrFirmName(String rFirmName) {
        this.rFirmName = rFirmName;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcMailID() {
        return cMailID;
    }

    public void setcMailID(String cMailID) {
        this.cMailID = cMailID;
    }

    public String getcDob() {
        return cDob;
    }

    public void setcDob(String cDob) {
        this.cDob = cDob;
    }

    public String getcGender() {
        return cGender;
    }

    public void setcGender(String cGender) {
        this.cGender = cGender;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerToken() {
        return customerToken;
    }

    public void setCustomerToken(String customerToken) {
        this.customerToken = customerToken;
    }

    public String getcStatus() {
        return cStatus;
    }

    public void setcStatus(String cStatus) {
        this.cStatus = cStatus;
    }

    public String getcOtp() {
        return cOtp;
    }

    public void setcOtp(String cOtp) {
        this.cOtp = cOtp;
    }

    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
    }

    public String getcMobile() {
        return cMobile;
    }

    public void setcMobile(String cMobile) {
        this.cMobile = cMobile;
    }



    public String getcWhatsappNo() {
        return cWhatsappNo;
    }

    public void setcWhatsappNo(String cWhatsappNo) {
        this.cWhatsappNo = cWhatsappNo;
    }



    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public String getcPincode() {
        return cPincode;
    }

    public void setcPincode(String cPincode) {
        this.cPincode = cPincode;
    }


    public String getkStatus() {
        return kStatus;
    }

    public void setkStatus(String kStatus) {
        this.kStatus = kStatus;
    }

    public String getkPhotoCompany() {
        return kPhotoCompany;
    }

    public void setkPhotoCompany(String kPhotoCompany) {
        this.kPhotoCompany = kPhotoCompany;
    }

    public String getkDigitalSignature() {
        return kDigitalSignature;
    }

    public void setkDigitalSignature(String kDigitalSignature) {
        this.kDigitalSignature = kDigitalSignature;
    }

    public String getkPassword() {
        return kPassword;
    }

    public void setkPassword(String kPassword) {
        this.kPassword = kPassword;
    }

    public String getCrStatus() {
        return crStatus;
    }

    public void setCrStatus(String crStatus) {
        this.crStatus = crStatus;
    }

    public String getCrPhotoCompany() {
        return crPhotoCompany;
    }

    public void setCrPhotoCompany(String crPhotoCompany) {
        this.crPhotoCompany = crPhotoCompany;
    }

    public String getCrDigitalSignature() {
        return crDigitalSignature;
    }

    public void setCrDigitalSignature(String crDigitalSignature) {
        this.crDigitalSignature = crDigitalSignature;
    }

    public String getCrPassword() {
        return crPassword;
    }

    public void setCrPassword(String crPassword) {
        this.crPassword = crPassword;
    }

    public int getKeyAccountId() {
        return keyAccountId;
    }

    public void setKeyAccountId(int keyAccountId) {
        this.keyAccountId = keyAccountId;
    }

    public String getKeyAccountToken() {
        return keyAccountToken;
    }

    public void setKeyAccountToken(String keyAccountToken) {
        this.keyAccountToken = keyAccountToken;
    }

    public String getkOtp() {
        return kOtp;
    }

    public void setkOtp(String kOtp) {
        this.kOtp = kOtp;
    }

    public String getCrmobile() {
        return crmobile;
    }

    public void setCrmobile(String crmobile) {
        this.crmobile = crmobile;
    }

    public String getCrotp() {
        return crotp;
    }

    public void setCrotp(String crotp) {
        this.crotp = crotp;
    }

    public String getkOwnerName() {
        return kOwnerName;
    }

    public void setkOwnerName(String kOwnerName) {
        this.kOwnerName = kOwnerName;
    }

    public String getkWhatsappNo() {
        return kWhatsappNo;
    }

    public void setkWhatsappNo(String kWhatsappNo) {
        this.kWhatsappNo = kWhatsappNo;
    }

    public String getkEmployeeStrength() {
        return kEmployeeStrength;
    }

    public void setkEmployeeStrength(String kEmployeeStrength) {
        this.kEmployeeStrength = kEmployeeStrength;
    }

    public String getkAddress() {
        return kAddress;
    }

    public void setkAddress(String kAddress) {
        this.kAddress = kAddress;
    }

    public String getkPincode() {
        return kPincode;
    }

    public void setkPincode(String kPincode) {
        this.kPincode = kPincode;
    }

    public String getkVillageName() {
        return kVillageName;
    }

    public void setkVillageName(String kVillageName) {
        this.kVillageName = kVillageName;
    }

    public String getkVillageCode() {
        return kVillageCode;
    }

    public void setkVillageCode(String kVillageCode) {
        this.kVillageCode = kVillageCode;
    }

    public String getkAadharNumber() {
        return kAadharNumber;
    }

    public void setkAadharNumber(String kAadharNumber) {
        this.kAadharNumber = kAadharNumber;
    }

    public String getkGSTNumber() {
        return kGSTNumber;
    }

    public void setkGSTNumber(String kGSTNumber) {
        this.kGSTNumber = kGSTNumber;
    }

    public String getkPanNumber() {
        return kPanNumber;
    }

    public void setkPanNumber(String kPanNumber) {
        this.kPanNumber = kPanNumber;
    }

    public String getkBankName() {
        return kBankName;
    }

    public void setkBankName(String kBankName) {
        this.kBankName = kBankName;
    }

    public String getkBankAccountNumber() {
        return kBankAccountNumber;
    }

    public void setkBankAccountNumber(String kBankAccountNumber) {
        this.kBankAccountNumber = kBankAccountNumber;
    }

    public String getkBranch() {
        return kBranch;
    }

    public void setkBranch(String kBranch) {
        this.kBranch = kBranch;
    }

    public String getkIFSCCode() {
        return kIFSCCode;
    }

    public void setkIFSCCode(String kIFSCCode) {
        this.kIFSCCode = kIFSCCode;
    }

    public String getkMobile() {
        return kMobile;
    }

    public void setkMobile(String kMobile) {
        this.kMobile = kMobile;
    }

    public String getkOutletName() {
        return kOutletName;
    }

    public void setkOutletName(String kOutletName) {
        this.kOutletName = kOutletName;
    }

    public String getCrVillageName() {
        return crVillageName;
    }

    public void setCrVillageName(String crVillageName) {
        this.crVillageName = crVillageName;
    }

    public String getCrVillageCode() {
        return crVillageCode;
    }

    public void setCrVillageCode(String crVillageCode) {
        this.crVillageCode = crVillageCode;
    }

    public String getCrAadharNumber() {
        return crAadharNumber;
    }

    public void setCrAadharNumber(String crAadharNumber) {
        this.crAadharNumber = crAadharNumber;
    }

    public String getCrGSTNumber() {
        return crGSTNumber;
    }

    public void setCrGSTNumber(String crGSTNumber) {
        this.crGSTNumber = crGSTNumber;
    }

    public String getCrPanNumber() {
        return crPanNumber;
    }

    public void setCrPanNumber(String crPanNumber) {
        this.crPanNumber = crPanNumber;
    }

    public String getCrBankName() {
        return crBankName;
    }

    public void setCrBankName(String crBankName) {
        this.crBankName = crBankName;
    }

    public String getCrBankAccountNumber() {
        return crBankAccountNumber;
    }

    public void setCrBankAccountNumber(String crBankAccountNumber) {
        this.crBankAccountNumber = crBankAccountNumber;
    }

    public String getCrBranch() {
        return crBranch;
    }

    public void setCrBranch(String crBranch) {
        this.crBranch = crBranch;
    }

    public String getCrIFSCCode() {
        return crIFSCCode;
    }

    public void setCrIFSCCode(String crIFSCCode) {
        this.crIFSCCode = crIFSCCode;
    }

    public String getCrWhatsappNo() {
        return crWhatsappNo;
    }

    public void setCrWhatsappNo(String crWhatsappNo) {
        this.crWhatsappNo = crWhatsappNo;
    }

    public String getCrMailId() {
        return crMailId;
    }

    public void setCrMailId(String crMailId) {
        this.crMailId = crMailId;
    }

    public String getCrEmployeeStrength() {
        return crEmployeeStrength;
    }

    public void setCrEmployeeStrength(String crEmployeeStrength) {
        this.crEmployeeStrength = crEmployeeStrength;
    }

    public String getCrAddress() {
        return crAddress;
    }

    public void setCrAddress(String crAddress) {
        this.crAddress = crAddress;
    }

    public String getCrPincode() {
        return crPincode;
    }

    public void setCrPincode(String crPincode) {
        this.crPincode = crPincode;
    }

    public String getCrOwnerName() {
        return crOwnerName;
    }

    public void setCrOwnerName(String crOwnerName) {
        this.crOwnerName = crOwnerName;
    }

    public int getCorporateId() {
        return corporateId;
    }

    public void setCorporateId(int corporateId) {
        this.corporateId = corporateId;
    }

    public String getCorporateToken() {
        return corporateToken;
    }

    public void setCorporateToken(String corporateToken) {
        this.corporateToken = corporateToken;
    }

    public String getCrMobile() {
        return crMobile;
    }

    public void setCrMobile(String crMobile) {
        this.crMobile = crMobile;
    }

    public String getCrOutletName() {
        return crOutletName;
    }

    public void setCrOutletName(String crOutletName) {
        this.crOutletName = crOutletName;
    }

    public String getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(String retailerId) {
        this.retailerId = retailerId;
    }

    public String getRetailerToken() {
        return retailerToken;
    }

    public void setRetailerToken(String retailerToken) {
        this.retailerToken = retailerToken;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getMeName() {
        return meName;
    }

    public void setMeName(String meName) {
        this.meName = meName;
    }

    public String getPartnership() {
        return partnership;
    }

    public void setPartnership(String partnership) {
        this.partnership = partnership;
    }

    public String getPartnername() {
        return partnername;
    }

    public void setPartnername(String partnername) {
        this.partnername = partnername;
    }

    public String getTypeofoutlet() {
        return typeofoutlet;
    }

    public void setTypeofoutlet(String typeofoutlet) {
        this.typeofoutlet = typeofoutlet;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getAadhaarNo() {
        return aadhaarNo;
    }

    public void setAadhaarNo(String aadhaarNo) {
        this.aadhaarNo = aadhaarNo;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getVillage_code() {
        return village_code;
    }

    public void setVillage_code(String village_code) {
        this.village_code = village_code;
    }

    public String getTaluka() {
        return taluka;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAddressWithLandmark() {
        return addressWithLandmark;
    }

    public void setAddressWithLandmark(String addressWithLandmark) {
        this.addressWithLandmark = addressWithLandmark;
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public String getPermanentContactNo() {
        return permanentContactNo;
    }

    public void setPermanentContactNo(String permanentContactNo) {
        this.permanentContactNo = permanentContactNo;
    }

    public String getAlternateContactNo() {
        return alternateContactNo;
    }

    public void setAlternateContactNo(String alternateContactNo) {
        this.alternateContactNo = alternateContactNo;
    }

    public String getWhatsappNo() {
        return whatsappNo;
    }

    public void setWhatsappNo(String whatsappNo) {
        this.whatsappNo = whatsappNo;
    }

    public String getMailID() {
        return mailID;
    }

    public void setMailID(String mailID) {
        this.mailID = mailID;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getTerm_condition() {
        return term_condition;
    }

    public void setTerm_condition(String term_condition) {
        this.term_condition = term_condition;
    }

    public String getLive_location() {
        return live_location;
    }

    public void setLive_location(String live_location) {
        this.live_location = live_location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //Todo: Old

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUserlocation() {
        return userlocation;
    }

    public void setUserlocation(String userlocation) {
        this.userlocation = userlocation;
    }

    public String getOtp() { return otp; }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getNewpwd() {
        return newpwd;
    }

    public void setNewpwd(String newpwd) {
        this.newpwd = newpwd;
    }

    public String getOldpwd() {
        return oldpwd;
    }

    public void setOldpwd(String oldpwd) {
        this.oldpwd = oldpwd;
    }


    public Boolean getMobileVerified() {
        return isMobileVerified;
    }

    public void setMobileVerified(Boolean mobileVerified) {
        isMobileVerified = mobileVerified;
    }

    public String getDob() { return dob; }

    public void setDob(String dob) { this.dob = dob; }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getEducationQualification() {
        return educationQualification;
    }

    public void setEducationQualification(String educationQualification) {
        this.educationQualification = educationQualification;
    }

    public String getTwoWheelerDetail() {
        return twoWheelerDetail;
    }

    public void setTwoWheelerDetail(String twoWheelerDetail) {
        this.twoWheelerDetail = twoWheelerDetail;
    }

    public String getTwoWheelerLicenseNo() {
        return twoWheelerLicenseNo;
    }

    public void setTwoWheelerLicenseNo(String twoWheelerLicenseNo) {
        this.twoWheelerLicenseNo = twoWheelerLicenseNo;
    }

    public String getThreeWheelerDetail() {
        return threeWheelerDetail;
    }

    public void setThreeWheelerDetail(String threeWheelerDetail) {
        this.threeWheelerDetail = threeWheelerDetail;
    }

    public String getEtFourWheelerDetail16() {
        return etFourWheelerDetail16;
    }

    public void setEtFourWheelerDetail16(String etFourWheelerDetail16) {
        this.etFourWheelerDetail16 = etFourWheelerDetail16;
    }

    public String getAndroidPhoneName() {
        return androidPhoneName;
    }

    public void setAndroidPhoneName(String androidPhoneName) {
        this.androidPhoneName = androidPhoneName;
    }

    public String getSalesExperience() {
        return salesExperience;
    }

    public void setSalesExperience(String salesExperience) {
        this.salesExperience = salesExperience;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDigitalSignature() {
        return digitalSignature;
    }

    public void setDigitalSignature(String digitalSignature) {
        this.digitalSignature = digitalSignature;
    }


    public String getRsName() {
        return rsName;
    }

    public void setRsName(String rsName) {
        this.rsName = rsName;
    }

    public String getRsPermanentNumber() {
        return rsPermanentNumber;
    }

    public void setRsPermanentNumber(String rsPermanentNumber) {
        this.rsPermanentNumber = rsPermanentNumber;
    }

    public String getRsTypeofFirm() {
        return rsTypeofFirm;
    }

    public void setRsTypeofFirm(String rsTypeofFirm) {
        this.rsTypeofFirm = rsTypeofFirm;
    }

    public String getRsVillagePApulation() {
        return rsVillagePApulation;
    }

    public void setRsVillagePApulation(String rsVillagePApulation) {
        this.rsVillagePApulation = rsVillagePApulation;
    }

    public String getRsOwnerName() {
        return rsOwnerName;
    }

    public void setRsOwnerName(String rsOwnerName) {
        this.rsOwnerName = rsOwnerName;
    }

    public String getRsEmail() {
        return rsEmail;
    }

    public void setRsEmail(String rsEmail) {
        this.rsEmail = rsEmail;
    }

    public String getRsDob() {
        return rsDob;
    }

    public void setRsDob(String rsDob) {
        this.rsDob = rsDob;
    }

    public String getRsAnniversaryDate() {
        return rsAnniversaryDate;
    }

    public void setRsAnniversaryDate(String rsAnniversaryDate) {
        this.rsAnniversaryDate = rsAnniversaryDate;
    }

    public String getRsGstNo() {
        return rsGstNo;
    }

    public void setRsGstNo(String rsGstNo) {
        this.rsGstNo = rsGstNo;
    }

    public String getRsPanNo() {
        return rsPanNo;
    }

    public void setRsPanNo(String rsPanNo) {
        this.rsPanNo = rsPanNo;
    }

    public String getRsAddress() {
        return rsAddress;
    }

    public void setRsAddress(String rsAddress) {
        this.rsAddress = rsAddress;
    }

    public String getRsLandMark() {
        return rsLandMark;
    }

    public void setRsLandMark(String rsLandMark) {
        this.rsLandMark = rsLandMark;
    }

    public String getRsAlternateNo() {
        return rsAlternateNo;
    }

    public void setRsAlternateNo(String rsAlternateNo) {
        this.rsAlternateNo = rsAlternateNo;
    }

    public String getRsWhatsappNumber() {
        return rsWhatsappNumber;
    }

    public void setRsWhatsappNumber(String rsWhatsappNumber) {
        this.rsWhatsappNumber = rsWhatsappNumber;
    }

    public String getRsExperience() {
        return rsExperience;
    }

    public void setRsExperience(String rsExperience) {
        this.rsExperience = rsExperience;
    }

    public String getRsBuyWholesellerStock() {
        return rsBuyWholesellerStock;
    }

    public void setRsBuyWholesellerStock(String rsBuyWholesellerStock) {
        this.rsBuyWholesellerStock = rsBuyWholesellerStock;
    }

    public String getRsPlaceName() {
        return rsPlaceName;
    }

    public void setRsPlaceName(String rsPlaceName) {
        this.rsPlaceName = rsPlaceName;
    }

    public String getRsKmToTravel() {
        return rsKmToTravel;
    }

    public void setRsKmToTravel(String rsKmToTravel) {
        this.rsKmToTravel = rsKmToTravel;
    }

    public String getRsHowOften() {
        return rsHowOften;
    }

    public void setRsHowOften(String rsHowOften) {
        this.rsHowOften = rsHowOften;
    }

    public String getRsservice() {
        return rsservice;
    }

    public void setRsservice(String rsservice) {
        this.rsservice = rsservice;
    }

    public String getRsServiceBuyingFrequency() {
        return rsServiceBuyingFrequency;
    }

    public void setRsServiceBuyingFrequency(String rsServiceBuyingFrequency) {
        this.rsServiceBuyingFrequency = rsServiceBuyingFrequency;
    }

    public String getRsAverageCustomerDay() {
        return rsAverageCustomerDay;
    }

    public void setRsAverageCustomerDay(String rsAverageCustomerDay) {
        this.rsAverageCustomerDay = rsAverageCustomerDay;
    }

    public String getRsAverageSalesWeek() {
        return rsAverageSalesWeek;
    }

    public void setRsAverageSalesWeek(String rsAverageSalesWeek) {
        this.rsAverageSalesWeek = rsAverageSalesWeek;
    }

    public String getRsAverageSalesMonth() {
        return rsAverageSalesMonth;
    }

    public void setRsAverageSalesMonth(String rsAverageSalesMonth) {
        this.rsAverageSalesMonth = rsAverageSalesMonth;
    }

    public String getRsFinance() {
        return rsFinance;
    }

    public void setRsFinance(String rsFinance) {
        this.rsFinance = rsFinance;
    }

    public String getRsFinanceAmount() {
        return rsFinanceAmount;
    }

    public void setRsFinanceAmount(String rsFinanceAmount) {
        this.rsFinanceAmount = rsFinanceAmount;
    }

    public String getRsShopInsuranceAvailable() {
        return rsShopInsuranceAvailable;
    }

    public void setRsShopInsuranceAvailable(String rsShopInsuranceAvailable) {
        this.rsShopInsuranceAvailable = rsShopInsuranceAvailable;
    }

    public String getRsMedicalInsuranceAvailable() {
        return rsMedicalInsuranceAvailable;
    }

    public void setRsMedicalInsuranceAvailable(String rsMedicalInsuranceAvailable) {
        this.rsMedicalInsuranceAvailable = rsMedicalInsuranceAvailable;
    }

    public String getRsMedicalInsuranceRequired() {
        return rsMedicalInsuranceRequired;
    }

    public void setRsMedicalInsuranceRequired(String rsMedicalInsuranceRequired) {
        this.rsMedicalInsuranceRequired = rsMedicalInsuranceRequired;
    }

    public String getRsLifeInsuranceAvailable() {
        return rsLifeInsuranceAvailable;
    }

    public void setRsLifeInsuranceAvailable(String rsLifeInsuranceAvailable) {
        this.rsLifeInsuranceAvailable = rsLifeInsuranceAvailable;
    }

    public String getRsLifeInsuranceRequired() {
        return rsLifeInsuranceRequired;
    }

    public void setRsLifeInsuranceRequired(String rsLifeInsuranceRequired) {
        this.rsLifeInsuranceRequired = rsLifeInsuranceRequired;
    }

    public String getRsTermInsuranceAvailable() {
        return rsTermInsuranceAvailable;
    }

    public void setRsTermInsuranceAvailable(String rsTermInsuranceAvailable) {
        this.rsTermInsuranceAvailable = rsTermInsuranceAvailable;
    }

    public String getRsTermInsuranceRequired() {
        return rsTermInsuranceRequired;
    }

    public void setRsTermInsuranceRequired(String rsTermInsuranceRequired) {
        this.rsTermInsuranceRequired = rsTermInsuranceRequired;
    }

    public String getRsShopInsuranceRequired() {
        return rsShopInsuranceRequired;
    }

    public void setRsShopInsuranceRequired(String rsShopInsuranceRequired) {
        this.rsShopInsuranceRequired = rsShopInsuranceRequired;
    }

    public String getRsTypeOfRetailer() {
        return rsTypeOfRetailer;
    }

    public void setRsTypeOfRetailer(String rsTypeOfRetailer) {
        this.rsTypeOfRetailer = rsTypeOfRetailer;
    }

    public String getRsSocialMedia() {
        return rsSocialMedia;
    }

    public void setRsSocialMedia(String rsSocialMedia) {
        this.rsSocialMedia = rsSocialMedia;
    }


    public String getProduct_Search_key() {
        return product_Search_key;
    }

    public void setProduct_Search_key(String product_Search_key) {
        this.product_Search_key = product_Search_key;
    }

    public String getProduct_Search_cor() {
        return product_Search_cor;
    }

    public void setProduct_Search_cor(String product_Search_cor) {
        this.product_Search_cor = product_Search_cor;
    }

    public String getProduct_Search_emp() {
        return product_Search_emp;
    }

    public void setProduct_Search_emp(String product_Search_emp) {
        this.product_Search_emp = product_Search_emp;
    }
}
