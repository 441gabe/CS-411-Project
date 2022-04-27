package scooterLogin;
import java.util.*;
import java.io.*;

public class loginPrompt{
    public static void main(String[] args){

        /*
        ////////////////////////////////////////////////////////////////
        FOR TESTING PURPOSES ONLY, HASH TABLE TO READ FROM
        */
        //TODO: MAKE THIS IN A DATABASE FORMAT
        Hashtable<String, Object> ht1 = new Hashtable<>();
        user user1 = new user("username1","Henry", "Smith", "12345","hello@gmail.com", "111");
        ht1.put("user1", user1);

        System.out.println(ht1.get("user1"));
        System.out.println(user1.getUserName());
    }



    public void loginMethod(Hashtable ht1){
        String userInput = "";
        Scanner consoleInputScanner = new Scanner(System.in);

        while(userInput.equals("exit") == false){
            // main menu for now i guess

            /*
            input username from the user, if the username doesn't match with any known usernames,
                prompt the user asking if they want to make a new user
            */
            
            
            System.out.println("Input 'exit' to exit the program anytime.\nPlease enter your username to continue: ");
            userInput = consoleInputScanner.nextLine().toLowerCase();

            //JUST FOR TESTING TODO: REMOVE THIS LINE
            System.out.println("input recieved: " + userInput);


            if(ht1.containsKey(userInput)){
                System.out.println("PLEASE ENTER PASSWORD");
                //TODO: COMPLETE THIS CODE

                //GET PASSWORD FROM THE USER
                //IF PASSWORD IS CORRECT, GO TO MAIN RUNFILE OR SOMETHING LIKE THAT
            } else if(userInput.equals("register")){
                //TODO: CONNECT THIS TO THE REGISITRATION METHOD
                //run thing that registers a new account
                //then go to the same location that the first if statement would go
                user newUser = register();
                ht1.put(newUser.getUserName(), newUser);

                //enter another runfile like login
                
            } else{
                System.out.println("No current accounts match your input.\nTry again or input 'register' to make a new account");
            }



            //END OF WHILE LOOP (IK THIS IS REALLY OBVIOUS BUT ITS HELPFUL TO SEE IT BIGGER)
        }
        consoleInputScanner.close();
    }

    public static user register(){
        // make sure to use sleep command so the user can have time to read stuff
        //TODO: complete this section
        //TODO: if i have time make a basic af UI with the inbuilt UI stuff


        //basic return thing so VSC isn't mad at me for the time being
        user user1 = new user("username1","Henry", "Smith", "12345","HSmith@gmail.com", "password");
        return user1;
    }
}