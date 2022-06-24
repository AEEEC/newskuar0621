package com.sku.road.zolzak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.sku.road.zolzak.R;
import com.sku.road.zolzak.armap.ARSearchActivity;
import com.sku.road.zolzak.info.InfoActivity;
import com.sku.road.zolzak.timetable.TimeTableActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ARSearchActivity arSearchActivity;
    InfoActivity infoActivity;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.BasicTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        infoActivity = new InfoActivity();
        arSearchActivity = new ARSearchActivity();

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout, arSearchActivity).commitAllowingStateLoss();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_navi_ar: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout, arSearchActivity).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.bottom_navi_info: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout,infoActivity ).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.bottom_navi_schedule: {
                        Intent i = new Intent(getApplicationContext(), TimeTableActivity.class);
                        startActivity(i);
                        break;
                    }


                }
                return true;
            }
        });
    }//onCre END



}//class END

