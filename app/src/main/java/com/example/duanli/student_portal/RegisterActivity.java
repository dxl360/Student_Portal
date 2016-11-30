package com.example.duanli.student_portal;

/**
 * Created by Peiyan on 2016/10/24.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONException;
//import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {


    EditText etUsername,etPassword,etConfirmPassword;
    Button btnCreateAccount;
    SPDatabaseHelper spdh;
    boolean success = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        createInDatabase();
    }

    public void saveUser(String userName, String password, String confirmPassword, boolean istest) {
        // get Instance of Database Adapter
        spdh = new SPDatabaseHelper(this);
        //loginDataBaseAdapter = loginDataBaseAdapter.open();
        // check if any of the fields are vacant
        if (userName.equals("") || password.equals("") || confirmPassword.equals("")) {
            //Toast.makeText(getApplicationContext(), "Field Vacant", Toast.LENGTH_LONG).show();
            success = false;
            return;
        }
        // check if both password matches
        if (!password.equals(confirmPassword)) {
            //Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
            success = false;
            return;
        } else {
            if (!istest){
                User user = new User(-1, userName, password);
                // Save the Data in Database
                spdh.insertUser(user);
            }
            //Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
            success = true;
        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //loginDataBaseAdapter.close();
    }

    public void createInDatabase() {
        // Get References of Views
        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);
        etConfirmPassword=(EditText)findViewById(R.id.etConfirmPassword);

        btnCreateAccount=(Button)findViewById(R.id.bRegister);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                String userName=etUsername.getText().toString();
                String password=etPassword.getText().toString();
                String confirmPassword=etConfirmPassword.getText().toString();
                saveUser(userName, password, confirmPassword, false);
            }
        });
    }


}