package sies.Aptitude;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rahul on 7/27/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "aptitude.db";
    private static final String TABLE_NAME = "userdetails";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_PHONE = "phone";

    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table userdetails (id integer primary key not null ," +
            "name text not null, username text not null, email text not null, password text not null, phone text not null)";
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null , DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public void insertContact(contact c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String q = "select * from userdetails";
        Cursor cur = db.rawQuery(q , null);
        int count = cur.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_USERNAME, c.getUsername());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_PASSWORD, c.getPassword());
        values.put(COLUMN_PHONE, c.getPhone());

        db.insert(TABLE_NAME , null , values);
        db.close();
    }
    public String searchPass(String user){
        db = this.getReadableDatabase();
        String query = "select username, password from "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b = null;

        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);
                if(a.equals(user)){
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return b;
    }
    public int duplicate(String user){
        db = this.getReadableDatabase();
        String query = "select username, password from "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b = null;
        int count = 0;
        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);
                if(a.equals(user)){

                    count++;
                }
            }
            while(cursor.moveToNext());
        }
        return count;
    }
}
