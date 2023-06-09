package edu.ntnu.idatt2001.group_30.paths.view.views;

import edu.ntnu.idatt2001.group_30.paths.controller.HelpController;
import edu.ntnu.idatt2001.group_30.paths.controller.StageManager;
import edu.ntnu.idatt2001.group_30.paths.view.components.common.DefaultButton;
import edu.ntnu.idatt2001.group_30.paths.view.components.common.DefaultText;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * The Help page for the application.
 * This page contains information on how to play the game.
 * It also contains buttons for going back to the home page.
 * @author Nicolai H. Brand.
 */
public class HelpView extends View<VBox> {

    private final HelpController controller = new HelpController();

    /**
     * Creates the help page.
     */
    public HelpView() {
        super(VBox.class);
        VBox parent = getParentPane();
        parent.setAlignment(Pos.TOP_CENTER);
        parent.setSpacing(20);
        parent.setPadding(new javafx.geometry.Insets(50));

        add(DefaultText.big("Help"));
        add(helpText());
        addAll(getButtons());
    }

    /**
     * Creates a scroll pane containing the help text.
     * @return A scroll pane containing the help text.
     */
    private Node helpText() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.prefWidth(600);
        String howToPlay =
            """
                New game:
                
                First you must create your own player. You can customize your outfit and enter your name. If you press the Stats button you can enter in your starting stats. If you press the Goals button you can set the goals for the play-trough. Once you are happy with your player, click continue.
                
                Then you need to load or create a new story by pressing the respective buttons. After a story has been selected or created, click Start game.
                
                During a play through, you will find the current passage to the left of the screen with its links to the bottom. In order to make a turn in the game you must press a link. A dialog pop-up will appear if you win or lose the game. If you want to restart a game or go back to home, you can press the restart and or home buttons in the top right corner.
                """;
        Text help = DefaultText.medium(howToPlay);
        help.wrappingWidthProperty().bind(scrollPane.widthProperty().multiply(0.85));
        scrollPane.setContent(help);

        return scrollPane;
    }

    /**
     * Creates the buttons for the help page.
     * The buttons are for going back to the home page and going back to the previous page.
     * The previous page is the page the user was on before going to the help page.
     * @return A list of buttons for the help page.
     */
    private List<Node> getButtons() {
        List<Node> buttons = new ArrayList<>();
        add(DefaultButton.medium("Home", controller.goTo(HomeView.class)));
        add(
            DefaultButton.medium("Go back to " + StageManager.getInstance().getPreviousViewName(), controller.goBack())
        );
        return buttons;
    }
}
