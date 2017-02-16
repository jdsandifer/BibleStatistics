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

    // Split up text into books and create book object list
    String[] bookSplits =
        fullText.split("\\s+(?=\\s(?:\\d\\s)??(?:[A-z]+\\s)+\\s*1:1)");
    List<String> bookTexts = Arrays.asList(bookSplits);
    List<Book> books = bookTexts.stream()
                                .map(Book::new)
                                .collect(Collectors.toList());


    // Use book data to create chapter data List
    List<Chapter> chapters =
        books.stream()
             .flatMap(b->bookToChapters(b).stream())
             .collect(Collectors.toList());

    // Use chapter data to create verse data and sorted list
    List<Verse> verses =
        chapters.stream()
                .flatMap(c->chapterToVerses(c).stream())
                .collect(Collectors.toList());

    StringBuilder bookData = new StringBuilder();
    books.sort((a,b)->a.countWords()-b.countWords());
    /* Turn books data into nice text format and write to file
    books.stream()
         .forEach(b->{
              bookData.append(b.getTitle());
              bookData.append(" -> ");
              bookData.append(b.countWords());
              bookData.append(" words\r\n\r\n");
              bookData.append(b.getText());
              bookData.append("\r\n\r\n\r\n");
            });
    text.writeTextFile(bookData.toString(), BOOK_OUTPUT_FILE);
    //*/

    /* Turn book data into csv and write to file
    bookData = new StringBuilder();
    bookData.append("Book,Word Count\r\n");
    for (Book b : books) {
      bookData.append(b.getTitle());
      bookData.append(",");
      bookData.append(b.countWords());
      bookData.append("\r\n");
    }
    text.writeTextFile(bookData.toString(), BOOK_CSV_FILE);
    //*/

    StringBuilder chapterData = new StringBuilder();
    chapters.sort((a,b)->a.countWords()-b.countWords());
    /* Turn chapter data into nice text format and write to file
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
    text.writeTextFile(chapterData.toString(), CHAPTER_OUTPUT_FILE);
    //*/

    /* Turn chapter data into csv and write to file
    chapterData = new StringBuilder();
    chapterData.append("Chapter,Word Count\r\n");
    for (Chapter c : chapters) {
      chapterData.append(c.getBookAndNumber());
      chapterData.append(",");
      chapterData.append(c.countWords());
      chapterData.append("\r\n");
    }
    text.writeTextFile(chapterData.toString(), CHAPTER_CSV_FILE);
    //*/

    StringBuilder verseData = new StringBuilder();
    verses.sort((a,b)->a.countWords()-b.countWords());
    /* Turn verse data into nice text format and write to file
    int verse = 0;
    for (Verse v : verses) {
      verse++;
      verseData.append("Verse ");
      verseData.append(verse);
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
    text.writeTextFile(verseData.toString(), VERSE_OUTPUT_FILE);
    //*/

    /* Turn verse data into csv and write to file
    verseData = new StringBuilder();
    verseData.append("Verse,Word Count\r\n");
    for (Verse v : verses) {
      verseData.append(v.getBookChapterVerse());
      verseData.append(",");
      verseData.append(v.countWords());
      verseData.append("\r\n");
    }
    text.writeTextFile(verseData.toString(), VERSE_CSV_FILE);
    //*/
  }

  final static String BIBLE_INPUT_FILE =
      "NETBible no markings (formatting removed ASCII).txt";
  final static String BOOK_OUTPUT_FILE = "Bible Books - Text & Counts.txt";
  final static String BOOK_CSV_FILE = "Bible Books - Counts.csv";
  final static String CHAPTER_OUTPUT_FILE = "Bible Chapters - Text & Counts.txt";
  final static String CHAPTER_CSV_FILE = "Bible Chapters - Counts.csv";
  final static String VERSE_OUTPUT_FILE = "Bible Verses - Text & Counts.txt";
  final static String VERSE_CSV_FILE = "Bible Verses - Counts.csv";
  final static Charset INPUT_ENCODING = StandardCharsets.US_ASCII;
  final static Charset OUTPUT_ENCODING = StandardCharsets.UTF_8;

  private static List<Chapter> bookToChapters(Book book) {
    String bookTitle = book.getTitle();
    String[] chapTexts = book.getText()
                             .trim()
                             .split("\\s+(?=\\d{1,3}:1\\s)");
    List<Chapter> chapters = Arrays.stream(chapTexts)
                                   .map(c->new Chapter(bookTitle, c))
                                   .collect(Collectors.toList());
    return chapters;
  }

  private static List<Verse> chapterToVerses(Chapter chapter) {
    String chapterInfo = chapter.getBookAndNumber();
    String[] verseTexts = chapter.getText()
                                 .trim()
                                 .split("\\s+(?=\\d{1,3}:\\d{1,3})");
    List<Verse> verses = Arrays.stream(verseTexts)
                                   .map(v->new Verse(chapterInfo, v))
                                   .collect(Collectors.toList());
    return verses;
  }

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
