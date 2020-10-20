package com.example.user.jumpcountsample;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
public class MainActivity extends AppCompatActivity implements SensorEventListener{
    TextView tvCnt;

    SensorManager mManager;
    Sensor Acceleration;

    boolean isUp = false;
    boolean isDown = false;

    double gravityValue = 9.8;
    double accel;

    int iCnt = 0;

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCnt = (TextView)findViewById(R.id.tvCnt);

        mManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        Acceleration = mManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mManager.registerListener(this, Acceleration, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        accel = Math.sqrt(x*x + y*y + z*z); //현재 가속도 상태 값

        if(accel - gravityValue > 5)
            isUp = true;

        if(isUp && gravityValue - accel > 5)
            isDown = true;

        if(isDown == true)
        {
            iCnt++;
            tvCnt.setText(iCnt+"");
            isUp = false;
            isDown = false;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
