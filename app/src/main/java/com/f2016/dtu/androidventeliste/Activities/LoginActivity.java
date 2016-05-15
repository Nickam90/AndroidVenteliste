package com.f2016.dtu.androidventeliste.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import com.f2016.dtu.androidventeliste.Utils.DataAccess;
import com.f2016.dtu.androidventeliste.R;
import com.f2016.dtu.androidventeliste.Utils.UserSession;

public class LoginActivity extends AppCompatActivity {
    private Handler customHandler = new Handler();
    private EditText loginNumberInput1;
    private EditText loginNumberInput2;
    private EditText loginNumberInput3;
    private EditText loginNumberInput4;
    private EditText loginNumberInput5;
    private EditText loginNumberInput6;
    private int focusIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UserSession.setUserAuth(false);
        //customHandler.postDelayed(checkLoggedInThread, 0);

        loginNumberInput1 = (EditText)findViewById(R.id.loginNumberField1);
        loginNumberInput2 = (EditText)findViewById(R.id.loginNumberField2);
        loginNumberInput3 = (EditText)findViewById(R.id.loginNumberField3);
        loginNumberInput4 = (EditText)findViewById(R.id.loginNumberField4);
        loginNumberInput5 = (EditText)findViewById(R.id.loginNumberField5);
        loginNumberInput6 = (EditText)findViewById(R.id.loginNumberField6);

        loginNumberInput1.addTextChangedListener(new onTextChangeListner());
        loginNumberInput2.addTextChangedListener(new onTextChangeListner());
        loginNumberInput3.addTextChangedListener(new onTextChangeListner());
        loginNumberInput4.addTextChangedListener(new onTextChangeListner());
        loginNumberInput5.addTextChangedListener(new onTextChangeListner());
        loginNumberInput6.addTextChangedListener(new onTextChangeListner());

        loginNumberInput1.setOnFocusChangeListener(focusChangeHandler);
        loginNumberInput2.setOnFocusChangeListener(focusChangeHandler);
        loginNumberInput3.setOnFocusChangeListener(focusChangeHandler);
        loginNumberInput4.setOnFocusChangeListener(focusChangeHandler);
        loginNumberInput5.setOnFocusChangeListener(focusChangeHandler);
        loginNumberInput6.setOnFocusChangeListener(focusChangeHandler);

        loginNumberInput1.requestFocus();

    }

    @Override
    public void onResume() {
        super.onResume();
        UserSession.setUserAuth(false);
        customHandler.postDelayed(checkLoggedInThread, 0);
    }
    @Override
    public void onPause() {
        super.onPause();
        customHandler.removeCallbacks(checkLoggedInThread);
    }

    View.OnFocusChangeListener focusChangeHandler = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
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

    private void changeInputFocus(boolean right) {
        int key = focusIndex;
        if (key < 5 && right) {
            key++;
        } else if (key > 0 && !right) {
            key--;
        } else {
            key = 0;
            login();
        }
        setFocus(key);
    }
        private void setFocus(int key){
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
        String digit1 = loginNumberInput1.getText().toString();
        String digit2 = loginNumberInput2.getText().toString();
        String digit3 = loginNumberInput3.getText().toString();
        String digit4 = loginNumberInput4.getText().toString();
        String digit5 = loginNumberInput5.getText().toString();
        String digit6 = loginNumberInput6.getText().toString();
        String code = digit1 + digit2 + digit3 + digit4 + digit5 + digit6;
        loginNumberInput1.setText("");
        loginNumberInput2.setText("");
        loginNumberInput3.setText("");
        loginNumberInput4.setText("");
        loginNumberInput5.setText("");
        loginNumberInput6.setText("");
        new DataAccess().loginUser(code);
    }

    private boolean checkNumberInput(String input){
        if(input.matches("\\d")) return true;
        return false;
    }

    private void moveToMain(){
        startActivity(new Intent(this, MainActivity.class));
    }

    private Runnable checkLoggedInThread = new Runnable() {

        public void run() {
            if(UserSession.getUserAuth()) {
                moveToMain();
                customHandler.removeCallbacks(this);
            }
            else{
                customHandler.postDelayed(this, 500);
            }
        }
    };

    class onTextChangeListner implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(checkNumberInput(s.toString())){
                changeInputFocus(true);
            }
        }
    }
}


