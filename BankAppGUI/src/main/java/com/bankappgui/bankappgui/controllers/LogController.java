package com.bankappgui.bankappgui.controllers;

import com.bankappgui.bankappgui.GlobalData;
import com.bankappgui.bankappgui.View;
import com.bankappgui.bankappgui.ViewSwitch;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogController {

    // Grab a reference to the GlobalData singleton
    private GlobalData globalData = GlobalData.getInstance();

    @FXML
    public void initialize(){

    }

    public void toMenu() throws IOException {
        ViewSwitch.switchTo(View.MENU);
    }


}
