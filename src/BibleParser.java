import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BibleParser {
  public static List<Book> parseBooks(String fullText) {
    // Split up text into books and create book object list
    String[] bookSplits =
        fullText.split("\\s+(?=\\s(?:\\d\\s)??(?:[A-z]+\\s)+\\s*1:1)");
    List<String> bookTexts = Arrays.asList(bookSplits);
    return bookTexts.stream()
                    .map(Book::new)
                    .collect(Collectors.toList());
    }
}
