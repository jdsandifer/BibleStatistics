import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Test;

public class BibleParserTest {
  @Test
  public void parseBooksReturnsBookList() {
    List<Book> expectedList = new ArrayList<Book>();
    Book a = new Book("ABook\n1:1 The first verse.");
    Book b = new Book("AnotherBook\n1:1 Another first verse.");
    expectedList.add(a);
    expectedList.add(b);

    List<Book> resultList = BibleParser.parseBooks("ABook\n1:1 The first verse.\n\nAnotherBook\n1:1 Another first verse.");

    assertThat("Should return matching Book list.", resultList, is(expectedList));
  }

  @Test
  public void parseChaptersReturnsChapterList() {
    List<Book> bookList = new ArrayList<Book>();
    Book a = new Book("ABook\n1:1 The first verse.");
    Book b = new Book("AnotherBook\n1:1 Another first verse.");
    bookList.add(a);
    bookList.add(b);

    List<Chapter> expectedList = new ArrayList<Chapter>();
    Chapter c = new Chapter("ABook", "1:1 The first verse.");
    Chapter d = new Chapter("AnotherBook", "1:1 Another first verse.");
    expectedList.add(c);
    expectedList.add(d);

    List<Chapter> resultList = BibleParser.parseChapters(bookList);

    assertThat("Should return matching Chapter list.", resultList, is(expectedList));
  }

  @Test
  public void parseVersesReturnsVerseList() {
    List<Chapter> chapterList = new ArrayList<Chapter>();
    Chapter a = new Chapter("ABook", "1:1 The first verse.");
    Chapter b = new Chapter("AnotherBook", "1:1 Another first verse.");
    chapterList.add(a);
    chapterList.add(b);

    List<Verse> expectedList = new ArrayList<Verse>();
    Verse c = new Verse("ABook 1", "1:1 The first verse.");
    Verse d = new Verse("AnotherBook 1", "1:1 Another first verse.");
    expectedList.add(c);
    expectedList.add(d);

    List<Verse> resultList = BibleParser.parseVerses(chapterList);

    assertThat("Should return matching Verse list.", resultList, is(expectedList));
  }
}
