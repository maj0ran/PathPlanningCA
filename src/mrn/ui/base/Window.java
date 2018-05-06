package mrn.ui.base;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mrn.ui.JfxUi;

/**
 * this Class represents a whole Window and saves the JavaFX Stage and Scene-mrn.data.Graph.
 * A Window may contain multiple views which will be arranged in Windows' Layout.
 * Instantiate an object of this class with a JavaFX-defined Layout as Generic Parameter
 * @param <LAYOUT> the Layout (see JavaFX Pane) of the Window.
 */
public class Window<LAYOUT extends Pane> {

    private Stage stage;
    private LAYOUT layout;
    private JfxUi parent;


    public Window(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return this.stage;
    }

    public void setStage(Stage s) {
        this.stage = s;
    }

    public LAYOUT getLayout() {
        return layout;
    }

    public void setLayout(LAYOUT layout) {

        this.layout = layout;
    }


    public JfxUi getParent() {
        return parent;
    }

    public void setParent(JfxUi parent) {
        this.parent = parent;
    }
}
