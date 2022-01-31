package edu.ithaca.dturnbull.bank;

public class BankAccount {

    private String email;
    private double balance;
    private String domain;

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
     * @param amount
     * @return true if the amount is positive and has two decmial points or less
    */
    public boolean isAmountValid(double amount){
        
        String decimalCheck = String.valueOf(amount);

        if (amount < 0){
            return false;
        }
        else if (decimalCheck.contains(".")){
            boolean request = false;
            if(decimalCheck.length() - 3 == decimalCheck.indexOf(".")){
                request = true;
            }
            else if(decimalCheck.length() - 2 == decimalCheck.indexOf(".")){
                request = true;
            }
            else if (decimalCheck.length() - 3 != decimalCheck.indexOf(".")){
                request = false;
            }
            return request;
        }
        else{
            return true;
        }
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * if the amount is negative alert, invaild withdrawl amount "please enter a positive number"
     * if the amount is larger than the balance alert, "not enough money"
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

       
        

        int lengthOfEmail = email.length();
        int atSymbol = email.indexOf("@");
        int dashSymbol =  email.indexOf("-");
        int dotSymbol = email.indexOf(".");
        
        if (atSymbol == -1){
            return false;
        }
        String[] parts = email.split("@");
        String suffix = parts[1]; 

        if (atSymbol == -1 || atSymbol == 0 || atSymbol == lengthOfEmail - 1){
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
        else if(suffix.indexOf(".") == -1){
            return false;
        }
        else{
            return true;
        }

    }
    public static void main(String[] args) {
         BankAccount.isEmailValid("");
    }
}

/**this is a comment */