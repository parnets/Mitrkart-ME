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
import android.widget.Toast;

import com.Mitrakarte_ME.meappmintrakarte.Adapter.CorporateViewCartAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.CorporateViewcart;

public class CorporateViewcartActivity extends AppCompatActivity {

    RecyclerView view_cart_cor;
    CorporateViewCartAdapter viewProductAdapter;
    Button Place_Order;
    AG ht_inst = AG.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corporate_viewcart);


        view_cart_cor = findViewById(R.id.view_cart_cor_recyclerview);
        Place_Order = findViewById(R.id.Place_Order_cor);

        Place_Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CorporateViewcartActivity.this, CorporateAddAddressActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        LinearLayoutManager llm = new LinearLayoutManager(this);

        AG.getInstance().getCorporateViewCartlist().clear();


        AsyncTask<Context, Void, Integer> result2 = new CorporateViewcart().execute(CorporateViewcartActivity.this);
        try {

            //  Toast.makeText(CorporateViewcartActivity.this, "Application Fetching data", Toast.LENGTH_SHORT).show();

            int code = result2.get();
            System.out.println("jyo code" + code);
        } catch (Exception e) {

            Toast.makeText(CorporateViewcartActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

        }

        viewProductAdapter = new CorporateViewCartAdapter(CorporateViewcartActivity.this, AG.getInstance().getCorporateViewCartlist(), new CorporateViewCartAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Log.d("ViewartActivity ", "array size 2dd : " + AG.getInstance().getCorporateViewCartlist().size());
                if (AG.getInstance().getCorporateViewCartlist().size() == 0) {
                    Place_Order.setVisibility(View.INVISIBLE);
                    //tv_empty_cart.setVisibility(View.VISIBLE);
                } else {
                    Place_Order.setVisibility(View.VISIBLE);
                    // tv_empty_cart.setVisibility(View.INVISIBLE);
                }


            }
        });

        view_cart_cor.setLayoutManager(llm);
        view_cart_cor.setAdapter(viewProductAdapter);

        viewProductAdapter.notifyDataSetChanged();
        if (AG.getInstance().getCorporateViewCartlist().size() == 0) {
            Place_Order.setVisibility(View.INVISIBLE);
            //  tv_empty_cart.setVisibility(View.VISIBLE);
        } else {
            Place_Order.setVisibility(View.VISIBLE);
            // tv_empty_cart.setVisibility(View.INVISIBLE);
        }


    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(CorporateViewcartActivity.this, CorporateHomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        super.onBackPressed();
    }
}
