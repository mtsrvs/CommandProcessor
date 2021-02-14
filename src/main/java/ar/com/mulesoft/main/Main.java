package ar.com.mulesoft.main;

import ar.com.mulesoft.command.ErrorMessage;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final String COMMANDS = "cd|ls|mkdir|pwd|quit|touch";
    private static final String LOAD_STATE = "-loadstate";
    private static final String SPACE = " ";
    private static final String QUIT_COMMAND = "quit";
    private static final String USERNAME_COMMANDLINE = "mulesoft> ";

    public static void main(String[] args) {
        boolean isRunning = true;
        CommandLineShell commandLineShell = setCommandLineShell(args);

        while(isRunning) {
            Input input = readInput();
            if(input != null) {
                if(QUIT_COMMAND.equals(input.command)) {
                    isRunning = false;
                    commandLineShell.save();
                }
                commandLineShell.execute(input.command, input.params);
            }
        }
        return;
    }

    private static boolean validateCommand(String[] line) {
        return (line.length > 0 && COMMANDS.contains(line[0]));
    }

    private static boolean isEmptyLine(String[] line) {
        return line.length == 0;
    }

    private static CommandLineShell setCommandLineShell(String[] args) {
        if(args.length == 1 && LOAD_STATE.equals(args[0])) {
            return CommandLineShell.load();
        } else {
            return new CommandLineShell();
        }
    }

    private static Input readInput() {
        System.out.printf(USERNAME_COMMANDLINE);
        Scanner s = new Scanner(System.in);
        String line = s.nextLine();
        String[] split = line.split(SPACE);
        Input input = null;

        if(isEmptyLine(split)) {
            input = new Input(null, null);
        } else if(validateCommand(split)) {
            input = new Input(split[0], Arrays.copyOfRange(split, 1, split.length));
        } else {
            System.out.println(ErrorMessage.UNRECOGNIZED_COMMAND);
        }

        return input;
    }
}