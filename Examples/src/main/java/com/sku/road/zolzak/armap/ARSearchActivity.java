package com.sku.road.zolzak.armap;

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

public class ARSearchActivity extends Fragment implements View.OnClickListener{
    Button btn_start_arnavi;
    Button btn_set_destination;
    // 가장 처음에 실행되는 액티비티
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.activity_ar_search, container, false);
        init(view);
        return view;
    }

    public void init(View v){
        btn_start_arnavi = (Button) v.findViewById(R.id.btnStartARNavigation);
        btn_set_destination = (Button) v.findViewById(R.id.btnSetDestination);

        btn_start_arnavi.setOnClickListener(this);
        btn_set_destination.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {


            case R.id.btnStartARNavigation: {
                Intent i = new Intent(getActivity(), ArActivity.class);
                startActivity(i);
                break;
            }
            case R.id.btnSetDestination: {
                Intent intent1 = new Intent(getActivity(), BuildingSearchActivity.class);
                startActivity(intent1);
                break;
            }
        }
    }//onClick end
}// END