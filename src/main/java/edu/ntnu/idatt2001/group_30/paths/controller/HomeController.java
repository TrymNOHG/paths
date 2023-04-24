package edu.ntnu.idatt2001.group_30.paths.controller;

import edu.ntnu.idatt2001.group_30.paths.view.HelpView;
import edu.ntnu.idatt2001.group_30.paths.view.NewGameView;

public class HomeController extends Controller {

    public HomeController() {
        super(HelpView.class, NewGameView.class);
    }
}
