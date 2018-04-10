package com.example.medireadynew;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private static final int ACTIVITY_START_CAMERA_APP = 1;

    EditText firstName,lastName,age,gender,relationship;
    EditText medicalID, allergies, medication, conditions;

    TextInputLayout text_input_first,text_input_last,text_input_gender,text_input_age,text_input_relationship,text_input_medicalID,text_input_allergies,text_input_medication,text_input_conditions;

    ImageView userPhoto;
    DatabaseHelper helpher;
    List<DatabaseModel> dbList;

    final int REQUEST_CAMERA = 999;

    AutoCompleteTextView genderAutoComplete;
    String[] genders = {
            "male",
            "female",
            "x",
            "non-binary",
            "transgender"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbList= new ArrayList<DatabaseModel>();

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        age = findViewById(R.id.age);
        //gender = findViewById(R.id.gender);
        relationship = findViewById(R.id.relationship);
        userPhoto = (ImageView) findViewById(R.id.imageViewPhoto);

        medicalID = findViewById(R.id.medicalID);
        allergies = findViewById(R.id.allergies);
        medication = findViewById(R.id.medication);
        conditions = findViewById(R.id.conditions);

        genderAutoComplete = (AutoCompleteTextView)findViewById(R.id.genderAutoComplete);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.select_dialog_item,genders);

        //waits for one character to show hint
        genderAutoComplete.setThreshold(1);
        genderAutoComplete.setAdapter(adapter);

        age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });


        text_input_first = findViewById(R.id.text_input_first);
        text_input_last = findViewById(R.id.text_input_last);
        text_input_gender = findViewById(R.id.text_input_gender);
        text_input_age = findViewById(R.id.text_input_age);
        text_input_relationship = findViewById(R.id.text_input_relationship);
        text_input_medicalID = findViewById(R.id.text_input_medicalID);
        text_input_allergies = findViewById(R.id.text_input_allergies);
        text_input_medication = findViewById(R.id.text_input_medication);
        text_input_conditions = findViewById(R.id.text_input_conditions);

    }

    public void takePhoto(View view) {
        //Toast.makeText(this, "the button is pressed", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(); //use this intent to trigger the camera
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, ACTIVITY_START_CAMERA_APP); //return to activity after photo has been taken
    }


    protected void onActivityResult (int requestCode, int resultCode, Intent data) { //data = thumbnail data. good to be used into a database?
        if(requestCode == ACTIVITY_START_CAMERA_APP && resultCode == RESULT_OK) {
            //Toast.makeText(this, "the cmaera has been triggered and pic taken", Toast.LENGTH_SHORT).show();
            Bundle extras = data.getExtras(); //bundle= a way to collect data

            Bitmap imageBitmap = (Bitmap) extras.get("data");
            userPhoto.setImageBitmap(imageBitmap);
//            imageByte(imageBitmap);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte imageInByte[] = stream.toByteArray();

            /*ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            //return byteArray;*/
        }
    }

    public static byte[] imageByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


    public void finished(View view) {
        String first = firstName.getText().toString();
        String last = lastName.getText().toString();
        //String gen = gender.getText().toString();
        String genderAutoComplete1 = genderAutoComplete.getText().toString();
        String age2 = age.getText().toString();
        String relat = relationship.getText().toString();

        String medicalID2 = medicalID.getText().toString();
        String allergies2 = allergies.getText().toString();
        String medication2 = medication.getText().toString();
        String conditions2 = conditions.getText().toString();

        if (first.equals("") || last.equals("") || genderAutoComplete1.equals("") || relat.equals("")|| medicalID2.equals("")|| allergies2.equals("")|| medication2.equals("")|| conditions2.equals("")    ) {
            Toast.makeText(this, "Not all fields are filled in.", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "" + first +" "+ last + " "+ genderAutoComplete1 + " "+ age2 + " "+ relat + " "+ medicalID2 + " "+ allergies2 +" "+ medication2 + " "+ conditions2, Toast.LENGTH_SHORT).show();
            helpher = new DatabaseHelper(MainActivity.this);
            helpher.insertIntoDB(first,last,genderAutoComplete1,age2,relat, medicalID2, allergies2, medication2, conditions2);

            //Intent intent = new Intent(this, MediReady.class);
            //Intent intent = new Intent(this, NewHome.class);
            Intent intent = new Intent(this, NewHomeTwo.class);
            startActivity(intent);

            firstName.setText("");
            lastName.setText("");
            genderAutoComplete.setText("");
            age.setText("");
            relationship.setText("");

            medicalID.setText("");
            allergies.setText("");
            medication.setText("");
            conditions.setText("");
        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());

        EditText age = (EditText) findViewById(R.id.age);
        age.setText(currentDateString);
    }

}
