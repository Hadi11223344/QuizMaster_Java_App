package com.example.quizmaster;

import java.util.ArrayList;
import java.util.List;

public class QuizManager {
    private static QuizManager instance;
    private final List<Question> questionList;
    private int currentQuesIndex;

    private QuizManager() {
        questionList = new ArrayList<>();
        questionList.add(new Question("What is the capital of France?",
                "Paris",
                "Berlin",
                "London",
                "Rome",
                "Paris"));
        questionList.add(new Question("Who wrote the novel 'To Kill a Mockingbird'?",
                "Harper Lee",
                "J.K. Rowling",
                "Charles Dickens",
                "Ernest Hemingway",
                "Harper Lee"));
        questionList.add(new Question("Which planet is known as the Red Planet?",
                "Mars",
                "Venus",
                "Jupiter",
                "Saturn",
                "Mars"));
        questionList.add(new Question("What is the chemical symbol for water?",
                "H2O",
                "CO2",
                "NaCl",
                "O2",
                "H2O"));
        questionList.add(new Question("Which country is famous for the ancient city of Machu Picchu?",
                "Peru",
                "Brazil",
                "Mexico",
                "Chile",
                "Peru"));
        questionList.add(new Question("Who painted the Mona Lisa?",
                "Leonardo da Vinci",
                "Vincent van Gogh",
                "Pablo Picasso",
                "Michelangelo",
                "Leonardo da Vinci"));
        questionList.add(new Question("Which element has the chemical symbol 'Fe'?",
                "Iron",
                "Gold",
                "Copper",
                "Silver",
                "Iron"));
        questionList.add(new Question("What is the tallest mountain in the world?",
                "Mount Everest",
                "K2",
                "Kangchenjunga",
                "Lhotse",
                "Mount Everest"));
        questionList.add(new Question("Who was the first person to step on the moon?",
                "Neil Armstrong",
                "Buzz Aldrin",
                "Yuri Gagarin",
                "Alan Shepard",
                "Neil Armstrong"));
        questionList.add(new Question("Which gas is most abundant in the Earth's atmosphere?",
                "Oxygen",
                "Nitrogen",
                "Carbon dioxide",
                "Argon",
                "Nitrogen"));


    }

    public int getTotalQuestions() {
        return questionList.size();
    }

    public static synchronized QuizManager getInstance() {
        if (instance == null) {
            instance = new QuizManager();
        }
        return instance;
    }

    public Question getNextQues() {
        if (currentQuesIndex < questionList.size()) {
            return questionList.get(currentQuesIndex++);
        }
        return null;
    }

    public void resetQuiz() {
        currentQuesIndex = 0;
    }

}
