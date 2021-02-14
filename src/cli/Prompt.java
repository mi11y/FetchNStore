package cli;

public class Prompt {

    public boolean shouldExit;

    public final boolean OK     = true;
    public final boolean ERROR  = false;


    private final String PROMPT         = "> ";
    private final String COMMAND_EXIT   = "exit";
    private final String COMMAND_PUT    = "put";
    private final String COMMAND_FETCH  = "fetch";
    private final String MSG_ERROR_INVALID = "Invalid syntax.";
    private final String MSG_ERROR_VALUE = "Value not found.";
    private final String MSG_ERROR_UNKWN = "Unknown command. Known commands are: put, fetch, exit";
    private final String MSG_OK     = "ok";
    private final String MSG_BYE    = "Bye!";

    private String arg1;
    private String arg2;

    private String currentCommand;
    public String getCurrentCommand() {
        return currentCommand;
    }

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
        init();
        this.dataStore = new MapDataStore();
    }

    private void init() {
        shouldExit = false;
        commandMessage = "";
        currentCommand = "";
        arg1 = "";
        arg2 = "";
    }

    public String getPROMPT() {
        return PROMPT;
    }

    public boolean execute(String userInput) {
        if(userInput == null) {
            return false;
        }

        String[] tokens = cleanUserInput(userInput);

        if(validateCommand(tokens) == false) {
            return ERROR;
        }

        return handleCommand();
    }

    private boolean handleCommand() {
        if(currentCommand.equals(COMMAND_EXIT)) {
            commandMessage = MSG_BYE;
            return OK;
        }
        else if(currentCommand.equals(COMMAND_PUT)) {
            commandMessage  = MSG_OK;
            dataStore.create(arg1, arg2);
            return OK;
        }
        else if(currentCommand.equals(COMMAND_FETCH)) {
            String readResult   = dataStore.read(arg1);
            if(readResult.isBlank() == false) {
                commandMessage      = readResult;
                return OK;
            }
            else {
                commandMessage = MSG_ERROR_VALUE;
            }
        }

        return ERROR;
    }

    private String[] cleanUserInput(String userInput) {

        return userInput
                .trim()
                .replaceAll(" +", " ")
                .split(" ");
    }

    private String getFirstArg(String[] tokens) {
        return tokens[1];
    }

    private String getSecondArg(String[] tokens) {
        return tokens[2];
    }

    private boolean validateCommand(String[] tokens) {
        if(tokens == null || tokens.length == 0) {
            return ERROR;
        }

        if(isExitCommand(tokens)) {
            if(hasNoArgs(tokens)) {
                currentCommand = COMMAND_EXIT;
                shouldExit = true;
                return OK;
            }
            else {
                commandMessage = MSG_ERROR_INVALID;
                return ERROR;
            }
        }
        else if(isPutCommand(tokens)) {
            if(hasTwoArgs(tokens)) {
                arg1            = getFirstArg(tokens);
                arg2            = getSecondArg(tokens);
                currentCommand  = COMMAND_PUT;
                return OK;
            }
            else {
                commandMessage = MSG_ERROR_INVALID;
                return ERROR;
            }
        }
        else if(isFetchCommand(tokens)) {
            if(hasOneArg(tokens)) {
                arg1            = getFirstArg(tokens);
                currentCommand  = COMMAND_FETCH;
                return OK;
            }
            else {
                commandMessage  = MSG_ERROR_INVALID;
                return ERROR;
            }
        }
        else {
            commandMessage = MSG_ERROR_UNKWN;
            return ERROR;
        }
    }

    private boolean isExitCommand(String[] tokens) {
        if(tokens[0].equalsIgnoreCase(COMMAND_EXIT)) {
            return true;
        }
        return false;
    }

    private boolean hasNoArgs(String[] tokens) {
        if(tokens.length - 1 == 0) {
            return true;
        }
        return false;
    }

    private boolean isPutCommand(String[] tokens) {
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

    private boolean isFetchCommand(String[] tokens) {
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
}
