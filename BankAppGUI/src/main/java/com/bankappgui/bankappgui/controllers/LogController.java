package com.bankappgui.bankappgui.controllers;

import com.bankappgui.bankappgui.GlobalData;
import com.bankappgui.bankappgui.View;
import com.bankappgui.bankappgui.ViewSwitch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;


public class LogController {

    @FXML private ListView listView;
    @FXML private Label logBoxHeader;
    @FXML private TextField logNameBox;
    @FXML private Button logEnter;
    @FXML private Button printEnter;
    ObservableList<String> transactionLog = FXCollections.observableArrayList();

    // Grab a reference to the GlobalData singleton
    private GlobalData globalData = GlobalData.getInstance();

    @FXML
    public void initialize(){
        logBoxHeader.setVisible(false);
        logNameBox.setVisible(false);
        logEnter.setVisible(false);
        printEnter.setVisible(false);
        transactionLog = globalData.getLog().getLog();
        listView.setItems(transactionLog);
        globalData.getLog().showLog();
    }

    public void getPrintName() throws IOException {
        logBoxHeader.setVisible(true);
        logNameBox.setVisible(true);
        printEnter.setVisible(true);

        logBoxHeader.setText("Please enter the desired name of the log file:");

    }

    public void makeReport() throws IOException {
        String name = logNameBox.getText();
        globalData.getLog().printLog(name);

        logNameBox.setVisible(false);
        printEnter.setVisible(false);
        logBoxHeader.setText("Log file named "+name+ " successfully made!");
    }

    public void getReport() throws IOException {

        //Set controls to visible
        logBoxHeader.setVisible(true);
        logNameBox.setVisible(true);
        logEnter.setVisible(true);
        printEnter.setVisible(false);


        //Ask user for input file containing transaction log
        logBoxHeader.setText("Please enter the name of the log file:");

    }

    public void checkFileName(){
        String fName = logNameBox.getText();
        fName = fName.concat(".txt");

        //Check if user entered a name
        File inFile = new File(fName);
        //Check if file exists
        if(!inFile.exists()){
            logBoxHeader.setText("File does not exist. Enter name of log file:");
        }
        else{
            //Import the log and update account balance
            logBoxHeader.setText("Successful import of log");
            logNameBox.setVisible(false);
            logEnter.setVisible(false);
            globalData.getAccount().setBalance(globalData.getLog().readLog(inFile));
        }
    }

    public void toMenu() throws IOException {
        ViewSwitch.switchTo(View.MENU);
    }


}
