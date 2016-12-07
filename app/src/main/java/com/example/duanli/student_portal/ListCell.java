package com.example.duanli.student_portal;

import android.content.Context;
import java.util.ArrayList;

public class ListCell {

    public static final String TAG = ListCell.class.getSimpleName();

    public String title;
    public String description;
    public String label;

    public static ArrayList<ListCell> getCellsFromDatabase(String type, int filter, Context context) {
        ArrayList<ListCell> result = new ArrayList<>();
        ArrayList<Event> cells;
        try {
            SPDatabaseHelper spdh = new SPDatabaseHelper(context);
            if (type.equals("event")) {
                cells = spdh.retrieveEvents(filter);
                for (int i = 0; i < cells.size(); i++) {
                    ListCell temp = new ListCell();
                    temp.title = cells.get(i).getEventName();
                    temp.description = cells.get(i).getLocation();
                    temp.label = cells.get(i).getDate();
                    result.add(temp);
                }
            }
            else if (type.equals("item")) {
                // retrieve items to display
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}