package mrn.ui.menu;

import javafx.event.Event;
import mrn.data.Model;
import mrn.ui.base.Controller;

import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;

public class ControllerMenu extends Controller<Model, ViewMenu> {

    public ControllerMenu(Model model, ViewMenu view) {
        super(model, view);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override
    protected void init(Model model, ViewMenu view) {
        view.reset.setOnAction(e -> { model.ca.reset();
        change.firePropertyChange("update", null, null);
        });


    }
}
