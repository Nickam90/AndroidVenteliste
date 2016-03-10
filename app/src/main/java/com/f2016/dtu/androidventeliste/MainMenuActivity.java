package com.f2016.dtu.androidventeliste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class MainMenuActivity extends AppCompatActivity {

    private EditText loginNumberInput1;
    private EditText loginNumberInput2;
    private EditText loginNumberInput3;
    private EditText loginNumberInput4;
    private EditText loginNumberInput5;
    private EditText loginNumberInput6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginNumberInput1 = (EditText)findViewById(R.id.loginNumberField1);
        loginNumberInput2 = (EditText)findViewById(R.id.loginNumberField2);
        loginNumberInput3 = (EditText)findViewById(R.id.loginNumberField3);
        loginNumberInput4 = (EditText)findViewById(R.id.loginNumberField4);
        loginNumberInput5 = (EditText)findViewById(R.id.loginNumberField5);
        loginNumberInput6 = (EditText)findViewById(R.id.loginNumberField6);

        loginNumberInput1.setOnKeyListener(keyPressHandler);
        loginNumberInput2.setOnKeyListener(keyPressHandler);
        loginNumberInput3.setOnKeyListener(keyPressHandler);
        loginNumberInput4.setOnKeyListener(keyPressHandler);
        loginNumberInput5.setOnKeyListener(keyPressHandler);
        loginNumberInput6.setOnKeyListener(keyPressHandler);

        loginNumberInput1.requestFocus();

    }

    View.OnKeyListener keyPressHandler = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if(event.getAction() == KeyEvent.ACTION_UP ){
                Log.d("KeyEvent UP", "Pressed " + event.getCharacters() + ", " + event.getKeyCode());

            }
            return false;
        }
    };

}
