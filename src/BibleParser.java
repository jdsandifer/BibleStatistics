import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BibleParser {
  public static List<Book> parseBooks(String fullText) {
    // Split up text into books and create book data list
    String[] bookSplits =
        fullText.split("\\s+(?=\\s(?:\\d\\s)??(?:[A-z]+\\s)+\\s*1:1)");
    List<String> bookTexts = Arrays.asList(bookSplits);
    return bookTexts.stream()
                    .map(Book::new)
                    .collect(Collectors.toList());
  }

  public static List<Chapter> parseChapters(List<Book> books) {
    // Use book data list to create chapter data List
    return books.stream()
                .flatMap(b->BibleReader.bookToChapters(b).stream())
                .collect(Collectors.toList());
  }

  public static List<Verse> parseVerses(List<Chapter> chapters) {
    // Use chapter data list to create verse data list
    return chapters.stream()
                   .flatMap(c->BibleReader.chapterToVerses(c).stream())
                   .collect(Collectors.toList());
  }
}
