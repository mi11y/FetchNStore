package cli;

public class UpdateCommand extends AbstractCommand{

    private final AbstractDataStore dataStore;

    public UpdateCommand(AbstractDataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void handle(String[] tokens) {
       if(tokens == null || hasTwoArgs(tokens) == false) {
           commandMessage = "Invalid syntax";
       }
       else if(dataStore.read(getFirstArg(tokens)).isBlank()) {
           commandMessage = "Key does not exist";
       }
       else {
           dataStore.update(getFirstArg(tokens), getSecondArg(tokens));
           commandMessage = "ok";
       }
    }

    @Override
    public boolean isMatchingCommand(String[] tokens) {
        return tokens != null && tokens.length > 0 && tokens[0].equalsIgnoreCase("update");
    }

    public boolean hasTwoArgs(String[] tokens) {
        return tokens != null && tokens.length - 1 == 2;
    }
}
