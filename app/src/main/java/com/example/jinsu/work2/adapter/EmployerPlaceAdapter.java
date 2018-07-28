package com.example.jinsu.work2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.network.model.Company;

import java.util.ArrayList;

public class EmployerPlaceAdapter extends RecyclerView.Adapter<EmployerPlaceAdapter.ViewHolder> {
    private ArrayList<Company> list;
    private Context context;
    private onClickCallback callback;

    public EmployerPlaceAdapter(Context context, ArrayList<Company> list, onClickCallback callback) {
        this.context = context;
        this.list = list;
        this.callback = callback;
    }

    public interface onClickCallback {
        public void onClick(int position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employer_place, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(list.get(position).name);
        holder.address.setText(list.get(position).address);
        holder.phone.setText(list.get(position).phone);
        holder.owner.setText(list.get(position).employer_name);
        holder.layout.setOnClickListener(v ->
        {
            callback.onClick(position);
        });
        /*String start_date = list.get(position).getCoupon_start_date().substring(0, 10);
        String expired_date = list.get(position).getCoupon_expired_date().substring(0, 10);
        start_date = start_date.replaceAll("-", ".");
        expired_date = expired_date.replaceAll("-", ".");
        holder.txt_date.setText(start_date + " ~ " + expired_date);
        holder.txt_content.setText(String.valueOf(list.get(position).getCoupon_content()));
        holder.txt_brand.setText(list.get(position).getCoupon_brand());
        Glide.with(context).load(RetroClient.getInstance().getBASE_URL() + list.get(position).getCoupon_image()).into(holder.im_logo);
        holder.relativeLayout.setOnClickListener(v ->
        {
            callback.onPost(position);
        });*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView address;
        public TextView phone;
        public TextView owner;
        public LinearLayout layout;


        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.employer_place_txt_name);
            address = (TextView)itemView.findViewById(R.id.employer_place_txt_address);
            phone = (TextView)itemView.findViewById(R.id.employer_place_txt_phone);
            owner = (TextView)itemView.findViewById(R.id.employer_place_txt_owner);
            layout = (LinearLayout) itemView.findViewById(R.id.employer_place_layout_place);

        }

    }

}
