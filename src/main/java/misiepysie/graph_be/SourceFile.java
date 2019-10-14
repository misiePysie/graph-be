package misiepysie.graph_be;

public class SourceFile {
    private String sizeOfFile;
    private String nameOfFile;
    private String connections; //todo: na razie string pozniej tzreba to jakos fajnie zapisac
    SourceFile(String name,String size, String connections)
    {

        sizeOfFile=size;
        nameOfFile=name;
        connections=connections;

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
