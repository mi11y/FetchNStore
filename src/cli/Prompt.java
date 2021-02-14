package cli;

public class Prompt {

    public final boolean OK     = true;
    public final boolean ERROR  = false;

    private final String PROMPT         = "> ";
    private final String COMMAND_EXIT   = "exit";
    private final String COMMAND_PUT    = "put";
    private final String MSG_ERROR_INVALID = "Invalid syntax.";
    private final String MSG_ERROR_UNKWN = "Unknown command. Known commands are: put, fetch, exit";
    private final String MSG_OK     = "ok";
    private final String MSG_BYE    = "Bye!";

    private String requestedCommand;
    private String arg1;
    private String arg2;
    private String currentCommand;
    public String getCurrentCommand() {
        return currentCommand;
    }

    public boolean shouldExit;

    private String commandMessage;
    public String getCommandMessage() {
        return commandMessage;
    }

    public Prompt() {
        shouldExit = false;
        commandMessage = "";
        currentCommand = "";
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

        return OK;
    }

    private String[] cleanUserInput(String userInput) {

        return userInput
                .trim()
                .replaceAll(" +", " ")
                .split(" ");
    }

    private boolean validateCommand(String[] tokens) {
        if(tokens == null || tokens.length == 0) {
            return ERROR;
        }

        if(isExitCommand(tokens)) {
            if(hasNoArgs(tokens)) {
                commandMessage = MSG_BYE;
                currentCommand = COMMAND_EXIT;
                shouldExit = true;
            }
            else {
                commandMessage = MSG_ERROR_INVALID;
                return ERROR;
            }
        }
        else if(isPutCommand(tokens)) {
            if(hasTwoArgs(tokens)) {
                commandMessage = MSG_OK;
                currentCommand = COMMAND_PUT;
            }
            else {
                commandMessage = MSG_ERROR_INVALID;
                return ERROR;
            }
        }
        else {
            commandMessage = MSG_ERROR_UNKWN;
            return ERROR;
        }

        return OK;
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
}
