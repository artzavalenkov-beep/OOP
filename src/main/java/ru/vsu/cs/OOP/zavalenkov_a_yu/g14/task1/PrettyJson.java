package ru.vsu.cs.OOP.zavalenkov_a_yu.g14.task1;

public class PrettyJson {

    private final String space;
    private final String newLine;

    public PrettyJson() {
        this.space = "  ";
        this.newLine = "\n";
    }

    public PrettyJson(String space, String newLine) {
        this.space = space;
        this.newLine = newLine;
    }

    public String format(String json) {
        if (json == null) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        int brackets = 0;
        boolean inString = false;
        boolean notInString = false;

        for (int i = 0; i < json.length(); i++) {
            char str = json.charAt(i);

            if (inString) {
                res.append(str);
                if (notInString) {
                    notInString = false;
                } else if (str == '\\') {
                    notInString = true;
                } else if (str == '"') {
                    inString = false;
                }
                continue;
            }

            switch (str) {
                case '"':
                    inString = true;
                    res.append(str);
                    break;

                case '{':
                case '[':
                    res.append(str);
                    brackets = brackets + 1;
                    res.append(newLine);
                    for (int j = 0; j < brackets; j++) {
                        res.append(space);
                    }
                    break;

                case '}':
                case ']':
                    brackets = brackets - 1;
                    res.append(newLine);
                    for (int j = 0; j < brackets; j++) {
                        res.append(space);
                    }
                    res.append(str);
                    break;

                case ',':
                    res.append(str);
                    res.append(newLine);
                    for (int j = 0; j < brackets; j++) {
                        res.append(space);
                    }
                    break;

                case ':':
                    res.append(": ");
                    break;

                case ' ':
                case '\t':
                case '\n':
                case '\r':
                    break;
                default:
                    res.append(str);
            }
        }
        return res.toString();
    }
}