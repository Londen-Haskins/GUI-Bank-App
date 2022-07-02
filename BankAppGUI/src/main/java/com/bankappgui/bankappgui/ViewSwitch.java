package com.bankappgui.bankappgui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class ViewSwitch {

    private static Scene scene;

    public static void setScene(Scene scene){
        ViewSwitch.scene = scene;
    }

    public static void switchTo(View view) throws IOException {
        Parent root = FXMLLoader.load(ViewSwitch.class.getResource(view.getFileName()));
        scene.setRoot(root);
    }
}
