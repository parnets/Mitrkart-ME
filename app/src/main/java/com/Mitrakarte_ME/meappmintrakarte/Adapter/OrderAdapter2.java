package com.Mitrakarte_ME.meappmintrakarte.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.Mitrakarte_ME.meappmintrakarte.Activity.KeyAccount_OrderInvoiceActivity;
import com.Mitrakarte_ME.meappmintrakarte.Activity.OrderProductViewActivity;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.Model.Tables;
import com.Mitrakarte_ME.meappmintrakarte.R;

import java.util.ArrayList;

public class OrderAdapter2 extends RecyclerView.Adapter<OrderAdapter2.ViewHolder>{

    private Context mContext;
    private ArrayList<Tables> mcatList;
    LayoutInflater mInflater;
    String pdf_url;

  
    //   private Integer viewingPage;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    static  public OrderAdapter2.OnItemClickListener onClickListener;



    public OrderAdapter2(Context c, ArrayList<Tables> catList, OnItemClickListener listener) {
        this.mcatList = catList;
        this.mContext = c;
        onClickListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_list, parent, false);

        return new OrderAdapter2.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Tables cat= mcatList.get(position);
        String date = cat.getCreated_on_place_order();
        date = date.substring(0,10);
        holder.tv_orderID.setText(mcatList.get(position).getOrder_id());
        holder.t_order_date_k.setText(date);
        holder.t_order_total_amount_k.setText("₹"+mcatList.get(position).getGrand_price());




        //holder.btndeletecartfinal.setTag(position);
        holder.tv_orderID.setTag(position);
        holder.t_order_date_k.setTag(position);
        //  holder.imageView.setTag(position);



        holder.order_product_view_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(mContext , OrderProductViewActivity.class);
                mContext.startActivity(intent);



                AG.getInstance().setTables(mcatList.get(position));
                //Todo: View Order Produt Details  Key Account
//                AsyncTask<Context, Void, Integer> result2 = new OrderHistoryProductView().execute(mContext);
//                try {
//
//                    Toast.makeText(mContext, "Order Products Fetch Successfully", Toast.LENGTH_SHORT).show();
//                    int code = result2.get();
//                    System.out.println("jyo code" + code);
//                } catch (Exception e) {
//
//                    Toast.makeText(mContext, "Order Products Fail to fetch", Toast.LENGTH_SHORT).show();
//
//                }
            }
        });

        holder.mInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String send_order_id = mcatList.get(position).getOrder_id();
                System.out.println("send_order_id is " + send_order_id);

                Intent intent = new Intent(mContext, KeyAccount_OrderInvoiceActivity.class);
                intent.putExtra("send_order_id", send_order_id);
                mContext.startActivity(intent);

            }
        });

        holder.mDownloadInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String send_order_id = mcatList.get(position).getOrder_id();

                pdf_url = "http://mitrakart.com/crm/Orders/printkeyaccountinvoice/";
                pdf_url = pdf_url + send_order_id;

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdf_url));
                mContext.startActivity(browserIntent);
            }
        });




//        holder.btndeletecart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                AG ht_inst = AG.getInstance();
//                ht_inst.setCategory(cat);
//
//                //Todo: Delete cart Key Account
//                AsyncTask<Context, Void, Integer> result2 = new DeleteCart().execute(mContext);
//                try {
//
//                    Toast.makeText(mContext, "Deleted Successfully", Toast.LENGTH_SHORT).show();
//                    //Todo: Delete automatically
//                    mcatList.remove(position);
//                    notifyDataSetChanged();
//                    int code = result2.get();
//                    System.out.println("jyo code" + code);
//                } catch (Exception e) {
//
//                    Toast.makeText(mContext, "Application Fail to fetch", Toast.LENGTH_SHORT).show();
//
//                }
//
//           /*  Intent i = new Intent(mContext, ViewcartActivity.class);
//             mContext.startActivity(i);
//*/
//
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mcatList == null ? 0: mcatList.size();
    }

    public void setData(ArrayList<Tables> catList) {
        mcatList =  catList;
        notifyDataSetChanged();
    }
  

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView ordered_product_cardview;
        public TextView tv_orderID,t_order_date_k,order_product_view_k, t_order_total_amount_k, mInvoice, mDownloadInvoice;


        public ImageView imageView;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            ordered_product_cardview = (CardView)itemView.findViewById(R.id.ordered_product_cardview);

            tv_orderID = (TextView) itemView.findViewById(R.id.tv_orderID);
            t_order_date_k = (TextView) itemView.findViewById(R.id.t_order_date_k);
            order_product_view_k = (TextView) itemView.findViewById(R.id.order_product_view_k);
            t_order_total_amount_k = (TextView) itemView.findViewById(R.id.t_order_total_amount_k);
            mInvoice = (TextView) itemView.findViewById(R.id.order_invoice_k);
            mDownloadInvoice = (TextView) itemView.findViewById(R.id.order_download_invoice_k);


        }
    }
}
