package jgis.psv;

import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

public class PSVOutputStreamTest {

    @Test
    public void testEncodeStream() throws Exception {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PSVOutputStream out = new PSVOutputStream(bos);

        out.writeRow("this", "is", "the", "first", "row");
        out.writeRow(new String[] { "this", "is", "the", "second", "row"});
        out.writeRow("this", "is", "the", "third", "row", "with more fields");

        out.close();

        byte[] bytes = bos.toByteArray();

        String result = new String(bytes, "UTF-8");

        assertEquals("this|is|the|first|row\nthis|is|the|second|row\nthis|is|the|third|row|with more fields", result);

    }

}