import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Book {
  private String title;
  private String text;
  private int wordCount = 0;

  public Book(String rawText) {
    //System.out.println(rawText.substring(0,100));
    Pattern regex = Pattern.compile("^(\\d\\s)?[A-z]+(?:\\s[A-z]+)*(?=\\s*1:1)");
    Matcher matcher = regex.matcher(rawText.trim());
    matcher.find();
    this.title = matcher.group(0);

    this.text = rawText.trim()
                       .replaceFirst(
                          "^(\\d\\s)?[A-z]+(?:\\s[A-z]+)*\\s*(?=1:1)", "");

    //System.out.println("Initialized " + this.title + ". (" + countWords() + ")");
  }

  public String getTitle() {
    return title;
  }

  public String getText() {
    return text;
  }

  public int countWords() {
    if (wordCount == 0) {
      String[] words = this.text.replaceAll("\\d{1,3}:\\d{1,3}\\s", "")
                                .replaceAll("\\s+", " ")
                                .split(" ");
      wordCount = words.length;
    }
    return wordCount;
  }

  public String toString() {
    return "[" + getTitle() + ", " + countWords() + " words]";
  }
}
