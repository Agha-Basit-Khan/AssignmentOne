package com.example.assignment_one;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    MediaPlayer mp;
    int selectedAudio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.tvName);
        Intent intent = getIntent();
        if(intent.getExtras() != null){
            String selectedName = intent.getStringExtra("name");
            int selectedImage = intent.getIntExtra("image",0);
            selectedAudio = intent.getIntExtra("audio",0);

            textView.setText(selectedName);
            imageView.setImageResource(selectedImage);
            mp = MediaPlayer.create(getApplicationContext(),selectedAudio);
            mp.start();
        }
    }

    public void back(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
        startActivity(intent);
    }

    public void again(View view) {
        mp = MediaPlayer.create(getApplicationContext(),selectedAudio);
        mp.start();
    }
}