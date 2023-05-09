package com.example.test;

public class Questions {
  private String Questions, Answer1, Answer2, Answer3, Answer4;
  private int Correct_Ans_Num;

    public Questions(String questions, String answer1, String answer2, String answer3, String answer4, int correct_Ans_Num) {
        Questions = questions;
        Answer1 = answer1;
        Answer2 = answer2;
        Answer3 = answer3;
        Answer4 = answer4;
        Correct_Ans_Num = correct_Ans_Num;
    }

    public String getQuestions() {
        return Questions;
    }

    public void setQuestions(String questions) {
        Questions = questions;
    }

    public String getAnswer1() {
        return Answer1;
    }

    public void setAnswer1(String answer1) {
        Answer1 = answer1;
    }

    public String getAnswer2() {
        return Answer2;
    }

    public void setAnswer2(String answer2) {
        Answer2 = answer2;
    }

    public String getAnswer3() {
        return Answer3;
    }

    public void setAnswer3(String answer3) {
        Answer3 = answer3;
    }

    public String getAnswer4() {
        return Answer4;
    }

    public void setAnswer4(String answer4) {
        Answer4 = answer4;
    }

    public int getCorrect_Ans_Num() {
        return Correct_Ans_Num;
    }

    public void setCorrect_Ans_Num(int correct_Ans_Num) {
        Correct_Ans_Num = correct_Ans_Num;
    }
}
