package cli;

public class ExitCommand extends AbstractCommand {

    private final String COMMAND_EXIT   = "exit";
    private final String MSG_BYE    = "Bye!";

    private boolean shouldExit;
    public boolean getShouldExit() {
        return shouldExit;
    }

    public ExitCommand() {
        commandMessage  = "";
        shouldExit      = false;
    }

    public void handle(String[] tokens) {
        if(tokens == null || hasNoArgs(tokens) == false) {
            commandMessage = MSG_ERROR_INVALID;
        }
        else {
            commandMessage = MSG_BYE;
            shouldExit = true;
        }
    }

    public boolean isExitCommand(String[] tokens) {
        return tokens != null && tokens[0].equalsIgnoreCase(COMMAND_EXIT);
    }

    private boolean hasNoArgs(String[] tokens) {
        return tokens.length - 1 == 0;
    }

}
