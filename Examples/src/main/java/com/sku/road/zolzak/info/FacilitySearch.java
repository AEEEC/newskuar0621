package com.sku.road.zolzak.info;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.tlaabs.timetableview.Schedule;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sku.road.zolzak.R;

import java.util.ArrayList;

public class FacilitySearch extends AppCompatActivity {

    private TextView tv_name, tv_location, tv_tel;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<FacilityModel> arrayList;
    // 파베 DB 받아와서 저장하는 배열리스트
    ArrayList<Schedule> schedules = new ArrayList<>();

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;
    InfoAdapter adapter = null;
    private androidx.appcompat.widget.SearchView autoCompleteText;

    private Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_facility_search);
        autoCompleteText = findViewById(R.id.autoCompleteText);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // 시설 객체를 담을 어레이 리스트 (어댑터쪽으로)
        tv_name = findViewById(R.id.tv_name);
        tv_location = findViewById(R.id.tv_location);
        tv_tel = findViewById(R.id.tv_tel);

        initDatabase();
        mDatabase = FirebaseDatabase.getInstance("https://sku-ar-map-b3205-default-rtdb.firebaseio.com/");
        mReference = mDatabase.getReference().child("sisul");

        autoCompleteText.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

//                query = mReference.orderByChild("name"); //이걸로 실행하면 시설db 전체출력 볼수있음
                query = mReference.orderByChild("name").startAt(s).endAt(s + "\uf8ff");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                        arrayList.clear(); // 기존 배열리스트가 존재하지않게 초기화
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                            Log.d("onDatachange", "onDataChange: snapshot "+snapshot.getKey()+" = "+snapshot.getValue());
//                            tv_location=tv_location.replace("_n", "\n");
//                              (String)dataSnapshot.child("name").getValue().replace("_b","/n");
                            arrayList.add(0, snapshot.getValue(FacilityModel.class));
                        }
                        adapter = new InfoAdapter(arrayList);
                        recyclerView.setAdapter(adapter); // 리사이클러뷰에 어댑터 연결

                    //    adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침해야 반영이 됨
                        if (arrayList==null){
                            Log.e("arrayList","arrayList null임");
                        }
                        Log.e("Facility.java", "시설검색 리사이클러뷰 셋어댑터");

                    }//onDataChange end

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // 디비를 가져오던중 에러 발생 시
                        Log.e("FacilitySearch.java", String.valueOf(error.toException())); // 에러문 출력
                    }//onCancelled end
                });
                autoCompleteText.setQuery("", false);
                autoCompleteText.clearFocus();
                return true;
            }//onQuertTextSubmit end

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });//autoCompleteText.setOnQuery~
    }//onCreate end


    private void initDatabase() {
        mChild = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
    }//initDatabase end

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("FacilitySearch.java", "onActivityResult 실행");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mReference.removeEventListener(mChild);
    }
}
