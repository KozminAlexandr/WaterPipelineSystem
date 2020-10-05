package kom.model;

import java.util.Objects;

public class Point {
    private int name;

    public Point(int name) {
        this.name = name;
    }

    public int getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return name == point.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
