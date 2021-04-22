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

public class KeyAccountHomeAdapter extends RecyclerView.Adapter<KeyAccountHomeAdapter.ViewHolder> {

    private Context mContext;
    // Todo: the Category list that will be displayed
    private ArrayList<Category> mcatList;
    LayoutInflater mInflater;
    //   private Integer viewingPage;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    static  public OnItemClickListener onClickListener;

    public KeyAccountHomeAdapter(Context c, ArrayList<Category> catList, OnItemClickListener listener) {
        this.mcatList = catList;
        this.mContext = c;

        onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Todo: creating a view for Category with  xml layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.key_account_catogaries, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        // Todo: Getting the Category for the specified position
        Category cat=mcatList.get(position);
        holder.textView.setText(mcatList.get(position).getCatName());

        // Todo: setting Category values to textviews and imageView
        holder.textView.setTag(position);
        holder.imageView.setTag(position);


        try {
            System.out.println("jyo main image null"+cat.getCatImage());

            // Todo: Getting the Category Image
            Picasso.with(mContext).load(cat.getCatImage()).into(holder.imageView);
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


    // extends RecyclerView.Adapter<KeyAccountHomeAdapter.ViewHolder>
    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;
        public CardView card_all_product;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            card_all_product = (CardView) itemView.findViewById(R.id.card_all_product);

            textView = (TextView) itemView.findViewById(R.id.category_item_title);
            imageView = (ImageView)itemView.findViewById(R.id.category_item_image);

        }
    }

}
