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
    EditText etItemName,etPrice,etContact,etDescription;
    String itemName,contact,description;
    int sellerID, price,itemId, status;
    SPDatabaseHelper spdh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        spdh = SPDatabaseHelper.getInstance(this);
        etItemName = (EditText) findViewById(R.id.etItemName);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etContact = (EditText) findViewById(R.id.etContact);
        etDescription = (EditText) findViewById(R.id.etDescription);
        Create = (Button) findViewById(R.id.button);
        Create.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        int edit = getIntent().getExtras().getInt("edit");
        System.out.println("edit = " + edit);
        if (getIntent().getExtras().getInt("edit")==1){
            itemId = getIntent().getExtras().getInt("itemId");
            System.out.println("itemId = " + itemId);
            etItemName.setText(spdh.queryItem(itemId).getItemName());
            etPrice.setText(String.valueOf(spdh.queryItem(itemId).getPrice()));
            etContact.setText(spdh.queryItem(itemId).getContact());
            etDescription.setText(spdh.queryItem(itemId).getDescription());
            status = spdh.queryItem(itemId).getStatus();
        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.button) {
            Toast.makeText(NewItemActivity.this, "Submitting", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(this, Db_connection.class);
            //startActivity(intent);
            sellerID = ThisUser.getUserID();
            itemName = etItemName.getText().toString();
            price = Integer.parseInt(etPrice.getText().toString());
            contact = etContact.getText().toString();
            description = etDescription.getText().toString();
 //           if (isValidSellerName(sellerName) && isValidItemName(itemName) && isValidContact(contact) && isValidDescription(description));
            if (getIntent().getExtras().getInt("edit")==1){
                Item item = new Item(itemId,itemName,sellerID,price,contact,description,status);
                spdh.updateItem(item);
            }
            else {
                System.out.println("edit is "+getIntent().getExtras().getInt("edit"));
                status = 0;
                Item item = new Item(-1,itemName,sellerID,price,contact,description,status);
                spdh.insertItem(item);
            }
            finish();
            startActivity(new Intent(this, SlidingMenu.class));
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
