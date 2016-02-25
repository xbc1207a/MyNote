package com.example.user.mynote;

/**
 * Created by user on 2016/2/14.
 */
public class Note {
    private long id=0;
    private String title;
    private String text;
    private int year=0,month=0,day=0,hour=0,minute=0;
    /* month : 0 to 11 */

    public Note(){}
    public Note(long id,String title,String text){
        this.id=id;
        this.title=title;
        this.text=text;
    }
    public Note(long id,String title,String text,int year,int month,int day,int hour,int minute){
        this.id=id;
        this.title=title;
        this.text=text;
        this.year=year;
        this.month=month;
        this.day=day;
        this.hour=hour;
        this.minute=minute;
    }

    public void setId( long id ){
        this.id=id;
    }
    public void setTitle( String title ){
        this.title=title;
    }
    public void setText( String text ){
        this.text=text;
    }
    public void setTime(int year,int month,int day,int hour,int minute){
        this.year=year;
        this.month=month;
        this.day=day;
        this.hour=hour;
        this.minute=minute;
    }
    public void setYear(int year){
        this.year=year;
    }
    public void setMonth(int month){
        this.month=month;
    }
    public void setDay(int day){
        this.day=day;
    }
    public void setHour(int hour){
        this.hour=hour;
    }
    public void setMinute(int minute){
        this.minute=minute;
    }

    public long getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }
    public String getText(){
        return this.text;
    }
    public int getYear(){
        return this.year;
    }
    public int getMonth(){
        return this.month;
    }
    public int getDay(){
        return this.day;
    }
    public int getHour(){
        return this.hour;
    }
    public int getMinute(){
        return this.minute;
    }
}
