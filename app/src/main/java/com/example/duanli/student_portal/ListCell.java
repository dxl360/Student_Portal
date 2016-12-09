package com.example.duanli.student_portal;

import android.content.Context;
import java.util.ArrayList;

public class ListCell {

    public static final String TAG = ListCell.class.getSimpleName();

    public String title;
    public String description;
    public String label;
    public int id;

    public static ArrayList<ListCell> getCellsFromDatabase(String type, int filter, Context context) {
        ArrayList<ListCell> result = new ArrayList<>();

        try {
            SPDatabaseHelper spdh = new SPDatabaseHelper(context);
            if (type.equals("event")) {
                ArrayList<Event> cells = spdh.retrieveEvents(filter);
                for (int i = 0; i < cells.size(); i++) {
                    ListCell temp = new ListCell();
                    temp.title = cells.get(i).getEventName();
                    temp.description = cells.get(i).getLocation();
                    temp.label = cells.get(i).getDate();
                    temp.id = cells.get(i).getEventID();
                    result.add(temp);
                }
            }
            else if (type.equals("item")) {
                ArrayList<Item> cells = spdh.retrieveItems(filter);
                for (int i = 0; i < cells.size(); i++) {
                    ListCell temp = new ListCell();
                    temp.title = cells.get(i).getItemName();
                    temp.description = cells.get(i).getContact();
                    temp.label = String.valueOf(cells.get(i).getPrice());
                    temp.id = cells.get(i).getItemID();
                    result.add(temp);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}