package scooter;
import java.util.*;

public class loginPrompt{
    // public static void main(String[] args){

    //     /*
    //     ////////////////////////////////////////////////////////////////
    //     FOR TESTING PURPOSES ONLY, HASH TABLE TO READ FROM
    //     */
    //     //TODO: MAKE THIS IN A DATABASE FORMAT
    //     Hashtable<String, Object> ht1 = new Hashtable<>();
    //     user user1 = new user("username1","Henry", "Smith", "12345","hello@gmail.com", "111");
    //     ht1.put("user1", user1);

    //     loginMethod(ht1);

    //     //System.out.println("Success");
    // }

    public static user loginMethod(Hashtable ht1){
        String userInput = "";
        Scanner consoleInputScanner = new Scanner(System.in);
        boolean exitCondition = false;

        while(!exitCondition){
            // main menu for now i guess

            /*
            input username from the user, if the username doesn't match with any known usernames,
                prompt the user asking if they want to make a new user
            */
            
            
            System.out.println(
                "Input 'exit' to exit the program anytime.\n"
                + "Please enter your username to continue: "
                + "Or type in 'register' to create a new account."
                );
            
            userInput = consoleInputScanner.nextLine().toLowerCase();

            if(userInput.equals("exit")){
                exitCondition = true;
            } 
            else if(ht1.containsKey(userInput)){
                // CASE 1: USER INPUTS A VALID USERNAME
                user currentUser = (user) ht1.get(userInput);

                //password correctness state
                boolean correct = false;
                while(!correct){
                    // NOT SURE WHY VSCODE SAYS THIS ALWAYS EVALUATES TO TRUE, INCORRECT
                    System.out.println("Please enter your password: ");
                    String inputPassword = consoleInputScanner.nextLine();

                    if(currentUser.getPassword().equals(inputPassword)){
                        //RETURNING TO THE MAIN RUNFILE
                        consoleInputScanner.close();
                        return currentUser;
                    } else{
                        System.out.println("Incorrect! Please Try Again");
                    }
                }
            } else if(userInput.equals("register")){
                /*
                RUNS REGISTRATION METHOD THEN PUTS THE NEW USER OBJECT THAT WAS
                    RETURNED FROM REGISTRATION INTO THE EXISTING HASH TABLE
                */

                user newUser = register(ht1);
                ht1.put(newUser.getUserName(), newUser);

                // TESTING CASES
                System.out.println(ht1.toString());
                System.out.println("SUCCESS");
                
                //RETURNING TO THE MAIN RUNFILE
                consoleInputScanner.close();
                return newUser;
                
            } else{
                System.out.println(
                    "No current accounts match your input."
                    + "\nTry again or input 'register' to make a new account" 
                    + "\nOr input 'exit' to exit the program"
                    );
            }



            //END OF WHILE LOOP (IK THIS IS REALLY OBVIOUS BUT ITS HELPFUL TO SEE IT BIGGER)
        }
        
        consoleInputScanner.close();
        return null;
    }

    public static user register(Hashtable ht1){
        Scanner consoleInputScanner = new Scanner(System.in);

        //new variables that will be filled by the user
        String newUsername = "";
        String newFirstName = "";
        String newLastName = "";
        String newPhone = "";
        String newEmail = "";
        String newPassword = "";
        
        // JUST A WELCOME MESSAGE:
        System.out.println(
            "------------------------------" + 
            "\nWelcome to user registration!"
            );

        /*
        ASKS USER FOR A NEW USERNAME
        THEN THE IF STATEMENT CHECKS IF THE USERNAME THAT THE USER WANTS ALREADY EXISTS
        IF THE NEW USERNAME EXISTS ALREADY, THEN ASKS THE USER FOR A DIFFERENT NAME
        */

        boolean validUser = false;
        while(!validUser){
            System.out.println("Please enter your desired username");
            String usernameInput = consoleInputScanner.nextLine();
            if(ht1.containsKey(usernameInput)){
                System.out.println("That username already exists.\nPlease choose a different one.");
            } else{
                newUsername = usernameInput;
                validUser = true;
            }
        }

        // ASKING USER FOR THEIR NAME
        System.out.println("Please enter your first name: ");
        newFirstName = consoleInputScanner.nextLine();

        System.out.println("Please enter your last name: ");
        newLastName = consoleInputScanner.nextLine();

        // ASKING USER PHONE NUMBER
        System.out.println("Please enter your phone number: ");
        newPhone = consoleInputScanner.nextLine();

        // ASKING USER EMAIL, MUST CONTAIN AN '@' TO BE A VALID EMAIL
        boolean validEmail = false;
        while(!validEmail){
            System.out.println("Please enter your email: ");
            newEmail = consoleInputScanner.nextLine();
            if(newEmail.contains("@")){
                validEmail = true;
            } else{
                System.out.println("Invalid email, please try again.");
            }
        }

        // ASKING USER PASSWORD, FOR NOW IT CAN BE ANYTHING THEY WANT
        System.out.println("Please enter your desired password: ");
        newPassword = consoleInputScanner.nextLine();

        consoleInputScanner.close();
        return new user(newUsername, newFirstName, newLastName, newPhone, newEmail, newPassword, "False");
    }
}