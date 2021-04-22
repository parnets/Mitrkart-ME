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

import com.Mitrakarte_ME.meappmintrakarte.Model.Tables;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EmployeeOrderProductAdapter extends RecyclerView.Adapter<EmployeeOrderProductAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<Tables> mcatList;
    LayoutInflater mInflater;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    static  public EmployeeOrderProductAdapter.OnItemClickListener onClickListener;

    public EmployeeOrderProductAdapter(Context c, ArrayList<Tables> catList, EmployeeOrderProductAdapter.OnItemClickListener listener) {
        this.mcatList = catList;
        this.mContext = c;
        onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_order_product_list, parent, false);

        return new EmployeeOrderProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Tables cat= (Tables) mcatList.get(position);

        holder.order_product_name_emp.setText(mcatList.get(position).getOrder_pro_name_emp());
        holder.order_product_Quantity_emp.setText(mcatList.get(position).getSt_qnt_emp());
        holder.order_product_price_emp.setText(mcatList.get(position).getOrder_pro_price_emp());
        holder.order_id_emp.setText(mcatList.get(position).getOrder_id_emp());


        holder.order_product_img_emp.setTag(position);
        holder.order_product_name_emp.setTag(position);
        holder.order_product_Quantity_emp.setTag(position);



        try {
            System.out.println("jyo main image null"+cat.getCatImage());

            // Todo: Getting the Ordered Product Image
            Picasso.with(mContext).load(cat.getOrder_pro_img_emp()).into(holder.order_product_img_emp);
        } catch (Exception e) {

        }
    }

    @Override
    public int getItemCount() {return mcatList == null ? 0: mcatList.size();
    }

    public void setData(ArrayList<Tables>catList) {
        mcatList =  catList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView order_product_img_emp;
        public TextView order_product_name_emp,order_product_Quantity_emp, order_product_price_emp;
        public TextView order_id_emp;
        public CardView card_order_productview_emp;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            card_order_productview_emp = (CardView) itemView.findViewById(R.id.card_order_productview_emp);

            order_product_name_emp = (TextView) itemView.findViewById(R.id.order_product_name_emp);
            order_product_Quantity_emp = (TextView) itemView.findViewById(R.id.order_product_Quantity_emp);
            order_product_img_emp = (ImageView)itemView.findViewById(R.id.order_product_img_emp);
            order_product_price_emp  = (TextView) itemView.findViewById(R.id.order_product_price_emp);
            order_id_emp  = (TextView) itemView.findViewById(R.id.order_id_emp);


        }
    }

}
