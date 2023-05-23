package edu.ntnu.idatt2001.group_30.paths.model;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2001.group_30.paths.model.goals.Goal;
import edu.ntnu.idatt2001.group_30.paths.model.goals.GoldGoal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlaythroughTest {

    private Playthrough playthrough;
    private Passage openingPassage;

    @BeforeEach
    void setUp() {
        openingPassage = new Passage("Opening passage", "This is the opening passage");
        Player player = new Player("Player", 10, 20, 30);
        Story story = new Story("My story", openingPassage);
        List<Goal<?>> goals = List.of(new GoldGoal(50));
        Game game = new Game(player, story, goals);

        playthrough = new Playthrough(game);
    }

    @Test
    void testBeginPlaythrough() {
        PlaythroughState state = playthrough.beginPlaythrough();
        assertEquals(PlaythroughState.STUCK, state);
        assertEquals(openingPassage, playthrough.getCurrentPassage());
    }

    @Test
    void testMakeTurn() {
        playthrough.beginPlaythrough();
        Link link = new Link("Link", "Passage123");
        PlaythroughState state = playthrough.makeTurn(link);
        assertEquals(PlaythroughState.STUCK, state);
    }

    @Test
    void testMakeTurnWithNullLink() {
        assertThrows(NullPointerException.class, () -> playthrough.makeTurn(null));
    }
}
