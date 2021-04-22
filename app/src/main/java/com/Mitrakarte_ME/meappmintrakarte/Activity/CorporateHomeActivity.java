package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Mitrakarte_ME.meappmintrakarte.Adapter.CorporateFetchProductAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Adapter.CorporateHomeAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Adapter.CorporateViewPagerAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.CorporateFetchCategories;
import com.Mitrakarte_ME.meappmintrakarte.Service.FetchProductCorporate;

import java.util.Timer;
import java.util.TimerTask;

public class CorporateHomeActivity extends CorporateSlidingBarActivity {

    //Todo: Before Option Menu
    TextView mModuleTitle, mcart, cart_quantity;
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    RecyclerView categories, product_all;
    CorporateHomeAdapter categoryAdapter;
    CorporateFetchProductAdapter fetchProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_corporate_home);

        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_corporate_home, null, false);
        mDrawer.addView(view, 0);


        mModuleTitle = (TextView) findViewById(R.id.product_title_cor);
        mModuleTitle.setVisibility(View.VISIBLE);
        mModuleTitle.setText("MitraKarte");
        // netWorkAlert(this);

        Toolbar toolbar = findViewById(R.id.toolbar_cor);
        mcart = toolbar.findViewById(R.id.header_cart_cor);
        mcart.setVisibility(View.GONE);
        cart_quantity = toolbar.findViewById(R.id.tv_cartValue_Cor);
        cart_quantity.setVisibility(View.GONE);

        viewPager = (ViewPager) findViewById(R.id.im_slider1_cor);
        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots_cor);


        CorporateViewPagerAdapter viewPagerAdapter = new CorporateViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];
        final int[] currentPage = {0};

        // Todo: Image Slider
//        viewPager = (ViewPager) findViewById(R.id.im_slider1_cor);
//        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots_cor);
//
//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
//
//        viewPager.setAdapter(viewPagerAdapter);
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


        categories = (RecyclerView) findViewById(R.id.main_category_cor);
        product_all = (RecyclerView) findViewById(R.id.product_all_cor);

        // Todo: Fetching Categories
        AsyncTask<Context, Void, Integer> result2 = new CorporateFetchCategories().execute(CorporateHomeActivity.this);
        try {

            // Toast.makeText(HomeActivity.this, "Application Fetching data", Toast.LENGTH_SHORT).show();

            int code = result2.get();
            System.out.println("jyo code" + code);
        } catch (Exception e) {

            Toast.makeText(CorporateHomeActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

        }

        LinearLayoutManager llm1 = new LinearLayoutManager(this);

        //todo:  Fetching all the Category List (getCategoryList)
        //Todo: Onclick on Category
        categoryAdapter = new CorporateHomeAdapter(CorporateHomeActivity.this, AG.getInstance().getCategoryList(), new CorporateHomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(CorporateHomeActivity.this, CorporateProductsActivity.class);
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
        AsyncTask<Context, Void, Integer> result3 = new FetchProductCorporate().execute(CorporateHomeActivity.this);
        try {

           // Toast.makeText(CorporateHomeActivity.this, "Application Fetching data", Toast.LENGTH_SHORT).show();

            int code = result3.get();
            System.out.println("jyo code" + code);
        } catch (Exception e) {

            Toast.makeText(CorporateHomeActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

        }



        fetchProductAdapter = new CorporateFetchProductAdapter(CorporateHomeActivity.this, AG.getInstance().getMobileProductlist());

        product_all.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        product_all.setAdapter(fetchProductAdapter);
        fetchProductAdapter.notifyDataSetChanged();
    }

    // Todo: Menu Corporate Option

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.corporate_menu, menu);
//        return true;
//    }

    @Override
    protected void onResume () {
        AG ht_inst = AG.getInstance();
        if (!ht_inst.getInitDone()) {
            Intent intent = new Intent(CorporateHomeActivity.this, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        super.onResume();
    }
}