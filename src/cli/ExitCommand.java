package cli;

/**
 * The ExitCommand is responsible for signaling when the user
 * has correctly requested the program to exit.
 *
 * ExitCommand will set it's commandMessage and shouldExit fields
 * depending if an error was encountered handling the user input.
 */
public class ExitCommand extends AbstractCommand {

    /**
     * ExitCommand sets shouldExit to true when the user
     * has correctly requested the program to terminate.
     */
    private boolean shouldExit;

    /**
     * This method is provided for the Prompt class to
     * check if ExitCommand has determined that the program
     * should terminate.
     * @return A boolean (true) if the program should quit,
     * false otherwise.
     */
    public boolean getShouldExit() {
        return shouldExit;
    }

    public ExitCommand() {
        commandMessage  = "";
        shouldExit      = false;
    }

    /**
     * This method is responsible for setting the commandMessage
     * and, if appropriate, whether the program should exit given the
     * user's input in tokens.
     * @param tokens A String[] array representing the user's input. It is
     *               expected the command to be first in the array, followed by
     *               any parameters
     */
    @Override
    public void handle(String[] tokens) {
        if(tokens == null || hasNoArgs(tokens) == false) {
            commandMessage = MSG_ERROR_INVALID;
        }
        else {
            commandMessage = "Bye!";
            shouldExit = true;
        }
    }

    /**
     * This method is used to determine if ExitCommand is responsible for
     * handling the user's input.
     * @param tokens A String[] array representing the user's input.  It is
     *               expected the command to be first in the array, followed by
     *               any parameters
     * @return A boolean (true) indicating if a command class is responsible for
     *         handling the user's input.
     */
    @Override
    public boolean isMatchingCommand(String[] tokens) {
        return tokens != null && tokens[0].equalsIgnoreCase("exit");
    }

    /**
     * A helper method used by ExitCommand to check if the user's input has
     * no extra parameters after the command token.
     * @param tokens A String[] array representing the user's input. It is
     *               expected the command to be first in the array, followed by
     *               any parameters
     * @return A boolean (true) indicating if the String[] token array only contains
     * the command token.
     */
    private boolean hasNoArgs(String[] tokens) {
        return tokens.length - 1 == 0;
    }

}
