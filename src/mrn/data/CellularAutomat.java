package mrn.data;

import java.util.LinkedList;

public class CellularAutomat {

    private Cell[][] cells;
    private int size_x, size_y;



    public CellularAutomat(int size_x, int size_y) {
        this.size_x = size_x;
        this.size_y = size_y;
        cells = new Cell[size_x][size_y];

        for (int i = 0; i < size_x; i++) {
            for (int j = 0; j < size_y; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public Cell getCell(int x, int y) {
        if(x >= size_x || y >= size_y) {
            throw new IndexOutOfBoundsException();
        }
        return cells[x][y];
    }

    public LinkedList<Cell> getAllNeighbors(Cell c) {
        return this.getAllNeighbors(c.getX(), c.getY());
    }

    private LinkedList<Cell> getAllNeighbors(int x, int y){
        LinkedList<Cell> neighbors = new LinkedList<>();
        Coord2D[] indeces = {
                new Coord2D(x, y-1),            // upper
                new Coord2D(x + 1, y - 1),   // upper right
                new Coord2D(x + 1, y),          // right
                new Coord2D(x + 1, y + 1),   // lower right
                new Coord2D(x, y + 1),          // lower
                new Coord2D(x - 1, y + 1),   // lower left
                new Coord2D(x - 1, y),          // left
                new Coord2D(x - 1, y - 1),   // upper left
        };

        for (Coord2D index : indeces) {
            try {
                Cell nCell = getCell(index.x, index.y);
                neighbors.add(nCell);
            } catch (IndexOutOfBoundsException ignore) {

            }
        }
        return neighbors;
    }
    public LinkedList<Cell> getEdgeNeighbors(Cell c) {
        return this.getEdgeNeighbors(c.getX(), c.getY());
    }
    private LinkedList<Cell> getEdgeNeighbors(int x, int y) {

        LinkedList<Cell> neighbors = new LinkedList<>();
        Coord2D[] indeces = {
                new Coord2D(x, y - 1),            // upper
                new Coord2D(x + 1, y),          // right
                new Coord2D(x, y + 1),          // lower
                new Coord2D(x - 1, y),          // left
        };

        for (Coord2D index : indeces) {
            try {
                Cell nCell = getCell(index.x, index.y);
                neighbors.add(nCell);
            } catch (IndexOutOfBoundsException ignore) {

            }
        }
        return neighbors;
    }


    public LinkedList<Cell> getCornerNeighbors(Cell c) {
        return this.getCornerNeighbors(c.getX(), c.getY());
    }

    private LinkedList<Cell> getCornerNeighbors(int x, int y) {
        LinkedList<Cell> neighbors = new LinkedList<>();
        Coord2D[] indeces = {
                new Coord2D(x + 1, y - 1),   // upper right
                new Coord2D(x + 1, y + 1),   // lower right
                new Coord2D(x - 1, y + 1),   // lower left
                new Coord2D(x - 1, y - 1),   // upper left
        };

        for (Coord2D index : indeces) {
            try {
                Cell nCell = getCell(index.x, index.y);
                neighbors.add(nCell);
            } catch (IndexOutOfBoundsException ignore) {

            }
        }
        return neighbors;
    }


    public int getSize_x() {
        return size_x;
    }

    public int getSize_y() {
        return size_y;
    }

}
