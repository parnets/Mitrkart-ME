package com.Mitrakarte_ME.meappmintrakarte.Service;

import android.content.Context;
import android.os.AsyncTask;

import com.Mitrakarte_ME.meappmintrakarte.Activity.CorporateAddAddressActivity;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.Model.Address;

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

public class AddressCorporate extends AsyncTask<Context, Void, Integer> {

    CorporateAddAddressActivity mContext;
    Integer result = 200;
    AG ht_inst = AG.getInstance();

    @Override
    protected Integer doInBackground(Context... params) {

        BufferedReader reader = null;

        try {
            // Create Apache HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base+"userAddressCorporate");

            List<NameValuePair> postRequestParams = new ArrayList<>();
            mContext = (CorporateAddAddressActivity) params[0];

            System.out.println("CorporateAddAddressActivity  " +"mobile "+ht_inst.getUser().getCrmobile());
            System.out.println("CorporateAddAddressActivity  "+"corporate_token "+ht_inst.getUser().getCorporateToken());
            System.out.println("CorporateAddAddressActivity  "+"corporate_id "+ht_inst.getUser().getCorporateId());
            postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(ht_inst.getUser().getCrmobile())));
            postRequestParams.add(new BasicNameValuePair("corporate_token", String.valueOf(ht_inst.getUser().getCorporateToken())));
            postRequestParams.add(new BasicNameValuePair("corporate_id", String.valueOf(ht_inst.getUser().getCorporateId())));

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

            JSONArray post1 = post.getJSONArray("data");

            ht_inst.getKeyAddressList().clear();


            System.out.println("jyo 1 "+post.length());
            for (int i = 0; i < post1.length(); i++) {
                JSONObject item = post1.getJSONObject(i);

                Address udata = new Address();
                udata.setAdr_cor_id(item.getInt("ca_id"));
                // catItem.setu(item.getString("key_account_id"));
                udata.setAdr_name_cor(item.getString("name"));
                udata.setAdr_corporate_id_cor(item.getInt("corporate_id"));
                udata.setAdr_address_cor(item.getString("address"));
                udata.setAdr_locality_cor(item.getString("locality"));
                udata.setAdr_land_mark_cor(item.getString("land_mark"));
                udata.setAdr_country_cor(item.getString("country"));
                udata.setAdr_state_cor(item.getString("state"));
                udata.setAdr_city_cor(item.getString("city"));
                udata.setAdr_pin_cor(item.getString("pin"));

                udata.setAdr_mobile_cor(item.getString("mobile"));
                udata.setAdr_email_cor(item.getString("email"));
                udata.setAdr_updated_on_cor(item.getString("updated_on"));
                udata.setAdr_created_on_cor(item.getString("created_on"));


                ht_inst.getKeyAddressList().add(udata);

            }

            System.out.println("jyo number of category fetched is "+ht_inst.getKeyAddressList().size());

        }
        catch (Exception e) {
            System.out.println("jyo failed  parse fetch category");
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

    public void execute(String feed_url) {


    }
}


