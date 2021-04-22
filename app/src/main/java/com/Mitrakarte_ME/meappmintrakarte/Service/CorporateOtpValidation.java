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

import static com.Mitrakarte_ME.meappmintrakarte.Util.Constants.url_base;

public class CorporateOtpValidation extends AsyncTask<String, Void, Integer>{

    AG ag_inst = AG.getInstance();
    Integer result = 200;

    @Override
    protected Integer doInBackground(String... strings) {

        BufferedReader reader = null;
        try {
            // Create Apache HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base + "verifyOtpRegistrationCorporate");

            AG ag_inst = AG.getInstance();

            List<NameValuePair> postRequestParams = new ArrayList<>();

            //Todo: Changes
            System.out.println("sun sending mob"+ag_inst.getUser().getCrmobile()+"sun otp"+ag_inst.getUser().getOtp());
            postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(ag_inst.getUser().getCrmobile())));
            postRequestParams.add(new BasicNameValuePair("otp", String.valueOf(ag_inst.getUser().getOtp())));
            postRequest.setEntity(new UrlEncodedFormEntity(postRequestParams));

            HttpResponse httpResponse = httpclient.execute(postRequest);


            String line;
            reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            if ((line = reader.readLine()) != null) {
                System.out.println("sun get otp response " + line);
            }

            parseResult(line);

        } catch (Exception e) {
            System.out.println("sun" + e.getCause());
            e.printStackTrace();
            System.out.println("sun exception occurred ");
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

    private void parseResult(String response) {
        try {
            System.out.println("jyo otp verfied calling parse");
            JSONObject post = new JSONObject(response);
            result = post.getInt("errorCode");

            // todo: handling the response
            //Todo: Changes
            ag_inst.getUser().setCorporateId(post.getInt("corporate_id"));
            System.out.println("sun id"+ag_inst.getUser().getCorporateId());
            ag_inst.getUser().setCorporateToken(post.getString("corporate_token"));
            System.out.println("sun token"+ag_inst.getUser().getCorporateToken());


        } catch (Exception e) {
            System.out.println("sun failed login parse");
        }

    }

    @Override
    protected void onPostExecute(Integer integer) {

        if (result == 1) {

            //mGridAdapter.setGridData(mGridData);
        } else {
            // Toast.makeText(LocationViewActivity.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }
}
