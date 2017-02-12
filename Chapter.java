public class Chapter {
  private String book&Number;
  private String text;
  private int wordCount;

  public Chapter(String book, String text) {
    this.book&Number = book + " "
                            + text.trim()
                                  .split(":")
                                  .indexOf(0);
    this.text = text;
  }

  public String getBook&Number() {
    return this.book&Number;
  }

  public String getText() {
    return this.text;
  }

  public int countWords() {
    if(wordCount != null) {
      return wordCount;
    } else {
      return;
    }
  }
}
