import java.util.Scanner;

public class Main {

	public static void main(String[] args) {	
	    
        Customer cust1 = new Customer("Null", "Null", "Null", "Null", "Null", "Null", "Null", 0);
        Scooter scoot1 = new Scooter("Null", "Null", "Null", "Null", "Null", "Null", "Null", 0);
        
		Scanner scan = new Scanner(System.in);
        System.out.println("Customer or Admin (1 or 2)");
        int choice = scan.nextInt();
        
        if (choice == 1) {
        	System.out.println("Login or Register (1 or 2)");
            int Cust_Choice = scan.nextInt();
            
            if (Cust_Choice == 2) {
                cust1.register();
                System.out.println("Type 1 to log in.");
                Cust_Choice = scan.nextInt();
            }
            if (Cust_Choice == 1) {
            	boolean login = cust1.loginMethod();
                while (login){
                	Scanner scans = new Scanner(System.in);
                    System.out.println("Type '1' to reserve a scooter; Type '2' to return a scooter; Type 3 to logout");
                    int ans = scans.nextInt();
                	if (ans == 1){
                    cust1.reserveScooter();
                    System.out.println("Scooter reserved successfully");
                    } 
                    else if (answer == 2) {
                    	cust1.returnScooter();
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
                      
            }
        }
        else if (choice == 2){
        	boolean login = scoot1.loginMethod();
        	while (login) {
        		Scanner scans = new Scanner(System.in); 
        		System.out.println("Type '1' to track a scooter ; Type '2' to logout"); 
        		int ans = scans.nextInt(); 

        		if (ans== 1) { 
        			scoot1.trackScooter(); 
        		}
            else if(ans == 2) {
        			login = false;
        		}
        		else { 
        			System.out.println("Invalid selection, try again"); 
        		} 
        	}
        	System.out.println("You have logged out!");
        }
        
	}

}
