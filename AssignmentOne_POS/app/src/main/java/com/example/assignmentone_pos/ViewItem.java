package com.example.assignmentone_pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewItem extends AppCompatActivity {

    ListView list1;
    Button b1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter<itemModel> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        b1=findViewById(R.id.btn1);
        show();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewItem.this,Item.class);
                startActivity(i);
            }
        });
    }

    public void show(){
        list1=findViewById(R.id.list1);
        SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);

        final Cursor c = db.rawQuery("select * from itemTable1",null);
        int id = c.getColumnIndex("id");
        int name = c.getColumnIndex("name");
        int description = c.getColumnIndex("description");
        int amount = c.getColumnIndex("amount");
        int quantity = c.getColumnIndex("quantity");

        titles.clear();
        arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,titles);
        list1.setAdapter(arrayAdapter);

        final ArrayList<itemModel> catee = new ArrayList<itemModel>();
        if(c.moveToFirst()){
            do {
                itemModel ca = new itemModel();
                ca.id = c.getString(id);
                ca.name = c.getString(name);
                ca.description = c.getString(description);
                ca.amount = c.getString(amount);
                ca.quantity = c.getString(quantity);
                catee.add(ca);
                String str = "Name : " + c.getString(name) + " Amount : " + c.getString(amount) + " Quantity : " + c.getString(quantity) ;
                titles.add(str);
            }while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            list1.invalidateViews();
        }
    }
}