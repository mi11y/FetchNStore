package cli;

import org.junit.Assert;
import org.junit.Test;

public class PromptTest {

    @Test
    public void shouldPrintPromptWithoutNewline() {
        Prompt testPrompt = new Prompt();
        Assert.assertFalse("The prompt should be printed without a newline.", testPrompt.getPROMPT().contains("\n"));
    }

    @Test
    public void shouldSignalExit() {
        Prompt testPrompt = new Prompt();

        testPrompt.execute("exit");

        Assert.assertTrue("After entering 'exit', the prompt should set shouldExit to true.", testPrompt.shouldExit);
    }

    @Test
    public void shouldAcceptValidPut() {
        Prompt testPrompt = new Prompt();

        boolean succesful = testPrompt.execute("put thing_1 thing_2");
        Assert.assertTrue("Executing put with valid input should be accepted.", succesful);
    }

    @Test
    public void shouldAcceptPromptWithExtraWhitespace() {
        Prompt testPrompt = new Prompt();

        boolean succesful = testPrompt.execute("      put       thing_1   thing_2      ");
        Assert.assertTrue("Executing put with extra whitespace input should be accepted.", succesful);
    }

    @Test
    public void shouldRejectPutWithNoParams() {
        Prompt testPrompt = new Prompt();

        boolean successful = testPrompt.execute("put ");
        Assert.assertFalse("Executing put with no parameters should be rejected.", successful);
    }

    @Test
    public void shouldRejectPutWithOneParam() {
        Prompt testPrompt = new Prompt();

        boolean successful = testPrompt.execute("put thing_1");
        Assert.assertFalse("Executing put with one parameters should be rejected.", successful);
    }

    @Test
    public void shouldRejectPutWithThreeParams() {
        Prompt testPrompt = new Prompt();

        boolean successful = testPrompt.execute("put thing_1 thing_2 thing_3");
        Assert.assertFalse("Executing put with three parameters should be rejected.", successful);
    }
}
