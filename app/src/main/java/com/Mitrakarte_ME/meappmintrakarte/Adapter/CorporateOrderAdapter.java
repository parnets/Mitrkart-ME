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

import com.Mitrakarte_ME.meappmintrakarte.Activity.CorporateOrderProductViewActivity;
import com.Mitrakarte_ME.meappmintrakarte.Activity.Corporate_OrderInvoiceActivity;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.Model.Tables;
import com.Mitrakarte_ME.meappmintrakarte.R;

import java.util.ArrayList;

public class CorporateOrderAdapter extends RecyclerView.Adapter<CorporateOrderAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<Tables> mcatList;
    LayoutInflater mInflater;
    String pdf_url;

    //   private Integer viewingPage;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    static  public CorporateOrderAdapter.OnItemClickListener onClickListener;



    public CorporateOrderAdapter(Context c, ArrayList<Tables> catList, CorporateOrderAdapter.OnItemClickListener listener) {
        this.mcatList = catList;
        this.mContext = c;
        onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.corporate_my_order_list, parent, false);

        return new CorporateOrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Tables cat= mcatList.get(position);
        String date = cat.getCreated_on_place_order_cor();
        date = date.substring(0,10);
        holder.tv_orderID__cor.setText(mcatList.get(position).getOrder_id_cor());
        holder.t_order_date__cor.setText(date);
        holder.t_order_total_amount_cor.setText("₹"+mcatList.get(position).getGrand_price_cor());




        //holder.btndeletecartfinal.setTag(position);
        holder.tv_orderID__cor.setTag(position);
        holder.t_order_date__cor.setTag(position);
        holder.t_order_total_amount_cor.setTag(position);
        //  holder.imageView.setTag(position);



        holder.order_product_view__cor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Todo: View Order Produt Details  Key Account
                Intent intent = new Intent(mContext , CorporateOrderProductViewActivity.class);
                mContext.startActivity(intent);

                AG.getInstance().setTables(mcatList.get(position));


            }
        });

        holder.mInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String send_order_id = mcatList.get(position).getOrder_id_cor();
                System.out.println("send_order_id is " + send_order_id);

                Intent intent = new Intent(mContext, Corporate_OrderInvoiceActivity.class);
                intent.putExtra("send_order_id", send_order_id);
                mContext.startActivity(intent);

            }
        });


        holder.mDownloadInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String send_order_id = mcatList.get(position).getOrder_id_cor();

                pdf_url = "http://mitrakart.com/crm/Orders/printcorporateinvoice/";
                pdf_url = pdf_url + send_order_id;

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdf_url));
                mContext.startActivity(browserIntent);
            }
        });

    }

    @Override
    public int getItemCount() {return mcatList == null ? 0: mcatList.size();
    }

    public void setData(ArrayList<Tables> catList) {
        mcatList =  catList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView ordered_product_cardview;
        public TextView tv_orderID__cor,t_order_date__cor,t_order_total_amount_cor, order_product_view__cor, mInvoice, mDownloadInvoice;


        public ImageView imageView;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            ordered_product_cardview = (CardView)itemView.findViewById(R.id.ordered_product_cardview);

            tv_orderID__cor = (TextView) itemView.findViewById(R.id.tv_orderID__cor);
            t_order_date__cor = (TextView) itemView.findViewById(R.id.t_order_date__cor);
            t_order_total_amount_cor = (TextView) itemView.findViewById(R.id.t_order_total_amount_cor);
           order_product_view__cor = (TextView) itemView.findViewById(R.id.order_product_view__cor);
            mInvoice = (TextView) itemView.findViewById(R.id.order_invoice_cor);
            mDownloadInvoice = (TextView) itemView.findViewById(R.id.order_download_invoice_cor);


        }
    }
}
