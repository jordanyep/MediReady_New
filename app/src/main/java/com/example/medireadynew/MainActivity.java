package com.example.medireadynew;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int ACTIVITY_START_CAMERA_APP = 1;

    EditText firstName,lastName,age,gender,relationship;
    EditText medicalID, allergies, medication, conditions;

    ImageView userPhoto;
    DatabaseHelper helpher;
    List<DatabaseModel> dbList;

    /*private SensorManager mSensorManager;
    private Sensor mProximity;
    private double currentValue;
    float[] values;
    float distance;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbList= new ArrayList<DatabaseModel>();

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);
        relationship = findViewById(R.id.relationship);
        userPhoto = (ImageView) findViewById(R.id.imageViewPhoto);

        medicalID = findViewById(R.id.medicalID);
        allergies = findViewById(R.id.allergies);
        medication = findViewById(R.id.medication);
        conditions = findViewById(R.id.conditions);

        /*mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);*/

    }

    public void takePhoto(View view) {
        Toast.makeText(this, "the button is pressed", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(); //use this intent to trigger the camera
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, ACTIVITY_START_CAMERA_APP); //return to activity after photo has been taken
    }


    protected void onActivityResult (int requestCode, int resultCode, Intent data) { //data = thumbnail data. good to be used into a database?
        if(requestCode == ACTIVITY_START_CAMERA_APP && resultCode == RESULT_OK) {
            Toast.makeText(this, "the cmaera has been triggered and pic taken", Toast.LENGTH_SHORT).show();
            Bundle extras = data.getExtras(); //bundle= a way to collect data
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            userPhoto.setImageBitmap(imageBitmap);

        }
    }

    public void finished(View view) {
        String first = firstName.getText().toString();
        String last = lastName.getText().toString();
        String gen = gender.getText().toString();
        String age2 = age.getText().toString();
        String relat = relationship.getText().toString();

        String medicalID2 = medicalID.getText().toString();
        String allergies2 = allergies.getText().toString();
        String medication2 = medication.getText().toString();
        String conditions2 = conditions.getText().toString();

        if (first.equals("") || last.equals("") || gen.equals("") || relat.equals("")|| medicalID2.equals("")|| allergies2.equals("")|| medication2.equals("")|| conditions2.equals("")    ) {
            Toast.makeText(this, "Not all fields are filled in.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "" + first +" "+ last + " "+ gen + " "+ age2 + " "+ relat + " "+ medicalID2 + " "+ allergies2 +" "+ medication2 + " "+ conditions2, Toast.LENGTH_SHORT).show();
            helpher = new DatabaseHelper(MainActivity.this);
            helpher.insertIntoDB(first,last,gen,age2,relat, medicalID2, allergies2, medication2, conditions2);

            Intent intent = new Intent(this, MediReady.class);
            startActivity(intent);
        }

        firstName.setText("");
        lastName.setText("");
        gender.setText("");
        age.setText("");
        relationship.setText("");

        medicalID.setText("");
        allergies.setText("");
        medication.setText("");
        conditions.setText("");
    }

    /*@Override
    protected void onResume() {
        super.onResume();

        mSensorManager.registerListener((SensorEventListener) this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            currentValue = event.values[0];
            if (currentValue < 5) {
                Toast.makeText(getApplicationContext(), "near", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        mSensorManager.unregisterListener(this);
    }*/

}
