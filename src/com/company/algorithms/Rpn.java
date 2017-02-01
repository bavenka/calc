package com.company.algorithms;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

/**
 * Created by Pavel on 27.01.2017.
 */
public class Rpn {

    private static double getOperationResult(double op1, double op2, char operation) {
        switch (operation) {
            case '+':
                return op2 + op1;
            case '-':
                return op2 - op1;
            case '*':
                return op2 * op1;
            case '/':
                if (op1 == 0) {
                    return 0;
                }
                return op2 / op1;
            case '^':
                return Math.pow(op2, op1);
        }
        return -1;
    }

    public static String calculate(String postfixForm) {
        Stack<Double> stack = new Stack<>();
        StringBuilder calculationSteps = new StringBuilder();
        int i = 1;
        for (String token : postfixForm.split(" ")) {
            if (Character.isDigit(token.charAt(0)) || (token.charAt(0) == '-' && token.length() > 1)) {
                stack.push(Double.parseDouble(token));
            } else {
                double operationResult = 0;
                double op1 = stack.pop();
                double op2 = stack.pop();
                operationResult = new BigDecimal(getOperationResult(op1, op2, token.charAt(0))).setScale(3, RoundingMode.UP).doubleValue();
                calculationSteps.append(i).append(") ").append(op2).append(" ").append(token.charAt(0)).append(" ").append(op1).append(" = ");
                if (operationResult == 0) {
                    calculationSteps.append("You can't divide by zero");
                    return calculationSteps.toString();
                }
                stack.push(operationResult);
                calculationSteps.append(operationResult).append("\n");
                i++;
            }
        }
        calculationSteps.append("Answer: ").append(stack.pop());
        return calculationSteps.toString();
    }
}
