package cli;

public class PutCommand extends AbstractCommand {

    private final AbstractDataStore dataStore;

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

    private boolean hasTwoArgs(String[] tokens) {
        return tokens != null && tokens.length - 1 == 2;
    }



}
