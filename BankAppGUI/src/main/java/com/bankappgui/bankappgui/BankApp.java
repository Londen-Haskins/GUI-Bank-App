package com.bankappgui.bankappgui;

import com.bankappgui.bankappgui.controllers.AccountController;
import com.bankappgui.bankappgui.controllers.LogController;
import com.bankappgui.bankappgui.models.AccountModel;
import com.bankappgui.bankappgui.models.LogModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class BankApp extends Application {

    private GlobalData globalData = GlobalData.getInstance();

    @Override
    public void start(Stage stage) throws IOException {
        // Create the user account
        globalData.setAccount(new AccountModel(56.00));

        var scene = new Scene(new Pane());
        ViewSwitch.setScene(scene);
        ViewSwitch.switchTo(View.MENU);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}