package com.justin.mobilefinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnLongClickListener {

    private MyDBHelper myDBHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Intent intentAdd;
    private Intent intentUpdate;
    private ListView listView;
    public String selectedId;
    public int selectedPosition;
    private ArrayList<String> listIds;
    private ArrayList<String> listPropertyTypes;
    private ArrayList<String> listYears;
    private ArrayList<String> listLandSizes;
    private ArrayList<String> listFeatures;
    private ArrayList<String> listPropertySizes;
    private ArrayList<String> listPrices;
    private ArrayList<String> listPostalCodes;
    private ArrayList<String> listProvinces;
    private ArrayList<String> listStreets;
    private ArrayList<String> listCities;

    private String [] arrIds;
    private String [] arrProps;
    private String [] arrYears;
    private String [] arrLandSizes;
    private String [] arrFeatures;
    private String [] arrPropertySizes;
    private String [] arrPrices;
    private String [] arrPostalCodes;
    private String [] arrProvinces;
    private String [] arrStreets;
    private String [] arrCities;
    private LinearLayout linlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDBHelper = new MyDBHelper(MainActivity.this);
        sqLiteDatabase = myDBHelper.getWritableDatabase();

        intentAdd= new Intent(MainActivity.this,ListingView.class);
        intentUpdate = new Intent(MainActivity.this,ListingView.class);
        listView = findViewById(R.id.listViewID);

        linlayout = (LinearLayout) findViewById(R.id.lin);
        listIds = new ArrayList<>();
        listPropertyTypes = new ArrayList<>();
        listYears = new ArrayList<>();
        listLandSizes = new ArrayList<>();
        listFeatures = new ArrayList<>();
        listPropertySizes = new ArrayList<>();
        listPrices = new ArrayList<>();
        listPostalCodes = new ArrayList<>();
        listProvinces = new ArrayList<>();
        listStreets = new ArrayList<>();
        listCities = new ArrayList<>();

        getAll();
        arrIds = listIds.toArray(new String[listIds.size()]);
        arrProps = listPropertyTypes.toArray(new String[listPropertyTypes.size()]);
        arrYears = listYears.toArray(new String[listYears.size()]);
        arrLandSizes = listLandSizes.toArray(new String[listLandSizes.size()]);
        arrFeatures = listFeatures.toArray(new String[listFeatures.size()]);
        arrPropertySizes = listPropertySizes.toArray(new String[listPropertySizes.size()]);
        arrPrices = listPrices.toArray(new String[listPrices.size()]);
        arrPostalCodes = listPostalCodes.toArray(new String[listPostalCodes.size()]);
        arrProvinces = listProvinces.toArray(new String[listProvinces.size()]);
        arrStreets = listStreets.toArray(new String[listStreets.size()]);
        arrCities = listCities.toArray(new String[listCities.size()]);

        if(arrIds != null) {
            CustomAdapter customAdapater
                    = new CustomAdapter(MainActivity.this, arrIds, arrProps, arrYears);

            listView.setAdapter(customAdapater);
        }

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundResource(R.drawable.ic_launcher_background);
                        break;

                    case MotionEvent.ACTION_UP:
                        v.setBackgroundResource(R.drawable.ic_launcher_foreground);
                        break;
                }

                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //We are interested about the position/index of the item, here
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedId = arrIds[position];
                selectedPosition = position;
                Toast.makeText(MainActivity.this, selectedId+" "+position,Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void getAll(){
        Cursor resultSet = myDBHelper.displayAllData();
        if(resultSet.getCount() == 0){
            showToast("Display error: No row selected");

        } else {
            while(resultSet.moveToNext()){
                listIds.add(resultSet.getString(0));
                listPropertyTypes.add(resultSet.getString(1));
                listYears.add(resultSet.getString(2));
                listLandSizes.add(resultSet.getString(3));
                listFeatures.add(resultSet.getString(4));
                listPropertySizes.add(resultSet.getString(5));
                listPrices.add(resultSet.getString(6));
                listPostalCodes.add(resultSet.getString(7));
                listProvinces.add(resultSet.getString(8));
                listStreets.add(resultSet.getString(9));
                listCities.add(resultSet.getString(10));
            }
        }
    }

    public void openAddListing(View v){
        if(v.getId()==R.id.btnAddId){
            startActivity(intentAdd);

        }
    }

    public void deleteListing(View v){
        if(v.getId()==R.id.btnDelete){
            boolean isDeleted=myDBHelper.deleteData(selectedId);
            if(isDeleted){
                showToast("Update successful");
            }
            else{
                showToast("Update unsuccessful");
            }

        }
    }

    public void updateListing(View v){
        if(v.getId()==R.id.btnUpdate){
            intentUpdate.putExtra("id", selectedId);
            intentUpdate.putExtra("propertyType", arrProps[selectedPosition]);
            intentUpdate.putExtra("year", arrYears[selectedPosition]);
            intentUpdate.putExtra("landSize", arrLandSizes[selectedPosition]);
            intentUpdate.putExtra("features", arrFeatures[selectedPosition]);
            intentUpdate.putExtra("propertySize", arrPropertySizes[selectedPosition]);
            intentUpdate.putExtra("price", arrPrices[selectedPosition]);
            intentUpdate.putExtra("postalCode", arrPostalCodes[selectedPosition]);
            intentUpdate.putExtra("province", arrProvinces[selectedPosition]);
            intentUpdate.putExtra("street", arrStreets[selectedPosition]);
            intentUpdate.putExtra("city", arrCities[selectedPosition]);
            startActivity(intentUpdate);
        }
    }

    private void showToast(String message){
        Toast toast;
        toast = Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}