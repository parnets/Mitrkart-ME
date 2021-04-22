package com.Mitrakarte_ME.meappmintrakarte.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.Mitrakarte_ME.meappmintrakarte.Model.Category;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EmployeeViewCartDeliveryAdapter extends RecyclerView.Adapter<EmployeeViewCartDeliveryAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<Category> mcatList;
    LayoutInflater mInflater;


    //   private Integer viewingPage;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    static  public EmployeeViewCartDeliveryAdapter.OnItemClickListener onClickListener;

    public EmployeeViewCartDeliveryAdapter(Context c, ArrayList<Category> catList, EmployeeViewCartDeliveryAdapter.OnItemClickListener listener) {
        this.mcatList = catList;
        this.mContext = c;
        onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_listall_viewcart, parent, false);

        return new EmployeeViewCartDeliveryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Category cat=mcatList.get(position);
        holder.textView1.setText(mcatList.get(position).getPro_name_emp());
        holder.textView2.setText(mcatList.get(position).getPrice_emp());
        holder.produt_total_Quantity_emp.setText(mcatList.get(position).getQuantity_emp());
        holder.produt_total_Price_emp.setText(mcatList.get(position).getPrice_emp());

        holder.textView1.setTag(position);
        holder.textView2.setTag(position);
        holder.produt_total_Quantity_emp.setTag(position);
        holder.produt_total_Price_emp.setTag(position);
        holder.imageView.setTag(position);


        //Todo: Calculating Total Price of Individual Product(eg 9 * 2 = 18)
        int totalQuantityEmp = Integer.parseInt(holder.produt_total_Quantity_emp.getText().toString());
        int totalPriceEmp = Integer.parseInt(holder.produt_total_Price_emp.getText().toString());
        totalPriceEmp = totalPriceEmp * totalQuantityEmp;

        holder.produt_total_Price_emp.setText(String.valueOf(totalPriceEmp));
        holder.produt_total_Quantity_emp.setText(String.valueOf(totalQuantityEmp));



        try {
            //System.out.println("jyo main image null"+cat.getCatImage());
            Picasso.with(mContext).load(cat.getImage_emp()).into(holder.imageView);
        } catch (Exception e) {

        }

        holder.textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onItemClick(v,position);
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
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
        public ImageView imageView;
        public TextView textView1,textView2, produt_total_Quantity_emp, produt_total_Price_emp;
        public CardView card_all_product6f;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            card_all_product6f = (CardView) itemView.findViewById(R.id.card_all_product6f_Emp);

            textView1 = (TextView) itemView.findViewById(R.id.category_item_title6f_Emp);
            textView2 = (TextView) itemView.findViewById(R.id.category_item_title7f_Emp);
            produt_total_Quantity_emp = (TextView) itemView.findViewById(R.id.produt_total_Quantity_emp);
            produt_total_Price_emp  = (TextView) itemView.findViewById(R.id.produt_total_Price_emp);

            imageView = (ImageView)itemView.findViewById(R.id.category_item_image6f_Emp);

        }
    }
}
