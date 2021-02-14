package ar.com.mulesoft.main;

import ar.com.mulesoft.command.CommandException;
import ar.com.mulesoft.command.impl.*;

public class CommandLine {
    private State state;

    public CommandLine() {
        this.state = new State();
    }

    public void execute(String command, String[] params) {
        try {
            switch (command) {
                case "cd":
                    cd c = new cd();
                    c.execute(this.state, params);
                    break;
                case "touch":
                    touch t = new touch();
                    t.execute(this.state, params);
                    break;
                case "ls":
                    ls l = new ls();
                    l.execute(this.state, params);
                    break;
                case "mkdir":
                    mkdir m = new mkdir();
                    m.execute(this.state, params);
                    break;
                case "pwd":
                    pwd p = new pwd();
                    p.execute(this.state, params);
                    break;
                default:
                    break;
            }
        } catch (CommandException e) {
            //DO NOTHING
        }
    }

    public void save() {
        this.state.save();
    }

    public static CommandLine load() {
        CommandLine commandLine = new CommandLine();
        commandLine.state = State.load();

        return commandLine;
    }
}
