package com.safelyroads.www.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";
    private SensorManager sensorManager;
    private Sensor accelerometer, mGyro, mMagno, mLight, mPressure, mTemp, mHumid;
    double acc, acc2;

    TextView xValue, yValue, zValue, xGyroValue, yGyroValue, zGyroValue, xMagnoValue, yMagnoValue, zMagnoValue, light, temp, pressure, humi, acceleration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xValue = (TextView)findViewById(R.id.xValue);
        yValue = (TextView)findViewById(R.id.yValue);
        zValue = (TextView)findViewById(R.id.zValue);

        xGyroValue = (TextView)findViewById(R.id.xGyroValue);
        yGyroValue = (TextView)findViewById(R.id.yGyroValue);
        zGyroValue = (TextView)findViewById(R.id.zGyroValue);

        xMagnoValue = (TextView)findViewById(R.id.xMagnoValue);
        yMagnoValue = (TextView)findViewById(R.id.yMagnoValue);
        zMagnoValue = (TextView)findViewById(R.id.zMagnoValue);

        light = (TextView)findViewById(R.id.light);
        temp = (TextView)findViewById(R.id.temp);
        pressure = (TextView)findViewById(R.id.pressure);
        humi = (TextView)findViewById(R.id.humi);
        acceleration = (TextView)findViewById(R.id.acceleration);


        Log.d(TAG, "onCreate: Initializing Sensor Services");

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(accelerometer !=null){
            sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered accelerometer listener");
        }
        else {
            xValue.setText("Accelerometer is not supported");
            yValue.setText("Accelerometer is not supported");
            zValue.setText("Accelerometer is not supported");
            acceleration.setText("Accelerometer is not supported");
        }

        mGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(mGyro !=null){
            sensorManager.registerListener(MainActivity.this, mGyro, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Gyroscope listener");
        }
        else {
            xGyroValue.setText("Gyroscope is not supported");
            yGyroValue.setText("Gyroscope is not supported");
            zGyroValue.setText("Gyroscope is not supported");
        }

        mMagno = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if(mMagno !=null){
            sensorManager.registerListener(MainActivity.this, mMagno, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Magno listener");
        }
        else {
            xMagnoValue.setText("Magno is not supported");
            yMagnoValue.setText("Magno is not supported");
            zMagnoValue.setText("Magno is not supported");
        }

        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(accelerometer !=null){
            sensorManager.registerListener(MainActivity.this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Light listener");
        }
        else {
            light.setText("Light Sensor is not supported");
        }

        mPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if(mPressure !=null){
            sensorManager.registerListener(MainActivity.this, mPressure, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Pressure listener");
        }
        else {
            pressure.setText("Barometer is not supported");
        }

        mTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if(mTemp !=null){
            sensorManager.registerListener(MainActivity.this, mTemp, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered temperature listener");
        }
        else {
            temp.setText("Temperature is not supported");
        }

        mHumid = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if(mHumid !=null){
            sensorManager.registerListener(MainActivity.this, mHumid, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Humidity listener");
        }
        else {
            humi.setText("Humidity sensor is not supported");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Sensor sensor = sensorEvent.sensor;
        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            Log.d(TAG, "onSensorChanged: X:" + sensorEvent.values[0] + "Y:" + sensorEvent.values[1] + "Z:" + sensorEvent.values[2]);
            xValue.setText("xValue: "+sensorEvent.values[0]);
            yValue.setText("yValue: "+sensorEvent.values[1]);
            zValue.setText("zValue: "+sensorEvent.values[2]);
            acc = Math.sqrt((sensorEvent.values[0]*sensorEvent.values[0])+ (sensorEvent.values[1]*sensorEvent.values[1])+ (sensorEvent.values[2]*sensorEvent.values[2]));
            acc2 = acc;
            acc = acc/(9.8);
            acceleration.setText("acceleration: " +acc + "G");
        }

        else if(sensor.getType() == Sensor.TYPE_GYROSCOPE){
            xGyroValue.setText("xGyroValue: "+sensorEvent.values[0]);
            yGyroValue.setText("xGyroValue: "+sensorEvent.values[1]);
            zGyroValue.setText("xGyroValue: "+sensorEvent.values[2]);
        }

        else if(sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            xMagnoValue.setText("xMagnoValue: "+sensorEvent.values[0]);
            yMagnoValue.setText("xMagnoValue: "+sensorEvent.values[1]);
            zMagnoValue.setText("xMagnoValue: "+sensorEvent.values[2]);
        }

        else if(sensor.getType() == Sensor.TYPE_LIGHT){
            light.setText("Light: "+sensorEvent.values[0]);
        }

        else if(sensor.getType() == Sensor.TYPE_PRESSURE){
            pressure.setText("Pressure: "+sensorEvent.values[0]);
        }

        else if(sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
            temp.setText("Temperature: "+sensorEvent.values[0]);
        }

        else if(sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY){
            humi.setText("Humidity: "+sensorEvent.values[0]);
        }

    }





}
