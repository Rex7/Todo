package com.example.regischarles.sometodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static  String database_Name="todo.db";
   private String table_Name="UserTodo";
   private String name="Name";
   private String email="Email";
   private String password="password";
   private String date ="assignedDate";

   private String query=" DROP TABLE IF EXISTS " +table_Name;
  private  String sqlQuery="CREATE TABLE  " + table_Name + "(\n" +
            "\t`UserId`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t" + name + "\tTEXT NOT NULL,\n" +
            "\t" + email + "\tTEXT NOT NULL,\n" +
            "\t" + date + "\tdate  ,\n" +
            "\t" + password + "\tTEXT NOT NULL\n" +

            ");";
   private  String taskCreate="CREATE TABLE \"task\" (\n" +
            "\t\"username\"\tTEXT,\n" +
            "\t\"task\"\tTEXT NOT NULL,\n" +
           "\t\"subject\"\tTEXT NOT NULL,\n" +

            "\t\"status\"\tTEXT NOT NULL,\n" +
           "\t`TaskId`\tINTEGER PRIMARY KEY AUTOINCREMENT\n" +
            ")";
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, database_Name, factory, 5);
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

        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor = null;
        try
        {
            int i;


          db.rawQuery("select * from " + table_Name + " where `" + email + "` =  '"+emailId+"' and   `" + password+
                    "` = '"+pass+"'",null);
           cursor=  db.query(table_Name, new String[] { "Name", "Email", "password"},
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
        finally {
            if(cursor!=null){
                cursor.close();
            }

        }
        return "failure";






    }
    public long addTask(Task task){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",task.getUsername());
        contentValues.put("task",task.getTask());
        contentValues.put("status",task.getStatus());
        contentValues.put("subject",task.getSubject());
        long val =database.insert("task",null,contentValues);
        Log.v("UniqueTag ","addTask "+val);

        return val;
    }
    public int updateTask(int position){
        int count=0;
        try {
            Cursor cursor = getWritableDatabase().rawQuery("update task set status = ' finished ' where TaskId = " + position, null);
             count = cursor.getCount();
        }
        catch (Exception ex){
            Log.v("UniqueTag","exception"+ex.getMessage());
        }
        return count;
    }
    public ArrayList<Task> getAllRecord(SessionManage sessionManage){
        ArrayList<Task> taskList =new ArrayList<>();
    Cursor cursor=getReadableDatabase().rawQuery("select * from  task where username = '" +sessionManage.getUsername()+"'" ,null);
    Log.v("UniqueTag","Count in ArrayList "+cursor.getCount());
        Log.v("UniqueTag","Username in ArrayList "+sessionManage.getUsername());
    if(cursor.getCount()>0){
        while (cursor.moveToNext()){
            String task=cursor.getString(cursor.getColumnIndex("task"));
            String title=cursor.getString(cursor.getColumnIndex("subject"));
            String status=cursor.getString(cursor.getColumnIndex("status"));
            String username=cursor.getString(cursor.getColumnIndex("username"));
            int taskId=cursor.getInt(cursor.getColumnIndex("TaskId"));
            if(!status.trim().equals("finished")){
                taskList.add(new Task(title,task,status,username,taskId));
            }

        }
    }
    cursor.close();

    return  taskList;
    }
    public ArrayList<Task> getAllFinishedTask(SessionManage sessionManage){
        ArrayList<Task> taskList =new ArrayList<>();
        Cursor cursor=getReadableDatabase().rawQuery("select * from  task where username = '" +sessionManage.getUsername()+"'" ,null);
        Log.v("UniqueTag","Count in ArrayList "+cursor.getCount());
        Log.v("UniqueTag","Username in ArrayList "+sessionManage.getUsername());
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                String task=cursor.getString(cursor.getColumnIndex("task"));
                String title=cursor.getString(cursor.getColumnIndex("subject"));
                String status=cursor.getString(cursor.getColumnIndex("status"));
                String username=cursor.getString(cursor.getColumnIndex("username"));
                int taskId=cursor.getInt(cursor.getColumnIndex("TaskId"));
                if(status.trim().equals("finished")){
                    taskList.add(new Task(title,task,status,username,taskId));
                }

            }
        }
        cursor.close();

        return  taskList;
    }
    public String getUserName(String email ,String password){
        Log.v("UniqueTag","email "+email+"password "+password);
        String username = "";
      try{
          Cursor cursor=getWritableDatabase().rawQuery("SELECT Name from UserTodo where password = "+password +"  and Email='"+ email+"'",null);
          cursor.moveToFirst();
          username=cursor.getString(0);
          Log.v("UniqueTag","Count of user "+cursor.getCount()+"name "+cursor.getString(0));

      }
      catch (Exception ex){
          Log.v("UniqueTag"," messag bbbb b \\"+ex.getMessage());
      }

      return username;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
   db.execSQL(sqlQuery);
   db.execSQL(taskCreate);
        Log.d("databasehelper", "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL(query);
     onCreate(db);
    }
}
