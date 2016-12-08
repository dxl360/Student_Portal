package com.example.duanli.student_portal;

import android.support.v4.app.Fragment;
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

    SPDatabaseHelper spdh;
    // empty constructor
    public ProfileActivity() {
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
        spdh = SPDatabaseHelper.getInstance(this.getContext());
        // linking xml objects to actual fields
        rootView.findViewById(R.id.tvUsername);
        rootView.findViewById(R.id.tvPassword);
        final TextView etUsername = (TextView) rootView.findViewById(R.id.etUsername);
        etUsername.setText(spdh.queryUserID(ThisUser.getUserID()).getUserName());
        final TextView etPassword = (TextView)rootView.findViewById(R.id.etPassword);
        etPassword.setText(spdh.queryUserID(ThisUser.getUserID()).getPassword());
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
            }

        });
        return rootView;
    }
}