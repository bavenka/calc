package com.company.algorithms;

import java.util.Stack;

/**
 * Created by Pavel on 27.01.2017.
 */
public class Converter {

    private static int getOperatorPriority(char operator) {
        switch (operator) {
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    private static boolean isOperation(String token) {
        if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^")) {
            return true;
        }
        return false;
    }

    public static String toRpn(String splitInfixForm) {
        Stack<Character> operationsStack = new Stack<>();
        Stack<Integer> prioritiesStack = new Stack<>();
        StringBuilder postfixForm = new StringBuilder();
        for (String token : splitInfixForm.split(" ")) {
            if (token.isEmpty()) {
                continue;
            }
            if (Character.isDigit(token.charAt(0)) || (token.charAt(0) == '-' && token.length() > 1)) {
                postfixForm.append(token).append(" ");
                continue;
            }
            if (isOperation(token)) {
                char existingOp = token.charAt(0);
                int existingOpPriority = getOperatorPriority(existingOp);
                if (operationsStack.isEmpty()) {
                    prioritiesStack.push(existingOpPriority);
                    operationsStack.push(existingOp);
                } else {
                    while (!prioritiesStack.isEmpty()) {
                        char peekOp = operationsStack.peek();
                        int peekOpPriority = getOperatorPriority(peekOp);
                        if (existingOpPriority <= peekOpPriority && peekOpPriority != -1) {
                            postfixForm.append(operationsStack.pop()).append(" ");
                            prioritiesStack.pop();
                        } else {
                            break;
                        }
                    }
                    operationsStack.push(existingOp);
                    prioritiesStack.push(existingOpPriority);
                }
                continue;
            }
            if (token.equals("(")) {
                operationsStack.push(token.charAt(0));
                prioritiesStack.push(0);
                continue;
            }
            if (token.equals(")")) {
                while (operationsStack.peek() != '(') {
                    postfixForm.append(operationsStack.pop()).append(" ");
                    prioritiesStack.pop();
                }
                operationsStack.pop();
                prioritiesStack.pop();
            }
        }
        while (!operationsStack.isEmpty()) {
            postfixForm.append(operationsStack.pop()).append(" ");
            prioritiesStack.pop();
        }
        return postfixForm.toString();
    }
}
