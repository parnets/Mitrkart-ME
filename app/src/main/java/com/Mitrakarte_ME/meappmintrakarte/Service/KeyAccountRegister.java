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

public class KeyAccountRegister  extends AsyncTask<String, Void, Integer> {



    AG om_inst = AG.getInstance();
    Integer result = 200;

    @Override
    protected Integer doInBackground(String... strings) {

        BufferedReader reader = null;

        try {
            // Create Apache HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base + "profileUpdateKeyAccount");

            List<NameValuePair> postRequestParams = new ArrayList<>();


            System.out.println("jyo reg sending paramter" + "mobile" + om_inst.getUser().getKeyAccountId() + "name" + om_inst.getUser().getKeyAccountToken());



            //Todo: Key Account Registers field added

            postRequestParams.add(new BasicNameValuePair("keyaccount_id", String.valueOf(om_inst.getUser().getKeyAccountId())));
            postRequestParams.add(new BasicNameValuePair("keyaccount_token", String.valueOf(om_inst.getUser().getKeyAccountToken())));
//            postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(om_inst.getUser().getMobile())));

//            postRequestParams.add(new BasicNameValuePair("email", String.valueOf(om_inst.getUser().getMailID())));
           // postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(om_inst.getUser().getkMobile())));
            postRequestParams.add(new BasicNameValuePair("outlet_name", String.valueOf(om_inst.getUser().getkOutletName())));
            postRequestParams.add(new BasicNameValuePair("owner_name", String.valueOf(om_inst.getUser().getkOwnerName())));
            postRequestParams.add(new BasicNameValuePair("whatsapp_no", String.valueOf(om_inst.getUser().getkWhatsappNo())));

            postRequestParams.add(new BasicNameValuePair("mail_id", String.valueOf(om_inst.getUser().getkMAil())));

            postRequestParams.add(new BasicNameValuePair("employee_strength", String.valueOf(om_inst.getUser().getkEmployeeStrength())));
            postRequestParams.add(new BasicNameValuePair("address", String.valueOf(om_inst.getUser().getkAddress())));
            postRequestParams.add(new BasicNameValuePair("pincode", String.valueOf(om_inst.getUser().getkPincode())));


            postRequestParams.add(new BasicNameValuePair("village_name", String.valueOf(om_inst.getUser().getkVillageName())));
            postRequestParams.add(new BasicNameValuePair("village_code", String.valueOf(om_inst.getUser().getkVillageCode())));
            postRequestParams.add(new BasicNameValuePair("aadhaar_no", String.valueOf(om_inst.getUser().getkAadharNumber())));
            postRequestParams.add(new BasicNameValuePair("gst_no", String.valueOf(om_inst.getUser().getkGSTNumber())));
            postRequestParams.add(new BasicNameValuePair("pan_no", String.valueOf(om_inst.getUser().getkPanNumber())));
            postRequestParams.add(new BasicNameValuePair("bank_name", String.valueOf(om_inst.getUser().getkBankName())));
            postRequestParams.add(new BasicNameValuePair("acccount_number", String.valueOf(om_inst.getUser().getkAadharNumber())));
            postRequestParams.add(new BasicNameValuePair("branch", String.valueOf(om_inst.getUser().getkBranch())));
            postRequestParams.add(new BasicNameValuePair("ifsc_code", String.valueOf(om_inst.getUser().getkIFSCCode())));
            postRequestParams.add(new BasicNameValuePair("password", String.valueOf(om_inst.getUser().getkPassword())));



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

