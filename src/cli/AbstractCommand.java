package cli;

public class AbstractCommand {
    protected final String MSG_ERROR_INVALID = "Invalid syntax.";

    protected String commandMessage;
    public String getCommandMessage() {
        return commandMessage;
    }

    protected boolean isOK;
    public boolean getIsOK() {
        return isOK;
    }

    protected String getFirstArg(String[] tokens) {
        return tokens[1];
    }

    protected String getSecondArg(String[] tokens) {
        return tokens[2];
    }

}
