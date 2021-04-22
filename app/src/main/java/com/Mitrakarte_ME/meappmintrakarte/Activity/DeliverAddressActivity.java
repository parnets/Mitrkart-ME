package com.Mitrakarte_ME.meappmintrakarte.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Mitrakarte_ME.meappmintrakarte.Adapter.ViewCartDeliveryAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.Model.Address;
import com.Mitrakarte_ME.meappmintrakarte.Model.Category;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.PlaceOrder;
import com.Mitrakarte_ME.meappmintrakarte.Service.ViewCartDelivery;

import java.util.ArrayList;

public class DeliverAddressActivity extends AppCompatActivity {

    AG ht_inst = AG.getInstance();
    RecyclerView view_cart2, choosen_delivery_address;
    ViewCartDeliveryAdapter viewCartDeliveryAdapter;
    TextView price;

    //PlaceOrderAdapter placeOrderAdapter;

    Button btnonfirm_order,change_address;
    TextView orders_history_address,orders_history_name,orders_history_phone, orders_history_Email, orders_history_Locality;
    TextView  orders_history_Pincode, orders_history_State, orders_history_Country;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_delivery);

        view_cart2 = findViewById(R.id.view_cart2);

        btnonfirm_order = findViewById(R.id.btnonfirm_order);
        orders_history_name = findViewById(R.id.orders_history_name);
        change_address = findViewById(R.id.change_address);
        orders_history_address = findViewById(R.id.orders_history_address);
        // orders_history_phone = findViewById(R.id.orders_history_phone);

        //  orders_history_Email = findViewById(R.id.orders_history_Email);
        orders_history_Locality = findViewById(R.id.orders_history_Locality);
        orders_history_Pincode = findViewById(R.id.orders_history_Pincode);
        orders_history_State = findViewById(R.id.orders_history_State);
        orders_history_Country = findViewById(R.id.orders_history_Country);
      // orders_history_phone = findViewById(R.id.orders_history_phone);

        price = findViewById(R.id.price);

        //Todo: User Address for Placing Order
//       orders_history_address.setText(""+ ht_inst.getInstance().getUser().getkAddress());
//       orders_history_name.setText(""+ ht_inst.getInstance().getUser().getkOwnerName());
//       orders_history_phone.setText(""+ ht_inst.getInstance().getUser().getkMobile());
        Intent data = getIntent();
        int position = data.getIntExtra("addressFromPrev", 0);
        Address address = AG.getInstance().getKeyAddressList().get(position);
        Log.d("DeliverAddressActivity", "addressFrom Prev: " + address.getAdr_mobile_k());
        //orders_history_phone.setText(address.getAdr_mobile_k());
        orders_history_name.setText(address.getAdr_name_k());
        orders_history_address.setText(address.getAdr_address_k());
        orders_history_Locality.setText(address.getAdr_locality_k());
        orders_history_Pincode.setText(address.getAdr_pin_k());
        orders_history_State.setText(address.getAdr_state_k());
        orders_history_Country.setText(address.getAdr_country_k());


        //Todo: Displaying View Cart Key Account Products
        AsyncTask<Context, Void, Integer> result2 = new ViewCartDelivery().execute(DeliverAddressActivity.this);
        try {

         //   Toast.makeText(DeliverAddressActivity.this, "Application Fetching data", Toast.LENGTH_SHORT).show();

            int code = result2.get();
            System.out.println("jyo code" + code);
        } catch (Exception e) {

            Toast.makeText(DeliverAddressActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

        }

        LinearLayoutManager llm = new LinearLayoutManager(this);

        viewCartDeliveryAdapter = new ViewCartDeliveryAdapter(DeliverAddressActivity.this, AG.getInstance().getViewcartlist(), new ViewCartDeliveryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
               /* Intent intent = new Intent(ViewcartActivity.this, ViewcartActivity.class);
                intent.putExtra("pid", AG.getInstance().getCategory().getPro_id());
                intent.putExtra("subid",AG.getInstance().getCategory().getSub_catId());
                intent.putExtra("Position", position);
                startActivity(intent);
*/




            }
        });

        view_cart2.setLayoutManager(llm);
        view_cart2.setAdapter(viewCartDeliveryAdapter);
        view_cart2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));




//        LinearLayoutManager llm3 = new LinearLayoutManager(this);
//
//        viewCartDeliveryAdapter = new ViewCartDeliveryAdapter(DeliverAddressActivity.this, AG.getInstance().getViewcartlist(), new ViewCartDeliveryAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//               /* Intent intent = new Intent(ViewcartActivity.this, ViewcartActivity.class);
//                intent.putExtra("pid", AG.getInstance().getCategory().getPro_id());
//                intent.putExtra("subid",AG.getInstance().getCategory().getSub_catId());
//                intent.putExtra("Position", position);
//                startActivity(intent);
//*/
//
//
//
//
//            }
//        });
//
//        view_cart2.setLayoutManager(llm);
//        view_cart2.setAdapter(viewCartDeliveryAdapter);
//        view_cart2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));



//        fetchProductAdapter = new FetchProductAdapter(KeyAccountHomeActivity.this, AG.getInstance().getMobileProductlist());
//
//        product_all.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        product_all.setAdapter(fetchProductAdapter);
//        fetchProductAdapter.notifyDataSetChanged();

        btnonfirm_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Todo: Place Order  Key Account
                AsyncTask<Context, Void, Integer> result3 = new PlaceOrder().execute(DeliverAddressActivity.this);
                try {

                    Toast.makeText(DeliverAddressActivity.this, "Order Placed", Toast.LENGTH_SHORT).show();

                    int code = result3.get();
                    System.out.println("jyo code" + code);


                    //Todo: Clear cart product after Order placed
                    AG.getInstance().getViewcartlist().clear();

                    viewCartDeliveryAdapter = new ViewCartDeliveryAdapter(DeliverAddressActivity.this, AG.getInstance().getViewcartlist(), new ViewCartDeliveryAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                        }
                    });

                    view_cart2.setAdapter(viewCartDeliveryAdapter);
                    viewCartDeliveryAdapter.notifyDataSetChanged();



                } catch (Exception e) {

                    Toast.makeText(DeliverAddressActivity.this, "Application Fail to Order Placing", Toast.LENGTH_SHORT).show();

                }

                Intent intent = new Intent(DeliverAddressActivity.this, ActivityThankYou.class);
                startActivity(intent);
            }
        });

        change_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //Todo: Displayng Total Price
    @Override
    protected void onResume() {
        super.onResume();
        price.setText(getTotalPrice());
    }

    //Todo: Displayng Total Price
    private String getTotalPrice(){
        ArrayList<Category> category =AG.getInstance().getViewcartlist();
        double totalPrice = 0;
        for(Category cat : category){
            totalPrice += (Double.parseDouble(cat.getPrice()) * Double.parseDouble(cat.getQuantity()));
        }
        return String.valueOf("₹"+totalPrice);
    }

    //Todo: Getting Delivery Address From the AddAddressAtivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 21 && resultCode == 21){



        }
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(DeliverAddressActivity.this, AddAddressActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }

}

