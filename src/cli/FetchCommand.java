package cli;

public class FetchCommand extends AbstractCommand {

    private final AbstractDataStore dataStore;

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
                commandMessage = "Value not found.";
            }
        }
    }

    public boolean isFetchCommand(String[] tokens) {
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
