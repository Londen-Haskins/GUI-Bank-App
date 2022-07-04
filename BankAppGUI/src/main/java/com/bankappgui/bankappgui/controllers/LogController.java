package com.bankappgui.bankappgui.controllers;

import com.bankappgui.bankappgui.GlobalData;
import com.bankappgui.bankappgui.View;
import com.bankappgui.bankappgui.ViewSwitch;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import javax.swing.*;

public class LogController {

    @FXML private ListView listView;
    ObservableList<String> transactionLog = FXCollections.observableArrayList();

    // Grab a reference to the GlobalData singleton
    private GlobalData globalData = GlobalData.getInstance();

    @FXML
    public void initialize(){
        transactionLog = globalData.getLog().getLog();
        listView.setItems(transactionLog);
        globalData.getLog().showLog();
    }

    public void makeReport() throws IOException {
        globalData.getLog().printLog();
    }

    public void toMenu() throws IOException {
        ViewSwitch.switchTo(View.MENU);
    }


}
