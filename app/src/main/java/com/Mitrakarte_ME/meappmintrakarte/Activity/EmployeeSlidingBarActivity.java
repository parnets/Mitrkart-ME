package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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

import com.Mitrakarte_ME.meappmintrakarte.Service.CorporateViewcart;
import com.Mitrakarte_ME.meappmintrakarte.Service.EmployeeViewcart;
import com.google.android.material.navigation.NavigationView;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;

public class EmployeeSlidingBarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    AG ht_inst = AG.getInstance();
    protected DrawerLayout drawerLayout;
    protected DrawerLayout mDrawer;
    TextView mCart;
    EditText et_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_sliding_bar);

        LayoutInflater inflater = getLayoutInflater();
        Toolbar toolbar = findViewById(R.id.toolbar_Emp);
        TextView moduleTitle = (TextView) toolbar.findViewById(R.id.product_title_Emp);
        setSupportActionBar(toolbar);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_emp);
         mCart = (TextView) findViewById(R.id.tv_cart_emp);
        TextView mWallet = (TextView) findViewById(R.id.tv_wallet_emp);
        et_search = findViewById(R.id.et_search_Emp);


        Menu menu = navigationView.getMenu();
        View headerLayout =
                navigationView.getHeaderView(0);
        ImageView userImageView = headerLayout.findViewById(R.id.userImage_Emp);
        TextView usrName = headerLayout.findViewById(R.id.userName_Emp);
        TextView usrphone = headerLayout.findViewById(R.id.usernumber_Emp);

        //todo: User Name & Mobile Number
        usrphone.setText("" + ht_inst.getInstance().getUser().getEmpMobile());
        usrName.setText("" + ht_inst.getInstance().getUser().getEmpNAme());
        // userImageView.setImageBitmap(""+ht_inst.getInstance().getCategory().getImage());

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout_emp);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        //Todo: getting cart size
        AsyncTask<Context, Void, Integer> result2 = new EmployeeViewcart().execute(EmployeeSlidingBarActivity.this);
        try {

            //  Toast.makeText(CorporateViewcartActivity.this, "Application Fetching data", Toast.LENGTH_SHORT).show();

            int code = result2.get();
            System.out.println("jyo code" + code);
        } catch (Exception e) {

            Toast.makeText(EmployeeSlidingBarActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

        }

        String cart_size = String.valueOf(AG.getInstance().getEmployeeViewCartlist().size());
        System.out.println("Cart_size is "+  AG.getInstance().getEmployeeViewCartlist().size());

        if (cart_size.contains("0")){
        }else {
            mCart.setText(cart_size);
        }
        //Todo: getting cart size end

        // Todo: Cart page
        mCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(EmployeeSlidingBarActivity.this, EmployeeViewcartActivity.class);
                startActivity(i);
                finish();
            }
        });

        et_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(EmployeeSlidingBarActivity.this, Employee_Product_Search_Activity.class);
                i.putExtra("editext_search",et_search.getText().toString());
                startActivity(i);
                //finish();
            }
        });

        // Todo: Wallet page
        mWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(EmployeeSlidingBarActivity.this, Employee_WalletActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {

            case R.id.account_emp:
                Intent intent = new Intent(EmployeeSlidingBarActivity.this, EmployeeProfileActivity.class);
                startActivity(intent);
                break;


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

//
//            case R.id.delivery_address:
//                intent = new Intent(SlidingBarActivity.this, DeliverAddressActivity.class);
//                startActivity(intent);
//                break;
//
            case R.id.viewcart_emp:
                intent = new Intent(EmployeeSlidingBarActivity.this, EmployeeViewcartActivity.class);
                startActivity(intent);
                break;

            case R.id.navigation_orders_emp:
                intent = new Intent(EmployeeSlidingBarActivity.this, EmployeeMyOrderActivity.class);
                startActivity(intent);
                break;

            case R.id.wallet_emp:
                intent = new Intent(EmployeeSlidingBarActivity.this, Employee_WalletActivity.class);
                startActivity(intent);
                break;

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


            case R.id.logout_emp:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Are you sure,You want to Logout");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                               /* DbHelper.createVendorDbHelper(SlidingBarActivity.this);
                                Dao vendorDetailsDao = new Dao();
                                vendorDetailsDao.deleteUserDetails();*/

                                Intent intent1 = new Intent(EmployeeSlidingBarActivity.this, FirstActivityAccountSelection.class);
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_emp);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}