package mrn.data;

import javafx.geometry.Point2D;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class PathPlanningCA extends CellularAutomat {

    private Cell start = null;
    private Cell target = null;

    private boolean isFlooded = false;
    private boolean targetReachable = false;

    public PathPlanningCA(int size_x, int size_y) {
        super(size_x, size_y);
    }


    public void setStartCell(Cell cell) {
        if (start != null) {
            start.setState(CellState.FREE);
        }
        cell.setState(CellState.VEHICLE);
        this.start = cell;
    }

    public void setTargetCell(Cell cell) {
        if (target != null) {
            target.setState(CellState.FREE);
        }
        cell.setState(CellState.TARGET);
        this.target = cell;
    }

    public Cell getTargetCell() {
        return this.target;
    }

    public Cell getStartCell() {
        return this.start;
    }

    public void reset() {
        start = null;
        target = null;
        isFlooded = false;
        targetReachable = false;

        for(int i = 0; i < this.getSize_x(); i++) {
            for(int j = 0; j < this.getSize_y(); j++) {
                this.getCell(i, j).setState(CellState.FREE);
            }
        }
    }

    public boolean flood() {
        if (this.start == null || this.target == null) {
            return false;
        }

        if(this.isFlooded) {
            return false;
        }


        Queue<Cell> q = new LinkedList<>();
        LinkedList<Cell> nghbrs;

        this.target.setFloodDistance(.0);
        q.offer(this.target);
        Cell curr;
        while (!q.isEmpty() && !targetReachable) {
            curr = q.poll();
            nghbrs = this.getMooreNeighbors(curr.getX(), curr.getY());
            for (Cell c : nghbrs) {
                if (c.getState() == CellState.FREE) {
                    c.setState(CellState.FLOODED);
                    c.setFloodDistance(curr.getFloodDistance() + 1);
                    q.offer(c);
                } else if (c.getState() == CellState.VEHICLE) {
                    c.setFloodDistance(curr.getFloodDistance() + 1);
                    targetReachable = true;
                    break;
                }
            }
        }
        isFlooded = true;
        return true;
    }

    public boolean flood2() {
        if (start == null || target == null) {
            return false;
        }

        if(isFlooded) {
            return false;
        }
        int time = 0;

        Queue<Cell> q = new LinkedList<>();
        LinkedList<Cell> nghbrs;
        LinkedList<Cell> edgeNghbrs;
        LinkedList<Cell> cornerNghbrs;

        target.setFloodDistance(.0);
        target.isChild = true;

        q.offer(target);
        Cell curr;
        boolean fireFlag = false;
        while (!q.isEmpty() && !targetReachable) {
            curr = q.poll();
            nghbrs = this.getMooreNeighbors(curr.getX(), curr.getY());
            edgeNghbrs = this.getNeumannNeighbors(curr.getX(), curr.getY());
            cornerNghbrs = this.getDiagonalNeighbors(curr.getX(), curr.getY());

            for (Cell next : nghbrs) {
                if (next.getState() == CellState.FREE) {
                    next.setState(CellState.FLOODED);
                    double minCost = Double.POSITIVE_INFINITY;
                    double transitionCost = 0;

                    for(Cell nextNeighbor : this.getNeumannNeighbors(next)) {
                        if(nextNeighbor.getState() == CellState.FLOODED | nextNeighbor.getState() == CellState.TARGET) {
                            transitionCost = nextNeighbor.getFloodDistance() + 1;
                            if(transitionCost < minCost) {
                                minCost = transitionCost;
                                next.parent = nextNeighbor;
                            }
                        }
                    }

                    for(Cell nextNeighbor : this.getDiagonalNeighbors(next)) {
                        if(nextNeighbor.getState() == CellState.FLOODED | nextNeighbor.getState() == CellState.TARGET) {
                            transitionCost = nextNeighbor.getFloodDistance() + Math.sqrt(2);
                            if(transitionCost < minCost) {
                                minCost = transitionCost;
                                next.parent = nextNeighbor;
                            }
                        }
                    }

                    next.setFloodDistance(minCost);
                    next.isChild = true;
                    q.offer(next);

                } else if (next.getState() == CellState.VEHICLE) {
                    next.setFloodDistance(curr.getFloodDistance() + 1);
                    next.isChild = true;
                    next.parent = curr;
                    targetReachable = true;
                    break;
                }
            }
            time++;
        }
        isFlooded = true;
        return true;
    }

    public boolean findPath() {
        if(!isFlooded) {
            return false;
        }

        if(!targetReachable) {
            return false;
        }
        Cell curr = this.getStartCell();
        LinkedList<Cell> nbrs;

        while(curr.getState() != CellState.TARGET) {
            nbrs = this.getMooreNeighbors(curr.getX(), curr.getY());
            for(Cell n : nbrs) {
                if((n.getState() == CellState.FLOODED) && n.getFloodDistance() < curr.getFloodDistance()) {
                    n.setState(CellState.PATH);
                    curr = n;
                    break;
                } else if( n.getState() == CellState.TARGET) {
                    curr = n;
                    break;
                }
            }

        }
        return true;
    }

    public boolean findPath2() {
        if(!isFlooded) {
            return false;
        }

        if(!targetReachable) {
            return false;
        }
        Cell curr = this.getStartCell();
        curr = curr.parent;

        while(curr != target) {
            curr.setState(CellState.PATH);
            curr = curr.parent;
        }
        return true;
    }
}
