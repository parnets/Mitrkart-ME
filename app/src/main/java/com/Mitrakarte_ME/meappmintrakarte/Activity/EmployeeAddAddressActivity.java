package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.Mitrakarte_ME.meappmintrakarte.Adapter.EmployeeAddressAdpater;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.Model.Address;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.AddressEmployee;
import com.Mitrakarte_ME.meappmintrakarte.Service.EmployeeUpdateAddressToCart;

public class EmployeeAddAddressActivity extends AppCompatActivity {

    TextView new_address_Emp;
    RecyclerView address_list_Recy;
    EmployeeAddressAdpater employeeAddressAdpater;
    AG ht_inst = AG.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_add_address);

        new_address_Emp = findViewById(R.id.new_address_Emp);
        address_list_Recy = (RecyclerView) findViewById(R.id.address_list_Recy);


        //Todo: Displaying User Multiple Address For Delivery Key Account
        AsyncTask<Context, Void, Integer> result2 = new AddressEmployee().execute(EmployeeAddAddressActivity.this);
        try {

           // Toast.makeText(EmployeeAddAddressActivity.this, "Address Fetching data", Toast.LENGTH_SHORT).show();

            int code = result2.get();
            System.out.println("jyo code" + code);
        } catch (Exception e) {

            Toast.makeText(EmployeeAddAddressActivity.this, "Address Fail to fetch", Toast.LENGTH_SHORT).show();

        }

        LinearLayoutManager llm = new LinearLayoutManager(this);


        //Todo: Send Delivery address to DeliverAddress activity
        employeeAddressAdpater = new EmployeeAddressAdpater(EmployeeAddAddressActivity.this, AG.getInstance().getEmployeeAddressList(), new EmployeeAddressAdpater.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Address address = AG.getInstance().getEmployeeAddressList().get(position);
                AG.getInstance().setAddress(address);

                //Todo: Updated User address to cart
                AsyncTask<Context, Void, Integer> result2 = new EmployeeUpdateAddressToCart().execute(EmployeeAddAddressActivity.this);
                try {

                    Toast.makeText(EmployeeAddAddressActivity.this, "Updated user address Sucessfully", Toast.LENGTH_SHORT).show();
                    int code = result2.get();
                    System.out.println("jyo code" + code);
                    Intent intent = new Intent(EmployeeAddAddressActivity.this, EmployeeDeliverAddressActivity.class);
                    intent.putExtra("addressFromPrevEmp", position);
                    startActivity(intent);

                } catch (Exception e) {

                    Toast.makeText(EmployeeAddAddressActivity.this, "Application Fail to Updated address", Toast.LENGTH_SHORT).show();

                }


            }
        });
        address_list_Recy.setLayoutManager(llm);
        address_list_Recy.setAdapter(employeeAddressAdpater);
        address_list_Recy.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));




        new_address_Emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeAddAddressActivity.this, EmployeeAddsressSelectActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(EmployeeAddAddressActivity.this, EmployeeViewcartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }
}