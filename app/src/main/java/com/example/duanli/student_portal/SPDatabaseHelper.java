package com.example.duanli.student_portal;

/**
 * Created by XiaoyingJi on 2016/11/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SPDatabaseHelper extends SQLiteOpenHelper{
    private static SPDatabaseHelper sInstance;

    // Database Info
    private static final String DATABASE_NAME = "studentPortalDatabase";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_USER = "user";
    private static final String TABLE_ITEM = "item";
    private static final String TABLE_EVENT= "event";

    // User Table Columns
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_USERNAME= "username";
    private static final String KEY_USER_PASSWORD = "password";

    // ITEM Table Columns
    private static final String KEY_ITEM_ID = "itemId";
    private static final String KEY_ITEM_NAME = "itemName";
    private static final String KEY_ITEM_Picture = "itemPictureUrl";
    private static final String KEY_ITEM_SELLER = "seller";

    public static synchronized SPDatabaseHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new SPDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public SPDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database connection is being configured.
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER +
                "(" +
                KEY_USER_ID + " integer primary key autoincrement," +
                KEY_USER_USERNAME +" text "+ "," +
                KEY_USER_PASSWORD + " text " +
                ")";

        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_ITEM +
                "(" +
                KEY_ITEM_ID + " integer primary key autoincrement," +
                KEY_ITEM_NAME + " TEXT" + ","+
                KEY_ITEM_Picture + " TEXT" +","+
                KEY_ITEM_SELLER + " TEXT "+","+
                "FOREIGN KEY(" + KEY_ITEM_SELLER + ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_USERNAME + ")"+
        ")";

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
            onCreate(db);
        }
    }


    // Insert a user into the database
    public boolean insertUser(User user) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            // The user might already exist in the database (i.e. the same user created multiple posts).
            ContentValues values = new ContentValues();
            values.put(KEY_USER_USERNAME, user.getUserName());
            values.put(KEY_USER_PASSWORD, user.getPassword());
            //check if this username already exists
            if ((this.queryUser(user.getUserName())).getUserID()!=-1)
            {
                return false;
            }
            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insertOrThrow(TABLE_USER, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            System.out.println("Error while register user to database");
        } finally {
            db.endTransaction();
        }
        return true;
    }

    // Insert a user into the database
    public void insertItem(Item item) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            // The user might already exist in the database (i.e. the same user created multiple posts).
            ContentValues values = new ContentValues();
            values.put(KEY_ITEM_NAME, item.getItemName());
            //values.put(KEY_ITEM_Picture,item.picture);
            int check=(this.queryUser(item.getSellerName())).getUserID();
            if (check!=-1)
            {
                //values.put(KEY_ITEM_SELLER,item.picture);
                db.insertOrThrow(TABLE_ITEM, null, values);
            }
            else
            {
                System.out.println("Create New Item failed, no user found.\n");
            }
            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.setTransactionSuccessful();
        } catch (Exception e) {
            System.out.println("Error while register user to database");
        } finally {
            db.endTransaction();
        }
    }

    /** Update the User Table, the method takes userI as input
     *  No Foreign key involved in the User Table
     *  The method use Username to locate the user
     *  ?Checking and Skipping Null Values waiting to be resolved
     */
    public int updateUser(User user) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();
        int userId = -1;
        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            // The user might already exist in the database (i.e. the same user created multiple posts).
            ContentValues values = new ContentValues();
            values.put(KEY_USER_USERNAME, user.getUserName());
            values.put(KEY_USER_PASSWORD, user.getPassword());

            // First try to update the user in case the user already exists in the database
            // This assumes userNames are unique
            int rows = db.update(TABLE_USER, values, KEY_USER_USERNAME + "= ?", new String[]{user.getUserName()});

            // Check if update succeeded
            if (rows == 1) {
                // Get the primary key of the user we just updated
                String usersSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
                        KEY_USER_ID, TABLE_USER, KEY_USER_USERNAME);
                Cursor cursor = db.rawQuery(usersSelectQuery, new String[]{String.valueOf(user.getUserName())});
                try {
                    if (cursor.moveToFirst()) {
                        userId = cursor.getInt(0);
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error while trying to update user to database");
        } finally {
            db.endTransaction();
        }
        return userId;
    }

    //The method takes username as input and find the copy of the user
    //Assuming the username is unique
    //If the user is not found, the userID entry will remain -1
    public User queryUser(String username){
         SQLiteDatabase db = getWritableDatabase();
         Cursor c= db.rawQuery("SELECT * FROM user WHERE username = '"+username+"'",null);

        if(c.getCount()<1) // UserName Not Exist
         {
            c.close();
             return new User(-1, "noUser", "no");
         }
         c.moveToFirst();
         User result= new User(Integer.parseInt(c.getString(c.getColumnIndex("id"))),c.getString(c.getColumnIndex("username")),c.getString(c.getColumnIndex("password")));
         return result;
    }

    //take username as input and delete the corresponding row
    //return 0 if none entry found, rows=numbers of entry deleted
    public int deleteUser(String username){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause= "username = ?";
        String[] whereArgs = new String[] {username};
        int rows = db.delete("user", whereClause, whereArgs);
        return rows;
    }



/*    // Get all posts in the database
    public List<ItemI> getAllItems() {
        List<ItemI> posts = new ArrayList<>();

        // SELECT * FROM POSTS
        // LEFT OUTER JOIN USERS
        // ON POSTS.KEY_POST_USER_ID_FK = USERS.KEY_USER_ID
        String ITEMS_SELECT_QUERY =
                String.format("SELECT * FROM %s LEFT OUTER JOIN %s ON %s.%s = %s.%s",
                        TABLE_POSTS,
                        TABLE_USERS,
                        TABLE_POSTS, KEY_POST_USER_ID_FK,
                        TABLE_USERS, KEY_USER_ID);

        // "getReadableDatabase()" and "getWriteableDatabase()" return the same object (except under low
        // disk space scenarios)
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(POSTS_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    User newUser = new User();
                    newUser.userName = cursor.getString(cursor.getColumnIndex(KEY_USER_NAME));
                    newUser.profilePictureUrl = cursor.getString(cursor.getColumnIndex(KEY_USER_PROFILE_PICTURE_URL));

                    Post newPost = new Post();
                    newPost.text = cursor.getString(cursor.getColumnIndex(KEY_POST_TEXT));
                    newPost.user = newUser;
                    posts.add(newPost);
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return posts;
    }*/



}
