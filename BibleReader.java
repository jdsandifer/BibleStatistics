import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BibleReader {

  public static void main(String[] args) throws IOException {
    BibleReader text = new BibleReader();

    String fullText = text.readTextFile(INPUT_FILE);

    String[] books = fullText.split("\\s+(?=\\s(?:\\d\\s)??[A-z]+\\s+1:1)");
    List<String> outputs = Arrays.asList(books);

    // Test for proper Regex parsing (worked!)
    //text.writeTextFile(String.join("\n\nNext part:\n", outputs), OUTPUT_FILE);
  }

  final static String INPUT_FILE = "NETBible no markings - last books.txt";
  final static String OUTPUT_FILE = "output.txt";
  final static Charset ENCODING = StandardCharsets.ISO_8859_1;

  String readTextFile(String fileName) throws IOException {
    Path path = Paths.get(fileName);
    List<String> lines = Files.readAllLines(path, ENCODING);

    return String.join("\n", lines);
  }

  void writeTextFile(String textToWrite, String fileName) throws IOException {
    Path path = Paths.get(fileName);

    List<String> lines = new ArrayList<String>();
    lines.add(textToWrite);

    Files.write(path, lines, ENCODING);
  }
}
