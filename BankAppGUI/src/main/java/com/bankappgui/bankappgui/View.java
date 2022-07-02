package com.bankappgui.bankappgui;

public enum View {
    MENU("menu.fxml"),
    ACCOUNT("account.fxml"),
    LOG("log.fxml");

    private String fileName;

    View(String fileName){
        this.fileName = fileName;
    }

    public String getFileName(){
        return fileName;
    }
}
