package ar.com.mulesoft.filesystem.impl;

public class File extends AbstractFileSystem implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    public File(String name, Folder parentFolder) {
        super.setName(name);
        super.setParentFolder(parentFolder);
    }
}
