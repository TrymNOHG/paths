package edu.ntnu.idatt2001.group_30.paths.view;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Unfinished Help page.
 * @author Nicolai H. Brand.
 */
public class HelpView extends View<BorderPane> {

    public HelpView() {
        super(BorderPane.class);
        add(helpText());
    }

    public Text helpText() {
        String howToPlay =
                """
                \n\n\n\n
                Hvordan spiller jeg Paths?
                Du klikker på spill!
                """;

        Text text = new Text(howToPlay);
        text.setFont(new Font(36));
        return text;
    }

}
