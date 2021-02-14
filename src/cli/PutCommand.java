package cli;

public class PutCommand extends AbstractCommand {

    private final String COMMAND_PUT    = "put";
    private final String MSG_OK     = "ok";


    private AbstractDataStore dataStore;

    public PutCommand(AbstractDataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void handle(String[] tokens) {
        if(hasTwoArgs(tokens) == false) {
            commandMessage = MSG_ERROR_INVALID;
            isOK = false;
        }
        else {
            dataStore.create(getFirstArg(tokens), getSecondArg(tokens));
            commandMessage = MSG_OK;
            isOK = true;
        }
    }

    public boolean isPutCommand(String[] tokens) {
        if(tokens[0].equalsIgnoreCase(COMMAND_PUT)) {
            return true;
        }
        return false;
    }

    private boolean hasTwoArgs(String[] tokens) {
        if(tokens.length - 1 == 2) {
            return true;
        }
        return false;
    }



}
