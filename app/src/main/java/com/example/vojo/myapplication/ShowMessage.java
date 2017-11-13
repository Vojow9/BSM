package com.example.vojo.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Vojo on 13.11.2017.
 */

public class ShowMessage extends Activity {

    private String text;
    private Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_message);

        TextView messageText = (TextView) findViewById(R.id.text_Message);
        Button changePass = (Button) findViewById(R.id.button_changePass);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("MESSAGE_ID");
            text = extras.getString("MESSAGE_TEXT");
        }

        messageText.setText(text);

        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(view.getContext(), ChangePass.class);
                myIntent.putExtra("MESSAGE_TEXT", text);
                myIntent.putExtra("MESSAGE_ID", id);
                startActivityForResult(myIntent, 0);


            }
        });

    }



}
