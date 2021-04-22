package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.Mitrakarte_ME.meappmintrakarte.Adapter.CorporateAddressAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.Model.Address;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.AddressCorporate;
import com.Mitrakarte_ME.meappmintrakarte.Service.CorporateUpdateAddressToCart;

public class CorporateAddAddressActivity extends AppCompatActivity {

    TextView new_address_cor;
    TextView mobile_number,addressoforder;
    RecyclerView address_list_cor;
    CorporateAddressAdapter corporateAddressAdapter;
    TextView name;
    AG ht_inst = AG.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corporate_add_address);


        new_address_cor = findViewById(R.id.new_address_cor);

        address_list_cor = (RecyclerView) findViewById(R.id.address_list_cor);


        //Todo: Displaying User Multiple Address For Delivery Key Account
        AsyncTask<Context, Void, Integer> result2 = new AddressCorporate().execute(CorporateAddAddressActivity.this);
        try {

          //  Toast.makeText(CorporateAddAddressActivity.this, "Address Fetching data", Toast.LENGTH_SHORT).show();

            int code = result2.get();
            System.out.println("jyo code" + code);
        } catch (Exception e) {

            Toast.makeText(CorporateAddAddressActivity.this, "Address Fail to fetch", Toast.LENGTH_SHORT).show();

        }

        LinearLayoutManager llm = new LinearLayoutManager(this);


       //Todo: Send Delivery address to DeliverAddress activity
        corporateAddressAdapter = new CorporateAddressAdapter(CorporateAddAddressActivity.this, AG.getInstance().getKeyAddressList(), new CorporateAddressAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Address address = AG.getInstance().getKeyAddressList().get(position);
                AG.getInstance().setAddress(address);

                //Todo: Updated User address to cart
                AsyncTask<Context, Void, Integer> result2 = new CorporateUpdateAddressToCart().execute(CorporateAddAddressActivity.this);
                try {

                    Toast.makeText(CorporateAddAddressActivity.this, "Address Added to cart Sucessfully", Toast.LENGTH_SHORT).show();
//                    mcatList.remove(position);
//                    notifyDataSetChanged();
                    int code = result2.get();
                    System.out.println("jyo code" + code);
                    Intent intent = new Intent(CorporateAddAddressActivity.this, CorporateDeliverAddressActivity.class);
                    intent.putExtra("addressFromPrevCor", position);
                    startActivity(intent);

                } catch (Exception e) {

                    Toast.makeText(CorporateAddAddressActivity.this, "Application Fail to Updated address", Toast.LENGTH_SHORT).show();

                }


            }
        });
        address_list_cor.setLayoutManager(llm);
        address_list_cor.setAdapter(corporateAddressAdapter);
        address_list_cor.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));



        new_address_cor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CorporateAddAddressActivity.this, CorporateAddsressSelectActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(CorporateAddAddressActivity.this, CorporateViewcartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }
}