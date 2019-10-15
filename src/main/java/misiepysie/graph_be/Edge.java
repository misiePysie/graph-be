package misiepysie.graph_be;

public class Edge {
    private Node n1;
    private Node n2;
    private int numberOfConnections;

    @Override
    public String toString() {
        return "Edge{" +
                "" + n1.getPathToFile() +
                "," + n2.getPathToFile() +
                "," + numberOfConnections +'\n'+
                '}';
    }



    public Edge(Node n1, Node n2, int numberOfConnections) {
        this.n1 = n1;
        this.n2 = n2;
        this.numberOfConnections = numberOfConnections;
    }

    public Node getN1() {
        return n1;
    }

    public void setN1(Node n1) {
        this.n1 = n1;
    }

    public Node getN2() {
        return n2;
    }

    public void setN2(Node n2) {
        this.n2 = n2;
    }

    public int getNumberOfConnections() {
        return numberOfConnections;
    }

    public void setNumberOfConnections(int numberOfConnections) {
        this.numberOfConnections = numberOfConnections;
    }


}
