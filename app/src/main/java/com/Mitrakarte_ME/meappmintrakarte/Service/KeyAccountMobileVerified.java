package com.Mitrakarte_ME.meappmintrakarte.Service;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.Model.User;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.Mitrakarte_ME.meappmintrakarte.Util.Constants.url_base;

public class KeyAccountMobileVerified extends AsyncTask<String, Void, Integer> {

    Integer result = 201;

    @Override
    protected Integer doInBackground(String... params) {

        BufferedReader reader = null;
        try {
            // Create Apache HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base+"validateMobileRegKeyAcccount");

            AG ag_inst = AG.getInstance();

            User inParam = new User();

            //Todo: Changes
            System.out.println("sun mobile"+ag_inst.getUser().getkMobile());
            inParam.setMobile(ag_inst.getUser().getkMobile());

            Gson gson = new GsonBuilder().disableHtmlEscaping().create();

            StringEntity stringEntity = new StringEntity(gson.toJson(inParam));
            postRequest.setEntity(stringEntity);
            postRequest.setHeader("Content-Type", "application/json");

            HttpResponse httpResponse = httpclient.execute(postRequest);

            String line;
            reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            if((line = reader.readLine())!=null){
                System.out.println("sun mobile verified status "+line);
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

    private void parseResult(String response) {
        try {
            System.out.println("sun mobverfied calling parse ");
            JSONObject post = new JSONObject(response);
            result = post.getInt("errorCode");

        } catch (Exception e) {
            System.out.println("sun failed  parse");
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {

        if (result == 1) {


        } else {

        }
    }
}
