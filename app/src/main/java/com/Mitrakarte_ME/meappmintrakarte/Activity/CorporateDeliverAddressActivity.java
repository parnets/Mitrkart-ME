package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Mitrakarte_ME.meappmintrakarte.Adapter.CoporateViewCartDeliveryAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Adapter.ViewCartAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.Model.Address;
import com.Mitrakarte_ME.meappmintrakarte.Model.Category;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.CorporateViewCartDelivery;
import com.Mitrakarte_ME.meappmintrakarte.Service.PlaceOrderCorporate;

import java.util.ArrayList;

public class CorporateDeliverAddressActivity extends AppCompatActivity {

    AG ht_inst = AG.getInstance();
    RecyclerView view_cart_place_cor;
    CoporateViewCartDeliveryAdapter coporateViewCartDeliveryAdapter;

    ViewCartAdapter viewProductAdapter;

    Button btnonfirm_order,change_address_cor;
    TextView orders_history_address,orders_history_name,orders_history_phone, orders_history_Locality_cor, orders_history_Landmark_cor;
    TextView orders_history_Pincode_cor, orders_history_City_cor, orders_history_State_cor, orders_history_Country_cor;
    TextView payable_price_Cor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corporate_deliver_address);

        view_cart_place_cor = findViewById(R.id.view_cart_place_cor);

        payable_price_Cor  = findViewById(R.id.payable_price_Cor);

        btnonfirm_order = findViewById(R.id.btnonfirm_order_cor);
        orders_history_name = findViewById(R.id.orders_history_name_cor);
        change_address_cor = findViewById(R.id.change_address_cor);
        orders_history_address = findViewById(R.id.orders_history_address_cor);
        orders_history_Locality_cor = findViewById(R.id.orders_history_Locality_cor);

        orders_history_Landmark_cor = findViewById(R.id.orders_history_Landmark_cor);
        orders_history_Pincode_cor = findViewById(R.id.orders_history_Pincode_cor);
        orders_history_City_cor = findViewById(R.id.orders_history_City_cor);
        orders_history_State_cor = findViewById(R.id.orders_history_State_cor);
        orders_history_Country_cor = findViewById(R.id.orders_history_Country_cor);
       // orders_history_phone = findViewById(R.id.orders_history_phone_cor);

        //Todo: Corporate Address for Placing Order
        Intent data = getIntent();
        int position = data.getIntExtra("addressFromPrevCor", 0);
        Address address = AG.getInstance().getKeyAddressList().get(position);
        Log.d("CDeliverAddressActivity", "addressFrom Prev Cor: " + address.getAdr_mobile_cor());
        // orders_history_phone.setText(address.getAdr_mobile_k());
        orders_history_name.setText(address.getAdr_name_cor());
        orders_history_address.setText(address.getAdr_address_cor());

        //orders_history_Email.setText(address.getAdr_email_k());
        orders_history_Locality_cor.setText(address.getAdr_locality_cor());
        orders_history_Landmark_cor.setText(address.getAdr_land_mark_cor());
        orders_history_Pincode_cor.setText(address.getAdr_pin_cor());
        orders_history_City_cor.setText(address.getAdr_city_cor());
        orders_history_State_cor.setText(address.getAdr_state_cor());
        orders_history_Country_cor.setText(address.getAdr_country_cor());

        //Todo: Displaying View Cart Key Account Products
        AsyncTask<Context, Void, Integer> result2 = new CorporateViewCartDelivery().execute(CorporateDeliverAddressActivity.this);
        try {

          //  Toast.makeText(CorporateDeliverAddressActivity.this, "Application Fetching data", Toast.LENGTH_SHORT).show();

            int code = result2.get();
            System.out.println("jyo code" + code);
        } catch (Exception e) {

            Toast.makeText(CorporateDeliverAddressActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

        }

        LinearLayoutManager llm = new LinearLayoutManager(this);

        coporateViewCartDeliveryAdapter = new CoporateViewCartDeliveryAdapter(CorporateDeliverAddressActivity.this, AG.getInstance().getCorporateViewCartlist(), new CoporateViewCartDeliveryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {




            }
        });

        view_cart_place_cor.setLayoutManager(llm);
        view_cart_place_cor.setAdapter(coporateViewCartDeliveryAdapter);
        view_cart_place_cor.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        btnonfirm_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Todo: Place Order  Corporate
                AsyncTask<Context, Void, Integer> result3 = new PlaceOrderCorporate().execute(CorporateDeliverAddressActivity.this);
                try {

                    Toast.makeText(CorporateDeliverAddressActivity.this, "Order Placed", Toast.LENGTH_SHORT).show();

                    int code = result3.get();
                    System.out.println("jyo code" + code);




                    //Todo: Clear cart product after Order placed
                    AG.getInstance().getCorporateViewCartlist().clear();
                    coporateViewCartDeliveryAdapter = new CoporateViewCartDeliveryAdapter(CorporateDeliverAddressActivity.this, AG.getInstance().getCorporateViewCartlist(), new CoporateViewCartDeliveryAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                        }
                    });

                    view_cart_place_cor.setAdapter(coporateViewCartDeliveryAdapter);
                    coporateViewCartDeliveryAdapter.notifyDataSetChanged();

                    //Todo: Clear cart product after Order placed from View Cart Activiy




                } catch (Exception e) {

                    Toast.makeText(CorporateDeliverAddressActivity.this, "Application Fail to Order Placing", Toast.LENGTH_SHORT).show();

                }



                Intent intent = new Intent(CorporateDeliverAddressActivity.this, CorporateActivityThankYou.class);
                startActivity(intent);
            }
        });

        change_address_cor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CorporateDeliverAddressActivity.this, CorporateAddAddressActivity.class);
                startActivity(intent);
            }
        });
    }


    //Todo: Displayng Total Price
    @Override
    protected void onResume() {


        super.onResume();
        payable_price_Cor.setText("₹"+getTotalPrice());
    }

    //Todo: Displayng Total Price
    private String getTotalPrice(){
        ArrayList<Category> category =AG.getInstance().getCorporateViewCartlist();
        double totalPriceCorporate = 0;
        for(Category cat : category){
            totalPriceCorporate += (Double.parseDouble(cat.getPrice_cor()) * Double.parseDouble(cat.getQuantity_cor()));
        }
        return String.valueOf(totalPriceCorporate);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(CorporateDeliverAddressActivity.this, CorporateAddAddressActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }


}