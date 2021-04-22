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
import com.Mitrakarte_ME.meappmintrakarte.Service.CorporateDeleteCart;
import com.Mitrakarte_ME.meappmintrakarte.Service.UpdateCartCorporateHome;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CorporateViewCartAdapter extends RecyclerView.Adapter<CorporateViewCartAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<Category> mcatList;
    LayoutInflater mInflater;
    //   private Integer viewingPage;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    static  public CorporateViewCartAdapter.OnItemClickListener onClickListener;

    public CorporateViewCartAdapter(Context c, ArrayList<Category> catList, CorporateViewCartAdapter.OnItemClickListener listener) {
        this.mcatList = catList;
        this.mContext = c;
        onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.corporate_listallview_cart, parent, false);

        return new CorporateViewCartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final Category cat=mcatList.get(position);
        holder.textView1.setText(mcatList.get(position).getPro_name_cor());
        holder.textView2.setText(mcatList.get(position).getPrice_cor());
        holder.btn_deletecart_cor.setTag(position);
        holder.textView1.setTag(position);
        holder.textView2.setTag(position);
        holder.imageView.setTag(position);
        holder.tv_num10.setText(mcatList.get(position).getQuantity_cor());

        holder.btnUpdatecart_cor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AG ht_inst = AG.getInstance();
                ht_inst.setCategory(cat);

                ht_inst.getCategory().setQuantity_cor((holder.tv_num10.getText().toString()));
                //    ht_inst.getCategory().setDistributor_cost(holder.productdistriprice.getText().toString());

                //Todo: Update  Product to Cart
                AsyncTask<Context, Void, Integer> result2 = new UpdateCartCorporateHome().execute(mContext);
                try {

                    Toast.makeText(mContext, "Updated Successfully", Toast.LENGTH_SHORT).show();

                    int code = result2.get();
                    System.out.println("jyo code" + code);
                } catch (Exception e) {

                    Toast.makeText(mContext, " Fail to Updated", Toast.LENGTH_SHORT).show();

                }



            }
        });

        holder.tv_plus10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                int num2 = Integer.parseInt(holder.tv_num10.getText().toString());
                num2 = num2 +1;


                holder.tv_num10.setText(String.valueOf(num2));

                /*if(num <= 1 && num <= 0){

                    holder.rlBtn.setVisibility(view.GONE);
                    //textView.setVisibility(View.GONE);
                    //holder.tv_num.clearFocus();
                    }*/


            }
        });

        holder.tv_minus10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int num = Integer.parseInt(holder.tv_num10.getText().toString());
                num = num - 1;
                holder.tv_num10.setText(String.valueOf(num));
                if(num <1 ){
                    num = 1;
                    holder.tv_num10.setText(String.valueOf(num));

                    // holder.btnaddcartnew.setVisibility(view.VISIBLE);
                    //textView.setVisibility(View.GONE);//holder.tv_num.clearFocus();
                }
            }
        });

        holder.btn_deletecart_cor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AG ht_inst = AG.getInstance();
                ht_inst.setCategory(cat);

                //Todo: Delete cart Corporate
                AsyncTask<Context, Void, Integer> result2 = new CorporateDeleteCart().execute(mContext);
                try {

                    Toast.makeText(mContext, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    //Todo: Delete automatically
                    mcatList.remove(position);
                    notifyDataSetChanged();
                    int code = result2.get();
                    System.out.println("jyo code" + code);
                } catch (Exception e) {

                    Toast.makeText(mContext, " Fail to Delete", Toast.LENGTH_SHORT).show();

                }

           /*  Intent i = new Intent(mContext, ViewcartActivity.class);
             mContext.startActivity(i);
*/


            }
        });

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
        public TextView textView1,textView2, tv_minus10, tv_plus10;
        public CardView card_all_product;
        public Button btn_deletecart_cor, btnUpdatecart_cor;
        public EditText tv_num10;
        public LinearLayout rlBtn10;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            card_all_product = (CardView) itemView.findViewById(R.id.card_all_product6f_cor);

            textView1 = (TextView) itemView.findViewById(R.id.cart_item_title_cor);
            textView2 = (TextView) itemView.findViewById(R.id.cart_item_price_cor);
            imageView = (ImageView)itemView.findViewById(R.id.cart_item_image_cor);
            tv_num10 = (EditText)itemView.findViewById(R.id.tv_num10_cor);
            tv_plus10  = (TextView) itemView.findViewById(R.id.tv_plus10_cor);
            tv_minus10  = (TextView) itemView.findViewById(R.id.tv_minus10_cor);
            rlBtn10  = (LinearLayout) itemView.findViewById(R.id.rlBtn_cor);

            btn_deletecart_cor = (Button)itemView.findViewById(R.id.btn_deletecart_cor);
            btnUpdatecart_cor = (Button)itemView.findViewById(R.id.btnUpdatecart_cor);


        }
    }
}
