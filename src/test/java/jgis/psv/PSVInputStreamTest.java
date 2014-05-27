package jgis.psv;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class PSVInputStreamTest {

    @Test
    public void testReadRow() throws Exception {

        String sample = "first| line \r\n ||\n|\\|\\\\\\r\\n\nthird\n";

        ByteArrayInputStream bis = new ByteArrayInputStream(sample.getBytes("UTF-8"));

        PSVInputStream in = new PSVInputStream(bis);

        String[] row1 = in.readRow();
        assertEquals(2, row1.length);
        assertEquals("first", row1[0]);
        assertEquals(" line ", row1[1]);

        String[] row2 = in.readRow();
        assertEquals(3, row2.length);

        assertEquals(" ", row2[0]);
        assertEquals("", row2[1]);
        assertEquals("", row2[2]);

        String[] row3 = in.readRow();
        assertEquals(2, row3.length);
        assertEquals("", row3[0]);
        assertEquals("|\\\r\n", row3[1]);

        String[] row4 = in.readRow();
        assertEquals(1, row4.length);
        assertEquals("third", row4[0]);

        String[] row5 = in.readRow();
        assertNull(row5);

    }

}