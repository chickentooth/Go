package com.example.cant.goingtoplace;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by cant on 11/10/2016.
 */

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;

    protected
    CustomListAdapter(Activity context, String[] itemname, Integer[] imgid) {
        super(context, R.layout.home_list, itemname);
        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        //tham chieu den hang chua  1 hinh anh + 1 text
        View rowView=inflater.inflate(R.layout.home_list, null,true);
        TextView title = (TextView) rowView.findViewById(R.id.list_item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        title.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);
        return rowView;
    };
}