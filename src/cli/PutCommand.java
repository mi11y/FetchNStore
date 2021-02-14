package cli;

public class PutCommand extends AbstractCommand {

    private final String COMMAND_PUT    = "put";
    private final String MSG_OK     = "ok";


    private AbstractDataStore dataStore;

    public PutCommand(AbstractDataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void handle(String[] tokens) {
        if(tokens == null || hasTwoArgs(tokens) == false) {
            commandMessage = MSG_ERROR_INVALID;
        }
        else {
            dataStore.create(getFirstArg(tokens), getSecondArg(tokens));
            commandMessage = MSG_OK;
        }
    }

    public boolean isPutCommand(String[] tokens) {
        return tokens != null && tokens[0].equalsIgnoreCase(COMMAND_PUT);
    }

    private boolean hasTwoArgs(String[] tokens) {
        return tokens != null && tokens.length - 1 == 2;
    }



}
