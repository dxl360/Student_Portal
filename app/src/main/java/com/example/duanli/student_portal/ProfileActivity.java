package com.example.duanli.student_portal;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import com.google.zxing.WriterException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

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
        String currentUserName = spdh.queryUserID(ThisUser.getUserID()).getUserName();
        final TextView etUsername = (TextView) rootView.findViewById(R.id.etUsername);
        etUsername.setText(currentUserName);
        final TextView etPassword = (TextView)rootView.findViewById(R.id.etPassword);
        etPassword.setText(spdh.queryUserID(ThisUser.getUserID()).getPassword());
        // button interactions (onClick)
        final ImageView ivEdit = (ImageView) rootView.findViewById(R.id.ivEdit);

        ImageView profilePicture = (ImageView) rootView.findViewById(R.id.circleimage);

        try {
            // generate a 150x150 QR code
            QRCodeWriter writer = new QRCodeWriter();
            int size = 512;

            ByteMatrix bitMatrix = writer.encode("This User is" + currentUserName,BarcodeFormat.QR_CODE, size, size);
            int width = bitMatrix.width();
            Bitmap bmp = Bitmap.createBitmap(width, width, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < width; y++) {
                    bmp.setPixel(y, x, bitMatrix.get(x, y)==0 ? Color.BLACK : Color.WHITE);
                }
            }
            if (bmp != null) {
                profilePicture.setImageBitmap(bmp);
            }
        } catch (WriterException e) {
            System.out.println("Error generating QR code");
        }

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


//    public Bitmap generateQrCode(String content) throws WriterException {
//        try {
//            // generate a 150x150 QR code
//            QRCodeWriter writer = new QRCodeWriter();
//            ByteMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 150, 150);
//            int width = bitMatrix.width();
//            int height = bitMatrix.height();
//            Bitmap bm = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
//            if (bm != null) {
//                return bm;
//                //image_view.setImageBitmap(bm);
//            }
//        } catch (WriterException e) {
//            System.out.println("Error generating QR code");
//        }
//    }

}