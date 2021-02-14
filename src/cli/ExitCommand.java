package cli;

public class ExitCommand {

    private final String COMMAND_EXIT   = "exit";
    private final String MSG_ERROR_INVALID = "Invalid syntax.";
    private final String MSG_BYE    = "Bye!";

    private String commandMessage;
    public String getCommandMessage() {
        return commandMessage;
    }

    private boolean isOK;
    public boolean getIsOK() {
        return isOK;
    }

    private boolean shouldExit;
    public boolean getShouldExit() {
        return shouldExit;
    }

    public ExitCommand() {
        commandMessage  = "";
        isOK            = true;
        shouldExit      = false;
    }

    public void handle(String[] tokens) {
        if(hasNoArgs(tokens) == false) {
            commandMessage = MSG_ERROR_INVALID;
            isOK = false;
        }
        else {
            commandMessage = MSG_BYE;
            shouldExit = true;
            isOK = true;
        }
    }

    public boolean isExitCommand(String[] tokens) {
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

}
