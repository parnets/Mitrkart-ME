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

import com.Mitrakarte_ME.meappmintrakarte.Adapter.EmployeeOrderProductAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.EmployeeOrderHistoryProduct;

public class EmployeeOrderProductViewActivity extends AppCompatActivity {

    EmployeeOrderProductAdapter employeeOrderProductAdapter;
    RecyclerView order_products_view_emp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_order_product_view);

        order_products_view_emp = (RecyclerView) findViewById(R.id.order_products_view_emp);
        //todo: View Order Produt Details  Key Account
        AsyncTask<Context, Void, Integer> result1 = new EmployeeOrderHistoryProduct().execute(EmployeeOrderProductViewActivity.this);
        try {

            int code = result1.get();
            System.out.println("jyo code" + code);

        } catch (Exception e) {

            Toast.makeText(EmployeeOrderProductViewActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();
        }

        LinearLayoutManager llm = new LinearLayoutManager(this);




        employeeOrderProductAdapter = new EmployeeOrderProductAdapter(EmployeeOrderProductViewActivity.this, AG.getInstance().getEmployeeOrderHistoryProductlist(), new EmployeeOrderProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


            }
        });


        order_products_view_emp.setLayoutManager(llm);
        order_products_view_emp.setAdapter(employeeOrderProductAdapter);
        order_products_view_emp.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(EmployeeOrderProductViewActivity.this, EmployeeMyOrderActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }
}