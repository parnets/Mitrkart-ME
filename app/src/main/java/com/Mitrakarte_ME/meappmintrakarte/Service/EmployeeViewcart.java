package com.Mitrakarte_ME.meappmintrakarte.Service;

import android.content.Context;
import android.os.AsyncTask;

import com.Mitrakarte_ME.meappmintrakarte.Activity.EmployeeViewcartActivity;
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

public class EmployeeViewcart extends AsyncTask<Context, Void, Integer> {

    //Todo: Key Account View Cart
    EmployeeViewcartActivity mContext;
    Integer result = 200;
    AG ht_inst = AG.getInstance();

    @Override
    protected Integer doInBackground(Context... params) {

        BufferedReader reader = null;

        try {
            // Create Apache HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base+"viewCartEmployee");

            List<NameValuePair> postRequestParams = new ArrayList<>();
            mContext = (EmployeeViewcartActivity) params[0];

            System.out.println("jyo Category sending paramter"+"mobile"+ht_inst.getUser().getEmpMobile()+"employee_token"+ht_inst.getUser().getEmployeeToken());
            postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(ht_inst.getUser().getEmpMobile())));
            postRequestParams.add(new BasicNameValuePair("employee_token", String.valueOf(ht_inst.getUser().getEmployeeToken())));
            postRequestParams.add(new BasicNameValuePair("emp_id", String.valueOf(ht_inst.getUser().getEmployeeId())));

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

            ht_inst.getEmployeeViewCartlist().clear();


            System.out.println("jyo 1 "+post.length());
            for (int i = 0; i < post1.length(); i++) {
                JSONObject item = post1.getJSONObject(i);

                Category catItem = new Category();
                catItem.setCart_id_emp(item.getString("cart_id"));
                //  catItem.setu(item.getString("key_account_id"));
                catItem.setPro_id_emp(item.getInt("p_id"));
                catItem.setPro_name_emp(item.getString("p_name"));
                catItem.setImage_emp(item.getString("p_image"));
                catItem.setPrice_emp(item.getString("p_price"));
                catItem.setQuantity_emp(item.getString("pro_qnty"));
                catItem.setDeliverType_emp(item.getString("delivery_type"));
                catItem.setP_address_emp(item.getString("address"));
                catItem.setPinCode_emp(item.getString("pin"));
                catItem.setUpdated_on_emp(item.getString("updated_on"));
                catItem.setCreated_on_emp(item.getString("created_on"));


                ht_inst.getEmployeeViewCartlist().add(catItem);

            }

            System.out.println("jyo number of category fetched is "+ht_inst.getEmployeeViewCartlist().size());

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


