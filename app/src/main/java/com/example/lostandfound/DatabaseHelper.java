package com.example.lostandfound;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "item_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_ITEM_TABLE = "CREATE TABLE ITEMS (ITEMID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PHONE TEXT, DESCRIPTION TEXT, DATE TEXT, LOCATION TEXT)";
        sqLiteDatabase.execSQL(CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_ITEM_TABLE = "DROP TABLE IF EXISTS ITEMS";
        sqLiteDatabase.execSQL(DROP_ITEM_TABLE);

        onCreate(sqLiteDatabase);
    }

    public long insertItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", item.getName());
        contentValues.put("PHONE", item.getPhone());
        contentValues.put("DESCRIPTION", item.getDescription());
        contentValues.put("DATE", item.getDate());
        contentValues.put("LOCATION", item.getLocation());
        long row = db.insert("ITEMS", null, contentValues);
        db.close();
        return row;
    }

    public boolean fetchItem(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("USERS", new String[]{"USERID"}, "USERNAME=? AND PASSWORD=?", new String[]{username, password}, null, null, null);
        int num_rows = cursor.getCount();
        db.close();
        if (num_rows > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<User> fetchAllUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM USERS";
        Cursor cursor = db.rawQuery(sql, null);

        List<User> userList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                User user = new User(cursor.getString(1), cursor.getString(2));
                userList.add(user);
                cursor.moveToNext();
            } while(!cursor.isAfterLast());
        }
        return userList;
    }

    public int updatePassword(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("PASSWORD", user.getPassword());

        return db.update("USERS", contentValues, "USERNAME=?", new String[]{user.getUsername()});
    }
}
