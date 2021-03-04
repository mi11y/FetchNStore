package cli;

/**
 * This class is responsible for handling all put commands. It will
 * store all key-value pairs in the provided data store.
 *
 * PutCommand will set the commandMessage to "ok" if the key-value pair
 * is successfully stored. If the command is malformed, the commandMessage
 * is set to "Invalid syntax.".
 */
public class PutCommand extends AbstractCommand {

    /**
     * A data store to be used for inserting key-values.
     */
    private final AbstractDataStore dataStore;

    /**
     * Creates a PutCommand handling class. This class
     * requires an AbstractDataStore to insert key-value pairs into.
     * The data store must implement Create() such that it behaves as
     * both create and update. The data store must also implement Read().
     * Others are not used.
     * @param dataStore An AbstractDataStore to be used for inserting key-values pairs.
     */
    public PutCommand(AbstractDataStore dataStore) {
        this.dataStore = dataStore;
    }

    /**
     * This method is responsible for handling the user's input for the Put
     * command. It will set the commandMessage to "ok" if the user's input
     * the key-value pair is successfully stored. It will set commandMessage
     * to "Invalid syntax." if the command is malformed.
     *
     * @param tokens A String[] array representing the user's input. It is
     *               expected the command to be first in the array, followed by
     */
    @Override
    public void handle(String[] tokens) {
        if(tokens == null || hasTwoArgs(tokens) == false) {
            commandMessage = MSG_ERROR_INVALID;
        }
        else if(dataStore.read(getFirstArg(tokens)).isBlank() == false) {
            commandMessage = "Key already defined.";
        }
        else {
            dataStore.create(getFirstArg(tokens), getSecondArg(tokens));
            commandMessage = "ok";
        }
    }

    /**
     * This method is used to determine if PutCommand is responsible for
     * handling the user's input.
     * @param tokens A String[] array representing the user's input.  It is
     *               expected the command to be first in the array, followed by
     *               any parameters
     * @return A boolean (true) indicating if a command class is responsible for
     *         handling the user's input.
     */
    @Override
    public boolean isMatchingCommand(String[] tokens) {
        return tokens != null && tokens[0].equalsIgnoreCase("put");
    }

    /**
     * This helper method is responsible for checking if the user's input contains
     * exactly two parameters/args following the command token.
     *
     * @param tokens A String[] array representing the user's input. It is
     *               expected the command to be first in the array, followed by
     *               any parameters
     * @return A boolean (true) indicating if two tokens follow the command token,
     * false otherwise.
     */
    private boolean hasTwoArgs(String[] tokens) {
        return tokens != null && tokens.length - 1 == 2;
    }

}
