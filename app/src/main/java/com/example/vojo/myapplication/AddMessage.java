package com.example.vojo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Vojo on 13.11.2017.
 */

public class AddMessage extends AppCompatActivity {

    private String text;
    private String sha256hex;

    EditText editPassword;
    EditText editMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_message);

        editPassword = (EditText) findViewById(R.id.editText2);
        editMessage = (EditText) findViewById(R.id.editText3);
        Button save = (Button) findViewById(R.id.button3);

        editPassword.requestFocus();
        editMessage.requestFocus();




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                sha256hex = new String(Hex.encodeHex(DigestUtils.sha256(editPassword.getText().toString())));
                text = editMessage.getText().toString();
                Log.d("Password: ",sha256hex);
                Log.d("Text: ",text);


                newMessage(view);

                finish();
            }
        });
    }

    public void newMessage(View view){

        DBHandler dbHandler =  new DBHandler(this);





        Message message = new Message(sha256hex,text);

        dbHandler.addMessage(message);
        editPassword.setText("");
        editMessage.setText("");


    }




}
