//
//package com.mapbox.vision.examples.crolling;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.mapbox.vision.examples.R;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class StudentDiet extends AppCompatActivity {
//    TextView textView2, textView3, textView4, textView5,  textView8, textView9, textView10, textView11, textView12, textView14, textView15, textView16, textView17, textView18;
//    String url="https://www.sungkyul.ac.kr/skukr/340/subview.do";
//    final Bundle bundle = new Bundle();
//
//    String msg="";
//    String sendMsg="";
//    ArrayList<String> arr = new ArrayList<>();
//    int count=1;
//    String msg1="";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.crolling_student_diet);
//
//        textView2=findViewById(R.id.textView2);
//        textView3=findViewById(R.id.textView3);
//        textView4=findViewById(R.id.textView4);
//        textView5=findViewById(R.id.textView5);
//        textView8=findViewById(R.id.textView8);
//        textView9=findViewById(R.id.textView9);
//        textView10=findViewById(R.id.textView10);
//        textView12=findViewById(R.id.textView12);
//
//        new Thread(){
//            @Override
//            public void run(){
//                Document doc = null;
//                try{
//
//                    doc = Jsoup.connect(url).get();
//                    Element elements = doc.select(".box-table").first();
//                    msg = elements.text();
//
//                    sendMsg=msg;
//                    sendMsg=sendMsg.substring(sendMsg.indexOf("s")+2);
//
//                    msg=sendMsg.substring(0,50);
//                    msg1=msg.replace(" ",", ");
//                    arr.add(msg1);
//
//                    msg1="";
//                    sendMsg=sendMsg.substring(sendMsg.indexOf("b")+4);
//                    msg=sendMsg.substring(0,24);
//                    msg1=msg.replace(" ",", ");
//                    sendMsg=sendMsg.substring(32);
//                    arr.add(msg1);
//
//                    msg1="";
//                    sendMsg=sendMsg.substring(sendMsg.indexOf("e")+2);
//                    msg=sendMsg.substring(0,78);
//                    msg1=msg.replace(" ",", ");
//                    sendMsg=sendMsg.substring(54);
//                    arr.add(msg1);
//
//                    for(String i: arr) {
//                        bundle.putString("message", i);
//                        Message msg = handler.obtainMessage();
//                        msg.setData(bundle);
//                        handler.sendMessage(msg);
//                        showDiet();
//                        count++;
//                    }
//
//                }catch(IOException e){
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//
//    }//onCre END
//    Handler handler = new Handler(){
//        public void handleMessage(Message msg){
//            Bundle bundle = msg.getData();
//        }
//    }; // handler END
//
//
//    void showDiet(){
//        if(count==1)
//            textView8.setText(bundle.getString("message"));
//        else if(count==2)
//            textView10.setText(bundle.getString("message"));
//        else if(count==3)
//            textView12.setText(bundle.getString("message"));
//    }
//
//
//}//class END
