package com.company.validator;

import java.util.Stack;
import java.util.regex.Pattern;

/**
 * Created by Pavel on 27.01.2017.
 */
public class Validator {

    public static boolean areBracketsBalanced(String expression) {
        Stack<Character> bracketsStack = new Stack<Character>();
        for (char symbol : expression.toCharArray()) {
            if (symbol == '(') {
                bracketsStack.push(symbol);
            }
            if (symbol == ')') {
                if (bracketsStack.isEmpty()) {
                    return false;
                }
                bracketsStack.pop();
            }
        }
        if (!bracketsStack.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean isExpressionValid(String expression) {
        return Pattern.matches("\\s*\\(*-?\\d*\\.?\\d+(\\s*[-+ */^]\\s*\\(*\\s*-?\\s*\\d*\\.?\\d+\\s*\\)*\\s*)*\\s*", expression);
    }

}
