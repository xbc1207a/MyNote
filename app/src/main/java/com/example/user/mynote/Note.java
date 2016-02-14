package com.example.user.mynote;

/**
 * Created by user on 2016/2/14.
 */
public class Note {
    private String title;
    private String text;
    private long id=0;

    public void setId( long id ){
        this.id=id;
    }
    public void setTitle( String title ){
        this.title=title;
    }
    public void setText( String text ){
        this.text=text;
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
}
