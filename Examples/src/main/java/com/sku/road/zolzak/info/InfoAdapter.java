package com.sku.road.zolzak.info;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sku.road.zolzak.R;
import com.sku.road.zolzak.timetable.OnClassItemClickListener;

import java.util.ArrayList;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.CustomViewHolder> {
    private ArrayList<FacilityModel> arrayList;
    OnClassItemClickListener listener;
    TextView tv_location;

    //constructor
    public InfoAdapter(ArrayList<FacilityModel> arrayList) {
        this.arrayList = arrayList;
    }
    //1.onCreateViewHolder -------------------------------------------------------
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_facility_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    //2.onBindViewHolder  -------------------------------------------------------
    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, int position) {
        holder.tv_name.setText(arrayList.get(position).getName());
        holder.tv_tel.setText(arrayList.get(position).getTel());
        holder.tv_location.setText(arrayList.get(position).getLocation().replace("_n","\n"));

//        if(holder.tv_location.getText(arrayList.get(position).getLocation().contains("_n")){
//            String newName = tv_location.getText(arrayList.getText().toString.replace("_n","\n"));
//            String newName = tv_location.getText().toString().replace("_n","\n");
//            holder.tv_location.setText(newName);
//        }

//        }
    }

    //3.getItemCount  -------------------------------------------------------
    @Override
    public int getItemCount() {
        // 삼항 연산자
        Log.e("FacilityModel 리스트카운트", "getItemCount ~");

        return (arrayList != null ? arrayList.size() : 0);
    }
//
//    public void setOnItemClickListener(OnClassItemClickListener listener) {
//        this.listener = listener;
//    }


//    public void onItemClick(MyAdapter.CustomViewHolder holder, View view, int position) {
//        if(listener != null) {
//            listener.onItemClick(holder,view,position); }
//    }


    // 뷰홀더 클래스  ---------------------------------
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_location;
        TextView tv_tel;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_name = itemView.findViewById(R.id.tv_name);
            this.tv_location = itemView.findViewById(R.id.tv_location);
            this.tv_tel = itemView.findViewById(R.id.tv_tel);
        }
    }

}
