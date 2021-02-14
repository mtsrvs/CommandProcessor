package ar.com.mulesoft.command.impl;

import ar.com.mulesoft.command.Command;
import ar.com.mulesoft.filesystem.impl.AbstractFileSystem;
import ar.com.mulesoft.filesystem.impl.Folder;
import ar.com.mulesoft.main.State;

public class ls implements Command {

    private static final String RECURSIVE = "-r";

    @Override
    public void execute(State state, String[] params) {
        Folder currentFolder = state.getCurrent();
        Folder folder;

        if(params.length == 2 && RECURSIVE.equals(params[0]) && !params[1].isEmpty()) {
            try {
                folder = cd.navegate(state.getCurrent(), params[1]);
                printFolderContentRecursive(folder);
            } catch (Exception e) {
            }

        } else if(params.length == 1 && RECURSIVE.equals(params[0])) {
            printFolderContentRecursive(currentFolder);
        } else {
            if(params.length == 1) {
                try {
                    folder = cd.navegate(state.getCurrent(), params[0]);
                    printFolderContent(folder);
                } catch (Exception e) {
                }
            } else {
                printFolderContent(state.getCurrent());
            }

        }
    }
    private void printFolderContent(Folder folder) {
        for(AbstractFileSystem abs: folder.getList()) {
            System.out.println(abs.getName());
        }
    }

    private void printFolderContentRecursive(Folder f) {
        String path = pwd.absolutePath(f);
        System.out.println(path);

        for(AbstractFileSystem abs: f.getList()) {
            if(abs instanceof Folder) {
                printFolderContentRecursive((Folder) abs);
            } else {
                System.out.println(path + "/" + abs.getName());
            }
        }
    }
}