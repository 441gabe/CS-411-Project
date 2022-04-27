package scooterLogin;

public class user {
    /*
    username is the object name
    first name / last name / phone / password
    */
    /*
    replaceAll on the lines where phone is written is meant to just leave the phone number
        in one universal format
    */

    String userName;
    String firstName;
    String lastName;
    String phone;
    String email;
    String password;

    public user(String inputUserName, String inputFirstName, String inputLastName, String inputPhone, String inputEmail, String inputPassword) {
        userName = inputUserName;
        firstName = inputFirstName;
        lastName = inputLastName;
        password = inputPassword;
        email = inputEmail;
        phone = inputPhone.replaceAll("[^\\d.]", "");
    }

    public void newPassword(String newPass){
        //updates the password in the object, useful for a forgotPassword method
        password = newPass;
    }

    public void editName(String newFirstName, String newLastName){
        //editing first and last name of the user if desired, error checks for blank
        if(!newFirstName.equals("")){
            firstName = newFirstName;
        } else if(!newLastName.equals("")){
            lastName = newLastName;
        }
    }

    public void editPhone(String newPhone){
        //updates the object with a new phone number
        phone = newPhone.replaceAll("[^\\d.]", "");
    }

    public void editUsername(String newUserName){
        //updates the object with a new username
        userName = newUserName;
    }

    public String getUserName(){
        return userName;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getPhone(){
        return phone;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }

}
