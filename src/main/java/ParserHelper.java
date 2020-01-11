import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Helper class to get and parse grammar json file.
 */
public class ParserHelper {

  public static JSONObject grammar = null;

  /**
   * Read grammar json files and store data in JSONObject.
   *
   * @param file json file
   * @throws IOException    IOException
   * @throws ParseException ParseException
   */
  public static void getGrammar(File file) throws IOException, ParseException {
    grammar = (JSONObject) new JSONParser().parse(new FileReader(file));
  }

  /**
   * Parse grammar.
   *
   * @param str grammar
   * @return generated sentence
   */
  public static String parse(String str) {
    String[] holders = str.split(" ");
    for (int i = 0; i < holders.length; i++) {
      if (holders[i].startsWith("<") && holders[i].endsWith(">")) {
        holders[i] = expandBracket(holders[i]);
      }
    }
    return String.join(" ", holders);
  }

  /**
   * Expand non-terminal.
   *
   * @param str non-terminal
   * @return expanded non-terminal
   */
  private static String expandBracket(String str) {
    Random random = new Random();
    JSONArray jsonArray;
    String output = "";
    try {
      jsonArray = (JSONArray) getJSONCaseInsen(grammar, str.substring(1, str.length() - 1));
      output = parse((String) jsonArray.get(random.nextInt(jsonArray.size())));
    } catch (Exception e) {
      output = str + " is not defined";
    } finally {
      return output;
    }
  }

  /**
   * Gets value in a JSON object case-insensitively.
   *
   * @param jobj      JSONObject
   * @param targetKey A key
   * @return An object (JSONArray) related to the input key.
   */
  @SuppressWarnings("unchecked")
  private static Object getJSONCaseInsen(JSONObject jobj, String targetKey) {

    Set<String> iter = jobj.keySet();
    for (String key : iter) {
      if (key.equalsIgnoreCase(targetKey)) {
        return jobj.get(key);
      }
    }
    return null;
  }
}
