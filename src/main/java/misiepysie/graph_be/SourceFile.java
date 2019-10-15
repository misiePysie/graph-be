package misiepysie.graph_be;

public class SourceFile {
    private String sizeOfFile;
    private String nameOfFile;
    private String connectionsOfFile; //todo: na razie string pozniej tzreba to jakos fajnie zapisac

    SourceFile(String name,String size, String connections)
    {

        sizeOfFile=size;
        nameOfFile=name;
        connectionsOfFile=connections;

    }




    @Override
    public String toString() {
        return this.nameOfFile + "," + this.sizeOfFile + "," + this.connectionsOfFile;
    }
}