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
    String sellerName,itemName,price,contact,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        etItemName = (EditText) findViewById(R.id.etItemName);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etContact = (EditText) findViewById(R.id.etContact);
        etDescription = (EditText) findViewById(R.id.etDescription);
        Create = (Button) findViewById(R.id.button);
        Create.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.button) {
            Toast.makeText(NewItemActivity.this, "Submitting", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(this, Db_connection.class);
            //startActivity(intent);
            sellerName = new User().getUserName();
            itemName = etItemName.getText().toString();
            price = etPrice.getText().toString();
            contact = etContact.getText().toString();
            description = etDescription.getText().toString();
            if (isValidSellerName(sellerName) && isValidItemName(itemName) && isValidContact(contact) && isValidDescription(description));
            Item item = new Item(sellerName,itemName,price,contact,description);
            item.addItem();
            finish();
            startActivity(new Intent(this, ItemDetailActivity.class));
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
