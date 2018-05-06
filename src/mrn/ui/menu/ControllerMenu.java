package mrn.ui.menu;

import mrn.data.Model;
import mrn.ui.base.Controller;

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



    }
}
