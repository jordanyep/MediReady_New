package com.example.medireadynew;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MediReady extends AppCompatActivity {
    DatabaseHelper helpher;
    List<DatabaseModel> dbList;
    RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medi_ready);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        helpher = new DatabaseHelper(this);
        dbList= new ArrayList<DatabaseModel>();
        dbList = helpher.getDataFromDB();


        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RecyclerAdapter(this,dbList);
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add a person here", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(MediReady.this,MainActivity.class));
            }
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    android.support.v4.app.Fragment selectedFragment = null;
                    switch(item.getItemId()) {
                        case R.id.nav_home:
//                            selectedFragment = new FragmentHome();
//                            break;
                            startActivity(new Intent(MediReady.this,MediReady.class));
                        case R.id.nav_addFam:
//                            selectedFragment = new FragmentMap();
//                            break;
                            startActivity(new Intent(MediReady.this,MainActivity.class));
                        case R.id.nav_info:
//                            selectedFragment = new FragmentDoctor();
//                            break;
                            startActivity(new Intent(MediReady.this,FamilyDoctor.class));
                    }

//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                            selectedFragment).commit();
                    return true;
                }
            };

}
