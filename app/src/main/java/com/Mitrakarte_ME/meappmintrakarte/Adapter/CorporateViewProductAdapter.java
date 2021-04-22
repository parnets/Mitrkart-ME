package com.Mitrakarte_ME.meappmintrakarte.Adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.Model.Category;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.AddCartCorporate;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CorporateViewProductAdapter extends RecyclerView.Adapter<CorporateViewProductAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<Category> mcatList;
    LayoutInflater mInflater;
    int pid;
    String pname,pquantity,price,p_image;


    //   private Integer viewingPage;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    static  public CorporateViewProductAdapter.OnItemClickListener onClickListener;

    public CorporateViewProductAdapter(Context c, ArrayList<Category> catList, CorporateViewProductAdapter.OnItemClickListener listener) {
        this.mcatList = catList;
        this.mContext = c;
        onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.corporaet_productcatview, parent, false);

        return new CorporateViewProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final Category cat=mcatList.get(position);
        holder.textView.setText(mcatList.get(position).getPro_name());
        holder.product_pricetxt.setText(mcatList.get(position).getCustomer_discount_price());
        holder.tvccpv_mrp.setText(mcatList.get(position).getMrp());

        //  holder.productdistribox.setText(mcatList.get(position).getDistributor_pack());
        holder.productQuantity.setText(mcatList.get(position).getQuantity());
        holder.rlBtn.setTag(position);
        holder.product_pricetxt.setTag(position);
        holder.textView.setTag(position);
        holder.imageView.setTag(position);
        holder.btnaddcartnew.setTag(position);
        // holder.productdistribox.setTag(position);
        holder.productQuantity.setTag(position);

//        int numbox = Integer.parseInt(holder.productdistribox.getText().toString());
//        int numprice = Integer.parseInt(holder.product_pricetxt.getText().toString());
//        numprice = numbox * numprice;
//
//        holder.productdistribox.setText(String.valueOf(numbox));
//        holder.product_pricetxt.setText(String.valueOf(numprice));




        holder.tv_plus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                int num2 = Integer.parseInt(holder.tv_num.getText().toString());
                num2 = num2 +1;


                holder.tv_num.setText(String.valueOf(num2));

                /*if(num <= 1 && num <= 0){

                    holder.rlBtn.setVisibility(view.GONE);
                    //textView.setVisibility(View.GONE);
                    //holder.tv_num.clearFocus();
                    }*/


            }
        });

        holder.tv_minus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int num = Integer.parseInt(holder.tv_num.getText().toString());
                num = 1;
                holder.tv_num.setText(String.valueOf(num));
                if(num <1 ){
                    holder.rlBtn.setVisibility(view.GONE);

                    // holder.btnaddcartnew.setVisibility(view.VISIBLE);
                    //textView.setVisibility(View.GONE);//holder.tv_num.clearFocus();
                }
            }
        });



        holder.btnaddcartnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.rlBtn.setVisibility(view.VISIBLE);

               /* holder.rlBtn.setVisibility(view.VISIBLE);
                int num = Integer.parseInt(holder.tv_num.getText().toString());
                num = num - 1;
                holder.tv_num.setText(String.valueOf(num));
                if(num <=1 ){
                    holder.rlBtn.setVisibility(view.GONE);

                    // holder.btnaddcartnew.setVisibility(view.VISIBLE);
                    //textView.setVisibility(View.GONE);//holder.tv_num.clearFocus();
                    //
                    }

*/
                AG ht_inst = AG.getInstance();
                ht_inst.setCategory(cat);

                ht_inst.getCategory().setNewquantity(Integer.parseInt(holder.tv_num.getText().toString()));
                ht_inst.getCategory().setDistributor_cost(holder.product_pricetxt.getText().toString());

                ht_inst.getCategory().setHsn(mcatList.get(position).getHsn());
                ht_inst.getCategory().setSku(mcatList.get(position).getSku());
                ht_inst.getCategory().setFree(mcatList.get(position).getFree());
                ht_inst.getCategory().setCustomer_discount_percentage(mcatList.get(position).getCustomer_discount_percentage());


                //Todo : Add to Corporate Cart
                AsyncTask<Context, Void, Integer> result2 = new AddCartCorporate().execute(mContext);
                try {

                    Toast.makeText(mContext, "Added to Cart Successfully", Toast.LENGTH_SHORT).show();

                    int code = result2.get();
                    System.out.println("jyo code" + code);
                } catch (Exception e) {

                    Toast.makeText(mContext, "Failed to Add Cart", Toast.LENGTH_SHORT).show();

                }


            }
        });


        try {
            //System.out.println("jyo main image null"+cat.getCatImage());
            Picasso.with(mContext).load(cat.getImage()).into(holder.imageView);
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
        public TextView textView,productQuantity,product_pricetxt,productdistribox,tv_minus5,tv_plus5, tvccpv_mrp;
        public EditText tv_num;
        public CardView card_all_product;
        public LinearLayout rlBtn;
        public Button btnaddcartnew;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            card_all_product = (CardView) itemView.findViewById(R.id.card_productview_corporate);
            //productdistribox = (TextView) itemView.findViewById(R.id.productdistribox9);
            textView = (TextView) itemView.findViewById(R.id.product_viewtxt_cor);
            product_pricetxt =itemView.findViewById(R.id.product_pricetxt_cor);
            imageView = (ImageView)itemView.findViewById(R.id.product_viewimg_cor);
            btnaddcartnew = (Button) itemView.findViewById(R.id.btn_addcart_corporate);
            tv_plus5  =(TextView)itemView.findViewById(R.id.tv_plus9_cor);
            tv_num = (EditText) itemView.findViewById(R.id.tv_num9_cpor);
            tv_minus5 = (TextView) itemView.findViewById(R.id.tv_minus9_cor);
            rlBtn = (LinearLayout) itemView.findViewById(R.id.rlBtn9_corporate);
            productQuantity = (TextView) itemView.findViewById(R.id.product_Quantity_cor);
            tvccpv_mrp = (TextView) itemView.findViewById(R.id.tvccpv_mrp);

        }
    }
}
