package cli;

public class FetchCommand extends AbstractCommand {
    private final String COMMAND_FETCH  = "fetch";
    private final String MSG_ERROR_VALUE = "Value not found.";

    private AbstractDataStore dataStore;

    public FetchCommand(AbstractDataStore dataStore) {
        this.dataStore = dataStore;
    }

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
                commandMessage = MSG_ERROR_VALUE;
            }
        }
    }

    public boolean isFetchCommand(String[] tokens) {
        if(tokens != null && tokens[0].equalsIgnoreCase(COMMAND_FETCH)) {
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
