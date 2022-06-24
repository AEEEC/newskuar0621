package com.sku.road.zolzak.timetable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.tlaabs.timetableview.Schedule;
import com.sku.road.zolzak.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {
    private ArrayList<ClassModel> arrayList;
    private Schedule schedule;
    OnClassItemClickListener listener;

    //constructor
    public MyAdapter(ArrayList<ClassModel> arrayList) {
        this.arrayList = arrayList;
        schedule = new Schedule();
    }
    //1.onCreateViewHolder -------------------------------------------------------
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timetable_classlist_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    //2.onBindViewHolder  -------------------------------------------------------
    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, int position) {
        holder.tv_classname.setText(arrayList.get(position).getClassname());
        holder.tv_isugubun.setText(arrayList.get(position).getIsugubun());
        holder.tv_classroom.setText(arrayList.get(position).getClassroom());
        holder.tv_professor.setText(arrayList.get(position).getProfessor());
        holder.tv_classtime.setText(arrayList.get(position).getClasstime());
        holder.tv_classscore.setText(arrayList.get(position).getClassscore());
        holder.tv_classcode.setText(arrayList.get(position).getClasscode());
        holder.tv_grade.setText(arrayList.get(position).getGrade());
//        }
    }

    //3.getItemCount  -------------------------------------------------------
    @Override
    public int getItemCount() {
        // 삼항 연산자
        Log.e("ClassModel 리스트카운트", "getItemCount ~");

        return (arrayList != null ? arrayList.size() : 0);
    }

    public void setOnItemClickListener(OnClassItemClickListener listener) {
        this.listener = listener;
    }


    public void onItemClick(CustomViewHolder holder, View view, int position) {
        if(listener != null) {
            listener.onItemClick(holder,view,position); }
    }


    // 뷰홀더 클래스  ---------------------------------
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView tv_classname;
        TextView tv_isugubun;
        TextView tv_professor;
        TextView tv_classtime;
        TextView tv_classscore;
        TextView tv_classroom;
        TextView tv_grade;
        TextView tv_classcode;
        Button btn_Add_Class;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_isugubun = itemView.findViewById(R.id.tv_isugubun);
            this.tv_classname = itemView.findViewById(R.id.tv_classname);
            this.tv_classscore = itemView.findViewById(R.id.tv_classscore);
            this.tv_professor = itemView.findViewById(R.id.tv_professor);
            this.tv_classtime = itemView.findViewById(R.id.tv_classtime);
            this.tv_classroom = itemView.findViewById(R.id.tv_classroom);
            this.tv_grade = itemView.findViewById(R.id.tv_grade);
            this.tv_classcode = itemView.findViewById(R.id.tv_classcode);
            this.btn_Add_Class = itemView.findViewById(R.id.btn_Add_Class);

//            btn_add_class.setOnClickListener(new View.OnClickListener(){
            btn_Add_Class.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick(CustomViewHolder.this, v, position);
                    }
                }
            });
        }
    }

}//MyAdqpter END
