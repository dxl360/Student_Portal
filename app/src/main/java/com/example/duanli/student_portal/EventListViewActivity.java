package com.example.duanli.student_portal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class EventListViewActivity extends Activity{

    public static final String TAG = EventListViewActivity.class.getSimpleName();

    private ListView mListView;

    Toolbar mActionBarToolbar;

    // TXL330
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list_view);

        //mActionBarToolbar = (Toolbar) findViewById(R.id.Events);
        //mActionBarToolbar.setTitle("Events");

        final Context context = this;

        int filter = getIntent().getExtras().getInt("case");

        final ArrayList<Recipe> recipeList;
        // Get data to display
        if (filter == 1) {
            recipeList = Recipe.getRecipesFromFile("eventsStarted.json", this);
            System.out.println("here");
        }
        else {
           recipeList = Recipe.getRecipesFromFile("recipes.json", this);
        }

        // Create adapter
        ListViewAdapter adapter = new ListViewAdapter(this, recipeList);

        // Create list view
        mListView = (ListView) findViewById(R.id.event_list);
        mListView.setAdapter(adapter);

        // Set what happens when a list view item is clicked
        mListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe selectedRecipe = recipeList.get(position);

                Intent detailIntent = new Intent(context, EventDetailActivity.class);
                detailIntent.putExtra("title", selectedRecipe.title);
                detailIntent.putExtra("url", selectedRecipe.instructionUrl);

                startActivity(detailIntent);
            }

        });
    }}