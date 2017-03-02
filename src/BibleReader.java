import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BibleReader {
  final static Charset INPUT_ENCODING = StandardCharsets.US_ASCII;
  final static Charset OUTPUT_ENCODING = StandardCharsets.UTF_8;

  public String readTextFile(String fileName) throws IOException {
    Path path = Paths.get(fileName);
    List<String> lines = Files.readAllLines(path, INPUT_ENCODING);
    return String.join("\n", lines);
  }

  public String readTextFile(String fileName, Charset encoding) throws IOException {
    Path path = Paths.get(fileName);
    List<String> lines = Files.readAllLines(path, encoding);
    return String.join("\n", lines);
  }

  public void writeTextFile(String textToWrite, String fileName) throws IOException {
    Path path = Paths.get(fileName);

    List<String> lines = new ArrayList<String>();
    lines.add(textToWrite);

    Files.write(path, lines, OUTPUT_ENCODING);
  }
}
