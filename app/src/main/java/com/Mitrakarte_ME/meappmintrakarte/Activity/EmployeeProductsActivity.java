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
import android.widget.TextView;
import android.widget.Toast;

import com.Mitrakarte_ME.meappmintrakarte.Adapter.EmployeeFetchCatProductAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Adapter.EmployeeSubcategoryAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.Model.Category;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.EmployeeFetchCategoryProduct;
import com.Mitrakarte_ME.meappmintrakarte.Service.EmployeeFetchSubCategories;

import java.util.ArrayList;

public class EmployeeProductsActivity extends EmployeeSlidingBarActivity {

    TextView mModuleTitle;
    RecyclerView subcategoryKeyAcc, categoryProduct_Emp;

    int id=0;
    //FetchCatProductAdapter fetchCatProductAdapter;
    EmployeeSubcategoryAdapter corporateSubcategoryAdapter;

    EmployeeFetchCatProductAdapter fetchCatProductAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_employee_products);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_employee_products, null, false);

        mDrawer.addView(contentView, 0);
        mModuleTitle = (TextView) findViewById(R.id.product_title_Emp);
        mModuleTitle.setVisibility(View.VISIBLE);
        mModuleTitle.setText("SubCategories");
        AG dg_inst = AG.getInstance();

        netWorkAlert(this);

        //setContentView(R.layout.activity_product);



        //  mDrawer.addView(contentView, 0);
        //Todo
//        mModuleTitle = (TextView) findViewById(R.id.product_title);
//        mModuleTitle.setVisibility(View.VISIBLE);
//        mModuleTitle.setText("SubCategories");
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        dg_inst.getCategory().setCatId(id);

        subcategoryKeyAcc  = (RecyclerView)findViewById(R.id.subcategory_Emp);
        categoryProduct_Emp  = (RecyclerView)findViewById(R.id.categoryProduct_Emp);
        //   categoryProduct = findViewById(R.id.categoryProduct);


        //  id = intent.getIntExtra("id", 0);

        //subcategories= findViewById(R.id.sub_cat);
        //Todo
        //dg_inst.getCategory().setCatId(id);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Emp);
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
        AsyncTask<Context, Void, Integer> result2 = new EmployeeFetchSubCategories().execute(EmployeeProductsActivity.this);
        try {

         //   Toast.makeText(EmployeeProductsActivity.this, "Application Fetching data", Toast.LENGTH_SHORT).show();

            int code = result2.get();
            System.out.println("jyo code" + code);
        } catch (Exception e) {

            Toast.makeText(EmployeeProductsActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

        }

        LinearLayoutManager llm4 = new LinearLayoutManager(this);

        corporateSubcategoryAdapter = new EmployeeSubcategoryAdapter(EmployeeProductsActivity.this, AG.getInstance().getAllSubCategorylistCorporate(), new EmployeeSubcategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(EmployeeProductsActivity.this, EmployeeProductViewActivity.class);
                //    intent.putExtra("id", AG.getInstance().getCategoryList().get(position).getCatId());

                intent.putExtra("id",AG.getInstance().getCategory().getCatId());
                intent.putExtra("subid",AG.getInstance().getAllSubCategorylistCorporate().get(position).getSub_catId());
                intent.putExtra("Position", position);
                startActivity(intent);


            }
        });
        subcategoryKeyAcc.setLayoutManager(llm4);
        subcategoryKeyAcc.setAdapter(corporateSubcategoryAdapter);
        subcategoryKeyAcc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        // Todo: Fetching Product Based on  Categories EmployeeFetchCategoryProduct
        AsyncTask<Context, Void, Integer> result3 = new EmployeeFetchCategoryProduct().execute(EmployeeProductsActivity.this);
        try {

         //   Toast.makeText(EmployeeProductsActivity.this, "Application Fetching data", Toast.LENGTH_SHORT).show();

            int code = result3.get();
            System.out.println("jyo code" + code);
        } catch (Exception e) {

            Toast.makeText(EmployeeProductsActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

        }

        LinearLayoutManager llm3 = new LinearLayoutManager(this);


        //Todo:If the Products is empty in that Subcategory
        ArrayList<Category> array_size = AG.getInstance().getFetchcatproductlist();
        int size = array_size.size();
        if (size == 0){
            Toast.makeText(EmployeeProductsActivity.this, "No Products Available", Toast.LENGTH_SHORT).show();
        }
        //Todo:If the Products is empty in that Subcategory

        fetchCatProductAdapter = new EmployeeFetchCatProductAdapter(EmployeeProductsActivity.this, AG.getInstance().getFetchcatproductlist(), new EmployeeFetchCatProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {



            }
        });

        categoryProduct_Emp.setLayoutManager(llm3);
        categoryProduct_Emp.setAdapter(fetchCatProductAdapter);
        categoryProduct_Emp.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    private void netWorkAlert(final EmployeeProductsActivity context) {

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

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(EmployeeProductsActivity.this, EmployeeHomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }


}