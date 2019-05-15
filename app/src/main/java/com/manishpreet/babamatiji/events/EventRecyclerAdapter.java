package com.manishpreet.babamatiji.events;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.manishpreet.babamatiji.Event;
import com.manishpreet.babamatiji.R;

import java.util.ArrayList;



class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.MyEventRecyclerAdapter> {

    private Context context;
ArrayList<Event> arrayList;
    public EventRecyclerAdapter (Context context, ArrayList<Event> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public MyEventRecyclerAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_list_single_item,viewGroup,false);
        return new MyEventRecyclerAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyEventRecyclerAdapter holder, int i) {
holder.textdate.setText(arrayList.get(i).getDate());
holder.textEvent.setText(arrayList.get(i).getEvent());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public void add(Event event, String id) {
        arrayList.add(event);
        notifyDataSetChanged();
    }
    class MyEventRecyclerAdapter extends RecyclerView.ViewHolder {
        TextView textEvent,textdate;
        public MyEventRecyclerAdapter(@NonNull View itemView) {
            super(itemView);
            textEvent=itemView.findViewById(R.id.tvEvents);
            textdate=itemView.findViewById(R.id.tvEventDate);
        }
    }
}
