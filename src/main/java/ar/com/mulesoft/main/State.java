package ar.com.mulesoft.main;

import ar.com.mulesoft.filesystem.impl.Folder;

import java.io.*;

public class State implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private static final String SAVEFILE_NAME = "state.save";
    private static final String ROOT_FOLDER = "root";
    private static final Folder NO_PARENT_FOLDER = null;

    private Folder root;
    private Folder current;

    public State() {
        this.root = new Folder(ROOT_FOLDER, NO_PARENT_FOLDER);
        this.current = root;
    }

    public Folder getCurrent() {
        return current;
    }

    public void setCurrent(Folder folder) {
        this.current = folder;
    }

    public void save() {
        try {
            FileOutputStream fileOut = new FileOutputStream(SAVEFILE_NAME);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
//            System.out.printf("Serialized data is saved in state.save");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static State load() {
        State state;
        try {
            File file = new File(SAVEFILE_NAME);
            if(file.exists()) {
                FileInputStream fileIn = new FileInputStream(SAVEFILE_NAME);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                state = (State) in.readObject();
                in.close();
                fileIn.close();
            } else {
                //si el parametro -loadstate esta setteado pero no existe el archivo, lo creo de cero.
                state = new State();
            }
        } catch (Exception e) {
//            System.out.println("Error loading savestate");
            return null;
        }

        return state;
    }
}
