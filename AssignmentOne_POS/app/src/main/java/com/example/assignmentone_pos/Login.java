package com.example.assignmentone_pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText ed1,ed2;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed1=findViewById(R.id.user);
        ed2=findViewById(R.id.pass);
        b1=findViewById(R.id.btn1);
        b2=findViewById(R.id.btn2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
    public void login(){
        String username = ed1.getText().toString();
        String password = ed2.getText().toString();
        if(username.equals("admin")&&password.equals("123")){
            Toast.makeText(this,"Successfully Login",Toast.LENGTH_LONG);
            Intent i = new Intent(Login.this,Item.class);
            startActivity(i);
        }
        else if(username.equals("")&&password.equals("")){
            Toast.makeText(this,"Username And Password are Empty",Toast.LENGTH_LONG);
        }
        else{
            Toast.makeText(this,"Error Please Enter Correct Data",Toast.LENGTH_LONG);
        }


    }
}
