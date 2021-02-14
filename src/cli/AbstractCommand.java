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

}
