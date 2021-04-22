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

import com.Mitrakarte_ME.meappmintrakarte.Adapter.CorporateOrderAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.CorporateFetchOrderHistoryview;

public class CorporateMyOrderActivity extends AppCompatActivity {

    CorporateOrderAdapter corporateOrderAdapter;
    RecyclerView recycl_order_product_cor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corporate_my_order);


        recycl_order_product_cor = (RecyclerView) findViewById(R.id.recycl_order_product_cor);


        //todo: Placed Order Details
        AsyncTask<Context, Void, Integer> result1 = new CorporateFetchOrderHistoryview().execute(CorporateMyOrderActivity.this);
        try {

        //    Toast.makeText(CorporateMyOrderActivity.this, "Application  fetching", Toast.LENGTH_SHORT).show();


            int code = result1.get();
            System.out.println("jyo code" + code);

        } catch (Exception e) {

            Toast.makeText(CorporateMyOrderActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();
        }

        LinearLayoutManager llm2 = new LinearLayoutManager(this);




        corporateOrderAdapter = new CorporateOrderAdapter(CorporateMyOrderActivity.this, AG.getInstance().getCorporateOrderHistorylist(), new CorporateOrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });


        recycl_order_product_cor.setLayoutManager(llm2);
        recycl_order_product_cor.setAdapter(corporateOrderAdapter);
        recycl_order_product_cor.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(CorporateMyOrderActivity.this, CorporateHomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }
}