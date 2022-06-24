package com.sku.road.zolzak.armap;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sku.road.zolzak.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder1> {
    private ArrayList<ClassBuildingList> arrayList;
    OnClassItemClickListener1 listener;

    //constructor
    public CustomAdapter(ArrayList<ClassBuildingList> arrayList) {
        this.arrayList = arrayList;
    }
    //1.onCreateViewHolder -------------------------------------------------------
    @NonNull
    @Override
    public CustomViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buildinglist_item, parent, false);
        CustomViewHolder1 holder = new CustomViewHolder1(view);
        return holder;
    }

    //2.onBindViewHolder  -------------------------------------------------------
    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder1 holder, int position) {
        holder.tv_buildingname.setText(arrayList.get(position).getBuildingname());
        holder.tv_location.setText(arrayList.get(position).getLocation());
        holder.tv_tel.setText(arrayList.get(position).getTel());
//        }
    }

    //3.getItemCount  -------------------------------------------------------
    @Override
    public int getItemCount() {
        // 삼항 연산자
        Log.e("BuildingList 리스트카운트", "getItemCount ~");

        return (arrayList != null ? arrayList.size() : 0);
    }

    public void setOnItemClickListener(OnClassItemClickListener1 listener) {
        this.listener = listener;
    }


    public void onItemClick(CustomViewHolder1 holder, View view, int position) {
        if(listener != null) {
            listener.onItemClick(holder,view,position); }
    }


    // 뷰홀더 클래스  ---------------------------------
    public class CustomViewHolder1 extends RecyclerView.ViewHolder {
        TextView tv_buildingname;
        TextView tv_tel;
        TextView tv_location;

        Button btn_Add_Class;

        public CustomViewHolder1(@NonNull View itemView) {
            super(itemView);
            this.tv_buildingname = itemView.findViewById(R.id.tv_buildingname);
            this.tv_location = itemView.findViewById(R.id.tv_location);
            this.tv_tel = itemView.findViewById(R.id.tv_tel);
            this.btn_Add_Class = itemView.findViewById(R.id.btn_Add_Class);

            btn_Add_Class.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick(CustomViewHolder1.this, v, position);
                    }
                }
            });
        }
    }

}//MyAdqpter END
