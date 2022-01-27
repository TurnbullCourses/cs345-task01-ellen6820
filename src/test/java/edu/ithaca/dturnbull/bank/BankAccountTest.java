package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance(), 0.001);
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address
        assertFalse( BankAccount.isEmailValid(""));         // empty string
        assertFalse(BankAccount.isEmailValid("ellen-@.com")); // symbol adjacent to @ is invalid
        assertFalse(BankAccount.isEmailValid("e..a.chapman@gmail.com")); // double dots symbol is invalid in an email address
        assertFalse(BankAccount.isEmailValid("o#dd@gmail.com")); //invaild symbol
        assertFalse(BankAccount.isEmailValid(".ea.chapman11@gmail.com")); //invaild opening symbol
        assertFalse(BankAccount.isEmailValid("eac@hotmail.c")); //invaild domain
        assertFalse(BankAccount.isEmailValid("eac@#otmail.com")); //invalid symbol in domain
        assertFalse(BankAccount.isEmailValid("eac@aol")); //invalid domain   
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}