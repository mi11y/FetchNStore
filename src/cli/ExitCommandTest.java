package cli;

import org.junit.Assert;
import org.junit.Test;

public class ExitCommandTest {

    @Test
    public void shouldRecognizeExitCommand() {
        String[] testTokens = {"exit"};

        boolean isExitCommand = new ExitCommand().isMatchingCommand(testTokens);
        Assert.assertTrue(
                "The isExitCommand() method should recognize the exit command token",
                isExitCommand
        );
    }

    @Test
    public void shouldRejectNonExitCommand() {
        String[] testTokens = {"potato"};

        boolean isExitCommand = new ExitCommand().isMatchingCommand(testTokens);
        Assert.assertFalse(
                "The isExitCommand() method should reject a non-exit command token",
                isExitCommand
        );
    }

    @Test
    public void shouldRejectNullCommand() {
        boolean isExitCommand = new ExitCommand().isMatchingCommand(null);
        Assert.assertFalse(
                "The isExitCommand() method should reject null tokens",
                isExitCommand
        );
    }

    @Test
    public void shouldRecognizeExitCommandWithExtras() {
        String[] testTokens = {"exit", "potato"};

        boolean isExitCommand = new ExitCommand().isMatchingCommand(testTokens);
        Assert.assertTrue(
                "The isExitCommand() method should recognize an exit command with extra params",
                isExitCommand
        );
    }

    @Test
    public void shouldSetByeMessage() {
        String[] testTokens = {"exit"};

        ExitCommand testExitCommand = new ExitCommand();
        testExitCommand.handle(testTokens);

        Assert.assertEquals(
                "Given a valid exit command, the command message should be set to 'Bye!'",
                "Bye!",
                testExitCommand.getCommandMessage()
        );
    }

    @Test
    public void shouldSetShouldExit() {
        String[] testTokens = {"exit"};

        ExitCommand testExitCommand = new ExitCommand();
        testExitCommand.handle(testTokens);

        Assert.assertTrue(
                "The shouldExit field/flag should be set to true given a valid exit",
                testExitCommand.getShouldExit()
        );
    }

    @Test
    public void shouldSetInvalidSyntaxMessage() {
        String[] testTokens = {"exit", "potato"};

        ExitCommand testExitCommand = new ExitCommand();
        testExitCommand.handle(testTokens);

        Assert.assertEquals(
                "The invalid syntax message should be set when exit is called with extras",
                "Invalid syntax.",
                testExitCommand.getCommandMessage()
        );
    }

    @Test
    public void shouldSetInvalidSyntaxOnNullTokens() {
        ExitCommand testExitCommand = new ExitCommand();
        testExitCommand.handle(null);

        Assert.assertEquals(
                "The invalid syntax message should be set when exit is called with null tokens",
                "Invalid syntax.",
                testExitCommand.getCommandMessage()
        );
    }
}
