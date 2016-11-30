package com.example.duanli.student_portal;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final Button bEditProfile = (Button) findViewById(R.id.bEditProfile);

    }
    boolean success = false;
    public void ageChecker(String age, boolean isTest) {
        if (age.isEmpty()) {
            success = false;
            return;
        }
        for (int i = 0; i < age.length(); i++) {
            if (i == 0 && age.charAt(i) == '-') {
                if (age.length() == 1) {

                    success = false;
                    return;
                } else continue;
            }
            if (Integer.parseInt(age) < 0 || Integer.parseInt(age) > 100) {
                success = false;
                return;
            }


                else {

                    if (!isTest) {

                        //loginDataBaseAdapter.insertEntry(userName, password);
                    }

                    success = true;
                }
            }


        }

    public void emailChecker(String password,  boolean isTest) {


        // check if any of the fields are vacant
        if (password.equals("") ) {

            success = false;
            return;
        }
        // check if password contains"@"
        if (!password.contains("@")) {

            success = false;
            return;
        } else {
            if (!isTest){

                //loginDataBaseAdapter.insertEntry(userName, password);
            }

            success = true;
        }


    }

 public void phoneNumberChecker(String phoneNumber, boolean isTest){
    if(phoneNumber.equals("")){

        success = false;
        return;
    }

     if (phoneNumber.length() != 10) {
         success = false;
         return;

     }

     else{
         if (!isTest){

             //loginDataBaseAdapter.insertEntry(userName, password);
         }

         success = true;
     }
     }
 }

