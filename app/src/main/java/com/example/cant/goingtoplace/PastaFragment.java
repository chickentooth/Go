package com.example.cant.goingtoplace;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class PastaFragment extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle sis){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                inflater.getContext(),
                R.layout.pasta_list,R.id.pasta_item,
                getResources().getStringArray(R.array.pasta));
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, sis);
    }
}