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
  final static Charset INPUT_ENCODING = StandardCharsets.US_ASCII;
  final static Charset OUTPUT_ENCODING = StandardCharsets.UTF_8;

  public static List<Chapter> bookToChapters(Book book) {
    String bookTitle = book.getTitle();
    String[] chapTexts = book.getText()
                             .trim()
                             .split("\\s+(?=\\d{1,3}:1\\s)");
    List<Chapter> chapters = Arrays.stream(chapTexts)
                                   .map(c->new Chapter(bookTitle, c))
                                   .collect(Collectors.toList());
    return chapters;
  }

  public static List<Verse> chapterToVerses(Chapter chapter) {
    String chapterInfo = chapter.getBookAndNumber();
    String[] verseTexts = chapter.getText()
                                 .trim()
                                 .split("\\s+(?=\\d{1,3}:\\d{1,3})");
    List<Verse> verses = Arrays.stream(verseTexts)
                                   .map(v->new Verse(chapterInfo, v))
                                   .collect(Collectors.toList());
    return verses;
  }

  public String readTextFile(String fileName) throws IOException {
    Path path = Paths.get(fileName);
    List<String> lines = Files.readAllLines(path, INPUT_ENCODING);
    return String.join("\n", lines);
  }

  public String readTextFile(String fileName, Charset encoding) throws IOException {
    Path path = Paths.get(fileName);
    List<String> lines = Files.readAllLines(path, encoding);
    return String.join("\n", lines);
  }

  public void writeTextFile(String textToWrite, String fileName) throws IOException {
    Path path = Paths.get(fileName);

    List<String> lines = new ArrayList<String>();
    lines.add(textToWrite);

    Files.write(path, lines, OUTPUT_ENCODING);
  }
}
