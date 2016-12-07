//package com.example.duanli.student_portal;
//
///**
// * Created by XiaoyingJi on 2016/11/17.
// */
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class SPDatabaseHelper extends SQLiteOpenHelper{
//    private static SPDatabaseHelper sInstance;
//
//    // Database Info
//    private static final String DATABASE_NAME = "studentPortalDatabase";
//    private static final int DATABASE_VERSION = 1;
//
//    // Table Names
//    private static final String TABLE_USER = "user";
//    private static final String TABLE_ITEM = "item";
//    private static final String TABLE_EVENT= "event";
//
//    private static final String RELATIONSHIP_SELL = "sell";
//    private static final String RELATIONSHIP_RESERVE="reserve";
//    private static final String RELATIONSHIP_WATCHLIST="watchlist";
//    private static final String RELATIONSHIP_BLOCK="block";
//    private static final String RELATIONSHIP_RATING="rating";
//    private static final String RELATIONSHIP_ORGANIZE="organize";
//    private static final String RELATIONSHIP_JOIN="join";
//    private static final String RELATIONSHIP_BOOKMARK="bookmark";
//    private static final String RELATIONSHIP_PERMIT="permit";
//
//    // User Relationship Columns
//    // Sell
//    private static final String KEY_SELL_ID = "itemId";
//    private static final String KEY_SELL_SELLER = "seller";
//    private static final String KEY_SELL_STATUS = "status";
//
//    // Reserve
//    private static final String KEY_RESERVE_ID = "reservationId";
//    private static final String KEY_RESERVE_ITEM = "itemId";
//    private static final String KEY_RESERVE_BUYER = "buyer";
//    private static final String KEY_RESERVE_SELLER = "seller";
//    private static final String KEY_RESERVE_STATUS = "status";
//
//    //Watchlist
//    private static final String KEY_WATCHLIST_ID = "watchlistId";
//    private static final String KEY_WATCHLIST_SELLER = "seller";
//    private static final String KEY_WATCHLIST_BUYER = "buyer";
//
//    //Block
//    private static final String KEY_BLOCK_SELLER = "seller";
//    private static final String KEY_BLOCK_BUYER = "buyer";
//
//    //Rating
//    private static final String KEY_RATING_RESERVATION = "reservationId";
//    private static final String KEY_RATING_SELLER = "seller";
//    private static final String KEY_RATING_BUYER = "buyer";
//    private static final String KEY_RATING_SCORE = "score";
//
//    //Organize
//    private static final String KEY_ORGANIZER_EVENT = "eventID";
//    private static final String KEY_ORGANIZER_ORGANIZER = "organizer";
//
//    //JOIN
//    private static final String KEY_JOIN_EVENT = "eventID";
//    private static final String KEY_JOIN_PARTICIPANT = "participant";
//
//    //BOOKMARK
//    private static final String KEY_BOOKMARK_EVENT = "eventID";
//    private static final String KEY_BOOKMARK_PARTICIPANT = "participant";
//
//    //Permit
//    private static final String KEY_PERMIT_EVENT = "eventID";
//    private static final String KEY_PERMIT_PARTICIPANT = "participant";
//
//    // User Table Columns
//    private static final String KEY_USER_ID = "id";
//    private static final String KEY_USER_USERNAME= "username";
//    private static final String KEY_USER_PASSWORD = "password";
//
//    // ITEM Table Columns
//    private static final String KEY_ITEM_ID = "itemId";
//    private static final String KEY_ITEM_NAME = "itemName";
//    private static final String KEY_ITEM_Picture = "itemPictureUrl";
//    private static final String KEY_ITEM_SELLER = "seller";
//
//    // EVENT Table Columns
//    private static final String KEY_EVENT_ID = "eventId";
//
//    public static synchronized SPDatabaseHelper getInstance(Context context) {
//        // Use the application context, which will ensure that you
//        // don't accidentally leak an Activity's context.
//        // See this article for more information: http://bit.ly/6LRzfx
//        if (sInstance == null) {
//            sInstance = new SPDatabaseHelper(context.getApplicationContext());
//        }
//        return sInstance;
//    }
//
//    public SPDatabaseHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    // Called when the database connection is being configured.
//    @Override
//    public void onConfigure(SQLiteDatabase db) {
//        super.onConfigure(db);
//        db.setForeignKeyConstraintsEnabled(true);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER +
//                "(" +
//                KEY_USER_ID + " integer primary key autoincrement," +
//                KEY_USER_USERNAME +" text "+ "," +
//                KEY_USER_PASSWORD + " text " +
//                ")";
//
//        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_ITEM +
//                "(" +
//                KEY_ITEM_ID + " integer primary key autoincrement," +
//                KEY_ITEM_NAME + " TEXT" + ","+
//                KEY_ITEM_Picture + " TEXT" +","+
//                KEY_ITEM_SELLER + " TEXT "+","+
//                "FOREIGN KEY(" + KEY_ITEM_SELLER + ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_USERNAME + ")"+
//        ")";
//
//        String CREATE_RESERVE_TABLE = "CREATE TABLE " + RELATIONSHIP_RESERVE +
//                "(" +
//                KEY_RESERVE_ID + " integer primary key autoincrement," +
//                KEY_RESERVE_STATUS + " INTEGER" + ","+
//                "FOREIGN KEY(" + KEY_RESERVE_SELLER + ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
//                "FOREIGN KEY(" + KEY_RESERVE_BUYER + ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
//                "FOREIGN KEY(" + KEY_RESERVE_ITEM + ") REFERENCES "+TABLE_ITEM+ "("+ KEY_ITEM_ID + ")"+
//                ")";
//
//        String CREATE_SELL_TABLE = "CREATE TABLE " + RELATIONSHIP_SELL +
//                "(" +
//                KEY_SELL_STATUS + " INTEGER" + ","+
//                "FOREIGN KEY(" + KEY_SELL_SELLER + ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
//                "FOREIGN KEY(" + KEY_SELL_ID+ ") REFERENCES "+TABLE_ITEM+ "("+ KEY_ITEM_ID + ")"+
//                ")";
//
//        String CREATE_WATCHLIST_TABLE = "CREATE TABLE " + RELATIONSHIP_WATCHLIST +
//                "(" +
//                "FOREIGN KEY(" + KEY_WATCHLIST_BUYER + ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
//                "FOREIGN KEY(" + KEY_WATCHLIST_SELLER + ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
//                "FOREIGN KEY(" + KEY_WATCHLIST_ID+ ") REFERENCES "+TABLE_ITEM+ "("+ KEY_ITEM_ID + ")"+
//                ")";
//
//        String CREATE_BLOCK_TABLE = "CREATE TABLE " + RELATIONSHIP_BLOCK +
//                "(" +
//                "FOREIGN KEY(" + KEY_BLOCK_BUYER + ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
//                "FOREIGN KEY(" + KEY_BLOCK_SELLER + ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+
//                ")";
//
//        String CREATE_RATING_TABLE = "CREATE TABLE " + RELATIONSHIP_RATING +
//                "(" +
//                KEY_RATING_SCORE + " DOUBLE" + ","+
//                "FOREIGN KEY(" + KEY_RATING_BUYER + ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
//                "FOREIGN KEY(" + KEY_RATING_SELLER + ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
//                "FOREIGN KEY(" + KEY_RATING_RESERVATION+ ") REFERENCES "+RELATIONSHIP_RESERVE+ "("+ KEY_RESERVE_ID + ")"+
//                ")";
//
//        String CREATE_ORGANIZE_TABLE = "CREATE TABLE " + RELATIONSHIP_ORGANIZE +
//                "(" +
//                "FOREIGN KEY(" + KEY_ORGANIZER_ORGANIZER + ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
//                "FOREIGN KEY(" + KEY_ORGANIZER_EVENT+ ") REFERENCES "+TABLE_EVENT+ "("+ KEY_EVENT_ID + ")"+
//                ")";
//
//        String CREATE_JOIN_TABLE = "CREATE TABLE " + RELATIONSHIP_JOIN +
//                "(" +
//                "FOREIGN KEY(" + KEY_JOIN_PARTICIPANT+ ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
//                "FOREIGN KEY(" + KEY_JOIN_EVENT+ ") REFERENCES "+TABLE_EVENT+ "("+ KEY_EVENT_ID + ")"+
//                ")";
//
//        String CREATE_BOOKMARK_TABLE = "CREATE TABLE " + RELATIONSHIP_BOOKMARK +
//                "(" +
//                "FOREIGN KEY(" + KEY_BOOKMARK_PARTICIPANT+ ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
//                "FOREIGN KEY(" + KEY_BOOKMARK_EVENT+ ") REFERENCES "+TABLE_EVENT+ "("+ KEY_EVENT_ID + ")"+
//                ")";
//
//        String CREATE_PERMIT_TABLE = "CREATE TABLE " + RELATIONSHIP_PERMIT +
//                "(" +
//                "FOREIGN KEY(" + KEY_PERMIT_PARTICIPANT+ ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
//                "FOREIGN KEY(" + KEY_PERMIT_EVENT+ ") REFERENCES "+TABLE_EVENT+ "("+ KEY_EVENT_ID + ")"+
//                ")";
//
//        db.execSQL(CREATE_USER_TABLE);
//        db.execSQL(CREATE_ITEM_TABLE);
//
//        db.execSQL(CREATE_RESERVE_TABLE );
//        db.execSQL(CREATE_SELL_TABLE);
//        db.execSQL(CREATE_WATCHLIST_TABLE);
//        db.execSQL(CREATE_BLOCK_TABLE );
//        db.execSQL(CREATE_RATING_TABLE);
//        db.execSQL(CREATE_ORGANIZE_TABLE);
//        db.execSQL(CREATE_JOIN_TABLE);
//        db.execSQL(CREATE_BOOKMARK_TABLE);
//        db.execSQL(CREATE_PERMIT_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if (oldVersion != newVersion) {
//            // Simplest implementation is to drop all old tables and recreate them
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
//            onCreate(db);
//        }
//    }
//
//
//    // Insert a user into the database
//    public boolean insertUser(User user) {
//        // Create and/or open the database for writing
//        SQLiteDatabase db = getWritableDatabase();
//
//        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
//        // consistency of the database.
//        db.beginTransaction();
//        try {
//            // The user might already exist in the database (i.e. the same user created multiple posts).
//            ContentValues values = new ContentValues();
//            values.put(KEY_USER_USERNAME, user.getUserName());
//            values.put(KEY_USER_PASSWORD, user.getPassword());
//            //check if this username already exists
//            if ((this.queryUser(user.getUserName())).getUserID()!=-1)
//            {
//                return false;
//            }
//            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
//            db.insertOrThrow(TABLE_USER, null, values);
//            db.setTransactionSuccessful();
//        } catch (Exception e) {
//            System.out.println("Error while register user to database");
//        } finally {
//            db.endTransaction();
//        }
//        return true;
//    }
//
//    // Insert a user into the database
//    public void insertItem(Item item) {
//        // Create and/or open the database for writing
//        SQLiteDatabase db = getWritableDatabase();
//
//        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
//        // consistency of the database.
//        db.beginTransaction();
//        try {
//            // The user might already exist in the database (i.e. the same user created multiple posts).
//            ContentValues values = new ContentValues();
//            values.put(KEY_ITEM_NAME, item.getItemName());
//            //values.put(KEY_ITEM_Picture,item.picture);
//            int check=(this.queryUser(item.getSellerName())).getUserID();
//            if (check!=-1)
//            {
//                //values.put(KEY_ITEM_SELLER,item.picture);
//                db.insertOrThrow(TABLE_ITEM, null, values);
//            }
//            else
//            {
//                System.out.println("Create New Item failed, no user found.\n");
//            }
//            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
//            db.setTransactionSuccessful();
//        } catch (Exception e) {
//            System.out.println("Error while register user to database");
//        } finally {
//            db.endTransaction();
//        }
//    }
//
//    // Insert a sell into the database
//    public boolean insertSell(int itemId, int seller, int status) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.beginTransaction();
//        try {
//            ContentValues values = new ContentValues();
//            values.put(KEY_SELL_ID, itemId);
//            values.put(KEY_SELL_SELLER, seller);
//            values.put(KEY_SELL_STATUS, status);
//            //check if this relationship already exists
//            //
//            //
//            //
//            db.insertOrThrow(RELATIONSHIP_SELL, null, values);
//            db.setTransactionSuccessful();
//        } catch (Exception e) {
//            System.out.println("Error while create new sell to database");
//        } finally {
//            db.endTransaction();
//        }
//        return true;
//    }
//
//    // Insert a reservation into the database
//    public boolean insertReserve(int itemId, int seller, int buyer, int status) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.beginTransaction();
//        try {
//            ContentValues values = new ContentValues();
//            values.put(KEY_RESERVE_ITEM, itemId);
//            values.put(KEY_RESERVE_BUYER, buyer);
//            values.put(KEY_RESERVE_SELLER, seller);
//            values.put(KEY_RESERVE_STATUS, status);
//            //check if this relationship already exists
//            //
//            //
//            //
//            db.insertOrThrow(RELATIONSHIP_RESERVE, null, values);
//            db.setTransactionSuccessful();
//        } catch (Exception e) {
//            System.out.println("Error while create new reservation to database");
//        } finally {
//            db.endTransaction();
//        }
//        return true;
//    }
//
//    // Insert a watchlist into the database
//    public boolean insertWatchlist(int itemId, int buyer, int seller) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.beginTransaction();
//        try {
//            ContentValues values = new ContentValues();
//            values.put(KEY_WATCHLIST_ID, itemId);
//            values.put(KEY_WATCHLIST_BUYER, buyer);
//            values.put(KEY_WATCHLIST_SELLER, seller);
//            //check if this relationship already exists
//            //
//            //
//            //
//            db.insertOrThrow(RELATIONSHIP_WATCHLIST, null, values);
//            db.setTransactionSuccessful();
//        } catch (Exception e) {
//            System.out.println("Error while create new watchlist to database");
//        } finally {
//            db.endTransaction();
//        }
//        return true;
//    }
//
//    // Insert a block into the database
//    public boolean insertBlock(int buyer, int seller) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.beginTransaction();
//        try {
//            ContentValues values = new ContentValues();
//            values.put(KEY_BLOCK_BUYER, buyer);
//            values.put(KEY_BLOCK_SELLER, seller);
//            //check if this relationship already exists
//            //
//            //
//            //
//            db.insertOrThrow(RELATIONSHIP_BLOCK, null, values);
//            db.setTransactionSuccessful();
//        } catch (Exception e) {
//            System.out.println("Error while create new block to database");
//        } finally {
//            db.endTransaction();
//        }
//        return true;
//    }
//
//    // Insert a rating into the database
//    public boolean insertRating(int reservationId, int buyer, int seller, double score) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.beginTransaction();
//        try {
//            ContentValues values = new ContentValues();
//            values.put(KEY_RATING_SCORE, score);
//            values.put(KEY_RATING_BUYER, buyer);
//            values.put(KEY_RATING_SELLER, seller);
//            values.put(KEY_RATING_RESERVATION, reservationId);
//            //check if this relationship already exists
//            //
//            //
//            //
//            db.insertOrThrow(RELATIONSHIP_RATING, null, values);
//            db.setTransactionSuccessful();
//        } catch (Exception e) {
//            System.out.println("Error while create new rating to database");
//        } finally {
//            db.endTransaction();
//        }
//        return true;
//    }
//
//    // Insert a organize into the database
//    public boolean insertOrganize(int eventId, int organizer) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.beginTransaction();
//        try {
//            ContentValues values = new ContentValues();
//            values.put(KEY_ORGANIZER_EVENT, eventId);
//            values.put(KEY_ORGANIZER_ORGANIZER, organizer);
//            //check if this relationship already exists
//            //
//            //
//            //
//            db.insertOrThrow(RELATIONSHIP_ORGANIZE, null, values);
//            db.setTransactionSuccessful();
//        } catch (Exception e) {
//            System.out.println("Error while create new organize to database");
//        } finally {
//            db.endTransaction();
//        }
//        return true;
//    }
//
//    // Insert a watchlist into the database
//    public boolean insertJoin(int eventId, int participant) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.beginTransaction();
//        try {
//            ContentValues values = new ContentValues();
//            values.put(KEY_JOIN_EVENT, eventId);
//            values.put(KEY_JOIN_PARTICIPANT, participant);
//            //check if this relationship already exists
//            //
//            //
//            //
//            db.insertOrThrow(RELATIONSHIP_JOIN, null, values);
//            db.setTransactionSuccessful();
//        } catch (Exception e) {
//            System.out.println("Error while create new join to database");
//        } finally {
//            db.endTransaction();
//        }
//        return true;
//    }
//
//    // Insert a watchlist into the database
//    public boolean insertBookmark(int eventId, int participant) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.beginTransaction();
//        try {
//            ContentValues values = new ContentValues();
//            values.put(KEY_BOOKMARK_EVENT, eventId);
//            values.put(KEY_BOOKMARK_PARTICIPANT, participant);
//            //check if this relationship already exists
//            //
//            //
//            //
//            db.insertOrThrow(RELATIONSHIP_BOOKMARK, null, values);
//            db.setTransactionSuccessful();
//        } catch (Exception e) {
//            System.out.println("Error while create new bookmark to database");
//        } finally {
//            db.endTransaction();
//        }
//        return true;
//    }
//
//    // Insert a watchlist into the database
//    public boolean insertPermit(int eventId, int participant) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.beginTransaction();
//        try {
//            ContentValues values = new ContentValues();
//            values.put(KEY_PERMIT_EVENT, eventId);
//            values.put(KEY_PERMIT_PARTICIPANT, participant);
//            //check if this relationship already exists
//            //
//            //
//            //
//            db.insertOrThrow(RELATIONSHIP_PERMIT, null, values);
//            db.setTransactionSuccessful();
//        } catch (Exception e) {
//            System.out.println("Error while create new permit to database");
//        } finally {
//            db.endTransaction();
//        }
//        return true;
//    }
//    /** Update the User Table, the method takes userI as input
//     *  No Foreign key involved in the User Table
//     *  The method use Username to locate the user
//     *  ?Checking and Skipping Null Values waiting to be resolved
//     */
//    public int updateUser(User user) {
//        // Create and/or open the database for writing
//        SQLiteDatabase db = getWritableDatabase();
//        int userId = -1;
//        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
//        // consistency of the database.
//        db.beginTransaction();
//        try {
//            // The user might already exist in the database (i.e. the same user created multiple posts).
//            ContentValues values = new ContentValues();
//            values.put(KEY_USER_USERNAME, user.getUserName());
//            values.put(KEY_USER_PASSWORD, user.getPassword());
//
//            // First try to update the user in case the user already exists in the database
//            // This assumes userNames are unique
//            int rows = db.update(TABLE_USER, values, KEY_USER_USERNAME + "= ?", new String[]{user.getUserName()});
//
//            // Check if update succeeded
//            if (rows == 1) {
//                // Get the primary key of the user we just updated
//                String usersSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
//                        KEY_USER_ID, TABLE_USER, KEY_USER_USERNAME);
//                Cursor cursor = db.rawQuery(usersSelectQuery, new String[]{String.valueOf(user.getUserName())});
//                try {
//                    if (cursor.moveToFirst()) {
//                        userId = cursor.getInt(0);
//                        db.setTransactionSuccessful();
//                    }
//                } finally {
//                    if (cursor != null && !cursor.isClosed()) {
//                        cursor.close();
//                    }
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Error while trying to update user to database");
//        } finally {
//            db.endTransaction();
//        }
//        return userId;
//    }
//
//    //The method takes username as input and find the copy of the user
//    //Assuming the username is unique
//    //If the user is not found, the userID entry will remain -1
//    public User queryUser(String username){
//         SQLiteDatabase db = getWritableDatabase();
//         Cursor c= db.rawQuery("SELECT * FROM user WHERE username = '"+username+"'",null);
//
//        if(c.getCount()<1) // UserName Not Exist
//         {
//            c.close();
//             return new User(-1, "noUser", "no");
//         }
//         c.moveToFirst();
//         User result= new User(Integer.parseInt(c.getString(c.getColumnIndex("id"))),c.getString(c.getColumnIndex("username")),c.getString(c.getColumnIndex("password")));
//         return result;
//    }
//
//    //take username as input and delete the corresponding row
//    //return 0 if none entry found, rows=numbers of entry deleted
//    public int deleteUser(String username){
//        SQLiteDatabase db = getWritableDatabase();
//        String whereClause= "username = ?";
//        String[] whereArgs = new String[] {username};
//        int rows = db.delete("user", whereClause, whereArgs);
//        return rows;
//    }
//
//
//
///*    // Get all posts in the database
//    public List<ItemI> getAllItems() {
//        List<ItemI> posts = new ArrayList<>();
//
//        // SELECT * FROM POSTS
//        // LEFT OUTER JOIN USERS
//        // ON POSTS.KEY_POST_USER_ID_FK = USERS.KEY_USER_ID
//        String ITEMS_SELECT_QUERY =
//                String.format("SELECT * FROM %s LEFT OUTER JOIN %s ON %s.%s = %s.%s",
//                        TABLE_POSTS,
//                        TABLE_USERS,
//                        TABLE_POSTS, KEY_POST_USER_ID_FK,
//                        TABLE_USERS, KEY_USER_ID);
//
//        // "getReadableDatabase()" and "getWriteableDatabase()" return the same object (except under low
//        // disk space scenarios)
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.rawQuery(POSTS_SELECT_QUERY, null);
//        try {
//            if (cursor.moveToFirst()) {
//                do {
//                    User newUser = new User();
//                    newUser.userName = cursor.getString(cursor.getColumnIndex(KEY_USER_NAME));
//                    newUser.profilePictureUrl = cursor.getString(cursor.getColumnIndex(KEY_USER_PROFILE_PICTURE_URL));
//
//                    Post newPost = new Post();
//                    newPost.text = cursor.getString(cursor.getColumnIndex(KEY_POST_TEXT));
//                    newPost.user = newUser;
//                    posts.add(newPost);
//                } while(cursor.moveToNext());
//            }
//        } catch (Exception e) {
//            Log.d(TAG, "Error while trying to get posts from database");
//        } finally {
//            if (cursor != null && !cursor.isClosed()) {
//                cursor.close();
//            }
//        }
//        return posts;
//    }*/
//
//
//
//}

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
import java.util.LinkedList;
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

    private static final String RELATIONSHIP_SELL = "sell";
    private static final String RELATIONSHIP_RESERVE="reserve";
    private static final String RELATIONSHIP_WATCHLIST="watchlist";
    private static final String RELATIONSHIP_BLOCK="block";
    private static final String RELATIONSHIP_RATING="rating";
    private static final String RELATIONSHIP_ORGANIZE="organize";
    private static final String RELATIONSHIP_JOIN="join";
    private static final String RELATIONSHIP_BOOKMARK="bookmark";
    private static final String RELATIONSHIP_PERMIT="permit";

    // User Relationship Columns
    // Sell
    private static final String KEY_SELL_ID = "itemId";
    private static final String KEY_SELL_SELLER = "seller";
    //
    private static final String KEY_SELL_STATUS = "status";

    // Reserve
    private static final String KEY_RESERVE_ID = "reservationId";
    private static final String KEY_RESERVE_ITEM = "itemId";
    private static final String KEY_RESERVE_BUYER = "buyer";
    private static final String KEY_RESERVE_SELLER = "seller";
    private static final String KEY_RESERVE_STATUS = "status";

    //Watchlist
    private static final String KEY_WATCHLIST_ID = "watchlistId";
    private static final String KEY_WATCHLIST_SELLER = "seller";
    private static final String KEY_WATCHLIST_BUYER = "buyer";

    //Block
    private static final String KEY_BLOCK_SELLER = "seller";
    private static final String KEY_BLOCK_BUYER = "buyer";

    //Rating
    private static final String KEY_RATING_RESERVATION = "reservationId";
    private static final String KEY_RATING_SELLER = "seller";
    private static final String KEY_RATING_BUYER = "buyer";
    private static final String KEY_RATING_SCORE = "score";

    //Organize
    private static final String KEY_ORGANIZER_EVENT = "eventID";
    private static final String KEY_ORGANIZER_ORGANIZER = "organizer";

    //JOIN
    private static final String KEY_JOIN_EVENT = "eventID";
    private static final String KEY_JOIN_PARTICIPANT = "participant";

    //BOOKMARK
    private static final String KEY_BOOKMARK_EVENT = "eventID";
    private static final String KEY_BOOKMARK_PARTICIPANT = "participant";

    //Permit
    private static final String KEY_PERMIT_EVENT = "eventID";
    private static final String KEY_PERMIT_PARTICIPANT = "participant";

    // User Table Columns
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_USERNAME = "username";
    private static final String KEY_USER_PASSWORD = "password";
    private static final String KEY_USER_GENDER = "gender";
    private static final String KEY_USER_MAJOR = "major";
    private static final String KEY_USER_EMAIL = "email";
    private static final String KEY_USER_RATE = "rate";
    private static final String KEY_USER_NUMBERRATE = "numberRate";
    private static final String KEY_USER_CONTACT = "contact";

    // ITEM Table Columns
    private static final String KEY_ITEM_ID = "itemId";
    private static final String KEY_ITEM_NAME = "itemName";
    private static final String KEY_ITEM_PICTURE = "itemPictureUrl";
    private static final String KEY_ITEM_SELLER = "seller";
    private static final String KEY_ITEM_PRICE = "price";
    private static final String KEY_ITEM_CONTACT = "contact";
    private static final String KEY_ITEM_DESCRIPTION = "description";
    //0:item is unreserved 1:item is reserved
    private static final String KEY_ITEM_STATUS = "status";

    // EVENT Table Columns
    private static final String KEY_EVENT_ID = "eventId";
    private static final String KEY_EVENT_NAME = "eventName";
    private static final String KEY_EVENT_PICTURE = "eventPictureUrl";
    private static final String KEY_EVENT_ORGANIZER = "organizer";
    private static final String KEY_EVENT_DATE = "date";
    private static final String KEY_EVENT_TIME = "time";
    private static final String KEY_EVENT_LOCATION = "location";
    private static final String KEY_EVENT_PRICE = "price";
    private static final String KEY_EVENT_CAPACITY = "capacity";
    private static final String KEY_EVENT_DESCRIPTION = "description";

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
        System.out.println("test line");
    }

    // Called when the database connection is being configured.
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("test line");
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER +
                "(" +
                KEY_USER_ID + " integer primary key autoincrement," +
                KEY_USER_USERNAME +" text "+ "," +
                KEY_USER_PASSWORD + " text " + "," +
                KEY_USER_GENDER + " text " + "," +
                KEY_USER_MAJOR + " text " + "," +
                KEY_USER_EMAIL + " text " + "," +
                KEY_USER_RATE + " text " + "," +
                KEY_USER_NUMBERRATE + " text " + "," +
                KEY_USER_CONTACT + " text " +
                ")";

        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_ITEM +
                "(" +
                KEY_ITEM_ID + " integer primary key autoincrement," +
                KEY_ITEM_NAME + " TEXT" + ","+
                KEY_ITEM_PICTURE + " TEXT" +","+
                KEY_ITEM_SELLER + " INTEGER "+","+
                KEY_ITEM_PRICE + " INTEGER" + ","+
                KEY_ITEM_CONTACT + " TEXT" +","+
                KEY_ITEM_DESCRIPTION + " TEXT "+","+
                KEY_ITEM_STATUS + " INTEGER "+","+
                "FOREIGN KEY(" + KEY_ITEM_SELLER + ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+
                ")";

        String CREATE_EVENT_TABLE = "CREATE TABLE " + TABLE_EVENT +
                "(" +
                KEY_EVENT_ID + " integer primary key autoincrement," +
                KEY_EVENT_NAME + " TEXT" + "," +
                KEY_EVENT_PICTURE + " TEXT" + "," +
                KEY_EVENT_ORGANIZER + " INTEGER " + "," +
                KEY_EVENT_DATE + " TEXT" + "," +
                KEY_EVENT_TIME + " TEXT" + "," +
                KEY_EVENT_LOCATION + " TEXT " + "," +
                KEY_EVENT_PRICE + " INTEGER" + "," +
                KEY_EVENT_CAPACITY + " INTEGER" + "," +
                KEY_EVENT_DESCRIPTION + " TEXT " + "," +
                "FOREIGN KEY(" + KEY_EVENT_ORGANIZER + ") REFERENCES " + TABLE_USER + "(" + KEY_USER_ID + ")" +
                ")";;

        String CREATE_RESERVE_TABLE = "CREATE TABLE " + RELATIONSHIP_RESERVE +
                "(" +
                KEY_RESERVE_ID + " integer primary key autoincrement," +
                KEY_RESERVE_STATUS + " INTEGER" + ","+
                "FOREIGN KEY(" + KEY_RESERVE_SELLER + ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
                "FOREIGN KEY(" + KEY_RESERVE_BUYER + ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
                "FOREIGN KEY(" + KEY_RESERVE_ITEM + ") REFERENCES "+TABLE_ITEM+ "("+ KEY_ITEM_ID + ")"+
                ")";

        String CREATE_SELL_TABLE = "CREATE TABLE " + RELATIONSHIP_SELL +
                "(" +
                KEY_SELL_STATUS + " INTEGER" + ","+
                "FOREIGN KEY(" + KEY_SELL_SELLER + ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
                "FOREIGN KEY(" + KEY_SELL_ID+ ") REFERENCES "+TABLE_ITEM+ "("+ KEY_ITEM_ID + ")"+
                ")";

        String CREATE_WATCHLIST_TABLE = "CREATE TABLE " + RELATIONSHIP_WATCHLIST +
                "(" +
                "FOREIGN KEY(" + KEY_WATCHLIST_BUYER + ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
                "FOREIGN KEY(" + KEY_WATCHLIST_SELLER + ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
                "FOREIGN KEY(" + KEY_WATCHLIST_ID+ ") REFERENCES "+TABLE_ITEM+ "("+ KEY_ITEM_ID + ")"+
                ")";

        String CREATE_BLOCK_TABLE = "CREATE TABLE " + RELATIONSHIP_BLOCK +
                "(" +
                "FOREIGN KEY(" + KEY_BLOCK_BUYER + ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
                "FOREIGN KEY(" + KEY_BLOCK_SELLER + ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+
                ")";

        String CREATE_RATING_TABLE = "CREATE TABLE " + RELATIONSHIP_RATING +
                "(" +
                KEY_RATING_SCORE + " DOUBLE" + ","+
                "FOREIGN KEY(" + KEY_RATING_BUYER + ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
                "FOREIGN KEY(" + KEY_RATING_SELLER + ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
                "FOREIGN KEY(" + KEY_RATING_RESERVATION+ ") REFERENCES "+RELATIONSHIP_RESERVE+ "("+ KEY_RESERVE_ID + ")"+
                ")";

        String CREATE_ORGANIZE_TABLE = "CREATE TABLE " + RELATIONSHIP_ORGANIZE +
                "(" +
                "FOREIGN KEY(" + KEY_ORGANIZER_ORGANIZER + ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
                "FOREIGN KEY(" + KEY_ORGANIZER_EVENT+ ") REFERENCES "+TABLE_EVENT+ "("+ KEY_EVENT_ID + ")"+
                ")";

        String CREATE_JOIN_TABLE = "CREATE TABLE " + RELATIONSHIP_JOIN +
                "(" +
                "FOREIGN KEY(" + KEY_JOIN_PARTICIPANT+ ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
                "FOREIGN KEY(" + KEY_JOIN_EVENT+ ") REFERENCES "+TABLE_EVENT+ "("+ KEY_EVENT_ID + ")"+
                ")";

        String CREATE_BOOKMARK_TABLE = "CREATE TABLE " + RELATIONSHIP_BOOKMARK +
                "(" +
                "FOREIGN KEY(" + KEY_BOOKMARK_PARTICIPANT+ ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
                "FOREIGN KEY(" + KEY_BOOKMARK_EVENT+ ") REFERENCES "+TABLE_EVENT+ "("+ KEY_EVENT_ID + ")"+
                ")";

        String CREATE_PERMIT_TABLE = "CREATE TABLE " + RELATIONSHIP_PERMIT +
                "(" +
                "FOREIGN KEY(" + KEY_PERMIT_PARTICIPANT+ ") REFERENCES "+TABLE_USER+ "("+ KEY_USER_ID + ")"+","+
                "FOREIGN KEY(" + KEY_PERMIT_EVENT+ ") REFERENCES "+TABLE_EVENT+ "("+ KEY_EVENT_ID + ")"+
                ")";

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_ITEM_TABLE);
        db.execSQL(CREATE_EVENT_TABLE);

        db.execSQL(CREATE_RESERVE_TABLE );
        db.execSQL(CREATE_SELL_TABLE);
        db.execSQL(CREATE_WATCHLIST_TABLE);
        db.execSQL(CREATE_BLOCK_TABLE );
        db.execSQL(CREATE_RATING_TABLE);
        db.execSQL(CREATE_ORGANIZE_TABLE);
        db.execSQL(CREATE_JOIN_TABLE);
        db.execSQL(CREATE_BOOKMARK_TABLE);
        db.execSQL(CREATE_PERMIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
            db.execSQL("DROP TABLE IF EXISTS " + RELATIONSHIP_SELL);
            db.execSQL("DROP TABLE IF EXISTS " + RELATIONSHIP_BLOCK);
            db.execSQL("DROP TABLE IF EXISTS " + RELATIONSHIP_BOOKMARK);
            db.execSQL("DROP TABLE IF EXISTS " + RELATIONSHIP_JOIN);
            db.execSQL("DROP TABLE IF EXISTS " + RELATIONSHIP_ORGANIZE);
            db.execSQL("DROP TABLE IF EXISTS " + RELATIONSHIP_PERMIT);
            db.execSQL("DROP TABLE IF EXISTS " + RELATIONSHIP_RATING);
            db.execSQL("DROP TABLE IF EXISTS " + RELATIONSHIP_RESERVE);
            db.execSQL("DROP TABLE IF EXISTS " + RELATIONSHIP_WATCHLIST);
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
        db.beginTransaction();
        System.out.println("begin");
        try {
            // The user might already exist in the database (i.e. the same user created multiple posts).
            ContentValues values = new ContentValues();
            values.put(KEY_ITEM_NAME, item.getItemName());
            values.put(KEY_ITEM_SELLER, item.getSellerName());
            values.put(KEY_ITEM_PRICE, item.getPrice());
            values.put(KEY_ITEM_CONTACT, item.getContact());
            values.put(KEY_ITEM_DESCRIPTION, item.getDescription());
            values.put(KEY_ITEM_STATUS, item.getStatus());
            System.out.println("put values");
            //values.put(KEY_ITEM_Picture,item.picture);
            db.insertOrThrow(TABLE_ITEM, null, values);
//            int check=(this.queryUser(item.getSellerName())).getUserID();
//            if (check!=-1)
//            {
//                values.put(KEY_ITEM_SELLER,item.getSellerName());
//                db.insertOrThrow(TABLE_ITEM, null, values);
//            }
//            else
//            {
//                System.out.println("Create New Item failed, no user found.\n");
//            }
            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.setTransactionSuccessful();
            System.out.println("successful");
        } catch (Exception e) {
            System.out.println("Error while insert item to database");
        } finally {
            db.endTransaction();
        }
    }

    // Insert a user into the database
    public boolean insertEvent(Event event) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();
        System.out.println("writable");
        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        System.out.println("begin");
        try {
            // The user might already exist in the database (i.e. the same user created multiple posts).
            ContentValues values = new ContentValues();
            values.put(KEY_EVENT_ORGANIZER, event.getOrganizerID());
            values.put(KEY_EVENT_NAME, event.getEventName());
            values.put(KEY_EVENT_DATE, event.getDate());
            values.put(KEY_EVENT_TIME, event.getTime());
            values.put(KEY_EVENT_LOCATION, event.getLocation());
            values.put(KEY_EVENT_PRICE, event.getPrice());
            values.put(KEY_EVENT_CAPACITY, event.getCapacity());
            values.put(KEY_EVENT_DESCRIPTION, event.getDescription());
            System.out.println("put values");
            //check if this username already exists
//            if ((this.queryUserID(event.getOrganizerID())).getUserID()!=-1)
//            {
//                return false;
//            }
            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insertOrThrow(TABLE_EVENT, null, values);
            System.out.println("insert or throw");
            db.setTransactionSuccessful();
            System.out.println("successful");
        } catch (Exception e) {
            System.out.println("Error while insert event to database");
        } finally {
            db.endTransaction();
            System.out.println("end");
        }
        return true;
    }

    // Insert a sell into the database
    public boolean insertSell(int itemId, int seller, int status) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_SELL_ID, itemId);
            values.put(KEY_SELL_SELLER, seller);
            values.put(KEY_SELL_STATUS, status);
            //check if this relationship already exists
            //
            //
            //
            db.insertOrThrow(RELATIONSHIP_SELL, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            System.out.println("Error while create new sell to database");
        } finally {
            db.endTransaction();
        }
        return true;
    }

    // Insert a reservation into the database
    public boolean insertReserve(int itemId, int seller, int buyer, int status) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_RESERVE_ITEM, itemId);
            values.put(KEY_RESERVE_BUYER, buyer);
            values.put(KEY_RESERVE_SELLER, seller);
            values.put(KEY_RESERVE_STATUS, status);
            //check if this relationship already exists
            //
            //
            //
            db.insertOrThrow(RELATIONSHIP_RESERVE, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            System.out.println("Error while create new reservation to database");
        } finally {
            db.endTransaction();
        }
        return true;
    }

    // Insert a watchlist into the database
    public boolean insertWatchlist(int itemId, int buyer, int seller) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_WATCHLIST_ID, itemId);
            values.put(KEY_WATCHLIST_BUYER, buyer);
            values.put(KEY_WATCHLIST_SELLER, seller);
            //check if this relationship already exists
            //
            //
            //
            db.insertOrThrow(RELATIONSHIP_WATCHLIST, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            System.out.println("Error while create new watchlist to database");
        } finally {
            db.endTransaction();
        }
        return true;
    }

    // Insert a block into the database
    public boolean insertBlock(int buyer, int seller) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_BLOCK_BUYER, buyer);
            values.put(KEY_BLOCK_SELLER, seller);
            //check if this relationship already exists
            //
            //
            //
            db.insertOrThrow(RELATIONSHIP_BLOCK, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            System.out.println("Error while create new block to database");
        } finally {
            db.endTransaction();
        }
        return true;
    }

    // Insert a rating into the database
    public boolean insertRating(int reservationId, int buyer, int seller, double score) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_RATING_SCORE, score);
            values.put(KEY_RATING_BUYER, buyer);
            values.put(KEY_RATING_SELLER, seller);
            values.put(KEY_RATING_RESERVATION, reservationId);
            //check if this relationship already exists
            //
            //
            //
            db.insertOrThrow(RELATIONSHIP_RATING, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            System.out.println("Error while create new rating to database");
        } finally {
            db.endTransaction();
        }
        return true;
    }

    // Insert a organize into the database
    public boolean insertOrganize(int eventId, int organizer) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_ORGANIZER_EVENT, eventId);
            values.put(KEY_ORGANIZER_ORGANIZER, organizer);
            //check if this relationship already exists
            //
            //
            //
            db.insertOrThrow(RELATIONSHIP_ORGANIZE, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            System.out.println("Error while create new organize to database");
        } finally {
            db.endTransaction();
        }
        return true;
    }

    // Insert a watchlist into the database
    public boolean insertJoin(int eventId, int participant) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_JOIN_EVENT, eventId);
            values.put(KEY_JOIN_PARTICIPANT, participant);
            //check if this relationship already exists
            //
            //
            //
            db.insertOrThrow(RELATIONSHIP_JOIN, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            System.out.println("Error while create new join to database");
        } finally {
            db.endTransaction();
        }
        return true;
    }

    // Insert a watchlist into the database
    public boolean insertBookmark(int eventId, int participant) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_BOOKMARK_EVENT, eventId);
            values.put(KEY_BOOKMARK_PARTICIPANT, participant);
            //check if this relationship already exists
            //
            //
            //
            db.insertOrThrow(RELATIONSHIP_BOOKMARK, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            System.out.println("Error while create new bookmark to database");
        } finally {
            db.endTransaction();
        }
        return true;
    }

    // Insert a watchlist into the database
    public boolean insertPermit(int eventId, int participant) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_PERMIT_EVENT, eventId);
            values.put(KEY_PERMIT_PARTICIPANT, participant);
            //check if this relationship already exists
            //
            //
            //
            db.insertOrThrow(RELATIONSHIP_PERMIT, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            System.out.println("Error while create new permit to database");
        } finally {
            db.endTransaction();
        }
        return true;
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

    public int updateEvent(Event event) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();
        int eventId = -1;
        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            // The user might already exist in the database (i.e. the same user created multiple posts).
            ContentValues values = new ContentValues();
            values.put(KEY_EVENT_ORGANIZER, event.getOrganizerID());
            values.put(KEY_EVENT_NAME, event.getEventName());
            values.put(KEY_EVENT_DATE, event.getDate());
            values.put(KEY_EVENT_TIME, event.getTime());
            values.put(KEY_EVENT_LOCATION, event.getLocation());
            values.put(KEY_EVENT_PRICE, event.getPrice());
            values.put(KEY_EVENT_CAPACITY, event.getCapacity());
            values.put(KEY_EVENT_DESCRIPTION, event.getDescription());
            // First try to update the user in case the user already exists in the database
            // This assumes userNames are unique
            int rows = db.update(TABLE_EVENT, values, KEY_EVENT_ID + "= ?", new String[]{String.valueOf(event.getEventID())});

            // Check if update succeeded
            if (rows == 1) {
                // Get the primary key of the user we just updated
                String usersSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
                        KEY_EVENT_ID, TABLE_EVENT, KEY_EVENT_ID);
                Cursor cursor = db.rawQuery(usersSelectQuery, new String[]{String.valueOf(event.getEventID())});
                try {
                    if (cursor.moveToFirst()) {
                        eventId = cursor.getInt(0);
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error while trying to update event to database");
        } finally {
            db.endTransaction();
        }
        return eventId;
    }

    public int updateItem(Item item) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();
        int itemId = -1;
        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            // The user might already exist in the database (i.e. the same user created multiple posts).
            ContentValues values = new ContentValues();
            values.put(KEY_ITEM_NAME, item.getItemName());
            values.put(KEY_ITEM_SELLER, item.getSellerID());
            values.put(KEY_ITEM_PRICE, item.getPrice());
            values.put(KEY_ITEM_CONTACT, item.getContact());
            values.put(KEY_ITEM_DESCRIPTION, item.getDescription());
            values.put(KEY_ITEM_STATUS, item.getStatus());
            // First try to update the user in case the user already exists in the database
            // This assumes userNames are unique
            int rows = db.update(TABLE_ITEM, values, KEY_ITEM_ID + "= ?", new String[]{String.valueOf(item.getItemID())});
            // Check if update succeeded
            if (rows == 1) {
                // Get the primary key of the user we just updated
                String usersSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
                        KEY_ITEM_ID, TABLE_ITEM, KEY_ITEM_ID);
                Cursor cursor = db.rawQuery(usersSelectQuery, new String[]{String.valueOf(item.getItemID())});
                try {
                    if (cursor.moveToFirst()) {
                        itemId = cursor.getInt(0);
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error while trying to update item to database");
        } finally {
            db.endTransaction();
        }
        return itemId;
    }


    public int updateItemStatus(int itemID, int status)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_ITEM_STATUS, status);
            int rows = db.update(TABLE_ITEM, values, KEY_ITEM_ID + "= ?", new String[]{Integer.toString(itemID)});
            // Check if update succeeded
            if (rows == 1) {
                db.setTransactionSuccessful();
                return 1;
            }
        } catch (Exception e) {
            System.out.println("Error while trying to update item status");
        } finally {
            db.endTransaction();
        }
        return 0;
    }

    public int updateReservationStatus(int reservationID, int status)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_RESERVE_STATUS, status);
            int rows = db.update(RELATIONSHIP_RESERVE, values, KEY_RESERVE_ID + "= ?", new String[]{Integer.toString(reservationID)});
            // Check if update succeeded
            if (rows == 1) {
                db.setTransactionSuccessful();
                return 1;
            }
        } catch (Exception e) {
            System.out.println("Error while trying to update reservation status");
        } finally {
            db.endTransaction();
        }
        return 0;
    }

    //The method takes username as input and find the copy of the user
    //Assuming the username is unique
    //If the user is not found, the userID entry will remain -1
    public User queryUser(String username){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c= db.rawQuery("SELECT * FROM user WHERE "+KEY_USER_USERNAME+" = '"+username+"'",null);

        if(c.getCount()<1) // UserName Not Exist
        {
            c.close();
            return new User(-1, "noUser", "no");
        }
        c.moveToFirst();
        User result= new User(Integer.parseInt(c.getString(c.getColumnIndex(KEY_USER_ID))),c.getString(c.getColumnIndex(KEY_USER_USERNAME)),c.getString(c.getColumnIndex(KEY_USER_PASSWORD)));
        return result;
    }

    public User queryUserID(int userID){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c= db.rawQuery("SELECT * FROM user WHERE "+KEY_USER_ID+" = '"+userID+"'",null);

        if(c.getCount()<1) // UserName Not Exist
        {
            c.close();
            return new User(-1, "noUser", "no");
        }
        c.moveToFirst();
        //User result= new User(Integer.parseInt(c.getString(c.getColumnIndex(KEY_USER_ID))),c.getString(c.getColumnIndex(KEY_USER_USERNAME)),c.getString(c.getColumnIndex(KEY_USER_PASSWORD)),Integer.parseInt(c.getString(c.getColumnIndex(KEY_USER_GENDER))),c.getString(c.getColumnIndex(KEY_USER_MAJOR)),c.getString(c.getColumnIndex(KEY_USER_EMAIL)),Double.parseDouble(c.getString(c.getColumnIndex(KEY_USER_RATE))),Integer.parseInt(c.getString(c.getColumnIndex(KEY_USER_NUMBERRATE))),c.getString(c.getColumnIndex(KEY_USER_CONTACT)));
        User result= new User(Integer.parseInt(c.getString(c.getColumnIndex(KEY_USER_ID))),c.getString(c.getColumnIndex(KEY_USER_USERNAME)),c.getString(c.getColumnIndex(KEY_USER_PASSWORD)));
        return result;
    }

    public Item queryItem(int itemID){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c= db.rawQuery("SELECT * FROM item WHERE "+KEY_ITEM_ID+" = '"+itemID+"'",null);

        if(c.getCount()<1) // itemName Not Exist
        {
            c.close();
            return new Item(-1, "noItem", -1);
        }
        c.moveToFirst();
        Item result= new Item(Integer.parseInt(c.getString(c.getColumnIndex(KEY_ITEM_ID))),
                c.getString(c.getColumnIndex(KEY_ITEM_NAME)),
                Integer.parseInt(c.getString(c.getColumnIndex(KEY_ITEM_SELLER))),
                Integer.parseInt(c.getString(c.getColumnIndex(KEY_ITEM_PRICE))),
                c.getString(c.getColumnIndex(KEY_ITEM_CONTACT)),
                        c.getString(c.getColumnIndex(KEY_ITEM_DESCRIPTION)),
                Integer.parseInt(c.getString(c.getColumnIndex(KEY_ITEM_STATUS))));
        return result;
    }

    public Event queryEvent(int eventID){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c= db.rawQuery("SELECT * FROM "+TABLE_EVENT+" WHERE "+KEY_EVENT_ID+" = '"+eventID+"'",null);

        if(c.getCount()<1) // itemName Not Exist
        {
            c.close();
            return new Event();
        }
        c.moveToFirst();
        Event result= new Event(Integer.parseInt(c.getString(c.getColumnIndex(KEY_EVENT_ID))),
                Integer.parseInt(c.getString(c.getColumnIndex(KEY_EVENT_ORGANIZER))),
                c.getString(c.getColumnIndex(KEY_EVENT_NAME)),
                c.getString(c.getColumnIndex(KEY_EVENT_DATE)),
                c.getString(c.getColumnIndex(KEY_EVENT_TIME)),
                c.getString(c.getColumnIndex(KEY_EVENT_LOCATION)),
                Integer.parseInt(c.getString(c.getColumnIndex(KEY_EVENT_PRICE))),
                Integer.parseInt(c.getString(c.getColumnIndex(KEY_EVENT_CAPACITY))),
                c.getString(c.getColumnIndex(KEY_EVENT_DESCRIPTION)));
        return result;
    }

    public int queryReserve(int sellerID, int buyerID){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c= db.rawQuery("SELECT * FROM "+RELATIONSHIP_RESERVE+" WHERE "+
                KEY_RESERVE_SELLER+" = '"+Integer.toString(sellerID)+"'"+
                " AND "+KEY_RESERVE_BUYER+" = '"+Integer.toString(buyerID)+"'", null);

        if(c.getCount()<1) // itemName Not Exist
        {
            c.close();
            return 0;
        }
        c.moveToFirst();
        return Integer.parseInt(c.getString(c.getColumnIndex(KEY_RESERVE_ID)));
    }

    //return the linked list of all event ids that the participant bookmarked
    public LinkedList<Integer> queryBookmark(int participant)
    {
        LinkedList<Integer> result= new LinkedList<Integer>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c= db.rawQuery("SELECT * FROM "+RELATIONSHIP_BOOKMARK+" WHERE "+KEY_BOOKMARK_PARTICIPANT+" = '"+participant+"'",null);
        if(c.getCount()<1) // seller didn't create any sell
        {
            c.close();
            return result;
        }
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    result.add(Integer.parseInt(c.getString(c.getColumnIndex(KEY_BOOKMARK_EVENT))));
                } while (c.moveToNext());
            }
        }
        return result;
    }

    //return the linked list of all event ids that the participant JOINed
    public LinkedList<Integer> queryJoin(int participant)
    {
        LinkedList<Integer> result= new LinkedList<Integer>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c= db.rawQuery("SELECT * FROM "+RELATIONSHIP_JOIN+" WHERE "+KEY_JOIN_PARTICIPANT+" = '"+participant+"'",null);
        if(c.getCount()<1) // seller didn't create any sell
        {
            c.close();
            return result;
        }
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    result.add(Integer.parseInt(c.getString(c.getColumnIndex(KEY_JOIN_EVENT))));
                } while (c.moveToNext());
            }
        }
        return result;
    }

    //return the linked list of all event ids that the participant JOINed
    public LinkedList<Integer> queryOrganize(int organizer)
    {
        LinkedList<Integer> result= new LinkedList<Integer>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c= db.rawQuery("SELECT * FROM "+RELATIONSHIP_ORGANIZE+" WHERE "+KEY_ORGANIZER_ORGANIZER+" = '"+organizer+"'",null);
        if(c.getCount()<1) // seller didn't create any sell
        {
            c.close();
            return result;
        }
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    result.add(Integer.parseInt(c.getString(c.getColumnIndex(KEY_ORGANIZER_EVENT))));
                } while (c.moveToNext());
            }
        }
        return result;
    }

    //return the linked list of all event ids that the participant JOINed
    public LinkedList<Integer> queryPermit(int event)
    {
        LinkedList<Integer> result= new LinkedList<Integer>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c= db.rawQuery("SELECT * FROM "+RELATIONSHIP_JOIN+" WHERE "+KEY_JOIN_EVENT+" = '"+event+"'",null);
        if(c.getCount()<1) // seller didn't create any sell
        {
            c.close();
            return result;
        }
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    result.add(Integer.parseInt(c.getString(c.getColumnIndex(KEY_JOIN_PARTICIPANT))));
                } while (c.moveToNext());
            }
        }
        return result;
    }

    //Check organizer
    public boolean queryOrganize(int organizer, int event)
    {
        LinkedList<Integer> result= new LinkedList<Integer>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c= db.rawQuery("SELECT * FROM "+RELATIONSHIP_ORGANIZE+" WHERE "+KEY_ORGANIZER_ORGANIZER+" = '"+organizer+"'" + " AND "+ KEY_ORGANIZER_EVENT+" = '"+event+"'",null);
        if(c.getCount()<1) // seller didn't create any sell
        {
            c.close();
            return false;
        }
        return true;
    }

    //Check bookmark
    public boolean queryBookmark(int participant, int event)
    {
        LinkedList<Integer> result= new LinkedList<Integer>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c= db.rawQuery("SELECT * FROM "+RELATIONSHIP_BOOKMARK+" WHERE "+KEY_BOOKMARK_PARTICIPANT+" = '"+participant+"'" + " AND "+ KEY_BOOKMARK_EVENT+" = '"+event+"'",null);
        if(c.getCount()<1) // seller didn't create any sell
        {
            c.close();
            return false;
        }
        return true;
    }

    //Check join
    public boolean queryJoin(int participant, int event)
    {
        LinkedList<Integer> result= new LinkedList<Integer>();
        SQLiteDatabase db = getWritableDatabase();
        String whereClause= KEY_JOIN_EVENT+" = ?"+ " AND "+ KEY_JOIN_PARTICIPANT+" = ?";
        String[] whereArgs = new String[] {Integer.toString(event),Integer.toString(participant)};
        Cursor c= db.rawQuery("SELECT * FROM "+RELATIONSHIP_JOIN+" WHERE "+KEY_JOIN_PARTICIPANT+" = '"+participant+"'" + " AND "+ KEY_JOIN_EVENT+" = '"+event+"'",null);
        if(c.getCount()<1) // seller didn't create any sell
        {
            c.close();
            return false;
        }
        return true;
    }

    //Check permit
    public boolean queryPermit(int participant, int event)
    {
        LinkedList<Integer> result= new LinkedList<Integer>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c= db.rawQuery("SELECT * FROM "+RELATIONSHIP_PERMIT+" WHERE "+KEY_PERMIT_PARTICIPANT+" = '"+participant+"'" + " AND "+ KEY_PERMIT_EVENT+" = '"+event+"'",null);
        if(c.getCount()<1) // seller didn't create any sell
        {
            c.close();
            return false;
        }
        return true;
    }

    //return the linked list of all item ids that the seller created
    public LinkedList<Integer> querySell(int sellerID)
    {
        LinkedList<Integer> result= new LinkedList<Integer>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c= db.rawQuery("SELECT * FROM "+RELATIONSHIP_SELL+" WHERE "+KEY_SELL_SELLER+" = '"+sellerID+"'",null);
        if(c.getCount()<1) // seller didn't create any sell
        {
            c.close();
            return result;
        }
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    result.add(Integer.parseInt(c.getString(c.getColumnIndex(KEY_SELL_ID))));
                } while (c.moveToNext());
            }
        }
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

    public int deleteItem(int ItemID){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause= KEY_ITEM_ID+" = ?";
        String[] whereArgs = new String[] {Integer.toString(ItemID)};
        int rows = db.delete(TABLE_ITEM, whereClause, whereArgs);
        return rows;
    }

    public int deleteEvent(int eventID){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause= KEY_EVENT_ID+" = ?";
        String[] whereArgs = new String[] {Integer.toString(eventID)};
        int rows = db.delete(TABLE_EVENT, whereClause, whereArgs);
        return rows;
    }

    public int deleteReservation(int reservationID){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause= KEY_RESERVE_ID+" = ?";
        String[] whereArgs = new String[] {Integer.toString(reservationID)};
        int rows = db.delete(RELATIONSHIP_RESERVE, whereClause, whereArgs);
        return rows;
    }

    public int deleteBlock(int buyer, int seller){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause= KEY_BLOCK_BUYER+" = ?"+ " AND "+ KEY_BLOCK_SELLER+" = ?";
        String[] whereArgs = new String[] {Integer.toString(buyer),Integer.toString(seller)};
        int rows = db.delete(RELATIONSHIP_BLOCK, whereClause, whereArgs);
        return rows;
    }

    public int deleteJoin(int event, int participant){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause= KEY_JOIN_EVENT+" = ?"+ " AND "+ KEY_JOIN_PARTICIPANT+" = ?";
        String[] whereArgs = new String[] {Integer.toString(event),Integer.toString(participant)};
        int rows = db.delete(RELATIONSHIP_JOIN, whereClause, whereArgs);
        return rows;
    }

    public int deleteBookmark(int event, int participant){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause= KEY_BOOKMARK_EVENT+" = ?"+ " AND "+ KEY_BOOKMARK_PARTICIPANT+" = ?";
        String[] whereArgs = new String[] {Integer.toString(event),Integer.toString(participant)};
        int rows = db.delete(RELATIONSHIP_BOOKMARK, whereClause, whereArgs);
        return rows;
    }

    public int deletePermission(int event, int participant){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause= KEY_PERMIT_EVENT+" = ?"+ " AND "+ KEY_PERMIT_PARTICIPANT+" = ?";
        String[] whereArgs = new String[] {Integer.toString(event),Integer.toString(participant)};
        int rows = db.delete(RELATIONSHIP_PERMIT, whereClause, whereArgs);
        return rows;
    }

    public int deleteWatchlist(int item, int buyer){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause= KEY_WATCHLIST_BUYER+" = ?"+ " AND "+ KEY_WATCHLIST_ID+" = ?";
        String[] whereArgs = new String[] {Integer.toString(buyer),Integer.toString(item)};
        int rows = db.delete(RELATIONSHIP_PERMIT, whereClause, whereArgs);
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