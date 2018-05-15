package mrn.ui.ctrlpanel;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import mrn.data.Model;
import mrn.ui.base.View;

public class ViewBtnPanel extends View<Model> {
    VBox layout;
    ToggleButton setStart;
    ToggleButton setTarget;

    Button flood;
    Button findPath;

    public ViewBtnPanel(Model model) {
        this.setModel(model);
        this.setController(new ControllerBtnPanel(model, this));
    }

    @Override
    protected void init() {
        layout = new VBox();
        layout.setSpacing(10);
        setStart = new ToggleButton("Set Start");
        setTarget = new ToggleButton("Set Target");
        flood = new Button("Flood");
        findPath = new Button("Find Path");

        setStart.setMinWidth(100);
        setTarget.setMinWidth(100);

        flood.setMinWidth(100);
        findPath.setMinWidth(100);
        layout.getChildren().addAll(setStart, setTarget, flood, findPath);
    }

    @Override
    public VBox getRoot() {
        return layout;
    }
}
