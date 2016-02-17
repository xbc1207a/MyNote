package com.example.user.mynote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by user on 2016/2/14.
 */
public class NotesDAO {
    public static final String tableName="Notes";

    public static final String Key="_id";// long 0
    public static final String ColumnTitle="title";// string 1
    public static final String ColumnText="text";// string 2

    public static final String CreateTable="Create Table "+tableName+" ("+
            Key+" integer primary key autoincrement, "+
            ColumnTitle+" text not null, "+
            ColumnText+" text not null"+
            ");";

    private SQLiteDatabase db=null;

    public NotesDAO( Context context ){
        db=MyDBHelper.getDatabase(context);
    }
    public void close(){
        db.close();
    }

    public long insert(Note note){
        ContentValues cv=new ContentValues();

        cv.put(ColumnTitle,note.getTitle());
        cv.put(ColumnText,note.getText());

        long id=db.insert(tableName,null,cv);

        return id;
    }
    public boolean update(Note note){
        String where=Key+"="+note.getId();
        ContentValues cv=new ContentValues();

        cv.put(ColumnTitle,note.getTitle());
        cv.put(ColumnText,note.getText());

        return db.update(tableName,cv,where,null)>0;
    }
    public boolean delete(long id){
        String where=Key+"="+id;

        return db.delete(tableName, where, null)>0;
    }
    public ArrayList<Note> getAllNotes(){
        String sql="select * "+
                "from "+tableName;
        ArrayList<Note> notes=new ArrayList<>();
        Cursor result=db.rawQuery(sql,null);

        while ( result.moveToNext() ){
            Note temp=new Note();

            temp.setId(result.getLong(0));
            temp.setTitle(result.getString(1));
            temp.setText(result.getString(2));

            notes.add(temp);
        }

        result.close();
        return notes;
    }
    public Note getOneNote(long id){
        String sql="select * from "+tableName+" where "+Key+"="+id;
        Cursor result=db.rawQuery(sql,null);
        Note temp=new Note( result.getLong(0),result.getString(1),result.getString(2) );
        return temp;
    }
}
