package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance(), 0.001);

        BankAccount bankAccount2 = new BankAccount("try@gmail.com", 700);
        assertEquals(700, bankAccount2.getBalance(), 0.001);
    }

    @Test
    void isAmountValidTest(){
        //Valid Cases
        assertTrue(BankAccount.isAmountValid(10.00)); 
        assertTrue(BankAccount.isAmountValid(1895.63));
        assertTrue(BankAccount.isAmountValid(170)); // no decimals

        //Invalid Cases
        assertThrows(IllegalArgumentException.class, () -> BankAccount.isAmountValid(150.232)); // too many decimals
        assertThrows(IllegalArgumentException.class, () -> BankAccount.isAmountValid(-95.12)); // Negative
        assertThrows(IllegalArgumentException.class, () -> BankAccount.isAmountValid(-50000)); // Negative edge case

    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address
        assertFalse(BankAccount.isEmailValid(""));         // empty string
        
        //prefix tests
        assertFalse(BankAccount.isEmailValid("ellen-@.com")); // symbol adjacent to @ is invalid
        assertFalse(BankAccount.isEmailValid("e..a.chapman@gmail.com")); // double dots symbol is invalid in an email address
        assertFalse(BankAccount.isEmailValid("o#dd@gmail.com")); //invaild symbol
        assertFalse(BankAccount.isEmailValid(".ea.chapman11@gmail.com")); //invaild opening symbol

        //domain tests
        assertFalse(BankAccount.isEmailValid("eac@hotmail.c")); //invaild domain
        assertFalse(BankAccount.isEmailValid("eac@#otmail.com")); //invalid symbol in domain
        assertFalse(BankAccount.isEmailValid("eac@aol")); //invalid domain   
    }

    @Test
    void withdrawTest() throws InsufficientFundsException, IllegalArgumentException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001); //vaild withdrawal
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(500)); // not enough money in the account
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-500)); // negative input 
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(500.555)); // too many decimals
      
    }

    @Test
    void depositTest() throws IllegalArgumentException{
        BankAccount bankAccount = new BankAccount("b@j.com", 200);
        
        bankAccount.deposit(100);
        assertEquals(300, bankAccount.getBalance(), 0.001); // valid deposit
        bankAccount.deposit(189.99);
        assertEquals(489.99, bankAccount.getBalance(), 0.001); // valid deposit
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-500)); // negative input 
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(500.555)); // too many decimals

    }

    @Test 
    void transferTest() throws InsufficientFundsException, IllegalArgumentException{
        BankAccount bankAccount = new BankAccount("m@c.com", 1000);
        
        bankAccount.transfer(50);
        assertEquals(950, bankAccount.getBalance(), 0.001);
        assertThrows(IllegalArgumentException.class, () -> bankAccount.transfer(-500)); // negative input 
        assertThrows(IllegalArgumentException.class, () -> bankAccount.transfer(500.555)); // too many decimals
        assertThrows(InsufficientFundsException.class, () -> bankAccount.transfer(5000));

    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));

        // Test for a valid amount input
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("e@c.com", -400));//invalid amount to withdraw (negative)
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("e@c.com", 400.235));//invalid amount to withdraw (too many decimals)
        
    }

}