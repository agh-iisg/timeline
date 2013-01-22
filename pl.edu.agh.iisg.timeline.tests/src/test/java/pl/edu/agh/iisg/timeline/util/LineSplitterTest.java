package pl.edu.agh.iisg.timeline.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LineSplitterTest {

    @Test
    public void testSplit() {
        // given
        LineSplitter splitter = new LineSplitter(10);
        String string = "Ala ma kota, a kot ma Ale";

        // when
        String[] res = splitter.split(string);

        // then
        assertEquals(3, res.length);
        assertEquals("Ala ma", res[0]);
        assertEquals("kota, a", res[1]);
        assertEquals("kot ma Ale", res[2]);
    }

    @Test
    public void testSplitTooLongWord() {
        // given
        LineSplitter splitter = new LineSplitter(10);
        String string = "Alamakota,a kot ma Ale";

        // when
        String[] res = splitter.split(string);

        // then
        assertEquals(3, res.length);
        assertEquals("Alamakota,", res[0]);
        assertEquals("a kot ma", res[1]);
        assertEquals("Ale", res[2]);
    }

    @Test
    public void testSplitTwoTimesTooLongWord() {
        // given
        LineSplitter splitter = new LineSplitter(4);
        String string = "Alamakota,a kot ma Ale";

        // when
        String[] res = splitter.split(string);

        // then
        assertEquals(6, res.length);
        assertEquals("Alam", res[0]);
        assertEquals("akot", res[1]);
        assertEquals("a,a", res[2]);
        assertEquals("kot", res[3]);
        assertEquals("ma", res[4]);
        assertEquals("Ale", res[5]);
    }

    @Test
    public void testSplitStartFromWhitespace() {
        // given
        LineSplitter splitter = new LineSplitter(11);
        String string = "Alamakota,a kot ma Ale";

        // when
        String[] res = splitter.split(string);

        // then
        assertEquals(2, res.length);
        assertEquals("Alamakota,a", res[0]);
        assertEquals("kot ma Ale", res[1]);
    }

    @Test
    public void testSplitTwoLongWordsBesideEachOther() {
        // given
        LineSplitter splitter = new LineSplitter(10);
        String string = "Firma rozpoczyna dzialalnosc";

        // when
        String[] res = splitter.split(string);

        // then
        assertEquals(4, res.length);
        assertEquals("Firma", res[0]);
        assertEquals("rozpoczyna", res[1]);
        assertEquals("dzialalnos", res[2]);
        assertEquals("c", res[3]);

    }

}
