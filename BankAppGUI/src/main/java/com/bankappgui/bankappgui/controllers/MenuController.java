package com.bankappgui.bankappgui.controllers;

import com.bankappgui.bankappgui.View;
import com.bankappgui.bankappgui.ViewSwitch;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class MenuController {

    public void checkAcct() throws IOException {
        ViewSwitch.switchTo(View.ACCOUNT);
    }

    public void showLog() throws IOException {
        ViewSwitch.switchTo(View.LOG);
    }

    public void closeApp(){
        Platform.exit();
    }
}