package com.example.bankapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(8320012687,'Saumil',9500.00,'saumil@gmail.com','1212121212121212','ABC12345678')");
        db.execSQL("insert into user_table values(7574737271,'Jinang',5000.00,'Jinang@gmail.com','2323232323232323','BCD12345678')");
        db.execSQL("insert into user_table values(7079787776,'Krutarth',1000.00,'Krutarth@gmail.com','3434343434343434','CDE12345678')");
        db.execSQL("insert into user_table values(8089888786,'Sunny',5500.00,'Sunny3@gmail.com','4545454545454545','DEF12345678')");
        db.execSQL("insert into user_table values(8887868584,'China',6500.00,'China@gmail.com','5656565656565656','EFG12345678')");
        db.execSQL("insert into user_table values(8786858483,'Dharmik',4000.00,'Dharmik@gmail.com','6767676767676767','GHI12345678')");
        db.execSQL("insert into user_table values(8584838281,'Riddhesh',9000.00,'Riddhesh@gmail.com','7878787878787878','HIJ12345678')");
        db.execSQL("insert into user_table values(8685848381,'Sahil',8500.00,'Sahil@gmail.com','8989898989898989','IJK12345678')");
        db.execSQL("insert into user_table values(9493929596,'Manan',4900.00,'Manan@gmail.com','9090909090909090','JKL12345678')");
        db.execSQL("insert into user_table values(9999999999,'Alpesh',2900.00,'Alpesh@gmail.com','222333444555666','KLM12345678')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
