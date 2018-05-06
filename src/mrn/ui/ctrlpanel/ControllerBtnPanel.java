package mrn.ui.ctrlpanel;

import javafx.scene.input.MouseEvent;
import mrn.data.Model;
import mrn.ui.base.Controller;
import java.beans.PropertyChangeEvent;

public class ControllerBtnPanel extends Controller<Model, ViewBtnPanel> {

    public ControllerBtnPanel(Model m, ViewBtnPanel v) {
        super(m, v);
    }

    @Override
    protected void init(Model model, ViewBtnPanel view) {
        view.setStart.setOnMouseClicked(this::clickSetStart);
        view.setTarget.setOnMouseClicked(this::clickSetTarget);
        view.flood.setOnMouseClicked(this::clickFloodButton);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void clickSetStart(MouseEvent e) {
        boolean newState = view.setStart.isSelected();
        view.setTarget.setSelected(false);
        change.firePropertyChange("start_clicked", !newState, newState);
        change.firePropertyChange("target_clicked", null, false);
    }

    public void clickSetTarget(MouseEvent e) {
        boolean newState = view.setTarget.isSelected();
        view.setStart.setSelected(false);
        change.firePropertyChange("target_clicked", !newState, newState);
        change.firePropertyChange("start_clicked", null, false);
    }

    public void clickFloodButton(MouseEvent e) {
        model.ca.flood();
        change.firePropertyChange("flood", null, null);
    }
}
