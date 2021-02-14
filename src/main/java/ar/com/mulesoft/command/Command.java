package ar.com.mulesoft.command;

import ar.com.mulesoft.main.State;

public interface Command {
    void execute(State state, String[] params) throws CommandException;
}
