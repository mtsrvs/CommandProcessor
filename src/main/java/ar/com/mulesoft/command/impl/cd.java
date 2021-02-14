package ar.com.mulesoft.command.impl;

import ar.com.mulesoft.command.Command;
import ar.com.mulesoft.command.CommandException;
import ar.com.mulesoft.command.ErrorMessage;
import ar.com.mulesoft.filesystem.impl.Folder;
import ar.com.mulesoft.main.State;

public class cd implements Command {
    private static final String BACK = "..";
    private static final String SEPARATOR = "/";

    @Override
    public void execute(State state, String[] params) throws CommandException {
        if(params.length == 0) {
            System.out.println(ErrorMessage.INVALID_COMMAND);
            throw new CommandException();
        }

        String path = params[0];
        Folder currentFolder = state.getCurrent();

        try {
            state.setCurrent(navegate(currentFolder, path));
        } catch (Exception e) {
            throw new CommandException();
        }
    }

    public static Folder navegate(Folder currentFolder, String param) throws Exception {
        String path = param;
        Folder folder = currentFolder;

        String[] split = path.split(SEPARATOR);

        for(String str: split) {
            if(BACK.equals(str)) {
                if(folder.getParentFolder() != null) {
                    folder = folder.getParentFolder();
                }
            } else if(folder.containsFolder(str)) {
                folder = folder.getFolder(str);

            } else {
                if(split.length == 1) {
                    System.out.println(ErrorMessage.DIRECTORY_NOT_FOUND);
                } else {
                    System.out.println(ErrorMessage.INVALID_PATH);
                }
                throw new Exception();
            }
        }

        return folder;
    }
}
