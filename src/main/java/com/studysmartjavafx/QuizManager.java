package com.studysmartjavafx;

import java.util.ArrayList;
import java.util.Scanner;

public class QuizManager {

    int numOfQuizCreated = 16;
    
    MakeQuizzes m = new MakeQuizzes();

   
    public void createOwnQuiz() {
        m.addQuiz("It'S TiMe to LeArN", "Level 1: Basics", "Multiple Choice Questions (MCQ)", "Science");
        m.addQuiz("Tech Magic", "Level 1: Basics", "Multiple Choice Questions (MCQ)", "Technology");
        m.addQuiz("Dora, the Engineer", "Level 1: Basics", "Multiple Choice Questions (MCQ)", "Engineering");
        m.addQuiz("Maths Mania", "Level 1: Basics", "Multiple Choice Questions (MCQ)", "Mathematics");
        m.addQuiz("Little Scientist", "Level 2: Intermediate", "Multiple Choice Questions (MCQ)", "Science");
        m.addQuiz("Tech Titans", "Level 2: Intermediate", "Multiple Choice Questions (MCQ)", "Technology");
        m.addQuiz("Engineering Explorers", "Level 2: Intermediate", "Multiple Choice Questions (MCQ)", "Engineering");
        m.addQuiz("Math Avengers", "Level 2: Intermediate", "Multiple Choice Questions (MCQ)", "Mathematics");
        m.addQuiz("For the Cooool Kids ;)", "Level 3: Advanced", "Multiple Choice Questions (MCQ)", "Science");
        m.addQuiz("Tech Whiz Kids", "Level 3: Advanced", "Multiple Choice Questions (MCQ)", "Technology");
        m.addQuiz("Innovator's Challenge", "Level 3: Advanced", "Multiple Choice Questions (MCQ)", "Engineering");
        m.addQuiz("Math Mastermind", "Level 3: Advanced", "Multiple Choice Questions (MCQ)", "Mathematics");
        m.addQuiz("Science Brain Busters", "Level 4: Genius", "Multiple Choice Questions (MCQ)", "Science");
        m.addQuiz("Tech Breakers", "Level 4: Genius", "Multiple Choice Questions (MCQ)", "Technology");
        m.addQuiz("Future Builders", "Level 4: Genius", "Multiple Choice Questions (MCQ)", "Engineering");
        m.addQuiz("EAsY-pEaSY", "Level 4: Genius", "Multiple Choice Questions (MCQ)", "Mathematics");
    }

  
    Scanner s = new Scanner(System.in);
    public void userCreateQuiz() {
        System.out.println("Quiz " + (numOfQuizCreated + 1) + ":");
        System.out.print("Title - ");
        String title = s.nextLine();
        System.out.print("Description - ");
        String description = s.nextLine();
        System.out.print("Content - ");
        String content = s.nextLine();
        System.out.print("Theme - ");
        String theme = s.nextLine();

        m.addQuiz(title, description, content, theme);
        numOfQuizCreated++;

        System.out.println();
        DisplayCreatedQuizNo();
        
    }

    
    public void DisplayCreatedQuizNo() {
        System.out.println("Total Number of Quizzes Created: " + numOfQuizCreated);
        
    }

    class Quiz {
        
        String title;
        String description;
        String content;
        String theme;

        public Quiz(String title, String description, String content, String theme) {
            this.title = title;
            this.description = description;
            this.content = content;
            this.theme = theme;
        }

        @Override
        public String toString() {
            return "Quiz " + (m.quizzes.indexOf(this) + 1) + ":" +
                   "\nTitle - " + title +
                   "\nDescription - " + description +
                   "\nContent - " + content +
                   "\nTheme - " + theme + "\n";
        }
        
    }

    class MakeQuizzes {
        
        ArrayList<Quiz> quizzes;

        MakeQuizzes() {
            quizzes = new ArrayList<>();
        }

        public void addQuiz(String title, String description, String content, String theme) {
            Quiz quiz = new Quiz(title, description, content, theme);
            quizzes.add(quiz);
        }

        
        public void displayQuizzes() {
            
            for (Quiz quiz : quizzes) {
                System.out.println(quiz);
            }
            
        }
    }


    
}