package com.example.medireadynew;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class NewHome extends AppCompatActivity implements SensorEventListener {
    FloatingActionButton fab2;
    private SensorManager mSensorManager;
    private Sensor mProximity;
    private double currentValue;

    int timer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_home);

        BottomNavigationView bottomNav2 = findViewById(R.id.bottom_navigation2);
        bottomNav2.setOnNavigationItemSelectedListener(navListener2);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,
                new NewFragmentHome()).commit();

        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add a person here", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(NewHome.this,MainActivity.class));
            }
        });

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener2 =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    android.support.v4.app.Fragment selectedFragment = null;
                    switch(item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new NewFragmentHome();
                            getSupportActionBar().hide();
                            fab2.show();
                            break;
                        case R.id.nav_addFam:
                            selectedFragment = new NewFragmentMap();
                            getSupportActionBar().show();
                            fab2.hide();
                            break;
                        case R.id.nav_info:
                            selectedFragment = new NewFragmentDoctor();
                            getSupportActionBar().show();
                            fab2.hide();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,
                            selectedFragment).commit();
                    return true;
                }
            };

    @Override
    protected void onResume() {
        super.onResume();

        mSensorManager.registerListener((SensorEventListener) this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {


        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            currentValue = event.values[0];

            if (currentValue < 5) {
                timer++;
                System.out.println("prox covered " + timer);

                if (timer == 5) {
                    Toast.makeText(getApplicationContext(), "calling 911", Toast.LENGTH_SHORT).show();
                    String phoneNum = "7787826930";
                    String dial = "tel:" + phoneNum;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                    timer = 0;
                }
                else {
                    timer++;
                }
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
    }

    public void openDialog() {
        DeleteRowDialog deleteRowDialog = new DeleteRowDialog();
        deleteRowDialog.show(getSupportFragmentManager(), "example");
    }
}
