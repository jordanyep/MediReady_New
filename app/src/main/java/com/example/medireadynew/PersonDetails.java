package com.example.medireadynew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PersonDetails extends AppCompatActivity {
    DatabaseHelper helpher;
    List<DatabaseModel> dbList;
    int position;
    TextView firstName,lastName,age,gender,relationship;
    TextView medicalID, allergies, medication, conditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        position = bundle.getInt("position");

        firstName =(TextView)findViewById(R.id.firstName);
        lastName =(TextView)findViewById(R.id.lastName);
        age =(TextView)findViewById(R.id.age);
        gender =(TextView)findViewById(R.id.gender);
        relationship =(TextView)findViewById(R.id.relationship);

        medicalID = findViewById(R.id.medicalID);
        allergies = findViewById(R.id.allergies);
        medication = findViewById(R.id.medication);
        conditions = findViewById(R.id.conditions);

        helpher = new DatabaseHelper(this);
        dbList= new ArrayList<DatabaseModel>();
        dbList = helpher.getDataFromDB();

        if(dbList.size()>0){
            String firstName1= dbList.get(position).getFirstName();
            String lastName1=dbList.get(position).getLastName();
            String age1=dbList.get(position).getAge();
            String gender1=dbList.get(position).getGender();
            String relationship1=dbList.get(position).getRelationship();
            String medicalID1=dbList.get(position).getMedicalID();
            String allergies1=dbList.get(position).getAllergies();
            String medication1=dbList.get(position).getMedication();
            String conditions1=dbList.get(position).getConditions();


            firstName.setText(firstName1);
            lastName.setText(lastName1);
            age.setText(age1);
            gender.setText(gender1);
            relationship.setText(relationship1);
            medicalID.setText(medicalID1);
            allergies.setText(allergies1);
            medication.setText(medication1);
            conditions.setText(conditions1);

            getSupportActionBar().setTitle("Information for " + lastName1 );
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Toast.makeText(PersonDetails.this, dbList.toString(), Toast.LENGTH_LONG);
    }
}

