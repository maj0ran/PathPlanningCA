package mrn.ui.menubar;


import javafx.scene.input.MouseEvent;
import mrn.data.Model;
import mrn.ui.base.Controller;

import java.beans.PropertyChangeEvent;

public class ControllerMenubar extends Controller<Model, ViewMenubar> {

    public ControllerMenubar(Model model, ViewMenubar view) {
        super(model, view);
    }

    @Override
    public void init(Model model, ViewMenubar view) {
        view.update();

        view.load.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {

        });

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
