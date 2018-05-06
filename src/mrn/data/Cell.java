package mrn.data;

import java.util.LinkedList;

public class Cell {

    private CellState state = CellState.FREE;
    private Integer floodDistance = null;


    private int x;
    private int y;

    public Cell(int posx, int posy) {
        x = posx;
        y = posy;
    }

    public void setState(CellState newState) {
        this.state = newState;
    }

    public CellState getState() {
        return this.state;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Integer getFloodDistance() {
        return floodDistance;
    }

    public void setFloodDistance(Integer d) {
        this.floodDistance = d;
    }
}
