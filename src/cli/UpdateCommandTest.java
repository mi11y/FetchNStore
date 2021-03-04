package cli;

import org.junit.Assert;
import org.junit.Test;

public class UpdateCommandTest {

    @Test
    public void shouldRecognizeUpdateCommand() {
        String[] testTokens = {"update", "key", "value"};

        boolean isUpdateCommand = new UpdateCommand(new MapDataStore()).isMatchingCommand(testTokens);
        Assert.assertTrue(
                "A valid update command with two params should be recognized",
                isUpdateCommand
        );
    }

    @Test
    public void shouldRecognizeUpdateCommandWithExtraParams() {
        String[] testTokens = {"update", "key", "value", "potato"};

        boolean isUpdateCommand = new UpdateCommand(new MapDataStore()).isMatchingCommand(testTokens);
        Assert.assertTrue(
                "An update command with extra params should still be recognized.",
                isUpdateCommand
        );
    }

    @Test
    public void shouldRecognizeUpdateCommandWithNoParams() {
        String[] testTokens = {"update"};

        boolean isUpdateCommand = new UpdateCommand(new MapDataStore()).isMatchingCommand(testTokens);
        Assert.assertTrue(
                "An update command with no params should still be recognized.",
                isUpdateCommand
        );
    }

    @Test
    public void shouldRejectNonPutCommand() {
        String[] testTokens = {"potato"};

        boolean isUpdateCommand = new UpdateCommand(new MapDataStore()).isMatchingCommand(testTokens);
        Assert.assertFalse(
                "A  non-update command should be rejectecd.",
                isUpdateCommand
        );
    }

    @Test
    public void shouldRejectNullCommand() {
        boolean isUpdateCommand = new UpdateCommand(new MapDataStore()).isMatchingCommand(null);
        Assert.assertFalse(
                "The isUpdateCommand() should reject a null token array.",
                isUpdateCommand
        );
    }

    @Test
    public void shouldRejectEmptyTokenArray() {
        String[] testTokens = {};
        boolean isUpdateCommand = new UpdateCommand(new MapDataStore()).isMatchingCommand(testTokens);
        Assert.assertFalse(
                "The isUpdateCommand() should reject an empty token array",
                isUpdateCommand
        );
    }

    @Test
    public void shouldSetOKMessage() {
        MapDataStore testDataStore = new MapDataStore();
        testDataStore.create("key", "value");

        String[] testTokens = {"update", "key", "newValue"};
        UpdateCommand testUpdateCommand = new UpdateCommand(testDataStore);

        testUpdateCommand.handle(testTokens);

        Assert.assertEquals(
                "The handle() method should set the 'ok' command message when updating an existing key value pair.",
                "ok",
                testUpdateCommand.getCommandMessage()
        );
    }

    @Test
    public void shouldUpdateMapDataStore() {
        MapDataStore testDataStore = new MapDataStore();
        testDataStore.create("key", "value");

        String[] testTokens = {"update", "key", "newValue"};
        UpdateCommand testUpdateCommand = new UpdateCommand(testDataStore);

        testUpdateCommand.handle(testTokens);

        Assert.assertEquals(
                "A valid update command should update the Map Data store.",
                "newValue",
                testDataStore.read("key")
        );
    }

    @Test
    public void shouldSetInvalidSyntaxMessageOnNoParams() {
        String[] testTokens = {"update"};

        UpdateCommand testUpdateCommand = new UpdateCommand(new MapDataStore());
        testUpdateCommand.handle(testTokens);

        Assert.assertEquals(
                "The handle() method should set the 'Invalid syntax' command message when given an update with no key-values to update",
                "Invalid syntax",
                testUpdateCommand.getCommandMessage()
        );
    }

    @Test
    public void shouldSetInvalidSyntaxMessageOnExtraParams() {
        String[] testTokens = {"update", "key", "value", "potato"};

        UpdateCommand testUpdateCommand = new UpdateCommand(new MapDataStore());
        testUpdateCommand.handle(testTokens);

        Assert.assertEquals(
                "The handle() method should set the 'Invalid syntax' command message when given an update command with extra params",
                "Invalid syntax",
                testUpdateCommand.getCommandMessage()
        );
    }

    @Test
    public void shouldSetInvalidSyntaxMessageOnNullTokens() {
        UpdateCommand testUpdateCommand = new UpdateCommand(new MapDataStore());
        testUpdateCommand.handle(null);

        Assert.assertEquals(
                "The handle() method should set the 'Invalid syntax' command message when given a null token array.",
                "Invalid syntax",
                testUpdateCommand.getCommandMessage()
        );
    }

    @Test
    public void shouldNotUpdateIfKeyDoesNotExist() {
        String[] testTokens = {"update", "key", "value"};

        UpdateCommand testUpdateCommand = new UpdateCommand(new MapDataStore());
        testUpdateCommand.handle(testTokens);

        Assert.assertEquals(
                "The handle() method should set the 'Key does not exist' command message when updating a key that does not exist.",
                "Key does not exist",
                testUpdateCommand.getCommandMessage()
        );
    }
}
