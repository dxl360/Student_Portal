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
    int eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.event_toolbar);
        setSupportActionBar(toolbar);
        spdh = SPDatabaseHelper.getInstance(this);
        event_content_Initialize("@string/demoEventID");
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
        eventId = getIntent().getExtras().getInt("eventId");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if (true) //Organizer
        {
            inflater.inflate(R.menu.organizer_detail, menu);
        }
        else { //participant
            inflater.inflate(R.menu.participant_detail, menu);
            if (false) //has bookmarked
            {
                MenuItem bookmark = menu.findItem(R.id.bookmark);
                bookmark.setVisible(false);
            }
            else
            {
                MenuItem cancel_bookmark = menu.findItem(R.id.cancel_bookmark);
                cancel_bookmark.setVisible(false);
            }
            if (false) //has Joined
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
                Snackbar.make(getWindow().getDecorView(),"The event has been added to Bookmark.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.cancel_bookmark:
                //cancel_bookmark
                Snackbar.make(getWindow().getDecorView(),"The event has been removed from Bookmark.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.join:
                //join
                Snackbar.make(getWindow().getDecorView(),"The event has been added to Join List and Google Calendar.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.cancel_join:
                //cancel join
                Snackbar.make(getWindow().getDecorView(),"The event has been removed from Join List and Google Calendar.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.edit_event:
                Intent intent = new Intent(EventDetailActivity.this, NewEventActivity.class);
                intent.putExtra("edit", 1);
                intent.putExtra("eventId", eventId);
                startActivity(intent);
                return true;
            //    case R.id.edit_permission:
            //        startActivity(new Intent(EventDetailActivity.this, Permission.class));
            //        return true;
            //    case R.id.scan_permission:
            //        startActivity(new Intent(EventDetailActivity.this, QRActivity.class));
            //        return true;
            case R.id.delete_event:
                //delete in database
                //startActivity(new Intent(EventDetailActivity.this, MainActivity.class));
                Snackbar.make(getWindow().getDecorView(),"The event has been deleted.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void event_content_Initialize(String EventID) {
//        CollapsingToolbarLayout layout = (CollapsingToolbarLayout) findViewById(R.id.event_toolbar_layout);
//        layout.setTitle("@string/demoEventTitle");
        ImageView view = (ImageView) findViewById(R.id.event_backdrop);
        view.setImageResource(R.drawable.picture_eventposter);
        System.out.println("eventId before is " + eventId);
        eventId = 1;
        TextView tvEventName = (TextView) findViewById(R.id.tvEventName);
        //tvEventName.setText(getResources().getString(R.string.demoEventTitle));
        tvEventName.setText(spdh.queryEvent(eventId).getEventName());

        TextView tvEventOrganizer = (TextView) findViewById(R.id.tvEventOrganizer);
        //tvEventOrganizer.setText(getResources().getString(R.string.demoEventOrganizer));
        int organizerID = spdh.queryEvent(eventId).getOrganizerID();
        tvEventOrganizer.setText(spdh.queryUserID(organizerID).getUserName());

        TextView tvEventTimeDate = (TextView) findViewById(R.id.tvEventTimeDate);
        //tvEventTimeDate.setText(getResources().getString(R.string.demoEventTimeDate));
        tvEventTimeDate.setText(spdh.queryEvent(eventId).getDate() + " " + spdh.queryEvent(eventId).getTime());

        TextView tvEventLocation = (TextView) findViewById(R.id.tvEventLocation);
        //tvEventLocation.setText(getResources().getString(R.string.demoEventLocation));
        tvEventLocation.setText(spdh.queryEvent(eventId).getLocation());

        TextView tvEventPrice = (TextView) findViewById(R.id.tvEventPrice);
        //tvEventPrice.setText(getResources().getString(R.string.demoEventPrice));
        tvEventPrice.setText(String.valueOf(spdh.queryEvent(eventId).getPrice()));

        TextView tvEventCapacity = (TextView) findViewById(R.id.tvEventCapacity);
//        tvEventCapacity.setText(getResources().getString(R.string.demoEventCapacity));
        tvEventCapacity.setText(String.valueOf(spdh.queryEvent(eventId).getCapacity()));

        TextView tvEventHashTag = (TextView) findViewById(R.id.tvEventHashTag);
        tvEventHashTag.setText(getResources().getString(R.string.demoEventHashTag));

        TextView tvEventDescription = (TextView) findViewById(R.id.tvEventDescription);
//        tvEventDescription.setText(getResources().getString(R.string.demoEventDescription));
        tvEventDescription.setText(spdh.queryEvent(eventId).getDescription());

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("EventDetail Page") // TODO: Define a title for the content shown.
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
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    /**
     * Created by duanli on 10/23/16.
     */

    public static class Event implements Serializable {

        //    private int eventID;
        private String organizerName;
        private String eventName;
        private String date;
        private String time;
        private String location;
        private String price;
        private String capacity;
        private String description;

        //    public int getEventId() { return eventID; }

        public String getOrganizerNameName() {
            return organizerName;
        }
        public void setOrganizerNameName(String organizerName) {
            this.organizerName = organizerName;
        }

        public String getItemName() {
            return eventName;
        }
        public void setItemName(String eventName) {
            this.eventName = eventName;
        }

        public String getDate() {
            return date;
        }
        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }
        public void setTime(String time) {
            this.time = time;
        }

        public String getLocation() {
            return location;
        }
        public void setLocation(String location) {
            this.location = location;
        }

        public String getPrice() {
            return price;
        }
        public void setPrice(String price) { this.price = price;
        }

        public String getCapacity() {
            return capacity;
        }
        public void setCapacity(String capacity) {
            this.capacity = capacity;
        }

        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }

        public Event(){}

        public Event(String OrganizerName,String EventName,String Date,String Time,String Location,String Price,String Capacity,String Description) {
            organizerName = OrganizerName;
            eventName = EventName;
            date = Date;
            time = Time;
            location = Location;
            price = Price;
            capacity = Capacity;
            description = Description;
        }

        public boolean addEvent(){
            //        DatabaseManager DbMan = DatabaseManager.getInstance();
            //        DbMan.addEvent(organizerName,eventName,date,time,capacity,location,description);
            return true;
        }

    }
}
