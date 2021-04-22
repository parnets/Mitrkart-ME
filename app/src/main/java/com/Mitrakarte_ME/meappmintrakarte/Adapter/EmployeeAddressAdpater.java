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
import com.Mitrakarte_ME.meappmintrakarte.Service.EployeeDeleteDeliveryAddress;

import java.util.ArrayList;

public class EmployeeAddressAdpater  extends RecyclerView.Adapter<EmployeeAddressAdpater.ViewHolder>{


    private Context mContext;
    private ArrayList<Address> mcatList;
    LayoutInflater mInflater;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    static  public EmployeeAddressAdpater.OnItemClickListener onClickListener;

    public EmployeeAddressAdpater(Context c, ArrayList<Address> catList, EmployeeAddressAdpater.OnItemClickListener listener) {
        this.mcatList = catList;
        this.mContext = c;
        onClickListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_list_all_address, parent, false);

        return new EmployeeAddressAdpater.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Address cat= mcatList.get(position);

        holder.address_name_emp.setText(mcatList.get(position).getAdr_name_emp());
        holder.address_number_emp.setText(mcatList.get(position).getAdr_mobile_emp());
        holder.address_email_emp.setText(mcatList.get(position).getAdr_email_emp());


        holder.address_locality_emp.setText(mcatList.get(position).getAdr_locality_emp());
        holder.address_landmark_emp.setText(mcatList.get(position).getAdr_land_mark_emp());
        holder.address_address_emp.setText(mcatList.get(position).getAdr_address_emp());
        holder.address_city_emp.setText(mcatList.get(position).getAdr_city_emp());
        holder.address_state_emp.setText(mcatList.get(position).getAdr_state_emp());
        holder.address_country_emp.setText(mcatList.get(position).getAdr_country_emp());
        holder.address_pin_emp.setText(mcatList.get(position).getAdr_pin_emp());



        holder.address_number_emp.setTag(position);
        holder.address_email_emp.setTag(position);

        //Todo: Updated User address to cart
        holder.select_delivery_address_emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onItemClick(view, position);


            }
        });


        //Todo: Deleted User Delivery address - pending
        holder.btn_delete_delivery_address_emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AG ht_inst = AG.getInstance();
                ht_inst.setAddress(cat);

                //Todo: Deleted User Delivery address
                AsyncTask<Context, Void, Integer> result2 = new EployeeDeleteDeliveryAddress().execute(mContext);
                try {

                    Toast.makeText(mContext, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    mcatList.remove(position);
                    notifyDataSetChanged();
                    int code = result2.get();
                    System.out.println("jyo code" + code);
                } catch (Exception e) {

                    Toast.makeText(mContext, "Application Fail to Deleted", Toast.LENGTH_SHORT).show();

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
        public TextView address_name_emp, address_number_emp,address_email_emp, address_locality_emp, address_landmark_emp;
        public TextView address_address_emp, address_city_emp, address_state_emp, address_country_emp, address_pin_emp;
        public Button btn_delete_delivery_address_emp;
        public CardView address_all_emp;

        public Button select_delivery_address_emp;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            address_all_emp = (CardView) itemView.findViewById(R.id.address_all_emp);



            address_name_emp  = (TextView) itemView.findViewById(R.id.address_name_emp);
            address_number_emp = (TextView) itemView.findViewById(R.id.address_number_emp);
            address_email_emp = (TextView) itemView.findViewById(R.id.address_email_emp);
            address_locality_emp = (TextView)itemView.findViewById(R.id.address_locality_emp);
            address_landmark_emp  = (TextView) itemView.findViewById(R.id.address_landmark_emp);
            address_address_emp = (TextView)itemView.findViewById(R.id.address_address_emp);
            address_city_emp = (TextView) itemView.findViewById(R.id.address_city_emp);
            address_state_emp = (TextView) itemView.findViewById(R.id.address_state_emp);
            address_country_emp = (TextView)itemView.findViewById(R.id.address_country_emp);
            address_pin_emp = (TextView)itemView.findViewById(R.id.address_pin_emp);

            select_delivery_address_emp = (Button)itemView.findViewById(R.id.select_delivery_address_emp);
            btn_delete_delivery_address_emp = (Button)itemView.findViewById(R.id.btn_delete_delivery_address_emp);

        }
    }
}
