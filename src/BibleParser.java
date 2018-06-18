import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BibleParser {
  
  // Splits up text into books and create book data list
  public static List<Book> parseBooks(String fullText) {
    // Regex will split up book text by looking for the 1:1
    // chaper/verse marking at the start of each boock.
    String regexToSplitTextByBooks =
        "\\s+(?=\\s(?:\\d\\s)??(?:[A-z]+\\s)+\\s*1:1)";
    String[] bookSplits = fullText.split(regexToSplitTextByBooks);
    
    List<String> bookTexts = Arrays.asList(bookSplits);
    
    // The constructor of Book will take the text as split above
    return bookTexts.stream()
                    .map(Book::new)
                    .collect(Collectors.toList());
  }

  // Uses book data list to create chapter data List
  public static List<Chapter> parseChapters(List<Book> books) {
    return books.stream()
                .flatMap(eachBook->bookToChapters(eachBook).stream())
                .collect(Collectors.toList());
  }

  // Uses chapter data list to create verse data list
  public static List<Verse> parseVerses(List<Chapter> chapters) {
    return chapters.stream()
                   .flatMap(eachChapter->chapterToVerses(eachChapter).stream())
                   .collect(Collectors.toList());
  }

  static List<Chapter> bookToChapters(Book book) {
    String bookTitle = book.title();
    
    // This regex looks for chapter/verse markers to find
    // the start of each chapter (e.g. 110:1).
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
    
    // This regex looks for verses by finding the references
    // at the start of each verse (e.g. 99:5).
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
