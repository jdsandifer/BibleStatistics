import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

// This test seems to be a lost cause. Seems like you can only
// guess at the encoding by analyzing character frequencies and/or
// present samples to the user to have them pick the correct one.
public class EncodingReader {
  final static String TEST_FILE = "..\\input\\test-file.txt";
  final static Charset[] ENCODINGS = {StandardCharsets.UTF_8,
                                      StandardCharsets.US_ASCII,
                                      StandardCharsets.ISO_8859_1,
                                      StandardCharsets.UTF_16,
                                      StandardCharsets.UTF_16BE,
                                      StandardCharsets.UTF_16LE};

  public static void main(String[] args) {
    BibleReader text = new BibleReader();

    // Try each encoding and list encodings that work.
    for (int i = 0; i < ENCODINGS.length; i++) {
      try {
        String lines = text.readTextFile(TEST_FILE, ENCODINGS[i]);
        System.out.println("This encoding worked: "
                           + ENCODINGS[i].toString()
                           + ".");
        System.out.println(lines);
      } catch(IOException e) {
        System.out.println("IO Error: " + e.toString());
      }
    }
  }

}
