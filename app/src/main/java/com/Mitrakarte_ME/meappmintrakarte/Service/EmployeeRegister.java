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

public class EmployeeRegister extends AsyncTask<String, Void, Integer> {



    AG om_inst = AG.getInstance();
    Integer result = 200;

    @Override
    protected Integer doInBackground(String... strings) {

        BufferedReader reader = null;

        try {
            // Create Apache HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base + "profileUpdateEmployee");

            List<NameValuePair> postRequestParams = new ArrayList<>();


            System.out.println("jyo reg sending paramter" + "mobile" + om_inst.getUser().getEmployeeId() + "name" + om_inst.getUser().getEmployeeToken());



            //Todo: Corporate Registers field added
            postRequestParams.add(new BasicNameValuePair("employee_id", String.valueOf(om_inst.getUser().getEmployeeId())));
            postRequestParams.add(new BasicNameValuePair("employee_token", String.valueOf(om_inst.getUser().getEmployeeToken())));
            // postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(om_inst.getUser().getCrmobile())));

//            postRequestParams.add(new BasicNameValuePair("email", String.valueOf(om_inst.getUser().getMailID())));
            // postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(om_inst.getUser().getCrmobile())));

           postRequestParams.add(new BasicNameValuePair("employee_name", String.valueOf(om_inst.getUser().getEmpNAme())));
            postRequestParams.add(new BasicNameValuePair("employee_code", String.valueOf(om_inst.getUser().getEmpCode())));
            postRequestParams.add(new BasicNameValuePair("residential_address", String.valueOf(om_inst.getUser().getEmpResidential_address())));

            postRequestParams.add(new BasicNameValuePair("office_address", String.valueOf(om_inst.getUser().getEmpOfficialAddress())));
            postRequestParams.add(new BasicNameValuePair("aadhaar_no", String.valueOf(om_inst.getUser().getEmpAadhaarNumber())));
            postRequestParams.add(new BasicNameValuePair("pan_no", String.valueOf(om_inst.getUser().getEmppPanNumber())));
            postRequestParams.add(new BasicNameValuePair("employee_email", String.valueOf(om_inst.getUser().getEmppEmployee_email())));
            postRequestParams.add(new BasicNameValuePair("personal_email", String.valueOf(om_inst.getUser().getEmpePersonalMail())));
            postRequestParams.add(new BasicNameValuePair("dob", String.valueOf(om_inst.getUser().getEmpDob())));
            postRequestParams.add(new BasicNameValuePair("password", String.valueOf(om_inst.getUser().getEmpPassword())));
//            postRequestParams.add(new BasicNameValuePair("gst_no", String.valueOf(om_inst.getUser().getCrGSTNumber())));
//            postRequestParams.add(new BasicNameValuePair("pan_no", String.valueOf(om_inst.getUser().getCrPanNumber())));
//            postRequestParams.add(new BasicNameValuePair("bank_name", String.valueOf(om_inst.getUser().getCrBankName())));



            postRequest.setEntity(new UrlEncodedFormEntity(postRequestParams));

            HttpResponse httpResponse = httpclient.execute(postRequest);
            postRequest.setEntity(new UrlEncodedFormEntity(postRequestParams));


            String line;
            reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            if ((line = reader.readLine()) != null) {
                System.out.println("jyo get register response " + line);
            }

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
    private void parseResult(String response) {
        try {
            System.out.println("jyo verfied calling parse");
            JSONObject post = new JSONObject(response);
            result = post.getInt("errorCode");

        } catch (Exception e) {
            System.out.println("jyo failed reg parse");
        }

    }

    @Override
    protected void onPostExecute(Integer integer) {
        // Download complete. Lets update UI

        if (result == 1) {

            //mGridAdapter.setGridData(mGridData);
        } else {
            // Toast.makeText(LocationViewActivity.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }
}

