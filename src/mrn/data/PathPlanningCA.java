package mrn.data;

import java.util.*;

public class PathPlanningCA extends CellularAutomat {

    private Cell startCell = null;
    private Cell targetCell = null;

    private boolean isFlooded = false;
    private boolean targetReached = false;
    private boolean pathFinished = false;
    private boolean finished = false;

    private Cell currPathCell = null;

    private Queue<Cell> q = new LinkedList<>();
    private int time = 0;

    public PathPlanningCA(int size_x, int size_y) {
        super(size_x, size_y);
    }

    public boolean isFinished() {
        return finished;
    }

    public void setStartCell(Cell cell) {
        if (startCell != null) {
            startCell.reset();
            q.clear();
        }
        cell.setState(CellState.START);
        this.startCell = cell;

    }

    public void setCellState(Cell cell, CellState state) {
        if(cell.getState() == CellState.START) {
            startCell = null;
        }
        if(cell.getState() == CellState.TARGET) {
            targetCell = null;
        }

        if(state == CellState.START)
            setStartCell(cell);

        else if(state == CellState.TARGET)
            setTargetCell(cell);

        else cell.setState(state);
    }

    public void setTargetCell(Cell cell) {
        if (targetCell != null) {
            targetCell.reset();
            q.clear();
        }

        cell.setState(CellState.TARGET);
        this.targetCell = cell;
        targetCell.setFloodDistance(0.0);
        targetCell.isChild = true;
    }

    private Cell getTargetCell() {
        return this.targetCell;
    }

    private Cell getStartCell() {
        return this.startCell;
    }

    public boolean isTargetReached() {
        return targetReached;
    }

    public boolean isPathFinished() {
        return pathFinished;
    }

    public void reset() {
        startCell = null;
        targetCell = null;
        currPathCell = null;
        isFlooded = false;
        targetReached = false;
        pathFinished = false;
        finished = false;
        time = 0;
        q.clear();

        for(int i = 0; i < this.getSize_x(); i++) {
            for(int j = 0; j < this.getSize_y(); j++) {
                this.getCell(i,j).reset();
            }
        }
    }

    public boolean nextFloodIteration() {
        if (startCell == null || targetCell == null) {
            return false;
        }

        if(time == 0) {
            q.offer(targetCell);
        }

        LinkedList<Cell> currNeighbors;
        Cell curr;

        // while there are still cells to flood and targetCell is not reached
        if (!q.isEmpty() && !targetReached) {
            Queue<Cell> nextQueue = new LinkedList<>(); // save changed cells for the next iteration
            while(!q.isEmpty()) {
                curr = q.poll();
                currNeighbors = this.getAllNeighbors(curr);

                // flood each neighbor which is not an obstacle
                for (Cell neighbor : currNeighbors) {
                    if (neighbor.getState() == CellState.FREE) {
                        neighbor.setState(CellState.FLOODED);

                        /*
                         get all flooded neighbors of this neighbor and calculate the distance from the neighbor
                         with the smallest d-value
                        */
                        double minCost = Double.POSITIVE_INFINITY;
                        double transitionCost;

                        // 1) for all edged Neighbors (d = d + 1)
                        for (Cell nextNeighbor : getEdgeNeighbors(neighbor)) {
                            if (nextNeighbor.getState() == CellState.FLOODED || nextNeighbor.getState() == CellState.TARGET) {
                                transitionCost = nextNeighbor.getFloodDistance() + 1;
                                if (transitionCost < minCost) {
                                    minCost = transitionCost;
                                    neighbor.parent = nextNeighbor; // parent shows from which cell the transition came
                                }
                            }
                        }
                        // 2) for all cornered Neighbors (d = d + sqrt(2))
                        for (Cell nextNeighbor : this.getCornerNeighbors(neighbor)) {
                            if (nextNeighbor.getState() == CellState.FLOODED || nextNeighbor.getState() == CellState.TARGET) {
                                transitionCost = nextNeighbor.getFloodDistance() + Math.sqrt(2);
                                if (transitionCost < minCost) {
                                    minCost = transitionCost;
                                    neighbor.parent = nextNeighbor; // parent shows from which cell the transition came
                                }
                            }
                        }

                        // the akkumulation of the neighbor with the smallest distance is the next path-cell
                        neighbor.setFloodDistance(minCost);
                        neighbor.isChild = true; // make it a child, so at next iteration it can be a parent
                        nextQueue.offer(neighbor);

                        // if neighbor of current Cell is the StartCell, set the finish flag
                    } else if (neighbor.getState() == CellState.START) {
                        neighbor.setFloodDistance(curr.getFloodDistance() + 1);
                        neighbor.isChild = true;
                        neighbor.parent = curr;
                        targetReached = true;
                        finished = true;
                        currPathCell = getStartCell();
                        q.clear();
                        break;
                    }
                }
            }
            q = nextQueue;
            time++;
        }
        else {
            finished = true;
        }
        return finished;
    }

    public boolean findNextPathCell() {
        if(!targetReached) {
            return false;
        }
        if(currPathCell != getTargetCell()) {
            currPathCell = currPathCell.parent;
            currPathCell.setState(CellState.PATH);
        } else {
            pathFinished = true;
        }
        return pathFinished;
    }
}

