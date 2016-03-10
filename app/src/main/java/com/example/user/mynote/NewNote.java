package com.example.user.mynote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class NewNote extends AppCompatActivity {
    private NotesDAO noteDatabase=null;
    private EditText title=null;
    private EditText text=null;
    private DatePicker date=null;
    private TimePicker time=null;
    private int year=0,month=0,day=0,hour=0,minute=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        noteDatabase=new NotesDAO(this);
        this.findView();
        this.setContent();
    }
    public void findView(){
        title=(EditText)findViewById(R.id.modifyTitle);
        text=(EditText)findViewById(R.id.text);
        date=(DatePicker)findViewById(R.id.datePicker);
        time=(TimePicker)findViewById(R.id.timePicker);
    }
    public void setContent(){
        Calendar today=Calendar.getInstance();
        // set the datePicker
        date.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int getYear, int getMonth, int getDay) {
                        year = getYear;
                        month = getMonth;
                        day = getDay;
                    }
                });
        // set the timePicker
        time.setIs24HourView(true);
        time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener(){
            @Override
            public void onTimeChanged(TimePicker view,int getHour,int getMinute){
                hour=getHour;
                minute=getMinute;
            }
        });
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
                newNote.setTime(year,month,day,hour,minute);

                if( noteDatabase.insert( newNote )==-1 ) Toast.makeText(getApplicationContext(),"Save has been fail",Toast.LENGTH_SHORT).show();
                else Toast.makeText(getApplicationContext(),newNote.getTitle()+" has been successful",Toast.LENGTH_SHORT).show();

                this.finish();
                break;
        }
    }
}
