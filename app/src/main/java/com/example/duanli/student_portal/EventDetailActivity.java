package com.example.duanli.student_portal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.Serializable;

public class EventDetailActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    SPDatabaseHelper spdh;
    int userID=-1;
    int eventID=-1;
    public Bundle getBundle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spdh = new SPDatabaseHelper(this);
        getBundle = this.getIntent().getExtras();
        userID = Integer.parseInt(getBundle.getString("userID"));
        eventID = Integer.parseInt(getBundle.getString("eventID"));
        setContentView(R.layout.activity_event_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.event_toolbar);
        setSupportActionBar(toolbar);

        event_content_Initialize(eventID);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add to google calendar
                Snackbar.make(view, "The event is added to your calendar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if (spdh.queryOrganize(userID,eventID)) //Organizer
        {
            inflater.inflate(R.menu.organizer_detail, menu);
        }
        else { //participant
            inflater.inflate(R.menu.participant_detail, menu);
            if (spdh.queryBookmark(userID, eventID)) //has bookmarked
            {
                MenuItem bookmark = menu.findItem(R.id.bookmark);
                bookmark.setVisible(false);
            }
            else
            {
                MenuItem cancel_bookmark = menu.findItem(R.id.cancel_bookmark);
                cancel_bookmark.setVisible(false);
            }
            if (spdh.queryJoin(userID, eventID)) //has Joined
            {
                MenuItem join = menu.findItem(R.id.join);
                join.setVisible(false);
            }
            else
            {
                MenuItem cancel_join = menu.findItem(R.id.cancel_join);
                cancel_join.setVisible(false);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bookmark:
                //bookmark
                spdh.insertBookmark(eventID,userID);
                Snackbar.make(getWindow().getDecorView(),"The event has been added to Bookmark.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.cancel_bookmark:
                //cancel_bookmark
                spdh.deleteBookmark(eventID,userID);
                Snackbar.make(getWindow().getDecorView(),"The event has been removed from Bookmark.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.join:
                //join
                spdh.insertJoin(eventID,userID);
                Snackbar.make(getWindow().getDecorView(),"The event has been added to Join List and Google Calendar.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.cancel_join:
                //cancel join
                spdh.deleteJoin(eventID,userID);
                Snackbar.make(getWindow().getDecorView(),"The event has been removed from Join List and Google Calendar.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.edit_event:
                startActivity(new Intent(EventDetailActivity.this, NewEventActivity.class));
                return true;
            //    case R.id.edit_permission:
            //        startActivity(new Intent(EventDetailActivity.this, Permission.class));
            //        return true;
            //    case R.id.scan_permission:
            //        startActivity(new Intent(EventDetailActivity.this, QRActivity.class));
            //        return true;
            case R.id.delete_event:
                //delete in database
                spdh.deleteEvent(eventID);
                //startActivity(new Intent(EventDetailActivity.this, MainActivity.class));
                Snackbar.make(getWindow().getDecorView(),"The event has been deleted.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void event_content_Initialize(int EventID) {
//        CollapsingToolbarLayout layout = (CollapsingToolbarLayout) findViewById(R.id.event_toolbar_layout);
//        layout.setTitle("@string/demoEventTitle");
        Event current=spdh.queryEvent(eventID);
        ImageView view = (ImageView) findViewById(R.id.event_backdrop);
        //view.setImageResource(current.getPosterURL());

        TextView tvEventName = (TextView) findViewById(R.id.tvEventName);
        tvEventName.setText(current.getEventName());

        TextView tvEventOrganizer = (TextView) findViewById(R.id.tvEventOrganizer);
        tvEventOrganizer.setText(current.getOrganizerName());

        TextView tvEventTimeDate = (TextView) findViewById(R.id.tvEventTimeDate);
        tvEventTimeDate.setText(current.getDate()+current.getTime());

        TextView tvEventLocation = (TextView) findViewById(R.id.tvEventLocation);
        tvEventLocation.setText(current.getLocation());

        TextView tvEventPrice = (TextView) findViewById(R.id.tvEventPrice);
        tvEventPrice.setText(Integer.toString(current.getPrice()));

        TextView tvEventCapacity = (TextView) findViewById(R.id.tvEventCapacity);
        tvEventCapacity.setText(Integer.toString(current.getCapacity()));

        TextView tvEventHashTag = (TextView) findViewById(R.id.tvEventHashTag);
        tvEventHashTag.setText(getResources().getString(R.string.demoEventHashTag));

        TextView tvEventDescription = (TextView) findViewById(R.id.tvEventDescription);
        tvEventDescription.setText(current.getDescription());
    }

}
