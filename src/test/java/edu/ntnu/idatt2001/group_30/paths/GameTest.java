package edu.ntnu.idatt2001.group_30.paths;

import edu.ntnu.idatt2001.group_30.goals.Goal;
import edu.ntnu.idatt2001.group_30.goals.GoldGoal;
import edu.ntnu.idatt2001.group_30.goals.HealthGoal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Nested
    class An_instantiated_Game_object {
        Game game;
        Player player;
        Passage opening;
        Passage attackPassage;

        Story story;
        List<Goal> goals;

        @BeforeEach
        void setup() {
            player = new Player("Ola Nordmann", 10, 0, 35);
            opening = new Passage("Escape the room", "You are in a room. There is a door to the north.");
            story = new Story("Escape from Callum", opening);

            Passage attackPassage = new Passage("Attack", "You attack the troll");
            // save the attackPassage object so that we can use it for equality check in the test for the go method
            this.attackPassage = attackPassage;
            Passage fleePassage = new Passage("Flee", "You flee from the troll");
            story.addPassage(attackPassage);
            story.addPassage(fleePassage);

            GoldGoal goldGoal = new GoldGoal(50);

            HealthGoal healthGoal = new HealthGoal(100);
            goals = new ArrayList<>();
            goals.add(goldGoal);
            goals.add(healthGoal);

            game = new Game(player, story, goals);
        }

        @Test
        void can_be_constructed() {
            // The game object is instantiated in the setup method that is called before each test
            assertNotNull(game);
        }

        @Test
        void cannot_be_constructed_if_any_parameter_is_null() {
            assertThrows(IllegalArgumentException.class, () -> new Game(null, story, goals));
            assertThrows(IllegalArgumentException.class, () -> new Game(player, null, goals));
            assertThrows(IllegalArgumentException.class, () -> new Game(player, story, null));
        }

        @Test
        void can_get_player() {
            // The player object from the class is created in the setup method and passed to the Game's constructor
            assertEquals(player, game.getPlayer());
        }

        @Test
        void can_get_story() {
            assertEquals(story, game.getStory());
        }

        @Test
        void can_get_goals() {
            assertEquals(goals, game.getGoals());
        }

        @Test
        void can_go_to_passage_based_on_link() {
            // An exact link like this is created in the setup method by calling the addPassage method on the story object
            // passing in the attackPassage object
            Link link = new Link("Attack", "Attack");
            assertEquals(attackPassage, game.go(link));
            assertNotEquals(opening, game.go(link));
        }

    }
}
