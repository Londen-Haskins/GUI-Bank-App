package com.bankappgui.bankappgui.models;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableNumberValue;
import javafx.beans.value.ObservableValue;

import java.math.BigDecimal;

public class AccountModel {
    private DoubleProperty balance = new SimpleDoubleProperty();

    public AccountModel(double funds){
        this.balance.set(funds);
    }

    //Get Account balance
    public double getBalance() {
        return balance.get();
    }

    public DoubleProperty balanceProperty() {
        return balance;
    }

    //Set Account balance
    public void setBalance(BigDecimal balance) {
        ObservableDoubleValue nBalance = (ObservableDoubleValue) balance;
        this.balance = (SimpleDoubleProperty) nBalance;
    }

    //Deposit BigDecimal value to Account balance
    public boolean deposit(double amount) {
        boolean flag = false;
        if (amount > 0) {
            double newBal = this.balance.doubleValue() + amount;
            this.balance = new ReadOnlyDoubleWrapper(newBal);
            flag = true;
        } else {
            System.out.println("Invalid deposit amount");
        }
        return flag;
    }

    //Withdraw BigDecimal value from Account balance
    public boolean withdraw(double amount) {
        boolean flag = false;

        if (amount > 0 && balance.doubleValue() >= amount) {
            double newBal = this.balance.doubleValue() - amount;
            this.balance = new ReadOnlyDoubleWrapper(newBal);
            System.out.println("Amount of $" + amount + " withdrew from account");

            flag = true;
        } else if (balance.doubleValue() < amount) {
            System.out.println("Account balance is insufficient");
        } else if (amount <= 0) {
            System.out.println("Invalid withdrawal amount");
        }
        return flag;
    }

}
