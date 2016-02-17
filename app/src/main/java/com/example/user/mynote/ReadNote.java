package com.example.user.mynote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReadNote extends AppCompatActivity {
    private NotesDAO noteDatabase=null;
    private TextView title=null;
    private TextView text=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_note);

        noteDatabase=new NotesDAO(this);
        findView();
        setTextView();
    }
    public void findView(){
        title=(TextView)findViewById(R.id.readTitle);
        text=(TextView)findViewById(R.id.readText);
    }
    public void setTextView(){
        Bundle take=getIntent().getExtras();
        long getId=take.getLong("noteId");

        Note n=noteDatabase.getOneNote( getId );

        title.setText( "Title : "+n.getTitle() );
        text.setText( "Text : "+n.getText() );
    }
}
