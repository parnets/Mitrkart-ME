package com.Mitrakarte_ME.meappmintrakarte.Service;

import android.os.AsyncTask;

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.Mitrakarte_ME.meappmintrakarte.Util.Constants.url_base;

public class MERegister extends AsyncTask<String, Void, Integer> {

    AG om_inst = AG.getInstance();
    Integer result = 200;

    @Override
    protected Integer doInBackground(String... strings) {

        BufferedReader reader = null;

        try {
            // Create Apache HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base + "profileDetailsUpdate");

            List<NameValuePair> postRequestParams = new ArrayList<>();


            System.out.println("jyo reg sending paramter" + "mobile" + om_inst.getUser().getUserId() + "name" + om_inst.getUser().getUserToken());


            //Todo: Retailer Registers field added
            postRequestParams.add(new BasicNameValuePair("me_id", String.valueOf(om_inst.getUser().getUserId())));
            postRequestParams.add(new BasicNameValuePair("me_token", String.valueOf(om_inst.getUser().getUserToken())));
            postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(om_inst.getUser().getMobile())));
            postRequestParams.add(new BasicNameValuePair("name", String.valueOf(om_inst.getUser().getMeName())));
            postRequestParams.add(new BasicNameValuePair("whatsaap_no", String.valueOf(om_inst.getUser().getWhatsappNo())));
            postRequestParams.add(new BasicNameValuePair("father_name", String.valueOf(om_inst.getUser().getFatherName())));
            postRequestParams.add(new BasicNameValuePair("mail_id", String.valueOf(om_inst.getUser().getMailID())));
            postRequestParams.add(new BasicNameValuePair("address", String.valueOf(om_inst.getUser().getAddress())));
            postRequestParams.add(new BasicNameValuePair("village_name", String.valueOf(om_inst.getUser().getVillageName())));
            postRequestParams.add(new BasicNameValuePair("village_code", String.valueOf(om_inst.getUser().getVillage_code())));
            postRequestParams.add(new BasicNameValuePair("pincode", String.valueOf(om_inst.getUser().getPincode())));
            postRequestParams.add(new BasicNameValuePair("taluka", String.valueOf(om_inst.getUser().getTaluka())));
            postRequestParams.add(new BasicNameValuePair("district_name", String.valueOf(om_inst.getUser().getDistrictName())));
            postRequestParams.add(new BasicNameValuePair("aadhaar_no", String.valueOf(om_inst.getUser().getAadhaarNo())));
            postRequestParams.add(new BasicNameValuePair("education_qualification", String.valueOf(om_inst.getUser().getEducationQualification())));
            postRequestParams.add(new BasicNameValuePair("two_wheeler_detail", String.valueOf(om_inst.getUser().getTwoWheelerDetail())));
            postRequestParams.add(new BasicNameValuePair("two_wheeler_licenseNo", String.valueOf(om_inst.getUser().getTwoWheelerLicenseNo())));
            postRequestParams.add(new BasicNameValuePair("three_wheeler_detail", String.valueOf(om_inst.getUser().getThreeWheelerDetail())));
            postRequestParams.add(new BasicNameValuePair("four_wheeler_detail", String.valueOf(om_inst.getUser().getEtFourWheelerDetail16())));
            postRequestParams.add(new BasicNameValuePair("android_phoneName", String.valueOf(om_inst.getUser().getAndroidPhoneName())));
            postRequestParams.add(new BasicNameValuePair("sales_experience", String.valueOf(om_inst.getUser().getSalesExperience())));
            postRequestParams.add(new BasicNameValuePair("bank_name", String.valueOf(om_inst.getUser().getBankName())));
            postRequestParams.add(new BasicNameValuePair("branch", String.valueOf(om_inst.getUser().getBranch())));
            postRequestParams.add(new BasicNameValuePair("ifsc_code", String.valueOf(om_inst.getUser().getIfscCode())));
            postRequestParams.add(new BasicNameValuePair("account_number", String.valueOf(om_inst.getUser().getAccountNumber())));
            postRequestParams.add(new BasicNameValuePair("password", String.valueOf(om_inst.getUser().getPassword())));
            postRequestParams.add(new BasicNameValuePair("photo", String.valueOf(om_inst.getUser().getMePhoto())));
//            postRequestParams.add(new BasicNameValuePair("residential_address", String.valueOf(om_inst.getUser().getResidentialAddress())));


