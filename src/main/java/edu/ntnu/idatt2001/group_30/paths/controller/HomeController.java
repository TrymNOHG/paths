package edu.ntnu.idatt2001.group_30.paths.controller;

import edu.ntnu.idatt2001.group_30.paths.view.views.CreatePlayerView;
import edu.ntnu.idatt2001.group_30.paths.view.views.HelpView;
import edu.ntnu.idatt2001.group_30.paths.view.views.LoadGameView;
import edu.ntnu.idatt2001.group_30.paths.view.views.PlaythroughView;

/**
 * This class is used to control the HomeView.
 * @author Nicolai H. Brand, Trym H. Gudvangen.
 *
 */
public class HomeController extends Controller {

    /**
     * Creates a new HomeController.
     */
    public HomeController() {
        super(HelpView.class, LoadGameView.class, CreatePlayerView.class, PlaythroughView.class);
    }

    /**
     * Checks if the PlaythroughView is in the view stack.
     * If it is, it means the user has started a game and can continue it.
     * @return True if the user can continue a game, false otherwise.
     */
    public boolean canContinueAGame() {
        return STAGE_MANAGER.getViewStack().stream().anyMatch(view -> view instanceof PlaythroughView);
    }
}
