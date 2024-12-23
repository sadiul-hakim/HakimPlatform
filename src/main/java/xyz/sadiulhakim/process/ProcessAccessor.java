package xyz.sadiulhakim.process;

import java.util.List;

public class ProcessAccessor {

    private static final List<String> CLEAR_COMMAND_ON_WINDOWS = List.of("cmd", "/c", "cls");

    private ProcessAccessor() {
    }

    public static void clear() {

        try {
            new ProcessBuilder(CLEAR_COMMAND_ON_WINDOWS).inheritIO().start().waitFor();
        } catch (Exception ignore) {
        }
    }
}
