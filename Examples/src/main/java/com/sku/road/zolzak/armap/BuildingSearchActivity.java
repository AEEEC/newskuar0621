package com.sku.road.zolzak.armap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sku.road.zolzak.R;

import java.util.ArrayList;

public class BuildingSearchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ClassBuildingList> arrayList;
    // 파베 DB 받아와서 저장하는 배열리스트

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;
    CustomAdapter adapter=null;
    private SearchView autoCompleteText;

    private Query query;
    Button btn_add_class;
    String buildingName="";
    String longitude="";
    String latitude="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.building_search_list);
        autoCompleteText = findViewById(R.id.autoCompleteText);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // 강의 객체를 담을 어레이 리스트 (어댑터쪽으로)
        ArrayList<String> array1 = new ArrayList<>(); // 강의 객체를 담을 어레이 리스트 (어댑터쪽으로)



        btn_add_class = findViewById(R.id.btn_Add_Class);

        initDatabase();
//        mDatabase = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance("https://sku-ar-map-b3205-default-rtdb.firebaseio.com/");
        mReference = mDatabase.getReference().child("sisul");

        autoCompleteText.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit (String s){
                query = mReference.orderByChild("name").startAt(s).endAt(s + "\uf8ff");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                        arrayList.clear(); // 기존 배열리스트가 존재하지않게 초기화
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                            Log.d("onDatachange", "onDataChange: snapshot "+snapshot.getKey()+" = "+snapshot.getValue());
                            arrayList.add(0, snapshot.getValue(ClassBuildingList.class));

                        }
                        adapter = new CustomAdapter(arrayList);
                        recyclerView.setAdapter(adapter); // 리사이클러뷰에 어댑터 연결


                        adapter.setOnItemClickListener(new OnClassItemClickListener1() {
                            @Override
                            public void onItemClick(CustomAdapter.CustomViewHolder1 holder, View view, int position){
                                ClassBuildingList list = arrayList.get(position);
                                Intent intent = new Intent(getApplicationContext(), ArActivity.class);

//                                buildingName = list.getBuildingname();
                                buildingName = "재림";
                                switch(buildingName){
                                    case "재림":
                                        longitude="37.380332";
                                        latitude="126.927608";
                                        break;

                                    case "중생":
                                        longitude="37.378910";
                                        latitude="126.920093";
                                        break;

                                    case "학생":
                                        longitude="37.379595";
                                        latitude="126.929343";
                                        break;

                                    case "각 ":
                                        longitude="37.378910";
                                        latitude="126.920093";
                                        break;

                                    case "기념":
                                        longitude="37.379398";
                                        latitude="126.927703";
                                        break;

                                    case "●도":
                                        longitude="37.380592";
                                        latitude="126.927703";
                                        break;

                                    case "학교":
                                        longitude="37.380643";
                                        latitude="126.929487";
                                        break;

                                    case "성결":
                                        longitude="37.379905";
                                        latitude="126.928846";
                                        break;

                                    case "영암":
                                        longitude="37.378960";
                                        latitude="126.929636";
                                        break;

                                    case "ht":
                                        longitude="37.380592";
                                        latitude="126.926835";
                                        break;

                                    case "학술":
                                        longitude="37.380592";
                                        latitude="126.926835";
                                        break;

                                }

                                intent.putExtra("longitude", longitude);
                                intent.putExtra("latitude", latitude);

                                setResult(RESULT_OK,intent);
                                startActivity(intent);
                                finish();
                            }

                        });


                    }//onDataChange end

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // 디비를 가져오던중 에러 발생 시
                        Log.e("ClassSearch", String.valueOf(error.toException())); // 에러문 출력
                    }//onCancelled end
                });
                autoCompleteText.setQuery("", false);
                autoCompleteText.clearFocus();
                return true;
            }//onQuertTextSubmit end

            @Override
            public boolean onQueryTextChange (String s){
                return false;
            }
        });//autoCompleteText.setOnQuery~
    }//onCreate end


    private void initDatabase() {
        mChild = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
    }//initDatabase end

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("FindBuilding_onActResult", "onActivityResult 실행");}
    @Override
    public void onDestroy() {
        super.onDestroy();
        mReference.removeEventListener(mChild);
    }

}

