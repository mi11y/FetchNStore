package cli;

import java.io.Console;

public class Prompt {

    private final String PROMPT = "> ";
    private Console CLIConsole;

    public static class FailedToGetConsoleException extends Exception {}

    /**
     * A prompt depends on the console for user input from the terminal.
     * Throws an exception if the prompt object cannot be accessed.
     * @throws FailedToGetConsoleException
     */
    public Prompt() throws FailedToGetConsoleException {
        CLIConsole = System.console();

        if(CLIConsole == null) {
            throw new FailedToGetConsoleException();
        }
    }

    /**
     * Prints a "> " line to standard out without a new line.
     * @return A string representing the prompt.
     */
    public String printCursor() {
        System.out.print(PROMPT);
        return PROMPT;
    }

    /**
     * Awaits input from the user via the terminal. If no input is provided,
     * the empty string is returned.
     * @return
     */
    public String awaitLineFromConsole() {
        String userInput = CLIConsole.readLine();
        return userInput == null ? "" : userInput;
    }

    public Boolean shouldLoop() {
        return true;
    }
}
