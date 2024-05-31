package com.studysmartjavafx;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AnsweringQuiz {
    
    public static void main(String[] args) {
        
        AnswerQuiz answerquiz = new AnswerQuiz();
        System.out.println(answerquiz.quizQuestions.size());
    }
  
}

class QuizQuestion{

    String question;
    String selection;
    String choice1;
    String choice2;
    String choice3;
    String choice4;
    String answer;  
    String level;
    String theme;
    
    public String getQuestion(){
        return question;
    }
    public String getSelection(){
        return selection;
    }
    public String getChoice1(){
        return choice1;
    }
    public String getChoice2(){
        return choice2;
    }
    public String getChoice3(){
        return choice3;
    }
    public String getChoice4(){
        return choice4;
    }
    public String getAnswer(){
        return answer;
    }
    public String getLevel(){
        return level;
    }
    public String getTheme(){
        return theme;
    }
    
    public void setQuestion(String a){
        this.question = a;
    }
    public void setSelection(String a){
        this.selection = a;
    }
    public void setChoice1(String a){
        this.choice1 = a;
    }
    public void setChoice2(String a){
        this.choice2 = a;
    }
    public void setChoice3(String a){
        this.choice3 = a;
    }
    public void setChoice4(String a){
        this.choice4 = a;
    }
    public void setAnswer(String a){
        this.answer = a;
    }
    public void setLevel(String a){
        this.level = a;
    }
    public void setTheme(String a){
        this.level = a;
    }


}

class AnswerQuiz {
    
    ArrayList<QuizQuestion> quizQuestions; 
    YoungStudent.PointSystem pointSystem = new YoungStudent.PointSystem();

    AnswerQuiz() {
        
        quizQuestions = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        
        String openQ = "";
        int themeIndex;
        int levelIndex = 0;
        int userScore = 0;
        
        
        
        QuizManager quizManager = new QuizManager();
        quizManager.createOwnQuiz();
        quizManager.m.displayQuizzes();
        
        System.out.println("Hi, Young Warrior! Ready to challenge yourself with questions from all themes, of every level, ALL AT ONCE?\n");

            System.out.print("Pick an option: \nYes[Y] / No[N]: ");
            openQ = scan.nextLine();

            if(openQ.equalsIgnoreCase("Y")){
                initQuestions(quizQuestions,"C://Users//USER//Downloads//QuestionsUpdated.csv/");
            }else{
                while (true){
            System.out.println("Select Theme : ");
            System.out.println("1 : Science");
            System.out.println("2 : Technology");
            System.out.println("3 : Engineering");
            System.out.println("4 : Mathematics\n");
            
            System.out.println("Select Level : ");
            System.out.println("1: Level 1(Basics)");
            System.out.println("2: Level 2(Intermediate)");
            System.out.println("3: Level 3(Advanced)");
            System.out.println("4: Level 4(Genius)\n");
            
            System.out.print("Enter Theme Index : ");
            themeIndex = scan.nextInt();
            
            System.out.print("Enter Level Index : ");
            levelIndex = scan.nextInt();
            
            if(themeIndex == 1 ||themeIndex == 2 ||themeIndex == 3 ||themeIndex == 4 )
                break;
            System.out.println("Invalid Theme Index, Please Re-enter");
            System.out.println();
            
            System.out.println("");
 
            if(levelIndex == 1 ||levelIndex == 2 ||levelIndex == 3 ||levelIndex == 4 )
                break;
            System.out.println("Invalid Level Index, Please Re-enter");
            System.out.println();
        }
                initQuestions(quizQuestions,"C://Users//USER//Downloads//QuestionsUpdated.csv/", themeIndex, levelIndex);
                scan.nextLine();
            }

            
            
            
        for(int i = 0; i<quizQuestions.size(); i++){ //Display questions
            System.out.println(quizQuestions.get(i).getQuestion());

            if (!quizQuestions.get(i).getSelection().equals("No Description"))
                System.out.println(quizQuestions.get(i).getSelection());
            
            System.out.println();
            System.out.println(quizQuestions.get(i).choice1);
            System.out.println(quizQuestions.get(i).choice2);
            System.out.println(quizQuestions.get(i).choice3);
            System.out.println(quizQuestions.get(i).choice4);


            System.out.print("Enter Your Answer : ");
            String userAnswer = scan.nextLine();
            System.out.println();

            if (userAnswer.equalsIgnoreCase(quizQuestions.get(i).answer)){
                System.out.println("Correct Answer !");
                userScore = userScore + 10;
                System.out.println("Current User Marks : " + userScore + " Marks");
                System.out.println();
            }

            else{
                System.out.println("Wrong Answer !");
                System.out.println("Current User Marks : " + userScore + " Marks");
                System.out.println();
            }
        }


        //Award 2 points upon completion of quiz
        if(openQ.equalsIgnoreCase("Y")){
            pointSystem.addPoints(30);
        }else{
            pointSystem.addPoints(2);
        }
        System.out.println("Congratulations! You have completed the quiz.");

        
    }

