package com.Mitrakarte_ME.meappmintrakarte.Service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.Mitrakarte_ME.meappmintrakarte.Activity.OrderProductViewActivity;
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

public class OrderHistoryProduct extends AsyncTask<Context, Void, Integer> {

    OrderProductViewActivity mContext;
    Integer result = 200;
    AG ht_inst = AG.getInstance();

    @Override
    protected Integer doInBackground(Context... params) {

        BufferedReader reader = null;

        try {
            // Create Apache HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base+"orderHistoryProView");

            List<NameValuePair> postRequestParams = new ArrayList<>();
            mContext = (OrderProductViewActivity) params[0];

          //  System.out.println("jyo Category sending paramter"+"mobile"+ht_inst.getUser().getkMobile()+"keyaccount_token"+ht_inst.getUser().getKeyAccountToken());
            System.out.println("OrderProductViewActivity      "+"keyaccount_order_id  "+ht_inst.getTables().getOrder_id());
            System.out.println("OrderProductViewActivity        "+"mobile "+ht_inst.getUser().getkMobile());
            System.out.println("OrderProductViewActivity       "+"keyaccount_token "+ht_inst.getUser().getKeyAccountToken());
            postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(ht_inst.getUser().getkMobile())));
            postRequestParams.add(new BasicNameValuePair("keyaccount_token", String.valueOf(ht_inst.getUser().getKeyAccountToken())));
            postRequestParams.add(new BasicNameValuePair("keyaccount_order_id", String.valueOf(ht_inst.getTables().getOrder_id())));

            postRequest.setEntity(new UrlEncodedFormEntity(postRequestParams));
            Log.d("PlaceOrderActivity", "The Sending Parametrers " + postRequestParams );



            HttpResponse httpResponse = httpclient.execute(postRequest);

            String line;
            reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));



            if((line = reader.readLine())!=null){
                System.out.println("jyo get fetch my categ response "+line);
            }

            Log.d("PlaceOrderActivity", "The Sending Parametrers 2 " + line );

            // Log.d("PlaceOrderActivity", "The Sending Parametrers 3 " +  parseResult(line) );

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

    /*
     * Parsing the feed results and get the list
     *
     * @param response
     */


    private void parseResult(String response) {
        try {
            JSONObject post = new JSONObject(response);
            result = post.getInt("errorCode");

            JSONArray post1 = post.getJSONArray("data");

            ht_inst.getOrderHistoryProductlist().clear();


            System.out.println("jyo 1 "+post.length());
            for (int i = 0; i < post1.length(); i++) {
                JSONObject item = post1.getJSONObject(i);

                Tables catItem = new Tables();
                catItem.setDop_id(item.getInt("dop_id"));
                //  catItem.setu(item.getString("key_account_id"));
                catItem.setOrder_id(item.getString("keyaccount_order_id"));
                //catItem.setk(item.getString("keyaccount_id"));
                catItem.setOrder_pro_img(item.getString("pro_img"));
                catItem.setOrder_pro_name(item.getString("pro_name"));
                catItem.setOrder_pro_price(item.getString("pro_price"));
                catItem.setOrder_pro_offer(item.getString("pro_offer"));
                catItem.setSt_qnt(item.getString("pro_qnty"));
                catItem.setOrder_pro_gst(item.getString("pro_gst"));
                catItem.setOrder_status(item.getString("status"));
                catItem.setUpdate_on_place_order(item.getString("updated_on"));
                catItem.setCreated_on_place_order(item.getString("created_on"));


                ht_inst.getOrderHistoryProductlist().add(catItem);

            }

            System.out.println("jyo number of category fetched is "+ht_inst.getOrderHistoryProductlist().size());

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

