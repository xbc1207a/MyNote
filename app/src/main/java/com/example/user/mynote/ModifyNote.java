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
    private int year=0,month=0,day=0,hour=0,minute=0;

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

        Note note=noteDatabase.getOneNote( getId );

        title.setText( note.getTitle() );
        text.setText( note.getText() );
        // set the DatePicker
        date.init( note.getYear(),note.getMonth(),note.getDay(),
                new DatePicker.OnDateChangedListener(){
                    @Override
                public void onDateChanged(DatePicker view,int getYear,int getMonth,int getDay){
                        year=getYear;
                        month=getMonth;
                        day=getDay;
                    }
                });
        // set the TimePicker
        time.setIs24HourView(true);
        time.setOnTimeChangedListener(
                new TimePicker.OnTimeChangedListener(){
                    @Override
                public void onTimeChanged(TimePicker view,int getHour,int getMinute){
                        hour=getHour;
                        minute=getMinute;
                    }
                }
        );
    }
    public void onClick(View view){
        switch ( view.getId() ){
            case R.id.modifyButton:
                Editable stringOfTitle=title.getText();
                Editable stringOfText=text.getText();
                Note modifyNote=new Note( getId,stringOfTitle.toString(),stringOfText.toString(),year,month,day,hour,minute );

                if( noteDatabase.update(modifyNote) ) Toast.makeText(getApplicationContext(),"Modify done",Toast.LENGTH_SHORT).show();
                else Toast.makeText(getApplicationContext(),"Modify fail",Toast.LENGTH_SHORT).show();

                this.finish();
                break;
            case R.id.cancelButton:
                Toast.makeText(getApplicationContext(),"Nothing change",Toast.LENGTH_SHORT).show();

                this.finish();
                break;
        }
    }
}
