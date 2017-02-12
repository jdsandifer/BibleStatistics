public class Verse {
  private String book;
  private String chapter&verse;
  private String text;
  private int wordCount;

  public Verse(String book, String text) {
    this.book = book;
    //this.chapter&verse = regex;
    this.text = text;
  }

  public String getBookChapterVerse() {
    return book + " " + chapter&verse;
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
