package com.example.user.mynote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ReadNote extends AppCompatActivity {
    private NotesDAO noteDatabase=null;
    private TextView title=null;
    private TextView text=null;
    private long getId=0;

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
        getId=take.getLong("noteId");

        Note n=noteDatabase.getOneNote( getId );

        title.setText( n.getTitle() );
        text.setText( n.getText() );
    }
    public void onClick(View view){
        switch ( view.getId() ){
            case R.id.back:
                this.finish();
                break;
            case R.id.modify:
                Bundle b=new Bundle();
                b.putLong("Id",getId);

                Intent goToModifyNote=new Intent( ReadNote.this,ModifyNote.class );
                goToModifyNote.putExtras(b);

                startActivity( goToModifyNote );
                this.finish();
                break;
            case R.id.delete:
                if( noteDatabase.delete( getId ) ){
                    Toast.makeText(getApplicationContext(),"Delete done",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Delete fail",Toast.LENGTH_SHORT).show();
                }
                this.finish();
                break;
        }
    }
}
