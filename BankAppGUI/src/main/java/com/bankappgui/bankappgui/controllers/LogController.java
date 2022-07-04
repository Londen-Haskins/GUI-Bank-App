package com.bankappgui.bankappgui.controllers;

import com.bankappgui.bankappgui.GlobalData;
import com.bankappgui.bankappgui.View;
import com.bankappgui.bankappgui.ViewSwitch;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

public class LogController {

    @FXML private ListView listView;

    // Grab a reference to the GlobalData singleton
    private GlobalData globalData = GlobalData.getInstance();

    @FXML
    public void initialize(){
        ObservableList<String> transactionLog = globalData.getLog().getLog();
        listView.setItems(transactionLog);
        globalData.getLog().showLog();
    }

    public void toMenu() throws IOException {
        ViewSwitch.switchTo(View.MENU);
    }


}
