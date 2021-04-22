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

public class KeyAccountAddressChange extends AsyncTask<String, Void, Integer> {

    AG om_inst = AG.getInstance();
    Integer result = 200;

    @Override
    protected Integer doInBackground(String... strings) {

        BufferedReader reader = null;

        try {
            // Create Apache HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base + "insertUserAddressKeyAccount");

            List<NameValuePair> postRequestParams = new ArrayList<>();


            System.out.println("AddsressSelectActivity" + "mobile " + om_inst.getUser().getkMobile() );
            System.out.println("AddsressSelectActivity"+ "keyaccount_token " + om_inst.getUser().getKeyAccountToken());
            System.out.println("AddsressSelectActivity"+ "keyaccount_id " + om_inst.getUser().getKeyAccountId());


                    //Todo: Customer Registers field added
             postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(om_inst.getUser().getkMobile())));
            postRequestParams.add(new BasicNameValuePair("keyaccount_token", String.valueOf(om_inst.getUser().getKeyAccountToken())));
            postRequestParams.add(new BasicNameValuePair("keyaccount_id", String.valueOf(om_inst.getUser().getKeyAccountId())));

            postRequestParams.add(new BasicNameValuePair("name", String.valueOf(om_inst.getUser().getkName_Del())));
            postRequestParams.add(new BasicNameValuePair("address", String.valueOf(om_inst.getUser().getKaddress_Del())));
            postRequestParams.add(new BasicNameValuePair("locality", String.valueOf(om_inst.getUser().getkLocality_Del())));
            postRequestParams.add(new BasicNameValuePair("land_mark", String.valueOf(om_inst.getUser().getkLand_mark_Del())));
            postRequestParams.add(new BasicNameValuePair("country", String.valueOf(om_inst.getUser().getkContact_del())));
            postRequestParams.add(new BasicNameValuePair("state", String.valueOf(om_inst.getUser().getkState_del())));
            postRequestParams.add(new BasicNameValuePair("city", String.valueOf(om_inst.getUser().getkCity_del())));
            postRequestParams.add(new BasicNameValuePair("pin", String.valueOf(om_inst.getUser().getkPin_del())));
            postRequestParams.add(new BasicNameValuePair("contact", String.valueOf(om_inst.getUser().getkContact_del())));
            postRequestParams.add(new BasicNameValuePair("email", String.valueOf(om_inst.getUser().getkEmail_del())));



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
