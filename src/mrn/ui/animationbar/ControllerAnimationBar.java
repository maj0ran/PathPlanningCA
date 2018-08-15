package mrn.ui.animationbar;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import mrn.data.Model;
import mrn.ui.base.Controller;

import java.beans.PropertyChangeEvent;

public class ControllerAnimationBar extends Controller<Model, ViewAnimationBar> {

    private boolean animationSet;


    public ControllerAnimationBar(Model m, ViewAnimationBar v) {
        super(m, v);
    }

    @Override
    protected void init(Model model, ViewAnimationBar view) {

        final int init_animation_delay = 500;
        view.speedSlider.setValue(init_animation_delay);
        view.speedValue.setText(String.valueOf(init_animation_delay));
        change.firePropertyChange("animationspeed", null, init_animation_delay);


        view.isAnimation.setOnMouseClicked(e -> {
            animationSet = view.isAnimation.isSelected();
            change.firePropertyChange("animation", !animationSet, animationSet);
        });

        view.speedSlider.addEventHandler(MouseEvent.MOUSE_RELEASED, this::sliderChange);
        view.speedSlider.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::sliderChange);
        view.speedValue.addEventHandler(KeyEvent.KEY_PRESSED, this::speedValueEnter);

        view.speedValue.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("^\\d*$")) {
                view.speedValue.setText(oldValue);
            }
        });

    }

    private void speedValueEnter(KeyEvent e) {
        if(e.getCode().equals(KeyCode.ENTER)) {
            fixBoundary();
            view.speedSlider.setValue(Integer.valueOf(view.speedValue.getText()));
            change.firePropertyChange("animationspeed", null, (int)view.speedSlider.getValue());
        }
    }

    private void fixBoundary() {
        if(view.speedValue.getText().isEmpty() || Integer.valueOf(view.speedValue.getText()) < view.speedSlider.getMin()) {
            view.speedValue.setText(String.valueOf((int)view.speedSlider.getMin()));
        }

        else if(Integer.valueOf(view.speedValue.getText()) > view.speedSlider.getMax()) {
            view.speedValue.setText(String.valueOf((int)view.speedSlider.getMax()));
        }
    }


    private void sliderChange(MouseEvent e) {
        int val = (int)view.speedSlider.getValue();
        view.speedValue.setText(String.valueOf(val));
        change.firePropertyChange("animationspeed", null, val);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
