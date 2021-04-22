package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.Mitrakarte_ME.meappmintrakarte.Adapter.OrderProductAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.OrderHistoryProduct;

public class OrderProductViewActivity extends AppCompatActivity {

    OrderProductAdapter orderProductAdapter;
    RecyclerView order_products_view_k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_product_view);

        order_products_view_k = (RecyclerView) findViewById(R.id.order_products_view_k);
        //todo: View Order Produt Details  Key Account
        AsyncTask<Context, Void, Integer> result1 = new OrderHistoryProduct().execute(OrderProductViewActivity.this);
        try {

            int code = result1.get();
            System.out.println("jyo code" + code);

        } catch (Exception e) {

            Toast.makeText(OrderProductViewActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();
        }

        LinearLayoutManager llm = new LinearLayoutManager(this);




        orderProductAdapter = new OrderProductAdapter(OrderProductViewActivity.this, AG.getInstance().getOrderHistoryProductlist(), new OrderProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


            }
        });


        order_products_view_k.setLayoutManager(llm);
        order_products_view_k.setAdapter(orderProductAdapter);
        order_products_view_k.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));

    }
}