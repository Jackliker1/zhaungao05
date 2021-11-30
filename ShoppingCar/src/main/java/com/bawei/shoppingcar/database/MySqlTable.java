package com.bawei.shoppingcar.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySqlTable extends SQLiteOpenHelper {

    public MySqlTable(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table orderTable(id integer primary key autoincrement,ordernumber varchar(200),totalvalue float,userid int,createtime datetime,status integer)");
        db.execSQL("create table orderTable2(id integer primary key autoincrement,ordernumber varchar(200),goodsid integer,goodsimgurl varchar(200),goodsprice float,goodscount integer,goodstotalvalue float)");
        db.execSQL("create table address(id integer primary key autoincrement,name varchar(10),phone varchar(20),province varchar(200),home varchar(200))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
