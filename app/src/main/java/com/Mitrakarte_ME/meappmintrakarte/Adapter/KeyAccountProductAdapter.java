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
import com.Mitrakarte_ME.meappmintrakarte.Service.KeyAccountAddCart;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class KeyAccountProductAdapter extends RecyclerView.Adapter<KeyAccountProductAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<Category> mcatList;
    LayoutInflater mInflater;
    int pid;
    String pname,pquantity,price,p_image;


    //   private Integer viewingPage;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    static  public KeyAccountProductAdapter.OnItemClickListener onClickListener;

    public KeyAccountProductAdapter(Context c, ArrayList<Category> catList, OnItemClickListener listener) {
        this.mcatList = catList;
        this.mContext = c;
        onClickListener = listener;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.key_account_product_list, parent, false);

        return new KeyAccountProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final Category cat=mcatList.get(position);
        holder.card_productview.setTag(position);
        AG ht_inst = AG.getInstance();
        ht_inst.setCategory(cat);
        holder.textView.setText(mcatList.get(position).getPro_name());
      //  Picasso.with(mContext).load(cat.getImage()).into(holder.imageView);
        holder.product_pricetxt.setText(mcatList.get(position).getDistributor_retailer_invoice_price());
        //holder.productdistribox.setText(mcatList.get(position).getDistributor_pack());
        holder.productQuantity.setText(mcatList.get(position).getQuantity());
        holder.rlBtn.setTag(position);
        holder.product_pricetxt.setTag(position);
        holder.textView.setTag(position);
        holder.product_viewimgnew.setTag(position);
       // holder.btnaddcartnew.setTag(position);
       // holder.productdistribox.setTag(position);
        holder.productQuantity.setTag(position);

//        int numbox = Integer.parseInt(holder.productdistribox.getText().toString());
//        int numprice = Integer.parseInt(holder.product_pricetxt.getText().toString());
//        numprice = numbox * numprice;
//
//        holder.productdistribox.setText(String.valueOf(numbox));
//        holder.product_pricetxt.setText(String.valueOf(numprice));

        try {
            //System.out.println("jyo main image null"+cat.getCatImage());
            Picasso.with(mContext).load(cat.getImage()).into(holder.product_viewimgnew);
        } catch (Exception e) {

        }


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




                AsyncTask<Context, Void, Integer> result2 = new KeyAccountAddCart().execute(mContext);
                try {

                    Toast.makeText(mContext, "Added Successfully", Toast.LENGTH_SHORT).show();

                    int code = result2.get();
                    System.out.println("jyo code" + code);
                } catch (Exception e) {

                    Toast.makeText(mContext, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

                }


            }
        });


        try {
            //System.out.println("jyo main image null"+cat.getCatImage());
            Picasso.with(mContext).load(cat.getImage()).into(holder.product_viewimgnew);
        } catch (Exception e) {

        }

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onItemClick(v,position);
            }
        });

        holder.product_viewimgnew.setOnClickListener(new View.OnClickListener() {
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
        public ImageView product_viewimgnew;
        public TextView textView,productQuantity,product_pricetxt,productdistribox,tv_minus5,tv_plus5;
        public EditText tv_num;
        public CardView card_productview;
        public LinearLayout rlBtn;
        public Button btnaddcartnew;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            card_productview = (CardView) itemView.findViewById(R.id.card_productview);
          //  productdistribox = (TextView) itemView.findViewById(R.id.productdistribox);
            textView = (TextView) itemView.findViewById(R.id.product_viewtxt);
            product_pricetxt =itemView.findViewById(R.id.product_pricetxt);
            product_viewimgnew = (ImageView)itemView.findViewById(R.id.product_viewimgnew);
            btnaddcartnew = (Button) itemView.findViewById(R.id.btnaddcartnew);
            tv_plus5  =(TextView)itemView.findViewById(R.id.tv_plus5);
            tv_num = (EditText) itemView.findViewById(R.id.tv_num5);
            tv_minus5 = (TextView) itemView.findViewById(R.id.tv_minus5);
            rlBtn = (LinearLayout) itemView.findViewById(R.id.rlBtn);
            productQuantity = (TextView) itemView.findViewById(R.id.productQuantity);

        }
    }
}
