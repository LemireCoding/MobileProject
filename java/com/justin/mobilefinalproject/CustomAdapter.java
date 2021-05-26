package com.justin.mobilefinalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private String[] ids;
    private String [] types;
    private String [] years;
    private LayoutInflater layoutInflater;

    public CustomAdapter(Context context, String[] ids, String[] types,String[] years) {
        this.context = context;
        this.ids = ids;
        this.types = types;
        this.years = years;
    }

    @Override
    public int getCount() {
        return ids.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            layoutInflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.sample_view,parent,false);
        }
        TextView textId= convertView.findViewById(R.id.textViewID);
        TextView textProp = convertView.findViewById(R.id.textViewProp);
        TextView textYear = convertView.findViewById(R.id.textViewYear);

        textId.setText(ids[position]);
        textProp.setText(types[position]);
        textYear.setText(years[position]);

        return convertView;
    }
}
