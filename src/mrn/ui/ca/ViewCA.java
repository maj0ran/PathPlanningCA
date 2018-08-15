package mrn.ui.ca;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import mrn.data.Cell;
import mrn.data.Model;
import mrn.ui.base.View;

public class ViewCA extends View<Model> {

    private GridPane layout;
    private Rectangle[][] cells;
    private StackPane[][] cellStacks;
    private Text[][] cellDistances;

    public int cell_size = 40;
    private int size_x = 0;
    private int size_y = 0;

    public ViewCA(Model model) {
        this.setModel(model);
        this.setController(new ControllerCA(model, this));
        initCE();
    }

    @Override
    public GridPane getRoot() {
        return this.layout;
    }

    public Rectangle[][] getCells() {
        return cells;
    }

    @Override
    protected void init() {
        layout = new GridPane();
        System.out.println(layout.getHgap() + " " + layout.getVgap());


    }

    public void initCE() {

        layout.getChildren().clear();

        this.size_x = model.ca.getSize_x();
        this.size_y = model.ca.getSize_y();
        cells = new Rectangle[size_x][size_y];
        cellDistances = new Text[size_x][size_y];
        cellStacks = new StackPane[size_x][size_y];

        for (int i = 0; i < size_x; i++) {
            for (int j = 0; j < size_y; j++) {
                cells[i][j] = new Rectangle();
                cells[i][j].setWidth(cell_size - cells[i][j].getStrokeWidth());
                cells[i][j].setHeight(cell_size - cells[i][j].getStrokeWidth());
                cells[i][j].setStroke(Color.color(0, 0, 0));
                cells[i][j].setFill(Color.color(1, 1, 1));

                cellDistances[i][j] = new Text();
                cellDistances[i][j].setFont(Font.font ("Verdana", cell_size / 3));
                cellDistances[i][j].maxWidth(cell_size);
                cellDistances[i][j].setVisible(false);

                cellStacks[i][j] = new StackPane();
                cellStacks[i][j].getChildren().add(0, cells[i][j]);
                cellStacks[i][j].getChildren().add(1, cellDistances[i][j]);
                layout.add(cellStacks[i][j], i, j);
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
                    case OBSTACLE:
                        cells[i][j].setFill(Color.color(0, 0, 0));
                        break;
                    case START:
                        cells[i][j].setFill(Color.color(0, 1, 0));
                        break;
                    case TARGET:
                        cells[i][j].setFill(Color.color(1, 0, 0));
                        break;
                    case FLOODED:
                        cells[i][j].setFill(Color.color(0, 0.85, 1));
                        cellDistances[i][j].setText(String.format("%.1f", model.ca.getCell(i,j).getFloodDistance()));
                        cellDistances[i][j].setVisible(true);
                       break;
                    case PATH:
                        cells[i][j].setFill(Color.color(1, .75, 0));
                        break;
                }
            }
        }
    }
}

