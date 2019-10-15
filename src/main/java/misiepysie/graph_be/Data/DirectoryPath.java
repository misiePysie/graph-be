package misiepysie.graph_be.Data;

public class DirectoryPath {
    private String backendSrc;
    private String frontendSrc;

//    public DirectoryPath() {
//    }

    public DirectoryPath(String backend, String frontend){
        this.backendSrc = backend;
        this.frontendSrc =frontend;
    }

    public String getFrontendSrc() {
        return frontendSrc;
    }

    public void setFrontendSrc(String frontendSrc) {
        this.frontendSrc = frontendSrc;
    }

    public String getBackendSrc() {
        return backendSrc;
    }

    public void setBackendSrc(String backendSrc) {
        this.backendSrc = backendSrc;
    }
}
