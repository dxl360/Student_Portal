package com.example.duanli.student_portal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.item_toolbar);
        setSupportActionBar(toolbar);
        item_content_Initialize("@string/demoItemID");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                Snackbar.make(view, "Item Added To Bookmark", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if (true) //Seller
        {
            inflater.inflate(R.menu.seller_detail, menu);
            if (false) //has not been reserved
            {
                MenuItem reject = menu.findItem(R.id.reject);
                reject.setVisible(false);
                MenuItem resolve = menu.findItem(R.id.resolve);
                resolve.setVisible(false);
                MenuItem block = menu.findItem(R.id.block);
                block.setVisible(false);
            }
        }
        else { //buyer
            inflater.inflate(R.menu.buyer_detail, menu);
            if (false) //has watched
            {
                MenuItem watchlist = menu.findItem(R.id.watchlist);
                watchlist.setVisible(false);
            }
            else
            {
                MenuItem cancel_watchlist = menu.findItem(R.id.cancel_watchlist);
                cancel_watchlist.setVisible(false);
            }
            if (false) //has reserved
            {
                MenuItem reserve = menu.findItem(R.id.reserve);
                reserve.setVisible(false);
            }
            else
            {
                MenuItem cancel_reserve = menu.findItem(R.id.cancel_reserve);
                cancel_reserve.setVisible(false);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.watchlist:
                //watchlist
                return true;
            case R.id.cancel_watchlist:
                //cancel_watchlist
                return true;
            case R.id.reserve:
                //reserve
                return true;
            case R.id.cancel_reserve:
                //cancel reserve
                return true;
            case R.id.edit_item:
                startActivity(new Intent(ItemDetailActivity.this, NewItemActivity.class));
                return true;
            case R.id.reject:
                //reject_reservation
                Snackbar.make(getWindow().getDecorView(),"The reservation has been rejected.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.resolve:
                //resolve_reservation
                Snackbar.make(getWindow().getDecorView(),"The reservation has been resolved.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.block:
                //block current user
                Snackbar.make(getWindow().getDecorView(),"The user has been blocked.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.delete_item:
                //delete in database
                //startActivity(new Intent(ItemDetailActivity.this, MainActivity.class));
                Snackbar.make(getWindow().getDecorView(),"The item has been deleted.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void item_content_Initialize(String ItemID){

        ImageView view = (ImageView)findViewById(R.id.item_backdrop);
        view.setImageResource(R.drawable.mask);

        TextView tvItemName = (TextView)findViewById(R.id.tvItemName);
        tvItemName.setText(getResources().getString(R.string.demoItemName));

        TextView tvItemType = (TextView)findViewById(R.id.tvItemType);
        tvItemType.setText(getResources().getString(R.string.demoItemType));

        TextView tvItemPrice = (TextView)findViewById(R.id.tvItemPrice);
        tvItemPrice.setText(getResources().getString(R.string.demoItemPrice));

        TextView tvItemSeller = (TextView)findViewById(R.id.tvItemSeller);
        tvItemSeller .setText(getResources().getString(R.string.demoItemSeller));

        TextView tvSellerContact = (TextView)findViewById(R.id.tvSellerContact);
        tvSellerContact.setText(getResources().getString(R.string.demoItemSellerContact));

        TextView tvItemDescription = (TextView)findViewById(R.id.tvItemDescription);
        tvItemDescription.setText(getResources().getString(R.string.demoItemDescription));
    }
}
