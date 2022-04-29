package scooter;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;
import java.io.*;

public class Main {

	public static void main(String[] args) {

        Hashtable<String, Object> userTable = new Hashtable<>();

        //FILE READING
        FileReader reader = new FileReader("users.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        while((line = bufferedReader.readLine()) != null) {

            //LINE TO READ FROM
            String[] tempLine = line.split(" ");

            /*
            TAKES EACH LINE OF THE HASH TABLE, MAKES A NEW USER OUT OF IT,
            AND PUTS IT IN A HASH TABLE FOR THE OTHER METHODS TO USE
            */
            for(int i = 0; i < tempLine.length; i++){
                user tempUser = new user(tempLine[0], tempLine[1], tempLine[2], tempLine[3], tempLine[4], tempLine[5], tempLine[6]);
                userTable.put(tempLine[0], tempUser); 
            }
        }
        reader.close();
        
		Scanner scan = new Scanner(System.in);
        System.out.println("Customer or Admin");
        String choice = scan.nextLine().toLowerCase();
        
        if (choice.equals("customer")) {

            //CALLING LOGIN METHOD
            user currentUser = loginPrompt.loginMethod(userTable);


            //change this so login is a user object

            boolean login = true;
            while (login){
                Scanner scans = new Scanner(System.in);
                System.out.println("Type '1' to reserve a scooter; Type '2' to return a scooter; Type 3 to logout");
                int ans = scans.nextInt();
                if (ans == 1){

                    //THIS IS A PLACEHOLDER
                    currentUser.reserveScooter();
                    System.out.println("Scooter reserved successfully");

                    //THE FORMAT SHOULD LOOK LIKE:
                    // SCOOTER.RESERVESCOOTER(USER OBJECT)
                } 
                else if (ans == 2) {
                    currentUser.returnScooter();
                    System.out.println("Scooter returned successfully");
                } 
                else if(ans == 3) {
                    login = false;
                }
                else {
                    System.out.println("Invalid Choice");
                }
            }
            System.out.println("You have logged out");
                      
        
        } else if (choice.equals("admin")){

            //CALLING LOGIN METHOD
        	user currentUser = loginPrompt.loginMethod(userTable);

            if(!currentUser.getAdmin().equals("Admin")){
                System.out.println("Not an admin!");
                break;
            }

            boolean login = true;
        	while (login) {
        		Scanner scans = new Scanner(System.in); 
        		System.out.println("Type '1' to track a scooter ; Type '2' to logout"); 
        		int ans = scans.nextInt(); 

        		if (ans == 1) {
                    //TRACK ALL SCOOTERS
                    //get it to say available/not available 
        			currentUser.trackScooter();
        		}
                else if(ans == 2) {
        			login = false;
        	    }
                else { 
        			System.out.println("Invalid selection, try again"); 
        		} 
        	}

        }
        //CODE TO WRITE TO A FILE ONCE WE'RE DONE AND THE USER WANTS TO EXIT
        FileWriter writer = new FileWriter("users.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        Set<String> setOfKeys = userTable.keySet();

        for(String key : setOfKeys){
            user tempUser = (user) userTable.get(key);
            bufferedWriter.write(
                key + " " + 
                tempUser.getUserName() + " " + 
                tempUser.getFirstName() + " " +
                tempUser.getLastName() + " " +
                tempUser.getPhone() + " " +
                tempUser.getEmail() + " " +
                tempUser.getPassword() + " " +
                tempUser.getAdmin()
            );
            bufferedWriter.newLine();
        }
        bufferedWriter.close();

        System.out.println("You have logged out!");
        break;
        
	}

}
