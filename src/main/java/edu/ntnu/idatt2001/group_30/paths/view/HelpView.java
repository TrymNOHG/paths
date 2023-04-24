package edu.ntnu.idatt2001.group_30.paths.view;

import edu.ntnu.idatt2001.group_30.paths.controller.HelpController;
import edu.ntnu.idatt2001.group_30.paths.view.ui.common.DefaultButton;
import edu.ntnu.idatt2001.group_30.paths.view.ui.common.DefaultText;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Unfinished Help page.
 * @author Nicolai H. Brand.
 */
public class HelpView extends View<VBox> {

    private final HelpController controller = new HelpController();

    public HelpView() {
        super(VBox.class);
        add(helpText());
        add(DefaultButton.medium("Hjem", controller.goTo(HomeView.class)));
        add(DefaultButton.medium("Tilbake", controller.goBack()));
    }

    public Text helpText() {
        String howToPlay =
                """
                Hvorfor er det så vanskelig å lage en hjelpeside?
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nisl eget aliquam tincidunt, nisl nisl aliquet nisl, eget aliquam nis
                """;

        return DefaultText.medium(howToPlay);
    }

}
