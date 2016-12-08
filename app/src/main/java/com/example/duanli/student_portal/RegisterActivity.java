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
        spdh = SPDatabaseHelper.getInstance(this);
        createInDatabase();
    }

    public void saveUser(String userName, String password, String confirmPassword, boolean istest) {
        // get Instance of Database Adapter
//        spdh = new SPDatabaseHelper(this);

        //loginDataBaseAdapter = loginDataBaseAdapter.open();
        // check if any of the fields are vacant
        if (userName.equals("") || password.equals("") || confirmPassword.equals("")) {
            if (!istest){ Toast.makeText(getApplicationContext(), "Field Vacant", Toast.LENGTH_LONG).show();}
            success = false;
            return;
        }
        // check if both password matches
        if (!password.equals(confirmPassword)) {
            if (!istest){Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();}
            success = false;
            return;
        } else {
                if(!isValidEmail(userName)){
                    if (!istest){Toast.makeText(RegisterActivity.this, "Username is not a valid email address", Toast.LENGTH_LONG).show();}
                    success = false;
                }
                if(!isValidPassword(password)){
                    if (!istest){Toast.makeText(RegisterActivity.this, "Password length should between 8 and 20 with at least 1 number, 1 lower case letter, 1 upper case letter", Toast.LENGTH_LONG).show();}
                    success = false;
                }
                if (isValidEmail(userName) && isValidPassword(password)){
                    User user = new User(ThisUser.getUserID(), userName, password);
                    if (!istest){spdh.insertUser(user);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();}
                    success = true;
                    if (!istest){startActivity(new Intent(getApplicationContext(), LoginActivity.class));}
                }

        }
    }

    //email has to contain @
    //Underscore and dot can't be at the end or start of a email address
    //Underscore and dot can't be next to each other (e.g user_.name).
    //Underscore or dot can't be used multiple times in a row (e.g user__name / user..name).name
    public static boolean isValidEmail(String s) {
        String EMAIL_REGEX = "^(?![_.])[a-zA-Z0-9._](?!.*[_.]{2})(.*)([@]{1})(.{1,})(\\.)(.{1,})+(?<![_.])$";
        if (!s.equals("")) {
            if (s.matches(EMAIL_REGEX))
                return true;}
        return false;
    }

    //password length should between 8 and 20
    //at least 1 number, 1 lower case letter, 1 upper case letter
    public static boolean isValidPassword(String s){
        String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$";
        if (!s.equals("")) {
            if (s.matches(PASSWORD_REGEX)) {
                return true;}}
        return false;
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