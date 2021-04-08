package com.example.assignmentone_pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class updateMenu extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    TextView ed5;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_menu);

        ed1 = findViewById(R.id.name1);
        ed2 = findViewById(R.id.description1);
        ed3 = findViewById(R.id.amount1);
        ed4 = findViewById(R.id.quantity1);
        ed5 = findViewById(R.id.idd1);
        b1 = findViewById(R.id.btn11);
        b2 = findViewById(R.id.btn21);

        Intent i = getIntent();
        String id = i.getStringExtra("id");
        String name = i.getStringExtra("name");
        String description = i.getStringExtra("description");
        String amount = i.getStringExtra("amount");
        String quantity = i.getStringExtra("quantity");
        ed1.setText(name);
        ed2.setText(description);
        ed3.setText(amount);
        ed4.setText(quantity);
        ed5.setText(id);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(id);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(updateMenu.this,UpdateItem.class);
                startActivity(i);
            }
        });
    }
    public void update(String id1){
        try {

            String name1 = ed1.getText().toString();
            String description1 = ed2.getText().toString();
            String amount1 = ed3.getText().toString();
            String quantity1 = ed4.getText().toString();
            String id2 = ed5.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);

            String sql = "UPDATE itemTable1 set name=?, description=?, amount=?, quantity=? where id=?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,name1);
            statement.bindString(2,description1);
            statement.bindString(3,amount1);
            statement.bindString(4,quantity1);
            statement.bindString(5, id2);
            statement.execute();
            Toast.makeText(this,"Item Updated ",Toast.LENGTH_LONG);
            Intent i = new Intent(getApplicationContext(),Item.class);
            startActivity(i);
        }
        catch (Exception ex){

            Toast.makeText(this,"fail Updated",Toast.LENGTH_LONG);
        }
    }
}