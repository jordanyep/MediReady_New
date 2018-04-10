package com.example.medireadynew;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jordanyep on 2018-03-19.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="family";
    private static final int DATABASE_VERSION = 10;
    private static final String FAMILY_TABLE = "famreg";
    //private static final String FAM_TABLE = "create table "+FAMILY_TABLE +"(first TEXT,last TEXT primary key,age TEXT,gender TEXT,relationship TEXT,medicalID TEXT,allergies TEXT,medication TEXT,conditions TEXT)";
    private static final String FAM_TABLE = "create table "+FAMILY_TABLE +"(first TEXT primary key,last TEXT,age TEXT,gender TEXT,relationship TEXT,medicalID TEXT,allergies TEXT,medication TEXT,conditions TEXT)";
    //private static final String FAM_TABLE = "create table "+FAMILY_TABLE +"(last TEXT,first TEXT primary key,age TEXT,gender TEXT,relationship TEXT,medicalID TEXT,allergies TEXT,medication TEXT,conditions TEXT,image IMAGE)";

    Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(FAM_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + FAMILY_TABLE);

        // Create tables again
        onCreate(db);
    }

    /* Insert into database*/
    public void insertIntoDB(String first,String last,String age,String gender,String relationship, String medicalID, String allergies, String medication, String conditions){
    //public void insertIntoDB(String first,String last,String age,String gender,String relationship, String medicalID, String allergies, String medication, String conditions, byte[] image){
        Log.d("insert", "before insert");

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("first", first);
        values.put("last", last);
        values.put("age", age);
        values.put("gender", gender);
        values.put("relationship", relationship);
        values.put("medicalID", medicalID);
        values.put("allergies", allergies);
        values.put("medication", medication);
        values.put("conditions", conditions);

        //values.put("image", image);

        // 3. insert
        db.insert(FAMILY_TABLE, null, values);

        // 4. close
        db.close();
        Toast.makeText(context, "insert value", Toast.LENGTH_LONG);
        Log.i("insert into DB", "After insert");
    }
    /* Retrive  data from database */
    public List<DatabaseModel> getDataFromDB(){
        List<DatabaseModel> modelList = new ArrayList<DatabaseModel>();
        String query = "select * from "+FAMILY_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        /*if (cursor.moveToFirst()){
            do {
                DatabaseModel model = new DatabaseModel();
                model.setFirstName(cursor.getString(0));
                model.setLastName(cursor.getString(1));
                model.setAge(cursor.getString(2));
                model.setGender(cursor.getString(3));
                model.setRelationship(cursor.getString(4));

                modelList.add(model);
            }while (cursor.moveToNext());
        }*/

        if (cursor.moveToFirst()){
            do {
                DatabaseModel model = new DatabaseModel();
                model.setFirstName(cursor.getString(0));
                model.setLastName(cursor.getString(1));
                model.setAge(cursor.getString(2));
                model.setGender(cursor.getString(3));
                model.setRelationship(cursor.getString(4));

                model.setMedicalID(cursor.getString(5));
                model.setAllergies(cursor.getString(6));
                model.setMedication(cursor.getString(7));
                model.setConditions(cursor.getString(8));

                //model.setImage(cursor.getBlob(9));

                modelList.add(model);
            }while (cursor.moveToNext());
        }



        Log.d("family data", modelList.toString());


        return modelList;
    }




}
