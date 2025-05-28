package com.example.spiningroulette2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int count;
    private ImageView roulette;
    private Button btnStart;
    private TextView txtResult;
    private TextView txtBack;
    private TextView txtChosen;
    private Random random = new Random();
    private int reset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        count = getIntent().getIntExtra("inputNum", 0);
        roulette = findViewById(R.id.roulette);
        btnStart = findViewById(R.id.btnStart);
        txtResult = findViewById(R.id.txtResult);
        txtBack = findViewById(R.id.txtBack);
        txtChosen = findViewById(R.id.txtChosen);
        /*
        for (int i=0; i < count; i++) {
            Toast.makeText(this, getIntent().getStringExtra("Sec" + (i+1)), Toast.LENGTH_SHORT).show();
        }
         */

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtChosen.setText("");
                roulette.setRotation(reset);
                spinRoulette();
            }
        });


        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtBack.setTextColor(getColor(R.color.black));
                Intent intent = new Intent(MainActivity.this, SettingValues.class);
                intent.putExtra("inputNum",count);
                startActivity(intent);
            }
        });
    }

    void spinRoulette() {
        //try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        int degrees = random.nextInt(360) + 3600;
        roulette.animate()
                .rotationBy(degrees)
                .setDuration(3000)
                .setInterpolator(new DecelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        // This runs AFTER the spin finishes
                        int result = (degrees % 360) / (360 / count) + 1;
                        reset = 360 - result;
                        txtChosen.setText(getIntent().getStringExtra("Sec" + result));
                    }
                })
                .start();


//        int result = (degrees % 360)/(360/count)+1;
//        txtChosen.setText(getIntent().getStringExtra("Sec" + result));
    }
}