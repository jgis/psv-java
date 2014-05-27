package jgis.psv;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class PSVInputStream extends FilterInputStream {

    protected Charset charset = Charset.forName("UTF-8");
    private InputStreamReader input;

    protected PSVInputStream(InputStream inputStream) {
        super(inputStream);
        input = new InputStreamReader(inputStream, charset);
    }

    public String[] readRow() throws IOException {

        List<String> fields = new ArrayList<String>();

        StringBuilder field = new StringBuilder();

        int ich;
        boolean escape = false;
        boolean stop = false;
        boolean append = false;

        while (!stop && (ich = input.read()) != -1) {
            char ch = (char) ich;

            append = true;

            switch (ch) {
                case '\r':
                    //ignore;
                    break;
                case '\\':
                    if (escape) {
                        field.append('\\');
                        escape = false;
                    } else {
                        escape = true;
                    }
                    break;
                case '\n':
                    stop = true;
                    break;
                case 'r':
                    if (escape) {
                        field.append('\r');
                        escape = false;
                    } else {
                        field.append(ch);
                    }
                    break;
                case 'n':
                    if (escape) {
                        field.append('\n');
                        escape = false;
                    } else {
                        field.append(ch);
                    }
                    break;
                case '|':
                    if (escape) {
                        field.append('|');
                        escape = false;
                    } else {
                        fields.add(field.toString());
                        field = new StringBuilder();

                    }
                    break;
                default:
                    escape = false;
                    field.append(ch);

            }

        }

        if (append) {
            fields.add(field.toString());
        }

        if (fields.size() > 0) {
            String[] result = new String[fields.size()];
            result = fields.toArray(result);
            return result;
        }

        return null;

    }

}
