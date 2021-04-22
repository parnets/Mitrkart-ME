package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Mitrakarte_ME.meappmintrakarte.Adapter.ViewProductAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.Model.Category;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.KeyAccountFetchProduct;

import java.util.ArrayList;

public class KeyAccountProductViewActivity extends KeyAccount_SlidingBarActivity {

    TextView mModuleTitle;
    RecyclerView view_productnew;
    int subcat_id,id1=0;
    ViewProductAdapter viewProductAdapter;
    Button btnaddtocart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.key_account_product_activity);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.key_account_product_view_activity, null, false);

        mDrawer.addView(contentView, 0);
        mModuleTitle = (TextView) findViewById(R.id.product_title);
        mModuleTitle.setVisibility(View.VISIBLE);
        mModuleTitle.setText("SubCategories");
        AG dg_inst = AG.getInstance();
        netWorkAlert(this);



        Intent intent = getIntent();
        subcat_id = intent.getIntExtra("subid", 0);
        dg_inst.getCategory().setSub_catId(subcat_id);

        view_productnew = findViewById(R.id.view_productnew);


        AsyncTask<Context, Void, Integer> result2 = new KeyAccountFetchProduct().execute(KeyAccountProductViewActivity.this);
        try {

           // Toast.makeText(KeyAccountProductViewActivity.this, "Application Fetching data", Toast.LENGTH_SHORT).show();

            int code = result2.get();
            System.out.println("jyo code" + code);
        } catch (Exception e) {

            Toast.makeText(KeyAccountProductViewActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

        }

        LinearLayoutManager llm = new LinearLayoutManager(this);

        //Todo:If the Products is empty in that Subcategory
        ArrayList<Category> array_size = AG.getInstance().getProductList2();
        int size = array_size.size();
        if (size == 0) {
            Toast.makeText(KeyAccountProductViewActivity.this, "No Products Available", Toast.LENGTH_SHORT).show();
        }
        //Todo:If the Products is empty in that Subcategory


        viewProductAdapter = new ViewProductAdapter(KeyAccountProductViewActivity.this, AG.getInstance().getProductList2(), new ViewProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Intent intent = new Intent(ProductViewActivity.this, ProductViewActivity.class);
//                intent.putExtra("pid", AG.getInstance().getCategory().getPro_id());
//                intent.putExtra("subid",AG.getInstance().getCategory().getSub_catId());
//                intent.putExtra("Position", position);
//                startActivity(intent);





            }
        });

       view_productnew.setLayoutManager(llm);
        view_productnew.setAdapter(viewProductAdapter);
        view_productnew.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    private void netWorkAlert(final KeyAccountProductViewActivity context) {

        if (isOnline(context)) {
            //do whatever you want to do
        } else {
            try {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();

                alertDialog.setTitle("Info");
                alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        context.finish();
                    }
                });

                alertDialog.show();
            } catch (Exception e) {
                Log.e("Constants.TAG", "Show Dialog: " + e.getMessage());
            }
        }
    }


    private static boolean isOnline(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {

            // Toast.makeText(context, "No Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}