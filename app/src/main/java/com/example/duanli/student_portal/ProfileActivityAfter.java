package com.example.duanli.student_portal;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author
 * Profile fragment to be displayed when selected in the sliding menu
 */
public class ProfileActivityAfter extends Fragment {

    // empty constructor
    public ProfileActivityAfter() {
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
        View rootView = inflater.inflate(R.layout.activity_profile, container, false);


        // linking xml objects to actual fields
        final TextView etUsername = (TextView) rootView.findViewById(R.id.etUsername);
        final TextView etPhoneNumber = (TextView) rootView.findViewById(R.id.etPhoneNumber);
        final TextView etEmail = (TextView) rootView.findViewById(R.id.etEmail);


        // button interactions (onClick)
        final ImageView ivEdit = (ImageView) rootView.findViewById(R.id.ivEdit);
        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfileActivity fragment = new EditProfileActivity();
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frag_container, fragment);
                fragmentTransaction.commit();


                // fetch the Password form database for respective user name


                // check if the Stored password matches with  Password entered by user


            }

        });
        String userName = etUsername.getText().toString();

  /*      User currentUser = spdh.queryUser(userName);
        String storedPassword = currentUser.getPassword();
        String phoneNumber = currentUser.getContact();
        String email = currentUser.getEmail();
        userName = currentUser.getEmail().substring(0, 3);
        */
        TextView etUsernameFromDB = (TextView) rootView.findViewById(R.id.userNameFromDB);
        TextView etEmailFromDB = (TextView) rootView.findViewById(R.id.etEmailFromDB);
        TextView etContactFromDB = (TextView) rootView.findViewById(R.id.etContactFromDB);
        etUsernameFromDB.setText("Mike");
        etEmailFromDB.setText("abc123@case.edu");
        etContactFromDB.setText("216666666");
        etUsername.setText("Username");
        etPhoneNumber.setText("Phone number");
        etEmail.setText("Email");
        return rootView;
    }
}