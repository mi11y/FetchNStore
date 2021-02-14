package cli;

/**
 * Prompt is responsible for delegating the user's input from the terminal
 * to the appropriate command class. After the command class has finished
 * handling the user's input, Prompt will retrieve the resulting commandMessage
 * to be returned to the terminal.
 *
 * Prompt is responsible for cleaning the user's input, producing an array
 * of Strings for the command classes to use.
 */
public class Prompt {

    private final PutCommand putCommand;
    private final ExitCommand exitCommand;
    private final FetchCommand fetchCommand;

    /**
     * ExitCommand class is responsible for determining if the program should exit.
     * This method is used by the key-value script to check if that is the case.
     * @return A boolean (true) if the program should quit, false otherwise.
     */
    public boolean shouldExit() {
        return exitCommand.getShouldExit();
    }

    /**
     * After a command class has finished handling the user's input,
     * Prompt will store the command class' commandMessage here.
     */
    private String commandMessage;

    /**
     * This method is used by the key-value script to print the results from
     * handling the user's latest input.
     * @return A String containing the results of the latest user input.
     */
    public String getCommandMessage() {
        return commandMessage;
    }

    /**
     * Creates a Prompt class responsible for delegating the handling
     * of the user's input. Prompt will by default use a MapDataStore
     * for use in "put" and "fetch" commands.
     */
    public Prompt() {
        AbstractDataStore dataStore = new MapDataStore();
        putCommand      = new PutCommand(dataStore);
        fetchCommand    = new FetchCommand(dataStore);
        exitCommand     = new ExitCommand();
        commandMessage  = "";

    }

    /**
     * Provides the cursor ("> ") for use in the terminal.
     * @return A String containing the cursor to be printed. Does not
     * contain a newline.
     */
    public String getCursor() {
        return "> ";
    }

    /**
     * This method is called by the key-value script for handling the raw user input.
     * This method will delegate to the appropriate command class for handling the input.
     *
     * This method will clean and tokenize the user's input String into a String[] array
     * for use by the command classes.
     *
     * Upon handling the user's input, this method will update Prompt's commandMessage
     * with the command class' commandMessage.
     *
     * If no command class can claim responsiblity for the user's input, the commandMessage
     * is set to "Unknown command. Known commands are: put, fetch, exit" to be printed
     * to the terminal.
     * @param userInput A String containing the user's raw input from the terminal
     */
    public void execute(String userInput) {
        if(userInput == null || userInput.isBlank()) {
            return;
        }

        String[] tokens = cleanUserInput(userInput);

        if(exitCommand.isMatchingCommand(tokens)) {
            exitCommand.handle(tokens);
            commandMessage = exitCommand.getCommandMessage();
        }
        else if(putCommand.isMatchingCommand(tokens)) {
            putCommand.handle(tokens);
            commandMessage = putCommand.getCommandMessage();
        }
        else if(fetchCommand.isMatchingCommand(tokens)) {
            fetchCommand.handle(tokens);
            commandMessage = fetchCommand.getCommandMessage();
        }
        else {
            commandMessage = "Unknown command. Known commands are: put, fetch, exit";
        }

    }

    /**
     * This helper method is responsible for removing extra whitespace from the user's
     * raw input and splitting it into tokens, returning tokens in a String[] array.
     * @param userInput The user's input as a String.
     * @return A String[] array containing the user's input as tokens with extra
     * whitespace removed.
     */
    private String[] cleanUserInput(String userInput) {

        return userInput
                .trim()
                .replaceAll("( |\\t)+", " ")
                .split(" ");
    }
}
