package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.Mitrakarte_ME.meappmintrakarte.Adapter.OrderAdapter2;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.FetchOrderHistoryview;

public class KeyAccount_MyOrderActivity extends AppCompatActivity {

    OrderAdapter2 orderAdapter2;
    RecyclerView recycl_order_product, view_cart_1llll;
    TextView mModuleTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
//        LayoutInflater inflater = (LayoutInflater) this
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.activity_product, null, false);
//        mDrawer.addView(view, 0);

//        mModuleTitle = (TextView) findViewById(R.id.product_title);
//        mModuleTitle.setText("My Orders");

        recycl_order_product = (RecyclerView) findViewById(R.id.recycl_order_product);


        //todo: Placed Order Details
        AsyncTask<Context, Void, Integer> result1 = new FetchOrderHistoryview().execute(KeyAccount_MyOrderActivity.this);
        try {

            int code = result1.get();
            System.out.println("jyo code" + code);

        } catch (Exception e) {

            Toast.makeText(KeyAccount_MyOrderActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();
        }

        LinearLayoutManager llm = new LinearLayoutManager(this);




        orderAdapter2 = new OrderAdapter2(KeyAccount_MyOrderActivity.this, AG.getInstance().getOrderHistorylist(), new OrderAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {



            }
        });


        recycl_order_product.setLayoutManager(llm);
        recycl_order_product.setAdapter(orderAdapter2);
        recycl_order_product.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));

//        orderAdapter2.notifyDataSetChanged();




    }

//    @Override
//    protected void onResume() {
//        AG ht_inst = AG.getInstance();
//        if(!ht_inst.getInitDone()) {
//            Intent intent = new Intent(MyOrderActivity.this, SplashActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
//        super.onResume();
//    }
}