package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Mitrakarte_ME.meappmintrakarte.Adapter.Corporate_Product_Search_Adapter;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.FetchProductSearch_Corporate;

public class Corporate_Product_Search_Activity extends AppCompatActivity {

    Corporate_Product_Search_Adapter product_search_adapter;
    EditText editText;
    Button button;
    RecyclerView recyclerView;
    String recv_search;
    AG dg_inst = AG.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corporate__product_search);

        Intent intent = getIntent();
        recv_search = intent.getStringExtra("editext_search");
        System.out.println("recv_search " + recv_search);


        editText = findViewById(R.id.search_bar_cor);
        button = findViewById(R.id.btn_search_cor);
        recyclerView = findViewById(R.id.recy_product_serach_cor);

        editText.setText(recv_search);


        dg_inst.getMobileProductlist().clear();
        dg_inst.getUser().setProduct_Search_cor(editText.getText().toString());
        AsyncTask<Context, Void, Integer> result2 = new FetchProductSearch_Corporate().execute(Corporate_Product_Search_Activity.this);
        try {

            //Toast.makeText(HomeActivity.this, "Application Fetching data", Toast.LENGTH_SHORT).show();

            int code = result2.get();
            System.out.println("jyo code" + code);


            if (code == 202) {
                Toast.makeText(Corporate_Product_Search_Activity.this, "No Product Available", Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {

            Toast.makeText(Corporate_Product_Search_Activity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

        }

        LinearLayoutManager llm = new LinearLayoutManager(Corporate_Product_Search_Activity.this);

        product_search_adapter = new Corporate_Product_Search_Adapter(Corporate_Product_Search_Activity.this, AG.getInstance().getMobileProductlist(), new Corporate_Product_Search_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


            }
        });

        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(product_search_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Corporate_Product_Search_Activity.this, LinearLayoutManager.VERTICAL, false));



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editText.getText().toString().length() == 0) {

                    editText.setError("Enter Product");
                    return;

                } else {

                    dg_inst.getMobileProductlist().clear();
                    dg_inst.getUser().setProduct_Search_cor(editText.getText().toString());
                    AsyncTask<Context, Void, Integer> result2 = new FetchProductSearch_Corporate().execute(Corporate_Product_Search_Activity.this);
                    try {

                        //Toast.makeText(HomeActivity.this, "Application Fetching data", Toast.LENGTH_SHORT).show();

                        int code = result2.get();
                        System.out.println("jyo code" + code);


                        if (code == 202) {
                            Toast.makeText(Corporate_Product_Search_Activity.this, "No Product Available", Toast.LENGTH_SHORT).show();

                        }
                    } catch (Exception e) {

                        Toast.makeText(Corporate_Product_Search_Activity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

                    }

                    LinearLayoutManager llm = new LinearLayoutManager(Corporate_Product_Search_Activity.this);

                    product_search_adapter = new Corporate_Product_Search_Adapter(Corporate_Product_Search_Activity.this, AG.getInstance().getMobileProductlist(), new Corporate_Product_Search_Adapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {


                        }
                    });

                    recyclerView.setLayoutManager(llm);
                    recyclerView.setAdapter(product_search_adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Corporate_Product_Search_Activity.this, LinearLayoutManager.VERTICAL, false));
                }

            }
        });

    }

    public void onBackPressed() {

        Intent setIntent = new Intent(Corporate_Product_Search_Activity.this, CorporateHomeActivity.class);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);

        return;
    }
}