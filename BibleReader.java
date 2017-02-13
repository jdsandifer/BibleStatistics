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

    String[] bookSplits = fullText.split("\\s+(?=\\s(?:\\d\\s)??(?:[A-z]+\\s)+\\s*1:1)");
    List<String> bookTexts = Arrays.asList(bookSplits);

    List<Book> books = bookTexts.stream()
                                .map(Book::new)
                                .collect(Collectors.toList());

    books.sort((a,b)->a.countWords()-b.countWords());

    System.out.println("\nPrinting book names...");
    int totalBooks = 0;
    for (Book b : books) {
      totalBooks++;
      System.out.println(b + " (" + b.countWords() + ") ["
                           + totalBooks + "]");
    }

    String bookData = "No book data yet...";

    // Test for proper Regex parsing (worked!)
    text.writeTextFile(bookData, BOOKS_OUTPUT_FILE);
  }

  final static String BIBLE_INPUT_FILE =
      "NETBible no markings (formatting removed ASCII).txt";
  final static String BOOKS_OUTPUT_FILE = "Bible Books - Text & Counts.txt";
  final static String CHAPTERS_OUTPUT_FILE = "Bible Chapters - Text & Counts.txt";
  final static String VERSES_OUTPUT_FILE = "Bible Verses - Text & Counts.txt";
  final static String TEST_OUTPUT_FILE = "test-output.txt";
  final static Charset ENCODING = StandardCharsets.US_ASCII;

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
