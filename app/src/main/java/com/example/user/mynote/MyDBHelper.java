package com.example.user.mynote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 2016/2/14.
 */
public class MyDBHelper extends SQLiteOpenHelper {
    public static final String databaseName="database.db";
    public static final int Version=1;
    public static SQLiteDatabase database;

    public MyDBHelper( Context context,String name,SQLiteDatabase.CursorFactory factory,int version ){
        super(context,name,factory,version);
    }

    public static SQLiteDatabase getDatabase( Context context ){
        if( database==null || !database.isOpen() ){
            database=new MyDBHelper( context,databaseName,null,Version ).getReadableDatabase();
        }
        return database;
    }

    @Override
    public void onCreate( SQLiteDatabase db ){
        db.execSQL( NotesDAO.CreateTable );
    }
    @Override
    public void onUpgrade( SQLiteDatabase db,int oldVersion,int newVersion ){
        db.execSQL("Drop table if exists "+NotesDAO.tableName);
        onCreate(db);
    }
}
