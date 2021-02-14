package cli;

import org.junit.Assert;
import org.junit.Test;

public class PutCommandTest {

    @Test
    public void shouldRecognizePutCommand() {
        String[] testTokens = {"put", "key", "value"};

        boolean isPutComand = new PutCommand(new MapDataStore()).isPutCommand(testTokens);
        Assert.assertTrue(
                "A valid put command with two params should be recognized",
                isPutComand
        );
    }

    @Test
    public void shouldReconizePutCommandWithExtraParams() {
        String[] testTokens = {"put", "key", "value", "potato"};

        boolean isPutCommand = new PutCommand(new MapDataStore()).isPutCommand(testTokens);
        Assert.assertTrue(
                "A put command with extra params should be recognized",
                isPutCommand
        );
    }

    @Test
    public void shouldRecognizePutCommandWithNoParams() {
        String[] testTokens = {"put"};

        boolean isPutCommand = new PutCommand(new MapDataStore()).isPutCommand(testTokens);
        Assert.assertTrue(
                "A put command with no params should be recognized.",
                isPutCommand
        );
    }

    @Test
    public void shouldRejectNonPutCommand() {
        String[] testTokens = {"potato"};

        boolean isPutCommand = new PutCommand(new MapDataStore()).isPutCommand(testTokens);
        Assert.assertFalse(
                "A non-put command should be rejected",
                isPutCommand
        );
    }

    @Test
    public void shouldSetOKMessage() {
        String[] testTokens = {"put", "key", "value"};

        PutCommand testPutCommand = new PutCommand(new MapDataStore());
        testPutCommand.handle(testTokens);

        Assert.assertEquals(
                "PutCommand class should set the 'ok' command message when given valid key-values",
                "ok",
                testPutCommand.getCommandMessage()
        );
    }

    @Test
    public void shouldSetInvalidSyntaxMessageOnNoParams() {
        String[] testTokens = {"put"};

        PutCommand testPutCommand = new PutCommand(new MapDataStore());
        testPutCommand.handle(testTokens);

        Assert.assertEquals(
                "PutCommand class should set the 'Invalid syntax.' command message on put with no key-values",
                "Invalid syntax.",
                testPutCommand.getCommandMessage()
        );
    }

    @Test
    public void shouldSetInvalidSyntaxMessageOnExtraParams() {
        String[] testTokens = {"put", "key", "value", "potato"};

        PutCommand testPutCommand = new PutCommand(new MapDataStore());
        testPutCommand.handle(testTokens);

        Assert.assertEquals(
                "PutCommand class should set the 'Invalid syntax.' command message on put with extra key params",
                "Invalid syntax.",
                testPutCommand.getCommandMessage()
        );
    }
}