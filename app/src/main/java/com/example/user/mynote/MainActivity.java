package com.example.user.mynote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private NotesDAO notedb=null;
    private ListView list=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        notedb=new NotesDAO(this);
        findView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        notedb.close();
        super.onBackPressed();
    }
    @Override
    protected void onResume() {
        setListView();
        super.onResume();
    }
    public void findView(){
        list=(ListView)findViewById(R.id.noteList);
    }
    public void setListView(){
        ArrayList<Note> notes=notedb.getAllNotes();
        ArrayAdapter<String> adapter;
        ArrayList<String> strings=new ArrayList<>();

        if( notes.size()==0 ){
            String s="Nothing in the database";
            strings.add(s);
        }
        else{
            for(int i=0;i<notes.size();i++){
                strings.add( notes.get(i).getTitle() );
            }
        }
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,strings);
        list.setAdapter( adapter );
    }
}
