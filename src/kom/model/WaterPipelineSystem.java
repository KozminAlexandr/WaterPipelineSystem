package kom.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class WaterPipelineSystem {
    private List<Point> points;
    private List<PipeLine> pipeLines;
}
