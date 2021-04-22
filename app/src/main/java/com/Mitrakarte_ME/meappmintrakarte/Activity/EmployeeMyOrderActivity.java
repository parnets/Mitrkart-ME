package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.Mitrakarte_ME.meappmintrakarte.Adapter.EmployeeOrderAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.EmployeeFetchOrderHistoryview;

public class EmployeeMyOrderActivity extends AppCompatActivity {

    EmployeeOrderAdapter employeeOrderAdapter;
    RecyclerView recycl_order_product_emp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_my_order);

        recycl_order_product_emp = (RecyclerView) findViewById(R.id.recycl_order_product_emp);


        //todo: Placed Order Details
        AsyncTask<Context, Void, Integer> result1 = new EmployeeFetchOrderHistoryview().execute(EmployeeMyOrderActivity.this);
        try {

       //     Toast.makeText(EmployeeMyOrderActivity.this, "Application  fetching", Toast.LENGTH_SHORT).show();


            int code = result1.get();
            System.out.println("jyo code" + code);

        } catch (Exception e) {

            Toast.makeText(EmployeeMyOrderActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();
        }

        LinearLayoutManager llm2 = new LinearLayoutManager(this);




        employeeOrderAdapter = new EmployeeOrderAdapter(EmployeeMyOrderActivity.this, AG.getInstance().getEmployeeOrderHistorylist(), new EmployeeOrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });


        recycl_order_product_emp.setLayoutManager(llm2);
        recycl_order_product_emp.setAdapter(employeeOrderAdapter);
        recycl_order_product_emp.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));

    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(EmployeeMyOrderActivity.this, EmployeeHomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }
}