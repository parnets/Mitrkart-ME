package com.Mitrakarte_ME.meappmintrakarte.Service;

import android.content.Context;
import android.os.AsyncTask;

import com.Mitrakarte_ME.meappmintrakarte.Activity.CorporateHomeActivity;
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

public class CorporateFetchCategories extends AsyncTask<Context, Void, Integer> {

    CorporateHomeActivity mContext;
    Integer result = 200;
    AG ht_inst = AG.getInstance();

    @Override
    protected Integer doInBackground(Context... params) {
        BufferedReader reader = null;

        try {

            // Create Apache HttpClient
            //Todo: Sending the http request
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base + "fetchCategoriesCorporate");

            List<NameValuePair> postRequestParams = new ArrayList<>();
            mContext = (CorporateHomeActivity) params[0];

            System.out.println("jyo Category sending paramter" + "mobile" + ht_inst.getUser().getCrmobile() + "corporate_token" + ht_inst.getUser().getCorporateToken());

            //Todo: Passing the Paramater of User
            postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(ht_inst.getUser().getCrmobile())));
            postRequestParams.add(new BasicNameValuePair("corporate_token", String.valueOf(ht_inst.getUser().getCorporateToken())));
            postRequest.setEntity(new UrlEncodedFormEntity(postRequestParams));

            //Todo: Getting the http response
            HttpResponse httpResponse = httpClient.execute(postRequest);

            String line;
            reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

            if ((line = reader.readLine()) != null) {
                System.out.println("jyo get fetch my categ response " + line);
            }

            System.out.println("jyo get fetch my categ response " + line);
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

    //Todo: Parsing the result
    private void parseResult(String response) {

        try {
            JSONObject post = new JSONObject(response);
            result = post.getInt("errorCode");

            JSONArray post1 = post.getJSONArray("image");

            ht_inst.getCategoryList().clear();

            System.out.println("jyo 1 " + post.length());
            for (int i = 0; i < post1.length(); i++) {
                JSONObject item = post1.getJSONObject(i);

                Category catItem = new Category();

                //Todo: Handling the response
                catItem.setCatId(item.getInt("cat_id"));
                catItem.setCatName(item.getString("categories"));
                catItem.setCatImage(item.getString("cat_img"));

                //todo: Getting all the Category List(getCategoryList)
                ht_inst.getCategoryList().add(catItem);

            }

            System.out.println("jyo number of category fetched is " + ht_inst.getCategoryList().size());
        } catch (Exception e) {


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