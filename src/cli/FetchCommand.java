package cli;

public class FetchCommand {
    private final String COMMAND_FETCH  = "fetch";
    private final String MSG_ERROR_INVALID = "Invalid syntax.";
    private final String MSG_ERROR_VALUE = "Value not found.";


    private String commandMessage;
    public String getCommandMessage() {
        return commandMessage;
    }

    private boolean isOK;
    public boolean getIsOK() {
        return isOK;
    }


    private AbstractDataStore dataStore;

    private String arg1;

    public FetchCommand(AbstractDataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void handle(String[] tokens) {
        if(hasOneArg(tokens) == false) {
            commandMessage = MSG_ERROR_INVALID;
            isOK = false;
        }
        else {
            String readResult   = dataStore.read(getFirstArg(tokens));
            if(readResult.isBlank() == false) {
                commandMessage      = readResult;
                isOK = true;
            }
            else {
                commandMessage = MSG_ERROR_VALUE;
                isOK = false;
            }
        }
    }

    public boolean isFetchCommand(String[] tokens) {
        if(tokens[0].equalsIgnoreCase(COMMAND_FETCH)) {
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

    private String getFirstArg(String[] tokens) {
        return tokens[1];
    }

}
