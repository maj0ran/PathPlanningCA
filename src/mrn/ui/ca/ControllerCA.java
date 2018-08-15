package mrn.ui.ca;

import javafx.application.Platform;
import javafx.scene.input.MouseEvent;
import mrn.data.Cell;
import mrn.data.CellState;
import mrn.data.Model;
import mrn.ui.base.Controller;

import java.beans.PropertyChangeEvent;
import java.util.concurrent.*;

public class ControllerCA extends Controller<Model, ViewCA> {

    private boolean isSetStart = false;
    private boolean isSetTarget = false;

    private int animationDelay = 100;
    private boolean animationSet;
    private Future<?> animationStatus;
    private ScheduledExecutorService animationService;


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

        if(property.equals("animation")) {
            animationSet = (boolean)evt.getNewValue();
        }

        if(property.equals("animationspeed")) {
            animationDelay = (int)evt.getNewValue();
        }

        if(property.equals("start_clicked")) {
            isSetStart = (boolean)evt.getNewValue();

        }

        if(property.equals("target_clicked")) {
            isSetTarget = (boolean)evt.getNewValue();
        }

        if(property.equals("nextFloodIteration")) {

            if(!animationSet) {
                while (!model.ca.isFinished()) {
                    model.ca.nextFloodIteration();
                }
                change.firePropertyChange("floodFinished", null, model.ca.isTargetReached());
                view.update();
            }

            else {
                animationService = Executors.newSingleThreadScheduledExecutor();
                animationStatus = animationService.scheduleAtFixedRate(() -> {
                                model.ca.nextFloodIteration();
                                Platform.runLater(() -> view.update());
                                if(model.ca.isFinished()) {
                                    change.firePropertyChange("animation_finished", null, null);
                                    change.firePropertyChange("floodFinished", null, model.ca.isTargetReached());
                                }
                            }, 0, animationDelay, TimeUnit.MILLISECONDS);
            }
        }

        if(property.equals("nextPathIteration")) {
            if(!animationSet) {
                while(!model.ca.isPathFinished()) {
                    model.ca.findNextPathCell();
                }
                view.update();
            }

            else {
                animationService = Executors.newSingleThreadScheduledExecutor();
                animationStatus = animationService.scheduleAtFixedRate(() -> {
                    model.ca.findNextPathCell();
                    Platform.runLater(() -> view.update());
                    if(model.ca.isPathFinished()) {
                        change.firePropertyChange("animation_finished", null, null);
                    }

                }, 0, animationDelay, TimeUnit.MILLISECONDS);
            }
        }

        if(property.equals("animation_finished")) {
            animationStatus.cancel(false);
            animationService.shutdownNow();
            System.out.println("Animation Finished");
        }

        if(property.equals("drawpath")) {
            view.update();
        }

        if(property.equals("update")) {
            view.initCE();
            view.update();
        }

        if(property.equals("reset")) {
            view.initCE();
            //view.update();
        }
    }

    private void changeCellState(MouseEvent e) {


        int x = (int)(e.getX() / view.cell_size);
        int y = (int)(e.getY() / view.cell_size);


        if(x >= model.ca.getSize_x() || y >= model.ca.getSize_y() || x < 0 || y < 0) {
            return;
        }

        Cell cell = model.ca.getCell(x, y);

        if(this.isSetStart) {
            model.ca.setStartCell(cell);
            isSetStart = true;
            change.firePropertyChange("start_set", !isSetStart, isSetStart);
        }

        else if(this.isSetTarget) {
            model.ca.setTargetCell(cell);
            isSetTarget = true;
            change.firePropertyChange("target_set", !isSetTarget, isSetTarget);
        }

        else {
            if (e.isPrimaryButtonDown()) {
                deleteStartOrTargetCell(cell);
                model.ca.setCellState(cell, CellState.OBSTACLE);
                //cell.setState(CellState.OBSTACLE);
            }

            if (e.isSecondaryButtonDown()) {
                deleteStartOrTargetCell(cell);
                model.ca.setCellState(cell, CellState.FREE);
                //cell.setState(CellState.FREE);
            }
        }
        view.update();
    }

    private void deleteStartOrTargetCell(Cell cell) {
        if(cell.getState() == CellState.START) {
            isSetStart = false;
            change.firePropertyChange("start_set", !isSetStart, isSetStart);
        } else if(cell.getState() == CellState.TARGET) {
            isSetTarget = false;
            change.firePropertyChange("target_set", !isSetTarget, isSetTarget);
        }
    }
}
