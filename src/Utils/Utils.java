package Utils;

import java.util.regex.Pattern;

public class Utils {

    public static String typeDetector(String token) {
        Pattern addRegex = Pattern.compile("^([+])");
        Pattern subRegex = Pattern.compile("^([\\-])");
        Pattern mulRegex = Pattern.compile("^([*])");
        Pattern divRegex = Pattern.compile("^([/])");
        Pattern modRegex = Pattern.compile("^([%])");

        Pattern numberRegex = Pattern.compile("^(([-+])?[0-9])*([.]([0-9]+))?$");
        Pattern complexRegex = Pattern.compile("^([^ij])*([ij]){1}(([^ij])*)$");
        Pattern vectorRegex = Pattern.compile("^(([\\[])(([-+])?[0-9])*([.]([0-9]+))?)((,)(([-+])?[0-9])*([.]([0-9]+))*)*([]])$");

        if (token.equals("delete") || token.equals("d")) {
            return "DELETE";
        }
        else if (token.equals("help") || token.equals("h")) {
            return "HELP";
        }
        else if (token.equals("hide") || token.equals("hi")) {
            return "HIDE";
        }
        else if (token.equals("clear") || token.equals("c")) {
            return "CLEAR";
        }
        else if (token.equals("quit") || token.equals("q")) {
            return "QUIT";
        }
        else if (vectorRegex.matcher(token).matches()) {
            return "VECTOR";
        }
        else if (complexRegex.matcher(token).matches()) {
            String[] strings = token.split("[ij]");
            switch (strings.length) {
                case 0 -> {
                    return "COMPLEX"; // i
                }
                case 1 -> { // i at the start or end
                    String[] tmp = strings[0].split("[+-]");
                    int cpt = 0;
                    for (String s : tmp) {
                        if (numberRegex.matcher(s).matches()) {
                            ++cpt;
                        }
                    }
                    if (cpt == tmp.length)
                        return "COMPLEX";
                }
                case 2 -> {
                    int cpt = 0;
                    for (String string : strings) {
                        if (numberRegex.matcher(string).matches()) {
                            ++cpt;
                        }
                    }
                    if (cpt == strings.length)
                        return "COMPLEX";
                }
            }
        }
        else if (numberRegex.matcher(token).matches()) {
            return "NUMBER";
        }
        else if (addRegex.matcher(token).matches()) {
            return "ADD";
        }
        else if (subRegex.matcher(token).matches()) {
            return "SUB";
        }
        else if (mulRegex.matcher(token).matches()) {
            return "MUL";
        }
        else if (divRegex.matcher(token).matches()) {
            return "DIV";
        }
        else if (modRegex.matcher(token).matches()) {
            return "MOD";
        }
        return null;
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void moveCursorXY(int X, int Y) {
        System.out.printf("%c[%d;%df", 0x1B, X, Y);
    }
}
