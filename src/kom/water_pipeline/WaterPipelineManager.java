package kom.water_pipeline;

import kom.model.PipeLine;
import kom.model.WaterPipelineSystem;
import kom.model.Point;

import java.util.*;

public class WaterPipelineManager {
    private List<Point> points;
    private List<PipeLine> pipeLines;
    private Set<Point> visitedPoints;
    private Set<Point> unvisitedPoints;
    private Map<Point, Point> predecessors;
    private Map<Point, Integer> length;

    public WaterPipelineManager(WaterPipelineSystem waterPipelineSystem){
        this.points = new ArrayList<>(waterPipelineSystem.getPoints());
        this.pipeLines = new ArrayList<>(waterPipelineSystem.getPipeLines());
    }

    public void execute(Point source) {
        visitedPoints = new HashSet<>();
        unvisitedPoints = new HashSet<>();
        predecessors = new HashMap<>();
        length = new HashMap<>();
        length.put(source, 0);
        unvisitedPoints.add(source);
        while (unvisitedPoints.size() > 0) {
            Point point = getMinimum(unvisitedPoints);
            visitedPoints.add(point);
            unvisitedPoints.remove(point);
            findMinimalDistances(point);
        }
    }

    public int getMinLength(Point point) {
        return length.get(point);
    }

    private void findMinimalDistances(Point point) {
        List<Point> adjacentPoints = getNeighbors(point);
        for (Point target : adjacentPoints) {
            if (getMinimumLength(target) > getMinimumLength(point)
                    + getDistance(point, target)) {
                length.put(target, getMinimumLength(point)
                        + getDistance(point, target));
                predecessors.put(target, point);
                unvisitedPoints.add(target);
            }
        }
    }

    private int getDistance(Point point, Point target) {
        for (PipeLine pipeLine : pipeLines) {
            if (pipeLine.getSource().equals(point)
                    && pipeLine.getDestination().equals(target)) {
                return pipeLine.getLength();
            }
        }
        throw new RuntimeException("Something wrong");
    }

    private List<Point> getNeighbors(Point point) {
        List<Point> neighbors = new ArrayList<>();
        for (PipeLine pipeLine : pipeLines) {
            if (pipeLine.getSource().equals(point)
                    && !isVisited(pipeLine.getDestination())) {
                neighbors.add(pipeLine.getDestination());
            }
        }
        return neighbors;
    }

    private Point getMinimum(Set<Point> points) {
        Point minimum = null;
        for (Point point : points) {
            if (minimum == null) {
                minimum = point;
            } else {
                if (getMinimumLength(point) < getMinimumLength(minimum)) {
                    minimum = point;
                }
            }
        }
        return minimum;
    }

    private boolean isVisited(Point point) {
        return visitedPoints.contains(point);
    }

    private int getMinimumLength(Point destination) {
        Integer d = length.get(destination);
        return Objects.requireNonNullElse(d, Integer.MAX_VALUE);
    }

    public boolean pathExist(Point destination) {
        return predecessors.get(destination) != null;
    }
}
