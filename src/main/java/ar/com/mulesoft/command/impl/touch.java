package ar.com.mulesoft.command.impl;

import ar.com.mulesoft.command.Command;
import ar.com.mulesoft.command.CommandException;
import ar.com.mulesoft.command.ErrorMessage;
import ar.com.mulesoft.command.ValidationName;
import ar.com.mulesoft.filesystem.impl.File;
import ar.com.mulesoft.filesystem.impl.Folder;
import ar.com.mulesoft.main.State;

public class touch implements Command {
    @Override
    public void execute(State state, String[] params) throws CommandException {
        Folder currentFolder = state.getCurrent();
        if(params.length == 0) {
            System.out.println(ErrorMessage.INVALID_COMMAND);
            throw new CommandException();
        }

        if(!ValidationName.validate(params[0])) {
            System.out.println(ErrorMessage.INVALID_FILE_OR_FOLDER_NAME);
            throw new CommandException();
        }

        File file = new File(params[0], currentFolder);
        currentFolder.add(file);
    }
}
