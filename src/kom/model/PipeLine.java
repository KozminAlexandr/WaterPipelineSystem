package kom.model;

public class PipeLine {
    private Point source;
    private Point destination;
    private int length;

    public PipeLine(Point source, Point destination, int length) {
        this.source = source;
        this.destination = destination;
        this.length = length;
    }

    public Point getSource() {
        return source;
    }

    public Point getDestination() {
        return destination;
    }

    public int getLength() {
        return length;
    }
}
