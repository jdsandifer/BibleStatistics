import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class BibleReaderTest {
  final static String expectedResult =
      "This is a test file.\n"
      + "It should be in US_ASCII encoding with CRLF new line terminators.";

  @Test
  public void readTextFileReturnsText() throws IOException {
    BibleReader reader = new BibleReader();
    String result = reader.readTextFile("../input/test-file.txt");
    assertEquals("readTextFile() should return test text.", expectedResult, result);
  }

  @Test
  public void readTextFileWithEncodingReturnsText() throws IOException {
    BibleReader reader = new BibleReader();
    String result = reader.readTextFile("../input/test-file.txt",
        StandardCharsets.US_ASCII);
    assertEquals("readTextFile(with encoding) should return test text.", expectedResult, result);
  }
}
