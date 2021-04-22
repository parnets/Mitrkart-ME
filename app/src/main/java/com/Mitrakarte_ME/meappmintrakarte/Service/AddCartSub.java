package com.Mitrakarte_ME.meappmintrakarte.Service;

import android.content.Context;
import android.os.AsyncTask;

import com.Mitrakarte_ME.meappmintrakarte.Activity.KeyAccountProductsActivity;
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

public class AddCartSub extends AsyncTask<Context, Void, Integer> {

    KeyAccountProductsActivity mContext;
    Integer result = 200;
    AG ht_inst = AG.getInstance();

    @Override
    protected Integer doInBackground(Context... params) {

        BufferedReader reader = null;

        try {
            // Create Apache HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base+"addCart");

            List<NameValuePair> postRequestParams = new ArrayList<>();
            mContext = (KeyAccountProductsActivity) params[0];

            System.out.println("jyo Category sending paramter"+"mobile"+ht_inst.getUser().getkMobile()+"keyaccount_token"+ht_inst.getUser().getKeyAccountToken());
            System.out.println("jyo Category sending paramter"+"key_account_id"+ht_inst.getUser().getKeyAccountId()+"p_price"+ht_inst.getCategory().getCustomer_discount_price());

            postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(ht_inst.getUser().getkMobile())));
            postRequestParams.add(new BasicNameValuePair("keyaccount_token", String.valueOf(ht_inst.getUser().getKeyAccountToken())));
            postRequestParams.add(new BasicNameValuePair("key_account_id", String.valueOf(ht_inst.getUser().getKeyAccountId())));
            postRequestParams.add(new BasicNameValuePair("p_id", String.valueOf(ht_inst.getCategory().getPro_id())));
            postRequestParams.add(new BasicNameValuePair("p_name", String.valueOf(ht_inst.getCategory().getPro_name())));
            postRequestParams.add(new BasicNameValuePair("pro_qnty", String.valueOf(ht_inst.getCategory().getNewquantity())));
            postRequestParams.add(new BasicNameValuePair("p_price", String.valueOf(ht_inst.getCategory().getCustomer_discount_price())));
            postRequestParams.add(new BasicNameValuePair("p_image", String.valueOf(ht_inst.getCategory().getImage())));

            postRequestParams.add(new BasicNameValuePair("hsn", String.valueOf(ht_inst.getCategory().getHsn())));
            postRequestParams.add(new BasicNameValuePair("sku", String.valueOf(ht_inst.getCategory().getSku())));
            postRequestParams.add(new BasicNameValuePair("free", String.valueOf(ht_inst.getCategory().getFree())));
            postRequestParams.add(new BasicNameValuePair("discount", String.valueOf(ht_inst.getCategory().getCustomer_discount_percentage())));

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

