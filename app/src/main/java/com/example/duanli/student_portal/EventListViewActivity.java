package com.example.duanli.student_portal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.json.JSONObject;

import java.util.ArrayList;

public class EventListViewActivity extends Fragment{

    public static final String TAG = EventListViewActivity.class.getSimpleName();

    private ListView mListView;
    //private static final String TABLE_EVENT= "event";
    Bundle bundle;
    int eventId;

    public EventListViewActivity() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.activity_event_list_view, container, false);

        bundle = this.getArguments();
        int filter = bundle.getInt("case");
        //int filter = getIntent().getExtras().getInt("case");

        final ArrayList<ListCell> eventList;
        // Get data to display
        if (filter == 1) {
            eventList = ListCell.getCellsFromDatabase("event", 1, getContext());
        }
        else if  (filter == 2){
            eventList = ListCell.getCellsFromDatabase("event", 2, getContext());
        }
         else if  (filter == 3){
            eventList = ListCell.getCellsFromDatabase("event", 3, getContext());
         }
         else {
            eventList = ListCell.getCellsFromDatabase("event", 0, getContext());
         }

        // Create adapter
        ListViewAdapter adapter = new ListViewAdapter(getContext(), eventList);

        // Create list view
        mListView = (ListView) rootView.findViewById(R.id.event_list);
        mListView.setAdapter(adapter);

        // Set what happens when a list view item is clicked
        mListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListCell selectedRecipe = eventList.get(position);
                Intent detailIntent = new Intent(getContext(), EventDetailActivity.class);
                detailIntent.putExtra("title", selectedRecipe.title);
                //detailIntent.putExtra("url", selectedRecipe.instructionUrl);
                eventId = selectedRecipe.id;
                detailIntent.putExtra("eventId", eventId);
                startActivity(detailIntent);
            }

        });
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_search, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.search){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}