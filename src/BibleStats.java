import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class BibleStats {
  // TODO: These would probably work better as command line arguments,
  // or a config file for easier modification down the road.
  final static String BOOK_OUTPUT_FILE = "Bible Books - Text & Counts.txt";
  final static String BOOK_CSV_FILE = "Bible Books - Counts.csv";
  final static String CHAPTER_OUTPUT_FILE = "Bible Chapters - Text & Counts.txt";
  final static String CHAPTER_CSV_FILE = "Bible Chapters - Counts.csv";
  final static String VERSE_OUTPUT_FILE = "Bible Verses - Text & Counts.txt";
  final static String VERSE_CSV_FILE = "Bible Verses - Counts.csv";

  static String inputFolder = "../input/";
  static String outputFolder = "../output/";
  static String bibleInputFile =
      "NETBible no markings (formatting removed ASCII).txt";

  static boolean shouldCreateBookFiles = false;
  static boolean shouldCreateChapterFiles = false;
  static boolean shouldCreateVerseFiles = false;

  public static void main(String[] args) throws IOException {
    readArgs(args);
    BibleReader bibleIO = new BibleReader();
    String fullText = bibleIO.readTextFile(inputFolder + bibleInputFile);
    List<Book> books = BibleParser.parseBooks(fullText);
    List<Chapter> chapters = BibleParser.parseChapters(books);
    List<Verse> verses = BibleParser.parseVerses(chapters);

    if (shouldCreateBookFiles) {
      books.sort((a,b)->a.countWords()-b.countWords());
      bibleIO.writeTextFile(FileFormatter.formatBookText(books),
                         outputFolder + BOOK_OUTPUT_FILE);
      bibleIO.writeTextFile(FileFormatter.formatBookCSV(books),
                         outputFolder + BOOK_CSV_FILE);
    }

    if (shouldCreateChapterFiles) {
      chapters.sort((a,b)->a.countWords()-b.countWords());
      bibleIO.writeTextFile(FileFormatter.formatChapterText(chapters),
                         outputFolder + CHAPTER_OUTPUT_FILE);
      bibleIO.writeTextFile(FileFormatter.formatChapterCSV(chapters),
                         outputFolder + CHAPTER_CSV_FILE);
    }

    if (shouldCreateVerseFiles) {
      verses.sort((a,b)->a.countWords()-b.countWords());
      bibleIO.writeTextFile(FileFormatter.formatVerseText(verses),
                         outputFolder + VERSE_OUTPUT_FILE);
      bibleIO.writeTextFile(FileFormatter.formatVerseCSV(verses),
                         outputFolder + VERSE_CSV_FILE);
    }
  }

  static void readArgs(String[] args) {
    show("Reading command line arguments...");
    for (int a = 0; a < args.length; a++) {
      if (args[a].equals("-i")  && (a + 1) < args.length) {
        bibleInputFile = args[++a];
        show("Input file set to: " + bibleInputFile);
      } else if (args[a].equals("-ip") && (a + 1) < args.length) {
        inputFolder = args[++a];
        show("Input path set to: " + inputFolder);
      } else if (args[a].equals("-op") && (a + 1) < args.length) {
        outputFolder = args[++a];
        show("Output path set to: " + outputFolder);
      } else if (args[a].equals("books")) {
        shouldCreateBookFiles = true;
        show("Going to create the book files!");
      } else if (args[a].equals("chapters")) {
        shouldCreateChapterFiles = true;
        show("Going to create the chapter files!");
      } else if (args[a].equals("verses")) {
        shouldCreateVerseFiles = true;
        show("Going to create the verse files!");
      }
    }
  }

  // TODO: Create generic List sorter by word counts? (DRY)
  // Would replace <list>.sort(a,b->etc.) above

  static void show(String message) {
    System.out.println(message);
  }
}
