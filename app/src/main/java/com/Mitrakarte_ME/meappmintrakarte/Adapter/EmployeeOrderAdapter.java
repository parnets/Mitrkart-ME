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

import com.Mitrakarte_ME.meappmintrakarte.Activity.EmployeeOrderProductViewActivity;
import com.Mitrakarte_ME.meappmintrakarte.Activity.Employee_OrderInvoiceActivity;
import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.Model.Tables;
import com.Mitrakarte_ME.meappmintrakarte.R;

import java.util.ArrayList;

public class EmployeeOrderAdapter extends RecyclerView.Adapter<EmployeeOrderAdapter.ViewHolder>{


    private Context mContext;
    private ArrayList<Tables> mcatList;
    LayoutInflater mInflater;
    String pdf_url;


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    static  public EmployeeOrderAdapter.OnItemClickListener onClickListener;



    public EmployeeOrderAdapter(Context c, ArrayList<Tables> catList, EmployeeOrderAdapter.OnItemClickListener listener) {
        this.mcatList = catList;
        this.mContext = c;
        onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_my_order_list, parent, false);

        return new EmployeeOrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Tables cat= mcatList.get(position);
        String date = cat.getCreated_on_place_order_emp();
        date = date.substring(0,10);
        holder.tv_orderID_emp.setText(mcatList.get(position).getOrder_id_emp());
        holder.tv_order_date_emp.setText(date);
        holder.tv_order_total_amount_emp.setText("₹"+mcatList.get(position).getGrand_price_emp());


        holder.tv_orderID_emp.setTag(position);
        holder.tv_order_date_emp.setTag(position);
        holder.tv_order_total_amount_emp.setTag(position);



        holder.order_product_view_emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Todo: View Order Produt Details  Key Account
                Intent intent = new Intent(mContext , EmployeeOrderProductViewActivity.class);
                mContext.startActivity(intent);

                AG.getInstance().setTables(mcatList.get(position));


            }
        });


        holder.mInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String send_order_id = mcatList.get(position).getOrder_id_emp();
                System.out.println("send_order_id is " + send_order_id);

                Intent intent = new Intent(mContext, Employee_OrderInvoiceActivity.class);
                intent.putExtra("send_order_id", send_order_id);
                mContext.startActivity(intent);

            }
        });


        holder.mDownloadInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String send_order_id = mcatList.get(position).getOrder_id_emp();

                pdf_url = "http://mitrakart.com/crm/Orders/printemployeeinvoice/";
                pdf_url = pdf_url + send_order_id;

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdf_url));
                mContext.startActivity(browserIntent);
            }
        });


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

        public CardView ordered_product_cardview_emp;
        public TextView tv_orderID_emp,tv_order_date_emp,tv_order_total_amount_emp, order_product_view_emp, mInvoice, mDownloadInvoice;


        public ImageView imageView;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            ordered_product_cardview_emp = (CardView)itemView.findViewById(R.id.ordered_product_cardview_emp);

            tv_orderID_emp = (TextView) itemView.findViewById(R.id.tv_orderID_emp);
            tv_order_date_emp = (TextView) itemView.findViewById(R.id.tv_order_date_emp);
            tv_order_total_amount_emp = (TextView) itemView.findViewById(R.id.tv_order_total_amount_emp);
            order_product_view_emp = (TextView) itemView.findViewById(R.id.order_product_view_emp);
            mInvoice = (TextView) itemView.findViewById(R.id.order_invoice_emp);
            mDownloadInvoice = (TextView) itemView.findViewById(R.id.order_download_invoice_emp);

        }
    }
}
