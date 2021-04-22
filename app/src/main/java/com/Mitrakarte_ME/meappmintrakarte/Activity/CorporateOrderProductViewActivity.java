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

import com.Mitrakarte_ME.meappmintrakarte.Adapter.CorporateOrderProductAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.CorporateOrderHistoryProduct;

public class CorporateOrderProductViewActivity extends AppCompatActivity {

    CorporateOrderProductAdapter corporateOrderProductAdapter;
    RecyclerView order_products_view_cor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corporate_order_product_view);

        order_products_view_cor = (RecyclerView) findViewById(R.id.order_products_view_cor);
        //todo: View Order Produt Details  Key Account
        AsyncTask<Context, Void, Integer> result1 = new CorporateOrderHistoryProduct().execute(CorporateOrderProductViewActivity.this);
        try {

            int code = result1.get();
            System.out.println("jyo code" + code);

        } catch (Exception e) {

            Toast.makeText(CorporateOrderProductViewActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();
        }

        LinearLayoutManager llm = new LinearLayoutManager(this);




        corporateOrderProductAdapter = new CorporateOrderProductAdapter(CorporateOrderProductViewActivity.this, AG.getInstance().getCorporateOrderHistoryProductlist(), new CorporateOrderProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


            }
        });


        order_products_view_cor.setLayoutManager(llm);
        order_products_view_cor.setAdapter(corporateOrderProductAdapter);
        order_products_view_cor.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));

    }

    @Override
    protected void onResume() {
        AG ht_inst = AG.getInstance();
        if(!ht_inst.getInitDone()) {
            Intent intent = new Intent(CorporateOrderProductViewActivity.this, CorporateMyOrderActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(CorporateOrderProductViewActivity.this, CorporateMyOrderActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }
}