package com.bankappgui.bankappgui.controllers;

import com.bankappgui.bankappgui.GlobalData;
import com.bankappgui.bankappgui.View;
import com.bankappgui.bankappgui.ViewSwitch;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Observer;

public class AccountController {
    @FXML private Text currentBalField;
    @FXML private Label amtHeader;
    @FXML private TextField amtBox;
    @FXML private Button amtButton;

    double curBal;
    int opType; //Determines the operation type. 0 for deposits and 1 for withdrawals

    // Grab a reference to the GlobalData singleton
    private GlobalData globalData = GlobalData.getInstance();

    @FXML
    public void initialize(){
        amtBox.setVisible(false);
        amtHeader.setVisible(false);
        amtButton.setVisible(false);
        curBal = globalData.getAccount().getBalance();
        currentBalField.setText("Current Balance: $"+curBal);
    }

    public void depositAmt(ActionEvent actionEvent) {
        amtBox.setVisible(true);
        amtHeader.setText("Enter the deposit amount");
        amtHeader.setVisible(true);
        amtButton.setVisible(true);
        opType = 0;
    }

    public void withdrawAmt(ActionEvent actionEvent) {
        amtBox.setVisible(true);
        amtHeader.setText("Enter the withdrawal amount");
        amtHeader.setVisible(true);
        amtButton.setVisible(true);
        opType = 1;
    }

    public void processOperation(){
        double amtBoxValue;

        amtBoxValue = Double.parseDouble(amtBox.getText());

        //Deposit operation
        if(opType == 0){
            //If the deposit was valid
            if(globalData.getAccount().deposit(amtBoxValue)){
                globalData.getLog().logDeposit(amtBoxValue,curBal,globalData.getAccount().getBalance());
                curBal = globalData.getAccount().getBalance();
                currentBalField.setText("Current Balance: $"+curBal);
            }
            else{

            }
        }
        else{
            //If the withdrawal was valid
            if(globalData.getAccount().withdraw(amtBoxValue)){
                globalData.getLog().logWithdraw(amtBoxValue,curBal,globalData.getAccount().getBalance());
                curBal = globalData.getAccount().getBalance();
                currentBalField.setText("Current Balance: $"+curBal);
            }
            else{

            }
        }
        amtBox.setVisible(false);
        amtHeader.setVisible(false);
        amtButton.setVisible(false);
    }

    public void toMenu() throws IOException {
        ViewSwitch.switchTo(View.MENU);
    }
}
