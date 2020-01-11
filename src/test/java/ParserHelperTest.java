import java.io.File;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test parseHelper class.
 */
public class ParserHelperTest {

  @Test
  public void getGrammar() {
    try {
      ParserHelper.getGrammar(new File("../../../grammars/poem.json"));
      Assert.assertNotNull(ParserHelper.grammar);
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }

  }

  @Test
  public void parseA() {
    try {
      ParserHelper.getGrammar(new File("../../../grammars/poem.json"));
      String str = "The <object> <verb> tonight.";
      Assert.assertNotNull(ParserHelper.parse(str));
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void parseB() {
    try {
      ParserHelper.getGrammar(new File("../../../grammars/poem.json"));
      String str = "The <obj> <verb> tonight.";
      Assert.assertNotNull(ParserHelper.parse(str));
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
  }
}