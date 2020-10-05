package kom.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PipeLine {
    private Point source;
    private Point destination;
    private int length;
}
