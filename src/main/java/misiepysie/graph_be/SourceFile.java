package misiepysie.graph_be;

public class SourceFile {
    private double sizeOfFile;
    private String nameOfFile;
    private String connectionsOfFile; //todo: na razie string pozniej tzreba to jakos fajnie zapisac

    SourceFile(String name,double size, String connections)
    {
        sizeOfFile=size;
        nameOfFile=name;
        connectionsOfFile=connections;

    }


    @Override
    public String toString() {
        return this.nameOfFile + "," + this.sizeOfFile + "," + this.connectionsOfFile+"\n";
    }
}