package com.example.medireadynew;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class NewFragmentDoctor extends Fragment {
    TextView familyDoctor, phoneDoctor;
    Button editDataButton;
    public static final String DEFAULT = "not available";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.new_fragment_doctor,container, false);
        //((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Family Doctor Info");

        familyDoctor = v.findViewById(R.id.familyDoctor);
        phoneDoctor = v.findViewById(R.id.phoneDoctor);

        SharedPreferences sharedPrefs = getActivity().getSharedPreferences("DocData", Context.MODE_PRIVATE);

        String doctor = sharedPrefs.getString("doctor", DEFAULT);
        String phone = sharedPrefs.getString("phone", DEFAULT);

        familyDoctor.setText(doctor);
        phoneDoctor.setText(phone);

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
    }
}
