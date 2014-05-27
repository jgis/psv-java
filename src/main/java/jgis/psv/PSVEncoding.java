package jgis.psv;

/**
 * Contains encoding routines.
 */
public final class PSVEncoding {

    /**
     * Escapes a character sequence to a PSV field.
     *
     * @param input The character sequence to escape.
     * @return The escaped character sequence.
     */
    public static String escapeField(CharSequence input) {

        if (input == null) {
            return "";
        }

        StringBuilder output = new StringBuilder(input.length());

        for (int i = 0; i < input.length(); i++) {

            char ch = input.charAt(i);

            switch (ch) {
                case '|':
                    output.append("\\|");
                    break;
                case '\r':
                    output.append("\\r");
                    break;
                case '\n':
                    output.append("\\n");
                    break;
                case '\\':
                    output.append("\\\\");
                    break;
                default:
                    output.append(ch);
            }

        }

        return output.toString();
    }

    /**
     * Encodes an array of character sequences to create a row in a PSV stream.
     *
     * @param fields The array of character sequences.
     * @return The encoded string.
     */
    public static String encodeRow(CharSequence[] fields) {

        if (fields == null) {
            return "";
        }

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < fields.length; i++) {

            CharSequence field = fields[i];

            if (i > 0) {
                output.append('|');
            }

            output.append(escapeField(field));

        }

        return output.toString();
    }


}
