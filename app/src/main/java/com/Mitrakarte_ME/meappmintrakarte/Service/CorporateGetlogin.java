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

public class CorporateGetlogin extends AsyncTask<String, Void, Integer> {

    Integer result = 201;
    AG om_inst = AG.getInstance();

    @Override
    protected Integer doInBackground(String... params) {

        BufferedReader reader = null;
        try {
            // Create Apache HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base + "loginCorporate");

            List<NameValuePair> postRequestParams = new ArrayList<>();

            //System.out.println("kiran login sending paramter "  + "password " + params[1]+ "mobile"+params[0]);

            postRequestParams.add(new BasicNameValuePair("mobile", params[0]));
            postRequestParams.add(new BasicNameValuePair("password", params[1]));

            postRequest.setEntity(new UrlEncodedFormEntity(postRequestParams));

            HttpResponse httpResponse = httpclient.execute(postRequest);

            String line;

            reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            if ((line = reader.readLine()) != null) {
                System.out.println("kiran get login response " + line);
            }

            parseResult(line);

        } catch (Exception e) {
            System.out.println("kiran" + e.getCause());
            e.printStackTrace();
            System.out.println("kiran exception occurred ");
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
            System.out.println("kiran parse response " + response);
            result = post.getInt("errorCode");

            JSONArray post1 = post.getJSONArray("data");
            System.out.println("kiran 1 " + post1.length());
            for (int i = 0; i < post1.length(); i++) {

                JSONObject item = post1.getJSONObject(i);

                User udata = new User();
                udata.setCorporateId(item.getInt("corporate_id"));
                udata.setCorporateToken(item.getString("corporate_token"));
                udata.setCrStatus(item.getString("status"));
                udata.setCrmobile(item.getString("mobile"));
                udata.setCrPassword(item.getString("password"));
                udata.setCrOutletName(item.getString("outlet_name"));
                udata.setCrOwnerName(item.getString("owner_name"));
                udata.setCrWhatsappNo(item.getString("whatsapp_no"));
                udata.setCrMailId(item.getString("mail_id"));

                udata.setCrEmployeeStrength(item.getString("employee_strength"));
                udata.setCrAddress(item.getString("address"));
                udata.setCrPincode(item.getString("pincode"));

                udata.setCrVillageName(item.getString("village_name"));
                udata.setCrVillageCode(item.getString("village_code"));
                udata.setCrAadharNumber(item.getString("aadhaar_no"));
                udata.setCrGSTNumber(item.getString("gst_no"));
                udata.setCrPanNumber(item.getString("pan_no"));
                udata.setCrBankName(item.getString("bank_name"));
                udata.setCrBankAccountNumber(item.getString("acccount_number"));
                udata.setCrBranch(item.getString("branch"));
                udata.setCrIFSCCode(item.getString("ifsc_code"));

                udata.setCrPhotoCompany(item.getString("photo_company"));
               // udata.setCrDigitalSignature(item.getString("digital_signature"));


                om_inst.setUser(udata);

                System.out.println("kiran Cor_Login_response1 " + "mobile " + udata.getCrmobile());
                System.out.println("kiran Cor_Login_response1 " + "corporate_token " + udata.getCorporateToken());


                System.out.println("kiran sucessfully login parse");
            }
        } catch (Exception e) {
            System.out.println("kiran failed login parse");
        }

        System.out.println("Cor_Login_response " + "mobile " + AG.getInstance().getUser().getCrmobile());
        System.out.println("Cor_Login_response " + "corporate_token " + AG.getInstance().getUser().getCorporateToken());
        System.out.println("Cor_Login_response " + "status " + AG.getInstance().getUser().getCrStatus());

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
