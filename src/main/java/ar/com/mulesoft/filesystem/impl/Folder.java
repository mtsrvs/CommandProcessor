package ar.com.mulesoft.filesystem.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Folder extends AbstractFileSystem implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Set<AbstractFileSystem> list;

    public Folder(String folderName, Folder parentFolder) {
        super.setName(folderName);
        super.setParentFolder(parentFolder);
        this.list = new HashSet<AbstractFileSystem>();
    }

    public Set<AbstractFileSystem> getList() {
        return list;
    }

    public void add(AbstractFileSystem afs) {
        this.list.add(afs);
    }

    public boolean containsFolder(String str) {
        List<Folder> result = find(str);

        return !result.isEmpty();
    }

    public Folder getFolder(String str) {
        List<Folder> result = find(str);

        return !result.isEmpty() ? result.get(0) : null;
    }

    private List<Folder> find(String str) {
        return list
                .stream()
                .filter(f -> f instanceof Folder && ((Folder) f).getName().equals(str))
                .map(f -> (Folder) f)
                .collect(Collectors.toList());
    }
}
