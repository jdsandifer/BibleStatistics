import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BibleReader {

  public static void main(String[] args) throws IOException {
    BibleReader text = new BibleReader();
    
    String fullText = text.readTextFile(INPUT_FILE);
    
    text.writeTextFile(fullText, OUTPUT_FILE);
  }
      
  final static String INPUT_FILE = "input.txt";
  final static String OUTPUT_FILE = "output.txt";
  final static Charset ENCODING = StandardCharsets.UTF_8;
  
  String readTextFile(String fileName) throws IOException {
    Path path = Paths.get(fileName);
    List<String> lines = Files.readAllLines(path, ENCODING);
    
    return lines.remove(0) + "\n" + lines.remove(0);
  }
  
  void writeTextFile(String textToWrite, String fileName) throws IOException {
    Path path = Paths.get(fileName);
    
    List<String> lines = new ArrayList<String>();
    lines.add(textToWrite);
    
    Files.write(path, lines, ENCODING);
  }
}
