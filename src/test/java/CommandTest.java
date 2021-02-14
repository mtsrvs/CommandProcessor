import ar.com.mulesoft.command.CommandException;
import ar.com.mulesoft.command.impl.cd;
import ar.com.mulesoft.command.impl.mkdir;
import ar.com.mulesoft.command.impl.touch;
import ar.com.mulesoft.main.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class CommandTest {

    @Test
    void moreThan100CharacterFileNameTest() {
        String name = "nombreMuyLargoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo";

        Executable executable = () -> {
            State state = new State();
            touch touch = new touch();
            touch.execute(state, new String[]{name});
        };

        Assertions.assertThrows(CommandException.class, executable);
    }

    @Test
    void lessThan100CharacterFileNameTest() {
        String name = "nombreMuyLargoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo";

        Executable executable = () -> {
            State state = new State();
            touch touch = new touch();
            touch.execute(state, new String[]{name});
        };

        Assertions.assertDoesNotThrow(executable);
    }

    @Test
    void noNameFileTest() {
        Executable executable = () -> {
            State state = new State();
            touch touch = new touch();
            touch.execute(state, new String[]{});
        };

        Assertions.assertThrows(CommandException.class, executable);
    }

    @Test
    void invalidPathTest() {
        String folder1 = "folder1";
        String folder2 = "folder2";
        String folder3 = "folder3";
        String backPath = "../..";
        String invalidPath = "folder1/folder2/sarasa";

        Executable executable = () -> {
            State state = new State();
            mkdir mkdir = new mkdir();
            cd cd = new cd();

            mkdir.execute(state, new String[]{folder1});
            cd.execute(state, new String[]{folder1});
            mkdir.execute(state, new String[]{folder2});
            cd.execute(state, new String[]{folder2});
            mkdir.execute(state, new String[]{folder3});
            cd.execute(state, new String[]{backPath});
            cd.execute(state, new String[]{invalidPath});
        };

        Assertions.assertThrows(CommandException.class, executable);
    }

    @Test
    void moreThan100CharacterFolderNameTest() {
        String folder1 = "nombreMuyLargoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo";

        Executable executable = () -> {
            State state = new State();
            mkdir mkdir = new mkdir();

            mkdir.execute(state, new String[]{folder1});
        };

        Assertions.assertThrows(CommandException.class, executable);
    }
}
