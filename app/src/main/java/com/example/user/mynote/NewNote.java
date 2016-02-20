package com.example.user.mynote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewNote extends AppCompatActivity {
    private NotesDAO noteDatabase=null;
    private EditText title=null;
    private EditText text=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        noteDatabase=new NotesDAO(this);
        this.findView();
    }
    public void findView(){
        title=(EditText)findViewById(R.id.modifyTitle);
        text=(EditText)findViewById(R.id.text);
    }
    public void onClick(View event){
        switch ( event.getId() ){
            case R.id.cancel:
                this.finish();
                break;
            case R.id.finish:
                Editable stringOfTitle=title.getText();
                Editable stringOfText=text.getText();
                Note newNote=new Note( 0,stringOfTitle.toString(),stringOfText.toString() );

                if( noteDatabase.insert( newNote )==-1 ){
                    Toast.makeText(getApplicationContext(),"Save has been fail",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),newNote.getTitle()+" has been successful",Toast.LENGTH_SHORT).show();
                }

                this.finish();
                break;
        }
    }
}
