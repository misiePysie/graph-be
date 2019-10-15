package misiepysie.graph_be;

public class Nodes {
    private String id;
    private String label;
    private String size;
    private String fileSize;

    public Nodes(String id, String label, String size, String fileSize) {
        this.id = id;
        this.label = label;
        this.size = size;
        this.fileSize = fileSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
}
