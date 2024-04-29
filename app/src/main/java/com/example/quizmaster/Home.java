package com.example.quizmaster;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.text.MessageFormat;

public class Home extends AppCompatActivity {

    private TextView tvTotalQues, tvQuestion;
    private Button btnAnsa,btnAnsb,btnAnsc,btnAnsd,btnSubmitHome;
    private final QuizManager quizManager = QuizManager.getInstance();
    private Question currentQues;
    private  int totalQuestions;
    private int correctAnswersCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        quizManager.resetQuiz();
        init();

        btnAnsa.setOnClickListener(v -> checkAnswer(btnAnsa));

        btnAnsb.setOnClickListener(v -> checkAnswer(btnAnsb));

        btnAnsc.setOnClickListener(v -> checkAnswer(btnAnsc));

        btnAnsd.setOnClickListener(v -> checkAnswer(btnAnsd));
        btnSubmitHome.setOnClickListener(v -> loadNextQuestion());


    }
    private void loadNextQuestion(){
        currentQues = quizManager.getNextQues();
        if(currentQues != null){
            totalQuestions--;
            tvTotalQues.setText(MessageFormat.format("Total Questions: {0}", totalQuestions));
            tvQuestion.setText(currentQues.getQuesStatement());
            btnAnsa.setText(currentQues.getOptionA());
            btnAnsb.setText(currentQues.getOptionB());
            btnAnsc.setText(currentQues.getOptionC());
            btnAnsd.setText(currentQues.getOptionD());

            btnAnsa.setEnabled(true);
            btnAnsb.setEnabled(true);
            btnAnsc.setEnabled(true);
            btnAnsd.setEnabled(true);

            btnAnsa.setBackgroundColor(getColor(R.color.blue_btn));
            btnAnsb.setBackgroundColor(getColor(R.color.blue_btn));
            btnAnsc.setBackgroundColor(getColor(R.color.blue_btn));
            btnAnsd.setBackgroundColor(getColor(R.color.blue_btn));



        }else{
            Toast.makeText(Home.this, "Quiz completed. Correct answers: " + correctAnswersCount, Toast.LENGTH_LONG).show();

            btnSubmitHome.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                builder.setTitle("Quiz" ).setMessage("Quiz Completed\nScore: "+ correctAnswersCount).show();
                builder.setPositiveButton("OK", (dialog,which)-> {
                    dialog.dismiss();
                    Intent intent = new Intent(Home.this, Login.class);
                    startActivity(intent);
                    finish();

                });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

            });


        }
    }
    private void init(){
        currentQues = quizManager.getNextQues();

        tvQuestion = findViewById(R.id.tvQuestion);
        tvTotalQues = findViewById(R.id.tvTotalQues);
        btnAnsa = findViewById(R.id.btnAnsa);
        btnAnsb = findViewById(R.id.btnAnsb);
        btnAnsc = findViewById(R.id.btnAnsc);
        btnAnsd = findViewById(R.id.btnAnsd);
        btnSubmitHome = findViewById(R.id.btnSubmitHome);

        totalQuestions = quizManager.getTotalQuestions();
        tvTotalQues.setText(MessageFormat.format("Total Questions: {0}", totalQuestions));
        tvQuestion.setText(currentQues.getQuesStatement());
        btnAnsa.setText(currentQues.getOptionA());
        btnAnsb.setText(currentQues.getOptionB());
        btnAnsc.setText(currentQues.getOptionC());
        btnAnsd.setText(currentQues.getOptionD());


    }
    private void checkAnswer(Button selectedButton){
        String optionSelected = selectedButton.getText().toString();
        if(optionSelected.equals(currentQues.getCorrectAns())){
            selectedButton.setBackgroundColor(getColor(R.color.btn_right_ans));
            correctAnswersCount++;

        }else{
            selectedButton.setBackgroundColor(getColor(R.color.btn_wrong_ans));
            if(currentQues.getCorrectAns().equals(btnAnsa.getText().toString())){
                btnAnsa.setBackgroundColor(getColor(R.color.btn_right_ans));
            }else if(currentQues.getCorrectAns().equals(btnAnsb.getText().toString())){
                btnAnsb.setBackgroundColor(getColor(R.color.btn_right_ans));

            }else if(currentQues.getCorrectAns().equals(btnAnsc.getText().toString())){
                btnAnsc.setBackgroundColor(getColor(R.color.btn_right_ans));
            }else if(currentQues.getCorrectAns().equals(btnAnsd.getText().toString())){
                btnAnsd.setBackgroundColor(getColor(R.color.btn_right_ans));
            }
        }

        btnAnsa.setEnabled(false);
        btnAnsb.setEnabled(false);
        btnAnsc.setEnabled(false);
        btnAnsd.setEnabled(false);

    }
}