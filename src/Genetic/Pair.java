package Genetic;

public class Pair<T, P> {
    public T vertex;
    public P existence;

    public Pair(T vertex, P existence) {
        this.vertex = vertex;
        this.existence = existence;
    }

    public T getVertex() {
        return vertex;
    }

    public P exists() {
        return existence;
    }

    public void setVertex(T vertex) {
        this.vertex = vertex;
    }

    public void setExistence(P existence) {
        this.existence = existence;
    }
}

