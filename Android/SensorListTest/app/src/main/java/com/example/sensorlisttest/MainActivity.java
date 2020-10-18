package com.example.sensorlisttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tvList;

    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvList = findViewById(R.id.tvList);

        // 센서 매니저 객체 획득
        SensorManager manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        // 센서 목록 획득
        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);
        // 결과 출력을 위한 반복문으로 문자열 생성
        result += "[전체 센서 수]" + sensors.size() + "개" +"\n\n";
        int iNum = 0;
        for(Sensor s : sensors){
            result +=  ++iNum + ") 센서이름: " + s.getName() + "\n";
        }

        // 결과 출력

        tvList.setText(result);
    }
}
