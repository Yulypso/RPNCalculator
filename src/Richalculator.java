import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Richalculator {

    private static String typeDetector(String token) {
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
        if (token.equals("clear") || token.equals("c")) {
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

    public static void main(String[] args) {

        PileRPL pile = new PileRPL();

        Scanner scanner = new Scanner(System.in);
        String token;

        Pattern numberRegex = Pattern.compile("^(([-+])?[0-9])*([.]([0-9]+))?$");
        Pattern numberRegex2 = Pattern.compile("^(([-+])?[0-9])*([.]([0-9]+))?[+-]$");

        richalculator: while (true) {
            token = scanner.nextLine().replaceAll(" ", "").trim();
            try {
                switch (typeDetector(token)) {
                    case "QUIT" -> {
                        break richalculator;
                    }
                    case "CLEAR" -> pile.clear();
                    case "DELETE" -> pile.delete();
                    case "NUMBER" -> pile.stack(new Nombre(Double.parseDouble(token)));
                    case "COMPLEX" -> {
                        String[] strings = token.split("[ij]");

                        switch (strings.length) {
                            case 0 ->  pile.stack(new Complexe(1.0)); // i
                            case 1 -> { // a + bi
                                String[] tmp = strings[0].split("[+-]");
                                switch (tmp.length) {
                                    case 0 -> { // +i -i
                                        if (strings[0].equals("+"))
                                            pile.stack(new Complexe(1.0));
                                        else if (strings[0].equals("-"))
                                            pile.stack(new Complexe(-1.0));
                                    }
                                    case 1 -> { // 3.0i 3i 3+i 3-i
                                        if (numberRegex2.matcher(strings[0]).matches()) { // 3+i 3-i
                                            if (strings[0].contains("+"))
                                                pile.stack(new Complexe(Double.parseDouble(tmp[0]), 1.0));
                                            else if (strings[0].contains("-"))
                                                pile.stack(new Complexe(Double.parseDouble(tmp[0]), -1.0));
                                        } else
                                            pile.stack(new Complexe(Double.parseDouble(tmp[0]))); // 3i
                                    }
                                    case 2 -> { // +- 3.0i
                                        if ("".equals(tmp[0]) && numberRegex.matcher(tmp[1]).matches()) { // +3i -3.0i
                                            if (strings[0].contains("+"))
                                                pile.stack(new Complexe(Double.parseDouble(tmp[1])));
                                            else if (strings[0].contains("-"))
                                                pile.stack(new Complexe(-Double.parseDouble(tmp[1])));
                                        } else if (numberRegex.matcher(tmp[0]).matches() && numberRegex.matcher(tmp[1]).matches()) { // 2+3i 2-3i
                                            if (strings[0].contains("+"))
                                                pile.stack(new Complexe(Double.parseDouble(tmp[0]), Double.parseDouble(tmp[1])));
                                            else if (strings[0].contains("-"))
                                                pile.stack(new Complexe(Double.parseDouble(tmp[0]), -Double.parseDouble(tmp[1])));
                                        }
                                    }
                                    case 3 -> {
                                        if ("".equals(tmp[0]) && numberRegex.matcher(tmp[1]).matches() && numberRegex.matcher(tmp[2]).matches()) {
                                            if (strings[0].startsWith("+")) {
                                                if (strings[0].substring(1).contains("+")) // +2.0+3i
                                                    pile.stack(new Complexe(Double.parseDouble(tmp[1]), Double.parseDouble(tmp[2])));
                                                else if (strings[0].substring(1).contains("-")) // +2.0-3i
                                                    pile.stack(new Complexe(Double.parseDouble(tmp[1]), -Double.parseDouble(tmp[2])));
                                            } else if (strings[0].startsWith("-")) {
                                                if (strings[0].substring(1).contains("+")) // -2.0+3i
                                                    pile.stack(new Complexe(-Double.parseDouble(tmp[1]), Double.parseDouble(tmp[2])));
                                                else if (strings[0].substring(1).contains("-")) // -2.0-3i
                                                    pile.stack(new Complexe(-Double.parseDouble(tmp[1]), -Double.parseDouble(tmp[2])));
                                            }
                                        }
                                    }
                                }
                            }
                            case 2 -> pile.stack(new Complexe(Double.parseDouble(strings[1]), Double.parseDouble(strings[0]))); // bi + a
                        }
                    }
                    case "VECTOR" -> {
                        String[] stringArray = token.trim().replaceAll("\\[", "").replaceAll("]", "").split(",");
                        Double[] doubleArray = Arrays.stream(stringArray)
                                .map(Double::valueOf)
                                .toArray(Double[]::new);
                        pile.stack(new Vecteur(doubleArray));
                    }
                    case "ADD" -> {
                        if (pile.pile.size() > 1) pile.add();
                        else System.err.println("[Warning] - Not enough operands.");
                    }
                    case "SUB" -> {
                        if (pile.pile.size() > 1) pile.sub();
                        else System.err.println("[Warning] - Not enough operands.");
                    }
                    case "MUL" -> {
                        if (pile.pile.size() > 1) pile.mul();
                        else System.err.println("[Warning] - Not enough operands.");
                    }
                    case "DIV" -> {
                        if (pile.pile.size() > 1) pile.div();
                        else System.err.println("[Warning] - Not enough operands.");
                    }
                    case "MOD" -> {
                        if (pile.pile.size() > 1) pile.mod();
                        else System.err.println("[Warning] - Not enough operands.");
                    }
                    case null, default -> System.err.println("[Warning] - Unknown input: " + token);
                }
            } catch (Exception e) {System.err.println("[Warning] - " + e.getMessage());}
            System.out.println(pile);
        }
        scanner.close();
    }
}
