package edu.ithaca.dturnbull.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }


    public static boolean isEmailValid(String email){

        String[] parts = email.split("@");
        String prefix = parts[0];
        String suffix = parts[1];

        int lengthOfEmail = email.length();
        int atSymbol = email.indexOf("@");
        int dashSymbol =  email.indexOf("-");
        int dotSymbol = email.indexOf(".");
        
        if (atSymbol == -1 || atSymbol == 0 || atSymbol == lengthOfEmail - 1){
            return false;
        }
        else if(atSymbol > 2){
            return false;
        }
        else if(!suffix.contains(".")){
            return false;
        }
        else if(email.startsWith(".")){
            return false;
        }
        else if(email.contains("#")){
            return false;
        }

        else if (dashSymbol == atSymbol -1){
            return false;
        }

        else if(dotSymbol == dotSymbol -1){
            return false;
        }
        else if(email.contains("..")){
            return false;
        }
        else if(lengthOfEmail - dotSymbol < 3){
            return false;
        }
        else{
            return true;
        }

    }
}