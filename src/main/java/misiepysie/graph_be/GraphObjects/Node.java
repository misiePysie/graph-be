package misiepysie.graph_be.GraphObjects;

import com.google.gson.Gson;

public class Node {


    private String id;
    private String label;
    private int size;
    private double fileSize;



    public Node(String path, String name, double size)
    {
        label =name;
        fileSize =size;
        id =path;


    }

    public Node(String label) {
        this.label = label;
    }

    public double getFileSize() {
        return fileSize;
    }
    public void setSize(double size) {
        this.size = (int) size;
    }
//
//    @Override
//    public String toString() {
//        return this.id + "," + this.label +","+this.fileSize + +this.size +'\n';
//    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public int getSize() {
        return size;
    }

    public void setSizeOfCircle(int sizeOfCircle) {
        this.size = sizeOfCircle;
    }

    public String toString(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}