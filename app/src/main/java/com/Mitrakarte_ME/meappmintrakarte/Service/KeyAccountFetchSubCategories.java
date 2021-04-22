package com.Mitrakarte_ME.meappmintrakarte.Service;

import android.content.Context;
import android.os.AsyncTask;

import com.Mitrakarte_ME.meappmintrakarte.Activity.KeyAccountProductsActivity;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.Model.Category;

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

public class KeyAccountFetchSubCategories extends AsyncTask<Context, Void, Integer> {

    KeyAccountProductsActivity mContext;
    Integer result = 200;
    AG ht_inst = AG.getInstance();

    @Override
    protected Integer doInBackground(Context... params) {

        BufferedReader reader = null;

        try {
            // Create Apache HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base+"fetchSubcategoryKeyAccount");

            List<NameValuePair> postRequestParams = new ArrayList<>();
            mContext = (KeyAccountProductsActivity) params[0];

            System.out.println("jyo Category sending paramter"+"mobile"+ht_inst.getUser().getkMobile()+"keyaccount_token"+ht_inst.getUser().getKeyAccountToken());
            postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(ht_inst.getUser().getkMobile())));
            postRequestParams.add(new BasicNameValuePair("category", String.valueOf(ht_inst.getCategory().getCatId())));
            postRequestParams.add(new BasicNameValuePair("keyaccount_token", String.valueOf(ht_inst.getUser().getKeyAccountToken())));
            postRequest.setEntity(new UrlEncodedFormEntity(postRequestParams));

            HttpResponse httpResponse = httpclient.execute(postRequest);

            String line;
            reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));



            if((line = reader.readLine())!=null){
                System.out.println("jyo get fetch my sub_category response "+line);
            }


            System.out.println("jyo get fetch my sub_category response "+line);
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

            JSONArray post1 = post.getJSONArray("image");

            //todo: Clear Previous Sub Category List(getAllProductlistnew)
            ht_inst.getAllProductlistnew().clear();
            ArrayList<Category> categories=new ArrayList<>();

            for (int i = 0; i < post1.length(); i++) {

                JSONObject item = post1.getJSONObject(i);
                Category catItem = new Category();
                catItem.setSub_catId(item.getInt("subcategory_id"));
                catItem.setSub_catName(item.getString("subcategory"));
                catItem.setSub_catImage(item.getString("img"));
                catItem.setCatId(item.getInt("category"));
                //todo: Getting all the Sub Category List(getAllProductlistnew)
                ht_inst.getAllProductlistnew().add(catItem);

            }

            System.out.println("jyo number of sub_category fetched is " + ht_inst.getAllProductlistnew().size());
        } catch (Exception e) {


            System.out.println("jyo failed  parse fetch sub_category");

            //Todo:If the Subcategory is empty Clear the old Subcategory
           // ht_inst.getAllProductlistnew().clear();
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