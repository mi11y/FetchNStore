package cli;

import java.io.Console;

public class Prompt {

    public final boolean OK     = true;
    public final boolean ERROR  = false;

    private final String PROMPT         = "> ";
    private final String COMMAND_EXIT   = "exit";
    private final String COMMAND_PUT    = "put";
    private final String ERROR_MSG_INVALID = "Invalid syntax.";

    private String requestedCommand;
    private String arg1;
    private String arg2;
    private String currentCommand;

    public boolean shouldExit;

    private String lastErrorMessage;

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    public Prompt() {
        shouldExit = false;
        lastErrorMessage = "";
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

        if(isExitCommand(tokens) && hasNoArgs(tokens)) {
            currentCommand = COMMAND_EXIT;
            shouldExit = true;
        }
        else if(isPutCommand(tokens) && hasTwoArgs(tokens)) {
            currentCommand = COMMAND_PUT;
        }
        else {
            lastErrorMessage = ERROR_MSG_INVALID;
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
