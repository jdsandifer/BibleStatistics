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

  public static void main(String[] args) throws IOException {
    BibleReader text = new BibleReader();
    String fullText = text.readTextFile(BIBLE_INPUT_FILE);

    // Split up text into books and create sorted book object list
    String[] bookSplits =
        fullText.split("\\s+(?=\\s(?:\\d\\s)??(?:[A-z]+\\s)+\\s*1:1)");
    List<String> bookTexts = Arrays.asList(bookSplits);
    List<Book> books = bookTexts.stream()
                                .map(Book::new)
                                .collect(Collectors.toList());
    books.sort((a,b)->a.countWords()-b.countWords());

    // Use book data to create chapter data and sorted List


    // Use chapter data to create verse data and sorted list


    /* Turn books data into nice text format and write to file
    StringBuilder bookData = new StringBuilder();
    books.stream()
         .forEach(b->{
              bookData.append(b.getTitle());
              bookData.append(" -> ");
              bookData.append(b.countWords());
              bookData.append(" words\r\n\r\n");
              bookData.append(b.getText());
              bookData.append("\r\n\r\n\r\n");
            });
    text.writeTextFile(bookData.toString(), BOOKS_OUTPUT_FILE);
    //*/

    /* Turn chapter data into nice text format and write to file
    //*/

    /* Turn chapter data into csv and write to file
    //*/

    /* Turn verse data into nice text format and write to file
    //*/
  }

  final static String BIBLE_INPUT_FILE =
      "NETBible no markings (formatting removed ASCII).txt";
  final static String BOOKS_OUTPUT_FILE = "Bible Books - Text & Counts.txt";
  final static String CHAPTERS_OUTPUT_FILE = "Bible Chapters - Text & Counts.txt";
  final static String VERSES_OUTPUT_FILE = "Bible Verses - Text & Counts.txt";
  final static String TEST_OUTPUT_FILE = "test-output.txt";
  final static Charset INPUT_ENCODING = StandardCharsets.US_ASCII;
  final static Charset OUTPUT_ENCODING = StandardCharsets.UTF_8;

  String readTextFile(String fileName) throws IOException {
    Path path = Paths.get(fileName);
    List<String> lines = Files.readAllLines(path, INPUT_ENCODING);
    return String.join("\n", lines);
  }

  void writeTextFile(String textToWrite, String fileName) throws IOException {
    Path path = Paths.get(fileName);

    List<String> lines = new ArrayList<String>();
    lines.add(textToWrite);

    Files.write(path, lines, OUTPUT_ENCODING);
  }
}
