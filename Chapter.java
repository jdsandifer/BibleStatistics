public class Chapter {
  private String book&Number;
  private String text;
  private int wordCount = 0;

  public Chapter(String book, String text) {
    String[] chapPlus = text.trim()
                            .split(":");
    this.book&Number = book + " " + chapPlus[0];
    this.text = text.trim();
  }

  public String getBook&Number() {
    return book&Number;
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
    return "[" + getBook&Number() + ", " + countWords() + " words]";
  }
}
