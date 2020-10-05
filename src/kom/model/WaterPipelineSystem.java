package kom.model;

import java.util.List;

public class WaterPipelineSystem {
    private List<Point> points;
    private List<PipeLine> pipeLines;

    public WaterPipelineSystem(List<Point> points, List<PipeLine> pipeLines) {
        this.points = points;
        this.pipeLines = pipeLines;
    }

    public List<Point> getPoints() {
        return points;
    }

    public List<PipeLine> getPipeLines() {
        return pipeLines;
    }
}
