import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class ChapterTest {
  private Chapter chapter;

  @Before
  public void setUp() {
    chapter = new Chapter("Not A Book",
                          "1:150 This is text in the chapter. 1:151 I am.\n"
                          + "1:152 This is more text in the chapter.");
  }

  @Test
  public void BookAndNumber_method_returns_the_info() {
    String result = chapter.bookAndNumber();
    assertEquals("Chapter should return info.", "Not A Book 1", result);
  }

  @Test
  public void Text_method_returns_the_text() {
    String result = chapter.text();
    assertEquals("Chapter should return text.",
                 "1:150 This is text in the chapter. 1:151 I am.\n"
                 + "1:152 This is more text in the chapter.",
                 result);
  }

  @Test
  public void WordCount_method_returns_the_word_count() {
    int result = chapter.wordCount();
    assertEquals("Chapter should return word count.", 15, result);
  }
}
