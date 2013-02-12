package pl.edu.agh.iisg.timeline.util;

import java.util.LinkedList;
import java.util.List;

public class LineSplitter implements ILineSplitter {

    private final int charsPerLine;

    private List<String> lines = new LinkedList<>();

    private StringBuilder builder;

    public LineSplitter(int charsPerLine) {
        this.charsPerLine = charsPerLine;
    }

    @Override
    public String[] split(String string) {
        clearFields();

        String[] words = splitByWhitespaces(string);
        for (String word : words) {
            addLineIfOverflowChars();
            addWordsIfOverflow(word);
            builder.append(word);
        }
        addLineIfOverflowChars();
        addIfNotEmpty();
        return lines.toArray(new String[lines.size()]);
    }

    /**
     * Splits by whitespaces and leaves these whitespaces in the result array.
     *
     * @param string
     * @return
     */
    private String[] splitByWhitespaces(String string) {
        return string.split("(?<=\\s+)");
    }

    private void clearFields() {
        lines.clear();
        builder = new StringBuilder();
    }

    private void addLineIfOverflowChars() {
        while (builder.length() > charsPerLine) {
            lines.add(builder.substring(0, charsPerLine).trim());
            builder.replace(0, charsPerLine, "");
        }
    }

    private void addWordsIfOverflow(String word) {
        if (builder.length() > 0 && builder.length() + word.length() > charsPerLine) {
            addIfNotEmpty();
            builder = new StringBuilder();
        }
    }

    private void addIfNotEmpty() {
        String toAdd = builder.toString().trim();
        if (!toAdd.isEmpty()) {
            lines.add(toAdd);
        }
    }
}
