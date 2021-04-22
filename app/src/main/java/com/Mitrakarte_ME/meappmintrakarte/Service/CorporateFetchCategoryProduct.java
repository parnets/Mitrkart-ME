package com.Mitrakarte_ME.meappmintrakarte.Service;

import android.content.Context;
import android.os.AsyncTask;

import com.Mitrakarte_ME.meappmintrakarte.Activity.CorporateProductsActivity;
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

public class CorporateFetchCategoryProduct extends AsyncTask<Context, Void, Integer> {

    CorporateProductsActivity mContext;
    Integer result3 = 200;
    AG ht_inst = AG.getInstance();

    @Override
    protected Integer doInBackground(Context... params) {

        BufferedReader reader = null;

        try {
            // Create Apache HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base+"fetchCategoryBasedProductCorporate");

            List<NameValuePair> postRequestParams = new ArrayList<>();
            mContext = (CorporateProductsActivity) params[0];

            System.out.println("jyo Category sending paramter"+"mobile"+ht_inst.getUser().getCrmobile()+"corporate_token"+ht_inst.getUser().getCorporateToken());
            postRequestParams.add(new BasicNameValuePair("mobile", String.valueOf(ht_inst.getUser().getCrmobile())));
            postRequestParams.add(new BasicNameValuePair("category", String.valueOf(ht_inst.getCategory().getCatId())));
            postRequestParams.add(new BasicNameValuePair("corporate_token", String.valueOf(ht_inst.getUser().getCorporateToken())));
            postRequest.setEntity(new UrlEncodedFormEntity(postRequestParams));

            HttpResponse httpResponse = httpclient.execute(postRequest);

            String line;
            reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));



            if((line = reader.readLine())!=null){
                System.out.println("jyo get fetch my categ_Product response "+line);
            }


            System.out.println("jyo get fetch my categ_Product response "+line);
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

        return result3;
    }


    /**
     * Parsing the feed results and get the list
     *
     * @param response
     */





    private void parseResult(String response) {
        try {
            JSONObject post = new JSONObject(response);
            result3 = post.getInt("errorCode");

            JSONArray post1 = post.getJSONArray("image");

            ht_inst.getFetchcatproductlist().clear();

            System.out.println("jyo 1 "+post.length());
            for (int i = 0; i < post1.length(); i++) {
                JSONObject item = post1.getJSONObject(i);

                Category catItem = new Category();

                catItem.setPrice(item.getString("productprice_id"));
                catItem.setPro_id(item.getInt("product_id"));
                catItem.setVendorId(item.getString("vendor_id"));
                catItem.setProductcode(item.getString("productcode"));
                catItem.setQuantity(item.getString("quantity"));
                catItem.setMrp(item.getString("mrp"));
                catItem.setOverall_margin_percentage(item.getString("aziure_overall_margin_percentage"));
                catItem.setOverall_margin_value(item.getString("aziure_overall_margin_value"));
                catItem.setAziure_cost_price(item.getString("aziure_cost_price"));
                catItem.setAziure_margin_percentage(item.getString("aziure_margin_percentage"));
                catItem.setAziure_margin_value(item.getString("aziure_margin_value"));
                catItem.setDistributor_invoice_price(item.getString("aziure_distributor_invoice_price"));
                catItem.setDistributor_cost(item.getString("distributor_cost"));
                catItem.setDistributor_margin_percentage(item.getString("distributor_margin_percentage"));
                catItem.setDistributor_margin_value(item.getString("distributor_margin_value"));
                catItem.setDistributor_retailer_invoice_price(item.getString("distributor_retailer_invoice_price"));
                catItem.setRetailer_cost(item.getString("retailer_cost"));
                catItem.setRetailer_margin_percentage(item.getString("retailer_margin_percentage"));
                catItem.setRetailer_margin_value(item.getString("retailer_margin_value"));
                catItem.setRetailer_customer_invoice_price(item.getString("retailer_customer_invoice_price"));
                catItem.setRetailer_profit(item.getString("retailer_profit"));
                catItem.setCustomer_price(item.getString("customer_price"));
                catItem.setCustomer_discount_percentage(item.getString("customer_discount_percentage"));
                catItem.setCustomer_discount_value(item.getString("customer_discount_value"));
                catItem.setCustomer_discount_price(item.getString("customer_discount_price"));
                catItem.setImage(item.getString("image"));
                catItem.setPro_stock(item.getString("p_stock"));
                catItem.setP_desc(item.getString("p_desc"));
                catItem.setDistributor_pack(item.getString("distributor_pack"));
                catItem.setGST(item.getString("gst"));

                catItem.setHsn(item.getString("hsn"));
                catItem.setSku(item.getString("sku"));
                catItem.setFree(item.getString("free"));

                catItem.setCatId(item.getInt("category"));
                catItem.setSub_catId(item.getInt("subcategory_name"));
                catItem.setPro_name(item.getString("productname"));
                catItem.setNewbrand(item.getString("brand"));
                catItem.setCompanyname(item.getString("companyname"));




                ht_inst.getFetchcatproductlist().add(catItem);

            }

            System.out.println("jyo number of categ_Product fetched is "+ht_inst.getFetchcatproductlist().size());

        }
        catch (Exception e) {
            System.out.println("jyo failed  parse fetch categ_Product");

            //Todo:If the Products is empty in that Subcategory Clear the old Subcategory Based Product
            ht_inst.getFetchcatproductlist().clear();
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