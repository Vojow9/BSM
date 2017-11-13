package com.example.vojo.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;


/**
 * Created by Vojo on 13.11.2017.
 */

public class ChangePass extends Activity {

    private TextView pass;
    private Button save;
    private Integer id;
    private String text;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pass);

        pass = (TextView) findViewById(R.id.edit_pass);
        save = (Button) findViewById(R.id.button_save_pass);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("MESSAGE_ID");
            text = extras.getString("MESSAGE_TEXT");
        }

        pass.requestFocus();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDB(view);

                finish();
            }
        });

    }

    public void updateDB(View view){

        DBHandler dbHandler = new DBHandler(this);

        dbHandler.updateMessage(id,new String(Hex.encodeHex(DigestUtils.sha256(pass.getText().toString()))),text);

    }
}
