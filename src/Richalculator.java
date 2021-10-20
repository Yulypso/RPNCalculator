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

/*
cas tmp = 3
-2.0-3.0i
+2.0-3.0i

cas tmp = 2
2+3i
2.0+3.0i
2-3i
2.0-3.0i
+3i
-3i
+3.0i
-3.0i

cas tmp = 1 OK
3i
3.0i
3+i
3-i

cas tmp = 0 OK
+i
-i

cas strings = 0 OK
i


plus tard
3i+2
3.0i-2.0
-3.0i-1.0*/
        //System.out.println(token);

        if (token.equals("clear") || token.equals("c")) {
            System.out.println("CLEAR");
            return "CLEAR";
        }
        else if (token.equals("quit") || token.equals("q")) {
            System.out.println("QUIT");
            return "QUIT";
        }


        else if (vectorRegex.matcher(token).matches()) {
            return "VECTOR";
        }
        else if (complexRegex.matcher(token).matches()) {
            String[] strings = token.split("[ij]");
            //System.out.println("array: "+ Arrays.toString(strings) + " length: " + strings.length);
            switch (strings.length) {
                case 0 -> {
                    return "COMPLEX"; // i
                }
                case 1 -> { // i at the start or end
                        String[] tmp = strings[0].split("[+-]");
                        //System.out.println("array: "+ Arrays.toString(tmp) + " length: " + tmp.length);

                        int cpt = 0;
                        for (int i = 0; i < tmp.length; i++) {
                            if (numberRegex.matcher(tmp[i]).matches()) {
                                ++cpt;
                            }
                        }
                        if (cpt == tmp.length)
                            return "COMPLEX";
                }
                case 2 -> {
                    int cpt = 0;
                    for (int i = 0; i < strings.length; i++) {
                        if (numberRegex.matcher(strings[i]).matches()) {
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
        return null;
    }

    public static void main(String[] args) {

        PileRPL pile = new PileRPL();

        Scanner scanner = new Scanner(System.in);
        String token;

        Pattern complexPMRegex = Pattern.compile("[+-]");
        Pattern numberRegex = Pattern.compile("^(([-+])?[0-9])*([.]([0-9]+))?$");
        Pattern numberRegex2 = Pattern.compile("^(([-+])?[0-9])*([.]([0-9]+))?[+-]$");

        richalculator: while (true) {
            token = scanner.nextLine().replaceAll(" ", "").trim();
            System.out.println("token: " + token);

            try {
                switch (typeDetector(token)) {
                    case "QUIT" -> {
                        break richalculator;
                    }
                    case "CLEAR" -> pile.clear();
                    case "NUMBER" -> pile.stack(new Nombre(Double.parseDouble(token)));
                    case "COMPLEX" -> {
                        System.out.println("Complex token: " + token);
                        String[] strings = token.split("[ij]");
                        System.out.println("strings length (ij): " + strings.length);

                        switch (strings.length) {
                            case 0 -> { // i
                                pile.stack(new Complexe(1.0));
                            }
                            case 1 -> {
                                String[] tmp = strings[0].split("[+-]");
                                System.out.println("tmp length (+-): " + tmp.length);
                                switch (tmp.length) {
                                    case 0 -> { // +i -i
                                        if (strings[0].equals("+"))
                                            pile.stack(new Complexe(1.0));
                                        else if(strings[0].equals("-"))
                                            pile.stack(new Complexe(-1.0));
                                    }
                                    case 1 -> { // 3.0i 3i 3+i 3-i
                                        System.out.println("tmp[0]: [" + tmp[0] + "]");
                                        System.out.println("strings[0]: [" + strings[0] + "]");

                                        if (numberRegex2.matcher(strings[0]).matches()) { // 3+i 3-i
                                            if (strings[0].contains("+"))
                                                pile.stack(new Complexe(Double.parseDouble(tmp[0]), 1.0));
                                            else if (strings[0].contains("-"))
                                                pile.stack(new Complexe(Double.parseDouble(tmp[0]), -1.0));
                                        } else
                                            pile.stack(new Complexe(Double.parseDouble(tmp[0]))); // 3i
                                    }
                                    case 2 -> { // +- 3.0i
                                        System.out.println("tmp[0][1]: [" + tmp[0] + "][" + tmp[1] + "]");
                                        System.out.println("strings[0]: [" + strings[0] + "]");

                                    }
                                }



                            }
                        }
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
