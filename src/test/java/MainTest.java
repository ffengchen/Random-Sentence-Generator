import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test Main class.
 */
public class MainTest {

  @Test
  public void mainA() throws IOException, ParseException {
    String[] args = new String[1];
    args[0] = "grammars";
    String inputMessage = "a\n"
        + "100\n"
        + "1\n"
        + "y\n"
        + "n\n"
        + "2\n"
        + "n\n"
        + "3\n"
        + "n\n"
        + "q\n"
        + "";
    File grammarFolder = new File(args[0]);
    File[] grammarFiles = grammarFolder.listFiles();
    System.setIn(new ByteArrayInputStream(inputMessage.getBytes()));
    assert grammarFiles != null;
    Main.runGenerator(grammarFiles);
  }

  @Test
  public void mainB() throws IOException, ParseException {
    final ByteArrayOutputStream output = new ByteArrayOutputStream();
    System.setOut(new PrintStream(output));
    String[] args = new String[1];
    args[0] = "gra";
    Main.main(args);
    Assert.assertEquals("Loading grammars...\nGrammar files not found!\n", output.toString());
  }
}