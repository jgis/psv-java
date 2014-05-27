package jgis.psv;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PSVEncodingTest {

    @Test
    public void testEscapeField() throws Exception {

        // null values are treated as empty field
        assertEquals("", PSVEncoding.escapeField(null));

        // characters are escaped without change and case transformations
        assertEquals("abC", PSVEncoding.escapeField("abC"));

        // pipe symbols are escaped with a slash
        assertEquals("cat log \\| grep error", PSVEncoding.escapeField("cat log | grep error"));

        // escape carriage returns and line feeds
        assertEquals("first line\\r\\n second line\\nthird line",
                PSVEncoding.escapeField("first line\r\n second line\nthird line"));

        // escape the backslash
        assertEquals("The backslash \\\\ is escaped as well",
                PSVEncoding.escapeField("The backslash \\ is escaped as well"));

    }

    @Test
    public void testEncodeLine() throws Exception {

        // an empty array leads to a single field
        assertEquals("", PSVEncoding.encodeRow(null));

        // null or empty string elements are empty fields
        assertEquals("||", PSVEncoding.encodeRow(new String[]{null, "", null}));

        // a more typical example. strings are not trimmed.
        assertEquals(" hello |\\|SV|world", PSVEncoding.encodeRow(new String[]{" hello ", "|SV", "world"}));


    }

}