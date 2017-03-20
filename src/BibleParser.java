import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BibleParser {
  public static List<Book> parseBooks(String fullText) {
    // Split up text into books and create book data list
    String regexToSplitTextByBooks =
        "\\s+(?=\\s(?:\\d\\s)??(?:[A-z]+\\s)+\\s*1:1)";
    String[] bookSplits = fullText.split(regexToSplitTextByBooks);
    List<String> bookTexts = Arrays.asList(bookSplits);
    return bookTexts.stream()
                    .map(Book::new)
                    .collect(Collectors.toList());
  }

  public static List<Chapter> parseChapters(List<Book> books) {
    // Use book data list to create chapter data List
    return books.stream()
                .flatMap(eachBook->bookToChapters(eachBook).stream())
                .collect(Collectors.toList());
  }

  public static List<Verse> parseVerses(List<Chapter> chapters) {
    // Use chapter data list to create verse data list
    return chapters.stream()
                   .flatMap(eachChapter->chapterToVerses(eachChapter).stream())
                   .collect(Collectors.toList());
  }

  static List<Chapter> bookToChapters(Book book) {
    String bookTitle = book.title();
    String regexToSplitTextByChapters = "\\s+(?=\\d{1,3}:1\\s)";
    String[] chapTexts = book.text()
                             .trim()
                             .split(regexToSplitTextByChapters);
    List<Chapter> chapters = Arrays.stream(chapTexts)
                                   .map(c->new Chapter(bookTitle, c))
                                   .collect(Collectors.toList());
    return chapters;
  }

  static List<Verse> chapterToVerses(Chapter chapter) {
    String chapterInfo = chapter.bookAndNumber();
    String regexToSplitTextByVerses = "\\s+(?=\\d{1,3}:\\d{1,3})";
    String[] verseTexts = chapter.text()
                                 .trim()
                                 .split(regexToSplitTextByVerses);
    List<Verse> verses = Arrays.stream(verseTexts)
                                   .map(eachVerse->new Verse(chapterInfo, eachVerse))
                                   .collect(Collectors.toList());
    return verses;
  }
}
