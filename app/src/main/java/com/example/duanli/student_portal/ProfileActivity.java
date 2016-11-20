package com.example.duanli.student_portal;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

/**
 * @author
 * Profile fragment to be displayed when selected in the sliding menu
 */
public class ProfileActivity extends Fragment {

    // empty constructor
    public ProfileActivity() {}

    /**
     * Method that processes the xml file and pulls/pushes the changes to the screen
     * @param inflater LayoutInflater that will inflate fragment view
     * @param container view the fragment's UI atached to
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
        final TextView etUserID = (TextView) rootView.findViewById(R.id.etUserID);

        // button interactions (onClick)
        final ImageView ivEdit = (ImageView) rootView.findViewById(R.id.ivEdit);
        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editProfileIntent = new Intent(getActivity(), EditProfileActivity.class);
                getActivity().startActivity(editProfileIntent);
            }
        });

        return rootView;
    }


}





