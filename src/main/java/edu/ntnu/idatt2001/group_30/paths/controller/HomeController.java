package edu.ntnu.idatt2001.group_30.paths.controller;

import javafx.event.ActionEvent;

public class HomeController extends Controller {
    public void startNewGame(ActionEvent event) {};

    public void showSettings(ActionEvent event) {};

    public void showHelp(ActionEvent event) {
        STAGE_MANAGER.setCurrentView("Help");
    };

}
