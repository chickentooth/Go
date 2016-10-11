package com.example.cant.goingtoplace;


import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
/**
 * A simple {@link Fragment} subclass.
 */
public class PizzaFragment extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle sis){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),
                R.layout.pizza_list,R.id.pizza_item, getResources().getStringArray(R.array.pizzas));
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, sis);
    }
}
