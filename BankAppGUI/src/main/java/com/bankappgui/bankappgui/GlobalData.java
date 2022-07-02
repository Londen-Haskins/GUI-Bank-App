package com.bankappgui.bankappgui;

import com.bankappgui.bankappgui.models.AccountModel;
import com.bankappgui.bankappgui.models.LogModel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.ObjectProperty;

public class GlobalData {
    // Global property to hold the account
    private ObjectProperty<AccountModel> userAccount = new SimpleObjectProperty<>();
    private ObjectProperty<LogModel> userLog = new SimpleObjectProperty<>();

    private static GlobalData ourInstance = new GlobalData();

    public static GlobalData getInstance() {
        return ourInstance;
    }

    private GlobalData() {
    }

    public AccountModel getAccount() {
        return userAccount.get();
    }

    public ObjectProperty<AccountModel> accountProperty() {
        return userAccount;
    }

    public void setAccount(AccountModel account) {
        this.userAccount.set(account);
    }

    public LogModel getLog() {
        return userLog.get();
    }

    public ObjectProperty<LogModel> LogProperty() {
        return userLog;
    }

    public void setLog(LogModel log) {
        this.userLog.set(log);
    }
}
