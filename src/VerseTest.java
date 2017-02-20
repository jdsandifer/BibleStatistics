import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class VerseTest {
  private Verse verse;

  @Before
  public void setUp() {
    verse = new Verse("Not A Book 1",
                      "1:150 This is the text of the verse.");
  }

  @Test
  public void getBookChapterVerseReturnsInfo() {
    String result = verse.getBookChapterVerse();
    assertEquals("Verse should return info.", "Not A Book 1:150", result);
  }

  @Test
  public void getTextReturnsText() {
    String result = verse.getText();
    assertEquals("Verse should return text.",
                 "1:150 This is the text of the verse.",
                 result);
  }

  @Test
  public void countWordsReturnsWordCount() {
    int result = verse.countWords();
    assertEquals("Verse should return word count.", 7, result);
  }
}
