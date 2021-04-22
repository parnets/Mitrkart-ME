package com.Mitrakarte_ME.meappmintrakarte.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.Mitrakarte_ME.meappmintrakarte.Model.Category;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CoporateViewCartDeliveryAdapter extends RecyclerView.Adapter<CoporateViewCartDeliveryAdapter.ViewHolder>{


    private Context mContext;
    private ArrayList<Category> mcatList;
    LayoutInflater mInflater;
    //   private Integer viewingPage;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    static  public CoporateViewCartDeliveryAdapter.OnItemClickListener onClickListener;

    public CoporateViewCartDeliveryAdapter(Context c, ArrayList<Category> catList, CoporateViewCartDeliveryAdapter.OnItemClickListener listener) {
        this.mcatList = catList;
        this.mContext = c;
        onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.corporaet_listall_viewcart, parent, false);

        return new CoporateViewCartDeliveryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Category cat=mcatList.get(position);
        holder.textView1.setText(mcatList.get(position).getPro_name_cor());
        holder.textView2.setText(mcatList.get(position).getPrice_cor());
        holder.produt_total_Quantity_cor.setText(mcatList.get(position).getQuantity_cor());
        holder.produt_total_Price_cor.setText(mcatList.get(position).getPrice_cor());
        //holder.btndeletecartfinal.setTag(position);
        holder.textView1.setTag(position);
        holder.textView2.setTag(position);
        holder.produt_total_Quantity_cor.setTag(position);
        holder.produt_total_Price_cor.setTag(position);
        holder.imageView.setTag(position);

        //Todo: Calculating Total Price of Individual Product(eg 9 * 2 = 18)
        int totalQuantityCorporate = Integer.parseInt(holder.produt_total_Quantity_cor.getText().toString());
        int totalPriceCorporate = Integer.parseInt(holder.produt_total_Price_cor.getText().toString());
        totalPriceCorporate = totalPriceCorporate * totalQuantityCorporate;

        holder.produt_total_Price_cor.setText(String.valueOf(totalPriceCorporate));
        holder.produt_total_Quantity_cor.setText(String.valueOf(totalQuantityCorporate));





        try {
            //System.out.println("jyo main image null"+cat.getCatImage());
            Picasso.with(mContext).load(cat.getImage_cor()).into(holder.imageView);
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
        public TextView textView1,textView2, produt_total_Quantity_cor, produt_total_Price_cor;
        public CardView card_all_product6f;
        public Button btndeletecartfinal;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            card_all_product6f = (CardView) itemView.findViewById(R.id.card_all_product6f_cor);

            textView1 = (TextView) itemView.findViewById(R.id.category_item_title6f_cor);
            textView2 = (TextView) itemView.findViewById(R.id.category_item_title7f_Cor);
            produt_total_Quantity_cor = (TextView) itemView.findViewById(R.id.produt_total_Quantity_cor);
            produt_total_Price_cor = (TextView) itemView.findViewById(R.id.produt_total_Price_cor);
            imageView = (ImageView)itemView.findViewById(R.id.category_item_image6f_cor);
            // btndeletecartfinal = (Button)itemView.findViewById(R.id.btndeletecartfinal);

        }
    }
}
