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
import com.Mitrakarte_ME.meappmintrakarte.Service.DeleteDeliveryAddress;

import java.util.ArrayList;

public class AddressAdpater extends RecyclerView.Adapter<AddressAdpater.ViewHolder>{

    private Context mContext;
    private ArrayList<Address> mcatList;
    LayoutInflater mInflater;
    //   private Integer viewingPage;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    static  public AddressAdpater.OnItemClickListener onClickListener;

    public AddressAdpater(Context c, ArrayList<Address> catList, OnItemClickListener listener) {
        this.mcatList = catList;
        this.mContext = c;
        onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.key_list_all_address, parent, false);

        return new AddressAdpater.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final Address cat= mcatList.get(position);

        holder.address_name_k.setText(mcatList.get(position).getAdr_name_k());
        holder.address_number_k.setText(mcatList.get(position).getAdr_mobile_k());
        holder.address_email_k.setText(mcatList.get(position).getAdr_email_k());


        holder.address_locality_k.setText(mcatList.get(position).getAdr_locality_k());
        holder.address_landmark_k.setText(mcatList.get(position).getAdr_land_mark_k());
        holder.address_address_k.setText(mcatList.get(position).getAdr_address_k());
        holder.address_city_k.setText(mcatList.get(position).getAdr_city_k());
        holder.address_state_k.setText(mcatList.get(position).getAdr_state_k());
        holder.address_country_k.setText(mcatList.get(position).getAdr_country_k());
        holder.address_pin_k.setText(mcatList.get(position).getAdr_pin_k());



        holder.address_number_k.setTag(position);
        holder.address_email_k.setTag(position);

        //Todo: Updated User address to cart
        holder.select_delivery_address_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onItemClick(view, position);


            }
        });


        //Todo: Deleted User Delivery address - pending
        holder.btn_delete_delivery_address_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AG ht_inst = AG.getInstance();
                ht_inst.setAddress(cat);

                //Todo: Deleted User Delivery address
                AsyncTask<Context, Void, Integer> result2 = new DeleteDeliveryAddress().execute(mContext);
                try {

                    Toast.makeText(mContext, "Address Deleted Successfully", Toast.LENGTH_SHORT).show();
                    mcatList.remove(position);
                    notifyDataSetChanged();
                    int code = result2.get();
                    System.out.println("jyo code" + code);
                } catch (Exception e) {

                    Toast.makeText(mContext, "Application Fail to fetch", Toast.LENGTH_SHORT).show();

                }



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
        public TextView address_name_k, address_number_k,address_email_k, address_locality_k, address_landmark_k;
        public TextView address_address_k, address_city_k, address_state_k, address_country_k, address_pin_k;
        public Button btn_delete_delivery_address_k;
        public CardView address_all_k;

        public Button select_delivery_address_k;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            address_all_k = (CardView) itemView.findViewById(R.id.card_all_product6f);



            address_name_k  = (TextView) itemView.findViewById(R.id.address_name_k);
            address_number_k = (TextView) itemView.findViewById(R.id.address_number_k);
            address_email_k = (TextView) itemView.findViewById(R.id.address_email_k);
            address_locality_k = (TextView)itemView.findViewById(R.id.address_locality_k);
            address_landmark_k  = (TextView) itemView.findViewById(R.id.address_landmark_k);
            address_address_k = (TextView)itemView.findViewById(R.id.address_address_k);
            address_city_k = (TextView) itemView.findViewById(R.id.address_city_k);
            address_state_k = (TextView) itemView.findViewById(R.id.address_state_k);
            address_country_k = (TextView)itemView.findViewById(R.id.address_country_k);
            address_pin_k = (TextView)itemView.findViewById(R.id.address_pin_k);

            select_delivery_address_k = (Button)itemView.findViewById(R.id.select_delivery_address_k);
            btn_delete_delivery_address_k = (Button)itemView.findViewById(R.id.btn_delete_delivery_address_k);

        }
    }
}
