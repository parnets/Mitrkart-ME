package com.Mitrakarte_ME.meappmintrakarte.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Mitrakarte_ME.meappmintrakarte.Adapter.AddressAdpater;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.Model.Address;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.AddressKeyAccount;
import com.Mitrakarte_ME.meappmintrakarte.Service.UpdateAddressToCart;

public class AddAddressActivity extends KeyAccount_SlidingBarActivity {

    TextView new_address;
    TextView mobile_number,addressoforder;
    TextView name;
    AddressAdpater addressAdpater;
    RecyclerView key_address_list;

    AG ht_inst = AG.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_change);

        new_address = findViewById(R.id.new_address);
       // mobile_number = findViewById(R.id.mobile_number);
       // name = findViewById(R.id.name);
       // addressoforder = findViewById(R.id.addressoforder);

//
//        mobile_number.setText(""+ ht_inst.getInstance().getUser().getkMobile());
//        name.setText(""+ ht_inst.getInstance().getUser().getkOwnerName());
//        addressoforder.setText(""+ ht_inst.getInstance().getUser().getkAddress());



        key_address_list = (RecyclerView) findViewById(R.id.key_address_list);





        //Todo: Displaying User Multiple Address For Delivery Key Account
        AsyncTask<Context, Void, Integer> result2 = new AddressKeyAccount().execute(AddAddressActivity.this);
        try {

           // Toast.makeText(AddAddressActivity.this, "Application Fetching data", Toast.LENGTH_SHORT).show();

            int code = result2.get();
            System.out.println("jyo code" + code);
        } catch (Exception e) {

            Toast.makeText(AddAddressActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

        }

        LinearLayoutManager llm = new LinearLayoutManager(this);

        //Todo: Send Delivery address to DeliverAddress activity
        addressAdpater = new AddressAdpater(AddAddressActivity.this, AG.getInstance().getKeyAddressList(), new AddressAdpater.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Address address = AG.getInstance().getKeyAddressList().get(position);
                AG.getInstance().setAddress(address);

                //Todo: Updated User address to cart
                AsyncTask<Context, Void, Integer> result2 = new UpdateAddressToCart().execute(AddAddressActivity.this);
                try {

                    Toast.makeText(AddAddressActivity.this, "Address Added to cart Sucessfully", Toast.LENGTH_SHORT).show();
//                    mcatList.remove(position);
//                    notifyDataSetChanged();
                    int code = result2.get();
                    System.out.println("jyo code" + code);
                    Intent intent = new Intent(AddAddressActivity.this, DeliverAddressActivity.class);
                    intent.putExtra("addressFromPrev", position);
                    startActivity(intent);

                } catch (Exception e) {

                    Toast.makeText(AddAddressActivity.this, "Application Fail to Updated address", Toast.LENGTH_SHORT).show();

                }









            }
        });
        key_address_list.setLayoutManager(llm);
        key_address_list.setAdapter(addressAdpater);
        key_address_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));





        new_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddAddressActivity.this, AddsressSelectActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(AddAddressActivity.this, KeyAccount_ViewcartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }


}





