package com.company.parser;

/**
 * Created by Pavel on 29.01.2017.
 */
public class Parser {

    public static String split(String infixForm) {
        String infixFormWithoutSpaces = infixForm.replaceAll(" ", "");
        infixFormWithoutSpaces = " " + infixFormWithoutSpaces;
        char[] symbolsMassive = infixFormWithoutSpaces.toCharArray();
        StringBuilder splitInfixForm = new StringBuilder();
        for (int i = 0; i < symbolsMassive.length - 1; i++) {
            if ((symbolsMassive[i] == '-' && (!Character.isDigit(symbolsMassive[i - 1]) && symbolsMassive[i - 1] != ')') && Character.isDigit(symbolsMassive[i + 1])) || ((Character.isDigit(symbolsMassive[i]) && (Character.isDigit(symbolsMassive[i + 1]) || symbolsMassive[i + 1] == '.')))
                    || symbolsMassive[i] == '.') {
                splitInfixForm.append(symbolsMassive[i]);
            } else {
                splitInfixForm.append(symbolsMassive[i]).append(" ");
            }
        }
        splitInfixForm.append(symbolsMassive[symbolsMassive.length - 1]);
        return splitInfixForm.toString().trim();
    }
}
