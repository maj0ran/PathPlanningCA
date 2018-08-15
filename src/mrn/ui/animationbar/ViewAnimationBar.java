package mrn.ui.animationbar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import mrn.data.Model;
import mrn.ui.base.View;

public class ViewAnimationBar extends View<Model> {

    private HBox root;
    private Label animate;
    CheckBox isAnimation;
    Slider speedSlider;
    TextField speedValue;

    public ViewAnimationBar(Model model) {
        this.model = model;
        this.ctrl = new ControllerAnimationBar(model, this);
    }

    @Override
    protected void init() {
        root = new HBox();
        root.setMinHeight(40);
        root.setAlignment(Pos.CENTER_LEFT);

        animate = new Label("Animate:");
        animate.setPadding(new Insets(0, 10, 0, 0));
        animate.setFont(Font.font ("Verdana", 16));

        isAnimation = new CheckBox();
        isAnimation.setPadding(new Insets(0, 20, 0, 0));
        isAnimation.setContentDisplay(ContentDisplay.BOTTOM);

        speedSlider = new Slider();
        speedSlider.setMinWidth(400);
        speedSlider.setMin(1);
        speedSlider.setMax(1000);
        speedSlider.setValue(500);
        speedSlider.setPadding(new Insets(0, 0, 0, 0));

        speedValue = new TextField();
        speedValue.setMinWidth(80);
        speedValue.setMaxWidth(80);
        root.getChildren().addAll(animate, isAnimation, speedSlider, speedValue);
    }

    @Override
    public HBox getRoot() {
        return root;
    }
}
