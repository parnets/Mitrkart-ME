package com.Mitrakarte_ME.meappmintrakarte.Adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.Model.Category;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.AddCartCorporateHome;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CorporateFetchProductAdapter extends RecyclerView.Adapter<CorporateFetchProductAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<Category> mcatList;
    LayoutInflater mInflater;
    //   private Integer viewingPage;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }



    public CorporateFetchProductAdapter(Context c, ArrayList<Category> catList) {
        this.mcatList = catList;
        this.mContext = c;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listallproduct_home_corporate, parent, false);

        return new CorporateFetchProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final    Category cat=mcatList.get(position);
        holder.textView.setText(mcatList.get(position).getPro_name());
        holder.textView2.setText(mcatList.get(position).getQuantity());
        holder.productdistriprice.setText(mcatList.get(position).getCustomer_discount_price());
        holder.tvcch_mrp.setText(mcatList.get(position).getMrp());
        
        //  holder.category_item_titleproductbox.setText((mcatList.get(position).getDistributor_pack()));
        holder.btnaddcartnewproduct.setTag(position);
        holder.tv_num2.setTag(position);
        holder.textView.setTag(position);
        holder.textView2.setTag(position);
        holder.imageView.setTag(position);
        holder.tv_plus2.setTag(position);
        holder.tv_minus2.setTag(position);
        // holder.category_item_titleproductbox.setTag(position);
        holder.productdistriprice.setTag(position);


//        int numbox = Integer.parseInt(holder.category_item_titleproductbox.getText().toString());
//        int numprice = Integer.parseInt(holder.productdistriprice.getText().toString());
//        numprice = numbox * numprice;

//        holder.category_item_titleproductbox.setText(String.valueOf(numbox));
//        holder.productdistriprice.setText(String.valueOf(numprice));



        holder.btnaddcartnewproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AG ht_inst = AG.getInstance();
                ht_inst.setCategory(cat);

                ht_inst.getCategory().setNewquantity(Integer.parseInt(holder.tv_num2.getText().toString()));
                ht_inst.getCategory().setDistributor_cost(holder.productdistriprice.getText().toString());

                ht_inst.getCategory().setHsn(mcatList.get(position).getHsn());
                ht_inst.getCategory().setSku(mcatList.get(position).getSku());
                ht_inst.getCategory().setFree(mcatList.get(position).getFree());
                ht_inst.getCategory().setCustomer_discount_percentage(mcatList.get(position).getCustomer_discount_percentage());

                //Todo: Add Product to Cart Based on Mobile & Token
                AsyncTask<Context, Void, Integer> result2 = new AddCartCorporateHome().execute(mContext);
                try {

                    Toast.makeText(mContext, "Added to Cart Successfully", Toast.LENGTH_SHORT).show();

                    int code = result2.get();
                    System.out.println("jyo code" + code);
                } catch (Exception e) {

                    Toast.makeText(mContext, "Failed to Add Cart", Toast.LENGTH_SHORT).show();

                }



            }
        });

        holder.tv_plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int num2 = Integer.parseInt(holder.tv_num2.getText().toString());
                num2 = num2 +1;

                holder.tv_num2.setText(String.valueOf(num2));


            }
        });
        holder.tv_minus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int  num = 1;
                holder.tv_num2.setText(String.valueOf(num));
                if(num <1 ){
                    //holder.rlBtn.setVisibility(view.GONE);

                    // holder.btnaddcartnew.setVisibility(view.VISIBLE);
                    //textView.setVisibility(View.GONE);//holder.tv_num.clearFocus();
                }


            }
        });



        try {
            //System.out.println("jyo main image null"+cat.getCatImage());
            Picasso.with(mContext).load(cat.getImage()).into(holder.imageView);
        } catch (Exception e) {

        }



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
        public TextView textView,productdistriprice,textView2,tv_minus2,tv_plus2,tvcch_mrp,category_item_titleproductbox;
        public EditText tv_num2;
        public Button btnaddcartnewproduct;
        public CardView card_all_product;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            card_all_product = (CardView) itemView.findViewById(R.id.card_all_productall_cor);
            btnaddcartnewproduct = (Button) itemView.findViewById(R.id.btnaddcartnewproductall_cor);
            tv_minus2 = (TextView)itemView.findViewById(R.id.tv_minus2all_cor);
            tv_plus2 = (TextView)itemView.findViewById(R.id.tv_plus2all_cor);
            tv_num2 = (EditText)itemView.findViewById(R.id.tv_num2all_cor);
            productdistriprice = (TextView)itemView.findViewById(R.id.productdistripriceall_cor);
            textView = (TextView) itemView.findViewById(R.id.category_item_titleproductall_cor);
            // category_item_titleproductbox = (TextView) itemView.findViewById(R.id.category_item_titleproductboxall);
            textView2= (TextView) itemView.findViewById(R.id.category_item_productQuantityall_cor);
            imageView = (ImageView)itemView.findViewById(R.id.category_item_imageproductall_cor);
            tvcch_mrp= (TextView) itemView.findViewById(R.id.tvcch_mrp);
            

        }
    }
}
