package com.example.vojo.myapplication;

/**
 * Created by Vojo on 13.11.2017.
 */

public class Message {


    private int _id;
    private String password;
    private String text;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Message(){

    }

    public Message(String password, String text){
        this.password = password;
        this.text = text;
    }
}
