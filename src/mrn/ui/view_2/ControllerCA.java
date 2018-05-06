package mrn.ui.view_2;

import javafx.scene.input.MouseEvent;
import mrn.data.Cell;
import mrn.data.CellState;
import mrn.data.Model;
import mrn.ui.base.Controller;

import java.beans.PropertyChangeEvent;
import java.util.LinkedList;

public class ControllerCA extends Controller<Model, ViewCA> {

    private boolean isSetStart = false;
    private boolean isSetTarget = false;

    public ControllerCA(Model model, ViewCA view) {

        super(model, view);
    }

    public void init(Model model, ViewCA view) {
        view.getRoot().setOnMouseDragged(this::changeCellState);
        view.getRoot().setOnMouseClicked(this::changeCellState); // will probably not work
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String property = evt.getPropertyName();

        if(property.equals("start_clicked")) {
            isSetStart = (boolean)evt.getNewValue();

        }

        if(property.equals("target_clicked")) {
            isSetTarget = (boolean)evt.getNewValue();
        }

        if(property.equals("flood")) {
            view.update();
        }
    }

    public void changeCellState(MouseEvent e) {


        int x = (int)(e.getX() / view.cell_size);
        int y = (int)(e.getY() / view.cell_size);


        if(x >= model.ca.getSize_x() || y >= model.ca.getSize_y() || x < 0 || y < 0) {
            return;
        }

        Cell cell = model.ca.getCell(x, y);

        if(this.isSetStart) {
            model.ca.setStartCell(cell);
        }

        else if(this.isSetTarget) {
            model.ca.setTargetCell(cell);
        }

        else {
            if (e.isPrimaryButtonDown()) {
                cell.setState(CellState.BLOCKED);
            }
            if (e.isSecondaryButtonDown()) {
                cell.setState(CellState.FREE);
            }
        }



        view.update();
    }
}