    public static void checkTheme(){
    }
    
    @SuppressWarnings("unchecked")
    public static void initQuestions(ArrayList questionArrayList, String questionPath){
        
        try { 
            BufferedReader br1 = new BufferedReader(new FileReader(questionPath));
            
            int lineCount = 0; //num of lines
            int indexCount = 0; //num of ques

                while (br1.ready()){
                    if (lineCount==0){
                        String line = br1.readLine();
                        lineCount++;
                        continue;
                    }
                        String line = br1.readLine();
                        String[] lineArray = line.split(",");
                        
                        QuizQuestion question = new QuizQuestion();
                        question.question = lineArray[0];
                        question.selection = lineArray[1];
                        question.theme = lineArray[2];
                        question.choice1 = lineArray[3];
                        question.choice2 = lineArray[4];
                        question.choice3 = lineArray[5];
                        question.choice4 = lineArray[6];
                        question.answer = lineArray[7];
                        question.level = lineArray[8];
                        
                        questionArrayList.add(question);
                        indexCount++;
                    }
                    System.out.println();
            }catch (Exception e) {
            System.out.println("Error");
        }
    }
    
    @SuppressWarnings("unchecked")
    public static void initQuestions(ArrayList questionArrayList, String questionPath, int themeIndex, int levelIndex){
        
        try { //Track questions
            BufferedReader br2 = new BufferedReader(new FileReader(questionPath));
            int lineCount = 0; //num of lines
            int indexCount = 0; //num of ques

            if(themeIndex==1 && levelIndex==1){
                while (br2.ready()){
                    if (lineCount==0){
                        String line = br2.readLine();
                        lineCount++;
                        continue;
                    }
                        String line = br2.readLine();
                        String[] lineArray = line.split(",");
                        
                        QuizQuestion question = new QuizQuestion();
                        question.theme = lineArray[2];
                    if (lineArray[2].equals("Science")&&lineArray[8].equals("1")){ 
                        
                        question.question = lineArray[0];
                        question.selection = lineArray[1];
                        question.choice1 = lineArray[3];
                        question.choice2 = lineArray[4];
                                
                        question.choice3 = lineArray[5];
                        question.choice4 = lineArray[6];
                        question.answer = lineArray[7];
                        question.level = lineArray[8];
                        
                        questionArrayList.add(question);
                        indexCount++;
                    }
                }
                System.out.println("");
            }

            else if(themeIndex==1 && levelIndex==2){
                while (br2.ready()){
                    if (lineCount==0){
                        String line = br2.readLine();
                        lineCount++;
                        continue;
                    }
                        String line = br2.readLine();
                        String[] lineArray = line.split(",");
                        
                        QuizQuestion question = new QuizQuestion();
                        question.theme = lineArray[2];
                    if (lineArray[2].equals("Science")&&lineArray[8].equals("2")){ 
                        
                        question.question = lineArray[0];
                        question.selection = lineArray[1];
                        question.choice1 = lineArray[3];
                        question.choice2 = lineArray[4];
                                
                        question.choice3 = lineArray[5];
                        question.choice4 = lineArray[6];
                        question.answer = lineArray[7];
                        question.level = lineArray[8];
                        
                        questionArrayList.add(question);
                        indexCount++;
                    }
                }
                System.out.println("");
            }
            
            else if(themeIndex==1 && levelIndex==3){
                while (br2.ready()){
                    if (lineCount==0){
                        String line = br2.readLine();
                        lineCount++;
                        continue;
                    }
                        String line = br2.readLine();
                        String[] lineArray = line.split(",");
                        
                        QuizQuestion question = new QuizQuestion();
                        question.theme = lineArray[2];
                    if (lineArray[2].equals("Science")&&lineArray[8].equals("3")){ 
                        
                        question.question = lineArray[0];
                        question.selection = lineArray[1];
                        question.choice1 = lineArray[3];
                        question.choice2 = lineArray[4];
                                
                        question.choice3 = lineArray[5];
                        question.choice4 = lineArray[6];
                        question.answer = lineArray[7];
                        question.level = lineArray[8];
                        
                        questionArrayList.add(question);
                        indexCount++;
                    }
                }
                System.out.println("");
            }
            
            else if(themeIndex==1 && levelIndex==4){
                while (br2.ready()){
                    if (lineCount==0){
                        String line = br2.readLine();
                        lineCount++;
                        continue;
                    }
                        String line = br2.readLine();
                        String[] lineArray = line.split(",");
                        
                        QuizQuestion question = new QuizQuestion();
                        question.theme = lineArray[2];
                    if (lineArray[2].equals("Science")&&lineArray[8].equals("4")){ 
                        
                        question.question = lineArray[0];
                        question.selection = lineArray[1];
                        question.choice1 = lineArray[3];
                        question.choice2 = lineArray[4];
                                
                        question.choice3 = lineArray[5];
                        question.choice4 = lineArray[6];
                        question.answer = lineArray[7];
                        question.level = lineArray[8];
                        
                        questionArrayList.add(question);
                        indexCount++;
                    }
                }
                System.out.println("");
            }
            else if(themeIndex==2 && levelIndex==1){
                while (br2.ready()){
                    if (lineCount==0){
                        String line = br2.readLine();
                        lineCount++;
                        continue;
                    }
                        String line = br2.readLine();
                        String[] lineArray = line.split(",");
                        
                        QuizQuestion question = new QuizQuestion();
                        question.theme = lineArray[2];
                    if (lineArray[2].equals("Technology")&&lineArray[8].equals("1")){ 
                        
                        question.question = lineArray[0];
                        question.selection = lineArray[1];
                        question.choice1 = lineArray[3];
                        question.choice2 = lineArray[4];
                                
                        question.choice3 = lineArray[5];
                        question.choice4 = lineArray[6];
                        question.answer = lineArray[7];
                        question.level = lineArray[8];
                        
                        questionArrayList.add(question);
                        indexCount++;
                    }
                }
                System.out.println("");
            }
            else if(themeIndex==2 && levelIndex==2){
                while (br2.ready()){
                    if (lineCount==0){
                        String line = br2.readLine();
                        lineCount++;
                        continue;
                    }
                        String line = br2.readLine();
                        String[] lineArray = line.split(",");
                        
                        QuizQuestion question = new QuizQuestion();
                        question.theme = lineArray[2];
                    if (lineArray[2].equals("Technology")&&lineArray[8].equals("1")){ 
                        
                        question.question = lineArray[0];
                        question.selection = lineArray[1];
                        question.choice1 = lineArray[3];
                        question.choice2 = lineArray[4];
                                
                        question.choice3 = lineArray[5];
                        question.choice4 = lineArray[6];
                        question.answer = lineArray[7];
                        question.level = lineArray[8];
                        
                        questionArrayList.add(question);
                        indexCount++;
                    }
                }
                System.out.println("");
            }
            else if(themeIndex==2 && levelIndex==3){
                while (br2.ready()){
                    if (lineCount==0){
                        String line = br2.readLine();
                        lineCount++;
                        continue;
                    }
                        String line = br2.readLine();
                        String[] lineArray = line.split(",");
                        
                        QuizQuestion question = new QuizQuestion();
                        question.theme = lineArray[2];
                    if (lineArray[2].equals("Technology")&&lineArray[8].equals("3")){ 
                        
                        question.question = lineArray[0];
                        question.selection = lineArray[1];
                        question.choice1 = lineArray[3];
                        question.choice2 = lineArray[4];
                                
                        question.choice3 = lineArray[5];
                        question.choice4 = lineArray[6];
                        question.answer = lineArray[7];
                        question.level = lineArray[8];
                        
                        questionArrayList.add(question);
                        indexCount++;
                    }
                }
                System.out.println("");
            }
            else if(themeIndex==2 && levelIndex==4){
                while (br2.ready()){
                    if (lineCount==0){
                        String line = br2.readLine();
                        lineCount++;
                        continue;
                    }
                        String line = br2.readLine();
                        String[] lineArray = line.split(",");
                        
                        QuizQuestion question = new QuizQuestion();
                        question.theme = lineArray[2];
                    if (lineArray[2].equals("Technology")&&lineArray[8].equals("4")){ 
                        
                        question.question = lineArray[0];
                        question.selection = lineArray[1];
                        question.choice1 = lineArray[3];
                        question.choice2 = lineArray[4];
                                
                        question.choice3 = lineArray[5];
                        question.choice4 = lineArray[6];
                        question.answer = lineArray[7];
                        question.level = lineArray[8];
                        
                        questionArrayList.add(question);
                        indexCount++;
                    }
                }
                System.out.println("");
            }
            
            else if(themeIndex==3 && levelIndex==1){
                while (br2.ready()){
                    if (lineCount==0){
                        String line = br2.readLine();
                        lineCount++;
                        continue;
                    }
                        String line = br2.readLine();
                        String[] lineArray = line.split(",");
                        
                        QuizQuestion question = new QuizQuestion();
                        question.theme = lineArray[2];
                    if (lineArray[2].equals("Engineering")&&lineArray[8].equals("1")){ 
                        
                        question.question = lineArray[0];
                        question.selection = lineArray[1];
                        question.choice1 = lineArray[3];
                        question.choice2 = lineArray[4];
                                
                        question.choice3 = lineArray[5];
                        question.choice4 = lineArray[6];
                        question.answer = lineArray[7];
                        question.level = lineArray[8];
                        
                        questionArrayList.add(question);
                        indexCount++;
                    }
                }
                System.out.println("");
            }
            if(themeIndex==1 && levelIndex==1){
                while (br2.ready()){
                    if (lineCount==0){
                        String line = br2.readLine();
                        lineCount++;
                        continue;
                    }
                        String line = br2.readLine();
                        String[] lineArray = line.split(",");
                        
                        QuizQuestion question = new QuizQuestion();
                        question.theme = lineArray[2];
                    if (lineArray[2].equals("Science")&&lineArray[8].equals("1")){ 
                        
                        question.question = lineArray[0];
                        question.selection = lineArray[1];
                        question.choice1 = lineArray[3];
                        question.choice2 = lineArray[4];
                                
                        question.choice3 = lineArray[5];
                        question.choice4 = lineArray[6];
                        question.answer = lineArray[7];
                        question.level = lineArray[8];
                        
                        questionArrayList.add(question);
                        indexCount++;
                    }
                }
                System.out.println("");
            }
            else if(themeIndex==3 && levelIndex==2){
                while (br2.ready()){
                    if (lineCount==0){
                        String line = br2.readLine();
                        lineCount++;
                        continue;
                    }
                        String line = br2.readLine();
                        String[] lineArray = line.split(",");
                        
                        QuizQuestion question = new QuizQuestion();
                        question.theme = lineArray[2];
                    if (lineArray[2].equals("Engineering")&&lineArray[8].equals("2")){ 
                        
                        question.question = lineArray[0];
                        question.selection = lineArray[1];
                        question.choice1 = lineArray[3];
                        question.choice2 = lineArray[4];
                                
                        question.choice3 = lineArray[5];
                        question.choice4 = lineArray[6];
                        question.answer = lineArray[7];
                        question.level = lineArray[8];
                        
                        questionArrayList.add(question);
                        indexCount++;
                    }
                }
                System.out.println("");
            }
            else if(themeIndex==3 && levelIndex==3){
                while (br2.ready()){
                    if (lineCount==0){
                        String line = br2.readLine();
                        lineCount++;
                        continue;
                    }
                        String line = br2.readLine();
                        String[] lineArray = line.split(",");
                        
                        QuizQuestion question = new QuizQuestion();
                        question.theme = lineArray[2];
                    if (lineArray[2].equals("Engineering")&&lineArray[8].equals("3")){ 
                        
                        question.question = lineArray[0];
                        question.selection = lineArray[1];
                        question.choice1 = lineArray[3];
                        question.choice2 = lineArray[4];
                                
                        question.choice3 = lineArray[5];
                        question.choice4 = lineArray[6];
                        question.answer = lineArray[7];
                        question.level = lineArray[8];
                        
                        questionArrayList.add(question);
                        indexCount++;
                    }
                }
                System.out.println("");
            }
            if(themeIndex==3 && levelIndex==4){
                while (br2.ready()){
                    if (lineCount==0){
                        String line = br2.readLine();
                        lineCount++;
                        continue;
                    }
                        String line = br2.readLine();
                        String[] lineArray = line.split(",");
                        
                        QuizQuestion question = new QuizQuestion();
                        question.theme = lineArray[2];
                    if (lineArray[2].equals("Engineering")&&lineArray[8].equals("4")){ 
                        
                        question.question = lineArray[0];
                        question.selection = lineArray[1];
                        question.choice1 = lineArray[3];
                        question.choice2 = lineArray[4];
                                
                        question.choice3 = lineArray[5];
                        question.choice4 = lineArray[6];
                        question.answer = lineArray[7];
                        question.level = lineArray[8];
                        
                        questionArrayList.add(question);
                        indexCount++;
                    }
                }
                System.out.println("");
            }
            else if(themeIndex==4 && levelIndex==1){
                while (br2.ready()){
                    if (lineCount==0){
                        String line = br2.readLine();
                        lineCount++;
                        continue;
                    }
                        String line = br2.readLine();
                        String[] lineArray = line.split(",");
                        
                        QuizQuestion question = new QuizQuestion();
                        question.theme = lineArray[2];
                    if (lineArray[2].equals("Mathematics")&&lineArray[8].equals("1")){ 
                        
                        question.question = lineArray[0];
                        question.selection = lineArray[1];
                        question.choice1 = lineArray[3];
                        question.choice2 = lineArray[4];
                                
                        question.choice3 = lineArray[5];
                        question.choice4 = lineArray[6];
                        question.answer = lineArray[7];
                        question.level = lineArray[8];
                        
                        questionArrayList.add(question);
                        indexCount++;
                    }
                }
                System.out.println("");
            }
            if(themeIndex==4 && levelIndex==2){
                while (br2.ready()){
                    if (lineCount==0){
                        String line = br2.readLine();
                        lineCount++;
                        continue;
                    }
                        String line = br2.readLine();
                        String[] lineArray = line.split(",");
                        
                        QuizQuestion question = new QuizQuestion();
                        question.theme = lineArray[2];
                    if (lineArray[2].equals("Mathematics")&&lineArray[8].equals("2")){ 
                        
                        question.question = lineArray[0];
                        question.selection = lineArray[1];
                        question.choice1 = lineArray[3];
                        question.choice2 = lineArray[4];
                                
                        question.choice3 = lineArray[5];
                        question.choice4 = lineArray[6];
                        question.answer = lineArray[7];
                        question.level = lineArray[8];
                        
                        questionArrayList.add(question);
                        indexCount++;
                    }
                }
                System.out.println("");
            }
            if(themeIndex==4 && levelIndex==3){
                while (br2.ready()){
                    if (lineCount==0){
                        String line = br2.readLine();
                        lineCount++;
                        continue;
                    }
                        String line = br2.readLine();
                        String[] lineArray = line.split(",");
                        
                        QuizQuestion question = new QuizQuestion();
                        question.theme = lineArray[2];
                    if (lineArray[2].equals("Mathematics")&&lineArray[8].equals("3")){ 
                        
                        question.question = lineArray[0];
                        question.selection = lineArray[1];
                        question.choice1 = lineArray[3];
                        question.choice2 = lineArray[4];
                                
                        question.choice3 = lineArray[5];
                        question.choice4 = lineArray[6];
                        question.answer = lineArray[7];
                        question.level = lineArray[8];
                        
                        questionArrayList.add(question);
                        indexCount++;
                    }
                }
                System.out.println("");
            }
            
            else if(themeIndex==4 && levelIndex==4){
                while (br2.ready()){
                    if (lineCount==0){
                        String line = br2.readLine();
                        lineCount++;
                        continue;
                    }
                        String line = br2.readLine();
                        String[] lineArray = line.split(",");
                        
                        QuizQuestion question = new QuizQuestion();
                        question.theme = lineArray[2];
                        if (lineArray[2].equals("Mathematics")&&lineArray[8].equals("4")){ 
                        
                        question.question = lineArray[0];
                        question.selection = lineArray[1];
                        question.choice1 = lineArray[3];
                        question.choice2 = lineArray[4];
                                
                        question.choice3 = lineArray[5];
                        question.choice4 = lineArray[6];
                        question.answer = lineArray[7];
                        question.level = lineArray[8];
                        
                        questionArrayList.add(question);
                        indexCount++;
                    }
                }
                System.out.println("");
            }
            
            //+points (+2 marks)

        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
