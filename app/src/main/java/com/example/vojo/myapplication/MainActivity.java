package com.example.vojo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class MainActivity extends AppCompatActivity {

    private EditText editPassword;

    private Message myMessage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button showMessage = (Button) findViewById(R.id.button);
        Button addMessage = (Button) findViewById(R.id.button2);
        editPassword = (EditText) findViewById(R.id.editText);

        showMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (lookupMessage(view)==true) {



                    Intent myIntent = new Intent(view.getContext(), ShowMessage.class);
                    myIntent.putExtra("MESSAGE_TEXT", myMessage.getText());
                    myIntent.putExtra("MESSAGE_ID", myMessage.get_id());
                    startActivityForResult(myIntent, 0);

                }
                else{

                    Toast.makeText(getApplicationContext(), "Złe hasło", Toast.LENGTH_SHORT).show();


                }


                editPassword.setText("");



            }
        });

        addMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(view.getContext(),AddMessage.class);
                startActivityForResult(myIntent,0);

            }
        });


    }

    public boolean lookupMessage(View view){

        DBHandler dbHandler = new DBHandler(this);

        myMessage = dbHandler.findMessage(new String(Hex.encodeHex(DigestUtils.sha256(editPassword.getText().toString()))));

        if( myMessage != null){
            return true;
        }
        else return false;


    }
}
