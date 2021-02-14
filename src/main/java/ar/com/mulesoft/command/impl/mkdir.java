package ar.com.mulesoft.command.impl;

import ar.com.mulesoft.command.Command;
import ar.com.mulesoft.command.CommandException;
import ar.com.mulesoft.command.ErrorMessage;
import ar.com.mulesoft.command.ValidationName;
import ar.com.mulesoft.filesystem.impl.AbstractFileSystem;
import ar.com.mulesoft.filesystem.impl.Folder;
import ar.com.mulesoft.main.State;

import java.util.Set;

public class mkdir implements Command {

    @Override
    public void execute(State state, String[] params) throws CommandException {
        Folder currentFolder = state.getCurrent();

        if(!ValidationName.validate(params[0])) {
            System.out.println(ErrorMessage.INVALID_FILE_OR_FOLDER_NAME);
            throw new CommandException();
        }

        if(!existFolder(currentFolder.getList(), params[0])) {
            Folder newFolder = new Folder(params[0], currentFolder);
            currentFolder.add(newFolder);
        } else {
            System.out.println(ErrorMessage.DIRECTORY_ALREADY_EXISTS);
            throw new CommandException();
        }
    }

    private boolean existFolder(Set<AbstractFileSystem> list, String folderName) {
        for(AbstractFileSystem abs: list) {
            if(abs instanceof Folder) {
                Folder f = (Folder) abs;
                if(f.getName().equals(folderName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
