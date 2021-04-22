package com.Mitrakarte_ME.meappmintrakarte.Util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.Mitrakarte_ME.meappmintrakarte.Model.AG;

public class Dao {
    SQLiteDatabase db;

    public Dao() {

        if (Dbhelper.getInstance() != null) {
            db = Dbhelper.getInstance().getWritableDatabase();
            db.execSQL("CREATE TABLE IF NOT EXISTS UsrDetails(mobileNum VARCHAR(20), email VARCHAR(20), id INT, token VARCHAR(50),name VARCHAR(50))");

        } else {
            throw new RuntimeException("DBHelper not initialized");
        }
    }

    public void addUserDetails() {
        AG ag_inst = AG.getInstance();
        db.delete("UsrDetails", null, null);
        db.execSQL("INSERT INTO UsrDetails VALUES('" + ag_inst.getUser().getMobile() + "','" + ag_inst.getUser().getEmail() + "','" + ag_inst.getUser().getUserId() + "','" + ag_inst.getUser().getUserToken() + "','" + ag_inst.getUser().getName() + "')");
        //System.out.println("jyo insert db"+om_inst.getUser().getMobile()+"','"+om_inst.getUser().getEmail()+"','"+om_inst.getUser().getUserId()+"','"+om_inst.getUser().getUserToken()+"','"+om_inst.getUser().getName()+"')");

    }

    public void getUserDetails() {
        Cursor cursor = db.rawQuery("Select * from UsrDetails", new String[]{});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            AG dss_inst = AG.getInstance();
            System.out.println("jyo get called mob " + dss_inst.getUser().getMobile());
            dss_inst.getUser().setMobile(cursor.getString(0));
            dss_inst.getUser().setEmail(cursor.getString(1));
            dss_inst.getUser().setUserId(cursor.getInt(2));
            dss_inst.getUser().setUserToken(cursor.getString(3));
            dss_inst.getUser().setName(cursor.getString(4));
            //  System.out.println("jyo get called toke "+dss_inst.getUser().getUserToken());
        } else {
            AG dss_inst = AG.getInstance();
            dss_inst.getUser().setUserId(-1);
              /* dss_inst.getUser().setMobile(cursor.getString(0));
               dss_inst.getUser().setUserToken(cursor.getString(3));*/
        }
    }

    public void deleteUserDetails() {
        db.delete("UsrDetails", null, null);
    }
}
