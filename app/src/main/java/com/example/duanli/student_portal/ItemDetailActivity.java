package com.example.duanli.student_portal;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ItemDetailActivity extends AppCompatActivity {

    private GoogleApiClient client;
    SPDatabaseHelper spdh;
    int itemId, userId, buyerId, reservationId = 0, reservationStatus;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;

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
        try {
            item_content_Initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                Snackbar.make(view, "Item Added To Bookmark", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if (spdh.querySell(userId, itemId)) //Seller
        {
            inflater.inflate(R.menu.seller_detail, menu);
            reservationId = spdh.queryNewReserve(userId, itemId);
            buyerId = spdh.queryReserveBuyer(userId, itemId);
            String text = spdh.queryUserID(buyerId).getUserName() + " has reserved this item.";
            if (buyerId != 0)
                Toast.makeText(ItemDetailActivity.this, text, Toast.LENGTH_LONG).show();
            if (reservationId == 0) //has not been reserved
            {
                MenuItem reject = menu.findItem(R.id.reject);
                reject.setVisible(false);
                MenuItem resolve = menu.findItem(R.id.resolve);
                resolve.setVisible(false);
                MenuItem block = menu.findItem(R.id.block);
                block.setVisible(false);
            }
        } else { //buyer
            inflater.inflate(R.menu.buyer_detail, menu);
            reservationId = spdh.queryReserveID(userId, itemId);
            reservationStatus = spdh.queryReserveStatus(userId, itemId);
            if (spdh.queryWatch(userId, itemId)) //has watched
            {
                MenuItem watchlist = menu.findItem(R.id.watchlist);
                watchlist.setVisible(false);
            } else {
                MenuItem cancel_watchlist = menu.findItem(R.id.cancel_watchlist);
                cancel_watchlist.setVisible(false);
            }
            if (reservationId != 0) //has reserved
            {
                if (reservationStatus == 1) {
                    Toast.makeText(ItemDetailActivity.this, "Reservation is rejected. Please cancel reservation to remove it from the list and reserve it again.", Toast.LENGTH_LONG).show();
                }
                if (reservationStatus == 2) {
                    Toast.makeText(ItemDetailActivity.this, "Reservation is accepted and the transaction is completed.", Toast.LENGTH_LONG).show();
                }
                MenuItem reserve = menu.findItem(R.id.reserve);
                reserve.setVisible(false);
            } else {
                MenuItem cancel_reserve = menu.findItem(R.id.cancel_reserve);
                cancel_reserve.setVisible(false);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int sellerId = spdh.queryItem(itemId).getSellerID();
        int buyerId = spdh.queryBuyer(reservationId);
        switch (item.getItemId()) {
            case R.id.watchlist:
                //watchlist
                spdh.insertWatchlist(itemId, userId, sellerId);
                Snackbar.make(getWindow().getDecorView(), "The item has been added to Watchlist.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.cancel_watchlist:
                //cancel_watchlist
                spdh.deleteWatchlist(itemId, userId);
                Snackbar.make(getWindow().getDecorView(), "The item has been removed from Watchlist.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.reserve:
                //reserve
                spdh.insertReserve(itemId, sellerId, userId, 0);//new reservation status = 0
                spdh.updateItemStatus(itemId, 1);//reserved item status = 1
                Snackbar.make(getWindow().getDecorView(), "The item has been added to Reservation List.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.cancel_reserve:
                //cancel reserve
                spdh.deleteReservation(reservationId);
                spdh.updateItemStatus(itemId, 0);//unreserved/available item status = 0
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
                spdh.updateReservationStatus(reservationId, 1);//rejected reservation status = 1
                spdh.updateItemStatus(itemId, 0);//unreserved/available item status = 0
                Snackbar.make(getWindow().getDecorView(), "The reservation has been rejected.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.resolve:
                //resolve_reservation
                spdh.updateReservationStatus(reservationId, 2);//resolved reservation status = 2
                spdh.updateItemStatus(itemId, 2);//resolved/sold item status = 2
                Snackbar.make(getWindow().getDecorView(), "The reservation has been resolved.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.block:
                //block current user
                spdh.insertBlock(buyerId, sellerId);
                Snackbar.make(getWindow().getDecorView(), "The user has been blocked.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.delete_item:
                //delete in database
                //startActivity(new Intent(ItemDetailActivity.this, ManageActivity.class));
                spdh.deleteItem(itemId);
                //startActivity(new Intent(ItemDetailActivity.this, MainActivity.class));
                Snackbar.make(getWindow().getDecorView(), "The item has been deleted.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void item_content_Initialize() throws IOException {
        Item currentItem = spdh.queryItem(itemId);
        ImageView view = (ImageView) findViewById(R.id.item_backdrop);
        String url = currentItem.getItemPictureUrl();
        ImageLoaderConfiguration config = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(config);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(url, view);

        TextView tvItemName = (TextView) findViewById(R.id.tvItemName);
        tvItemName.setText(currentItem.getItemName());

        //TextView tvItemType = (TextView)findViewById(R.id.tvItemType);
        //tvItemType.setText(currentItem.getItemType());

        TextView tvItemPrice = (TextView) findViewById(R.id.tvItemPrice);
        tvItemPrice.setText(String.valueOf(currentItem.getPrice()));

        TextView tvItemSeller = (TextView) findViewById(R.id.tvItemSeller);
        int sellerID = spdh.queryItem(itemId).getSellerID();
        tvItemSeller.setText(spdh.queryUserID(sellerID).getUserName());
        //tvItemSeller .setText(spdh.queryUserID(spdh.querySeller(reservationId)).getUserName());

        TextView tvSellerContact = (TextView) findViewById(R.id.tvSellerContact);
        tvSellerContact.setText(currentItem.getContact());

        TextView tvItemDescription = (TextView) findViewById(R.id.tvItemDescription);
        tvItemDescription.setText(currentItem.getDescription());
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ItemDetail Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2.connect();
        AppIndex.AppIndexApi.start(client2, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client2, getIndexApiAction());
        client2.disconnect();
    }
}
