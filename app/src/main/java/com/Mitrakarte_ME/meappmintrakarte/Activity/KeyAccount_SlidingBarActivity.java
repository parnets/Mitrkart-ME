package com.Mitrakarte_ME.meappmintrakarte.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.Mitrakarte_ME.meappmintrakarte.Service.Viewcart;
import com.google.android.material.navigation.NavigationView;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;

public class KeyAccount_SlidingBarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    AG ht_inst = AG.getInstance();
    protected DrawerLayout drawerLayout;
    protected DrawerLayout mDrawer;
    TextView mCart;
    EditText et_search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slidebar);


        LayoutInflater inflater = getLayoutInflater();
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView moduleTitle = (TextView) toolbar.findViewById(R.id.product_title);
        setSupportActionBar(toolbar);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
         mCart = (TextView)findViewById(R.id.tv_cart_k);
        TextView mWallet = (TextView) findViewById(R.id.tv_wallet_k);
        et_search = findViewById(R.id.et_search_k);

        Menu menu = navigationView.getMenu();
        View headerLayout =
                navigationView.getHeaderView(0);
        ImageView userImageView = headerLayout.findViewById(R.id.userImageView);
        TextView usrName = headerLayout.findViewById(R.id.userName);
        TextView usrphone = headerLayout.findViewById(R.id.usernumber);

        //todo: User Name & Mobile Number
        usrphone.setText(""+ ht_inst.getInstance().getUser().getkMobile());
        usrName.setText(""+ ht_inst.getInstance().getUser().getkOwnerName());
        // userImageView.setImageBitmap(""+ht_inst.getInstance().getCategory().getImage());

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


        //Todo: getting cart size
        AsyncTask<Context, Void, Integer> result2 = new Viewcart().execute(KeyAccount_SlidingBarActivity.this);
        try {

            //  Toast.makeText(ViewcartActivity.this, "Application Fetching data", Toast.LENGTH_SHORT).show();
            int code = result2.get();
            System.out.println("jyo code" + code);
        } catch (Exception e) {

            Toast.makeText(KeyAccount_SlidingBarActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

        }
        String cart_size = String.valueOf(AG.getInstance().getViewcartlist().size());
        System.out.println("Cart_size is "+  AG.getInstance().getViewcartlist().size());

        if (cart_size.contains("0")){
        }else {
            mCart.setText(cart_size);
        }
        //Todo: getting cart size

    // Todo: Cart page
        mCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(KeyAccount_SlidingBarActivity.this, KeyAccount_ViewcartActivity.class);
                startActivity(i);
                finish();
            }
        });

        et_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(KeyAccount_SlidingBarActivity.this, KeyAccount_Product_Search_Activity.class);
                i.putExtra("editext_search",et_search.getText().toString());
                startActivity(i);
                //finish();
            }
        });

        // Todo: Wallet page
        mWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(KeyAccount_SlidingBarActivity.this, KeyAccount_WalletActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch(id) {


            case R.id.account:
                Intent intent = new Intent(KeyAccount_SlidingBarActivity.this, KeyAccountProfile.class);
                startActivity(intent);
                break;


            case R.id.navigation_orders:
                Intent intent2 = new Intent(KeyAccount_SlidingBarActivity.this, KeyAccount_MyOrderActivity.class);
                startActivity(intent2);
                break;
//
//            case R.id.orders_history:
//                intent = new Intent(SlidingBarActivity.this, OrderHistory.class);
//                startActivity(intent);
//                break;
//
//
//            case R.id.wishlist:
//                intent = new Intent(SlidingBarActivity.this, WishList.class);
//                startActivity(intent);
//                break;
//
//            case R.id.wallet:
//                intent = new Intent(SlidingBarActivity.this, WalletActivity.class);
//                startActivity(intent);
//                break;
//
//            case R.id.delivery_address:
//                 intent = new Intent(SlidingBarActivity.this, AddAddressActivity.class);
//                startActivity(intent);
//                break;

            case R.id.viewcart:
                 intent = new Intent(KeyAccount_SlidingBarActivity.this, KeyAccount_ViewcartActivity.class);
                startActivity(intent);
                break;

            case R.id.wallet:
                intent = new Intent(KeyAccount_SlidingBarActivity.this, KeyAccount_WalletActivity.class);
                startActivity(intent);
                break;


//            case R.id.change_passowrd:
//                intent = new Intent(SlidingBarActivity.this, ChangePasswordActivity.class);
//                startActivity(intent);
//                break;


           /* case R.id.offers:
                intent = new Intent(SlidingBarActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
                break;

            case R.id.navigation_share:
                intent = new Intent(SlidingBarActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
                break;

            case R.id.navigation_rate:
                intent = new Intent(SlidingBarActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
                break;*/

//            case R.id.navigation_rate:
//                intent = new Intent(SlidingBarActivity.this, RateAppActivity.class);
//                startActivity(intent);
//                break;


            case R.id.logout:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Are you sure,You want to Logout");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                               /* DbHelper.createVendorDbHelper(SlidingBarActivity.this);
                                Dao vendorDetailsDao = new Dao();
                                vendorDetailsDao.deleteUserDetails();*/

                                Intent intent1= new Intent(KeyAccount_SlidingBarActivity.this, FirstActivityAccountSelection.class);
                                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent1);
                                finish();
                            }
                        });


                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;
    }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
