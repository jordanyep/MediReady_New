package com.example.medireadynew;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jordanyep on 2018-03-20.
 */

public class FragmentHome extends Fragment {
    DatabaseHelper helpher;
    List<DatabaseModel> dbList;
    RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private SensorManager mSensorManager;
    private Sensor mProximity;
    private double currentValue;

    int timer = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("MediReady");
        helpher = new DatabaseHelper(getActivity());
        dbList= new ArrayList<DatabaseModel>();
        dbList = helpher.getDataFromDB();


        mRecyclerView = v.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RecyclerAdapter(getActivity(),dbList);
        mRecyclerView.setAdapter(mAdapter);

//        BottomNavigationView bottomNav = v.findViewById(R.id.bottom_navigation);
//        bottomNav.setOnNavigationItemSelectedListener(navListener);

        return v;
    }

//    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
//            new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    android.support.v4.app.Fragment selectedFragment = null;
//                    switch(item.getItemId()) {
//                        case R.id.nav_home:
//                            selectedFragment = new FragmentHome();
//                            break;
//                        //startActivity(new Intent(MediReady.this,MediReady.class));
//                        case R.id.nav_addFam:
//                            selectedFragment = new FragmentMap();
//                            break;
//                            /*Uri gmmIntentUri = Uri.parse("geo:0,0?q=hospitals");
//                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                            mapIntent.setPackage("com.google.android.apps.maps");
//                            startActivity(mapIntent);*/
//
//                        //startActivity(new Intent(MediReady.this,MainActivity.class));
//                        case R.id.nav_info:
//                            selectedFragment = new FragmentDoctor();
//                            //startActivity(new Intent(MediReady.this,FamilyDoctor.class));
//                            break;
//                        //startActivity(new Intent(MediReady.this,FamilyDoctor.class));
//                        //startActivity(new Intent(MediReady.this,FamilyDoctor.class));
//                    }
//
//                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                            selectedFragment).commit();
//                    return true;
//                }
//            };

    @Override
    public void onResume() {
        super.onResume();

        //mSensorManager.registerListener((SensorEventListener) this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    /*@Override
    public void onSensorChanged(SensorEvent event) {


        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            currentValue = event.values[0];

            if (currentValue < 5) {
                timer++;
                System.out.println("prox covered " + timer);

                if (timer == 5) {
                    Toast.makeText(getActivity().getApplicationContext(), "calling 911", Toast.LENGTH_SHORT).show();
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

    }*/

    @Override
    public void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        //mSensorManager.unregisterListener(this);
    }
}
