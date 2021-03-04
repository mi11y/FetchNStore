package cli;

import org.junit.Assert;
import org.junit.Test;

public class PromptTest {

    /**
     * The prompt cursor string should not contain a new line.
     */
    @Test
    public void shouldPrintPromptWithoutNewline() {
        Prompt testPrompt = new Prompt();
        Assert.assertFalse(
                "The prompt should be printed without a newline.",
                testPrompt.getCursor().contains("\n")
        );
    }

    /**
     * By typing in ">exit" the prompt should indicate to exit the CLI.
     */
    @Test
    public void shouldSignalExit() {
        Prompt testPrompt = new Prompt();

        testPrompt.execute("exit");

        Assert.assertTrue("After entering 'exit', the prompt should set shouldExit to true.", testPrompt.shouldExit());
        Assert.assertEquals("Bye!", testPrompt.getCommandMessage());
    }

    /**
     * By typing in ">exit arg1" the prompt should not exit and indicate invalid syntax.
     */
    @Test
    public void shouldRejectInvalidExit() {
        Prompt testPrompt = new Prompt();

        testPrompt.execute("exit thing1");

        Assert.assertFalse("The prompt should reject an exit if given with extra args.", testPrompt.shouldExit());
        Assert.assertEquals("Invalid syntax.", testPrompt.getCommandMessage());
    }

    /**
     * By typing in ">put thing1 thing2" as appropriate, the prompt should accept this input.
     */
    @Test
    public void shouldAcceptValidPut() {
        Prompt testPrompt = new Prompt();

        testPrompt.execute("put thing_1 thing_2");
        Assert.assertEquals("ok", testPrompt.getCommandMessage());
    }

    /**
     * By typing in ">   put   thing1WithWhitespace    thing2", the prompt should accept
     * the input and ignore the extra whitespace.
     */
    @Test
    public void shouldAcceptPromptWithExtraWhitespace() {
        Prompt testPrompt = new Prompt();

        testPrompt.execute("      put       thing_1\t \tthing_2      ");
        Assert.assertEquals("ok", testPrompt.getCommandMessage());
    }

    /**
     * By typing in ">put", the prompt should reject the input and indicate
     * invalid syntax.
     */
    @Test
    public void shouldRejectPutWithNoParams() {
        Prompt testPrompt = new Prompt();

        testPrompt.execute("put ");
        Assert.assertEquals("Invalid syntax.", testPrompt.getCommandMessage());
    }

    /**
     * By typing in ">put thing1", the prompt should reject the input and
     * indicate invalid syntax.
     */
    @Test
    public void shouldRejectPutWithOneParam() {
        Prompt testPrompt = new Prompt();

        testPrompt.execute("put thing_1");
        Assert.assertEquals("Invalid syntax.", testPrompt.getCommandMessage());
    }

    /**
     * By typing in ">put thing1 thing2 thing3", the prompt should reject the
     * input and indicate invalid syntax.
     */
    @Test
    public void shouldRejectPutWithThreeParams() {
        Prompt testPrompt = new Prompt();

        testPrompt.execute("put thing_1 thing_2 thing_3");
        Assert.assertEquals("Invalid syntax.", testPrompt.getCommandMessage());
    }

    /**
     * By typing in ">fetch key" after putting a key-value pair, the value
     * for this key should be returned.
     */
    @Test
    public void shouldAcceptValidFetch() {
        Prompt testPrompt = new Prompt();

        testPrompt.execute("put thing_1 thing2");
        testPrompt.execute("fetch thing_1");
        Assert.assertEquals(
                "Fetching a key should return the key's value.",
                "thing2",
                testPrompt.getCommandMessage()
        );
    }

    /**
     * By typing in ">fetch key" where key has not been mapped should
     * be rejected and indicate "Value not found." message.
     */
    @Test
    public void shouldRejectFetchUndefinedKey() {
        Prompt testPrompt = new Prompt();

        testPrompt.execute("fetch thing_1");
        Assert.assertEquals(
                "Fetching an undefined key should return Value not found error.",
                "Value not found.",
                testPrompt.getCommandMessage()
        );
    }

    /**
     * By typing in ">fetch key thing1" after putting a key-value pair, the input
     * should be rejected and indicate invalid syntax.
     */
    @Test
    public void shouldRejectFetchWithExtraParams() {
        Prompt testPrompt = new Prompt();

        testPrompt.execute("put thing_1 thing2");
        testPrompt.execute("fetch thing_1 thing3");
        Assert.assertEquals(
                "Fetching with extra parameters should return invalid syntax error.",
                "Invalid syntax.",
                testPrompt.getCommandMessage()
        );
    }

    /**
     * By typing in ">fetch" without a key, the input should be rejected
     * and indicate invalid syntax.
     */
    @Test
    public void shouldRejectFetchWithNoParams() {
        Prompt testPrompt = new Prompt();

        testPrompt.execute("put thing_1 thing2");
        testPrompt.execute("fetch");
        Assert.assertEquals(
                "Fetching without a key should return invalid syntax error.",
                "Invalid syntax.",
                testPrompt.getCommandMessage()
        );
    }

    /**
     * By typing in ">update key newValue", the existing value in the data store
     * should be updated with the new value.
     */
    @Test
    public void shouldAcceptUpdateCommand() {
        Prompt testPrompt = new Prompt();

        testPrompt.execute("put key oldValue");
        testPrompt.execute("update key newValue");
        Assert.assertEquals(
                "The prompt should execute the update command and update the data store when calling update with valid parameters.",
                "ok",
                testPrompt.getCommandMessage()
        );
    }

    /**
     * The prompt should return 'Unknown command. Known commands are: put, fetch, update, exit'
     * when given an undefined command.
     */
    @Test
    public void shouldReturnUnknownCommandMessage() {
        Prompt testPrompt = new Prompt();

        testPrompt.execute("potato");
        Assert.assertEquals(
                "The prompt should return 'Unknown command. Known commands are: put, fetch, update, exit' when given an undefined command",
                "Unknown command. Known commands are: put, fetch, update, exit",
                testPrompt.getCommandMessage()
        );
    }
}
