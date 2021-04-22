package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Mitrakarte_ME.meappmintrakarte.Adapter.CorporateViewPagerAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Adapter.EmployeeFetchProductAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Adapter.EmployeeHomeAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.EmployeeFetchCategories;
import com.Mitrakarte_ME.meappmintrakarte.Service.FetchProductEmployee;

import java.util.Timer;
import java.util.TimerTask;

public class EmployeeHomeActivity extends EmployeeSlidingBarActivity {

    //Todo: Before Option Menu
    TextView mModuleTitle, mcart, cart_quantity;
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    RecyclerView categories, product_all_Emp;
    EmployeeHomeAdapter categoryAdapter;
    EmployeeFetchProductAdapter fetchProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_employee_home);


        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_employee_home, null, false);
        mDrawer.addView(view, 0);


        mModuleTitle = (TextView) findViewById(R.id.product_title_Emp);
        mModuleTitle.setVisibility(View.VISIBLE);
        mModuleTitle.setText("Employee");
        netWorkAlert(this);

        Toolbar toolbar = findViewById(R.id.toolbar_Emp);
        mcart = toolbar.findViewById(R.id.header_cart_Emp);
        mcart.setVisibility(View.GONE);
        cart_quantity = toolbar.findViewById(R.id.tv_cartValue_Emp);
        cart_quantity.setVisibility(View.GONE);

        viewPager = (ViewPager) findViewById(R.id.im_slider1_Emp);
        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots_Emp);


        CorporateViewPagerAdapter viewPagerAdapter = new CorporateViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];
        final int[] currentPage = {0};


        // Todo: Image Slider
//        viewPager = (ViewPager) findViewById(R.id.im_slider1_Emp);
//        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots_Emp);
//
//        CorporateViewPagerAdapter viewPagerAdapter = new CorporateViewPagerAdapter(this);
//
//        viewPager.setAdapter(viewPagerAdapter);
//
//
//        //todo:count the dot of image slider
//        dotscount = viewPagerAdapter.getCount();
//        dots = new ImageView[dotscount];
//        final int[] currentPage = {0};


        //Todo: Image Slider
        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage[0] == dots.length) {
                    currentPage[0] = 0;
                }
                viewPager.setCurrentItem(currentPage[0]++, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);

        categories = (RecyclerView) findViewById(R.id.main_category_Emp);
        product_all_Emp = (RecyclerView) findViewById(R.id.product_all_Emp);

        // Todo: Fetching Categories
        AsyncTask<Context, Void, Integer> result2 = new EmployeeFetchCategories().execute(EmployeeHomeActivity.this);
        try {

            // Toast.makeText(HomeActivity.this, "Application Fetching data", Toast.LENGTH_SHORT).show();

            int code = result2.get();
            System.out.println("jyo code" + code);
        } catch (Exception e) {

            Toast.makeText(EmployeeHomeActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

        }

        LinearLayoutManager llm1 = new LinearLayoutManager(this);

        //todo:  Fetching all the Category List (getCategoryList)
        //Todo: Onclick on Category
        categoryAdapter = new EmployeeHomeAdapter(EmployeeHomeActivity.this, AG.getInstance().getCategoryList(), new EmployeeHomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(EmployeeHomeActivity.this, EmployeeProductsActivity.class);
                intent.putExtra("id", AG.getInstance().getCategoryList().get(position).getCatId());
                intent.putExtra("Position", position);
                startActivity(intent);

            }
        });

        categories.setLayoutManager(llm1);
        categories.setAdapter(categoryAdapter);
        categories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        LinearLayoutManager llm2 = new LinearLayoutManager(this);
        // Todo: Corporate - Fetching Product With Mobile & Token
        AsyncTask<Context, Void, Integer> result3 = new FetchProductEmployee().execute(EmployeeHomeActivity.this);
        try {

         //   Toast.makeText(EmployeeHomeActivity.this, "Application Fetching data", Toast.LENGTH_SHORT).show();

            int code = result3.get();
            System.out.println("jyo code" + code);
        } catch (Exception e) {

            Toast.makeText(EmployeeHomeActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

        }


        fetchProductAdapter = new EmployeeFetchProductAdapter(EmployeeHomeActivity.this, AG.getInstance().getMobileProductlist());

        product_all_Emp.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        product_all_Emp.setAdapter(fetchProductAdapter);
        fetchProductAdapter.notifyDataSetChanged();
    }

    private void netWorkAlert(final EmployeeHomeActivity context) {

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

        Intent intent = new Intent(EmployeeHomeActivity.this, EmployeeHomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }

}
