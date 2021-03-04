package cli;

import org.junit.Assert;
import org.junit.Test;

public class PutCommandTest {

    @Test
    public void shouldRecognizePutCommand() {
        String[] testTokens = {"put", "key", "value"};

        boolean isPutComand = new PutCommand(new MapDataStore()).isMatchingCommand(testTokens);
        Assert.assertTrue(
                "A valid put command with two params should be recognized",
                isPutComand
        );
    }

    @Test
    public void shouldReconizePutCommandWithExtraParams() {
        String[] testTokens = {"put", "key", "value", "potato"};

        boolean isPutCommand = new PutCommand(new MapDataStore()).isMatchingCommand(testTokens);
        Assert.assertTrue(
                "A put command with extra params should be recognized",
                isPutCommand
        );
    }

    @Test
    public void shouldRecognizePutCommandWithNoParams() {
        String[] testTokens = {"put"};

        boolean isPutCommand = new PutCommand(new MapDataStore()).isMatchingCommand(testTokens);
        Assert.assertTrue(
                "A put command with no params should be recognized.",
                isPutCommand
        );
    }

    @Test
    public void shouldRejectNonPutCommand() {
        String[] testTokens = {"potato"};

        boolean isPutCommand = new PutCommand(new MapDataStore()).isMatchingCommand(testTokens);
        Assert.assertFalse(
                "A non-put command should be rejected",
                isPutCommand
        );
    }

    @Test
    public void shouldRejectNullCommand() {
        boolean isPutCommand = new PutCommand(new MapDataStore()).isMatchingCommand(null);
        Assert.assertFalse(
                "The isPutCommand() method should reject a null token array",
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

    @Test
    public void shouldSetInvalidSyntaxMessageOnNullTokens() {
        PutCommand testPutCommand = new PutCommand(new MapDataStore());
        testPutCommand.handle(null);

        Assert.assertEquals(
                "The handle() method should set the 'Invalid syntax.' command message when given null token array",
                "Invalid syntax.",
                testPutCommand.getCommandMessage()
        );
    }

    @Test
    public void shouldNotUpdateAfterCreate() {
        String[] initTokens = {"put", "key", "value"};
        PutCommand testPutCommand = new PutCommand(new MapDataStore());

        testPutCommand.handle(initTokens);

        String[] testTokens = {"put", "key", "potato"};
        testPutCommand.handle(testTokens);

        Assert.assertEquals(
                "The handle() method should set the 'Key already defined.' command message when given a put command with an existing key.",
                "Key already defined.",
                testPutCommand.getCommandMessage()
        );
    }
}
