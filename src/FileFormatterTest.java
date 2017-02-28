import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class FileFormatterTest {
  static List<Book> books;
  @Before
  public void setUp() {
    books = Arrays.asList(new Book("Joe\r\n1:1 Hi there. 1:2 Bye!"),
                          new Book("Bob\r\n1:1 Yo! 1:2 What's up?"),
                          new Book("Frank\r\n1:1 What's happening? \r\n1:2 Nothing much. You?\r\n2:1 Life is happening..."));
  }

  @Test
  public void formatBookTextReturnsString() {
    String expectation = "Joe -> 3 words\r\n\r\n1:1 Hi there. 1:2 Bye!\r\n\r\n\r\nBob -> 3 words\r\n\r\n1:1 Yo! 1:2 What's up?\r\n\r\n\r\nFrank -> 8 words\r\n\r\n1:1 What's happening? \r\n1:2 Nothing much. You?\r\n2:1 Life is happening...\r\n\r\n\r\n";
    String result = FileFormatter.formatBookText(books);

    assertEquals("FileFormatter should return formatted string.", expectation, result);
  }
}
