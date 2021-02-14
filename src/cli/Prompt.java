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

    public void execute(String userInput) {
        if(userInput == null || userInput.isBlank()) {
            return;
        }

        String[] tokens = cleanUserInput(userInput);

        if(exitCommand.isMatchingCommand(tokens)) {
            exitCommand.handle(tokens);
            commandMessage = exitCommand.getCommandMessage();
        }
        else if(putCommand.isMatchingCommand(tokens)) {
            putCommand.handle(tokens);
            commandMessage = putCommand.getCommandMessage();
        }
        else if(fetchCommand.isMatchingCommand(tokens)) {
            fetchCommand.handle(tokens);
            commandMessage = fetchCommand.getCommandMessage();
        }
        else {
            commandMessage = "Unknown command. Known commands are: put, fetch, exit";
        }

    }

    private String[] cleanUserInput(String userInput) {

        return userInput
                .trim()
                .replaceAll(" +", " ")
                .split(" ");
    }
}
