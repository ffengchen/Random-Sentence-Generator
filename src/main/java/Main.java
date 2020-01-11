import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

/**
 * Main class to generate random sentence.
 */
public class Main {

  /**
   * Main method.
   *
   * @param args input folder path.
   * @throws IOException    IOException
   * @throws ParseException ParseException
   */
  public static void main(String[] args) throws IOException, ParseException {
    String grammarFolderPath = args[0]; //"grammars";

    System.out.println("Loading grammars...");
    File grammarFolder = new File(grammarFolderPath);
    File[] grammarFiles = grammarFolder.listFiles();
    if (grammarFiles == null) {
      System.out.println("Grammar files not found!");
      return;
    }

    runGenerator(grammarFiles);

  }

  /**
   * Run random sentence generator.
   *
   * @param grammarFiles a file array containing all grammar files.
   * @throws IOException    IOException
   * @throws ParseException JSON file parse exception
   */
  public static void runGenerator(File[] grammarFiles)
      throws IOException, ParseException {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("The following grammars are available:");

      for (int i = 0; i < grammarFiles.length; i++) {
        String fileName = grammarFiles[i].getName();
        int lastIndex = fileName.lastIndexOf(".");
        System.out.println(i + 1 + ": " + fileName.substring(0, lastIndex));
      }

      System.out.println("Which would you like to use? (q to quit)");
      String input = scanner.nextLine();

      if (input.equals("q")) {
        break;
      }

      int grammarNum;
      try {
        grammarNum = Integer.parseInt(input) - 1;
      } catch (NumberFormatException e) {
        System.out.println("Please enter a valid option!");
        continue;
      }
      if (grammarNum >= grammarFiles.length) {
        System.out.println("Please enter a valid option!");
        continue;
      }

      ParserHelper.getGrammar(grammarFiles[grammarNum]);

      JSONArray jsonArray = (JSONArray) ParserHelper.grammar.get("start");
      Random random = new Random();
      do {
        String sentence = ParserHelper
            .parse((String) jsonArray.get(random.nextInt(jsonArray.size())));

        System.out.println(sentence);

        System.out.println("Would you like another? (y/n)");
        input = scanner.nextLine();
      } while (input.equals("y"));
    }
  }
}