            postRequest.setEntity(new UrlEncodedFormEntity(postRequestParams));

            HttpResponse httpResponse = httpclient.execute(postRequest);
            postRequest.setEntity(new UrlEncodedFormEntity(postRequestParams));


            String line;
            reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            if ((line = reader.readLine()) != null) {
                System.out.println("jyo get register response " + line);
            }

            parseResult(line);

        } catch (Exception e) {
            System.out.println("jyo" + e.getCause());
            e.printStackTrace();
            System.out.println("jyo exception occurred ");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    /**
     * Parsing the feed results and get the list
     *
     * @param response
     */
    private void parseResult(String response) {
        try {
            System.out.println("jyo verfied calling parse");
            JSONObject post = new JSONObject(response);
            result = post.getInt("errorCode");
            //  om_inst.getUser().setUserId(post.getInt("id"));

//            om_inst.getUser().setComp_id(post.getString("company_id"));
//            System.out.println("jyo"+om_inst.getUser().getComp_id());
        } catch (Exception e) {
            System.out.println("jyo failed reg parse");
        }

    }

    @Override
    protected void onPostExecute(Integer integer) {
        // Download complete. Lets update UI

        if (result == 1) {

            //mGridAdapter.setGridData(mGridData);
        } else {
            // Toast.makeText(LocationViewActivity.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }
}


//package com.mintrakarte.retailerappmintrakarte.Service;
//
//import android.os.AsyncTask;
//
//import com.mintrakarte.retailerappmintrakarte.Model.AG;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.mintrakarte.retailerappmintrakarte.Util.Constants.url_base;
//
//public class Register extends AsyncTask<String, Void, Integer> {
//
//    AG om_inst = AG.getInstance();
//    Integer result = 200;
//
//    @Override
//    protected Integer doInBackground(String... strings) {
//
//        BufferedReader reader = null;
//
//        try {
//            // Create Apache HttpClient
//            HttpClient httpclient = new DefaultHttpClient();
//            HttpPost postRequest = new HttpPost(url_base + "profileDetailsUpdate");
//
//            List<NameValuePair> postRequestParams = new ArrayList<>();
//
//
//            System.out.println("jyo reg sending paramter" + "mobile" + om_inst.getUser().getUserId() + "name" + om_inst.getUser().getUserToken());
//            //  postRequestParams.add(new BasicNameValuePair("user_id", String.valueOf(om_inst.getUser().getUserId())));
//            //  postRequestParams.add(new BasicNameValuePair("token", String.valueOf(om_inst.getUser().getUserToken())));
//            //   postRequestParams.add(new BasicNameValuePair("name", String.valueOf(om_inst.getUser().getName())));
//            //   postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(om_inst.getUser().getMobile())));
//            //   System.out.println("jyo reg sending paramter"+"email"+om_inst.getUser().getEmail()+"pwd"+om_inst.getUser().getPassword());
//            //  postRequestParams.add(new BasicNameValuePair("email", String.valueOf(om_inst.getUser().getEmail())));
//            //  postRequestParams.add(new BasicNameValuePair("password", String.valueOf(om_inst.getUser().getPassword())));
//            //  postRequestParams.add(new BasicNameValuePair("gender", String.valueOf(om_inst.getUser().getGender())));
//            // postRequestParams.add(new BasicNameValuePair("dob", String.valueOf(om_inst.getUser().getDob())));
//            // System.out.println("jyo reg sending paramter"+"gender"+om_inst.getUser().getGender());
//            // postRequestParams.add(new BasicNameValuePair("address", String.valueOf(om_inst.getUser().getAddress())));
//
//
//
//            //Todo: Vendor Registers field added
//            postRequestParams.add(new BasicNameValuePair("retailer_id", String.valueOf(om_inst.getUser().getUserId())));
//            postRequestParams.add(new BasicNameValuePair("retailer_token", String.valueOf(om_inst.getUser().getUserToken())));
//           postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(om_inst.getUser().getMobile())));
//
//            postRequestParams.add(new BasicNameValuePair("email", String.valueOf(om_inst.getUser().getMailID())));
//            postRequestParams.add(new BasicNameValuePair("firm_name", String.valueOf(om_inst.getUser().getFirmName())));
//            postRequestParams.add(new BasicNameValuePair("partnership", String.valueOf(om_inst.getUser().getPartnership())));
//            postRequestParams.add(new BasicNameValuePair("partnername", String.valueOf(om_inst.getUser().getPartnername())));
//            postRequestParams.add(new BasicNameValuePair("typeofoutlet", String.valueOf(om_inst.getUser().getTypeofoutlet())));
//            postRequestParams.add(new BasicNameValuePair("gst_no", String.valueOf(om_inst.getUser().getGstNo())));
//            postRequestParams.add(new BasicNameValuePair("aadhaar_no", String.valueOf(om_inst.getUser().getAadhaarNo())));
//            postRequestParams.add(new BasicNameValuePair("pan_no", String.valueOf(om_inst.getUser().getPanNo())));
//            postRequestParams.add(new BasicNameValuePair("village_name", String.valueOf(om_inst.getUser().getVillageName())));
//            postRequestParams.add(new BasicNameValuePair("village_code", String.valueOf(om_inst.getUser().getVillage_code())));
//            postRequestParams.add(new BasicNameValuePair("taluka", String.valueOf(om_inst.getUser().getTaluka())));
//            postRequestParams.add(new BasicNameValuePair("district_name", String.valueOf(om_inst.getUser().getDistrictName())));
//            postRequestParams.add(new BasicNameValuePair("state", String.valueOf(om_inst.getUser().getState())));
//           // postRequestParams.add(new BasicNameValuePair("country", String.valueOf(om_inst.getUser().getCountry())));
//            postRequestParams.add(new BasicNameValuePair("pincode", String.valueOf(om_inst.getUser().getPincode())));
//            postRequestParams.add(new BasicNameValuePair("addressWith_landmark", String.valueOf(om_inst.getUser().getAddressWithLandmark())));
//            postRequestParams.add(new BasicNameValuePair("residential_address", String.valueOf(om_inst.getUser().getResidentialAddress())));
//
//            //postRequestParams.add(new BasicNameValuePair("per_contactNo", String.valueOf(om_inst.getUser().getMobile())));
//            postRequestParams.add(new BasicNameValuePair("alt_contactNo", String.valueOf(om_inst.getUser().getAlternateContactNo())));
//            postRequestParams.add(new BasicNameValuePair("whatsapp_no", String.valueOf(om_inst.getUser().getWhatsappNo())));
//            postRequestParams.add(new BasicNameValuePair("bank_name", String.valueOf(om_inst.getUser().getBankName())));
//            postRequestParams.add(new BasicNameValuePair("account_number", String.valueOf(om_inst.getUser().getAccountNumber())));
//            postRequestParams.add(new BasicNameValuePair("ifsc_code", String.valueOf(om_inst.getUser().getIfscCode())));
//            postRequestParams.add(new BasicNameValuePair("branch", String.valueOf(om_inst.getUser().getBranch())));
//            postRequestParams.add(new BasicNameValuePair("password", String.valueOf(om_inst.getUser().getPassword())));
//
//
//            postRequest.setEntity(new UrlEncodedFormEntity(postRequestParams));
//
//            HttpResponse httpResponse = httpclient.execute(postRequest);
//            postRequest.setEntity(new UrlEncodedFormEntity(postRequestParams));
//
//
//            String line;
//            reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
//            if ((line = reader.readLine()) != null) {
//                System.out.println("jyo get register response " + line);
//            }
//
//            parseResult(line);
//
//        } catch (Exception e) {
//            System.out.println("jyo" + e.getCause());
//            e.printStackTrace();
//            System.out.println("jyo exception occurred ");
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return result;
//    }
//
//    /**
//     * Parsing the feed results and get the list
//     *
//     * @param response
//     */
//    private void parseResult(String response) {
//        try {
//            System.out.println("jyo verfied calling parse");
//            JSONObject post = new JSONObject(response);
//            result = post.getInt("errorCode");
//            //  om_inst.getUser().setUserId(post.getInt("id"));
//
////            om_inst.getUser().setComp_id(post.getString("company_id"));
////            System.out.println("jyo"+om_inst.getUser().getComp_id());
//        } catch (Exception e) {
//            System.out.println("jyo failed reg parse");
//        }
//
//    }
//
//    @Override
//    protected void onPostExecute(Integer integer) {
//        // Download complete. Lets update UI
//
//        if (result == 1) {
//
//            //mGridAdapter.setGridData(mGridData);
//        } else {
//            // Toast.makeText(LocationViewActivity.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
//        }
//    }
//}
