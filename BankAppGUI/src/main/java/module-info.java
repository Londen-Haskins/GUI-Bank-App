module com.bankappgui.bankappgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.bankappgui.bankappgui to javafx.fxml;
    exports com.bankappgui.bankappgui;
    exports com.bankappgui.bankappgui.models;
    opens com.bankappgui.bankappgui.models to javafx.fxml;
    exports com.bankappgui.bankappgui.controllers;
    opens com.bankappgui.bankappgui.controllers to javafx.fxml;
}