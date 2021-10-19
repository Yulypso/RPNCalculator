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
        Pattern vectorRegex = Pattern.compile("^(([\\[])(([-+])?[0-9])*([.]([0-9]+))?)((,)(([-+])?[0-9])*([.]([0-9]+))*)*([]])$");

        System.out.println(token);

        if (token.equals("clear") || token.equals("c")) {
            System.out.println("CLEAR");
            return "CLEAR";
        }
        else if (token.equals("quit") || token.equals("q")) {
            System.out.println("QUIT");
            return "QUIT";
        }
        else if (addRegex.matcher(token).matches()) {
            System.out.println("ADD MATCH");
            return "ADD";
        }
        else if (subRegex.matcher(token).matches()) {
            System.out.println("SUB MATCH");
            return "SUB";
        }
        else if (mulRegex.matcher(token).matches()) {
            System.out.println("MUL MATCH");
            return "MUL";
        }
        else if (divRegex.matcher(token).matches()) {
            System.out.println("DIV MATCH");
            return "DIV";
        }
        else if (modRegex.matcher(token).matches()) {
            System.out.println("MOD MATCH");
            return "MOD";
        }

        else if (vectorRegex.matcher(token).matches()) {
            return "VECTOR";
        }
        else if (numberRegex.matcher(token).matches()) {
            return "NUMBER";
        }

        return null;
    }

    public static void main(String[] args) {

        PileRPL pile = new PileRPL();

        Scanner scanner = new Scanner(System.in);
        String token;

        loop: while (true) {
            token = scanner.nextLine().trim();
            System.out.println("token: " + token);

            try {
                switch (typeDetector(token)) {
                    case "QUIT" -> {
                        break loop;
                    }
                    case "CLEAR" -> pile.clear();
                    case "NUMBER" -> pile.stack(new Nombre(Double.parseDouble(token)));
                    case "COMPLEX" -> {

                    }
                    case "VECTOR" -> {
                        System.out.println("PASS");
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
