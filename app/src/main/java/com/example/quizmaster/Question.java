package com.example.quizmaster;

public class Question {
    private final String quesStatement;
    private final String optionA;
    private final String optionB;
    private final String optionC;
    private final String optionD;
    private final String correctAns;

    public Question (String quesStatement,
                     String optionA,
                     String optionB,
                     String optionC,
                     String optionD,
                     String correctAns){

        this.quesStatement = quesStatement;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAns = correctAns;

    }
// getters
    public String getQuesStatement(){ return quesStatement;}
    public String getOptionA(){ return optionA;}
    public String getOptionB(){return optionB;}
    public String getOptionC(){return optionC;}
    public String getOptionD(){return optionD;}
    public String getCorrectAns(){return correctAns;}

}
