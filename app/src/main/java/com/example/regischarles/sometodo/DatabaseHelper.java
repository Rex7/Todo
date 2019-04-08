package com.example.regischarles.sometodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static  String database_Name="todo.db";
    String table_Name="UserTodo";
    String name="Name";
    String email="Email";
    String password="password";
    String date ="assignedDate";
    String query=" DROP TABLE IF EXISTS " +table_Name;
    String sqlQuery="CREATE TABLE  " + table_Name + "(\n" +
            "\t`UserId`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t" + name + "\tTEXT NOT NULL,\n" +
            "\t" + email + "\tTEXT NOT NULL,\n" +
            "\t" + date + "\tdate  ,\n" +
            "\t" + password + "\tTEXT NOT NULL\n" +

            ");";
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, database_Name, factory, version);
    }
    public long registerUser(User reg){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(name,reg.getName());
        contentValues.put(password,reg.getPassword());
        contentValues.put(email,reg.getEmail());
        long val =database.insert(table_Name,null,contentValues);
        Log.v("UniqueTag ","value "+val);

        return val;


    }
    public String checkUser(String emailId,String pass){
        String status="failure";
        SQLiteDatabase db=getWritableDatabase();
        String[] selectionArgs = new String[]{emailId.trim(), pass.trim()};
        try
        {
            int i;


          db.rawQuery("select * from " + table_Name + " where `" + email + "` =  '"+emailId+"' and   `" + password+
                    "` = '"+pass+"'",null);
            Cursor cursor=   db.query(table_Name, new String[] { "Name", "Email", "password"},
                    "Email like '" + emailId.trim() + "' AND " + "password like '" + pass.trim()+"'", null, null, null, null);

            i = cursor.getCount();
            Log.v("DatabaseHandler","coiunt "+i);

           if(i>0){
               return  "success";
           }
        }
        catch(Exception e)
        {
            Log.v("ExceptionData",e.getMessage());
        }
        return "failure";






    }
    @Override
    public void onCreate(SQLiteDatabase db) {
   db.execSQL(sqlQuery);
        Log.d("databasehelper", "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL(query);
     onCreate(db);
    }
}
