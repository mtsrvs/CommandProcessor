package ar.com.mulesoft.filesystem.impl;

public class AbstractFileSystem implements java.io.Serializable {

    private String name;
    private Folder parentFolder;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Folder getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }
}
