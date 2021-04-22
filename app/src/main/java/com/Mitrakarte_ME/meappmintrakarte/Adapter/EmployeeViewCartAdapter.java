package com.Mitrakarte_ME.meappmintrakarte.Adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.Model.Category;
import com.Mitrakarte_ME.meappmintrakarte.Model.EmployeeDeleteCart;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.EployeeUpdateCart;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EmployeeViewCartAdapter extends RecyclerView.Adapter<EmployeeViewCartAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<Category> mcatList;
    LayoutInflater mInflater;
    //   private Integer viewingPage;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    static  public EmployeeViewCartAdapter.OnItemClickListener onClickListener;

    public EmployeeViewCartAdapter(Context c, ArrayList<Category> catList, EmployeeViewCartAdapter.OnItemClickListener listener) {
        this.mcatList = catList;
        this.mContext = c;
        onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_listallview_cart, parent, false);

        return new EmployeeViewCartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final Category cat=mcatList.get(position);
        holder.cart_item_title_Emp.setText(mcatList.get(position).getPro_name_emp());
        holder.cart_item_price_Emp.setText("Price ₹"+mcatList.get(position).getPrice_emp());
        holder.btn_deletecart_Emp.setTag(position);
        holder.cart_item_title_Emp.setTag(position);
        holder.cart_item_price_Emp.setTag(position);
        holder.cart_item_image_Emp.setTag(position);
        holder.tv_num10_Emp.setText(mcatList.get(position).getQuantity_emp());

        holder.btnUpdatecart_emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AG ht_inst = AG.getInstance();
                ht_inst.setCategory(cat);

                ht_inst.getCategory().setNewquantity_emp(Integer.parseInt(holder.tv_num10_Emp.getText().toString()));


                //Todo: Update  Product to Cart
                AsyncTask<Context, Void, Integer> result2 = new EployeeUpdateCart().execute(mContext);
                try {

                    Toast.makeText(mContext, "Updated Successfully", Toast.LENGTH_SHORT).show();

                    int code = result2.get();
                    System.out.println("jyo code" + code);
                } catch (Exception e) {

                    Toast.makeText(mContext, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

                }



            }
        });

        holder.tv_plus10_Emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int num2 = Integer.parseInt(holder.tv_num10_Emp.getText().toString());
                num2 = num2 +1;

                holder.tv_num10_Emp.setText(String.valueOf(num2));



            }
        });

        holder.tv_minus10_Emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int num = Integer.parseInt(holder.tv_num10_Emp.getText().toString());
                num = num - 1;
                holder.tv_num10_Emp.setText(String.valueOf(num));
                if(num <1 ){
                    num = 1;
                    holder.tv_num10_Emp.setText(String.valueOf(num));


                }
            }
        });

        holder.btn_deletecart_Emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AG ht_inst = AG.getInstance();
                ht_inst.setCategory(cat);

                //Todo: Delete cart Corporate
                AsyncTask<Context, Void, Integer> result2 = new EmployeeDeleteCart().execute(mContext);
                try {

                    Toast.makeText(mContext, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    //Todo: Delete automatically
                    mcatList.remove(position);
                    notifyDataSetChanged();
                    int code = result2.get();
                    System.out.println("jyo code" + code);
                } catch (Exception e) {

                    Toast.makeText(mContext, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

                }

            }
        });

        try {
            //System.out.println("jyo main image null"+cat.getCatImage());
            Picasso.with(mContext).load(cat.getImage_emp()).into(holder.cart_item_image_Emp);
        } catch (Exception e) {

        }

        holder.cart_item_title_Emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onItemClick(v,position);
            }
        });

        holder.cart_item_image_Emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onItemClick(v, position);


            }
        });

    }

    @Override
    public int getItemCount() {
        return mcatList == null ? 0: mcatList.size();
    }

    public void setData(ArrayList<Category>catList) {
        mcatList =  catList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView cart_item_image_Emp;
        public TextView cart_item_title_Emp,cart_item_price_Emp, tv_minus10_Emp, tv_plus10_Emp;
        public CardView card_all_product;
        public Button btn_deletecart_Emp, btnUpdatecart_emp;
        public EditText tv_num10_Emp;
        public LinearLayout rlBtn_Emp;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            card_all_product = (CardView) itemView.findViewById(R.id.card_all_viewcart_Emp);

            cart_item_title_Emp = (TextView) itemView.findViewById(R.id.cart_item_title_Emp);
            cart_item_price_Emp = (TextView) itemView.findViewById(R.id.cart_item_price_Emp);
            cart_item_image_Emp = (ImageView)itemView.findViewById(R.id.cart_item_image_Emp);

            tv_num10_Emp = (EditText)itemView.findViewById(R.id.tv_num10_Emp);
            tv_plus10_Emp  = (TextView) itemView.findViewById(R.id.tv_plus10_Emp);
            tv_minus10_Emp  = (TextView) itemView.findViewById(R.id.tv_minus10_Emp);
            rlBtn_Emp  = (LinearLayout) itemView.findViewById(R.id.rlBtn_Emp);

            btn_deletecart_Emp = (Button)itemView.findViewById(R.id.btn_deletecart_Emp);
            btnUpdatecart_emp  = (Button)itemView.findViewById(R.id.btnUpdatecart_emp);


        }
    }
}
