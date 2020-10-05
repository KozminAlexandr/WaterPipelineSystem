package kom.model;

public class Route {
    private int idA;
    private int idB;

    public Route(int idA, int idB) {
        this.idA = idA;
        this.idB = idB;
    }

    public int getIdA() {
        return idA;
    }

    public int getIdB() {
        return idB;
    }
}
