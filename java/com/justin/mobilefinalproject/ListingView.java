package com.justin.mobilefinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ListingView extends AppCompatActivity {
    private MyDBHelper myDBHelper;
    private SQLiteDatabase sqLiteDatabase;
    private EditText editTextProperty;
    private EditText editTextYear;
    private EditText editTextLandSize;
    private EditText editTextFeatures;
    private EditText editTextPropertySize;
    private EditText editTextListingPrice;
    private EditText editTextPostalCode;
    private EditText editTextProvince;
    private EditText editTextStreet;
    private EditText editTextCity;
    private int Id;
    private String prop;
    private String year;
    private String landSize;
    private String feature;
    private String propSize;
    private String price;
    private String postalCode;
    private String province;
    private String street;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_view);

        myDBHelper = new MyDBHelper(ListingView.this);
        sqLiteDatabase = myDBHelper.getWritableDatabase();

        editTextProperty = findViewById(R.id.edtPropertyType);
        editTextYear = findViewById(R.id.edtListingDate);
        editTextLandSize = findViewById(R.id.edtLandSize);
        editTextFeatures = findViewById(R.id.edtFeatures);
        editTextPropertySize = findViewById(R.id.edtPropertySize);
        editTextListingPrice = findViewById(R.id.edtPrice);
        editTextPostalCode = findViewById(R.id.edtPostal);
        editTextProvince = findViewById(R.id.edtProvince);
        editTextStreet = findViewById(R.id.edtStreet);
        editTextCity = findViewById(R.id.edtCity);

        Bundle bundle;
        bundle=getIntent().getExtras();
        if (bundle!=null){
            Id = Integer.parseInt(bundle.getString("id"));
            prop = bundle.getString("propertyType");
            year = bundle.getString("year");
            landSize = bundle.getString("landSize");
            feature = bundle.getString("features");
            propSize = bundle.getString("propertySize");
            price = bundle.getString("price");
            postalCode = bundle.getString("postalCode");
            province = bundle.getString("province");
            street = bundle.getString("street");
            city = bundle.getString("city");

            editTextProperty.setText(prop);
            editTextYear.setText(year);
            editTextLandSize.setText(landSize);
            editTextFeatures.setText(feature);
            editTextPropertySize.setText(propSize);
            editTextListingPrice.setText(price);
            editTextPostalCode.setText(postalCode);
            editTextProvince.setText(province);
            editTextStreet.setText(street);
            editTextCity.setText(city);
        }

    }



    private void showToast(String message){
        Toast toast;
        toast = Toast.makeText(ListingView.this,message,Toast.LENGTH_LONG);
        toast.show();
    }


    public void AddListing(View v) {

        Bundle bundle;
        bundle=getIntent().getExtras();
        if (bundle!=null){
            String sProperty = editTextProperty.getText().toString();
            String sYear = editTextYear.getText().toString();
            String sLand = editTextLandSize.getText().toString();
            String sFeatures =editTextFeatures.getText().toString();
            String sPropertySize = editTextPropertySize.getText().toString();
            String sPrice =editTextListingPrice.getText().toString();
            String sPO = editTextPostalCode.getText().toString();
            String sProvince = editTextProvince.getText().toString();
            String sStreet = editTextStreet.getText().toString();
            String sCity =editTextCity.getText().toString() ;

            boolean isUpdated = myDBHelper.updateData(Integer.toString(Id),sProperty,sYear,sLand,sFeatures,sPropertySize,sPrice,sPO,sProvince,sStreet,sCity);
            if(isUpdated){
                showToast("Update successful");
            }
            else{
                showToast("Update successful");
            }

        } else{
        String sProperty = editTextProperty.getText().toString();
        String sYear = editTextYear.getText().toString();
        String sLand = editTextLandSize.getText().toString();
        String sFeatures =editTextFeatures.getText().toString();
        String sPropertySize = editTextPropertySize.getText().toString();
        String sPrice =editTextListingPrice.getText().toString();
        String sPO = editTextPostalCode.getText().toString();
        String sProvince = editTextProvince.getText().toString();
        String sStreet = editTextStreet.getText().toString();
        String sCity =editTextCity.getText().toString() ;

            long rowID = myDBHelper.insertTable(sProperty,sYear,sLand,sFeatures,sPropertySize,sPrice,sPO,sProvince,sStreet,sCity);
            if(rowID == -1){
                showToast("Row insertion failed");
            } else {
                showToast("Row:"+rowID +" insertion worked");
            }

        }
    }

        public void goBack(View v){
                this.finish();
        }
    }
