package cli;

/**
 * The AbstractCommand class serves as a base class for the three main
 * commands. It contains common method implementations, but should not
 * be instantiated.
 */
public abstract class AbstractCommand {

    /**
     * If a known command is malformed, this error message should
     * be returned to the Prompt for displaying to the terminal.
     */
    protected final String MSG_ERROR_INVALID = "Invalid syntax.";

    /**
     * All commands shall store their response/result to a command
     * in this field.
     */
    protected String commandMessage;

    /**
     * The Prompt class expects a command's response/result to be
     * available by this method. Commands should store their response
     * in the commandMessage field.
     * @return A command's response/result to a user's input.
     */
    public String getCommandMessage() {
        return commandMessage;
    }

    /**
     * This method will return the first arg/param of a list of
     * tokens from the prompt. It is up to the subclass to
     * validate the tokens to not be null and to be the correct
     * length.
     * @param tokens An array of strings representing the user's
     *               given command and any following parameters.
     * @return The first parameter/arg token (the token after the
     * given command token).
     */
    protected String getFirstArg(String[] tokens) {
        return tokens[1];
    }

    /**
     * This method will return the second arg/param of a list of
     * tokens from the prompt. It is up to the subclass to
     * validate the tokens to not be null and to be the correct
     * length.
     * @param tokens An array of strings representing the user's
     *               given command and any following parameters.
     * @return The second parameter/arg token of the user's input (
     * the third element in the Array of tokens).
     */
    protected String getSecondArg(String[] tokens) {
        return tokens[2];
    }

    /**
     * All commands shall handler user input in the form of a String array
     * of tokens. It is up to class calling the command to remove extra whitespace
     * and split the user's input.
     * @param tokens A String[] array representing the user's input. It is
     *               expected the command to be first in the array, followed by
     *               any parameters
     */
    public void handle(String[] tokens) { }


    /**
     * All commands should implement this method to indicate to a caller
     * whether the class is responsible for handling a command.
     *
     * @param tokens A String[] array representing the user's input.  It is
     *               expected the command to be first in the array, followed by
     *               any parameters
     * @return A boolean (true) indicating if a command class is responsible for
     * handling the user's input.
     */
    public boolean isMatchingCommand(String[] tokens) { return false; }
}
