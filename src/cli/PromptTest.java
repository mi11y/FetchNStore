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
                testPrompt.getPROMPT().contains("\n")
        );
    }

    /**
     * By typing in ">exit" the prompt should indicate to exit the CLI.
     */
    @Test
    public void shouldSignalExit() {
        Prompt testPrompt = new Prompt();

        testPrompt.execute("exit");

        Assert.assertTrue("After entering 'exit', the prompt should set shouldExit to true.", testPrompt.shouldExit);
        Assert.assertEquals("Bye!", testPrompt.getCommandMessage());
    }

    /**
     * By typing in ">exit arg1" the prompt should not exit and indicate invalid syntax.
     */
    @Test
    public void shouldRejectInvalidExit() {
        Prompt testPrompt = new Prompt();

        testPrompt.execute("exit thing1");

        Assert.assertFalse("The prompt should reject an exit if given with extra args.", testPrompt.shouldExit);
        Assert.assertEquals("Invalid syntax.", testPrompt.getCommandMessage());
    }

    /**
     * By typing in ">put thing1 thing2" as appropriate, the prompt should accept this input.
     */
    @Test
    public void shouldAcceptValidPut() {
        Prompt testPrompt = new Prompt();

        boolean succesful = testPrompt.execute("put thing_1 thing_2");
        Assert.assertTrue("Executing put with valid input should be accepted.", succesful);
        Assert.assertEquals("ok", testPrompt.getCommandMessage());
    }

    /**
     * By typing in ">   put   thing1WithWhitespace    thing2", the prompt should accept
     * the input and ignore the extra whitespace.
     */
    @Test
    public void shouldAcceptPromptWithExtraWhitespace() {
        Prompt testPrompt = new Prompt();

        boolean succesful = testPrompt.execute("      put       thing_1   thing_2      ");
        Assert.assertTrue("Executing put with extra whitespace input should be accepted.", succesful);
        Assert.assertEquals("ok", testPrompt.getCommandMessage());
    }

    /**
     * By typing in ">put", the prompt should reject the input and indicate
     * invalid syntax.
     */
    @Test
    public void shouldRejectPutWithNoParams() {
        Prompt testPrompt = new Prompt();

        boolean successful = testPrompt.execute("put ");
        Assert.assertFalse("Executing put with no parameters should be rejected.", successful);
        Assert.assertEquals("Invalid syntax.", testPrompt.getCommandMessage());
    }

    /**
     * By typing in ">put thing1", the prompt should reject the input and
     * indicate invalid syntax.
     */
    @Test
    public void shouldRejectPutWithOneParam() {
        Prompt testPrompt = new Prompt();

        boolean successful = testPrompt.execute("put thing_1");
        Assert.assertFalse("Executing put with one parameters should be rejected.", successful);
        Assert.assertEquals("Invalid syntax.", testPrompt.getCommandMessage());
    }

    /**
     * By typing in ">put thing1 thing2 thing3", the prompt should reject the
     * input and indicate invalid syntax.
     */
    @Test
    public void shouldRejectPutWithThreeParams() {
        Prompt testPrompt = new Prompt();

        boolean successful = testPrompt.execute("put thing_1 thing_2 thing_3");
        Assert.assertFalse("Executing put with three parameters should be rejected.", successful);
        Assert.assertEquals("Invalid syntax.", testPrompt.getCommandMessage());
    }

    /**
     * By typing in ">someCommandThatDoesntExist", the prompt should reject
     * the input and indicate what commands are known.
     */
    @Test
    public void shouldRejectUnknownCommands() {
        Prompt testPrompt = new Prompt();

        boolean successful = testPrompt.execute("notAcommand thing1 thing2");
        Assert.assertFalse("Executing an unknown command should be rejected.", successful);
        Assert.assertEquals("Unknown command. Known commands are: put, fetch, exit", testPrompt.getCommandMessage());
    }

    /**
     * By typing in ">fetch key" after putting a key-value pair, the value
     * for this key should be returned.
     */
    @Test
    public void shouldAcceptValidFetch() {
        Prompt testPrompt = new Prompt();

        testPrompt.execute("put thing_1 thing2");
        boolean succesful = testPrompt.execute("fetch thing_1");
        Assert.assertTrue("Executing fetch with valid input should be accepted.", succesful);
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

        boolean succesful = testPrompt.execute("fetch thing_1");
        Assert.assertFalse("Executing fetch on a key not defined should be rejected.", succesful);
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
        boolean succesful = testPrompt.execute("fetch thing_1 thing3");
        Assert.assertFalse("Executing fetch with extra parameters should be rejected.", succesful);
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
        boolean succesful = testPrompt.execute("fetch");
        Assert.assertFalse("Executing fetch without a key should be rejected.", succesful);
        Assert.assertEquals(
                "Fetching without a key should return invalid syntax error.",
                "Invalid syntax.",
                testPrompt.getCommandMessage()
        );
    }
}
