package misiepysie.graph_be;

public class SourceFile {


    private double sizeOfFile;
    private String nameOfFile;
    private String connectionsOfFile; //todo: na razie string pozniej tzreba to jakos fajnie zapisac

    private int sizeOfCircle;

    SourceFile(String name,double size, String connections)
    {
        sizeOfFile=size;
        nameOfFile=name;
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
        return this.nameOfFile + "," + this.sizeOfFile + "," + this.connectionsOfFile+","+this.sizeOfCircle+"\n";
    }
}