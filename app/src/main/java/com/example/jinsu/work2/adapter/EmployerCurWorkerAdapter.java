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

public class EmployerCurWorkerAdapter extends RecyclerView.Adapter<EmployerCurWorkerAdapter.ViewHolder> {
    private List<Worker> list;
    private EmployerCurWorkerAdapter.onClickCallback callback;
    private RequestManager glide;

    public EmployerCurWorkerAdapter(List<Worker> list, RequestManager requestManager, EmployerCurWorkerAdapter.onClickCallback callback) {
        glide = requestManager;
        this.list = list;
        this.callback = callback;
    }

    public interface onClickCallback {
        public void onClick(int position);
    }


    @NonNull
    @Override
    public EmployerCurWorkerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employer_cur_worker, parent, false);
        EmployerCurWorkerAdapter.ViewHolder viewHolder = new EmployerCurWorkerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EmployerCurWorkerAdapter.ViewHolder holder, int position) {
        if(!list.isEmpty()) {
            holder.name.setText(list.get(position).getName());
            holder.btn_out.setOnClickListener(v ->
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
        public ImageView im;
        public Button btn_out;


        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.cur_list_txt_name);
            im = (ImageView)itemView.findViewById(R.id.cur_list_im);
            btn_out = (Button)itemView.findViewById(R.id.cur_list_btn_out);

        }

    }

}
