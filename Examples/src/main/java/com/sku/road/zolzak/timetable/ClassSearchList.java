package com.sku.road.zolzak.timetable;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sku.road.zolzak.R;

import java.util.ArrayList;

public class ClassSearchList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ClassModel> arrayList;
    // 파베 DB 받아와서 저장하는 배열리스트
    ArrayList<Schedule> schedules = new ArrayList<>();

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;
    MyAdapter adapter=null;
    private SearchView autoCompleteText;

    Schedule schedule = new Schedule();
    private Query query;
    TextView tv_classname, tv_professor, tv_classcode, tv_classtime, tv_classroom, tv_grade, tv_isugubun, tv_classscore;
    Button btn_add_class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable_class_search_list);
        autoCompleteText = findViewById(R.id.autoCompleteText);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // 강의 객체를 담을 어레이 리스트 (어댑터쪽으로)

        tv_classname = findViewById(R.id.tv_classname);
        tv_professor = findViewById(R.id.tv_professor);
        tv_classcode = findViewById(R.id.tv_classcode);
        tv_classtime = findViewById(R.id.tv_classtime);
        tv_classroom = findViewById(R.id.tv_classroom);
        tv_grade = findViewById(R.id.tv_grade);
        tv_isugubun = findViewById(R.id.tv_isugubun);
        tv_classscore = findViewById(R.id.tv_classscore);

        btn_add_class = findViewById(R.id.btn_Add_Class);

        initDatabase();
//        mDatabase = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance("https://sku-ar-map-b3205-default-rtdb.firebaseio.com/");
        mReference = mDatabase.getReference().child("class");
//        mReference = mDatabase.getReference().child("class");

        autoCompleteText.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit (String s){
                query = mReference.orderByChild("classname").startAt(s).endAt(s + "\uf8ff");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                        arrayList.clear(); // 기존 배열리스트가 존재하지않게 초기화
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                            Log.d("onDatachange", "onDataChange: snapshot "+snapshot.getKey()+" = "+snapshot.getValue());
                            arrayList.add(0, snapshot.getValue(ClassModel.class));
                        }
                        adapter = new MyAdapter(arrayList);
                        recyclerView.setAdapter(adapter); // 리사이클러뷰에 어댑터 연결

                    /*    adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침해야 반영이 됨
                        if (arrayList==null){
                            Log.e("arrayList","arrayList null임");
                        }*/
                        Log.e("hey!!!!!!!!!!!!", "여기까진 됐따 리사이큘뷰 셋어뎁터");

                        adapter.setOnItemClickListener(new OnClassItemClickListener() {
                            @Override
                            public void onItemClick(MyAdapter.CustomViewHolder holder, View view, int position){
                                ClassModel list = arrayList.get(position);
                                Log.e("ok2222222222", "okay item listener!!!!!!!");
                                Intent intent = new Intent(getApplicationContext(), TimeTableActivity.class);
                                schedule.setProfessorName(list.getProfessor());
                                schedule.setClassPlace(list.getClassroom());
                                schedule.setClassTitle(list.getClassname());
                                /*intent.putExtra("classname", list.getClassname());
                                intent.putExtra("professor", list.getProfessor());
                                intent.putExtra("classroom", list.getClassroom());
                                intent.putExtra("classtime", list.getClasstime());*/
                                String strTime = list.getClasstime();
                                switch(strTime.substring(1)){
                                    case "1-3":
                                        schedule.setStartTime(new Time(9,0)); // sets the beginning of class time (hour,minute)
                                        schedule.setEndTime(new Time(11,40));
                                        break;

                                    case "4-6":
                                        schedule.setStartTime(new Time(11,55)); // sets the beginning of class time (hour,minute)
                                        schedule.setEndTime(new Time(14,35));
                                        break;

                                    case "4-5":
                                        schedule.setStartTime(new Time(11,55)); // sets the beginning of class time (hour,minute)
                                        schedule.setEndTime(new Time(13,40));
                                        break;

                                    case "7-9":
                                        schedule.setStartTime(new Time(14,40)); // sets the beginning of class time (hour,minute)
                                        schedule.setEndTime(new Time(17,20));
                                        break;

                                    case "8-10":
                                        schedule.setStartTime(new Time(15,35)); // sets the beginning of class time (hour,minute)
                                        schedule.setEndTime(new Time(18,30));
                                        break;

                                    case "10-11":
                                        schedule.setStartTime(new Time(17,40)); // sets the beginning of class time (hour,minute)
                                        schedule.setEndTime(new Time(19,20));
                                        break;

                                    case "10-12":
                                        schedule.setStartTime(new Time(17,40)); // sets the beginning of class time (hour,minute)
                                        schedule.setEndTime(new Time(20,10));
                                        break;
                                }

                                switch(strTime.charAt(0)){
                                    case '월':
                                        schedule.setDay(0);
                                        break;

                                    case '화':
                                        schedule.setDay(1);
                                        break;

                                    case '수':
                                        schedule.setDay(2);
                                        break;

                                    case '목':
                                        schedule.setDay(3);
                                        break;

                                    case '금':
                                        schedule.setDay(4);
                                        break;
                                }

                                schedules.add(schedule);
                                intent.putExtra("schedules", schedules);
                                setResult(RESULT_OK,intent);
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
        Log.e("FindClass_onActResult", "onActivityResult 실행");}
    @Override
    public void onDestroy() {
        super.onDestroy();
        mReference.removeEventListener(mChild);
    }

}

