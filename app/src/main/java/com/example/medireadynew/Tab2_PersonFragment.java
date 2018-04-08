package com.example.medireadynew;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Tab2_PersonFragment extends Fragment{
    private static final String TAG = "Tab2_PersonFragment";

    DatabaseHelper helpher;
    List<DatabaseModel> dbList;
    int position;
    TextView firstName,lastName,age,gender,relationship;
    TextView medicalID, allergies, medication, conditions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_persondetail2,container,false);

        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();

        position = bundle.getInt("position");

        firstName =view.findViewById(R.id.firstName);
        lastName =view.findViewById(R.id.lastName);
        age =view.findViewById(R.id.age);
        gender =view.findViewById(R.id.gender);
        relationship =view.findViewById(R.id.relationship);

        medicalID = view.findViewById(R.id.medicalID);
        allergies = view.findViewById(R.id.allergies);
        medication = view.findViewById(R.id.medication);
        conditions = view.findViewById(R.id.conditions);

        //helpher = new DatabaseHelper(helpher);
        helpher = new DatabaseHelper(getActivity());
        dbList= new ArrayList<DatabaseModel>();
        dbList = helpher.getDataFromDB();

        if(dbList.size()>5){
//            String firstName1= dbList.get(position).getFirstName();
//            String lastName1=dbList.get(position).getLastName();
//            String age1=dbList.get(position).getAge();
//            String gender1=dbList.get(position).getGender();
            String relationship1=dbList.get(position).getRelationship();
            String medicalID1=dbList.get(position).getMedicalID();
            String allergies1=dbList.get(position).getAllergies();
            String medication1=dbList.get(position).getMedication();
            String conditions1=dbList.get(position).getConditions();


//            firstName.setText(firstName1);
//            lastName.setText(lastName1);
//            age.setText(age1);
//            gender.setText(gender1);
            relationship.setText(relationship1);
            medicalID.setText(medicalID1);
            allergies.setText(allergies1);
            medication.setText(medication1);
            conditions.setText(conditions1);


        }

//        Toast.makeText(PersonDetails.this, dbList.toString(), Toast.LENGTH_LONG);
        return view;
    }
}
