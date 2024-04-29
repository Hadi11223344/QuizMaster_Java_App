package com.example.quizmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView animTextView;


        animTextView = findViewById(R.id.textView);
        Animation textViewAnimation = AnimationUtils.loadAnimation(this,R.anim.textviewsplash);

        animTextView.setAnimation(textViewAnimation);

        Intent login = new Intent(Splash.this, Login.class);
        new Handler().postDelayed(() -> {
            startActivity(login);
            finish();

        },4000);

    }



}
