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

public class RetailerSurveyRegister extends AsyncTask<String, Void, Integer> {

    AG om_inst = AG.getInstance();
    Integer result = 200;

    @Override
    protected Integer doInBackground(String... strings) {

        BufferedReader reader = null;

        try {
            // Create Apache HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base + "profileInsertRetailerSurvey");

            List<NameValuePair> postRequestParams = new ArrayList<>();


            System.out.println("jyo reg sending paramter" + "mobile" + om_inst.getUser().getUserId() + "name" + om_inst.getUser().getUserToken());



            //Todo: Retailer Registers field added
//            postRequestParams.add(new BasicNameValuePair("me_id", String.valueOf(om_inst.getUser().getUserId())));
//            postRequestParams.add(new BasicNameValuePair("me_token", String.valueOf(om_inst.getUser().getUserToken())));
//            postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(om_inst.getUser().getMobile())));

//            postRequestParams.add(new BasicNameValuePair("email", String.valueOf(om_inst.getUser().getMailID())));
            postRequestParams.add(new BasicNameValuePair("name", String.valueOf(om_inst.getUser().getRsName())));
            postRequestParams.add(new BasicNameValuePair("per_contactNo", String.valueOf(om_inst.getUser().getRsPermanentNumber())));
            postRequestParams.add(new BasicNameValuePair("type_of_firm", String.valueOf(om_inst.getUser().getRsTypeofFirm())));
            postRequestParams.add(new BasicNameValuePair("village_papulation", String.valueOf(om_inst.getUser().getRsVillagePApulation())));
            postRequestParams.add(new BasicNameValuePair("owner_name", String.valueOf(om_inst.getUser().getRsOwnerName())));
            postRequestParams.add(new BasicNameValuePair("email_id", String.valueOf(om_inst.getUser().getRsEmail())));
            postRequestParams.add(new BasicNameValuePair("dob", String.valueOf(om_inst.getUser().getRsDob())));
            postRequestParams.add(new BasicNameValuePair("anniversary_date", String.valueOf(om_inst.getUser().getRsAnniversaryDate())));
            postRequestParams.add(new BasicNameValuePair("gst_no", String.valueOf(om_inst.getUser().getRsGstNo())));
            postRequestParams.add(new BasicNameValuePair("pan_no", String.valueOf(om_inst.getUser().getRsPanNo())));
            postRequestParams.add(new BasicNameValuePair("address", String.valueOf(om_inst.getUser().getRsAddress())));
            postRequestParams.add(new BasicNameValuePair("landmark", String.valueOf(om_inst.getUser().getRsLandMark())));
            postRequestParams.add(new BasicNameValuePair("alternate_no", String.valueOf(om_inst.getUser().getRsAlternateNo())));
            postRequestParams.add(new BasicNameValuePair("whatsapp_no", String.valueOf(om_inst.getUser().getRsWhatsappNumber())));
            postRequestParams.add(new BasicNameValuePair("experience", String.valueOf(om_inst.getUser().getRsExperience())));

            postRequestParams.add(new BasicNameValuePair("type_of_retailer", String.valueOf(om_inst.getUser().getRsTypeOfRetailer())));
            postRequestParams.add(new BasicNameValuePair("social_media", String.valueOf(om_inst.getUser().getRsSocialMedia())));


            postRequestParams.add(new BasicNameValuePair("buy_wholeseller_stock", String.valueOf(om_inst.getUser().getRsBuyWholesellerStock())));
            postRequestParams.add(new BasicNameValuePair("place_name", String.valueOf(om_inst.getUser().getRsPlaceName())));
            postRequestParams.add(new BasicNameValuePair("km_to_travel", String.valueOf(om_inst.getUser().getRsKmToTravel())));
            postRequestParams.add(new BasicNameValuePair("how_often", String.valueOf(om_inst.getUser().getRsHowOften())));

            postRequestParams.add(new BasicNameValuePair("service", String.valueOf(om_inst.getUser().getRsservice())));
            postRequestParams.add(new BasicNameValuePair("service_buying_frequency", String.valueOf(om_inst.getUser().getRsServiceBuyingFrequency())));


            postRequestParams.add(new BasicNameValuePair("average_customer_day", String.valueOf(om_inst.getUser().getRsAverageCustomerDay())));
            postRequestParams.add(new BasicNameValuePair("average_sales_week", String.valueOf(om_inst.getUser().getRsAverageSalesWeek())));
            postRequestParams.add(new BasicNameValuePair("average_sales_month", String.valueOf(om_inst.getUser().getRsAverageSalesMonth())));


            postRequestParams.add(new BasicNameValuePair("finance", String.valueOf(om_inst.getUser().getRsFinance())));
            postRequestParams.add(new BasicNameValuePair("finance_amount", String.valueOf(om_inst.getUser().getRsFinanceAmount())));
            postRequestParams.add(new BasicNameValuePair("shop_insurance_available", String.valueOf(om_inst.getUser().getRsShopInsuranceAvailable())));
            postRequestParams.add(new BasicNameValuePair("shop_insurance_required", String.valueOf(om_inst.getUser().getRsShopInsuranceRequired())));

            postRequestParams.add(new BasicNameValuePair("medical_insurance_available", String.valueOf(om_inst.getUser().getRsMedicalInsuranceAvailable())));
            postRequestParams.add(new BasicNameValuePair("medical_insurance_required", String.valueOf(om_inst.getUser().getRsMedicalInsuranceRequired())));
            postRequestParams.add(new BasicNameValuePair("life_insurance_available", String.valueOf(om_inst.getUser().getRsLifeInsuranceAvailable())));
            postRequestParams.add(new BasicNameValuePair("life_insurance_required", String.valueOf(om_inst.getUser().getRsLifeInsuranceRequired())));
            postRequestParams.add(new BasicNameValuePair("term_insurance_available", String.valueOf(om_inst.getUser().getRsTermInsuranceAvailable())));
            postRequestParams.add(new BasicNameValuePair("term_insurance_required", String.valueOf(om_inst.getUser().getRsTermInsuranceRequired())));

            postRequestParams.add(new BasicNameValuePair("hul_avail", String.valueOf(om_inst.getUser().getRsHulAvailabilty())));
            postRequestParams.add(new BasicNameValuePair("hul_freq", String.valueOf(om_inst.getUser().getRsHulFrequency())));
            postRequestParams.add(new BasicNameValuePair("tata_avail", String.valueOf(om_inst.getUser().getRsTata_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("tata_freq", String.valueOf(om_inst.getUser().getRsTata_Frequency())));

            postRequestParams.add(new BasicNameValuePair("itc_avail", String.valueOf(om_inst.getUser().getRsItc_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("itc_freq", String.valueOf(om_inst.getUser().getRsItc_Frequency())));
            postRequestParams.add(new BasicNameValuePair("nestle_avail", String.valueOf(om_inst.getUser().getRsNestle_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("nestle_freq", String.valueOf(om_inst.getUser().getRsNestle_Frequency())));
            postRequestParams.add(new BasicNameValuePair("cadburys_avail", String.valueOf(om_inst.getUser().getRsCadburys_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("cadburys_freq", String.valueOf(om_inst.getUser().getRsCadburys_Frequency())));
            postRequestParams.add(new BasicNameValuePair("Kaleeshwari_avail", String.valueOf(om_inst.getUser().getRsKaleeshwari_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("Kaleeshwari_freq", String.valueOf(om_inst.getUser().getRsKaleeshwari_Frequency())));

            //
            postRequestParams.add(new BasicNameValuePair("dabur_avail", String.valueOf(om_inst.getUser().getRsDaburAvailabilty())));
            postRequestParams.add(new BasicNameValuePair("dabur_freq", String.valueOf(om_inst.getUser().getRsDaburFrequency())));
            postRequestParams.add(new BasicNameValuePair("britannia_avail", String.valueOf(om_inst.getUser().getRsBritannia_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("britannia_freq", String.valueOf(om_inst.getUser().getRsBritannia_Frequency())));
            postRequestParams.add(new BasicNameValuePair("kwality_avail", String.valueOf(om_inst.getUser().getRsKwality_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("kwality_freq", String.valueOf(om_inst.getUser().getRsKwality_Frequency())));

            postRequestParams.add(new BasicNameValuePair("hatsun_avail", String.valueOf(om_inst.getUser().getRsHatsunAvailabilty())));
            postRequestParams.add(new BasicNameValuePair("hatsun_freq", String.valueOf(om_inst.getUser().getRsHatsun_Frequency())));
            postRequestParams.add(new BasicNameValuePair("heritage_avail", String.valueOf(om_inst.getUser().getRsHeritage_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("heritage_freq", String.valueOf(om_inst.getUser().getRsHeritage_Frequency())));

            postRequestParams.add(new BasicNameValuePair("mtr_avail", String.valueOf(om_inst.getUser().getRsmtr_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("mtr_freq", String.valueOf(om_inst.getUser().getRsmtr_Frequency())));
            postRequestParams.add(new BasicNameValuePair("sakthi_avail", String.valueOf(om_inst.getUser().getRssakthi_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("sakthi_freq", String.valueOf(om_inst.getUser().getRssakthiFrequency())));
            postRequestParams.add(new BasicNameValuePair("amul_avail", String.valueOf(om_inst.getUser().getRsamul__Availabilty())));
            postRequestParams.add(new BasicNameValuePair("amul_freq", String.valueOf(om_inst.getUser().getRsamul__Frequency())));
            postRequestParams.add(new BasicNameValuePair("everest_avail", String.valueOf(om_inst.getUser().getRseverest_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("everest_freq", String.valueOf(om_inst.getUser().getRseverest_Frequency())));

            postRequestParams.add(new BasicNameValuePair("haldirams_avail", String.valueOf(om_inst.getUser().getRshaldirams_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("haldirams_freq", String.valueOf(om_inst.getUser().getRshaldirams_Frequency())));

            postRequestParams.add(new BasicNameValuePair("colgate_avail", String.valueOf(om_inst.getUser().getRscolgate_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("colgate_freq", String.valueOf(om_inst.getUser().getRscolgate_Frequency())));
            postRequestParams.add(new BasicNameValuePair("motherdi_avail", String.valueOf(om_inst.getUser().getRsmotherdi_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("motherdi_freq", String.valueOf(om_inst.getUser().getRsmotherdi_Frequency())));

            postRequestParams.add(new BasicNameValuePair("parle_avail", String.valueOf(om_inst.getUser().getRsparle_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("parle_freq", String.valueOf(om_inst.getUser().getRsparle_Frequency())));
            postRequestParams.add(new BasicNameValuePair("biskfarm_avail", String.valueOf(om_inst.getUser().getRsbiskfarm_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("biskfarm_freq", String.valueOf(om_inst.getUser().getRsbiskfarm_Frequency())));
            postRequestParams.add(new BasicNameValuePair("reiagro_avail", String.valueOf(om_inst.getUser().getRsreiagro_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("reiagro_freq", String.valueOf(om_inst.getUser().getRsreiagro_Frequency())));
            postRequestParams.add(new BasicNameValuePair("ruchi_avail", String.valueOf(om_inst.getUser().getRsruchi_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("ruchi_freq", String.valueOf(om_inst.getUser().getRsruchi_Frequency())));
            postRequestParams.add(new BasicNameValuePair("idhayam_avail", String.valueOf(om_inst.getUser().getRsidhayam__Availabilty())));
            postRequestParams.add(new BasicNameValuePair("idhayam_freq", String.valueOf(om_inst.getUser().getRsidhayam_Frequency())));
            postRequestParams.add(new BasicNameValuePair("jubliant_avail", String.valueOf(om_inst.getUser().getRsjubliant_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("jubliant_freq", String.valueOf(om_inst.getUser().getRsjubliant_Frequency())));

            postRequestParams.add(new BasicNameValuePair("coke_avail", String.valueOf(om_inst.getUser().getRscoke_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("coke_freq", String.valueOf(om_inst.getUser().getRscoke_Frequency())));
            postRequestParams.add(new BasicNameValuePair("pepsi_avail", String.valueOf(om_inst.getUser().getRspepsi_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("pepsi_freq", String.valueOf(om_inst.getUser().getRspepsi_Frequency())));
            postRequestParams.add(new BasicNameValuePair("bisleri_avail", String.valueOf(om_inst.getUser().getRsbisleri_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("bisleri_freq", String.valueOf(om_inst.getUser().getRsbisleri_Frequency())));
            postRequestParams.add(new BasicNameValuePair("gsk_avail", String.valueOf(om_inst.getUser().getRsgsk_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("gsk_freq", String.valueOf(om_inst.getUser().getRsgsk_Frequency())));
            postRequestParams.add(new BasicNameValuePair("pathanjalli_avail", String.valueOf(om_inst.getUser().getRspathanjalli_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("pathanjalli_freq", String.valueOf(om_inst.getUser().getRspathanjalli_Frequency())));

            postRequestParams.add(new BasicNameValuePair("marico_avail", String.valueOf(om_inst.getUser().getRsmarico_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("marico_freq", String.valueOf(om_inst.getUser().getRsmaricoFrequency())));
            postRequestParams.add(new BasicNameValuePair("pg_avail", String.valueOf(om_inst.getUser().getRspg_Availabilty())));
            postRequestParams.add(new BasicNameValuePair("pg_freq", String.valueOf(om_inst.getUser().getRspg_Frequency())));

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
            //  om_inst.getUser().setUserId(post.getInt("id"));

//            om_inst.getUser().setComp_id(post.getString("company_id"));
//            System.out.println("jyo"+om_inst.getUser().getComp_id());
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
