package com.example.duanli.student_portal;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final TextView etUsername = (EditText) findViewById(R.id.etUsername);
        final TextView etUserID = (EditText) findViewById(R.id.etUserID);
        final TextView etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        final TextView etEmail = (EditText) findViewById(R.id.etEmail);
        final ImageView ivEdit = (ImageView) findViewById(R.id.ivEdit);
        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editProfileIntent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                ProfileActivity.this.startActivity(editProfileIntent);
            }
        });
    }


}





