package com.example.assignmentone_pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerLogin extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        ed1=findViewById(R.id.name);
        ed2=findViewById(R.id.phone);
        ed3=findViewById(R.id.address);
        ed4=findViewById(R.id.email);
        b1=findViewById(R.id.btn1);
        b2=findViewById(R.id.btn2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
                Intent i = new Intent(CustomerLogin.this,Category.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CustomerLogin.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void add(){
        try {
            String name = ed1.getText().toString();
            String phone = ed2.getText().toString();
            String address = ed3.getText().toString();
            String email = ed4.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS customerTable2(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone TEXT, address TEXT, email TEXT)");

            String sql = "INSERT INTO customerTable2(name,phone,address,email) VALUES(?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,name);
            statement.bindString(2,phone);
            statement.bindString(3,address);
            statement.bindString(4,email);
            statement.execute();
            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
        }
        catch (Exception ex){

            Toast.makeText(this,"Customer Fail To Login",Toast.LENGTH_LONG);
        }
    }
}