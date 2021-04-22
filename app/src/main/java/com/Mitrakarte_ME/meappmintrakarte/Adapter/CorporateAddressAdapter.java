package com.Mitrakarte_ME.meappmintrakarte.Adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;
import com.Mitrakarte_ME.meappmintrakarte.Model.Address;
import com.Mitrakarte_ME.meappmintrakarte.R;
import com.Mitrakarte_ME.meappmintrakarte.Service.DeleteCorporateDeliveryAddress;

import java.util.ArrayList;

public class CorporateAddressAdapter extends RecyclerView.Adapter<CorporateAddressAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<Address> mcatList;
    LayoutInflater mInflater;
    //   private Integer viewingPage;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    static  public CorporateAddressAdapter.OnItemClickListener onClickListener;

    public CorporateAddressAdapter(Context c, ArrayList<Address> catList, OnItemClickListener listener) {
        this.mcatList = catList;
        this.mContext = c;
        onClickListener = listener;
    }

    @NonNull
    @Override
    public CorporateAddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.corporate_list_all_address, parent, false);

        return new CorporateAddressAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CorporateAddressAdapter.ViewHolder holder, final int position) {

        final Address cat= mcatList.get(position);

        holder.address_name_cor.setText(mcatList.get(position).getAdr_name_cor());
        holder.address_number_cor.setText(mcatList.get(position).getAdr_mobile_cor());
        holder.address_email_cor.setText(mcatList.get(position).getAdr_email_cor());

        holder.address_locality_cor.setText(mcatList.get(position).getAdr_locality_cor());
        holder.address_landmark_cor.setText(mcatList.get(position).getAdr_land_mark_cor());
        holder.address_address_cor.setText(mcatList.get(position).getAdr_address_cor());
        holder.address_city_cor.setText(mcatList.get(position).getAdr_city_cor());
        holder.address_state_cor.setText(mcatList.get(position).getAdr_state_cor());
        holder.address_country_cor.setText(mcatList.get(position).getAdr_country_cor());
        holder.address_pin_cor.setText(mcatList.get(position).getAdr_pin_cor());



        //holder.btndeletecartfinal.setTag(position);
        holder.address_number_cor.setTag(position);
        holder.address_email_cor.setTag(position);
        //  holder.imageView.setTag(position);

        //Todo: Updated User address to cart
        holder.select_delivery_address_cor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onItemClick(view, position);


            }
        });


        //Todo: Deleted User Delivery address - pending
        holder.btn_delete_delivery_address_cor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AG ht_inst = AG.getInstance();
                ht_inst.setAddress(cat);

                //Todo: Deleted User Delivery address
                AsyncTask<Context, Void, Integer> result2 = new DeleteCorporateDeliveryAddress().execute(mContext);
                try {

                    Toast.makeText(mContext, "Deleted Address Successfully", Toast.LENGTH_SHORT).show();
                    mcatList.remove(position);
                    notifyDataSetChanged();
                    int code = result2.get();
                    System.out.println("jyo code" + code);
                } catch (Exception e) {

                    Toast.makeText(mContext, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

                }

           /*  Intent i = new Intent(mContext, ViewcartActivity.class);
             mContext.startActivity(i);
*/


            }
        });







    }

    @Override
    public int getItemCount() {
        return mcatList == null ? 0: mcatList.size();
    }

    public void setData(ArrayList<Address> catList) {
        mcatList =  catList;
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView address_name_cor, address_number_cor,address_email_cor, address_locality_cor, address_landmark_cor;
        public TextView address_address_cor, address_city_cor, address_state_cor, address_country_cor, address_pin_cor;
        public Button btn_delete_delivery_address_cor;
        public CardView address_all_cor;

        public Button select_delivery_address_cor;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            address_all_cor = (CardView) itemView.findViewById(R.id.card_all_product6f);


            address_name_cor  = (TextView) itemView.findViewById(R.id.address_name_cor);
            address_number_cor = (TextView) itemView.findViewById(R.id.address_number_cor);
            address_email_cor = (TextView) itemView.findViewById(R.id.address_email_cor);
            address_locality_cor = (TextView)itemView.findViewById(R.id.address_locality_cor);
            address_landmark_cor  = (TextView) itemView.findViewById(R.id.address_landmark_cor);
            address_address_cor = (TextView)itemView.findViewById(R.id.address_address_cor);
            address_city_cor = (TextView) itemView.findViewById(R.id.address_city_cor);
            address_state_cor = (TextView) itemView.findViewById(R.id.address_state_cor);
            address_country_cor = (TextView)itemView.findViewById(R.id.address_country_cor);
            address_pin_cor = (TextView)itemView.findViewById(R.id.address_pin_cor);

            select_delivery_address_cor = (Button)itemView.findViewById(R.id.select_delivery_address_cor);
            btn_delete_delivery_address_cor = (Button)itemView.findViewById(R.id.btn_delete_delivery_address_cor);

        }
    }
}