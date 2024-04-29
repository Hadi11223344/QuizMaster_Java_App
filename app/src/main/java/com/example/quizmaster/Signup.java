package com.example.quizmaster;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Objects;

public class Signup extends AppCompatActivity {

    TextInputLayout til_name,til_email,til_password;
    TextInputEditText textInputETtName, textInputETEmail, textInputETPass;
    AppCompatButton appCompatBtnSignup;
    TextView tvSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();


            appCompatBtnSignup.setOnClickListener(v-> {

                    String name = Objects.requireNonNull(textInputETtName.getText()).toString().trim();
                    String email = Objects.requireNonNull(textInputETEmail.getText()).toString().trim();
                    String password = Objects.requireNonNull(textInputETPass.getText()).toString();
                    // Reset errors
                    til_name.setError(null);
                    til_email.setError(null);
                    til_password.setError(null);

                    if(name.isEmpty() || email.isEmpty() || password.isEmpty()){

                        Toast.makeText(Signup.this,"Please fill the required information", Toast.LENGTH_LONG).show();
                        // setting error in text input layout
                        if(name.isEmpty()){
                            til_name.setError("!");
                            textInputETtName.requestFocus();
                        } else if (email.isEmpty()) {
                            til_email.setError("!");
                            textInputETEmail.requestFocus();

                        }else {
                            til_password.setError("!");
                            textInputETPass.requestFocus();
                        }


                    }else{

                        if(!email.contains("@") || (!email.contains(".com"))){
                            Toast.makeText(Signup.this,"Please enter correct format of email", Toast.LENGTH_LONG).show();
                            textInputETEmail.requestFocus();
                        }else{

                            try (MyDBHelper myDBHelper = new MyDBHelper(Signup.this)) {
                                myDBHelper.addUser(name, email, password);

                            }


                            AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
                            builder.setTitle("Sucess").setMessage("Signup Sucess! ");

                            builder.setPositiveButton("OK", (dialog,which)-> {

                                    dialog.dismiss();
                                    Intent intent = new Intent(Signup.this, Login.class);
                                    startActivity(intent);
                                    finish();
                            });
                            builder.show();

                        }

                    }
            });

    }
    public void init(){
        textInputETtName = findViewById(R.id.tietNameSP);
        textInputETEmail = findViewById(R.id.tietEmailSP);
        textInputETPass = findViewById(R.id.tietPasswordSP);
        appCompatBtnSignup = findViewById(R.id.btnSignup);
        tvSignup = findViewById(R.id.tvSignup);
        til_name = findViewById(R.id.tf_name);
        til_email = findViewById(R.id.tf_email);
        til_password = findViewById(R.id.tf_password);
    }

}