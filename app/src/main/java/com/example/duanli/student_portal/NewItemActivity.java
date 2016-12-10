package com.example.duanli.student_portal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewItemActivity extends AppCompatActivity implements View.OnClickListener{

    Button Create;
    EditText etItemName,etPrice,etContact,etURL,etDescription;
    String itemName,contact,description,URL;
    int sellerID, price,itemId, status;
    SPDatabaseHelper spdh;
    boolean success = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        spdh = SPDatabaseHelper.getInstance(this);
        etItemName = (EditText) findViewById(R.id.etItemName);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etContact = (EditText) findViewById(R.id.etContact);
        etURL = (EditText) findViewById(R.id.etURL);
        etDescription = (EditText) findViewById(R.id.etDescription);
        Create = (Button) findViewById(R.id.button);
        Create.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        int edit = getIntent().getExtras().getInt("edit");
        System.out.println("edit = " + edit);
        if (getIntent().getExtras().getInt("edit")==1){
            itemId = getIntent().getExtras().getInt("itemId");
            Item current = spdh.queryItem(itemId);
            etItemName.setText(current.getItemName());
            etPrice.setText(String.valueOf(current.getPrice()));
            etContact.setText(current.getContact());
            etURL.setText(current.getItemPictureUrl());
            etDescription.setText(current.getDescription());
            status = current.getStatus();
        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.button) {
            //Intent intent = new Intent(this, Db_connection.class);
            //startActivity(intent);
            sellerID = ThisUser.getUserID();
            itemName = etItemName.getText().toString();

            contact = etContact.getText().toString();
            URL = etURL.getText().toString();
            description = etDescription.getText().toString();
            if (itemName.equals("") || etPrice.getText().toString().equals("") || contact.equals("") || description.equals("")) {
                Toast.makeText(getApplicationContext(), "Please enter all fields expect for Image URL(Optional).", Toast.LENGTH_LONG).show();
                System.out.println("false1");
            } else if (!isValidContact(contact)) {
                Toast.makeText(getApplicationContext(), "Please enter a valid phone number. Format: (123)-123-1234 / 1231231234", Toast.LENGTH_LONG).show();
                System.out.println("false2");
            } else {
                success = true;
                System.out.println("true");
            }
            System.out.println("before edit");
            if (success) {
                price = Integer.parseInt(etPrice.getText().toString());
                System.out.println("before edit1");
                if (getIntent().getExtras().getInt("edit") == 1) {
                    Item item = new Item(itemId, itemName, URL, sellerID, price, contact, description, status);
                    spdh.updateItem(item);
                    System.out.println("before edit2");
                } else {
                    System.out.println("before edit3");
                    System.out.println("edit is " + getIntent().getExtras().getInt("edit"));
                    status = 0;
                    Item item = new Item(-1, itemName, URL, sellerID, price, contact, description, status);
                    spdh.insertItem(item);
                    spdh.insertSell(spdh.queryItemName(itemName).getItemID(), ThisUser.getUserID(), 0);
                }
                Toast.makeText(NewItemActivity.this, "Submitting", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SlidingMenu.class));
            }
            //finish();
        }
    }

    public static boolean isValidSellerName(String s)
    {
        String ITEMNAME_REGEX="^(?=.{5,20}$)[a-zA-Z0-9]+$";
        if (!s.equals("")) {
            if (s.matches(ITEMNAME_REGEX)) {
                return true;}}
        return false;
    }

    public static boolean isValidItemName(String s)
    {
        String ITEMNAME_REGEX="^(?=.{5,20}$)[a-zA-Z0-9]+$";
        if (!s.equals("")) {
            if (s.matches(ITEMNAME_REGEX)) {
                return true;}}
        return false;
    }

    public static boolean isValidContact(String s){
        if (s.equals("")){return false;}
        //validate phone numbers of format "1234567890"
        if (s.matches("\\d{10}")) return true;
            //validating phone number with -, . or spaces
        else if(s.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
            //validating phone number with extension length from 3 to 5
        else if(s.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
            //validating phone number where area code is in braces ()
        else if(s.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
            //return false if nothing matches the input
        else return false;
    }

    public static boolean isValidDescription(String s)
    {
        String ITEMNAME_REGEX="^(?=.{5,20}$)[a-zA-Z0-9]+$";
        if (!s.equals("")) {
            if (s.matches(ITEMNAME_REGEX)) {
                return true;}}
        return false;
    }
}
