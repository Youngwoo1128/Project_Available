package com.mac_available.carrotmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MovieActivity extends AppCompatActivity {

    ArrayList<String> items = new ArrayList<>();
    ListView listView;
    ArrayAdapter listViewAdapter;

    String apiKey= "a5200f16d459977281a06cf3719d898b";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        listView = findViewById(R.id.movie_listView);
        listViewAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(listViewAdapter);
    }

    public void clickMovie(View view) {
        items.clear();

        Thread t = new Thread(){
            @Override
            public void run() {

                //시간설정하기
                Date date = new Date();
                date.setTime(date.getTime() - (1000*60*60*24));
                //일단전날 날짜로 설정
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String dateStr = sdf.format(date);

                String address = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml"
                                +"?key="+apiKey
                                +"&targetDt="+dateStr
                                +"&itemPerpage=7";

                try {
                    URL url = new URL(address);
                    InputStream is = url.openStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser xpp = factory.newPullParser();

                    xpp.setInput(isr);

                    int eventType = xpp.getEventType();

                    StringBuffer buffer = null;

                    while(eventType != XmlPullParser.END_DOCUMENT){


                        switch (eventType){
                            case XmlPullParser.START_DOCUMENT:
                                 runOnUiThread(new Runnable() {
                                     @Override
                                     public void run() {
                                         Toast.makeText(MovieActivity.this, "검색 시작!!!", Toast.LENGTH_SHORT).show();
                                     }
                                 });
                                 break;

                            case XmlPullParser.START_TAG:
                                String tagName = xpp.getName();

                                if (tagName.equals("dailyBoxOffice")){
                                    buffer = new StringBuffer();
                                }else if (tagName.equals("rank")){
                                    xpp.next();
                                    buffer.append("순위: "+xpp.getText()+"\n");


                                }else if (tagName.equals("movieNm")){
                                    buffer.append("제목: ");
                                    xpp.next();
                                    buffer.append(xpp.getText()+"\n");
                                }else if (tagName.equals("openDt")){
                                    buffer.append("개봉일: ");
                                    xpp.next();
                                    buffer.append(xpp.getText()+"\n");
                                }else if (tagName.equals("audiAcc")){
                                    buffer.append("누적관객수: ");
                                    xpp.next();
                                    buffer.append(xpp.getText()+"\n");
                                }
                                break;

                            case XmlPullParser.TEXT:
                                break;

                            case XmlPullParser.END_TAG:
                                String tagName2 = xpp.getName();
                                if(tagName2.equals("dailyBoxOffice")){
                                    Log.i("tag", ""+buffer.toString());


                                   items.add(buffer.toString());
                                }
                                break;
                        }

                        eventType=xpp.next();
                    }


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listViewAdapter.notifyDataSetChanged();
                        }
                    });

                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }
}