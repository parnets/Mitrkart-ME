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

public class RetailerRegister extends AsyncTask<String, Void, Integer> {

    AG om_inst = AG.getInstance();
    Integer result = 200;

    @Override
    protected Integer doInBackground(String... strings) {

        BufferedReader reader = null;

        try {
            // Create Apache HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base + "profileInsertRetailer");

            List<NameValuePair> postRequestParams = new ArrayList<>();

            System.out.println("jyo reg sending paramter" + "mobile" + om_inst.getUser().getUserId() + "name" + om_inst.getUser().getUserToken());

            //Todo: Retailer Registers field added
            postRequestParams.add(new BasicNameValuePair("retailer_id", String.valueOf(om_inst.getUser().getUserId())));
            postRequestParams.add(new BasicNameValuePair("retailer_token", String.valueOf(om_inst.getUser().getUserToken())));
            postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(om_inst.getUser().getMobile())));


            postRequestParams.add(new BasicNameValuePair("firm_name", String.valueOf(om_inst.getUser().getrFirmName())));
            postRequestParams.add(new BasicNameValuePair("partnership", String.valueOf(om_inst.getUser().getrPartnership())));
            postRequestParams.add(new BasicNameValuePair("partnername", String.valueOf(om_inst.getUser().getrPartnername())));
            postRequestParams.add(new BasicNameValuePair("typeofoutlet", String.valueOf(om_inst.getUser().getrTypeofoutlet())));
            postRequestParams.add(new BasicNameValuePair("gst_no", String.valueOf(om_inst.getUser().getrGstNo())));
            postRequestParams.add(new BasicNameValuePair("aadhaar_no", String.valueOf(om_inst.getUser().getrAadhaarNo())));
            postRequestParams.add(new BasicNameValuePair("pan_no", String.valueOf(om_inst.getUser().getrPanNo())));
            postRequestParams.add(new BasicNameValuePair("email", String.valueOf(om_inst.getUser().getrEmail())));

            postRequestParams.add(new BasicNameValuePair("village_name", String.valueOf(om_inst.getUser().getrVillageName())));
            postRequestParams.add(new BasicNameValuePair("village_code", String.valueOf(om_inst.getUser().getrVillage_code())));
            postRequestParams.add(new BasicNameValuePair("taluka", String.valueOf(om_inst.getUser().getrTaluka())));
            postRequestParams.add(new BasicNameValuePair("district_name", String.valueOf(om_inst.getUser().getrDistrictName())));
            postRequestParams.add(new BasicNameValuePair("state", String.valueOf(om_inst.getUser().getrState())));
            postRequestParams.add(new BasicNameValuePair("pincode", String.valueOf(om_inst.getUser().getrPincode())));
            postRequestParams.add(new BasicNameValuePair("addressWith_landmark", String.valueOf(om_inst.getUser().getrAddressWithLandmark())));
            postRequestParams.add(new BasicNameValuePair("residential_address", String.valueOf(om_inst.getUser().getrResidentialAddress())));
            postRequestParams.add(new BasicNameValuePair("alt_contactNo", String.valueOf(om_inst.getUser().getrAlternateContactNo())));
            postRequestParams.add(new BasicNameValuePair("whatsapp_no", String.valueOf(om_inst.getUser().getrWhatsappNo())));
            postRequestParams.add(new BasicNameValuePair("bank_name", String.valueOf(om_inst.getUser().getrBankName())));
            postRequestParams.add(new BasicNameValuePair("account_number", String.valueOf(om_inst.getUser().getrAccountNumber())));
            postRequestParams.add(new BasicNameValuePair("ifsc_code", String.valueOf(om_inst.getUser().getrIfscCode())));
            postRequestParams.add(new BasicNameValuePair("branch", String.valueOf(om_inst.getUser().getrBranch())));
            postRequestParams.add(new BasicNameValuePair("password", String.valueOf(om_inst.getUser().getrPassword())));
            postRequestParams.add(new BasicNameValuePair("longitude", String.valueOf(om_inst.getUser().getrLongitude())));
            postRequestParams.add(new BasicNameValuePair("latitude", String.valueOf(om_inst.getUser().getrLatitude())));


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

