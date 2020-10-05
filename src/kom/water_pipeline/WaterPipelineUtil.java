package kom.water_pipeline;

import kom.model.Point;

import java.util.List;

public class WaterPipelineUtil {
    public static Point findPointByName(List<Point> points, int name) {
        return points.stream()
                .filter(point -> point.getName() == name)
                .findFirst().orElseThrow(() -> new RuntimeException("Can't find point with this name"));
    }
}
