public class Chapter {
  private String bookAndNumber;
  private String text;
  private int wordCount = 0;

  public Chapter(String book, String text) {
    String[] chapPlus = text.trim()
                            .split(":");
    this.bookAndNumber = book + " " + chapPlus[0];
    this.text = text.trim();
    /*System.out.println("Created "
        + bookAndNumber()
        + ". ("
        + wordCount()
        + ")");*/
  }

  public String bookAndNumber() {
    return bookAndNumber;
  }

  public String text() {
    return text;
  }

  public int wordCount() {
    if (wordCount == 0) {
      String[] words = this.text.replaceAll("\\d{1,3}:\\d{1,3}\\s", "")
                                .replaceAll("\\s+", " ")
                                .split(" ");
      wordCount = words.length;
    }
    return wordCount;
  }

  public String toString() {
    return "[" + getBookAndNumber() + ", " + countWords() + " words]";
  }
}
