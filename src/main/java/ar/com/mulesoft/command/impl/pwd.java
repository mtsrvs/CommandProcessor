package ar.com.mulesoft.command.impl;

import ar.com.mulesoft.command.Command;
import ar.com.mulesoft.filesystem.impl.Folder;
import ar.com.mulesoft.main.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class pwd implements Command {

    @Override
    public void execute(State state, String[] params) {
        String path = pwd.absolutePath(state.getCurrent());
        System.out.println(path);
        return;
    }

    public static String absolutePath(Folder current) {
        String path = "";
        Folder f = current;

        List<String> names = new ArrayList<>();

        while(f.getParentFolder() != null) {
            names.add(f.getName());
            f = f.getParentFolder();
        }
        names.add(f.getName());

        Collections.reverse(names);

        for(String str: names) {
            path += "/" + str;
        }

        return path;
    }
}
