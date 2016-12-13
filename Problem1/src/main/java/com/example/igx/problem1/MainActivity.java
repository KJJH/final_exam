package com.example.igx.problem1;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity /* implements Something1, Something2 */ {

    private static final String TAG = "Sensor";

    SensorManager sm;
    SensorEventListener accL;
    SensorEventListener oriL;


    Sensor oriSensor; // 방향 센서
    Sensor accSensor; // 가속도 센서


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        oriSensor = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);    // 방향 센서
        accSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);    // 가속도 센서

        final Button btn_getLocation = (Button) findViewById(R.id.btn_getLocation);
        Button btn_getSensors = (Button) findViewById(R.id.btn_getSensors);
        Button btn_sendMessage = (Button) findViewById(R.id.btn_sendMessage);

        final TextView text_selectedData = (TextView) findViewById(R.id.text_selectedData);
        final TextView text_selectedType = (TextView) findViewById(R.id.text_selectedType);
        final EditText edit_phoneNumber = (EditText) findViewById(R.id.edit_phoneNumber);


        btn_getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_selectedType.setText("LOCATION");

                LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

                String getLocation;
                text_selectedData.setText("Location : " + getLocation);
            }
        });

        btn_getSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float getOriX, getOriY, getOriZ;
                float getAccX, getAccY, getAccZ;

                // 방향 센서에 대해..
                public class oriL implements SensorEventListener {
                    public void onSensorChanged(SensorEvent event) {
                        // 방향 센서
                        getOriX = oriSensor.event.values[0];
                        getOriY = oriSensor.event.values[1];
                        getOriZ = oriSensor.event.values[2];
                    }
                }

                // 가속도 센서에 대해...
                public class accL implements SensorEventListener {
                    public void onSensorChanged(SensorEvent event) {
                        // 가속도 센서
                        getAccX = accSensor.event.values[0];
                        getAccY = accSensor.event.values[1];
                        getAccZ = accSensor.event.values[2];
                    }
                }

                text_selectedData.setText(" [방향 센서]\nx: " + getOriX + ",\ty : " + getOriY + ",\tz : " + getOriZ
                        + "\n [가속도 센서]\nx : " + getAccX + ",\ty : " + getAccY + ",\tz : " + getAccZ);

            }
        });

        btn_sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = text_selectedData.getText();
                edit_phoneNumber.getText();

            }
        });
    }
}