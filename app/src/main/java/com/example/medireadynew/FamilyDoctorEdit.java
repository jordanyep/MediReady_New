package com.example.medireadynew;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FamilyDoctorEdit extends AppCompatActivity {
    EditText etFamDoc, etPhoneDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_doctor_edit);

        etFamDoc = findViewById(R.id.etFamDoc);
        etPhoneDoc = findViewById(R.id.etPhoneDoc);

        getSupportActionBar().setTitle("Edit Family Doctor Info" );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void submitButton (View view) {
        SharedPreferences sharedPrefs = getSharedPreferences("DocData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("doctor", etFamDoc.getText().toString());
        editor.putString("phone", etPhoneDoc.getText().toString());
        Toast.makeText(this, "family doctor info saved", Toast.LENGTH_LONG).show();
        editor.commit();

        Intent intent = new Intent(this,FamilyDoctor.class);
        startActivity(intent);
    }
}
