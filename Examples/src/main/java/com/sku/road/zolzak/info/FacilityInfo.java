package com.sku.road.zolzak.info;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

import com.sku.road.zolzak.R;

public class FacilityInfo extends TabActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.facility_info);

        TabHost mTab = getTabHost();

        Intent intent;

        LayoutInflater inflater = LayoutInflater.from(this);

        inflater.inflate(R.layout.info_facility_info, mTab.getTabContentView(), true);

        mTab.addTab(mTab.newTabSpec("GUIDE").setIndicator("시설안내").setContent(R.id.tabGuide));
        mTab.addTab(mTab.newTabSpec("FACILITIES").setIndicator("편의시설").setContent(R.id.tabFacilities));

        intent = new Intent().setClass(this, FacilitySearch.class);
        mTab.addTab(mTab.newTabSpec("SEARCH").setIndicator("검색").setContent(intent));
//        mTab.addTab(mTab.newTabSpec("SEARCH").setIndicator("검색").setContent(R.id.tabSearch));

        //먼저 열릴 탭 선택
        getTabHost().setCurrentTab(0);
    }
}
