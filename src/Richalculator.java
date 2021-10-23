import Models.*;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

import Utils.Utils;

public class Richalculator {

    private boolean help;

    public Richalculator() {
        this.help = false;
        Utils.clearConsole();

        PileRPL pile = new PileRPL();
        Scanner scanner = new Scanner(System.in);
        String token;

        Pattern numberRegex = Pattern.compile("^(([-+])?[0-9])*([.]([0-9]+))?$");
        Pattern numberRegex2 = Pattern.compile("^(([-+])?[0-9])*([.]([0-9]+))?[+-]$");

        displayStack(pile, null, null, null);

        richalculator: while (true) {
            token = scanner.nextLine().replaceAll(" ", "").trim();
            try {
                switch (Utils.typeDetector(token)) {
                    case "QUIT" -> {
                        displayStack(pile, null, null, "QUIT");
                        pile.clear();
                        break richalculator;
                    }
                    case "HELP" -> {
                        this.help = true;
                        displayStack(pile, null, null, "HELP");
                    }
                    case "HIDE" -> {
                        this.help = false;
                        displayStack(pile, null, null, "HIDE HELP");
                    }
                    case "CLEAR" -> {
                        pile.clear();
                        displayStack(pile, null, null, "CLEAR");
                    }
                    case "DELETE" -> {
                        pile.delete();
                        displayStack(pile, null,null, "DELETE");
                    }
                    case "NUMBER" -> {
                        pile.stack(new Nombre(Double.parseDouble(token)));
                        displayStack(pile, null, "NUMBER", null);
                    }
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
                        displayStack(pile, null, "COMPLEX", null);
                    }
                    case "VECTOR" -> {
                        String[] stringArray = token.trim().replaceAll("\\[", "").replaceAll("]", "").split(",");
                        Double[] doubleArray = Arrays.stream(stringArray)
                                .map(Double::valueOf)
                                .toArray(Double[]::new);
                        pile.stack(new Vecteur(doubleArray));
                        displayStack(pile, null, "VECTOR", null);
                    }
                    case "ADD" -> {
                        if (pile.pile.size() > 1) {
                            try {
                                pile.add();
                                displayStack(pile, "+", null, null);
                            } catch (Exception e) {
                                displayStack(pile, "+", null, null);
                                displayError(null, "[Error]: Forbidden operation.");
                            }
                        }
                        else {
                            displayStack(pile, null, null, null);
                            displayError(null, "[Warning]: Not enough operands.");
                        }
                    }
                    case "SUB" -> {
                        if (pile.pile.size() > 1){
                            try {
                                pile.sub();
                                displayStack(pile, "-", null, null);
                            } catch (Exception e) {
                                displayStack(pile, "-", null, null);
                                displayError(null, "[Error]: Forbidden operation.");
                            }
                        }
                        else {
                            displayStack(pile, null, null, null);
                            displayError(null, "[Warning]: Not enough operands.");
                        }
                    }
                    case "MUL" -> {
                        if (pile.pile.size() > 1) {
                            try {
                                pile.mul();
                                displayStack(pile, "*", null, null);
                            }catch (Exception e) {
                                displayStack(pile, "*", null, null);
                                displayError(null, "[Error]: Forbidden operation.");
                            }
                        }
                        else {
                            displayStack(pile, null, null, null);
                            displayError(null, "[Warning]: Not enough operands.");
                        }
                    }
                    case "DIV" -> {
                        if (pile.pile.size() > 1) {
                            try {
                                pile.div();
                                displayStack(pile, "/", null, null);
                            } catch (Exception e) {
                                displayStack(pile, "/", null, null);
                                displayError(null, "[Error]: Forbidden operation.");
                            }
                        }
                        else {
                            displayStack(pile, null, null, null);
                            displayError(null, "[Warning]: Not enough operands.");
                        }
                    }
                    case "MOD" -> {
                        if (pile.pile.size() > 1){
                            try {
                                pile.mod();
                                displayStack(pile, "%", null, null);
                            } catch (Exception e) {
                                displayStack(pile, "%", null, null);
                                displayError(null, "[Error]: Forbidden operation.");
                            }
                        }
                        else {
                            Utils.moveCursorXY(10, 10);
                            displayStack(pile, null, null, null);
                            displayError(null, "[Warning]: Not enough operands.");
                        }
                    }
                    case null, default -> {
                        displayStack(pile, null, null, null);
                        displayError(token, "[Warning]: Unknown input: ");
                    }
                }
            } catch (Exception e) {
                displayStack(pile, null, null, null);
                displayError(token, "[Error]: Incorrect command: ");}
        }
        scanner.close();
    }

    private void displayStack(PileRPL pileRPL, String operator, String operand, String command) {
        Utils.clearConsole();

        System.out.println("        ╔═══════════════════════════════════════════════╗");
        System.out.println("        ║            RiChalculator 🐼🦈🦓🐍🦫           ║");
        System.out.println("        ╠═══════════════════════════════════════════════╣");
        System.out.println("        ║                                               ║");
        System.out.println("        ║                                               ║");
        System.out.println("        ║                                               ║");
        System.out.println("        ║                                               ║");
        System.out.println("        ║                                               ║");
        System.out.println("        ╠═══════════════════════════════════════════════╣");
        System.out.println("        ║                                               ║");
        System.out.println("        ╠═══════════════════════════════════════════════╣");
        System.out.println("        ║ >                                             ║");
        System.out.println("        ╚═══════════════════════════════════════════════╝");

        if (pileRPL.pile.size() == 0) {
            Utils.moveCursorXY(4, 11);
            System.out.println("[0]: ");
            Utils.moveCursorXY(4, 20);
            System.out.println("__");
        }
        if (command != null) {
            Utils.moveCursorXY(10, 10);
            System.out.println("[Command]: " );
            Utils.moveCursorXY(10, 25);
            System.out.println(command);
        }

        for (int i=0; i<pileRPL.pile.size(); ++i) {
            if (i < 5) {
                Utils.moveCursorXY(4 + i, 11);
                System.out.println("[" + (pileRPL.pile.size() - i - 1) + "]: ");
                Utils.moveCursorXY(4 + i, 20);
                Utils.moveCursorXY(4 + i, 20);
                System.out.println(pileRPL.pile.get(pileRPL.pile.size() - i - 1));


                if (operator != null) {
                    Utils.moveCursorXY(10, 10);
                    System.out.println("[Operation]: " );
                    Utils.moveCursorXY(10, 25);
                    System.out.println(pileRPL.getOeB() + " " + operator + " " + pileRPL.getOeA());
                } else if (operand != null) {
                    Utils.moveCursorXY(10, 10);
                    System.out.println("[Operand]: " );
                    Utils.moveCursorXY(10, 25);
                    System.out.println(operand);
                } else if (command != null) {
                    Utils.moveCursorXY(10, 10);
                    System.out.println("[Command]: " );
                    Utils.moveCursorXY(10, 25);
                    System.out.println(command);
                }
            }
        }

        if (this.help)
            displayHelp();

        Utils.moveCursorXY(12, 13);
    }

    private void displayError (String token, String errorMessage) {
        Utils.moveCursorXY(10, 10);
        System.out.println("                                              ");
        Utils.moveCursorXY(10, 10);
        if (token != null)
            System.err.println(errorMessage + (token.length() > 17 ? token.substring(0, 17) + "...": token));
        else
            System.err.println(errorMessage);
        Utils.moveCursorXY(12, 13);
        System.out.println("                                            ");
        Utils.moveCursorXY(12, 13);
    }

    private void displayHelp() {
        Utils.moveCursorXY(1, 85);
        System.out.println("    [ RiChalculator Manual ]    ");

        Utils.moveCursorXY(2, 65);
        System.out.println("--- Commands ---");
        Utils.moveCursorXY(3, 65);
        System.out.println("[q] [quit]   : Quit");
        Utils.moveCursorXY(4, 65);
        System.out.println("[c] [clear]  : Clear stack");
        Utils.moveCursorXY(5, 65);
        System.out.println("[d] [delete] : Delete last item in stack");
        Utils.moveCursorXY(6, 65);
        System.out.println("[h] [help]   : Displays help");
        Utils.moveCursorXY(7, 65);
        System.out.println("[hi] [hide]  : Hide help");

        Utils.moveCursorXY(2, 120);
        System.out.println("--- Operators ---");
        Utils.moveCursorXY(3, 120);
        System.out.println("[+] : ADD operators");
        Utils.moveCursorXY(4, 120);
        System.out.println("[-] : SUB operators");
        Utils.moveCursorXY(5, 120);
        System.out.println("[*] : MUL operators");
        Utils.moveCursorXY(6, 120);
        System.out.println("[/] : DIV operators");
        Utils.moveCursorXY(7, 120);
        System.out.println("[%] : MOD operators");

        Utils.moveCursorXY(9, 65);
        System.out.println("--- Operands ---");
        Utils.moveCursorXY(10, 65);
        System.out.println("{+-}a{.b}                    : Number type  [N]");
        Utils.moveCursorXY(11, 65);
        System.out.println("[N, N, N{, [...N]}]          : Vector type  [V]");
        Utils.moveCursorXY(12, 65);
        System.out.println("N[+-]N{ij} or N{ij}[+-]N     : Complex type [C]");
    }

    public static void main(String[] args) {
        new Richalculator();
    }
}