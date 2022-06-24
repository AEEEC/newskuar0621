package com.sku.road.zolzak.timetable;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.TimetableView;
import com.sku.road.zolzak.R;

import java.util.ArrayList;


public class TimeTableActivity extends AppCompatActivity implements View.OnClickListener   {
    ImageButton btn_Add, btn_Alarm;
    private TimetableView timetable;
    Schedule schedule2 = new Schedule();
    static final int REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        btn_Add = findViewById(R.id.btn_Add);
        btn_Alarm =findViewById(R.id.btn_Alarm);

        timetable = (TimetableView) findViewById(R.id.timetable);

        btn_Add.setOnClickListener(this);
        btn_Alarm.setOnClickListener(this);
        Log.e("초반","초반timetableactivity는 ok");
    }



    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Add:
                Intent i = new Intent(this, ClassSearchList.class);
                startActivityForResult(i, REQUEST_CODE);
                break;
            case R.id.btn_Alarm:
                timetable.removeAll();
                break;

        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("잘받았다!!!", "잘받았구만요!!!!!!\nrequestcode=" + requestCode);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                ArrayList<Schedule> schedules = (ArrayList<Schedule>) data.getSerializableExtra("schedules");
                timetable.add(schedules);



            }
        }
    }
}// END




























