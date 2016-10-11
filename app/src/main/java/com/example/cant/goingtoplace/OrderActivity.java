package com.example.cant.goingtoplace;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends Activity {
    private int quantity = 0;
    private TextView quantity_display;
    private TextView price_display;
    private TextView response_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        quantity_display = (TextView)findViewById(R.id.quantity);
        price_display = (TextView)findViewById(R.id.price);
        response_display = (TextView)findViewById(R.id.response_message);
        getActionBar().setDisplayShowHomeEnabled(true);
    }
    // khi nguoi dung bam nut +
    public void onClickPlus(View view){
        quantity++;
        changeText(quantity);

    }

    // khi bam nguoi dung bam nut -
    public void onClickSub(View view){
        if(quantity-1 >=0) {
            quantity--;
        }
        changeText(quantity);
    }
    //khi nguoi dung bam nut order
    public void onClickSubmit(View view){
        String response_displayText = "Your order has been sent. Thank you!";
        response_display.setText(response_displayText);
    }

    //thay doi du lieu hien thi thong qua so luong
    private void changeText(int quantity){
        String quantityText = "quantity: " + quantity;
        String priceText = "Price: $" + 7*quantity;
        quantity_display.setText(quantityText);
        price_display.setText(priceText);
    }

}