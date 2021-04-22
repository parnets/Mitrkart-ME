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

public class ProfileKeyAccount extends AsyncTask<String, Void, Integer> {

    AG om_inst = AG.getInstance();
    Integer result = 200;

    @Override
    protected Integer doInBackground(String... strings) {

        BufferedReader reader = null;

        try {
            // Create Apache HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base + "fetchuserprofileKeyAccount");

            List<NameValuePair> postRequestParams = new ArrayList<>();


            System.out.println("jyo reg sending paramter" + "mobile" + om_inst.getUser().getUserId() + "name" + om_inst.getUser().getUserToken());



            //Todo: Retailer Registers field added
            postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(om_inst.getUser().getkMobile())));
            postRequestParams.add(new BasicNameValuePair("keyaccount_id", String.valueOf(om_inst.getUser().getKeyAccountId())));
            postRequestParams.add(new BasicNameValuePair("keyaccount_token", String.valueOf(om_inst.getUser().getKeyAccountToken())));


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
            JSONObject post = new JSONObject(response);
            System.out.println("jyo parse response "+response);
            result = post.getInt("errorCode");

            JSONArray post1 = post.getJSONArray("data");
            System.out.println("jyo 1 "+post1.length());
            for(int i = 0; i < post1.length(); i++ ) {

                JSONObject item = post1.getJSONObject(i);

                User udata = new User();
                udata.setKeyAccountId(item.getInt("keyaccount_id"));
                udata.setKeyAccountToken(item.getString("keyaccount_token"));
                udata.setkMobile(item.getString("mobile"));
                udata.setkPassword(item.getString("password"));
                udata.setkMAil(item.getString("mail_id"));
                udata.setkStatus(item.getString("status"));
                udata.setkOutletName(item.getString("outlet_name"));
                udata.setkOwnerName(item.getString("owner_name"));
                udata.setkWhatsappNo(item.getString("whatsapp_no"));
                udata.setkEmployeeStrength(item.getString("employee_strength"));
                udata.setkAddress(item.getString("address"));
                udata.setkPincode(item.getString("pincode"));
                udata.setkVillageName(item.getString("village_name"));
                udata.setkVillageCode(item.getString("village_code"));
                udata.setkAadharNumber(item.getString("aadhaar_no"));
                udata.setkGSTNumber(item.getString("gst_no"));
                udata.setkPanNumber(item.getString("pan_no"));
                udata.setkBankName(item.getString("bank_name"));
                udata.setkBankAccountNumber(item.getString("acccount_number"));
                udata.setkBranch(item.getString("branch"));
                udata.setkIFSCCode(item.getString("ifsc_code"));
                udata.setkPhotoCompany(item.getString("photo_company"));
                udata.setkDigitalSignature(item.getString("digital_signature"));




                om_inst.setUser(udata);


            }
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

