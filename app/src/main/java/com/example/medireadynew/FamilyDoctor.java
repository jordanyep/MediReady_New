package com.example.medireadynew;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class FamilyDoctor extends AppCompatActivity {
    TextView familyDoctor, phoneDoctor;
    public static final String DEFAULT = "not available";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_doctor);

        familyDoctor = findViewById(R.id.familyDoctor);
        phoneDoctor = findViewById(R.id.phoneDoctor);

        SharedPreferences sharedPrefs = getSharedPreferences("DocData", Context.MODE_PRIVATE);
        String doctor = sharedPrefs.getString("doctor", DEFAULT);
        String phone = sharedPrefs.getString("phone", DEFAULT);

        familyDoctor.setText(doctor);
        phoneDoctor.setText(phone);

        /*BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);*/

        getSupportActionBar().setTitle("Family Doctor Info");

    }

    public void editDoctor(View view) {

        Intent intent = new Intent(this, FamilyDoctorEdit.class);
        startActivity(intent);
    }

    /*private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    android.support.v4.app.Fragment selectedFragment = null;
                    switch(item.getItemId()) {
                        case R.id.nav_home:
                            startActivity(new Intent(FamilyDoctor.this,MediReady.class));
                        case R.id.nav_addFam:
                            startActivity(new Intent(FamilyDoctor.this,MainActivity.class));
                        case R.id.nav_info:
                            startActivity(new Intent(FamilyDoctor.this,FamilyDoctor.class));
                    }

                    return true;
                }
            };*/
}
