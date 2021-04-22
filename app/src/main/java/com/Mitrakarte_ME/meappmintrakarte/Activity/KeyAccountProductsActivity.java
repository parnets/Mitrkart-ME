package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.widget.Toolbar;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Mitrakarte_ME.meappmintrakarte.Adapter.FetchCatProductAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Adapter.KeyAccountSubcategoryAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.Model.Category;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.FetchCategoryProduct;
import com.Mitrakarte_ME.meappmintrakarte.Service.KeyAccountFetchSubCategories;

import java.util.ArrayList;

public class KeyAccountProductsActivity extends KeyAccount_SlidingBarActivity {

  //  private ProductAdapter Paraadapter;
    TextView mModuleTitle, tvshop, mcart, cart_quantity;
    ImageView ivfilter;
    Context context;
    ProgressBar progressBar;
    RelativeLayout relativesearch;
    EditText search;
    static View.OnClickListener myOnClickListener;
    LinearLayout llcartempty;
    ListView listView;

    RecyclerView subcategoryKeyAcc, categoryProduct;

    int id=0;
    FetchCatProductAdapter fetchCatProductAdapter;
    KeyAccountSubcategoryAdapter subcategoryAdapter;

   // CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_product, null, false);
        mDrawer.addView(contentView, 0);
        mModuleTitle = (TextView) findViewById(R.id.product_title);
        mModuleTitle.setVisibility(View.VISIBLE);
        mModuleTitle.setText("SubCategories");
        AG dg_inst = AG.getInstance();
        //setContentView(R.layout.activity_product);



      //  mDrawer.addView(contentView, 0);
        //Todo
//        mModuleTitle = (TextView) findViewById(R.id.product_title);
//        mModuleTitle.setVisibility(View.VISIBLE);
//        mModuleTitle.setText("SubCategories");
        netWorkAlert(this);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        dg_inst.getCategory().setCatId(id);

        subcategoryKeyAcc  = (RecyclerView)findViewById(R.id.subcategoryKeyAcc);
        categoryProduct = findViewById(R.id.categoryProduct);


      //  id = intent.getIntExtra("id", 0);

        //subcategories= findViewById(R.id.sub_cat);
        //Todo
        //dg_inst.getCategory().setCatId(id);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Todo
//        ivfilter = toolbar.findViewById(R.id.iv_filter);
//        ivfilter.setVisibility(View.GONE);
//        mcart = toolbar.findViewById(R.id.header_cart);
//         mcart.setVisibility(View.VISIBLE);
//        search = findViewById(R.id.et_search);
//        search.setVisibility(View.VISIBLE);
//        cart_quantity = toolbar.findViewById(R.id.tv_cartValue);
//        cart_quantity.setVisibility(View.VISIBLE);
//        llcartempty = findViewById(R.id.llCartEmpty);
        //listView = (ListView) findViewById(R.id.listView);
//        tvshop = findViewById(R.id.tv_continue_shopping);
        //  progressBar = (ProgressBar) findViewById(R.id.progress);


       // subcategories = findViewById(R.id.subCatlist);

      //  subCatlist2 = findViewById(R.id.subCatlist2);

        // Todo: Fetching Sub Categories
        AsyncTask<Context, Void, Integer> result2 = new KeyAccountFetchSubCategories().execute(KeyAccountProductsActivity.this);
        try {

       //     Toast.makeText(KeyAccountProductsActivity.this, "Application Fetching data", Toast.LENGTH_SHORT).show();

            int code = result2.get();
            System.out.println("jyo code" + code);
        } catch (Exception e) {

            Toast.makeText(KeyAccountProductsActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

        }

        LinearLayoutManager llm4 = new LinearLayoutManager(this);

        //Todo:If the Subcategory is empty
     /*   ArrayList<Category> array_size2 = AG.getInstance().getAllProductlistnew();
        int size2 = array_size2.size();
        if (size2 == 0){
            Toast.makeText(KeyAccountProductsActivity.this, "No Subcategory Available", Toast.LENGTH_SHORT).show();
        }*/
        //Todo:If the Subcategory is empty

        subcategoryAdapter = new KeyAccountSubcategoryAdapter(KeyAccountProductsActivity.this, AG.getInstance().getAllProductlistnew(), new KeyAccountSubcategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

              Intent intent = new Intent(KeyAccountProductsActivity.this, KeyAccountProductViewActivity.class);
            //    intent.putExtra("id", AG.getInstance().getCategoryList().get(position).getCatId());

                intent.putExtra("id",AG.getInstance().getCategory().getCatId());
                intent.putExtra("subid",AG.getInstance().getAllProductlistnew().get(position).getSub_catId());
                intent.putExtra("Position", position);
                startActivity(intent);


            }
        });
        subcategoryKeyAcc.setLayoutManager(llm4);
        subcategoryKeyAcc.setAdapter(subcategoryAdapter);
        subcategoryKeyAcc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Todo: Fetching Product Based on  Categories

        AsyncTask<Context, Void, Integer> result3 = new FetchCategoryProduct().execute(KeyAccountProductsActivity.this);
        try {

          //  Toast.makeText(KeyAccountProductsActivity.this, "Application Fetching data", Toast.LENGTH_SHORT).show();

            int code = result3.get();
            System.out.println("jyo code" + code);
        } catch (Exception e) {

            Toast.makeText(KeyAccountProductsActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

        }

        LinearLayoutManager llm3 = new LinearLayoutManager(this);


        //Todo:If the Products is empty in that Subcategory
        ArrayList<Category> array_size = AG.getInstance().getFetchcatproductlist();
        int size = array_size.size();
        if (size == 0){
            Toast.makeText(KeyAccountProductsActivity.this, "No Products Available", Toast.LENGTH_SHORT).show();
        }
        //Todo:If the Products is empty in that Subcategory

        fetchCatProductAdapter = new FetchCatProductAdapter(KeyAccountProductsActivity.this, AG.getInstance().getFetchcatproductlist(), new FetchCatProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(KeyAccountProductsActivity.this, KeyAccountProductsActivity.class);
                //    intent.putExtra("id", AG.getInstance().getCategoryList().get(position).getCatId());

                intent.putExtra("id",AG.getInstance().getFetchcatproductlist().get(position).getCatId());
                intent.putExtra("subid",AG.getInstance().getAllProductlistnew().get(position).getSub_catId());
                intent.putExtra("Position", position);
                startActivity(intent);


            }
        });

        categoryProduct.setLayoutManager(llm3);
        categoryProduct.setAdapter(fetchCatProductAdapter);
        categoryProduct.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

//          categories.setLayoutManager(llm1);
//        categories.setAdapter(categoryAdapter);
//        categories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
//    }

    }

    private void netWorkAlert(final KeyAccountProductsActivity context) {

        if (isOnline(context)) {
            //do whatever you want to do
        } else {
            try {
                android.app.AlertDialog alertDialog = new AlertDialog.Builder(context).create();

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