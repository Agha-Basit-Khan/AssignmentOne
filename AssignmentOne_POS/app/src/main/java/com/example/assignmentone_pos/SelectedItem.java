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

public class SelectedItem extends AppCompatActivity {

    Button b1,b2;
    ListView list1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter<itemModel> arrayAdapter;
    final ArrayList<itemModel> catee = new ArrayList<itemModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_item);

        b1=findViewById(R.id.btn1);
        b2=findViewById(R.id.btn2);
        show();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SelectedItem.this,Category.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SelectedItem.this,Invoice.class);
                startActivity(i);
            }
        });

    }
    public void show() {
        list1 = findViewById(R.id.list1);
        SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE, null);

        final Cursor c = db.rawQuery("select * from categoryTable1", null);
        int id = c.getColumnIndex("id");
        int name = c.getColumnIndex("name");
        int amount = c.getColumnIndex("amount");

        titles.clear();
        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, titles);
        list1.setAdapter(arrayAdapter);
        if (c.moveToFirst()) {
            do {
                itemModel ca = new itemModel();
                ca.id = c.getString(id);
                ca.name = c.getString(name);
                ca.amount = c.getString(amount);
                catee.add(ca);
                String str = "You Bought  " + c.getString(name) + " In Rupees  " + c.getString(amount);
                titles.add(str);
            } while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            list1.invalidateViews();
        }
    }
}