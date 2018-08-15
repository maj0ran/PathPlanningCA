package mrn.ui.ctrlpanel;

import javafx.scene.input.MouseEvent;
import mrn.data.Model;
import mrn.ui.base.Controller;
import java.beans.PropertyChangeEvent;

public class ControllerBtnPanel extends Controller<Model, ViewBtnPanel> {

    private boolean isStartSet = false;
    private boolean isTargetSet = false;

    public ControllerBtnPanel(Model m, ViewBtnPanel v) {
        super(m, v);
    }

    @Override
    protected void init(Model model, ViewBtnPanel view) {
        view.btnSetStart.setOnMouseClicked(this::clickSetStart);
        view.btnSetTarget.setOnMouseClicked(this::clickSetTarget);
        view.btnFlood.setOnMouseClicked(this::clickFloodButton);
        view.btnFindPath.setOnMouseClicked(this::clickFindPath);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("start_set")) {
            isStartSet = (boolean)evt.getNewValue();
            checkFloodAvailable();
        }
        if(evt.getPropertyName().equals("target_set")) {
            isTargetSet = (boolean)evt.getNewValue();
            checkFloodAvailable();
        }

        if(evt.getPropertyName().equals("floodFinished")) {
            boolean isTargetReached = model.ca.isTargetReached();
            if(isTargetReached) {
                view.btnFindPath.setDisable(false);
            }
        }

        if(evt.getPropertyName().equals("reset")) {
            view.btnFindPath.setDisable(true);
            view.btnFlood.setDisable(true);
        }
    }

    private void checkFloodAvailable() {
        if(isStartSet && isTargetSet) {
            view.btnFlood.setDisable(false);
        } else {
            view.btnFlood.setDisable(true);
        }
    }

    private void clickSetStart(MouseEvent e) {
            boolean newState = view.btnSetStart.isSelected();
            view.btnSetTarget.setSelected(false);
            change.firePropertyChange("start_clicked", !newState, newState);
            change.firePropertyChange("target_clicked", null, false);

    }

    private void clickSetTarget(MouseEvent e) {
            boolean newState = view.btnSetTarget.isSelected();
            view.btnSetStart.setSelected(false);
            change.firePropertyChange("target_clicked", !newState, newState);
            change.firePropertyChange("start_clicked", null, false);

    }

    private void clickFloodButton(MouseEvent e) {
            change.firePropertyChange("nextFloodIteration", null, null); // to ControllerCA

    }

    private void clickFindPath(MouseEvent e) {
            change.firePropertyChange("nextPathIteration", null, null); // to ControllerCA

    }
}
