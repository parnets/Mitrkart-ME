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

public class CorporateSubcategoryAdapter extends RecyclerView.Adapter<CorporateSubcategoryAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Category> mcatList;
    LayoutInflater mInflater;
    //private Integer viewingPage;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    static  public CorporateSubcategoryAdapter.OnItemClickListener onClickListener;

    public CorporateSubcategoryAdapter(Context c, ArrayList<Category> catList, CorporateSubcategoryAdapter.OnItemClickListener listener) {
        this.mcatList = catList;
        this.mContext = c;
        onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.corporate_subcat, parent, false);

        return new CorporateSubcategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Category cat=mcatList.get(position);
        holder.textView.setText(mcatList.get(position).getSub_catName());

        holder.textView.setTag(position);
        holder.imageView.setTag(position);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              /*  Intent intent = new Intent(mContext, ProductViewActivity.class);
                intent.putExtra("id", mcatList.get(position).getCatId());
                intent.putExtra("subid",mcatList.get(position).getSub_catId());
                intent.putExtra("Position", position);
                mContext.startActivity(intent);*/

            }
        });


        try {
            //System.out.println("jyo main image null"+cat.getCatImage());
            Picasso.with(mContext).load(cat.getSub_catImage()).into(holder.imageView);
        } catch (Exception e) {

        }

        holder.textView.setOnClickListener(new View.OnClickListener() {
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
        public TextView textView;
        public CardView card_all_subcat_cor;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            card_all_subcat_cor = (CardView) itemView.findViewById(R.id.card_all_subcat_cor);

            textView = (TextView) itemView.findViewById(R.id.subcategory_item_name_cor);
            imageView = (ImageView)itemView.findViewById(R.id.subcategory_item_image_cor);


        }
    }
}
