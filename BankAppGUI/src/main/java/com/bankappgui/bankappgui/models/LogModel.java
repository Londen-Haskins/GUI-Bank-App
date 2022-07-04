package com.bankappgui.bankappgui.models;

import javafx.beans.InvalidationListener;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.value.ObservableDoubleValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class LogModel {
    private ObservableList<String> log;

    public LogModel(){
        this.log = new ObservableList<String>() {
            @Override
            public void addListener(ListChangeListener<? super String> listChangeListener) {

            }

            @Override
            public void removeListener(ListChangeListener<? super String> listChangeListener) {

            }

            @Override
            public boolean addAll(String... strings) {
                return false;
            }

            @Override
            public boolean setAll(String... strings) {
                return false;
            }

            @Override
            public boolean setAll(Collection<? extends String> collection) {
                return false;
            }

            @Override
            public boolean removeAll(String... strings) {
                return false;
            }

            @Override
            public boolean retainAll(String... strings) {
                return false;
            }

            @Override
            public void remove(int i, int i1) {

            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<String> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(String s) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends String> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends String> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public String get(int index) {
                return null;
            }

            @Override
            public String set(int index, String element) {
                return null;
            }

            @Override
            public void add(int index, String element) {

            }

            @Override
            public String remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<String> listIterator() {
                return null;
            }

            @Override
            public ListIterator<String> listIterator(int index) {
                return null;
            }

            @Override
            public List<String> subList(int fromIndex, int toIndex) {
                return null;
            }

            @Override
            public void addListener(InvalidationListener invalidationListener) {

            }

            @Override
            public void removeListener(InvalidationListener invalidationListener) {

            }
        };
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

    public void printLog() throws IOException {
        FileWriter writer = new FileWriter("TransactionLog.txt");
        writer.write("Transaction Log for 'Praise The Sun Banking'\n");
        writer.write("-----------------------------------------------------\n");
        for(String str: this.log) {
            writer.write(str);
        }
        writer.close();
        System.out.println("Successfully printed transaction log to output file titled 'TransactionLog.txt'");
    }

    //Function that will read an input log and update current log along with account balance
    public ObservableDoubleValue readLog(File inFile) {
        String oldBString, newBString, amtString;
        int oldBIndex, amtIndex, newBIndex, temp1, temp2, temp3;   //Hold string index of values
        Boolean logCheck1 = false, logCheck2 = false, logCheck3 = false; //Bools to check validness of transaction log
        ObservableDoubleValue importBal = null;

        //Record value after 1st '$', find 'D' or 'W' to determine transaction type, record value after 2nd '$'
        BigDecimal newBal = null;
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

                    //Convert string values to big decimal
                    BigDecimal oldBal = new BigDecimal(oldBString);
                    newBal = new BigDecimal(newBString);
                    BigDecimal amt = new BigDecimal(amtString);


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
        importBal = new ReadOnlyDoubleWrapper(newBal.doubleValue());
        return importBal;
    }
}
