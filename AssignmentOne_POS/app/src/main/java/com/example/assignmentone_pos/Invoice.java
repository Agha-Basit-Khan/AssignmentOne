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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Invoice extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5,t6;
    Button b1;
    int num=0;
    final ArrayList<itemModel> catee = new ArrayList<itemModel>();
    final ArrayList<customerModel> catee1 = new ArrayList<customerModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        t1=findViewById(R.id.name);
        t2=findViewById(R.id.phone);
        t3=findViewById(R.id.address);
        t4=findViewById(R.id.email);
        t5=findViewById(R.id.bill);
        t6=findViewById(R.id.count);
        b1=findViewById(R.id.btn1);
        num = show();
        CustomerDetail();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Payment.class);
                i.putExtra("num",String.valueOf(num));
                startActivity(i);
            }
        });

    }
    public void CustomerDetail(){
        String a=null,b=null,e=null,d=null;
        SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);

        final Cursor c = db.rawQuery("select * from customerTable2",null);
        int id = c.getColumnIndex("id");
        int name = c.getColumnIndex("name");
        int phone = c.getColumnIndex("phone");
        int address = c.getColumnIndex("address");
        int email = c.getColumnIndex("email");
        if(c.moveToFirst()){
            do {
                customerModel ca = new customerModel();
                ca.id = c.getString(id);
                ca.name = c.getString(name);
                ca.phone = c.getString(phone);
                ca.address = c.getString(address);
                ca.email = c.getString(email);
                catee1.add(ca);
                a = "Name : " + c.getString(name);
                b = "Phone No. : " + c.getString(phone);
                d = "Address : " + c.getString(address);
                e = "Email : " + c.getString(email);
            }while (c.moveToNext());
        }
        t1.setText(String.valueOf(a));
        t2.setText(String.valueOf(b));
        t3.setText(String.valueOf(d));
        t4.setText(String.valueOf(e));

    }

    public int show(){
        int bill=0,count=0;
        String str1=null,str2=null;
        SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);

        final Cursor c = db.rawQuery("select * from categoryTable1",null);
        int id = c.getColumnIndex("id");
        int name = c.getColumnIndex("name");
        int amount = c.getColumnIndex("amount");
        if(c.moveToFirst()){
            do {
                itemModel ca = new itemModel();
                ca.id = c.getString(id);
                ca.name = c.getString(name);
                ca.amount = c.getString(amount);
                catee.add(ca);
                count = count + 1;
                bill = bill + Integer.parseInt(c.getString(amount));
            }while (c.moveToNext());
        }
        str1="Total Bill : " + String.valueOf(bill);
        str2="Total Items : " + String.valueOf(count);
        t5.setText(str1);
        t6.setText(str2);
        return bill;
    }
}