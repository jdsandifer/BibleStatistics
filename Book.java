public class Book {
  private String title;
  private String text;
  private int wordCount;

  //TODO: Need to test this constructor for functionality.
  public Book(String rawText) {
    String[] parts = rawText.trim()
                            .split("(?<=^(\\d\\s)?[A-z]+)\\s+");
    this.text = parts[0];
    this.title = parts[1];
  }

  public String getTitle() {
    return title;
  }

  public String getText() {
    return text;
  }

  public int countWords() {
    if(wordCount != null) {
      return wordCount;
    } else {
      return;
    }
  }
}
