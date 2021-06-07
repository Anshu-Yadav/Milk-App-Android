package com.pythonanywhere.anshu1yadav.milkapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DbHandler extends SQLiteOpenHelper {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDateTime now = LocalDateTime.now();

    ArrayList<String> arrayList = new ArrayList<>();
    Double totalMilk = 0.0;
    public DbHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String table = "CREATE TABLE milkrecords (sno INTEGER PRIMARY KEY, date TEXT, milk DOUBLE)";
        sqLiteDatabase.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String drop = String.valueOf("DROP TABLE IF EXISTS");
        sqLiteDatabase.execSQL(drop, new String[]{"milkrecords"});
        onCreate(sqLiteDatabase);
    }

    public void addMilk(MilkRecords milkRecords){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date", dtf.format(now));
        Log.d("mytag2", String.valueOf(milkRecords.getMilk())+"in click addmilk before ading confirm");
        values.put("milk", milkRecords.getMilk());
        Log.d("mytag2", String.valueOf(values)+ "++++");
        long k = db.insert("milkrecords", null, values);
        Log.d("mytag2", String.valueOf(milkRecords.getMilk())+"in click after set confirm");
        milkRecords.setMilk(0);
        db.close();
    }

    public void getMilk(String date){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor  = db.query("milkrecords", new String[]{"sno", "date", "milk"},
                "date=?", new String[]{date}, null,null,null);
        if(cursor != null && cursor.moveToFirst()){
            Log.d("mytag", cursor.getString(0));
            Log.d("mytag", cursor.getString(1));
            Log.d("mytag", cursor.getString(2));
        } else{
            Log.d("mytag", "error");
        }
        assert cursor != null;
        cursor.close();
    }

    public ArrayList<String> getMilkArraylist(){
        // Select All Query
        String selectQuery = "SELECT  * FROM milkrecords";

        SQLiteDatabase db = this.getReadableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    arrayList.clear();
                    while(cursor.moveToNext()) {
//                        MilkRecords obj = new MilkRecords();
//                        //only one column
//                        obj.setSno(cursor.getString(0));
//                        obj.setSno(cursor.getString(1));
//                        obj.setSno(cursor.getString(2));

                        //you could add additional columns here..
//                        arrayList.add(cursor.getString(0));
                        arrayList.add(String.valueOf(Integer.parseInt(cursor.getString(0))-1)+".   " + cursor.getString(1)+" - "+cursor.getString(2)+" litre");
//                        totalMilk = totalMilk + Double.parseDouble(cursor.getString(2));
//                        arrayList.add(cursor.getString(2));
//                        totalMilk = totalMilk + cursor.getDouble(2);

//                        arrayList.add(obj);
                    }
                }

            } finally {
                try { cursor.close(); } catch (Exception ignore) {}
            }

        } finally {
            try { db.close(); } catch (Exception ignore) {}
        }
        return arrayList;
    }

    public Double totalMilkReturn(){
        String selectQuery = "SELECT  * FROM milkrecords";

        SQLiteDatabase db = this.getReadableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    while (cursor.moveToNext()) {
//
                        totalMilk += Double.parseDouble(cursor.getString(2));
//
                    }
                }

            } finally {
                try { cursor.close(); } catch (Exception ignore) {}
            }

        } finally {
            try { db.close(); } catch (Exception ignore) {}
        }
        return totalMilk;
    }
//    public void deleteDatabase(SQLiteDatabase sqLiteDatabase){
//        String s = "DROP TABLE milkrecords";
//        sqLiteDatabase.execSQL(s, new String[]{"milkrecords"});
//        onCreate(sqLiteDatabase);
//    }

//    public void deleteCourse(String courseName) {
//
////         on below line we are creating
////         a variable to write our database.
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        // on below line we are calling a method to delete our
//        // course and we are comparing it with our course name.
//        db.delete("milkrecords", "date=?", new String[]{courseName});
//        db.close();
////        db.execSQL("DELETE FROM " + TABLE_NAME+ " WHERE "+COlUMN_NAME+"='"+value+"'");
//    }
    public void deleteDatabase(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ "milkrecords");
        db.close();
    }

    public void deleteRecord(int sno){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("DELETE FROM "+"milkrecords" + "WHERE sno=" + "'"+  sno +"'");
//        db.delete("milkrecords", "sno=?", new String[]{String.valueOf(sno)});
//        db.query("milkrecords", new String[]{"sno"}, "sno=?", new String[]{String.valsno}, null,null,null);
//        db.close();

    }
//    public void getsno(String date){
//        SQLiteDatabase db =this.getReadableDatabase();
//        Cursor cursor  = db.query("milkrecords", new String[]{"sno", "date", "milk"},
//                "date=?", new String[]{date}, null,null,null);
//        if(cursor != null && cursor.moveToFirst()){
//            Log.d("mytag", cursor.getString(0));
//            Log.d("mytag", cursor.getString(1));
//            Log.d("mytag", cursor.getString(2));
//        } else{
//            Log.d("mytag", "error");
//        }
//        assert cursor != null;
//        cursor.close();
//    }
//    public void giveDate(){
//
//    }
}

