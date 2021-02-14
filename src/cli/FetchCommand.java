package cli;

public class FetchCommand extends AbstractCommand {

    private final AbstractDataStore dataStore;

    public FetchCommand(AbstractDataStore dataStore) {
        this.dataStore = dataStore;
    }

    /**
     * This method is responsible for handling the user's input for the fetch
     * command. It will set the commandMessage to the return value for a key if
     * it is found. Otherwise it will store "Value not found." in the commandMessage.
     *
     * If the user's input is malformed or erroneous, the commandMessage is set
     * to "Invalid syntax."
     * @param tokens A String[] array representing the user's input. It is
     *               expected the command to be first in the array, followed by
     */
    @Override
    public void handle(String[] tokens) {
        if(tokens == null || hasOneArg(tokens) == false) {
            commandMessage = MSG_ERROR_INVALID;
        }
        else {
            String readResult   = dataStore.read(getFirstArg(tokens));
            if(readResult.isBlank() == false) {
                commandMessage      = readResult;
            }
            else {
                commandMessage = "Value not found.";
            }
        }
    }

    /**
     * This method is used to determine if FetchCommand is responsible for
     * handling the user's input.
     * @param tokens A String[] array representing the user's input.  It is
     *               expected the command to be first in the array, followed by
     *               any parameters
     * @return A boolean (true) indicating if a command class is responsible for
     *         handling the user's input.
     */
    @Override
    public boolean isMatchingCommand(String[] tokens) {
        if(tokens != null && tokens[0].equalsIgnoreCase("fetch")) {
            return true;
        }
        return false;
    }

    private boolean hasOneArg(String[] tokens) {
        if(tokens.length - 1 == 1) {
            return true;
        }
        return false;
    }

}
