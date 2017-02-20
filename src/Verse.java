public class Verse {
  private String bookChapterVerse;
  private String text;
  private int wordCount = 0;

  public Verse(String bookAndNumber, String text) {
    String[] words = text.trim()
                         .split("[:\\s]+");
    this.bookChapterVerse = bookAndNumber + ":" + words[1];
    this.text = text.trim();
    //System.out.println(toString());
  }

  public String getBookChapterVerse() {
    return bookChapterVerse;
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
    return "[" + getBookChapterVerse() + ", " + countWords() + " words]";
  }
}
