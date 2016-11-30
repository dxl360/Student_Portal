package com.example.duanli.student_portal;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class EditProfileActivity extends Fragment {


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


        // linking xml objects to actual fields
        final TextView tvUsername = (TextView) rootView.findViewById(R.id.etUsername);
        final TextView tvPhoneNumber = (TextView) rootView.findViewById(R.id.etPhoneNumber);
        final TextView tvEmail = (TextView) rootView.findViewById(R.id.etEmail);
        final EditText etABC123 = (EditText) rootView.findViewById(R.id.etABC123);
        final EditText etNA = (EditText)rootView.findViewById(R.id.etNA);
        final EditText etABC123Email = (EditText)rootView.findViewById(R.id.etABC123Email);

        // button interactions (onClick)
        final Button bOK = (Button) rootView.findViewById(R.id.bOK);
        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileActivityAfter fragment = new ProfileActivityAfter();
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frag_container, fragment);
                fragmentTransaction.commit();



            }

        });
        String userName = tvUsername.getText().toString();




        return rootView;
    }
}

