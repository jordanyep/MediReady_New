package com.example.medireadynew;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class NewHome extends AppCompatActivity {
    FloatingActionButton fab2;

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
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener2 =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    android.support.v4.app.Fragment selectedFragment = null;
                    switch(item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new NewFragmentHome();
                            fab2.show();
                            break;
                        case R.id.nav_addFam:
                            selectedFragment = new NewFragmentMap();
                            fab2.hide();
                            break;
                        case R.id.nav_info:
                            selectedFragment = new NewFragmentDoctor();
                            fab2.hide();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,
                            selectedFragment).commit();
                    return true;
                }
            };
}
