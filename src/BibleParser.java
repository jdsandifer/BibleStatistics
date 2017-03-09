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
                .flatMap(b->bookToChapters(b).stream())
                .collect(Collectors.toList());
  }

  public static List<Verse> parseVerses(List<Chapter> chapters) {
    // Use chapter data list to create verse data list
    return chapters.stream()
                   .flatMap(c->chapterToVerses(c).stream())
                   .collect(Collectors.toList());
  }

  static List<Chapter> bookToChapters(Book book) {
    String bookTitle = book.getTitle();
    String[] chapTexts = book.getText()
                             .trim()
                             .split("\\s+(?=\\d{1,3}:1\\s)");
    List<Chapter> chapters = Arrays.stream(chapTexts)
                                   .map(c->new Chapter(bookTitle, c))
                                   .collect(Collectors.toList());
    return chapters;
  }

  static List<Verse> chapterToVerses(Chapter chapter) {
    String chapterInfo = chapter.getBookAndNumber();
    String[] verseTexts = chapter.getText()
                                 .trim()
                                 .split("\\s+(?=\\d{1,3}:\\d{1,3})");
    List<Verse> verses = Arrays.stream(verseTexts)
                                   .map(v->new Verse(chapterInfo, v))
                                   .collect(Collectors.toList());
    return verses;
  }
}
