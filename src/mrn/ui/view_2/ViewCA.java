package mrn.ui.view_2;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import mrn.data.Cell;
import mrn.data.Model;
import mrn.ui.base.View;

import java.util.List;

public class ViewCA extends View<Model> {

    private GridPane layout;
    private Rectangle[][] cells;
    private StackPane[][] sells;
    public int cell_size = 15;
    int size_x = 0;
    int size_y = 0;

    public ViewCA(Model model) {
        this.setModel(model);
        this.setController(new ControllerCA(model, this));
        initCE();
    }

    public GridPane getRoot() {
        return this.layout;
    }

    public Rectangle[][] getCells() {
        return cells;
    }

    @Override
    protected void init() {
        cell_size = 15;
        layout = new GridPane();
        System.out.println(layout.getHgap() + " " + layout.getVgap());


    }

    public void initCE() {
        this.size_x = model.ca.getSize_x();
        this.size_y = model.ca.getSize_y();
        cells = new Rectangle[size_x][size_y];
        sells = new StackPane[size_x][size_y];

        for (int i = 0; i < size_x; i++) {
            for (int j = 0; j < size_y; j++) {
                cells[i][j] = new Rectangle();
                cells[i][j].setWidth(cell_size - cells[i][j].getStrokeWidth());
                cells[i][j].setHeight(cell_size - cells[i][j].getStrokeWidth());
                cells[i][j].setStroke(Color.color(0, 0, 0));
                cells[i][j].setFill(Color.color(1, 1, 1));
                sells[i][j] = new StackPane();
                sells[i][j].getChildren().add(0, cells[i][j]);
                layout.add(sells[i][j], i, j);
            }
        }
    }

    public void update() {
        for (int i = 0; i < size_x; i++) {
            for (int j = 0; j < size_y; j++) {
                Cell cell = model.ca.getCell(i, j);
                switch(cell.getState()) {
                    case FREE:
                        cells[i][j].setFill(Color.color(1, 1, 1));
                        break;
                    case BLOCKED:
                        cells[i][j].setFill(Color.color(0, 0, 0));
                        break;
                    case VEHICLE:
                        cells[i][j].setFill(Color.color(0, 1, 0));
                        break;
                    case TARGET:
                        cells[i][j].setFill(Color.color(1, 0, 0));
                        break;
                    case FLOODED:
                        cells[i][j].setFill(Color.color(0, .8, 1));
                        Text d = new Text(String.valueOf(model.ca.getCell(i, j).getFloodDistance()));
                       sells[i][j].getChildren().add(d);


                     //   StackPane s = (StackPane)layout.getChildren().get(i*this.size_y+j);
                    //    s.getChildren().add(d);
                }
            }
        }
    }
}

