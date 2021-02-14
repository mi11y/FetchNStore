package cli;

public class PutCommand extends AbstractCommand {

    private final AbstractDataStore dataStore;

    public PutCommand(AbstractDataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void handle(String[] tokens) {
        if(tokens == null || hasTwoArgs(tokens) == false) {
            commandMessage = MSG_ERROR_INVALID;
        }
        else {
            dataStore.create(getFirstArg(tokens), getSecondArg(tokens));
            commandMessage = "ok";
        }
    }

    public boolean isPutCommand(String[] tokens) {
        return tokens != null && tokens[0].equalsIgnoreCase("put");
    }

    private boolean hasTwoArgs(String[] tokens) {
        return tokens != null && tokens.length - 1 == 2;
    }



}
