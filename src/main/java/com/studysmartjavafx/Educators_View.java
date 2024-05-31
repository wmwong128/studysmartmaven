package com.studysmartjavafx;

import java.time.LocalDate;
import java.util.Scanner;
public class Educators_View {

         /* 
        1. Sign up
        2. Login
        3. View profile (Display name, role, coordinates, No. of quizzes and events created)
        4. View functions
        -Create events
        -Create Quizzes
        5. View ongoing events 
        */
    
    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);
        EventManager event = new EventManager();
        
        //Student view & register event
        event.m.displayEvents(LocalDate.MAX);
        
        
        
        
        
        
        //Generics (Create own events)
        event.createOwnEvent();
        event.m.displayEvents();
        
        /*ArrayList<CreateEventPage<String>> First10EventList = new ArrayList<>();
        for(int i=0; i<10; i++){
            First10EventList.add(new EventManager<>());
            First10EventList.get(i).createOwnEvent(10);
        } */

        
        //Accept users' input
        while(true){
           System.out.print("\nCreate an Event? Yes[1]/No[0]: ");
           int response = s.nextInt();
           if(response==1){
                         System.out.println();
                        event.userCreateEvent();
        }
           else{
               break;
        }
        }

//Generics (Create own quiz)

        QuizManager quiz1 = new QuizManager();
        quiz1.createOwnQuiz();
        quiz1.m.displayQuizzes();
        

        //Accept users' input
        while(true){
           System.out.print("\nCreate a Quiz? Yes[1]/No[0]: ");
           int response = s.nextInt();
           if(response==1){
                         System.out.println();
                        quiz1.userCreateQuiz();
        }
           else{
               break;
        }
        }
        System.out.println("");
        event.DisplayCreatedEventNo();
        quiz1.DisplayCreatedQuizNo();
          }
    
}
