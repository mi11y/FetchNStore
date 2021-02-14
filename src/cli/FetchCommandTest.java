package cli;

import org.junit.Assert;
import org.junit.Test;

public class FetchCommandTest {

    @Test
    public void shouldRecognizeFetchCommand() {
        String[] testTokens = {"fetch"};

        boolean isFetchCommand = new FetchCommand(new MapDataStore()).isFetchCommand(testTokens);

        Assert.assertTrue(
                "The FetchCommand class should recognize the fetch command token",
                isFetchCommand
        );
    }

    @Test
    public void shouldRejectNonFetchCommand() {
        String[] testTokens = {"potato"};

        boolean isFetchCommand = new FetchCommand(new MapDataStore()).isFetchCommand(testTokens);

        Assert.assertFalse(
                "The isFetchCommand() method should reject a non-fetch command",
                isFetchCommand
        );
    }

    @Test
    public void shouldRejectNullCommand() {
        boolean isFetchCommand = new FetchCommand(new MapDataStore()).isFetchCommand(null);

        Assert.assertFalse(
                "The isFetchCommand() should reject when given null tokens",
                isFetchCommand
        );
    }

    @Test
    public void shouldRecognizeFetchCommandWithExtraParams() {
        String[] testTokens = {"fetch", "key", "potato"};

        boolean isFetchCommand = new FetchCommand(new MapDataStore()).isFetchCommand(testTokens);
        Assert.assertTrue(
                "The isFetchCommand() method should recognize a fetch command with extra params",
                isFetchCommand
        );
    }

    @Test
    public void shouldSetInvalidSyntaxCommandMessage() {
        String[] testTokens = {"fetch", "key", "potato"};

        FetchCommand testFetchCommand = new FetchCommand(new MapDataStore());
        testFetchCommand.handle(testTokens);

        Assert.assertEquals(
                "The handle() method should set the command message to 'Invalid syntax' error message",
                "Invalid syntax.",
                testFetchCommand.getCommandMessage()
        );
    }

    @Test
    public void shouldSetInvalidSyntaxCommandMessageOnNullTokens() {
        FetchCommand testFetchCommand = new FetchCommand(new MapDataStore());
        testFetchCommand.handle(null);

        Assert.assertEquals(
                "The handle() method should set the command message to 'Invalid syntax' error message when given null for tokens",
                "Invalid syntax.",
                testFetchCommand.getCommandMessage()
        );
    }

    @Test
    public void shouldSetCommandMessageToFetchResult() {
        final String testKey = "key";
        final String testValue = "value";
        String[] testTokens = {"fetch", testKey};

        MapDataStore testMapDataStore = new MapDataStore();
        testMapDataStore.create(testKey, testValue);

        FetchCommand testFetchCommand = new FetchCommand(testMapDataStore);
        testFetchCommand.handle(testTokens);

        Assert.assertEquals(
                "The handle() method should save the fetch result into the CommandMessage",
                testValue,
                testFetchCommand.getCommandMessage()
        );
    }

    @Test
    public void shouldSetCommandMessageToValueNotFound() {
        String[] testTokens = {"fetch", "potato"};

        MapDataStore testMapDataStore = new MapDataStore();
        testMapDataStore.create("key", "value");

        FetchCommand testFetchCommand = new FetchCommand(testMapDataStore);
        testFetchCommand.handle(testTokens);

        Assert.assertEquals(
                "The handle() method should set the command message to 'Value not found.' when fetching an undefined key",
                "Value not found.",
                testFetchCommand.getCommandMessage()
        );
    }
}
