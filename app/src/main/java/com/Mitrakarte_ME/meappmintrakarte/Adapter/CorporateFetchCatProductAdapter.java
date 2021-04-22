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
import com.Mitrakarte_ME.meappmintrakarte.Service.CorporateAddCartSub;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CorporateFetchCatProductAdapter extends RecyclerView.Adapter<CorporateFetchCatProductAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<Category> mcatList;
    LayoutInflater mInflater;
    //   private Integer viewingPage;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    static  public CorporateFetchCatProductAdapter.OnItemClickListener onClickListener;

    public CorporateFetchCatProductAdapter(Context c, ArrayList<Category> catList, CorporateFetchCatProductAdapter.OnItemClickListener listener) {
        this.mcatList = catList;
        this.mContext = c;
        onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.corporate_list_all_category_product, parent, false);

        return new CorporateFetchCatProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final   Category cat=mcatList.get(position);
        holder.textView.setText(mcatList.get(position).getPro_name());
        holder.textView2.setText(mcatList.get(position).getQuantity());
        holder.category_distriprice.setText(mcatList.get(position).getCustomer_discount_price());
        holder.tvccp_mrp.setText(mcatList.get(position).getMrp());

        //  holder.category_item_titleproductboxnew.setText(mcatList.get(position).getDistributor_pack());
        holder.tv_num4.setTag(position);
        holder.textView.setTag(position);
        holder.tv_plus4.setTag(position);
        holder.textView2.setTag(position);
        holder.imageView.setTag(position);
        holder.tv_minus4.setTag(position);
        //  holder.productdistriCasenew.setTag(position);
        holder.btnaddcartnewproductsubcat.setTag(position);
        holder.category_distriprice.setTag(position);
        // holder.category_item_titleproductboxnew.setTag(position);



//        int numbox = Integer.parseInt(holder.category_item_titleproductboxnew.getText().toString());
//        int numprice = Integer.parseInt(holder.category_distriprice.getText().toString());
//        numprice = numbox * numprice;

//        holder.category_item_titleproductboxnew.setText(String.valueOf(numbox));
//        holder.category_distriprice.setText(String.valueOf(numprice));

        holder.btnaddcartnewproductsubcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AG ht_inst = AG.getInstance();
                ht_inst.setCategory(cat);

                ht_inst.getCategory().setNewquantity(Integer.parseInt(holder.tv_num4.getText().toString()));
                ht_inst.getCategory().setDistributor_cost(holder.category_distriprice.getText().toString());

                ht_inst.getCategory().setHsn(mcatList.get(position).getHsn());
                ht_inst.getCategory().setSku(mcatList.get(position).getSku());
                ht_inst.getCategory().setFree(mcatList.get(position).getFree());
                ht_inst.getCategory().setCustomer_discount_percentage(mcatList.get(position).getCustomer_discount_percentage());


                //Todo: Add cart category based product
                AsyncTask<Context, Void, Integer> result2 = new CorporateAddCartSub().execute(mContext);
                try {

                    Toast.makeText(mContext, "Added to Cart Successfully", Toast.LENGTH_SHORT).show();

                    int code = result2.get();
                    System.out.println("jyo code" + code);
                } catch (Exception e) {

                    Toast.makeText(mContext, "Failed to Add Cart", Toast.LENGTH_SHORT).show();

                }




            }
        });


        holder.tv_minus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int num = Integer.parseInt(holder.tv_num4.getText().toString());
                num = 1;
                holder.tv_num4.setText(String.valueOf(num));

            }
        });

        holder.tv_plus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int num2 = Integer.parseInt(holder.tv_num4.getText().toString());
                num2 = num2 +1;


                holder.tv_num4.setText(String.valueOf(num2));

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
        public TextView textView,tv_plus4,tvccp_mrp,productdistriCasenew,category_distriprice,tv_minus4,category_item_titleproductboxnew,textView2;
        public CardView card_all_product;
        public Button btnaddcartnewproductsubcat;
        public EditText tv_num4;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            card_all_product = (CardView) itemView.findViewById(R.id.card_all_productproduct4_cor);

            textView = (TextView) itemView.findViewById(R.id.category_item_titleproduct4_cor);
            textView2= (TextView) itemView.findViewById(R.id.category_item_titleproductQuantity4_cor);
            imageView = (ImageView)itemView.findViewById(R.id.category_item_imageproduct4_cor);
            category_distriprice = (TextView)itemView.findViewById(R.id.category_distriprice_cor);
            tv_plus4 = (TextView)itemView.findViewById(R.id.tv_plus4_cor);
            tv_num4 = (EditText) itemView.findViewById(R.id.tv_num4_cor);
            // productdistriCasenew = (TextView)itemView.findViewById(R.id.productdistriCasenew);
            //  category_item_titleproductboxnew = (TextView)itemView.findViewById(R.id.category_item_titleproductboxnew);
            tv_minus4 = (TextView) itemView.findViewById(R.id.tv_minus4_cor);
            btnaddcartnewproductsubcat = (Button) itemView.findViewById(R.id.btnaddcartnewproductsubcat_cor);
            tvccp_mrp = (TextView) itemView.findViewById(R.id.tvccp_mrp);



        }
    }
}
