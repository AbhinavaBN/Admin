package com.developers.spicknspan.admin.recycler_view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.developers.spicknspan.admin.R;

import java.util.List;

public class recycler_view_adapter extends RecyclerView.Adapter<recycler_view_adapter.MyViewHolder> {
    private List<String> names;
    private List<String> ph_nos;
    private LayoutInflater mInflater;

    public recycler_view_adapter(Context context, List<String> names, List<String> ph_nos) {
        this.mInflater = LayoutInflater.from(context);
        this.names = names;
        this.ph_nos = ph_nos;
    }
    @NonNull
    @Override
    public recycler_view_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return names == null ? 0 : names.size();
    }

    @Override
    public void onBindViewHolder(@NonNull recycler_view_adapter.MyViewHolder holder, final int position) {
        holder.myNameView.setText(names.get(position));
        holder.myPhoneView.setText(ph_nos.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("onclick", String.valueOf(position));
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView myNameView, myPhoneView;

        MyViewHolder(View itemView) {
            super(itemView);
            myNameView = itemView.findViewById(R.id.name_id);
            myPhoneView = itemView.findViewById(R.id.ph_id);
        }

        @Override
        public void onClick(View view) {
            Log.e("onclick", myPhoneView.getText().toString());
        }
    }
}
