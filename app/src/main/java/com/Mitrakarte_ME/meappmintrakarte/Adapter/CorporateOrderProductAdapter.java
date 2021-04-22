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

public class CorporateOrderProductAdapter extends RecyclerView.Adapter<CorporateOrderProductAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<Tables> mcatList;
    LayoutInflater mInflater;
    //   private Integer viewingPage;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    static  public CorporateOrderProductAdapter.OnItemClickListener onClickListener;

    public CorporateOrderProductAdapter(Context c, ArrayList<Tables> catList, CorporateOrderProductAdapter.OnItemClickListener listener) {
        this.mcatList = catList;
        this.mContext = c;
        onClickListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.corporate_order_product_list, parent, false);

        return new CorporateOrderProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Tables cat= (Tables) mcatList.get(position);

        holder.order_product_name_cor.setText(mcatList.get(position).getOrder_pro_name_cor());
        holder.order_product_Quantity_cor.setText(mcatList.get(position).getSt_qnt_cor());
        holder.order_product_price_cor.setText(mcatList.get(position).getOrder_pro_price_cor());
        holder.order_id_cor.setText(mcatList.get(position).getOrder_id_cor());


        holder.order_product_img_cor.setTag(position);
        holder.order_product_name_cor.setTag(position);
        holder.order_product_Quantity_cor.setTag(position);
        holder.order_product_price_cor.setTag(position);
      //  holder.order_id_cor.setTag(position);


        try {
            System.out.println("jyo main image null"+cat.getCatImage());

            // Todo: Getting the Ordered Product Image
            Picasso.with(mContext).load(cat.getOrder_pro_img_cor()).into(holder.order_product_img_cor);
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
        public ImageView order_product_img_cor;
        public TextView order_id_cor, order_product_name_cor,order_product_Quantity_cor, order_product_price_cor;
        public CardView card_order_productview_cor;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            card_order_productview_cor = (CardView) itemView.findViewById(R.id.card_order_productview_cor);

            order_product_img_cor = (ImageView)itemView.findViewById(R.id.order_product_img_cor);

            order_product_name_cor = (TextView) itemView.findViewById(R.id.order_product_name_cor);
            order_product_Quantity_cor = (TextView) itemView.findViewById(R.id.order_product_Quantity_cor);
            order_product_price_cor  = (TextView) itemView.findViewById(R.id.order_product_price_cor);
            order_id_cor  = (TextView) itemView.findViewById(R.id.order_id_cor);


        }
    }
}
