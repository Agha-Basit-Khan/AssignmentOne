package com.example.assignmentone_pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteItem extends AppCompatActivity {

    ListView list1;
    Button b1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter<itemModel> arrayAdapter;
    final ArrayList<itemModel> catee = new ArrayList<itemModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);

        b1=findViewById(R.id.btn1);
        show();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DeleteItem.this,Item.class);
                startActivity(i);
            }
        });
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                itemModel ca = catee.get(position);
                String id1 = ca.id;
                delete(id1);
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
    public void delete(String id1){
        try {
            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);

            String sql = "DELETE FROM itemTable1 where id=?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,id1);
            statement.execute();
            Toast.makeText(this,"Item Deleted",Toast.LENGTH_LONG);
            Intent i = new Intent(getApplicationContext(),Item.class);
            startActivity(i);
        }
        catch (Exception ex){

            Toast.makeText(this,"fail Deleted",Toast.LENGTH_LONG);
        }
    }
}