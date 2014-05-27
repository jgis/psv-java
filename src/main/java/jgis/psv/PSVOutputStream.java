package jgis.psv;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * An OutputStream for writing PSV streams.
 */
public class PSVOutputStream extends FilterOutputStream {

    private AtomicBoolean rowsWritten = new AtomicBoolean(false);
    protected Charset charset = Charset.forName("UTF-8");
    protected byte[] lineending = new byte[]{'\n'};

    public PSVOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    /**
     * Append a row to the output stream.
     *
     * @param fields The fields of the row.
     * @throws IOException
     */
    public void writeRow(CharSequence... fields) throws IOException {

        if (rowsWritten.getAndSet(true)) {
            write(lineending);
        }

        String row = PSVEncoding.encodeRow(fields);
        write(row.getBytes(charset));

    }

}
