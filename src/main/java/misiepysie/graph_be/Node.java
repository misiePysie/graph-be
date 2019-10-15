package misiepysie.graph_be;

public class Node {

    private String nameOfFile;
    private double sizeOfFile;
    private String pathToFile;
    private String connectionsOfFile; //todo: na razie string pozniej tzreba to jakos fajnie zapisac
    private int sizeOfCircle;

    public Node(String path, String name, double size, String connections)
    {
        nameOfFile=name;
        sizeOfFile=size;
        pathToFile=path;
        connectionsOfFile=connections;

    }
    public double getSizeOfFile() {
        return sizeOfFile;
    }
    public void setSizeOfCircle(double sizeOfCircle) {
        this.sizeOfCircle = (int)sizeOfCircle;
    }

    @Override
    public String toString() {
        return this.pathToFile + "," + this.nameOfFile+","+this.sizeOfFile + "," + this.connectionsOfFile+","+this.sizeOfCircle+"\n";
    }
}