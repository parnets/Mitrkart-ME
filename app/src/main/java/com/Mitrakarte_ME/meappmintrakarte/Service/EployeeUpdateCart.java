package com.Mitrakarte_ME.meappmintrakarte.Service;

import android.content.Context;
import android.os.AsyncTask;

import com.Mitrakarte_ME.meappmintrakarte.Activity.EmployeeViewcartActivity;
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

public class EployeeUpdateCart  extends AsyncTask<Context, Void, Integer> {

    EmployeeViewcartActivity mContext;
    Integer result = 200;
    AG ht_inst = AG.getInstance();

    @Override
    protected Integer doInBackground(Context... params) {

        BufferedReader reader = null;

        try {
            // Create Apache HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base+"updateCartEmployee");

            List<NameValuePair> postRequestParams = new ArrayList<>();
            mContext = (EmployeeViewcartActivity) params[0];

            System.out.println("ViewcartActivity_update_cart        "+"mobile"+ht_inst.getUser().getEmpMobile());
            System.out.println("ViewcartActivity_update_cart     "+"employee_token"+ht_inst.getUser().getEmployeeToken());
            System.out.println("ViewcartActivity_update_cart        "+"cart_id"+ht_inst.getCategory().getCart_id_emp());
            System.out.println("ViewcartActivity_update_cart      "+"pro_qnty"+ht_inst.getCategory().getNewquantity_emp());


            postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(ht_inst.getUser().getEmpMobile())));
            postRequestParams.add(new BasicNameValuePair("employee_token", String.valueOf(ht_inst.getUser().getEmployeeToken())));
            postRequestParams.add(new BasicNameValuePair("cart_id", String.valueOf(ht_inst.getCategory().getCart_id_emp())));
            postRequestParams.add(new BasicNameValuePair("pro_qnty", String.valueOf(ht_inst.getCategory().getNewquantity_emp())));

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

