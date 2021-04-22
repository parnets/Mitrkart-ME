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

import com.Mitrakarte_ME.meappmintrakarte.Adapter.EmployeeViewCartDeliveryAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.Model.Address;
import com.Mitrakarte_ME.meappmintrakarte.Model.Category;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.EmployeePlaceOrder;
import com.Mitrakarte_ME.meappmintrakarte.Service.EmployeeViewCartDelivery;

import java.util.ArrayList;

public class EmployeeDeliverAddressActivity extends AppCompatActivity {

    AG ht_inst = AG.getInstance();
    RecyclerView recy_view_cart_place_Emp;
    EmployeeViewCartDeliveryAdapter employeeViewCartDeliveryAdapter;

    Button btnonfirm_order_Emp,change_address_Emp;
    TextView orders_history_address,orders_history_name,orders_history_phone, orders_history_Locality_emp, orders_history_Landmark_emp;
    TextView orders_history_Pincode_emp, orders_history_City_emp, orders_history_State_emp, orders_history_Country_emp;
    TextView payable_price_Emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_deliver_address);

        recy_view_cart_place_Emp = findViewById(R.id.recy_view_cart_place_Emp);

        btnonfirm_order_Emp = findViewById(R.id.btnonfirm_order_Emp);
        change_address_Emp = findViewById(R.id.change_address_Emp);
        payable_price_Emp  = findViewById(R.id.payable_price_Emp);

        orders_history_name = findViewById(R.id.orders_history_name_emp);
        orders_history_address = findViewById(R.id.orders_history_address_emp);
        orders_history_Locality_emp = findViewById(R.id.orders_history_Locality_emp);

        orders_history_Landmark_emp = findViewById(R.id.orders_history_Landmark_emp);
        orders_history_Pincode_emp = findViewById(R.id.orders_history_Pincode_emp);
        orders_history_City_emp = findViewById(R.id.orders_history_City_emp);
        orders_history_State_emp = findViewById(R.id.orders_history_State_emp);
        orders_history_Country_emp = findViewById(R.id.orders_history_Country_emp);
    
        //Todo: Employee Address for Placing Order
        Intent data = getIntent();
        int position = data.getIntExtra("addressFromPrevEmp", 0);
        Address address = AG.getInstance().getEmployeeAddressList().get(position);
        Log.d("CDeliverAddressActivity", "addressFrom Prev Emp: " + address.getAdr_mobile_emp());
        // orders_history_phone.setText(address.getAdr_mobile_k());
        orders_history_name.setText(address.getAdr_name_emp());
        orders_history_address.setText(address.getAdr_address_emp());

        //orders_history_Email.setText(address.getAdr_email_k());
        orders_history_Locality_emp.setText(address.getAdr_locality_emp());
        orders_history_Landmark_emp.setText(address.getAdr_land_mark_emp());
        orders_history_Pincode_emp.setText(address.getAdr_pin_emp());
        orders_history_City_emp.setText(address.getAdr_city_emp());
        orders_history_State_emp.setText(address.getAdr_state_emp());
        orders_history_Country_emp.setText(address.getAdr_country_emp());


        //Todo: Displaying View Cart Key Account Products
        AsyncTask<Context, Void, Integer> result2 = new EmployeeViewCartDelivery().execute(EmployeeDeliverAddressActivity.this);
        try {

         //   Toast.makeText(EmployeeDeliverAddressActivity.this, "Application Fetching data", Toast.LENGTH_SHORT).show();

            int code = result2.get();
            System.out.println("jyo code" + code);
        } catch (Exception e) {

            Toast.makeText(EmployeeDeliverAddressActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

        }

        LinearLayoutManager llm = new LinearLayoutManager(this);

        employeeViewCartDeliveryAdapter = new EmployeeViewCartDeliveryAdapter(EmployeeDeliverAddressActivity.this, AG.getInstance().getEmployeeViewCartlist(), new EmployeeViewCartDeliveryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                


            }
        });

        recy_view_cart_place_Emp.setLayoutManager(llm);
        recy_view_cart_place_Emp.setAdapter(employeeViewCartDeliveryAdapter);
        recy_view_cart_place_Emp.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        btnonfirm_order_Emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Todo: Place Order  Key Account
                AsyncTask<Context, Void, Integer> result3 = new EmployeePlaceOrder().execute(EmployeeDeliverAddressActivity.this);
                try {

                    Toast.makeText(EmployeeDeliverAddressActivity.this, " Order Placed", Toast.LENGTH_SHORT).show();

                    int code = result3.get();
                    System.out.println("jyo code" + code);


                    //Todo: Clear cart product after Order placed
                    AG.getInstance().getEmployeeViewCartlist().clear();

                    employeeViewCartDeliveryAdapter = new EmployeeViewCartDeliveryAdapter(EmployeeDeliverAddressActivity.this, AG.getInstance().getEmployeeViewCartlist(), new EmployeeViewCartDeliveryAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                        }
                    });
                    recy_view_cart_place_Emp.setAdapter(employeeViewCartDeliveryAdapter);
                    employeeViewCartDeliveryAdapter.notifyDataSetChanged();



                } catch (Exception e) {

                    Toast.makeText(EmployeeDeliverAddressActivity.this, " Fail to Order Placing", Toast.LENGTH_SHORT).show();

                }


                Intent intent = new Intent(EmployeeDeliverAddressActivity.this, EmployeeActivityThankYou.class);
                startActivity(intent);
            }
        });

        change_address_Emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeDeliverAddressActivity.this, EmployeeAddAddressActivity.class);
                startActivity(intent);
            }
        });
    }

    //Todo: Displayng Total Price
    @Override
    protected void onResume() {


        super.onResume();
        payable_price_Emp.setText("₹"+getTotalPrice());
    }

    //Todo: Displayng Total Price
    private String getTotalPrice(){
        ArrayList<Category> category =AG.getInstance().getEmployeeViewCartlist();
        double totalPriceEmployee = 0;
        for(Category cat : category){
            totalPriceEmployee += (Double.parseDouble(cat.getPrice_emp()) * Double.parseDouble(cat.getQuantity_emp()));
        }
        return String.valueOf(totalPriceEmployee);
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(EmployeeDeliverAddressActivity.this, EmployeeAddAddressActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }

}