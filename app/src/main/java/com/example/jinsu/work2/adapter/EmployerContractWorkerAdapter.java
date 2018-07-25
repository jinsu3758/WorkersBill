package com.example.jinsu.work2.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.example.jinsu.work2.R;
import com.example.jinsu.work2.model.Contract;

import java.util.List;

public class EmployerContractWorkerAdapter extends RecyclerView.Adapter<EmployerContractWorkerAdapter.ViewHolder> {
    private List<Contract> list;
    private EmployerContractWorkerAdapter.onClickCallback callback;
    private RequestManager glide;

    public EmployerContractWorkerAdapter(List<Contract> list, RequestManager requestManager,EmployerContractWorkerAdapter.onClickCallback callback) {
        glide = requestManager;
        this.list = list;
        this.callback = callback;
    }

    public interface onClickCallback {
        public void onClick(int position);
    }


    @NonNull
    @Override
    public EmployerContractWorkerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contract_worker_name, parent, false);
        EmployerContractWorkerAdapter.ViewHolder viewHolder = new EmployerContractWorkerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EmployerContractWorkerAdapter.ViewHolder holder, int position) {
        if(!list.isEmpty()) {
            holder.name.setText(list.get(position).getName());
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


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public LinearLayout layout;


        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.contract_worker_name);
            layout = (LinearLayout) itemView.findViewById(R.id.contract_worker_layout);

        }

    }

}
