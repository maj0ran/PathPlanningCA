package mrn.ui.ctrlpanel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import mrn.data.Model;
import mrn.ui.base.View;

public class ViewBtnPanel extends View<Model> {
    private VBox layout;
    ToggleButton btnSetStart;
    ToggleButton btnSetTarget;
    Button btnFlood;
    Button btnFindPath;

    public ViewBtnPanel(Model model) {
        this.setModel(model);
        this.setController(new ControllerBtnPanel(model, this));
    }

    @Override
    protected void init() {
        layout = new VBox();
        layout.setSpacing(10);
        layout.setMinWidth(150);
        layout.setAlignment(Pos.TOP_CENTER);

        btnSetStart = new ToggleButton("Set Start");
        btnSetTarget = new ToggleButton("Set Target");
        btnFlood = new Button("Flood");
        btnFindPath = new Button("Find Path");


        btnSetStart.setMinWidth(100);

        btnSetTarget.setMinWidth(100);

        btnFlood.setMinWidth(100);
        btnFlood.setDisable(true);

        btnFindPath.setMinWidth(100);
        btnFindPath.setDisable(true);

        layout.getChildren().addAll(btnSetStart, btnSetTarget, btnFlood, btnFindPath);
    }

    @Override
    public VBox getRoot() {
        return layout;
    }
}
