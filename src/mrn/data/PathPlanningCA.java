package mrn.data;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class PathPlanningCA extends CellularAutomat {

    private Cell start = null;
    private Cell target = null;

    public PathPlanningCA(int size_x, int size_y) {
        super(size_x, size_y);
    }


    public void setStartCell(Cell cell) {
        if(start != null) {
            start.setState(CellState.FREE);
        }
        cell.setState(CellState.VEHICLE);
        this.start = cell;
    }

    public void setTargetCell(Cell cell) {
        if(target != null) {
            target.setState(CellState.FREE);
        }
        cell.setState(CellState.TARGET);
        this.target = cell;
    }


    // TODO: Should somehow detect that initial position is reached and then terminate
    public void flood() {
        if (this.start == null || this.target == null) {
            return;
        }

        Queue<Cell> q = new LinkedList<>();
        this.target.setFloodDistance(0);
        q.offer(this.target);
        Cell curr;
        do  {
            curr = q.poll();
            LinkedList<Cell> n = this.getMooreNeighbors(curr.getX(), curr.getY());
            for (Cell c : n) {
                if (c.getState() == CellState.FREE || c.getState() == CellState.VEHICLE) {
                    c.setState(CellState.FLOODED);
                    c.setFloodDistance(curr.getFloodDistance() + 1);
                    q.offer(c);
                }
            }
        } while (!q.isEmpty() && curr.getState() != CellState.VEHICLE);
    }
}
