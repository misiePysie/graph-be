package misiepysie.graph_be;

public class Node {

    private String nameOfFile;
    private double sizeOfFile;
    private String pathToFile;
    private int sizeOfCircle;

    public Node(String path, String name, double size)
    {
        nameOfFile=name;
        sizeOfFile=size;
        pathToFile=path;


    }
    public double getSizeOfFile() {
        return sizeOfFile;
    }
    public void setSizeOfCircle(double sizeOfCircle) {
        this.sizeOfCircle = (int)sizeOfCircle;
    }

    @Override
    public String toString() {
        return this.pathToFile + "," + this.nameOfFile+","+this.sizeOfFile + +this.sizeOfCircle+'\n';
    }

    public String getNameOfFile() {
        return nameOfFile;
    }

    public void setNameOfFile(String nameOfFile) {
        this.nameOfFile = nameOfFile;
    }

    public void setSizeOfFile(double sizeOfFile) {
        this.sizeOfFile = sizeOfFile;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }



    public int getSizeOfCircle() {
        return sizeOfCircle;
    }

    public void setSizeOfCircle(int sizeOfCircle) {
        this.sizeOfCircle = sizeOfCircle;
    }
}