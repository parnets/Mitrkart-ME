package com.Mitrakarte_ME.meappmintrakarte.Service;

import android.content.Context;
import android.os.AsyncTask;

import com.Mitrakarte_ME.meappmintrakarte.Activity.EmployeeAddAddressActivity;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.Model.Address;

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

public class AddressEmployee extends AsyncTask<Context, Void, Integer> {

    EmployeeAddAddressActivity mContext;
    Integer result = 200;
    AG ht_inst = AG.getInstance();

    @Override
    protected Integer doInBackground(Context... params) {

        BufferedReader reader = null;

        try {
            // Create Apache HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base+"userAddressEmployee");

            List<NameValuePair> postRequestParams = new ArrayList<>();
            mContext = (EmployeeAddAddressActivity) params[0];

            System.out.println("AddAddressActivity  " +"mobile"+ht_inst.getUser().getEmpMobile());
            System.out.println("AddAddressActivity  "+"employee_token"+ht_inst.getUser().getEmployeeToken());
            System.out.println("AddAddressActivity  "+"employee_id"+ht_inst.getUser().getEmployeeId());
            postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(ht_inst.getUser().getEmpMobile())));
            postRequestParams.add(new BasicNameValuePair("employee_token", String.valueOf(ht_inst.getUser().getEmployeeToken())));
            postRequestParams.add(new BasicNameValuePair("employee_id", String.valueOf(ht_inst.getUser().getEmployeeId())));

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

            ht_inst.getEmployeeAddressList().clear();


            System.out.println("jyo 1 "+post.length());
            for (int i = 0; i < post1.length(); i++) {
                JSONObject item = post1.getJSONObject(i);

                Address udata = new Address();
                udata.setAdr_emp_id(item.getInt("ea_id"));
                // catItem.setu(item.getString("key_account_id"));
                udata.setAdr_name_emp(item.getString("name"));
                udata.setAdr_employee_id_emp(item.getInt("employee_id"));
                udata.setAdr_address_emp(item.getString("address"));
                udata.setAdr_locality_emp(item.getString("locality"));
                udata.setAdr_land_mark_emp(item.getString("land_mark"));
                udata.setAdr_country_emp(item.getString("country"));
                udata.setAdr_state_emp(item.getString("state"));
                udata.setAdr_city_emp(item.getString("city"));
                udata.setAdr_pin_emp(item.getString("pin"));

                udata.setAdr_mobile_emp(item.getString("mobile"));
                udata.setAdr_email_emp(item.getString("email"));
                udata.setAdr_updated_on_emp(item.getString("updated_on"));
                udata.setAdr_created_on_emp(item.getString("created_on"));


                ht_inst.getEmployeeAddressList().add(udata);

            }

            System.out.println("jyo number of category fetched is "+ht_inst.getEmployeeAddressList().size());

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
