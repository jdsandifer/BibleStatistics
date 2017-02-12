import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Book {
  private String title;
  private String text;
  private int wordCount = 0;

  public Book(String rawText) {
    Pattern regex = Pattern.compile("^(\\d\\s)?[A-z]+");
    Matcher matcher = regex.matcher(rawText.trim());
    matcher.find();
    this.title = matcher.group(0);
    System.out.println("Title: " + this.title);

    this.text = rawText.trim()
                        .replaceFirst("^(\\d\\s)?[A-z]+\\s*", "");
    System.out.println("Text: " + this.text);
  }

  public String getTitle() {
    return title;
  }

  public String getText() {
    return text;
  }

  public int countWords() {
    if (wordCount != 0) {
      return wordCount;
    } else {
      // TODO: Add counting functionality.
      return -1;
    }
  }
}
