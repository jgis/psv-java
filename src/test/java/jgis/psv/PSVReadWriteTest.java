package jgis.psv;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

public class PSVReadWriteTest {

    @Test
    public void testReadWriteSample() throws Exception {

        String[] row1 = new String[]{"a", "very", "simple", "row"};
        String[] row2 = new String[]{"", null, " ", null};
        String[] row3 = new String[]{"another row"};


        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PSVOutputStream out = new PSVOutputStream(bos);
        out.writeRow(row1);
        out.writeRow(row2);
        out.writeRow(row3);
        out.close();

        byte[] bytes = bos.toByteArray();

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        PSVInputStream in = new PSVInputStream(bis);

        String[] i1 = in.readRow();
        assertArrayEquals(row1, i1);

        String[] i2 = in.readRow();
        assertArrayEquals(new String[] {"", "", " ", ""}, i2);

        String[] i3 = in.readRow();
        assertArrayEquals(new String[] {"another row"}, i3);

        String[] i4 = in.readRow();
        assertNull(i4);

    }


}
