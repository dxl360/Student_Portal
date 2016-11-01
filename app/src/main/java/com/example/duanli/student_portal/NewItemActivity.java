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
            sellerName = ThisUser.username;
            itemName = etItemName.getText().toString();
            price = etPrice.getText().toString();
            contact = etContact.getText().toString();
            description = etDescription.getText().toString();

            Item item = new Item(sellerName,itemName,price,contact,description);
            item.addItem();
            finish();
            startActivity(new Intent(this, ItemDetailActivity.class));
        }

    }
}
