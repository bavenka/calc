package com.company;

import com.company.algorithms.Rpn;
import com.company.algorithms.Converter;
import com.company.constant.Constant;
import com.company.parser.Parser;
import com.company.reader.Reader;
import com.company.validator.Validator;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println(Constant.HELP_MESSAGE);
        while (true) {
            System.out.print(Constant.MESSAGE_ENTER);
            String line = Reader.readInfixForm();
            if (line.equals("/q")) {
                System.exit(0);
            }
            boolean flag = true;
            while (flag) {
                if (!Validator.isExpressionValid(line)) {
                    System.out.println(Constant.MESSAGE_EXPRESSION_NOT_VALID);
                    flag = false;
                }
                if (!Validator.areBracketsBalanced(line)) {
                    System.out.println(Constant.MESSAGE_BRACKETS_NOT_VALID);
                    flag = false;
                }
                if (flag) {
                    System.out.println(Constant.CALCULATION_START_MESSAGE);
                    String splitInfixForm = Parser.split(line);
                    String postfixForm = Converter.toRpn(splitInfixForm);
                    String answer = Rpn.calculate(postfixForm);
                    System.out.println(answer);
                    System.out.println(Constant.CALCULATION_FINISH_MESSAGE);
                    flag = false;
                }
            }
        }
    }
}
