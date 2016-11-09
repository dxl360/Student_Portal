package com.example.duanli.student_portal;

/**
 * Created by Peiyan on 2016/10/24.
 */

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
}