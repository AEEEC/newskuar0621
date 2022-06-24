package com.sku.road.zolzak.info;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sku.road.zolzak.R;
import com.sku.road.zolzak.crolling.AcademicSchedule;

public class InfoActivity extends Fragment implements View.OnClickListener{

    Button btn_Calendar, btn_Bus, btn_Menu_cafeteria, btn_Facility;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.activity_info, container, false);
        init(view);
        return view;
    }

    public void init(View v){
        btn_Calendar = (Button) v.findViewById(R.id.btn_Calendar);
        btn_Bus = (Button) v.findViewById(R.id.btn_Bus);
        btn_Menu_cafeteria = (Button) v.findViewById(R.id.btn_Menu_cafeteria);
        btn_Facility = (Button) v.findViewById(R.id.btn_Facility);

        btn_Calendar.setOnClickListener(this);
        btn_Bus.setOnClickListener(this);
        btn_Menu_cafeteria.setOnClickListener(this);
        btn_Facility.setOnClickListener(this);

    }
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btn_Calendar: {
                Intent i = new Intent(getActivity(), AcademicSchedule.class);
                startActivity(i);
                break;
            }
            case R.id.btn_Bus: {
                Intent i2 = new Intent(getActivity(), SchoolbusInfo.class);
                startActivity(i2);
                break;
            }
            case R.id.btn_Menu_cafeteria: {

            }

            case R.id.btn_Facility: {
                Intent i4 = new Intent(getActivity(), FacilityInfo.class);
                startActivity(i4);
                break;
            }
        }
    }



}// END