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
  public void getBookAndNumberReturnsInfo() {
    String result = chapter.getBookAndNumber();
    assertEquals("Chapter should return info.", "Not A Book 1", result);
  }

  @Test
  public void getTextReturnsText() {
    String result = chapter.getText();
    assertEquals("Chapter should return text.",
                 "1:150 This is text in the chapter. 1:151 I am.\n"
                 + "1:152 This is more text in the chapter.",
                 result);
  }

  @Test
  public void countWordsReturnsWordCount() {
    int result = chapter.countWords();
    assertEquals("Chapter should return word count.", 15, result);
  }
}
