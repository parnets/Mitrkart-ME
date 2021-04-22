package com.Mitrakarte_ME.meappmintrakarte.Service;

import android.os.AsyncTask;

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

public class Forgotpassword extends AsyncTask<String, Void, Integer> {

    Integer result = 201;
    AG ag_inst = AG.getInstance();
    @Override
    protected Integer doInBackground(String... params) {

        BufferedReader reader = null;
        try {

            HttpClient httpclient = new DefaultHttpClient();
          //HttpPost postRequest = new HttpPost("http://parnetslink.com/mitrakarte_all/mitrakarte_api/api/ME_Cont_Api/forgotpwd");

            HttpPost postRequest = new HttpPost("http://mitrakarte.com/api/api/ME_Cont_Api/forgotpwd");

            List<NameValuePair> postRequestParams = new ArrayList<>();


            postRequestParams.add(new BasicNameValuePair("mail_id", ""+ag_inst.getUser().getMailID()));
            postRequestParams.add(new BasicNameValuePair("mobile", ""+ag_inst.getUser().getMobile()));
            System.out.println("Forgotpassword      "+"email "+ag_inst.getUser().getMailID());
            System.out.println("Forgotpassword      "+"mobile "+ag_inst.getUser().getMobile());

            //postRequestParams.add(new BasicNameValuePair("email", params[1]));

            postRequest.setEntity(new UrlEncodedFormEntity(postRequestParams));

            HttpResponse httpResponse = httpclient.execute(postRequest);

            String line;

            reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            if((line = reader.readLine())!=null){
                System.out.println("sun get login response "+line);
            }

            parseResult(line);

        } catch (Exception e) {
            System.out.println("sun"+e.getCause());
            e.printStackTrace();
            System.out.println("sun exception occurred ");
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