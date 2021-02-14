package cli;

public class Prompt {

    private final PutCommand putCommand;
    private final ExitCommand exitCommand;
    private final FetchCommand fetchCommand;

    public boolean shouldExit() {
        return exitCommand.getShouldExit();
    }

    private String commandMessage;
    public String getCommandMessage() {
        return commandMessage;
    }

    public Prompt() {
        AbstractDataStore dataStore = new MapDataStore();
        putCommand      = new PutCommand(dataStore);
        fetchCommand    = new FetchCommand(dataStore);
        exitCommand     = new ExitCommand();
        commandMessage  = "";

    }

    public String getCursor() {
        return "> ";
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
            commandMessage = "Unknown command. Known commands are: put, fetch, exit";
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
