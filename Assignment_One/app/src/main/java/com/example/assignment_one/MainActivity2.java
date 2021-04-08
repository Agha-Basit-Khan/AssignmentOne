package com.example.assignment_one;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {

    MediaPlayer mp;
    GridView gridView;
    int[] images = {R.drawable.aa,R.drawable.bb,R.drawable.cc,R.drawable.dd,R.drawable.ee,R.drawable.ff,R.drawable.gg,R.drawable.hh,R.drawable.ii,R.drawable.jj,R.drawable.kk,R.drawable.ll,R.drawable.mm,R.drawable.nn,R.drawable.oo,R.drawable.pp,R.drawable.qq,R.drawable.rr,R.drawable.ss,R.drawable.tt,R.drawable.uu,R.drawable.vv,R.drawable.ww,R.drawable.xx,R.drawable.yy,R.drawable.zz};
    String[] names = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    int[] audios = {R.raw.a,R.raw.b,R.raw.c,R.raw.d,R.raw.e,R.raw.f,R.raw.g,R.raw.h,R.raw.i,R.raw.j,R.raw.k,R.raw.l,R.raw.m,R.raw.n,R.raw.o,R.raw.p,R.raw.q,R.raw.r,R.raw.s,R.raw.t,R.raw.u,R.raw.v,R.raw.w,R.raw.x,R.raw.y,R.raw.z };
    int[] imagesShow = {R.drawable.ant,R.drawable.boat,R.drawable.cat,R.drawable.dog,R.drawable.elephant,R.drawable.fish,R.drawable.grapes,R.drawable.house,R.drawable.ice,R.drawable.jelly,R.drawable.kite,R.drawable.leaf,R.drawable.mouse,R.drawable.nails,R.drawable.orange,R.drawable.panda,R.drawable.queen,R.drawable.rabbit,R.drawable.sheep,R.drawable.teddy,R.drawable.umbrella,R.drawable.vegetable,R.drawable.watermelon,R.drawable.xray,R.drawable.yak,R.drawable.zebra};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mp = MediaPlayer.create(getApplicationContext(),R.raw.click);
        mp.start();

        gridView = findViewById(R.id.gridView);
        CustomAdapter customAdapter = new CustomAdapter(images,this);
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedName = names[i];
                int selectedImage = imagesShow[i];
                int selectedAudio = audios[i];

                startActivity(new Intent(MainActivity2.this,MainActivity3.class).putExtra("name",selectedName).putExtra("image",selectedImage).putExtra("audio",selectedAudio));
            }
        });

    }
    public class CustomAdapter extends BaseAdapter {
        private int[] imagesPhoto;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter(int[] imagesPhoto, Context context) {
            this.imagesPhoto = imagesPhoto;
            this.context = context;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return imagesPhoto.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            if (view == null) {
                view = layoutInflater.inflate(R.layout.row_items, viewGroup, false);

            }
            ImageView imageView = view.findViewById(R.id.imageView);
            imageView.setImageResource(imagesPhoto[i]);
            return view;
        }
    }
    }
