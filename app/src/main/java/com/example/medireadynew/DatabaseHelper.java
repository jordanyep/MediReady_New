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
    private static final String DATABASE_NAME="student";
    private static final int DATABASE_VERSION = 3;
    private static final String STUDENT_TABLE = "stureg";
    private static final String STU_TABLE = "create table "+STUDENT_TABLE +"(last TEXT,first TEXT primary key,age TEXT,gender TEXT,relationship TEXT)";

    Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(STU_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);

        // Create tables again
        onCreate(db);
    }

    /* Insert into database*/
    public void insertIntoDB(String first,String last,String age,String gender,String relationship){
        Log.d("insert", "before insert");

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("last", last);
        values.put("first", first);
        values.put("age", age);
        values.put("gender", gender);
        values.put("relationship", relationship);

        // 3. insert
        db.insert(STUDENT_TABLE, null, values);

        // 4. close
        db.close();
        Toast.makeText(context, "insert value", Toast.LENGTH_LONG);
        Log.i("insert into DB", "After insert");
    }
    /* Retrive  data from database */
    public List<DatabaseModel> getDataFromDB(){
        List<DatabaseModel> modelList = new ArrayList<DatabaseModel>();
        String query = "select * from "+STUDENT_TABLE;

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

                modelList.add(model);
            }while (cursor.moveToNext());
        }



        Log.d("student data", modelList.toString());


        return modelList;
    }


    /*delete a row from database*/

    public void deleteARow(String email){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(STUDENT_TABLE, "email" + " = ?", new String[] { email });
        db.close();
    }

}
