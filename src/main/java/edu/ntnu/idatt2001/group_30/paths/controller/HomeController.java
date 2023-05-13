package edu.ntnu.idatt2001.group_30.paths.controller;

import edu.ntnu.idatt2001.group_30.paths.view.CreatePlayerView;
import edu.ntnu.idatt2001.group_30.paths.view.HelpView;
import edu.ntnu.idatt2001.group_30.paths.view.NewGameView;

/**
 * This class is used to control the HomeView.
 * @author Nicolai H. Brand, Trym H. Gudvangen.
 *
 */
public class HomeController extends Controller {

    public HomeController() {
        super(HelpView.class, NewGameView.class, CreatePlayerView.class);
    }
}
