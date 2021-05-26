package com.justin.mobilefinalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.jar.Attributes;

public class MyDBHelper  extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabase;

    private static final String DATA_BASE = "Listing.db";
    private static final String TABLE_NAME = "listing_details";

    private static final String ID = "_id";
    private static final String PROPERTY = "Property";
    private static final String YEAR = "Year";
    private static final String LAND_SIZE = "Land_Size";
    private static final String FEATURES = "Features";
    private static final String PROPERTY_SIZE = "Property_Size";
    private static final String PRICE = "Price";
    private static final String POSTAL_CODE = "Postal_Code";
    private static final String PROVINCE = "Province";
    private static final String STREET = "Street";
    private static final String CITY = "City";

    private static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME +"(" +
            ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            PROPERTY+" VARCHAR(255),"+
            YEAR+" VARCHAR(255),"+
            LAND_SIZE+" VARCHAR(255),"+
            FEATURES+" VARCHAR(255),"+
            PROPERTY_SIZE+" VARCHAR(255),"+
            PRICE+" VARCHAR(255),"+
            POSTAL_CODE+" VARCHAR(255),"+
            PROVINCE+" VARCHAR(255),"+
            STREET +" VARCHAR(255),"+
            CITY +" VARCHAR(255));";

    private static final String CREATE_TABLE_UPD = "CREATE TABLE "+ TABLE_NAME +"(" +
            ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            PROPERTY+" VARCHAR(255),"+
            YEAR+" VARCHAR(255),"+
            LAND_SIZE+" VARCHAR(255),"+
            FEATURES+" VARCHAR(255),"+
            PROPERTY_SIZE+" VARCHAR(255),"+
            PRICE+" VARCHAR(255),"+
            POSTAL_CODE+" VARCHAR(255),"+
            PROVINCE+" VARCHAR(255),"+
            STREET +" VARCHAR(255),"+
            CITY +" VARCHAR(255));";

    private static final String SELECT_ALL = "SELECT * FROM "+ TABLE_NAME;

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME ;

    private static final int VERSION_NUMBER = 1;
    private Context context;

    public MyDBHelper(@Nullable Context context) {
        super(context,DATA_BASE,null,VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(CREATE_TABLE);
            showToast("Called onCreate()");
        }catch(Exception e){
            showToast("Exception:"+ e);
        }
        }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        try {
            sqLiteDatabase.execSQL(DROP_TABLE);
            showToast("Called DROP_TABLE");
        }catch(Exception e){
            showToast("Exception:"+ e);
        }

        try {
            sqLiteDatabase.execSQL(CREATE_TABLE_UPD);
            showToast("Called CREATE_TABLE_UPD");
        }catch(Exception e){
            showToast("Exception:"+ e);
        }
    }

    public Cursor displayAllData(){
        sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL,null);
        return cursor;
    }

    public long insertTable(String property, String year,String land_size,String features,String property_size,String price,String postal_code,String province,String street,String city){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Property", property);
        contentValues.put("Year", year);
        contentValues.put("Land_Size", land_size);
        contentValues.put("Features", features);
        contentValues.put("Property_Size", property_size);
        contentValues.put("Price", price);
        contentValues.put("Postal_Code", postal_code);
        contentValues.put("Province", province);
        contentValues.put("Street", street);
        contentValues.put("City", city);

        showToast(contentValues.toString());
        sqLiteDatabase = this.getWritableDatabase();
        long rowID = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rowID;
    }

    public boolean deleteData(String sId){
        sqLiteDatabase= this.getWritableDatabase();

        try{
            int delRowNo=sqLiteDatabase.delete
                    (TABLE_NAME, "_id = ?", new String []{sId});

            if(delRowNo >=0){
                return true;
            }
            else{
                return false;}
        }
        catch(Exception e){
            showToast("Exception: " + e);
            return false;

        }
    }

    public boolean updateData(String sId,String property, String year, String land_size, String features, String property_size, String price, String postal_code, String province, String street, String city){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Property", property);
        contentValues.put("Year", year);
        contentValues.put("Land_Size", land_size);
        contentValues.put("Features", features);
        contentValues.put("Property_Size", property_size);
        contentValues.put("Price", price);
        contentValues.put("Postal_Code", postal_code);
        contentValues.put("Province", province);
        contentValues.put("Street", street);
        contentValues.put("City", city);

        showToast(contentValues.toString());
        sqLiteDatabase=this.getWritableDatabase();
        try{
        int rowNumUpd = sqLiteDatabase.update(TABLE_NAME, contentValues, "_id = ?", new String[]{sId});

        if(rowNumUpd >=0){
            return true;
        }
        else{
            return false;}
    }
	catch(Exception e){
        showToast("Exception: " + e);
        return false;
    }
}

    private void showToast(String message){
        Toast toast;
        toast = Toast.makeText(this.context,message,Toast.LENGTH_LONG);
        toast.show();
    }


}
