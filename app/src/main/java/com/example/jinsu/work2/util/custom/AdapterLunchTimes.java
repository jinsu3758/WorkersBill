package com.example.jinsu.work2.util.custom;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.model.WorkTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kasum on 2018-06-03.
 */

public class AdapterLunchTimes extends RecyclerView.Adapter<ViewHolderLunchTimePicker> {
    private List<WorkTime> times = new ArrayList<WorkTime>();
    private boolean readonly;

    public AdapterLunchTimes() {
    }

    @NonNull
    @Override
    public ViewHolderLunchTimePicker onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.component_worktime_picker, parent, false);
        ViewHolderLunchTimePicker vh = new ViewHolderLunchTimePicker(v, this);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLunchTimePicker holder, int position) {
        short st = times.get(position).getStart();
        short et = times.get(position).getEnd();

        holder.title.setText(R.string.lunchtime);
        holder.SetTime(st, et);
        holder.SetReadonly(readonly);
        /*
        holder.StartTime.setEnabled(!readonly);
        holder.StartTime.setFocusable(!readonly);

        holder.EndTime.setEnabled(!readonly);
        holder.EndTime.setFocusable(!readonly);*/
    }

    @Override
    public int getItemCount() {
        return times.size();
    }

    public void AddItem(WorkTime time) {
        times.add(time);
        notifyDataSetChanged();
    }

    public void AddItemWithoutNotify(WorkTime time) {
        times.add(time);
    }

    public void RemoveItem(int index) {
        times.remove(index);
        notifyDataSetChanged();
    }

    public List<WorkTime> GetLunchTimes(){
        return times;
    }

    public WorkTime GetLunchTime(int pos) {
        return times.get(pos);
    }

    public void Clear() {
        times.clear();
        notifyDataSetChanged();
    }

    public void SetReadonly(boolean readonly) {
        this.readonly = readonly;
    }
}
