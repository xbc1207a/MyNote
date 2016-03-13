package com.example.user.mynote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class ModifyNote extends AppCompatActivity {
    private EditText title=null,text=null;
    private long getId=0;
    private NotesDAO noteDatabase=null;
    private DatePicker date=null;
    private TimePicker time=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_note);

        noteDatabase=new NotesDAO(this);
        findView();
        setText();
    }
    public void findView(){
        title=(EditText)findViewById(R.id.modifyTitle);
        text=(EditText)findViewById(R.id.modifyText);
        date=(DatePicker)findViewById(R.id.datePicker2);
        time=(TimePicker)findViewById(R.id.timePicker2);
    }
    public void setText(){
        Bundle take=getIntent().getExtras();
        getId=take.getLong("Id");

        Note n=noteDatabase.getOneNote( getId );

        title.setText( n.getTitle() );
        text.setText( n.getText() );
    }
    public void onClick(View view){
        switch ( view.getId() ){
            case R.id.modifyButton:
                Editable stringOfTitle=title.getText();
                Editable stringOfText=text.getText();
                Note modifyNote=new Note( getId,stringOfTitle.toString(),stringOfText.toString() );

                if( noteDatabase.update(modifyNote) ){
                    Toast.makeText(getApplicationContext(),"Modify done",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Modify fail",Toast.LENGTH_SHORT).show();
                }

                this.finish();
                break;
            case R.id.cancelButton:
                this.finish();
                break;
        }
    }
}
