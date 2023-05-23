package edu.ntnu.idatt2001.group_30.paths.model.actions;

/**
 * This class represents a factory for producing Action objects. It, therefore, has methods for instantiating a single
 * object from the information provided.
 *
 * @author Trym Hamer Gudvangen
 */
public class ActionFactory {

    /**
     * This method takes in an ActionType and the action value in order to create an Action object.
     *
     * @param actionType    The type of action, represented as a ActionType enumeration
     * @param actionValue   The action value, given as a String.
     * @return              An action object with the information specified
     */
    public static Action<?> getAction(ActionType actionType, String actionValue) throws IllegalArgumentException {
        return switch (actionType) {
            case GOLD_ACTION -> new GoldAction(Integer.parseInt(actionValue));
            case HEALTH_ACTION -> new HealthAction(Integer.parseInt(actionValue));
            case INVENTORY_ACTION -> new InventoryAction(actionValue);
            case SCORE_ACTION -> new ScoreAction(Integer.parseInt(actionValue));
        };
    }
}
