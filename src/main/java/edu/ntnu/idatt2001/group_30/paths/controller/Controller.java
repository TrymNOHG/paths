package edu.ntnu.idatt2001.group_30.paths.controller;

/**
 * The class Controller is the superclass of all controllers.
 * A Controller is responsible for managing one specific view.
 * Every controller has a reference to the StageManager singleton.
 *
 * @author Nicolai H. Brand.
 */
public class Controller {
    protected final StageManager STAGE_MANAGER = StageManager.getInstance();
}
