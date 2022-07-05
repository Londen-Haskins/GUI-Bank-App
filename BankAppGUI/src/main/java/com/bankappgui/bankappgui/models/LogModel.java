package com.bankappgui.bankappgui.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.*;

public class LogModel {
    private ObservableList<String> log;

    public LogModel(){
        this.log = FXCollections.observableArrayList();
    }

    public ObservableList<String> getLog() {
        return log;
    }

    public void setLog(ObservableList<String> log) {
        this.log = log;
    }

    public void logDeposit(double amount, double bal, double total){
        this.log.add("Old Balance: ($"+bal+")  Deposit of $"+amount+"   New Balance: ($"+total+")\n");
    }

    public void logWithdraw(double amount, double bal, double total){
        this.log.add("Old Balance: ($"+bal+")  Withdraw of $"+amount+"   New Balance: ($"+total+")\n");
    }

    public void showLog(){
        System.out.println(Arrays.toString(this.log.toArray()).replace("[","").replace("]","").replace(",",""));
        System.out.println("Log has been shown");
    }

    public void printLog(String nName) throws IOException {
        nName = nName.concat(".txt");

        FileWriter writer = new FileWriter(nName);
        writer.write("Transaction Log for 'Praise The Sun Banking'\n");
        writer.write("-----------------------------------------------------\n");
        for(String str: this.log) {
            writer.write(str);
        }
        writer.close();
        System.out.println("Successfully printed transaction log to output file titled 'TransactionLog.txt'");
    }

    //Function that will read an input log and update current log along with account balance
    public double readLog(File inFile) {
        String oldBString, newBString, amtString;
        int oldBIndex, amtIndex, newBIndex, temp1, temp2, temp3;   //Hold string index of values
        Boolean logCheck1 = false, logCheck2 = false, logCheck3 = false; //Bools to check validness of transaction log
        double importBal;

        //Record value after 1st '$', find 'D' or 'W' to determine transaction type, record value after 2nd '$'
        double newBal = 00.00;
        try (BufferedReader br = new BufferedReader(new FileReader(inFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                //Check if file is a certified transaction log
                if (line.contains("Transaction Log for 'Praise The Sun Banking")) {
                    logCheck1 = true;
                }
                if (line.contains("-----------------------------------------------------")) {
                    logCheck2 = true;
                }

                if (logCheck3) {
                    //Read the balances
                    oldBIndex = line.indexOf("$");
                    temp1 = line.indexOf(")");
                    amtIndex = line.indexOf("$", oldBIndex + 1);
                    temp2 = line.indexOf(" ", amtIndex);
                    newBIndex = line.lastIndexOf("$");
                    temp3 = line.lastIndexOf(")");
                    oldBString = line.substring(oldBIndex + 1, temp1);
                    amtString = line.substring(amtIndex + 1, temp2);
                    newBString = line.substring(newBIndex + 1, temp3);

                    //Convert string values to doubles
                    double oldBal = Double.parseDouble(oldBString);
                    newBal = Double.parseDouble(newBString);
                    double amt = Double.parseDouble(amtString);


                    //Determine transaction type
                    if (line.indexOf('D') > -1) {
                        this.log.add("Old Balance: ($" + oldBal + ")  Deposit of $" + amt + "   New Balance: ($" + newBal + ")\n");
                    } else if (line.indexOf('W') > -1) {
                        this.log.add("Old Balance: ($" + oldBal + ")  Withdrawal of $" + amt + "   New Balance: ($" + newBal + ")\n");
                    }
                }
                //If the file has the valid header of a transaction log
                if(logCheck1 && logCheck2){
                    logCheck3 = true;
                }

            }
            //If file wasn't a certified transaction log display error
            if (!logCheck1 && !logCheck2) {
                System.out.println("The entered file was not a valid transaction log");
            }
            else if(logCheck1 && logCheck2){
                //Confirm successful import of log
                System.out.println("Successfully imported transaction log");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Return the input file current balance
        importBal = newBal;
        return importBal;
    }
}
