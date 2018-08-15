package mrn.data;

import java.util.LinkedList;

public class Cell {

    private int x;
    private int y;

    private CellState state = CellState.FREE;
    private Double floodDistance = null;
    public Cell parent = null;
    public boolean isChild = false;

    public Cell getParent() {
        return parent;
    }

    public void setParent(Cell parent) {
        this.parent = parent;
    }

    public boolean isChild() {
        return isChild;
    }

    public void setChild(boolean child) {
        isChild = child;
    }

    public Cell(int posx, int posy) {
        x = posx;
        y = posy;
    }

    public void reset() {
        state = CellState.FREE;
        floodDistance = 0.0;
        parent = null;
        isChild = false;
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

    public Double getFloodDistance() {
        return floodDistance;
    }

    public void setFloodDistance(Double d) {
        this.floodDistance = d;
    }
}
