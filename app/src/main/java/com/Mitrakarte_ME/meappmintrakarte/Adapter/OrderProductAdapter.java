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

public class OrderProductAdapter extends RecyclerView.Adapter<OrderProductAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<Tables> mcatList;
    LayoutInflater mInflater;
    //   private Integer viewingPage;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    static  public OrderProductAdapter.OnItemClickListener onClickListener;

    public OrderProductAdapter(Context c, ArrayList<Tables> catList, OnItemClickListener listener) {
        this.mcatList = catList;
        this.mContext = c;
        onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_product_list, parent, false);

        return new OrderProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Tables cat= (Tables) mcatList.get(position);

        holder.order_product_name_k.setText(mcatList.get(position).getOrder_pro_name());
        holder.order_product_Quantity_k.setText(mcatList.get(position).getSt_qnt());
        holder.order_product_price_k.setText(mcatList.get(position).getOrder_pro_price());
        holder.order_id_k.setText(mcatList.get(position).getOrder_id());


        holder.order_product_img_k.setTag(position);
        holder.order_product_name_k.setTag(position);
       holder.order_product_Quantity_k.setTag(position);

//        setOrder_pro_img

        try {
            System.out.println("jyo main image null"+cat.getCatImage());

            // Todo: Getting the Ordered Product Image
            Picasso.with(mContext).load(cat.getOrder_pro_img()).into(holder.order_product_img_k);
        } catch (Exception e) {

        }
    }

    @Override
    public int getItemCount() {
        return mcatList == null ? 0: mcatList.size();
    }

    public void setData(ArrayList<Tables>catList) {
        mcatList =  catList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView order_product_img_k;
        public TextView order_product_name_k,order_product_Quantity_k, order_product_price_k;
        public TextView order_id_k;
        public CardView card_order_productview;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            card_order_productview = (CardView) itemView.findViewById(R.id.card_order_productview);

            order_product_name_k = (TextView) itemView.findViewById(R.id.order_product_name_k);
            order_product_Quantity_k = (TextView) itemView.findViewById(R.id.order_product_Quantity_k);
            order_product_img_k = (ImageView)itemView.findViewById(R.id.order_product_img_k);
            order_product_price_k  = (TextView) itemView.findViewById(R.id.order_product_price_k);
            order_id_k  = (TextView) itemView.findViewById(R.id.order_id_k);


        }
    }
}
