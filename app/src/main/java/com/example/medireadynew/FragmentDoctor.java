package com.example.medireadynew;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by jordanyep on 2018-03-20.
 */

public class FragmentDoctor extends Fragment {
    TextView familyDoctor, phoneDoctor;
    Button editDataButton;
    public static final String DEFAULT = "not available";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            //return inflater.inflate(R.layout.fragment_doctor, container, false);
        View v = inflater.inflate(R.layout.fragment_doctor, container, false);

        familyDoctor = v.findViewById(R.id.familyDoctor);
        phoneDoctor = v.findViewById(R.id.phoneDoctor);

        SharedPreferences sharedPrefs = getActivity().getSharedPreferences("DocData", Context.MODE_PRIVATE);
//        SharedPreferences sharedPrefs = this.getActivity().getSharedPreferences("DocData", Context.MODE_PRIVATE);

        String doctor = sharedPrefs.getString("doctor", DEFAULT);
        String phone = sharedPrefs.getString("phone", DEFAULT);

        familyDoctor.setText(doctor);
        phoneDoctor.setText(phone);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Family Doctor Info");
        //getSupportActionBar().setTitle("Family Doctor Info");

        Button editDataButton = (Button) v.findViewById(R.id.editDataButton);
        editDataButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), FamilyDoctorEdit.class);
                //Intent intent = new Intent(this, FamilyDoctorEdit.class);
                startActivity(intent);
            }
        });

        return v;



        /*familyDoctor = findViewById(R.id.familyDoctor);
        phoneDoctor = findViewById(R.id.phoneDoctor);

        SharedPreferences sharedPrefs = getSharedPreferences("DocData", Context.MODE_PRIVATE);
        String doctor = sharedPrefs.getString("doctor", DEFAULT);
        String phone = sharedPrefs.getString("phone", DEFAULT);

        familyDoctor.setText(doctor);
        phoneDoctor.setText(phone);


        getSupportActionBar().setTitle("Family Doctor Info");*/

    }

    /*public void editDoctor(View view) {

        Intent intent = new Intent(getActivity(), FamilyDoctorEdit.class);
        //Intent intent = new Intent(this, FamilyDoctorEdit.class);
        startActivity(intent);
    }*/


}
