package com.example.assignmentone_pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddItem extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        ed1=findViewById(R.id.name);
        ed2=findViewById(R.id.description);
        ed3=findViewById(R.id.amount);
        ed4=findViewById(R.id.quantity);
        b1=findViewById(R.id.btn1);
        b2=findViewById(R.id.btn2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddItem.this,Item.class);
                startActivity(i);
            }
        });
    }
    public void add(){
        try {
            String name = ed1.getText().toString();
            String description = ed2.getText().toString();
            String amount = ed3.getText().toString();
            String quantity = ed4.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS itemTable1(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, amount INTEGER, quantity INTEGER)");

            String sql = "INSERT INTO itemTable1(name,description,amount,quantity) VALUES(?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,name);
            statement.bindString(2,description);
            statement.bindString(3,amount);
            statement.bindString(4,quantity);
            Log.d("Add",amount);
            Toast.makeText(getApplicationContext(),"Item Added",Toast.LENGTH_LONG);
            statement.execute();
            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed1.requestFocus();
        }
        catch (Exception ex){

            Toast.makeText(this,"Item Fail To Add",Toast.LENGTH_LONG);
        }
    }
}