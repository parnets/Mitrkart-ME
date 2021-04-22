package com.Mitrakarte_ME.meappmintrakarte.Service;

import android.os.AsyncTask;

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.Model.User;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.Mitrakarte_ME.meappmintrakarte.Util.Constants.url_base;

public class Getlogin extends AsyncTask<String, Void, Integer> {

    Integer result = 201;
    AG om_inst = AG.getInstance();

    @Override
    protected Integer doInBackground(String... params) {

        BufferedReader reader = null;
        try {
            // Create Apache HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base+"login");

            List<NameValuePair> postRequestParams = new ArrayList<>();

            //System.out.println("jyo login sending paramter "  + "password " + params[1]+ "mobile"+params[0]);

            postRequestParams.add(new BasicNameValuePair("mobile", params[0]));
            postRequestParams.add(new BasicNameValuePair("password", params[1]));

            postRequest.setEntity(new UrlEncodedFormEntity(postRequestParams));

            HttpResponse httpResponse = httpclient.execute(postRequest);

            String line;

            reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            if((line = reader.readLine())!=null){
                System.out.println("jyo get login response "+line);
            }

            parseResult(line);

        } catch (Exception e) {
            System.out.println("jyo"+e.getCause());
            e.printStackTrace();
            System.out.println("jyo exception occurred ");
        }finally {
            if(reader != null){
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
            JSONObject post = new JSONObject(response);
            System.out.println("jyo parse response "+response);
            result = post.getInt("errorCode");

            JSONArray post1 = post.getJSONArray("data");
            System.out.println("jyo 1 "+post1.length());
            for(int i = 0; i < post1.length(); i++ ) {

                JSONObject item = post1.getJSONObject(i);

                User udata = new User();
                udata.setUserId(item.getInt("me_id"));
                udata.setUserToken(item.getString("me_token"));
                udata.setStatus(item.getString("status"));
                udata.setMobile(item.getString("mobile"));
                udata.setPassword(item.getString("password"));
                udata.setMeName(item.getString("name"));
                udata.setWhatsappNo(item.getString("whatsaap_no"));
                udata.setFatherName(item.getString("father_name"));
                udata.setMailID(item.getString("mail_id"));

                udata.setAddress(item.getString("address"));
                udata.setVillageName(item.getString("village_name"));
                udata.setVillage_code(item.getString("village_code"));
                udata.setGstNo(item.getString("pincode"));
                udata.setPincode(item.getString("taluka"));
                udata.setDistrictName(item.getString("district_name"));
                udata.setAadhaarNo(item.getString("aadhaar_no"));
                udata.setEducationQualification(item.getString("education_qualification"));
                udata.setTwoWheelerDetail(item.getString("two_wheeler_detail"));
                udata.setTwoWheelerLicenseNo(item.getString("two_wheeler_licenseNo"));
                udata.setThreeWheelerDetail(item.getString("three_wheeler_detail"));
                udata.setEtFourWheelerDetail16(item.getString("four_wheeler_detail"));

                udata.setAndroidPhoneName(item.getString("android_phoneName"));
                udata.setAlternateContactNo(item.getString("sales_experience"));
                udata.setBankName(item.getString("bank_name"));
                udata.setBranch(item.getString("branch"));
                udata.setIfscCode(item.getString("ifsc_code"));
                udata.setAccountNumber(item.getString("account_number"));
                udata.setPhoto(item.getString("photo"));
                udata.setDigitalSignature(item.getString("digital_signature"));

//                udata.setUserId(item.getInt("retailer_id"));
//                udata.setMobile(item.getString("per_contactNo"));
//                udata.setPassword(item.getString("password"));
//                udata.setUserToken(item.getString("retailer_token"));
//                udata.setStatus(item.getString("status"));
//                udata.setFirmName(item.getString("firm_name"));
//                udata.setPartnership(item.getString("partnership"));
//                udata.setPartnername(item.getString("partnername"));
//                udata.setTypeofoutlet(item.getString("typeofoutlet"));
//                udata.setGstNo(item.getString("gst_no"));
//                udata.setAadhaarNo(item.getString("aadhaar_no"));
//                udata.setPanNo(item.getString("pan_no"));
//                udata.setEmail(item.getString("email"));
//                udata.setVillageName(item.getString("village_name"));
//                udata.setVillage_code(item.getString("village_code"));
//                udata.setTaluka(item.getString("taluka"));
//                udata.setDistrictName(item.getString("district_name"));
//                udata.setState(item.getString("state"));
//                udata.setPincode(item.getString("pincode"));
//                udata.setAlternateContactNo(item.getString("addressWith_landmark"));
//                udata.setResidentialAddress(item.getString("residential_address"));
//                udata.setAlternateContactNo(item.getString("alt_contactNo"));
//                udata.setWhatsappNo(item.getString("whatsapp_no"));
//                udata.setBankName(item.getString("bank_name"));
//                udata.setAccountNumber(item.getString("account_number"));
//                udata.setIfscCode(item.getString("ifsc_code"));
//                udata.setBranch(item.getString("branch"));
//                udata.setFingerprint(item.getString("fingerprint"));
//                udata.setTerm_condition(item.getString("term_condition"));
//                udata.setLive_location(item.getString("live_location"));

                om_inst.setUser(udata);


                //System.out.println("jyo mail"+udata.getEmail()+"jyo id"+"jyo token"+udata.getUserToken()+"jyo name"+udata.getName()+"jyo mobile"+udata.getMobile());
               /* System.out.println("jyo id"+udata.getUserId());
                System.out.println("jyo id"+udata.getUserId());
                System.out.println("jyo mobile"+udata.getMobile());
                System.out.println("jyo mail"+udata.getEmail());
                System.out.println("jyo token"+udata.getUserToken());*/
            }
        }catch (Exception e) {
            System.out.println("jyo failed login parse");
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
