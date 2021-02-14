package ar.com.mulesoft.command;

public class ValidationName {

    public static boolean validate(String name) {
        return !(name.length() >= 100);
    }
}
