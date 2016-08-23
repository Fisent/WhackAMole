package com.example.lukzi_000.whackamole;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class GameActivity extends AppCompatActivity {

    private int clicked;
    public static int score;
    public static boolean game;
    private int time = 1500;
    //0 - hole, 1 - mole, 2 - shit
    private ImageView[] tab = new ImageView[9];
    private HashMap<ImageView,Integer> map = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        score = 0;
        map.put((ImageView)findViewById(R.id.img1),0);
        map.put((ImageView)findViewById(R.id.img2),0);
        map.put((ImageView)findViewById(R.id.img3),0);
        map.put((ImageView)findViewById(R.id.img4),0);
        map.put((ImageView)findViewById(R.id.img5),0);
        map.put((ImageView)findViewById(R.id.img6),0);
        map.put((ImageView)findViewById(R.id.img7),0);
        map.put((ImageView)findViewById(R.id.img8),0);
        map.put((ImageView)findViewById(R.id.img9),0);
        tab[0]=((ImageView)findViewById(R.id.img1));
        tab[1]=((ImageView)findViewById(R.id.img2));
        tab[2]=((ImageView)findViewById(R.id.img3));
        tab[3]=((ImageView)findViewById(R.id.img4));
        tab[4]=((ImageView)findViewById(R.id.img5));
        tab[5]=((ImageView)findViewById(R.id.img6));
        tab[6]=((ImageView)findViewById(R.id.img7));
        tab[7]=((ImageView)findViewById(R.id.img8));
        tab[8]=((ImageView)findViewById(R.id.img9));


        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                step();
                handler.postDelayed(this,time);
            }
        });

        handler.post(new Runnable() {
            @Override
            public void run() {
                actualize();
                handler.postDelayed(this,1);
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(!game)
        {
            onBackPressed();
        }
    }

    public void onClick1(View view){clicked=1;onClick(0);}
    public void onClick2(View view){clicked=2;onClick(1);}
    public void onClick3(View view){clicked=3;onClick(2);}
    public void onClick4(View view){clicked=4;onClick(3);}
    public void onClick5(View view){clicked=5;onClick(4);}
    public void onClick6(View view){clicked=6;onClick(5);}
    public void onClick7(View view){clicked=7;onClick(6);}
    public void onClick8(View view){clicked=8;onClick(7);}
    public void onClick9(View view){clicked=9;onClick(8);}

    public void onClick(int i)
    {
        if (map.get(tab[i]) == 0) {
            if(score >0) score--;
        } else if (map.get(tab[i]) == 1) {
            tab[i].setImageResource(R.drawable.hole);
            map.put(tab[i], 0);
            score++;
        } else {
            startActivity(new Intent(this, GameOverActivity.class));
        }
    }

    public void random()
    {
        for (ImageView img : map.keySet())
        {
            double r = Math.random();
            if(r<0.33)map.put(img,0);
            else if(r<0.66)map.put(img,1);
            else map.put(img,2);
        }
    }

    public void actualize()
    {
        ((TextView) findViewById(R.id.textView2)).setText(score+"");
        for(ImageView img : map.keySet())
        {
            if(map.get(img)==0) img.setImageResource(R.drawable.hole);
            else if(map.get(img)==1) img.setImageResource(R.drawable.mole);
            else img.setImageResource(R.drawable.shit);
        }
    }

    public void step()
    {

        random();

        actualize();
        time = Matematic.f(countMoles());
    }

    public int countMoles()
    {
        int out = 0;
        for(int i:map.values())
        {
            if(i==1)out++;
        }
        return out;
    }


}
