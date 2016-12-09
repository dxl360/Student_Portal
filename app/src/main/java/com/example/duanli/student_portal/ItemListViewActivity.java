package com.example.duanli.student_portal;

import android.content.Intent;
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

import java.util.ArrayList;

public class ItemListViewActivity extends Fragment {

    public static final String TAG = ItemListViewActivity.class.getSimpleName();

    private ListView mListView;
    Bundle bundle;
    int itemId;

    public ItemListViewActivity() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.activity_item_list_view, container, false);

        bundle = this.getArguments();
        int filter = bundle.getInt("case");
        // Get data to display
        final ArrayList<ListCell> itemList;
        // Get data to display
        if (filter == 1) {
            itemList = ListCell.getCellsFromDatabase("item", 1, getContext());
        }
        else if  (filter == 2){
            itemList = ListCell.getCellsFromDatabase("item", 2, getContext());
        }
        else if  (filter == 3){
            itemList = ListCell.getCellsFromDatabase("item", 3, getContext());
        }
        else {
            itemList = ListCell.getCellsFromDatabase("item", 0, getContext());
        }

        // Create adapter
        ListViewAdapter adapter = new ListViewAdapter(getContext(), itemList);

        // Create list view
        mListView = (ListView) rootView.findViewById(R.id.exchange_list);
        mListView.setAdapter(adapter);

        // Set what happens when a list view item is clicked
        mListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListCell selectedRecipe = itemList.get(position);
                Intent detailIntent = new Intent(getContext(), ItemDetailActivity.class);
                detailIntent.putExtra("title", selectedRecipe.title);
                itemId = selectedRecipe.id;
                detailIntent.putExtra("itemId", itemId);
                System.out.println("itemId = " + itemId);
                //detailIntent.putExtra("url", selectedRecipe.instructionUrl);
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