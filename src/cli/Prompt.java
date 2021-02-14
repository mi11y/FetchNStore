package cli;

public class Prompt {

    public PutCommand putCommand;

    public ExitCommand exitCommand;

    public FetchCommand fetchCommand;

    public final boolean OK     = true;
    public final boolean ERROR  = false;

    public boolean shouldExit() {
        return exitCommand.getShouldExit();
    }

    private final String PROMPT         = "> ";
    private final String MSG_ERROR_UNKWN = "Unknown command. Known commands are: put, fetch, exit";

    private String commandMessage;
    public String getCommandMessage() {
        return commandMessage;
    }

    private AbstractDataStore dataStore;

    public Prompt(AbstractDataStore dataStore) {
        init();
        this.dataStore = dataStore;
    }

    public Prompt() {
        this.dataStore = new MapDataStore();

        init();
    }

    private void init() {
        commandMessage = "";
        putCommand = new PutCommand(dataStore);
        fetchCommand = new FetchCommand(dataStore);
        exitCommand = new ExitCommand();
    }

    public String getPROMPT() {
        return PROMPT;
    }

    public boolean execute(String userInput) {
        if(userInput == null || userInput.isBlank()) {
            return false;
        }

        boolean isOK = false;
        String[] tokens = cleanUserInput(userInput);

        if(exitCommand.isExitCommand(tokens)) {
            exitCommand.handle(tokens);
            commandMessage = exitCommand.getCommandMessage();
            isOK = exitCommand.getIsOK();
        }
        else if(putCommand.isPutCommand(tokens)) {
            putCommand.handle(tokens);
            commandMessage = putCommand.getCommandMessage();
            isOK = putCommand.getIsOK();
        }
        else if(fetchCommand.isFetchCommand(tokens)) {
            fetchCommand.handle(tokens);
            commandMessage = fetchCommand.getCommandMessage();
            isOK = fetchCommand.getIsOK();
        }
        else {
            commandMessage = MSG_ERROR_UNKWN;
            isOK = false;
        }

        return isOK;
    }

    private String[] cleanUserInput(String userInput) {

        return userInput
                .trim()
                .replaceAll(" +", " ")
                .split(" ");
    }
}
