package com.sku.road.zolzak.crolling;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sku.road.zolzak.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class AcademicSchedule extends AppCompatActivity {
    TextView tv2,tv4,tv6,tv8,tv10,tv12;
    //tv8,tv10,tv12,tv14,tv16,tv18,tv20;
    String url="https://www.sungkyul.ac.kr/skukr/313/subview.do?enc=Zm5jdDF8QEB8JTJGc2NoZHVsbWFuYWdlJTJGc2t1a3IlMkYzJTJGdmlldy5kbyUzRg%3D%3D#none";
    final Bundle bundle = new Bundle();

    String msg="";
    String sendMsg="";

    int cnt=0;
    ArrayList<String> arr = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crolling_academic_schedule);


        tv2=findViewById(R.id.tv2);
        tv4=findViewById(R.id.tv4);
        tv6=findViewById(R.id.tv6);
       tv8=findViewById(R.id.tv8);
       tv10=findViewById(R.id.tv10);
       tv12=findViewById(R.id.tv12);
              /*
       tv14=findViewById(R.id.tv14);
       tv16=findViewById(R.id.tv16);
       tv18=findViewById(R.id.tv18);
       tv20=findViewById(R.id.tv20);
        */

        new Thread(){
            @Override
            public void run(){
                Document doc = null;
                try{

                    doc = Jsoup.connect(url).get();
                    Element elements = doc.select(".sche-comt table").first();
                    msg = elements.text();

                    sendMsg=msg.substring(19);
                    Log.e("sendmsg======", sendMsg);
                    while(sendMsg.contains("05")){
                        sendMsg=sendMsg.substring(sendMsg.indexOf("05")+13);
                        if(sendMsg.contains("05")) {
                            msg = sendMsg.substring(0, sendMsg.indexOf("05") - 1);
                            Log.e("msg", msg);
                            arr.add(msg);
                            sendMsg=sendMsg.substring(sendMsg.indexOf("05"));
                        }
                    }
                    msg=sendMsg.substring(sendMsg.indexOf("05")+2);
                    arr.add(msg);
                    for(String i: arr) {

                        bundle.putString("message", i);
                        Message msg = handler.obtainMessage();
                        msg.setData(bundle);
                        handler.sendMessage(msg);
                        showAcademicSchedule();

                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }.start();

    }//onCre END
    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            Bundle bundle = msg.getData();

        }
    }; // handler END


    void showAcademicSchedule(){

        switch(cnt){
            case 0:
                tv2.setText(bundle.getString("message"));
                break;
            case 1:
                tv4.setText(bundle.getString("message"));
                break;
            case 2:
                tv6.setText(bundle.getString("message"));
                break;
           case 3:
                tv8.setText(bundle.getString("message"));
                break;
            case 4:
                tv10.setText(bundle.getString("message"));
                break;
            case 5:
                tv12.setText(bundle.getString("message"));
                break;
                           /*

            case 6:
                tv14.setText(bundle.getString("message"));
                break;
            case 7:
                tv16.setText(bundle.getString("message"));
                break;
            case 8:
                tv18.setText(bundle.getString("message"));
                break;
            case 9:
                tv20.setText(bundle.getString("message"));
                break;
            */

        }


        cnt++;
    }
}//class END