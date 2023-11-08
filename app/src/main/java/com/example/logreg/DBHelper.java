package com.example.logreg;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "users.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NEV = "users";
    private static final String COL_ID = "ID";
    private static final String COL_EMAIL = "email";
    private static final String COL_FELHASZNALONEV = "username";
    private static final String COL_JELSZO = "password";

    private static final String COL_TELJESNEV = "fullname";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NEV + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_EMAIL + " TEXT NOT NULL, " +
                COL_FELHASZNALONEV + " TEXT NOT NULL, " +
                COL_JELSZO + " TEXT NOT NULL, " +
                COL_TELJESNEV + " TEXT NOT NULL" +
                ");";
        sqLiteDatabase.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(
                "DROP TABLE IF EXISTS " + TABLE_NEV
        );
        onCreate(sqLiteDatabase);
    }

    public  boolean addToTable(String email, String username, String password, String fullname) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_EMAIL, email);
        values.put(COL_FELHASZNALONEV, username);
        values.put(COL_JELSZO, password);
        values.put(COL_TELJESNEV, fullname);

        long result = database.insert(TABLE_NEV, null, values);
        return result != -1;
    }





    public boolean checkUserByUsername(String username, String password) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NEV, new String[] {COL_ID, COL_EMAIL, COL_FELHASZNALONEV, COL_JELSZO, COL_TELJESNEV},
                COL_FELHASZNALONEV + " = ? AND " + COL_JELSZO + " = ?", new String[] {username, password},
                null, null, null);
        return cursor.getCount() > 0;
    }


    public Cursor getTable() {
        SQLiteDatabase database = this.getReadableDatabase();
        return database.query(TABLE_NEV, new String[] {COL_ID, COL_EMAIL, COL_FELHASZNALONEV, COL_JELSZO, COL_TELJESNEV},
                null, null, null, null, null);
    }

    public Cursor getTableElementById(int id) {
        SQLiteDatabase database = this.getReadableDatabase();
        return database.query(TABLE_NEV, new String[] {COL_ID, COL_EMAIL, COL_FELHASZNALONEV, COL_JELSZO, COL_TELJESNEV},
                COL_ID + " = ?", new String[] {String.valueOf(id)},
                null, null, null);
    }

    public Cursor getTableElementByEmail(String email, String password) {
        SQLiteDatabase database = this.getReadableDatabase();
        return database.query(TABLE_NEV, new String[] {COL_ID, COL_EMAIL, COL_FELHASZNALONEV, COL_JELSZO, COL_TELJESNEV},
                COL_EMAIL + " = ? AND " + COL_JELSZO + " = ?", new String[] {email, password},
                null, null, null);
    }

    public Cursor getTableElementByUsername(String username, String password) {
        SQLiteDatabase database = this.getReadableDatabase();
        return  database.query(TABLE_NEV, new String[] {COL_ID, COL_EMAIL, COL_FELHASZNALONEV, COL_JELSZO, COL_TELJESNEV},
                COL_FELHASZNALONEV + " = ? AND " + COL_JELSZO + " = ?", new String[] {username, password},
                null, null, null);
    }

    public Cursor getTableElementByUsernameOrEmail(String usernameOrEmail, String password) {
        if (isEmail(usernameOrEmail)) {
            return getTableElementByEmail(usernameOrEmail, password);
        }
        return getTableElementByUsername(usernameOrEmail, password);
    }

    private boolean isEmail(String email) {
        Pattern regexPattern = Pattern.compile("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$");
        Matcher matcher = regexPattern.matcher(email);
        return matcher.matches();
    }

    private boolean isFullname(String fullname) {
        return fullname.split(" ").length > 1;
    }
}





