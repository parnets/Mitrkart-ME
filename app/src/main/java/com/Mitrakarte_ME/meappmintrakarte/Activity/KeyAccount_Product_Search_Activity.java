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

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.KeyAccount_FetchProductSearch;

public class KeyAccount_Product_Search_Activity extends AppCompatActivity {

    KeyAccount_Product_Search_Adapter product_search_adapter;
    EditText editText;
    Button button;
    RecyclerView recyclerView;
    String recv_search;
    AG dg_inst = AG.getInstance();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_account__product__search);

        Intent intent = getIntent();
        recv_search = intent.getStringExtra("editext_search");
        System.out.println("recv_search " + recv_search);


        editText = findViewById(R.id.search_bar_k);
        button = findViewById(R.id.btn_search_k);
        recyclerView = findViewById(R.id.recy_product_serach_k);

        editText.setText(recv_search);



        dg_inst.getMobileProductlist().clear();
        dg_inst.getUser().setProduct_Search_key(editText.getText().toString());
        AsyncTask<Context, Void, Integer> result2 = new KeyAccount_FetchProductSearch().execute(KeyAccount_Product_Search_Activity.this);
        try {

            //Toast.makeText(HomeActivity.this, "Application Fetching data", Toast.LENGTH_SHORT).show();

            int code = result2.get();
            System.out.println("jyo code" + code);


            if (code == 202) {
                Toast.makeText(KeyAccount_Product_Search_Activity.this, "No Product Available", Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {

            Toast.makeText(KeyAccount_Product_Search_Activity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

        }

        LinearLayoutManager llm = new LinearLayoutManager(KeyAccount_Product_Search_Activity.this);

        product_search_adapter = new KeyAccount_Product_Search_Adapter(KeyAccount_Product_Search_Activity.this, AG.getInstance().getMobileProductlist(), new KeyAccount_Product_Search_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


            }
        });

        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(product_search_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(KeyAccount_Product_Search_Activity.this, LinearLayoutManager.VERTICAL, false));




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editText.getText().toString().length() == 0) {

                    editText.setError("Enter Product");
                    return;

                } else {

                    dg_inst.getMobileProductlist().clear();
                    dg_inst.getUser().setProduct_Search_key(editText.getText().toString());

                    AsyncTask<Context, Void, Integer> result2 = new KeyAccount_FetchProductSearch().execute(KeyAccount_Product_Search_Activity.this);
                    try {

                        //Toast.makeText(HomeActivity.this, "Application Fetching data", Toast.LENGTH_SHORT).show();

                        int code = result2.get();
                        System.out.println("jyo code" + code);


                        if (code == 202) {
                            Toast.makeText(KeyAccount_Product_Search_Activity.this, "No Product Available", Toast.LENGTH_SHORT).show();

                        }
                    } catch (Exception e) {

                        Toast.makeText(KeyAccount_Product_Search_Activity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

                    }

                    LinearLayoutManager llm = new LinearLayoutManager(KeyAccount_Product_Search_Activity.this);

                    product_search_adapter = new KeyAccount_Product_Search_Adapter(KeyAccount_Product_Search_Activity.this, AG.getInstance().getMobileProductlist(), new KeyAccount_Product_Search_Adapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                        }
                    });

                    recyclerView.setLayoutManager(llm);
                    recyclerView.setAdapter(product_search_adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(KeyAccount_Product_Search_Activity.this, LinearLayoutManager.VERTICAL, false));
                }

            }
        });
    }

    public void onBackPressed() {

        Intent setIntent = new Intent(KeyAccount_Product_Search_Activity.this, KeyAccountHomeActivity.class);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);

        return;
    }
}