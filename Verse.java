public class Verse {
  private String book;
  private String chapter&verse;
  private String text;
  private int wordCount = 0;

  public Verse(String book, String text) {
    this.book = book;
    String[] words = text.trim()
                         .split(" ");
    this.chapter&verse = words[0];
    this.text = text.trim();
  }

  public String getBookChapterVerse() {
    return book + " " + chapter&verse;
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
