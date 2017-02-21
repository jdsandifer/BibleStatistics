import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class BookTest {
  private Book book;

  @Before
  public void setUp() {
    book = new Book("Not A Book\n"
                    + "1:150 This is text in the book. 1:151 I am.\n"
                    + "1:152 This is more text in the book.\n"
                    + "2:1 This is even more text in the book.");
  }

  @Test
  public void getTitleReturnsTitle() {
    String result = book.getTitle();
    assertEquals("Book should return title.", "Not A Book", result);
  }

  @Test
  public void getTextReturnsText() {
    String result = book.getText();
    assertEquals("Book should return text.",
                 "1:150 This is text in the book. 1:151 I am.\n"
                 + "1:152 This is more text in the book.\n"
                 + "2:1 This is even more text in the book.",
                 result);
  }

  @Test
  public void countWordsReturnsWordCount() {
    int result = book.countWords();
    assertEquals("Book should return word count.", 23, result);
  }
}
