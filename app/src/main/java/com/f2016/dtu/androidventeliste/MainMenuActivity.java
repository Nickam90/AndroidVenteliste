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
    int focusIndex;

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

        loginNumberInput1.setOnFocusChangeListener(focusChangeHandler);
        loginNumberInput2.setOnFocusChangeListener(focusChangeHandler);
        loginNumberInput3.setOnFocusChangeListener(focusChangeHandler);
        loginNumberInput4.setOnFocusChangeListener(focusChangeHandler);
        loginNumberInput5.setOnFocusChangeListener(focusChangeHandler);
        loginNumberInput6.setOnFocusChangeListener(focusChangeHandler);

        loginNumberInput1.requestFocus();

    }

    View.OnFocusChangeListener focusChangeHandler = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            //Log.d("KeyEvent UP", "Pressed " + v.getContext().getResources() + ", " + hasFocus);

            if(v == loginNumberInput1 && hasFocus){
                loginNumberInput1.setText("");
                focusIndex = 0;
            }
            else if(v == loginNumberInput2 && hasFocus){
                loginNumberInput2.setText("");
                focusIndex = 1;
            }
            else if(v == loginNumberInput3 && hasFocus){
                loginNumberInput3.setText("");
                focusIndex = 2;
            }
            else if(v == loginNumberInput4 && hasFocus){
                loginNumberInput4.setText("");
                focusIndex = 3;
            }
            else if(v == loginNumberInput5 && hasFocus){
                loginNumberInput5.setText("");
                focusIndex = 4;
            }
            else if(v == loginNumberInput6 && hasFocus){
                loginNumberInput6.setText("");
                focusIndex = 5;
            }
        }
    };

    View.OnKeyListener keyPressHandler = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if(event.getAction() == KeyEvent.ACTION_UP ) {
               // Log.d("KeyEvent UP", "Pressed " + event.getCharacters() + ", " + event.getKeyCode());
                if (event.getKeyCode() > 6 && event.getKeyCode() < 17) {
                    changeInputFocus(true);
                }
                return true;
            }
            return false;
        }
    };

    private void changeInputFocus(boolean right){
        int key = focusIndex;
        if(key < 5 && right){
            key++;
        }
        else if(key > 0 && !right){
            key--;
        }
        else{
            key = 0;
            login();

        }
        switch (key){
            case 0:
                loginNumberInput1.requestFocus();
                focusIndex = 0;
                break;
            case 1:
                loginNumberInput2.requestFocus();
                focusIndex = 1;
                break;
            case 2:
                loginNumberInput3.requestFocus();
                focusIndex = 2;
                break;
            case 3:
                loginNumberInput4.requestFocus();
                focusIndex = 3;
                break;
            case 4:
                loginNumberInput5.requestFocus();
                focusIndex = 4;
                break;
            case 5:
                loginNumberInput6.requestFocus();
                focusIndex = 5;
                break;
            default:
                break;
        }
    }

    private void login() {
Log.d("Login", "Login");
        String digit1 = loginNumberInput1.getText().toString();
        String digit2 = loginNumberInput2.getText().toString();
        String digit3 = loginNumberInput3.getText().toString();
        String digit4 = loginNumberInput4.getText().toString();
        String digit5 = loginNumberInput5.getText().toString();
        String digit6 = loginNumberInput6.getText().toString();

        String code = digit1+digit2+digit3+digit4+digit5+digit6;
        Log.d("Login", "Code: " + code);

        loginNumberInput1.setText("");
        loginNumberInput2.setText("");
        loginNumberInput3.setText("");
        loginNumberInput4.setText("");
        loginNumberInput5.setText("");
        loginNumberInput6.setText("");

    }
}
