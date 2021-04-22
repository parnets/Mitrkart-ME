package com.Mitrakarte_ME.meappmintrakarte.Service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.Mitrakarte_ME.meappmintrakarte.Activity.EmployeeDeliverAddressActivity;
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

public class EmployeePlaceOrder extends AsyncTask<Context, Void, Integer> {

    EmployeeDeliverAddressActivity mContext;
    Integer result = 200;
    AG ht_inst = AG.getInstance();

    @Override
    protected Integer doInBackground(Context... params) {
        //  'status'=> 'cod',
        BufferedReader reader = null;

        try {
            // Create Apache HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base+"orderHistoryEmployee");

            List<NameValuePair> postRequestParams = new ArrayList<>();
            mContext = (EmployeeDeliverAddressActivity) params[0];

            //System.out.println(" Category sending paramter"+"mobile"+ht_inst.getUser().getkMobile()+"keyaccount_token"+ht_inst.getUser().getKeyAccountToken());

            postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(ht_inst.getUser().getEmpMobile())));
            postRequestParams.add(new BasicNameValuePair("employee_token", String.valueOf(ht_inst.getUser().getEmployeeToken())));
            postRequestParams.add(new BasicNameValuePair("employee_id", String.valueOf(ht_inst.getUser().getEmployeeId())));


            System.out.println("PlaceOrderActivity"+"mobile "+ht_inst.getUser().getEmpMobile());
            System.out.println("PlaceOrderActivity"+"employee_token "+ht_inst.getUser().getEmployeeToken());
            System.out.println("PlaceOrderActivity"+"employee_id  "+ht_inst.getUser().getEmployeeId());

            postRequest.setEntity(new UrlEncodedFormEntity(postRequestParams));
            Log.d("PlaceOrderActivity", "The Sending Parametrers " + postRequestParams );


            HttpResponse httpResponse = httpclient.execute(postRequest);

            String line;
            reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));



            if((line = reader.readLine())!=null){
                System.out.println(" get fetch my categ response "+line);
            }

            Log.d("PlaceOrderActivity", "The Sending Parametrers 2 " + line );

            // Log.d("PlaceOrderActivity", "The Sending Parametrers 3 " +  parseResult(line) );

            System.out.println(" get fetch my categ response "+line);
            parseResult(line);

        } catch (Exception e) {
            System.out.println(""+e.getCause());
            e.printStackTrace();
            System.out.println(" exception occurred ");
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


        } catch (Exception e) {
            System.out.println(" failed order placing");
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


