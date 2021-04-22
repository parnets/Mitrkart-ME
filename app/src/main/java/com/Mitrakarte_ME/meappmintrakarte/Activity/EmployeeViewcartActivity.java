package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.Mitrakarte_ME.meappmintrakarte.Adapter.EmployeeViewCartAdapter;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.EmployeeViewcart;

public class EmployeeViewcartActivity extends AppCompatActivity {

    RecyclerView view_cart_recyclerview_emp;
    EmployeeViewCartAdapter employeeViewCartAdapter;
    Button Place_Order_emp;
    AG ht_inst = AG.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_viewcart);

        view_cart_recyclerview_emp = findViewById(R.id.view_cart_recyclerview_emp);
        Place_Order_emp = findViewById(R.id.Place_Order_emp);

        Place_Order_emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EmployeeViewcartActivity.this, EmployeeAddAddressActivity.class);
                startActivity(intent);
            }
        });

        AsyncTask<Context, Void, Integer> result2 = new EmployeeViewcart().execute(EmployeeViewcartActivity.this);
        try {

          //  Toast.makeText(EmployeeViewcartActivity.this, "Application Fetching data", Toast.LENGTH_SHORT).show();

            int code = result2.get();
            System.out.println("jyo code" + code);
        } catch (Exception e) {

            Toast.makeText(EmployeeViewcartActivity.this, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

        }

        LinearLayoutManager llm = new LinearLayoutManager(this);

        employeeViewCartAdapter = new EmployeeViewCartAdapter(EmployeeViewcartActivity.this, AG.getInstance().getEmployeeViewCartlist(), new EmployeeViewCartAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Log.d("ViewartActivity ", "array size 2dd : " + AG.getInstance().getEmployeeViewCartlist().size());
                if (AG.getInstance().getEmployeeViewCartlist().size() == 0){
                    Place_Order_emp.setVisibility(View.INVISIBLE);
                }else{
                    Place_Order_emp.setVisibility(View.VISIBLE);
                }



            }
        });

        view_cart_recyclerview_emp.setLayoutManager(llm);
        view_cart_recyclerview_emp.setAdapter(employeeViewCartAdapter);

        employeeViewCartAdapter.notifyDataSetChanged();
        if (AG.getInstance().getEmployeeViewCartlist().size() == 0){
            Place_Order_emp.setVisibility(View.INVISIBLE);
        }else{
            Place_Order_emp.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(EmployeeViewcartActivity.this, EmployeeHomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
        super.onBackPressed();
    }

}