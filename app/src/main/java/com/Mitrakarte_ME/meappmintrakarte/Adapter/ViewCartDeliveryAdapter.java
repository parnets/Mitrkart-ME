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

public class ViewCartDeliveryAdapter extends RecyclerView.Adapter<ViewCartDeliveryAdapter.ViewHolder>{


    private Context mContext;
    private ArrayList<Category> mcatList;
    LayoutInflater mInflater;
    //   private Integer viewingPage;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    static  public ViewCartDeliveryAdapter.OnItemClickListener onClickListener;

    public ViewCartDeliveryAdapter(Context c, ArrayList<Category> catList, ViewCartDeliveryAdapter.OnItemClickListener listener) {
        this.mcatList = catList;
        this.mContext = c;
        onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listallviewcartnew, parent, false);

        return new ViewCartDeliveryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Category cat=mcatList.get(position);
        holder.textView1.setText(mcatList.get(position).getPro_name());
        holder.textView2.setText(mcatList.get(position).getPrice());
        holder.produt_total_Price_k.setText(mcatList.get(position).getPrice());
        holder.produt_total_Quantity_k.setText(mcatList.get(position).getQuantity());

        //holder.btndeletecartfinal.setTag(position);
        holder.textView1.setTag(position);
        holder.textView2.setTag(position);
        holder.produt_total_Price_k.setTag(position);
        holder.imageView.setTag(position);



//Todo: Calculating Total Price of Individual Product(eg 9 * 2 = 18)
        int totalQuantity = Integer.parseInt(holder.produt_total_Quantity_k.getText().toString());
        int totalPrice = Integer.parseInt(holder.produt_total_Price_k.getText().toString());
        totalPrice = totalPrice * totalQuantity;

        holder.produt_total_Price_k.setText(String.valueOf(totalPrice));
        holder.produt_total_Quantity_k.setText(String.valueOf(totalQuantity));



      /*  holder.btndeletecartfinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AG ht_inst = AG.getInstance();
                ht_inst.setCategory(cat);

                AsyncTask<Context, Void, Integer> result2 = new DeleteCart().execute(mContext);
                try {

                    Toast.makeText(mContext, "Deleted Successfully", Toast.LENGTH_SHORT).show();

                    int code = result2.get();
                    System.out.println("jyo code" + code);
                } catch (Exception e) {

                    Toast.makeText(mContext, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

                }

           *//*  Intent i = new Intent(mContext, ViewcartActivity.class);
             mContext.startActivity(i);
*//*


            }
        });*/

        try {
            //System.out.println("jyo main image null"+cat.getCatImage());
            Picasso.with(mContext).load(cat.getImage()).into(holder.imageView);
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
        public TextView textView1,textView2, produt_total_Quantity_k, produt_total_Price_k;
        public CardView card_all_product6f;
        public Button btndeletecartfinal;
        
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            card_all_product6f = (CardView) itemView.findViewById(R.id.card_all_product6f);

            textView1 = (TextView) itemView.findViewById(R.id.category_item_title6f);
            textView2 = (TextView) itemView.findViewById(R.id.category_item_title7f);
            imageView = (ImageView)itemView.findViewById(R.id.category_item_image6f);
            produt_total_Quantity_k  = (TextView) itemView.findViewById(R.id.produt_total_Quantity_k);
            produt_total_Price_k  = (TextView) itemView.findViewById(R.id.produt_total_Price_k);
            // btndeletecartfinal = (Button)itemView.findViewById(R.id.btndeletecartfinal);

        }
    }
}
