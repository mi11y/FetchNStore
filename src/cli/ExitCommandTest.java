package cli;

import org.junit.Assert;
import org.junit.Test;

public class ExitCommandTest {

    @Test
    public void shouldRecognizeExitCommand() {
        String[] testTokens = {"exit"};

        boolean isExitCommand = new ExitCommand().isExitCommand(testTokens);
        Assert.assertTrue(
                "The exit command class should recognize the exit command token",
                isExitCommand
        );
    }

    @Test
    public void shouldRejectNonExitCommand() {
        String[] testTokens = {"potato"};

        boolean isExitCommand = new ExitCommand().isExitCommand(testTokens);
        Assert.assertFalse(
                "The exit command class should reject a non-exit command token",
                isExitCommand
        );
    }

    @Test
    public void shouldRecognizeExitCommandWithExtras() {
        String[] testTokens = {"exit", "potato"};

        boolean isExitCommand = new ExitCommand().isExitCommand(testTokens);
        Assert.assertTrue(
                "The exit command class should recognize an exit command with extra params",
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

}
