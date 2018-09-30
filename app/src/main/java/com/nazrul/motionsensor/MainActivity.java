package com.nazrul.motionsensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    TextView txtNumber;
    SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNumber = findViewById(R.id.text_number);

        sm=(SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener( this,sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float value[] = event.values;
            float x = value[0];
            float y = value[1];
            float z = value[2];

            float asr = (float) ((Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2))/Math.pow(SensorManager.GRAVITY_EARTH,2));

            if(asr >= 2){
                Random r = new Random();

                int i = r.nextInt(100);
                txtNumber.setText(""+i);
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
