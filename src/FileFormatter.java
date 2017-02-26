import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class FileFormatter {
  public static String formatBookText(List<Book> books) {
    // Turn books data into nice text format and return as string
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
    return bookData.toString();
  }

  public static String formatBookCSV(List<Book> books) {
    // Turn book data into csv format and return as a string
    StringBuilder bookData = new StringBuilder();
    bookData.append("Book,Word Count\r\n");
    for (Book b : books) {
      bookData.append(b.getTitle());
      bookData.append(",");
      bookData.append(b.countWords());
      bookData.append("\r\n");
    }
    return bookData.toString();
  }

  public static String formatChapterText(List<Chapter> chapters) {
    // Turn chapter data into nice text format and return as a string
    StringBuilder chapterData = new StringBuilder();
    int passage = 0;
    for (Chapter c : chapters) {
      passage++;
      chapterData.append("Passage ");
      chapterData.append(passage);
      chapterData.append(":");
      chapterData.append("\r\n");
      chapterData.append(c.getBookAndNumber());
      chapterData.append(" -> ");
      chapterData.append(c.countWords());
      chapterData.append(" words\r\n");
      chapterData.append(c.getText()
                          .trim()
                          .replaceAll("\\d{1,3}:\\d{1,3}\\s", "")
                          .replaceAll("\\s+", " "));
      chapterData.append("\r\n\r\n");
    }
    return chapterData.toString();
  }

  public static String formatChapterCSV(List<Chapter> chapters) {
    // Turn chapter data into csv format and return as a string
    StringBuilder chapterData = new StringBuilder();
    chapterData.append("Chapter,Word Count\r\n");
    for (Chapter c : chapters) {
      chapterData.append(c.getBookAndNumber());
      chapterData.append(",");
      chapterData.append(c.countWords());
      chapterData.append("\r\n");
    }
    return chapterData.toString();
  }

  public static String formatVerseText(List<Verse> verses) {
    // Turn verse data into nice text format and return as a string
    StringBuilder verseData = new StringBuilder();
    int vNum = 0;
    for (Verse v : verses) {
      vNum++;
      verseData.append("Verse ");
      verseData.append(vNum);
      verseData.append(":");
      verseData.append("\r\n");
      verseData.append(v.getBookChapterVerse());
      verseData.append(" -> ");
      verseData.append(v.countWords());
      verseData.append(" words\r\n");
      verseData.append(v.getText()
                        .trim()
                        .replaceAll("\\d{1,3}:\\d{1,3}\\s", "")
                        .replaceAll("\\s+", " "));
      verseData.append("\r\n\r\n");
    }
    return verseData.toString();
  }

  public static String formatVerseCSV(List<Verse> verses) {
    // Turn verse data into csv format and return as a string
    StringBuilder verseData = new StringBuilder();
    verseData = new StringBuilder();
    verseData.append("Verse,Word Count\r\n");
    for (Verse v : verses) {
      verseData.append(v.getBookChapterVerse());
      verseData.append(",");
      verseData.append(v.countWords());
      verseData.append("\r\n");
    }
    return verseData.toString();
  }
}
