package com.Mitrakarte_ME.meappmintrakarte.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Mitrakarte_ME.meappmintrakarte.Adapter.ViewCartAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.Viewcart;

public class KeyAccount_ViewcartActivity extends AppCompatActivity {

    RecyclerView view_cart1;
    ViewCartAdapter viewProductAdapter;
    Button Place_Order;
    AG ht_inst = AG.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcart);


        view_cart1 = findViewById(R.id.view_cart1);
        Place_Order = findViewById(R.id.Place_Order);

        Place_Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KeyAccount_ViewcartActivity.this, AddAddressActivity.class);
                startActivity(intent);
            }
        });


        AsyncTask<Context, Void, Integer> result2 = new Viewcart().execute(KeyAccount_ViewcartActivity.this);
        try {

            // Toast.makeText(ViewcartActivity.this, "Application Fetching data", Toast.LENGTH_SHORT).show();


            int code = result2.get();
            System.out.println("jyo code" + code);
        } catch (Exception e) {

            Toast.makeText(KeyAccount_ViewcartActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

        }

        LinearLayoutManager llm = new LinearLayoutManager(this);

        viewProductAdapter = new ViewCartAdapter(KeyAccount_ViewcartActivity.this, AG.getInstance().getViewcartlist(), new ViewCartAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Log.d("ViewartActivity ", "array size 2dd : " + AG.getInstance().getViewcartlist().size());
                if (AG.getInstance().getViewcartlist().size() == 0) {
                    Place_Order.setVisibility(View.INVISIBLE);
                } else {
                    Place_Order.setVisibility(View.VISIBLE);
                }


            }
        });

        view_cart1.setLayoutManager(llm);
        view_cart1.setAdapter(viewProductAdapter);


        viewProductAdapter.notifyDataSetChanged();
        if (AG.getInstance().getViewcartlist().size() == 0) {
            Place_Order.setVisibility(View.INVISIBLE);
        } else {
            Place_Order.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(KeyAccount_ViewcartActivity.this, KeyAccountHomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        super.onBackPressed();
    }
}
