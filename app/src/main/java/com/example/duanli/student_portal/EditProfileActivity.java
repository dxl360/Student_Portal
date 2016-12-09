package com.example.duanli.student_portal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EditProfileActivity extends Fragment {

    SPDatabaseHelper spdh;
    boolean success = false;
    // empty constructor
    public EditProfileActivity() {
    }

    /**
     * Method that processes the xml file and pulls/pushes the changes to the screen
     *
     * @param inflater           LayoutInflater that will inflate fragment view
     * @param container          view the fragment's UI atached to
     * @param savedInstanceState fragment being reconstructed from a previous state
     * @return what the screen should look  like
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_edit_profile, container, false);
        spdh = SPDatabaseHelper.getInstance(this.getContext());
        // linking xml objects to actual fields
        rootView.findViewById(R.id.tvUsername);
        rootView.findViewById(R.id.tvPassword);
        final EditText etUsername = (EditText) rootView.findViewById(R.id.etUsername);
        etUsername.setText(spdh.queryUserID(ThisUser.getUserID()).getUserName());
        final EditText etPassword = (EditText)rootView.findViewById(R.id.etPassword);
        etPassword.setText(spdh.queryUserID(ThisUser.getUserID()).getPassword());
        // button interactions (onClick)
        final Button bOK = (Button) rootView.findViewById(R.id.bOK);
        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etUsername.getText().toString();
                ThisUser.setUsername(userName);
                String password = etPassword.getText().toString();
                validation(userName, password, false);
                ProfileActivity fragment = new ProfileActivity();
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frag_container, fragment);
                fragmentTransaction.commit();
            }

        });
        return rootView;
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

    public void validation(String userName, String password, boolean istest){
        if(!isValidEmail(userName)){
            if (!istest){ Toast.makeText(EditProfileActivity.this.getContext(), "Username is not a valid email address", Toast.LENGTH_LONG).show();}
            success = false;
        }
        if(!isValidPassword(password)){
            if (!istest){ Toast.makeText(EditProfileActivity.this.getContext(), "Password length should between 8 and 20 with at least 1 number, 1 lower case letter, 1 upper case letter", Toast.LENGTH_LONG).show();}
            success = false;
        }
        if (isValidEmail(userName) && isValidPassword(password)){
            User user = new User(ThisUser.getUserID(), userName, password);
            if (!istest){spdh.updateUser(user);}
            success = true;
        }
    }
}

