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

public class Payment extends AppCompatActivity {

    TextView v1,v2;
    EditText ed1;
    String check,bill;
    Button b1;
    int a=0,b=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        b1=findViewById(R.id.btn1);
        ed1=findViewById(R.id.pay);
        v1=findViewById(R.id.view1);
        v2=findViewById(R.id.view2);
        Intent i = getIntent();
        bill = i.getStringExtra("num");
        v1.setText("Your Total Bill is : " + bill);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = ed1.getText().toString();
                a = Integer.parseInt(bill);
                b = Integer.parseInt(check);
                if (b >= a){
                    v2.setText("Payment Successfully Done.");
                    delete();
                    deleteCustomer();
                    Intent i = new Intent(Payment.this,MainActivity.class);
                    startActivity(i);
                }
                else {
                    v2.setText("Please Enter Correct Amount.");
                }
            }
        });

    }
    public void delete(){
        try {
            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);

            String sql = "DELETE FROM categoryTable1";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.execute();
            Toast.makeText(this,"Payment Done",Toast.LENGTH_LONG);
        }
        catch (Exception ex){

            Toast.makeText(this,"Fail Payment",Toast.LENGTH_LONG);
        }
    }
    public void deleteCustomer(){
        try {
            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);

            String sql = "DELETE FROM customerTable2";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.execute();
        }
        catch (Exception ex){

            Toast.makeText(this,"Fail ",Toast.LENGTH_LONG);
        }
    }
}