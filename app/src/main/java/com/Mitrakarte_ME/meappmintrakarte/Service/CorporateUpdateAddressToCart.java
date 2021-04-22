package com.Mitrakarte_ME.meappmintrakarte.Service;

import android.content.Context;
import android.os.AsyncTask;

import com.Mitrakarte_ME.meappmintrakarte.Activity.CorporateAddAddressActivity;
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

public class CorporateUpdateAddressToCart extends AsyncTask<Context, Void, Integer> {

    CorporateAddAddressActivity mContext;
    Integer result = 200;
    AG ht_inst = AG.getInstance();

    @Override
    protected Integer doInBackground(Context... params) {

        BufferedReader reader = null;

        try {
            // Create Apache HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base+"updateCorporateAddresscart");

            List<NameValuePair> postRequestParams = new ArrayList<>();
            mContext = (CorporateAddAddressActivity) params[0];

            System.out.println("AddAddressActivity"+"mobile   "+ht_inst.getUser().getkMobile()+"corporate_token "+ht_inst.getUser().getKeyAccountToken());

//            System.out.println("AddAddressActivity"+"name       "+ht_inst.getAddress().getAdr_name_k());
//            System.out.println("AddAddressActivity"+"name       "+ht_inst.getAddress().getAdr_name_k());
//            System.out.println("AddAddressActivity"+"name       "+ht_inst.getAddress().getAdr_name_k());


            postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(ht_inst.getUser().getCrmobile())));
            postRequestParams.add(new BasicNameValuePair("corporate_token", String.valueOf(ht_inst.getUser().getCorporateToken())));
            postRequestParams.add(new BasicNameValuePair("corporate_id", String.valueOf(ht_inst.getUser().getCorporateId())));

            postRequestParams.add(new BasicNameValuePair("name", String.valueOf(ht_inst.getAddress().getAdr_name_cor())));
            postRequestParams.add(new BasicNameValuePair("locality", String.valueOf(ht_inst.getAddress().getAdr_locality_cor())));
            postRequestParams.add(new BasicNameValuePair("land_mark", String.valueOf(ht_inst.getAddress().getAdr_land_mark_cor())));
            postRequestParams.add(new BasicNameValuePair("country", String.valueOf(ht_inst.getAddress().getAdr_country_cor())));
            postRequestParams.add(new BasicNameValuePair("state", String.valueOf(ht_inst.getAddress().getAdr_state_cor())));
            postRequestParams.add(new BasicNameValuePair("city", String.valueOf(ht_inst.getAddress().getAdr_city_cor())));
            postRequestParams.add(new BasicNameValuePair("pin", String.valueOf(ht_inst.getAddress().getAdr_pin_cor())));

            postRequest.setEntity(new UrlEncodedFormEntity(postRequestParams));

            HttpResponse httpResponse = httpclient.execute(postRequest);

            String line;
            reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));


            if((line = reader.readLine())!=null){
                System.out.println("jyo get fetch my categ response "+line);
            }


            System.out.println("jyo get fetch my categ response "+line);
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
            result = post.getInt("errorCode");


        } catch (Exception e) {
            System.out.println("jyo failed login parse");
        }

    }

    @Override
    protected void onPostExecute(Integer result) {
        // Download complete. Lets update UI

        if (result == 1) {

            //mGridAdapter.setGridData(mGridData);
        } else {
            // Toast.makeText(LocationViewActivity.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }
}
