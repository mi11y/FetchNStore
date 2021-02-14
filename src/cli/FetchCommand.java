package cli;

/**
 * The FetchCommand class is responsible for handling all fetch commands.
 * It will fetch from the provided Data Store if the user's commands
 * are not malformed.
 *
 * FetchCommand will set the commandMessage to the fetched value if
 * it is found, or to "Value not found." if that key is not contained in
 * the data store. It will set commandMessage to "Invalid Syntax." if the
 * fetch command is malformed.
 */
public class FetchCommand extends AbstractCommand {

    /**
     * A data store to be used for fetching key-values.
     */
    private final AbstractDataStore dataStore;

    /**
     * Creates a FetchCommand handling class. The class requires an
     * AbstractDataStore to fetch values from. It can be any data store
     * that implements Create() such that it behaves as both create
     * and update. The data store must also implement Read(). Others are
     * not used.
     *
     * @param dataStore An AbstractDataStore to be used for fetching values from.
     */
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

    /**
     * This helper method is responsible for checking if the user's input contains
     * only one parameter/arg following the command token. It will return true if
     * only one token follows the command token, false otherwise.
     *
     * @param tokens A String[] array representing the user's input. It is
     *               expected the command to be first in the array, followed by
     *               any parameters
     * @return A boolean (true) indicating if only one token follows the command token,
     * false otherwise.
     */
    private boolean hasOneArg(String[] tokens) {
        if(tokens.length - 1 == 1) {
            return true;
        }
        return false;
    }

}
