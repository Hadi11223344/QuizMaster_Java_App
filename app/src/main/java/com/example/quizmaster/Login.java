package com.example.quizmaster;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class Login extends AppCompatActivity {

    TextInputEditText tietEmail , tietPassword;
    AppCompatButton btnLogin;
    TextView tvSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        btnLogin.setOnClickListener(v -> {

            String email = Objects.requireNonNull(tietEmail.getText()).toString().trim();
            String password = Objects.requireNonNull(tietPassword.getText()).toString();
if(email.contains("@") && email.contains(".com") && !password.isEmpty()){
            try (
                    MyDBHelper dbHelper = new MyDBHelper(Login.this)) {

                if (dbHelper.verifyUser(email, password)) {

                    Log.d("ENTERED IF BLOCK OF VERIFY USER", "success ");
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setTitle("Login Success").setMessage("You are now logged in...");


                    builder.setPositiveButton("OK", (dialog, which) -> {
                        dialog.dismiss();

                        Intent intent = new Intent(Login.this, Home.class);
                        startActivity(intent);
                    });

                    //AlertDialog alertDialog = builder.create();
                    builder.show();

                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setTitle("Login Failed").setMessage("Email/Password incorrect");

                    builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }

        }else{

    Toast.makeText(Login.this,"Please correctly fill required information! ",Toast.LENGTH_LONG).show();
}
            });


        tvSignup.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this,Signup.class);
            startActivity(intent);
        });


    }

    private void init(){
        tietEmail = findViewById(R.id.tietEmail);
        tietPassword = findViewById(R.id.tietPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignup = findViewById(R.id.tvSignup);
    }
}