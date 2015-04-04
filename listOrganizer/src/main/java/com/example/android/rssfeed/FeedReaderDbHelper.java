package com.example.android.rssfeed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * This class handles the database operation for the application. It handles CRUD operations for the application
 * 
 * @author Eric
 *
 */
public class FeedReaderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";
	private static final String SQL_CREATE_ENTRIES = null;
	private static final String SQL_DELETE_ENTRIES = null;

 
    // Contacts table name
    private static final String TABLE_CONTACTS = "listitems";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_LIST_ITEM = "list_item";
    private static final String KEY_DATE = "date";
    private static final String KEY_POSITION="position";
    
    /**
     * Overloaded Constructed of FeedReaderDbHelper Class. Initialized context and database.
     * 
     * 
     * @param context Context of the application
     */
    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    /* (non-Javadoc)
     * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
     * 
     * Create the Database based on String containing Create table statement
     */
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY, " 
        		+ KEY_TITLE + " TEXT,"
                + KEY_LIST_ITEM + " TEXT,"
				+ KEY_DATE + " TEXT," 
                + KEY_POSITION + " INTEGER)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    
    
    /* (non-Javadoc)
     * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
     * Caches for Online data. Delete data from database.
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    
    /* (non-Javadoc)
     * @see android.database.sqlite.SQLiteOpenHelper#onDowngrade(android.database.sqlite.SQLiteDatabase, int, int)
     * Caches for Online data. Delete data from database.
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    // Adding new contact
    /**
     * Adds List Item object to database. Passed the title and listitem from ListItem Object.
     * 
     * @param listitem List Item Object
     */
    public void addListitem(ListItem listitem) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, listitem.getTitle()); // Contact Name
        values.put(KEY_LIST_ITEM, listitem.getListItem()); // Contact Phone
   		values.put(KEY_DATE, listitem.getDate().toString()); // Contact Phone
   		values.put(KEY_POSITION, listitem.getPosition()); // Contact Phone
   		
		// Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }
    
  
    /**
     * 
     * Getting All List Items from database
     * @return List of List Items from Database
     * 
     */
    public List<ListItem> getAllListItems() {
        List<ListItem> contactList = new ArrayList<ListItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ListItem contact = new ListItem();
                String id = cursor.getString(0);
                String name =  cursor.getString(1);
                String phonenumber = cursor.getString(2);
                
                contact.setPosition(Integer.parseInt(cursor.getString(4)));
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setTitle(cursor.getString(1));
                contact.setListItem(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return contactList;
    }
    
  
    /**
     * 
     * Getting All ListItem By Title passed in parameter. Gets all ListItem from database.
     * 
     * @param spinnertitle spinner title
     * @return ListItem By Title passed in parameter
     */
    public List<ListItem> getAllListItemsByTitle(String spinnertitle) {
        List<ListItem> contactList = new ArrayList<ListItem>();
        // Select All Query
       String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS + " WHERE " + KEY_TITLE +" = ?";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,  new String[] {spinnertitle});
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ListItem contact = new ListItem();
                String id = cursor.getString(0);
                String name =  cursor.getString(1);
                String phonenumber = cursor.getString(2);
                
                contact.setID(Integer.parseInt(cursor.getString(4)));
                contact.setTitle(cursor.getString(1));
                contact.setListItem(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return contactList;
    }

	    /**
     * 
     * Getting All List String Item By Title passed in parameter. Gets all ListItem from database by title.
     * 
     * @param spinnertitle spinner title
     * @return ListItem By Title passed in parameter
     */
    public ArrayList<String> getAllListStringItemsByTitle(String spinnertitle) {
        List<ListItem> contactList = new ArrayList<ListItem>();
        // Select All Query
       String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS + " WHERE " + KEY_TITLE +" = ?";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,  new String[] {spinnertitle});
 		ArrayList<String> list = new ArrayList();
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return list;
    }
	
		    /**
     * 
     * Getting All List String Item By Title passed in parameter. Gets all ListItem from database by title.
     * 
     * @param spinnertitle spinner title
     * @return ListItem By Title passed in parameter
     */
    public ArrayList<String> getAllListStringItemsByDateModified(String spinnertitle) {
        List<ListItem> contactList = new ArrayList<ListItem>();
        // Select All Query
       String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS + " WHERE " + KEY_TITLE +" = ?" + "ORDER BY" + KEY_DATE +"ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,  new String[] {spinnertitle});
 		ArrayList<String> list = new ArrayList();
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return list;
    }
    /**
     * 
     * Updating single listitem on database
     * 
     * @param listitem listitem that will be updated
     * @return  the number of rows updated
     */
    public int updateListItem(ListItem listitem, Date date) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_LIST_ITEM, listitem.getListItem());
 		values.put(KEY_DATE, date.toString());
		
        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_TITLE + " = ? AND " + KEY_POSITION+" = ?",
                new String[] { String.valueOf(listitem.getTitle()), String.valueOf(listitem.getPosition())});
    }
 
    
    
    /**
     * 
     * Updating single listitem on database
     * 
     * @param listitem listitem that will be updated
     * @return  the number of rows updated
     */
    public int updateListItem1(ListItem listitem) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
 		values.put(KEY_POSITION, listitem.getPosition()-1);
 		
        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_TITLE + " = ? AND " + KEY_POSITION+"  = ?",
                new String[] { String.valueOf(listitem.getTitle()), String.valueOf(listitem.getPosition())});
    }
     /**
     * 
     * Updating a spinner title from database
     * 
     * @param title spinner title that will be updated.
     * @return  the number of rows updated
     */
    public int updateSpinnerTitle(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, title);
 
        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_TITLE + " = ?",
                new String[] { title});
    }
    
    /**
     * 
     * Deleting single list item based on title and list item
     * 
     * @param listitem List item containing title and list item
     */
    public void deleteListItem(ListItem listitem) {
        SQLiteDatabase db = this.getWritableDatabase();
      db.delete(TABLE_CONTACTS, KEY_TITLE + " = ?" + " AND "+ KEY_LIST_ITEM + " = ? AND "+ KEY_POSITION + "=?",
              new String[] { String.valueOf(listitem.getTitle()),String.valueOf(listitem.getListItem()),String.valueOf(listitem.getPosition()) });

        db.close();
    }
    
     /**
     * 
     * Deleting single spinner title
     * 
     * @param spinnertitle current spinner title
     */
    public void deleteSpinnerTitle(String spinnertitle) {
        SQLiteDatabase db = this.getWritableDatabase();
      db.delete(TABLE_CONTACTS, KEY_TITLE + " = ?",
              new String[] { spinnertitle });

        db.close();
    }
	
    /**
      * Deleting all list item based on title 
     * 
     * @param listitem List item containing title and list item
     * 
     */
    public void deleteListItemByTitle(ListItem listitem) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_TITLE + " = ?",
                new String[] { String.valueOf(listitem.getTitle()) });
        db.close();
    }

}