package com.example.jinsu.work2.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.example.jinsu.work2.R;
import com.example.jinsu.work2.model.Worker;

import java.util.List;

public class EmployerReqWorkerAdapter extends RecyclerView.Adapter<EmployerReqWorkerAdapter.ViewHolder> {
    private List<Worker> list;
    private EmployerReqWorkerAdapter.onClickCallback callback;
    private RequestManager glide;

    public EmployerReqWorkerAdapter(List<Worker> list, RequestManager requestManager, EmployerReqWorkerAdapter.onClickCallback callback) {
        glide = requestManager;
        this.list = list;
        this.callback = callback;
    }

    public interface onClickCallback {
        public void onClick(int position, Boolean code);
    }


    @NonNull
    @Override
    public EmployerReqWorkerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employer_req_worker, parent, false);
        EmployerReqWorkerAdapter.ViewHolder viewHolder = new EmployerReqWorkerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EmployerReqWorkerAdapter.ViewHolder holder, int position) {
        if(!list.isEmpty()) {
            holder.name.setText(list.get(position).getName());
            holder.btn_accept.setOnClickListener(v ->
            {
                callback.onClick(position,true);
            });
            holder.btn_reject.setOnClickListener(v ->
            {
                callback.onClick(position,false);
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView im;
        public Button btn_accept;
        public Button btn_reject;


        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.req_list_txt_name);
            im = (ImageView)itemView.findViewById(R.id.req_list_im);
            btn_accept = (Button)itemView.findViewById(R.id.req_list_btn_accept);
            btn_reject = (Button)itemView.findViewById(R.id.req_list_btn_reject);

        }

    }

}
