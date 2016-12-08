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
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

public class ItemDetailActivity extends AppCompatActivity {

    private GoogleApiClient client;
    SPDatabaseHelper spdh;
    int itemId;
    int userId;
    int reservationId=0;
    int reservationStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        userId = ThisUser.getUserID();
        itemId = getIntent().getExtras().getInt("itemId");
        System.out.println("itemId = " + itemId);
        spdh = SPDatabaseHelper.getInstance(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.item_toolbar);
        setSupportActionBar(toolbar);
        item_content_Initialize();
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
        if (spdh.querySell(userId,itemId)) //Seller
        {
            inflater.inflate(R.menu.seller_detail, menu);
            reservationId=spdh.queryNewReserve(userId, itemId);
            if (reservationId==0) //has not been reserved
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
            reservationId=spdh.queryReserveBuyer(userId, itemId);
            reservationStatus=spdh.queryReserveStatus(userId, itemId);
            if (spdh.queryWatch(userId,itemId)) //has watched
            {
                MenuItem watchlist = menu.findItem(R.id.watchlist);
                watchlist.setVisible(false);
            }
            else
            {
                MenuItem cancel_watchlist = menu.findItem(R.id.cancel_watchlist);
                cancel_watchlist.setVisible(false);
            }
            if (reservationId!=0) //has reserved
            {
                if(reservationStatus ==1){
                    Toast.makeText(ItemDetailActivity.this, "Reservation is rejected. Please cancel reservation and reserve the item again.", Toast.LENGTH_LONG).show();
                }
                if(reservationStatus ==2){
                    Toast.makeText(ItemDetailActivity.this, "Reservation is accepted and the transaction is completed.", Toast.LENGTH_LONG).show();
                }
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
        int sellerId=spdh.queryItem(itemId).getSellerID();
        int buyerId=spdh.queryBuyer(reservationId);
        switch (item.getItemId()) {
            case R.id.watchlist:
                //watchlist
                spdh.insertWatchlist(itemId,userId,sellerId);
                Snackbar.make(getWindow().getDecorView(), "The item has been added to Watchlist.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.cancel_watchlist:
                //cancel_watchlist
                spdh.deleteWatchlist(itemId,userId);
                Snackbar.make(getWindow().getDecorView(), "The item has been removed from Watchlist.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.reserve:
                //reserve
                spdh.insertReserve(itemId,sellerId,userId,0);
                Snackbar.make(getWindow().getDecorView(), "The item has been added to Reservation List.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.cancel_reserve:
                //cancel reserve
                spdh.deleteReservation(reservationId);
                Snackbar.make(getWindow().getDecorView(), "The item has been removed from Reservation List.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.edit_item:
                Intent intent = new Intent(ItemDetailActivity.this, NewItemActivity.class);
                intent.putExtra("edit", 1);
                intent.putExtra("itemId", itemId);
                startActivity(intent);
                return true;
            case R.id.reject:
                //reject_reservation
                spdh.updateReservationStatus(reservationId,1);
                Snackbar.make(getWindow().getDecorView(),"The reservation has been rejected.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.resolve:
                //resolve_reservation
                spdh.updateReservationStatus(reservationId,2);
                Snackbar.make(getWindow().getDecorView(),"The reservation has been resolved.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.block:
                //block current user
                spdh.insertBlock(buyerId,sellerId);
                Snackbar.make(getWindow().getDecorView(),"The user has been blocked.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.delete_item:
                //delete in database
                startActivity(new Intent(ItemDetailActivity.this, ManageActivity.class));
                spdh.deleteItem(itemId);
                //startActivity(new Intent(ItemDetailActivity.this, MainActivity.class));
                Snackbar.make(getWindow().getDecorView(),"The item has been deleted.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void item_content_Initialize(){
        Item currentItem=spdh.queryItem(itemId);
        ImageView view = (ImageView)findViewById(R.id.item_backdrop);
        view.setImageResource(R.drawable.mask);

        TextView tvItemName = (TextView)findViewById(R.id.tvItemName);
        tvItemName.setText(currentItem.getItemName());

        //TextView tvItemType = (TextView)findViewById(R.id.tvItemType);
        //tvItemType.setText(currentItem.getItemType());

        TextView tvItemPrice = (TextView)findViewById(R.id.tvItemPrice);
        tvItemPrice.setText(String.valueOf(currentItem.getPrice()));

        TextView tvItemSeller = (TextView)findViewById(R.id.tvItemSeller);
        int sellerID = spdh.queryItem(itemId).getSellerID();
        tvItemSeller.setText(spdh.queryUserID(sellerID).getUserName());
        //tvItemSeller .setText(spdh.queryUserID(spdh.querySeller(reservationId)).getUserName());

        TextView tvSellerContact = (TextView)findViewById(R.id.tvSellerContact);
        tvSellerContact.setText(currentItem.getContact());

        TextView tvItemDescription = (TextView)findViewById(R.id.tvItemDescription);
        tvItemDescription.setText(currentItem.getDescription());
    }
}
