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
import com.example.jinsu.work2.model.Contract;

import java.util.List;

public class EmployerContractAdapter extends RecyclerView.Adapter<EmployerContractAdapter.ViewHolder> {
    private List<Contract> list;
    private Context context;
    private EmployerContractAdapter.onClickCallback callback;

    public EmployerContractAdapter(Context context, List<Contract> list, EmployerContractAdapter.onClickCallback callback) {
        this.context = context;
        this.list = list;
        this.callback = callback;
    }

    public interface onClickCallback {
        public void onClick(int position);
    }


    @NonNull
    @Override
    public EmployerContractAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employer_calc_list, parent, false);
        EmployerContractAdapter.ViewHolder viewHolder = new EmployerContractAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EmployerContractAdapter.ViewHolder holder, int position) {
        if(!list.isEmpty()) {
            holder.name.setText(list.get(position).getName());
            holder.date.setText(list.get(position).getDate());
            holder.layout.setOnClickListener(v ->
            {
                callback.onClick(position);
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public  class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView date;
        public LinearLayout layout;


        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.employer_calc_list_txt_name);
            date = (TextView)itemView.findViewById(R.id.employer_calc_list_txt_date);
            layout = (LinearLayout) itemView.findViewById(R.id.employer_calc_list);

        }

    }

}
