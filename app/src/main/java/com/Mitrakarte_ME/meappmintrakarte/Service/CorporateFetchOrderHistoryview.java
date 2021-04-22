package com.Mitrakarte_ME.meappmintrakarte.Service;

import android.content.Context;
import android.os.AsyncTask;

import com.Mitrakarte_ME.meappmintrakarte.Activity.CorporateMyOrderActivity;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.Model.Tables;

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

public class CorporateFetchOrderHistoryview extends AsyncTask<Context, Void, Integer> {

    CorporateMyOrderActivity mContext;
    Integer result = 200;
    AG ht_inst = AG.getInstance();

    @Override
    protected Integer doInBackground(Context... params) {

        BufferedReader reader = null;
        try {
            // Create Apache HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base+"orderHistoryViewCorporate");

            List<NameValuePair> postRequestParams = new ArrayList<>();
            mContext = (CorporateMyOrderActivity) params[0];


            System.out.println("MyOrderActivity "+"mobile "+ht_inst.getUser().getCrmobile());
            System.out.println("MyOrderActivity "+"corporate_token "+ht_inst.getUser().getCorporateToken());
            System.out.println("MyOrderActivity  "+"corporate_id "+ht_inst.getUser().getCorporateId());
            postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(ht_inst.getUser().getCrmobile())));
            postRequestParams.add(new BasicNameValuePair("corporate_token", String.valueOf(ht_inst.getUser().getCorporateToken())));
            postRequestParams.add(new BasicNameValuePair("corporate_id", String.valueOf(ht_inst.getUser().getCorporateId())));

            postRequest.setEntity(new UrlEncodedFormEntity(postRequestParams));

            HttpResponse httpResponse = httpclient.execute(postRequest);

            String line;

            reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            if((line = reader.readLine())!=null){
                System.out.println("jyo get fetch my orderHistory response "+line);
            }
            //  System.out.println("jyo get fetch my orderHistory response "+line);
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

           ht_inst.getCorporateOrderHistorylist().clear();

            System.out.println("jyo 1 "+post.length());
            for (int i = 0; i < post1.length(); i++) {
                JSONObject item = post1.getJSONObject(i);

                Tables catItem = new Tables();
                catItem.setOrder_id_cor(item.getString("corporate_order_id"));
                // System.out.println("jyo order id1 "+catItem.getOrder_id());
                catItem.setTrnsx_id_cor(item.getString("trnx_id"));
                catItem.setGrand_price_cor(item.getString("grand_total"));
                catItem.setCreated_on_place_order_cor(item.getString("created_on"));


                ht_inst.getCorporateOrderHistorylist().add(catItem);
                ht_inst.setAddtable(catItem);

            }

            System.out.println("jyo number of orderHistory fetched is "+ht_inst.getCorporateOrderHistorylist().size());
            // System.out.println("jyo number of orderHistory added "+ht_inst.getAddtable().getOrder_id());

        }catch (Exception e) {

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
}



